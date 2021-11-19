package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.block.Block;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LawfulMod.MOD_ID);

    public static final RegistryObject<Block> MANA_WOOD = BLOCKS.register("mana_wood",
            () -> new Block(AbstractBlock.Properties
                    .of(Material.WOOD, MaterialColor.COLOR_PURPLE)
                    .harvestLevel(-1)
                    .harvestTool(ToolType.AXE)
                    .sound(SoundType.WOOD)
                    .strength(1f)));

    public static final RegistryObject<Block> MANA_IRON_ORE = BLOCKS.register("mana_iron_ore",
            () -> new Block(AbstractBlock.Properties
                    .of(Material.HEAVY_METAL, MaterialColor.COLOR_PURPLE)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .sound(SoundType.METAL)
                    .strength(8f)
                    .requiresCorrectToolForDrops()));
}
