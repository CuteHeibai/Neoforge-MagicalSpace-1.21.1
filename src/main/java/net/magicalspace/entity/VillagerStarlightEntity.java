package net.magicalspace.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.phys.Vec3;
import net.magicalspace.init.MagicalspaceModItems;
import net.magicalspace.entity.MobDarkStarlightMageEntity;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

public class VillagerStarlightEntity extends PathfinderMob {
	private boolean isInDialog;
	private boolean isTurning;
	private float targetHeadRotation;
	private float targetBodyRotation;
	private float turnProgress;
	private static final double FLEE_SPEED = 0.25;
	private boolean isForcedHome;
	private static final int DAY_HOME_RADIUS = 20;
	private static final int NIGHT_HOME_RADIUS = 3;
	private BlockPos spawnPos;
	private long lastPathCheckTick;
	private static final int DOOR_CHECK_RANGE = 3;
	private static final int HOME_PRECISION = 2; // 到家精度（2x2区域）
	private long lastHealTick;

	public VillagerStarlightEntity(EntityType<? extends PathfinderMob> type, Level world) {
		super(type, world);
		setNoAi(false);
		setPersistenceRequired();
		this.spawnPos = this.blockPosition();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new ReturnHomeGoal(this));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(
				this,
				LivingEntity.class, // 改为检测所有生物
				12.0F,
				1.6,
				1.8,
				e -> e instanceof Monster // 只躲避敌对生物
		));
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.2) {
			@Override
			public boolean canUse() {
				return !isForcedHome && super.canUse();
			}
		});
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	protected void dropCustomDeathLoot(ServerLevel serverLevel, DamageSource source, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(serverLevel, source, recentlyHitIn);
		this.spawnAtLocation(new ItemStack(MagicalspaceModItems.GEM_STARLIGHT.get()));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.death"));
	}
	void checkAndOperateDoors(boolean open) {
		BlockPos center = spawnPos.offset(0, 1, 0);
		for(int x = -DOOR_CHECK_RANGE; x <= DOOR_CHECK_RANGE; x++) {
			for(int z = -DOOR_CHECK_RANGE; z <= DOOR_CHECK_RANGE; z++) {
				BlockPos pos = center.offset(x, 0, z);
				if (level().getBlockState(pos).getBlock() instanceof DoorBlock door) {
					if (door.isOpen(level().getBlockState(pos)) != open) {
						// 同步服务端与客户端状态
						if (!level().isClientSide) {
							door.setOpen(null, level(), level().getBlockState(pos), pos, open);
						}
						// 播放音效
						level().levelEvent(null, open ? 1005 : 1011, pos, 0);
					}
				}
			}
		}
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.2);
		builder = builder.add(Attributes.MAX_HEALTH, 30);
		builder = builder.add(Attributes.ARMOR, 3);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 0);
		builder = builder.add(Attributes.FOLLOW_RANGE, 10);
		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.1);
		return builder;
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
		// 注册实体生成逻辑（需要根据你的需求补充）
	}

	public void setInDialog(boolean inDialog) {
		this.isInDialog = inDialog;
	}
	private void returnToHome() {
		// 路径优化：直接移动到家中心
		this.getNavigation().moveTo(
				spawnPos.getX() + 0.5,
				spawnPos.getY(),
				spawnPos.getZ() + 0.5,
				1.5
		);
	}
	private BlockPos findValidPosition(BlockPos start) {
		// 三维搜索可站立位置
		for(int y = 0; y < 3; y++) {
			BlockPos pos = start.above(y);
			if(level().getBlockState(pos).isAir() &&
					level().getBlockState(pos.below()).isSolid()) {
				return pos;
			}
		}
		return start; // 保底返回
	}

	@Override
	public void tick() {
		super.tick();
		if (!level().isClientSide && level().getGameTime() - lastHealTick > 20) {
			this.heal(3.0F);
			lastHealTick = level().getGameTime();
		}
		if (spawnPos == null) spawnPos = this.blockPosition();
		if (isInDialog) {
			this.getNavigation().stop();
			this.setNoAi(true);
			if (isTurning) {
				turnProgress += 0.1F; // 调整转身的速度
				turnProgress = Math.min(turnProgress, 1.0F);
				float currentHeadRotation = Mth.lerp(turnProgress, this.getYHeadRot(), targetHeadRotation);
				float currentBodyRotation = Mth.lerp(turnProgress, this.getYHeadRot(), targetBodyRotation);
				this.setYHeadRot(currentHeadRotation);
				this.setYBodyRot(currentBodyRotation);
				if (turnProgress >= 1.0F) {
					isTurning = false;
				}
			}
		} else {
			this.setNoAi(false);
		}
		if (isForcedHome) {
			if (distanceToSqr(Vec3.atCenterOf(spawnPos)) < 4.0) {
				isForcedHome = false;
				this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2);
				// 关门粒子效果
				level().addParticle(ParticleTypes.SMOKE,
						spawnPos.getX() + 0.5, spawnPos.getY() + 1, spawnPos.getZ() + 0.5,
						0, 0.1, 0);
			} else {
				returnToHome();
			}
		}
		if (!level().isClientSide) {
			long time = level().getDayTime() % 24000;
			boolean isDay = time < 13000;
			int currentRadius = isDay ? DAY_HOME_RADIUS : NIGHT_HOME_RADIUS;

			// 强制回家检测
			if (distanceToSqr(Vec3.atCenterOf(spawnPos)) > currentRadius * currentRadius) {
				returnToHome();
				isForcedHome = true;
			}

			// 路径有效性检查（每20tick执行一次）
			if (level().getGameTime() - lastPathCheckTick > 20) {
				if (!this.getNavigation().isDone() &&
						this.getNavigation().getPath() != null &&
						this.getNavigation().getPath().isDone()) {
					navigateToRandomPoint();
				}
				lastPathCheckTick = level().getGameTime();
			}
		}
		if (!level().isClientSide && !isForcedHome) {
			long time = level().getDayTime() % 24000;
			boolean isDay = time < 13000;

			if (isDay) {
				// 开门逻辑（每天早晨触发一次）
				if (time % 24000 < 100 && distanceToSqr(Vec3.atCenterOf(spawnPos)) < 9.0) {
					level().addParticle(ParticleTypes.HAPPY_VILLAGER,
							spawnPos.getX() + 0.5, spawnPos.getY() + 1, spawnPos.getZ() + 0.5,
							0, 0.1, 0);
				}

				if (random.nextFloat() < 0.8 && this.getNavigation().isDone()) {
					navigateToRandomPoint();
				}
			} else {
				// 夜间强制回家
				if (distanceToSqr(Vec3.atCenterOf(spawnPos)) > 9.0) {
					returnToHome();
				}
			}
		}
		if (isForcedHome &&
				Math.abs(spawnPos.getX() - this.getX()) < HOME_PRECISION &&
				Math.abs(spawnPos.getZ() - this.getZ()) < HOME_PRECISION) {
			this.getNavigation().stop();
			this.setPos(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);
		}
	}
	private boolean isNightTime() {
		long time = this.level().getDayTime() % 24000;
		return time >= 13000 && time < 23000;
	}
	private void navigateToRandomPoint() {
		BlockPos target = findValidPosition(
				spawnPos.offset(
						random.nextInt(DAY_HOME_RADIUS*2) - DAY_HOME_RADIUS,
						0,
						random.nextInt(DAY_HOME_RADIUS*2) - DAY_HOME_RADIUS
				)
		);
		this.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), 1.2);
	}

	public void facePlayer(Player player) {
		if (!isTurning) {
			float targetRotation = player.getYHeadRot() + 180;
			// 确保旋转角度在合理范围内
			if (Math.abs(this.getYHeadRot() - targetRotation) > 180) {
				targetRotation -= 360;
			}
			targetHeadRotation = targetRotation;
			targetBodyRotation = targetRotation;
			isTurning = true;
			turnProgress = 0.0F;
		}
	}

	public void stopMoving() {
		this.getNavigation().stop();
		this.setDeltaMovement(0, 0, 0); // 停止速度
	}
	private boolean isDayTime() {
		long time = this.level().getDayTime() % 24000;
		return time < 13000;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.getDirectEntity() instanceof MobDarkStarlightMageEntity) {
			// 调用父类的 hurt 方法，但将伤害值设置为 1
			return super.hurt(source, 1.0F);
		}
		if (source.getDirectEntity() instanceof MobDarkStarlightMageEntity) {
			this.isForcedHome = true;
			this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(FLEE_SPEED);
			returnToHome(); // 立即启动回家
			return super.hurt(source, 5.0F);
		}
		if (source.getDirectEntity() instanceof LivingEntity) {
			amount = Math.min(amount, 3.0F); // 最大伤害3点
			// 清除负面效果
			this.removeAllEffects();
		}
		return super.hurt(source, amount); // 其他伤害正常处理
	}
	static class ReturnHomeGoal extends Goal {
		private final VillagerStarlightEntity villager;

		public ReturnHomeGoal(VillagerStarlightEntity villager) {
			this.villager = villager;
		}

		@Override
		public boolean canUse() {
			return villager.isForcedHome ||
					villager.isNightTime() ||
					villager.distanceToSqr(Vec3.atCenterOf(villager.spawnPos)) >
							(villager.isDayTime() ? VillagerStarlightEntity.DAY_HOME_RADIUS : VillagerStarlightEntity.NIGHT_HOME_RADIUS) *
									(villager.isDayTime() ? VillagerStarlightEntity.DAY_HOME_RADIUS : VillagerStarlightEntity.NIGHT_HOME_RADIUS);
		}

		@Override
		public void start() {
			villager.returnToHome();
			villager.checkAndOperateDoors(true); // 通过实例调用
		}

		@Override
		public void stop() {
			villager.checkAndOperateDoors(false); // 通过实例调用
		}
	}
	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		if (spawnPos != null) {
			tag.putLong("SpawnPos", spawnPos.asLong());
		}
	}
	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("SpawnPos")) {
			spawnPos = BlockPos.of(tag.getLong("SpawnPos"));
		} else {
			spawnPos = this.blockPosition();
		}
	}

}