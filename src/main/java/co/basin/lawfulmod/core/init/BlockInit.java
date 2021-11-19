package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.blocks.PactingTableBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LawfulMod.MOD_ID);

    public static final RegistryObject<Block> PACTING_TABLE_BLOCK = BLOCKS.register("pacting_table",
            () -> new PactingTableBlock(AbstractBlock.Properties.of(Material.STONE)
                    .strength(3, 10)
                    .sound(SoundType.ANCIENT_DEBRIS)
            ));
}
