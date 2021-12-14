package co.basin.lawfulmod;

import co.basin.lawfulmod.common.entities.LukkiEntity;
import co.basin.lawfulmod.common.entities.MeowlzebubEntity;
import co.basin.lawfulmod.core.init.*;
import co.basin.lawfulmod.core.packets.MeowlzebubShieldPKT;
import co.basin.lawfulmod.core.world.OreGeneration;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(LawfulMod.MOD_ID)
public class LawfulMod
{
    public static final String MOD_ID = "lawful";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final ItemGroup LAWFUL_GROUP = new LawfulGroup("lawfultab");
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL_INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public LawfulMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::createAttributes);
        EnchantmentInit.ENCHANTMENTS.register(bus);
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);

        //makes the ore generate
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);

        ContainerTypeInit.CONTAINER_TYPES.register(bus);
        TileEntityTypeInit.TILE_ENTITY_TYPES.register(bus);
        EntityTypeInit.ENTITY_TYPES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void createAttributes(final EntityAttributeCreationEvent event) {
        event.put(EntityTypeInit.MEOWLZEBUB.get(), MeowlzebubEntity.createAttributes().build());

        event.put(EntityTypeInit.LUKKI.get(), LukkiEntity.createAttributes().build());
    }

    private void setup(final FMLCommonSetupEvent event) {
        CHANNEL_INSTANCE.registerMessage(0, MeowlzebubShieldPKT.class, MeowlzebubShieldPKT::encode, MeowlzebubShieldPKT::new, MeowlzebubShieldPKT::handle);
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
