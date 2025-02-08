package net.magicalspace.entity;

import net.magicalspace.init.MagicalspaceModParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.magicalspace.init.MagicalspaceModEntities;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public class MobDarkStarlightMageEntity extends Monster {
	private boolean swinging;
	private int attackCooldown;
	private LivingEntity currentTarget;
	private int particleAge;

	public boolean isSwinging() { // 添加这个方法
		return swinging;
	}

	public void setSwinging(boolean swinging) { // 添加这个方法
		this.swinging = swinging;
	}
	public MobDarkStarlightMageEntity(EntityType<MobDarkStarlightMageEntity> type, Level world) {

		super(type, world);
		this.attackCooldown = 0;
		xpReward = 5;
		setNoAi(false);
		this.swinging = false;
	}


	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, VillagerStarlightEntity.class, true, false)); // 添加对 VillagerStarlightEntity 的攻击目标
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
		this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(5, new HurtByTargetGoal(this).setAlertOthers());
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(7, new FloatGoal(this));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.death"));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 50);
		builder = builder.add(Attributes.ARMOR, 2);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 8);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.2);
		builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.2);
		return builder;
	}
	private Vec3 getRightHandPos() {
		// 右手相对于模型原点的偏移量
		double rightHandOffsetX = -0.75; // 负 X 方向（根据模型定义，右手在负 X 方向）
		double rightHandOffsetY = 1.1;   // Y 方向（高度，根据模型定义）
		double rightHandOffsetZ = 1;   // Z 方向（前后，根据模型定义）

		// 计算右手的世界坐标
		Vec3 pos = position();
		return pos.add(new Vec3(rightHandOffsetX, rightHandOffsetY, rightHandOffsetZ)
				.yRot(-yBodyRot * ((float) Math.PI / 180F)));
	}
	private void generateCoolingParticles() {
		double angle = tickCount * 0.3;
		double radius = 0.8;
		double x = getX() + radius * Math.cos(angle);
		double z = getZ() + radius * Math.sin(angle);
		level().addParticle(MagicalspaceModParticleTypes.PARTICLES_DARK_STARLIGHT.get(),
				x, getY() + 1.5, z,
				0, 0, 0);
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
		event.register(MagicalspaceModEntities.MOB_DARK_STARLIGHT_MAGE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)),
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}
	@Override
	public void tick() {
		super.tick();

		// 攻击冷却处理
		if (attackCooldown > 0) {
			attackCooldown--;

			// 冷却期间生成环绕粒子
			if (level().isClientSide && attackCooldown % 3 == 0) {
				generateCoolingParticles();
			}
		}

		// 处理攻击粒子效果
		if (!level().isClientSide && currentTarget != null) {
			Vec3 startPos = getRightHandPos();
			Vec3 endPos = currentTarget.position().add(0, currentTarget.getBbHeight() / 2, 0);
			Vec3 direction = endPos.subtract(startPos);

			// 生成粒子束
			int particleCount = 20; // 粒子数量
			double step = 1.0 / particleCount;

			for (int i = 0; i < particleCount; i++) {
				double t = i * step;
				Vec3 particlePos = startPos.add(direction.scale(t));

				((ServerLevel) level()).sendParticles(
						MagicalspaceModParticleTypes.PARTICLES_DARK_STARLIGHT.get(),
						particlePos.x, particlePos.y, particlePos.z,
						1, // 每个位置生成1个粒子
						0, 0, 0, // 无随机偏移
						0.1 // 速度
				);
			}

			if (particleAge++ > 10) { // 粒子飞行时间约0.5秒
				currentTarget.hurt(damageSources().mobAttack(this), (float) getAttributeValue(Attributes.ATTACK_DAMAGE));
				currentTarget = null;
				particleAge = 0;
			}
		}
	}
	@Override
	public boolean doHurtTarget(Entity target) {
		if (attackCooldown <= 0 && target instanceof LivingEntity) {
			this.currentTarget = (LivingEntity) target;
			this.attackCooldown = 20; // 1秒冷却
			this.swing(InteractionHand.MAIN_HAND);
			return true;
		}
		return false;
	}

}