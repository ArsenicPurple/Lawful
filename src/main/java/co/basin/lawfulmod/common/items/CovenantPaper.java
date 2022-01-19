package co.basin.lawfulmod.common.items;

import co.basin.lawfulmod.core.util.ItemNBTUtil;
import co.basin.lawfulmod.core.util.PactUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

/**
 * TODO
 * BUGLIST
 * Health Boost pacting effect doesn't work properly
 * Sharpness -> Serrated ✔
 * Bane of Arthropods -> Leech ✔
 * Fire Aspect -> Blazing ✔
 * Efficiency -> Momentum ✔
 * Knockback -> Trapper ✔
 * Smite -> Judgement
 * Looting -> Scavenger
 * Sweeping Edge -> Transendance ✔
 * Unbreaking -> Resiliance
 * Fortune ->
 * Silk Touch ->
 * Power -> Chaining
 * Flame -> Combustion
 * Punch -> Launching
 * Infinity -> Materialize
 * Mending ->
 * Loyalty ->
 * Channeling ->
 * Riptide ->
 * Impaling ->
 * Multishot ->
 * Piercing -> Concentration
 * Quick Charge ->
 * Projectile Protection -> Phasing
 *
 * UPDATES - Not Ordered
 * Embossments
 * Finish Meowlzebub Animations and AI
 * Change Animation When a Ritual Finished
 * Add Advancements
 * Finish Lukki Animation and AI
 */

public class CovenantPaper extends SoulboundItem {
    private static final String TAG_IS_TOGGLABLE = "isTogglable";
    private static final String TAG_IS_ACTIVE = "isActive";
    private static final String TAG_PACT_TYPE = "pactType";
    private static final String TAG_LAST_TICK_BUFF_APPLIED = "lastTickBuffApplied";
    private static final String TAG_LAST_TICK_COVENANT_BROKE = "lastTickCovenantBroke";

    public CovenantPaper(Properties properties) {
        super(properties);
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        return getPactType(stack) != -1 ? PactUtils.getName(getPactType(stack)) : super.getName(stack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        if (isTogglable(stack)) { setActive(stack, !isActive(stack)); }
        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public boolean isTickingInBoundPlayer(ItemStack stack, PlayerEntity player) {
        return player.getUUID().equals(super.getSoulboundUUID(stack));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int inventorySlot, boolean isSelected) {
        if (world.isClientSide) { return; }
        if (!(entity instanceof PlayerEntity)) { return; }
        super.inventoryTick(stack, world, entity, inventorySlot, isSelected);

        if (!isTickingInBoundPlayer(stack, (PlayerEntity) entity)) { return; }

        if (isActive(stack) && getPactType(stack) != -1) {
            // Checks random slot in inventory for the Covenant Item
            int invSize = ((PlayerEntity)entity).inventory.getContainerSize();
            int random = world.random.nextInt(invSize + 1);
            // Skips tick the slot this Item Stack is in was selected
            if (random == inventorySlot) { return; }
            if (PactUtils.isItemBreakingCovenant(((PlayerEntity)entity).inventory.getItem(random), getPactType(stack))) {
                entity.hurt(new DamageSource("lawful.broken_covenant"), 5);
                setTagLastTickCovenantBroke(stack, world.getGameTime());
            }

            // Skips the tick if the Covenant has recently been broken or the buff was recently applied
            if (world.getGameTime() - getLastTickCovenantBroke(stack) < 600) { return; }
            if (world.getGameTime() - getLastTickBuffApplied(stack) < 160) { return; }

            EffectInstance[] instances = PactUtils.getPactEffects(getPactType(stack));
            if (instances == null) { return; }

            for (EffectInstance instance : instances) {
                ((PlayerEntity) entity).addEffect(instance);
            }
            setLastTickBuffApplied(stack, world.getGameTime());
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isActive(stack);
    }

    public void setLastTickBuffApplied(ItemStack stack, long lastTickBuffApplied) {
        ItemNBTUtil.setLong(stack, TAG_LAST_TICK_BUFF_APPLIED, lastTickBuffApplied);
    }

    public long getLastTickBuffApplied(ItemStack stack) {
        return ItemNBTUtil.getLong(stack, TAG_LAST_TICK_BUFF_APPLIED, -1);
    }

    public void setTagLastTickCovenantBroke(ItemStack stack, long lastTickCovenantBroke) {
        ItemNBTUtil.setLong(stack, TAG_LAST_TICK_COVENANT_BROKE, lastTickCovenantBroke);
    }

    public long getLastTickCovenantBroke(ItemStack stack) {
        return ItemNBTUtil.getLong(stack, TAG_LAST_TICK_COVENANT_BROKE, -1);
    }

    public void setPactType(ItemStack stack, int type) {
        ItemNBTUtil.setInt(stack, TAG_PACT_TYPE, type);
    }

    public int getPactType(ItemStack stack) {
        return ItemNBTUtil.getInt(stack, TAG_PACT_TYPE, -1);
    }

    public void setActive(ItemStack stack, boolean active) {
        ItemNBTUtil.setBoolean(stack, TAG_IS_ACTIVE, active);
    }

    public boolean isActive(ItemStack stack) {
        return ItemNBTUtil.getBoolean(stack, TAG_IS_ACTIVE, false);
    }

    public void setTogglable(ItemStack stack) { ItemNBTUtil.setBoolean(stack, TAG_IS_TOGGLABLE, true); }

    public boolean isTogglable(ItemStack stack) {
        return ItemNBTUtil.getBoolean(stack, TAG_IS_TOGGLABLE, false);
    }
}
