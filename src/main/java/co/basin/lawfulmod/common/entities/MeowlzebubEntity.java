package co.basin.lawfulmod.common.entities;

import co.basin.lawfulmod.common.goals.RangedMagicAttackGoal;
import co.basin.lawfulmod.core.packets.MeowlzebubShieldPKT;
import co.basin.lawfulmod.core.util.PacketUtil;
import co.basin.lawfulmod.core.util.ParticleUtil;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MeowlzebubEntity extends MonsterEntity implements IAnimatable, IRangedAttackMob {
    private AnimationFactory factory = new AnimationFactory(this);

    private boolean isAttacking;

    private boolean hasShield;
    private int shieldHealth;
    private final int maxShieldHealth = 10;

    public MeowlzebubEntity(EntityType<? extends MonsterEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.FOLLOW_RANGE, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.23F)
                .add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    final Vector3d[] points = {
            new Vector3d(1, 0, 0),
            new Vector3d(0, 0, 1),
            new Vector3d(-1, 0, 0),
            new Vector3d(0, 0, -1),
            new Vector3d(1, 0, 0),
    };

    @Override
    public boolean isNoGravity() {
        BlockPos pos = this.blockPosition();
        BlockState standingBlock = this.level.getBlockState(pos.below(2));
        return standingBlock.entityCanStandOn(this.level.getChunkAt(pos), pos.below(2), this);
    }

    @Override
    public void tick() {
        if (!this.level.isClientSide) {
            BlockPos pos = this.blockPosition();
            BlockState onBlock = this.level.getBlockState(pos.below());
            if (onBlock.entityCanStandOn(this.level.getChunkAt(pos), pos.below(), this)) {
                setPos(pos.getX(), pos.getY() + 1.5, pos.getZ());
            }
        } else {
            if (hasShield) {
                for (Vector3d point : points) {
                    point = point.xRot(this.level.getGameTime() % 360);
                    point = point.yRot(this.level.getGameTime() % 360);
                    point = point.zRot(this.level.getGameTime() % 360);
                    Vector3d pos = this.position().add(point);
                    ParticleUtil.spawnStillParticles((ClientWorld) this.level, ParticleTypes.HEART, pos.x, pos.y + 1, pos.z);
                }
            }
        }
        super.tick();
    }

    public void setHasShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    @Override
    protected int calculateFallDamage(float p_225508_1_, float p_225508_2_) {
        return 0;
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(4, new RangedMagicAttackGoal<>(this, 1.0D, 20, 15.0F));
    }

    private <E extends IAnimatable> PlayState idlePredicate(AnimationEvent<E> event)
    {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event) {
        if (isAttacking) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("armbob", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "idle", 0, this::idlePredicate));
        data.addAnimationController(new AnimationController(this, "attack", 2, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        if (hasShield) {
            if ((shieldHealth -= damage) <= 0) {
                hasShield = false;
                if (!this.level.isClientSide()) {
                    PacketUtil.sendToNearby(this.level, this, new MeowlzebubShieldPKT(this.getId(), hasShield));
                }
            }
            return false;
        }
        return super.hurt(source, damage);
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float damage) {
        int rand = this.random.nextInt(100);
        if (rand < 10) {
            performDefenseSpell(entity, damage);
            return;
        }

        if (rand < 20){
            performHeavyAttackSpell(entity, damage);
            return;
        }

        performLightAttackSpell(entity, damage);
    }

    private void performLightAttackSpell(LivingEntity entity, float damage) {
        MagicProjectileEntity projectile = new MagicProjectileEntity(entity, this.level)
                .setDamage((int) damage)
                .setParticle(ParticleTypes.FLAME);
        projectile.shoot(entity.getLookAngle(), 2, 1);
        this.level.addFreshEntity(projectile);
    }

    private void performDefenseSpell(LivingEntity entity, float damage) {
        this.hasShield = true;
        shieldHealth = maxShieldHealth;
        if (!this.level.isClientSide()) {
            PacketUtil.sendToNearby(this.level, this, new MeowlzebubShieldPKT(this.getId(), hasShield));
        }
    }

    private void performHeavyAttackSpell(LivingEntity entity, float damage) {
        final Vector3d[] points = {
                new Vector3d(1, 0, 0),
                new Vector3d(0, 0, 1),
                new Vector3d(-1, 0, 0),
                new Vector3d(0, 0, -1),
        };
        MagicProjectileEntity projectile = new MagicProjectileEntity(entity.position().add(0, 0.5, 0), this.level)
                .setDamage(13)
                .setParticle(ParticleTypes.CLOUD)
                .setTickPredicate((e) -> {
                    for (int y = 0; y < 10; y++) {
                        for (Vector3d point : points) {
                            point = point.yRot(e.ticks % 360);
                            Vector3d pos = e.position().add(point.multiply(y * 0.2, 0, y * 0.2)).add(0, y + (this.level.getRandom().nextDouble() - 0.5D), 0);
                            ParticleUtil.spawnParticles((ServerWorld) this.level, ParticleTypes.CLOUD, pos.x, pos.y, pos.z, 3, 0);
                        }
                    }
                    return true;
                });
        projectile.setNoGravity(true);
        projectile.shoot(entity.getLookAngle(), 0.25F, 1);
        this.level.addFreshEntity(projectile);
    }
}