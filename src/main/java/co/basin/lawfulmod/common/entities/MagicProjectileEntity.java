package co.basin.lawfulmod.common.entities;

import co.basin.lawfulmod.core.init.EntityTypeInit;
import co.basin.lawfulmod.core.util.ParticleUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.function.Predicate;

public class MagicProjectileEntity extends ProjectileItemEntity {

    //Particle Customization Variables
    private BasicParticleType particle;
    private int count;
    private double speed;
    private Double random;
    private int lifetime = 200;

    private Predicate<BlockRayTraceResult> blockImpactPredicate;
    private Predicate<EntityRayTraceResult> entityImpactPredicate;
    private Predicate<MagicProjectileEntity> tickPredicate;
    public int ticks;

    private int damage;

    public MagicProjectileEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public MagicProjectileEntity(LivingEntity shooter, World worldIn) {
        super(EntityTypeInit.MAGIC_PROJECTILE.get(), shooter, worldIn);
    }

    public MagicProjectileEntity(Vector3d vect, World world) {
        super(EntityTypeInit.MAGIC_PROJECTILE.get(), vect.x, vect.y, vect.z, world);
    }

    public MagicProjectileEntity setParticleCount(int count) { this.count = count; return this; }
    public MagicProjectileEntity setParticle(BasicParticleType particle) { this.particle = particle; return this; }
    public MagicProjectileEntity setDamage(int damage) { this.damage = damage; return this; }
    public MagicProjectileEntity setParticleRandom(Double random) { this.random = random; return this; }
    public MagicProjectileEntity setParticleSpeed(double speed) { this.speed = speed; return this; }
    public MagicProjectileEntity setTickPredicate(Predicate<MagicProjectileEntity> tickPredicate) { this.tickPredicate = tickPredicate; return this; }
    public MagicProjectileEntity setEntityImpactPredicate(Predicate<EntityRayTraceResult> entityImpactPredicate) { this.entityImpactPredicate = entityImpactPredicate; return this; }
    public MagicProjectileEntity setBlockImpactPredicate(Predicate<BlockRayTraceResult> blockImpactPredicate) { this.blockImpactPredicate = blockImpactPredicate; return this; }
    public MagicProjectileEntity setLifetime(int lifetime) { this.lifetime = lifetime; return this; }

    private void doStatusEffect(PlayerEntity player) {
        //statusEffect.onEvent();
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (!this.level.isClientSide()) {
            if (result.getType() == RayTraceResult.Type.ENTITY) {
                Entity entity = ((EntityRayTraceResult)result).getEntity();
                entity.hurt(DamageSource.MAGIC, damage);
                if (entityImpactPredicate != null) {
                    entityImpactPredicate.test((EntityRayTraceResult) result);
                }
                this.remove();
            } else if (result.getType() == RayTraceResult.Type.BLOCK) {
                Fluid fluid = this.level.getFluidState(((BlockRayTraceResult)result).getBlockPos()).getType();
                if (fluid == Fluids.EMPTY) {
                    this.remove();
                    return;
                }
                if (blockImpactPredicate != null) {
                    blockImpactPredicate.test((BlockRayTraceResult) result);
                }
            }
        }
    }

    public void shoot(Vector3d dir, float velocity, float inaccuracy) {
        super.shoot(dir.x, dir.y, dir.z, velocity, inaccuracy);
    }

    @Override
    public void tick() {
        super.tick();
        ticks++;

        if (ticks > lifetime) {
            this.remove();
        }

        if (tickPredicate != null) {
            tickPredicate.test(this);
        }

        if (!this.level.isClientSide()) {
            if (random == null) {
                ParticleUtil.spawnParticles(
                        (ServerWorld) this.level,
                        particle,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        count == 0 ? 1 : count,
                        speed
                );
            } else {
                ParticleUtil.spawnParticles(
                        (ServerWorld) this.level,
                        particle,
                        this.getRandomX(random),
                        this.getRandomY(),
                        this.getRandomZ(random),
                        count == 0 ? 1 : count,
                        speed
                );
            }

        }
    }
}
