package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.Embossment;
import co.basin.lawfulmod.core.util.EmbossmentUtil;
import co.basin.lawfulmod.core.util.ItemNBTUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

public class ChainingEmbossment extends Embossment {
    public ChainingEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.BOW, slotTypes);
    }

    private static final String TAG_CHAIN_COUNT = "chainCount";

    @Override
    public Enchantment getParent() {
        return Enchantments.POWER_ARROWS;
    }

    @Override
    public void doPostAttack(LivingEntity attacker, LivingEntity attacked, int enchantmentLevel) {
        ItemStack bow;
        if (!(attacker.getMainHandItem().getItem() instanceof BowItem)) { return; }
        bow = attacker.getMainHandItem();

        AxisAlignedBB box = EmbossmentUtil.getBoundingBox(attacked, 5, 5);
        List<Entity> entities = attacker.level.getEntities(attacked, box, (entity) -> entity instanceof LivingEntity && !entity.equals(attacker));
        Entity entity = EmbossmentUtil.getClosestEntity(entities, attacked.position());

        if (entity == null) { return; }
        incrementChainCount(bow);
        if (getChainCount(bow) > 4) { return; }

        ItemStack itemstack = new ItemStack(Items.ARROW);
        ArrowItem arrowitem = (ArrowItem) Items.ARROW;
        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(attacked.level, itemstack, attacked);
        abstractarrowentity.setOwner(attacker);

        Vector3d angle = entity.position().subtract(attacked.position());
        abstractarrowentity.shoot(angle.x, angle.y, angle.z, 3.0F, 0.0F);

        int power = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bow);
        int punch = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bow);
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bow) > 0) { abstractarrowentity.setSecondsOnFire(100); }
        if (power > 0) { abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage() + (double)power * 0.5D + 0.5D); }
        if (punch > 0) { abstractarrowentity.setKnockback(punch); }

        attacker.level.addFreshEntity(abstractarrowentity);
        attacker.level.playSound(null, attacked.getX(), attacked.getY(), attacked.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (attacked.level.random.nextFloat() * 0.4F + 1.2F) + 0.5F);
    }

    private void incrementChainCount(ItemStack stack) {
        int count = ItemNBTUtil.getInt(stack, TAG_CHAIN_COUNT, 0);
        ItemNBTUtil.setInt(stack, TAG_CHAIN_COUNT, count + 1);
    }

    private int getChainCount(ItemStack stack) {
        return ItemNBTUtil.getInt(stack, TAG_CHAIN_COUNT, 0);
    }

    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Subscriber {

        @SubscribeEvent
        public static void onArrowNock(ArrowNockEvent event) {
            if (!ItemNBTUtil.verifyExistance(event.getBow(), TAG_CHAIN_COUNT)) { return; }
            ItemNBTUtil.setInt(event.getBow(), TAG_CHAIN_COUNT, 0);
        }
    }
}
