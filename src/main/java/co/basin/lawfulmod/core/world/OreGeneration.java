package co.basin.lawfulmod.core.world;


import co.basin.lawfulmod.core.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class OreGeneration {

    public static void generateOres(final BiomeLoadingEvent event) {
        if(!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))){
            generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.MANA_IRON_ORE.get().defaultBlockState(), 5, 5, 30, 15);
        }
        else if(event.getCategory().equals(Biome.Category.THEEND)){
            generateOre(event.getGeneration(), new BlockMatchRuleTest(Blocks.END_STONE), BlockInit.ENRICHED_MANA_ORE.get().defaultBlockState(), 4, 10, 30, 4);
        }
    }
    private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType,
                             BlockState state, int veinSize, int minHeight, int maxHeight, int amount){
        settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(new OreFeatureConfig(fillerType, state, veinSize))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .squared().count(amount));
    }
}