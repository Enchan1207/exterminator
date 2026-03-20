package me.enchan.processors;

import com.mojang.serialization.MapCodec;

import me.enchan.ExterminatorMod;
import net.minecraft.block.InfestedBlock;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplate.StructureBlockInfo;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class DisinfectionProcessor extends StructureProcessor {
    public static final DisinfectionProcessor INSTANCE = new DisinfectionProcessor();

    public static final MapCodec<DisinfectionProcessor> CODEC = MapCodec.unit(INSTANCE);

    @Override
    public StructureBlockInfo process(
            WorldView world,
            BlockPos pos,
            BlockPos pivot,
            StructureBlockInfo originalBlockInfo,
            StructureBlockInfo currentBlockInfo,
            StructurePlacementData data) {
        var state = currentBlockInfo.state();
        var block = state.getBlock();

        if (!(block instanceof InfestedBlock)) {
            return currentBlockInfo;
        }

        // 感染ブロックを通常ブロックに戻す
        var disinfected = ((InfestedBlock) block).toRegularState(state);
        ExterminatorMod.Logger
                .info("構造物消毒: 座標 %s の %s を %s に置き換えました".formatted(
                        currentBlockInfo.pos().toShortString(),
                        block.getName().getString(),
                        disinfected.getBlock().getName().getString()));

        return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos(), disinfected, currentBlockInfo.nbt());
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return ExterminatorModProcessors.DISINFECT;
    }
}
