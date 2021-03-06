package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.blocks.CovenantPedestal;
import co.basin.lawfulmod.common.blocks.EmbossmentTableBlock;
import co.basin.lawfulmod.common.blocks.PactingTableBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

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

    public static final RegistryObject<Block> ENRICHED_MANA_ORE = BLOCKS.register("enriched_mana_ore",
            () -> new Block(AbstractBlock.Properties
                    .of(Material.HEAVY_METAL, MaterialColor.COLOR_PURPLE)
                    .harvestLevel(4)
                    .harvestTool(ToolType.PICKAXE)
                    .sound(SoundType.METAL)
                    .strength(14f)
                    .requiresCorrectToolForDrops()));
  
    public static final RegistryObject<Block> PACTING_TABLE_BLOCK = BLOCKS.register("pacting_table",
            () -> new PactingTableBlock(AbstractBlock.Properties
                    .of(Material.STONE)
                    .strength(3, 10)
                    .sound(SoundType.ANCIENT_DEBRIS)
            ));

    public static final RegistryObject<Block> COVENANT_PEDESTAL = BLOCKS.register("covenant_pedestal",
            () -> new CovenantPedestal(AbstractBlock.Properties
                    .of(Material.STONE, MaterialColor.COLOR_BLACK)
                    .sound(SoundType.ANCIENT_DEBRIS)
            ));

    public static final RegistryObject<Block> EMBOSSMENT_TABLE = BLOCKS.register("embossment_table",
            () -> new EmbossmentTableBlock(AbstractBlock.Properties
                    .of(Material.STONE)
                    .sound(SoundType.BASALT)
            ));
}
