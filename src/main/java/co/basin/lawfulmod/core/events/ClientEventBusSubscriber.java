package co.basin.lawfulmod.core.events;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.client.guis.PactingTableScreen;
import co.basin.lawfulmod.client.render.entity.LukkiRenderer;
import co.basin.lawfulmod.client.render.entity.MeowlzebubRenderer;
import co.basin.lawfulmod.client.render.tileentitiy.CovenantPedestalTileEntityRenderer;
import co.basin.lawfulmod.core.init.ContainerTypeInit;
import co.basin.lawfulmod.core.init.EntityTypeInit;
import co.basin.lawfulmod.core.init.TileEntityTypeInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.example.client.renderer.entity.ExampleGeoRenderer;
import software.bernie.example.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.register(ContainerTypeInit.PACTING_TABLE.get(), PactingTableScreen::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.MEOWLZEBUB.get(), MeowlzebubRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.LUKKI.get(), LukkiRenderer::new);

        ItemRenderer renderer = event.getMinecraftSupplier().get().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.MAGIC_PROJECTILE.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));

        ClientRegistry.bindTileEntityRenderer(TileEntityTypeInit.COVENANT_PEDESTAL.get(), CovenantPedestalTileEntityRenderer::new);
    }
}
