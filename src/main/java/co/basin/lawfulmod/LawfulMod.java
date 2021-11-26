package co.basin.lawfulmod;

import co.basin.lawfulmod.core.init.BlockInit;
import co.basin.lawfulmod.core.init.ContainerTypeInit;
import co.basin.lawfulmod.core.init.ItemInit;
import co.basin.lawfulmod.core.init.TileEntityTypeInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(LawfulMod.MOD_ID)
public class LawfulMod
{
    public static final String MOD_ID = "lawful";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final ItemGroup LAWFUL_GROUP = new LawfulGroup("lawfultab");

    public LawfulMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        ContainerTypeInit.CONTAINER_TYPES.register(bus);
        TileEntityTypeInit.TILE_ENTITY_TYPES.register(bus);

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
