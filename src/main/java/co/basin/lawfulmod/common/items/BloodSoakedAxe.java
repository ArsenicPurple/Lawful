package co.basin.lawfulmod.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.world.World;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Item Class
 * It seems to grow stronger the more blood it spills.
 */
public class BloodSoakedAxe extends SwordItem {

    private int rampage = 0;
    private OffsetDateTime time = OffsetDateTime.now();

    private final UUID STRENGTH_EFFECT_ID = UUID.randomUUID();

    public BloodSoakedAxe(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (entity instanceof LivingEntity) {
            if (rampage > 0 && rampage != 3) {
                final UUID STRENGTH_EFFECT_ID = UUID.randomUUID();
                final AttributeModifier STRENGTH_EFFECT = new AttributeModifier(STRENGTH_EFFECT_ID, "Rampage Attack Effect", rampage * 2, AttributeModifier.Operation.ADDITION);
                ModifiableAttributeInstance modifiableattributeinstance = player.getAttribute(Attributes.ATTACK_DAMAGE);
                assert modifiableattributeinstance != null;
                modifiableattributeinstance.addTransientModifier(STRENGTH_EFFECT);
            }
        }
        return false;
    }

    /**
     * Runs ~every tick (1/4 second)
     * @param stack The stack which is ticking.
     * @param world The world the tick is taking place in.
     * @param entity The entity that has the ticking stack.
     * @param inventorySlot The place in the inventory the stack is in.
     * @param isSelected Whether the stack is currently in the hand.
     */
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int inventorySlot, boolean isSelected) {
        if (rampage > 0 && OffsetDateTime.now().isAfter(time)) {
            if (entity instanceof PlayerEntity) {
                ModifiableAttributeInstance modifiableattributeinstance = ((PlayerEntity)entity).getAttribute(Attributes.ATTACK_DAMAGE);
                if (modifiableattributeinstance.getModifier(STRENGTH_EFFECT_ID) != null) {
                    modifiableattributeinstance.removeModifier(STRENGTH_EFFECT_ID);
                }
            }
        }
        super.inventoryTick(stack, world, entity, inventorySlot, isSelected);
    }
}
