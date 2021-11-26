package co.basin.lawfulmod.core.events;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.client.guis.PactingTableScreen;
import co.basin.lawfulmod.client.render.tileentitiy.CovenantPedestalTileEntityRenderer;
import co.basin.lawfulmod.core.init.BlockInit;
import co.basin.lawfulmod.core.init.ContainerTypeInit;
import co.basin.lawfulmod.core.init.ItemInit;
import co.basin.lawfulmod.core.init.TileEntityTypeInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.register(ContainerTypeInit.PACTING_TABLE.get(), PactingTableScreen::new);

        RenderTypeLookup.setRenderLayer(BlockInit.COVENANT_PEDESTAL.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInit.PACTING_TABLE_BLOCK.get(), RenderType.cutout());

        ClientRegistry.bindTileEntityRenderer(TileEntityTypeInit.COVENANT_PEDESTAL.get(), CovenantPedestalTileEntityRenderer::new);
    }
}
