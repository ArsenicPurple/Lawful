package co.basin.lawfulmod;

import co.basin.lawfulmod.core.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LawfulMod.MOD_ID)
public class LawfulMod
{
    public static final String MOD_ID = "lawful";
    private static final Logger LOGGER = LogManager.getLogger();
    public static final ItemGroup LAWFUL_GROUP = new LawfulGroup("lawfultab");

    public LawfulMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        ItemInit.ITEMS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    public static class LawfulGroup extends ItemGroup {
        public LawfulGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack makeIcon() {
            return ItemInit.ADVANCED_EXAMPLE_ITEM.get().getDefaultInstance();
        }
    }
}
