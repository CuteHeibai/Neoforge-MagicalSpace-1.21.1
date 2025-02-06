package net.magicalspace.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
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

	public VillagerStarlightEntity(EntityType<? extends PathfinderMob> type, Level world) {
		super(type, world);
		setNoAi(false);
		setPersistenceRequired();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
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

	@Override
	public void tick() {
		super.tick();
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

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.getDirectEntity() instanceof MobDarkStarlightMageEntity) {
			// 调用父类的 hurt 方法，但将伤害值设置为 0
			return super.hurt(source, 0.0F);
		}
		return super.hurt(source, amount); // 其他伤害正常处理
	}
}