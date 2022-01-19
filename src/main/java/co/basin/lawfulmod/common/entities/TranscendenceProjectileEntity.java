package co.basin.lawfulmod.common.entities;

import co.basin.lawfulmod.core.init.EntityTypeInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class TranscendenceProjectileEntity extends ProjectileItemEntity {

    public static final String TAG_IS_RETURNING = "IsReturning";
    public static final String TAG_OWNER_UUID = "Owner";
    public static final String TAG_SWORD = "Sword";

    private boolean isReturning;
    private PlayerEntity owner;
    private ItemStack sword;

    private long ticksAlive;

    public TranscendenceProjectileEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
        super(type, world);
    }

    public TranscendenceProjectileEntity(PlayerEntity owner, ItemStack sword) {
        super(EntityTypeInit.TRANSCENDENCE_ENTITY.get(), owner.level);
        this.owner = owner;
        this.sword = sword;
    }

    @Override
    public void tick() {
        ticksAlive++;
        if (!isReturning) {
            if (ticksAlive > 60) {
                startReturning();
            }
        }
        super.tick();
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult result) {
        startReturning();
        super.onHitBlock(result);
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        if (result.getEntity().getUUID().equals(owner.getUUID())) {
            if (owner.getMainHandItem().isEmpty()) {
                owner.inventory.add(owner.inventory.selected, sword);
            } else {
                owner.addItem(sword);
            }
            this.remove();
        } else {
            result.getEntity().hurt(new DamageSource("lawful.transcendence"), 8);
            startReturning();
        }

        super.onHitEntity(result);
    }

    private void startReturning() {
        Vector3d direction = this.position().subtract(owner.position());
        this.setDeltaMovement(direction);
        isReturning = true;
    }

    public void shoot(Vector3d dir, float velocity, float inaccuracy) {
        super.shoot(dir.x, dir.y, dir.z, velocity, inaccuracy);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    public boolean isReturning() {
        return this.isReturning;
    }

    public PlayerEntity getOwner() {
        return this.owner;
    }

    public ItemStack getSword() {
        return this.sword;
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    public boolean save(CompoundNBT nbt) {
        nbt.putBoolean(TAG_IS_RETURNING, isReturning);
        nbt.putUUID(TAG_OWNER_UUID, owner.getUUID());
        nbt.put(TAG_SWORD, sword.serializeNBT());
        return super.save(nbt);
    }

    @Override
    public void load(CompoundNBT nbt) {
        isReturning = nbt.getBoolean(TAG_IS_RETURNING);
        owner = level.getPlayerByUUID(nbt.getUUID(TAG_OWNER_UUID));
        sword = ItemStack.of(nbt.getCompound(TAG_SWORD));
        super.load(nbt);
    }
}
