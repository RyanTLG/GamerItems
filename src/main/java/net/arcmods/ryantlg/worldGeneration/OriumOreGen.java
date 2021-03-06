package net.arcmods.ryantlg.worldGeneration;

import net.arcmods.ryantlg.gameritems;
import net.arcmods.ryantlg.blocks.oriumBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

public class OriumOreGen {

    private static ConfiguredFeature<?, ?> ORIUM_ORE_CONFIGURED_FEATURE = Feature.ORE.configure(new OreFeatureConfig(
          OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
          oriumBlocks.ORIUM_ORE.getDefaultState(),
          5)); // vein size
 
  	public static PlacedFeature ORIUM_ORE_PLACED_FEATURE = ORIUM_ORE_CONFIGURED_FEATURE.withPlacement(
      	CountPlacementModifier.of(5), // number of veins per chunk
      	SquarePlacementModifier.of(), // spreading horizontally
      	HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(35))); // height
 
    

    public static void register() {

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
        new Identifier("gameritems", "orium_ore"), ORIUM_ORE_CONFIGURED_FEATURE);
    	Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("gameritems", "orium_ore"),
        	ORIUM_ORE_PLACED_FEATURE);
    	BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
        	RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            	new Identifier("gameritems", "orium_ore")));

		gameritems.LOGGER.info("OriumOreGen loaded");
    }
}
