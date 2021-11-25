package co.basin.lawfulmod.core.events;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.client.guis.PactingTableScreen;
import co.basin.lawfulmod.core.init.ContainerTypeInit;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.register(ContainerTypeInit.PACTING_TABLE.get(), PactingTableScreen::new);
    }
}
