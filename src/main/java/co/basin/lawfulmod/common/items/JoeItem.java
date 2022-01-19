package co.basin.lawfulmod.common.items;

import co.basin.lawfulmod.core.init.EffectInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class JoeItem extends Item {
    public JoeItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        entity.addEffect(new EffectInstance(EffectInit.BLEEDING.get(), 60, 1));
        return super.use(world, entity, hand);
    }
}
