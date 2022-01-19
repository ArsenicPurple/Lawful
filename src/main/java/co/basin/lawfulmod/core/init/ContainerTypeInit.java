package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.containers.EmbossmentContainer;
import co.basin.lawfulmod.common.containers.PactingContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, LawfulMod.MOD_ID);

    public static final RegistryObject<ContainerType<PactingContainer>> PACTING_TABLE = register("pacting_table", PactingContainer::new);

    public static final RegistryObject<ContainerType<EmbossmentContainer>> EMBOSSMENT_TABLE = register("embossment_table", EmbossmentContainer::new);

    private static <T extends Container> RegistryObject<ContainerType<T>> register(String name, IContainerFactory<T> factory) {
        return CONTAINER_TYPES.register(name, () -> IForgeContainerType.create(factory));
    }
}
