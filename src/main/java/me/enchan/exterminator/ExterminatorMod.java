package me.enchan.exterminator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.enchan.exterminator.processors.ExterminatorModProcessors;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ExterminatorMod implements ModInitializer {
    public static final String ModId = "exterminator";

    public static final Logger Logger = LoggerFactory.getLogger(ModId);

    private static final RegistryKey<PlacedFeature> infestedOreFeatureKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE,
            Identifier.of("minecraft", "ore_infested"));

    @Override
    public void onInitialize() {
        // カスタムプロセッサを登録する
        ExterminatorModProcessors.initialize();

        // ore_infested をバイオームフィーチャから削除する
        BiomeModifications.create(Identifier.of(ModId, "remove_infested_ore")).add(ModificationPhase.REMOVALS,
                BiomeSelectors.all(),
                ctx -> ctx.getGenerationSettings().removeFeature(infestedOreFeatureKey));
    }
}
