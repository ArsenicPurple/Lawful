package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.tileentities.CovenantPedestalTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class TileEntityTypeInit {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, LawfulMod.MOD_ID);

    public static final RegistryObject<TileEntityType<CovenantPedestalTileEntity>> COVENANT_PEDESTAL = register(
            "covenant_pedestal",
            CovenantPedestalTileEntity::new,
            BlockInit.COVENANT_PEDESTAL
    );

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> factory, RegistryObject<? extends Block> block) {
        return TILE_ENTITY_TYPES.register(name, () -> TileEntityType.Builder.of(factory, block.get()).build(null));
    }
}
