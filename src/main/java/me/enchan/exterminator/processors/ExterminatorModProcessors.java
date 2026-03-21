package me.enchan.exterminator.processors;

import com.mojang.serialization.MapCodec;

import me.enchan.exterminator.ExterminatorMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;

public class ExterminatorModProcessors {

        public static final StructureProcessorType<DisinfectionProcessor> DISINFECT = register("disinfect",
                        DisinfectionProcessor.CODEC);

        private static <P extends StructureProcessor> StructureProcessorType<P> register(
                        String id,
                        MapCodec<P> codec) {
                return Registry.register(
                                Registries.STRUCTURE_PROCESSOR,
                                Identifier.of(ExterminatorMod.ModId, id),
                                () -> codec);
        }

        public static void initialize() {
        }
}
