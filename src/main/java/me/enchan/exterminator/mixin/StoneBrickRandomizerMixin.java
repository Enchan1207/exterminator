package me.enchan.exterminator.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.random.Random;

@Mixin(targets = "net.minecraft.structure.StrongholdGenerator$StoneBrickRandomizer")
public class StoneBrickRandomizerMixin extends StructurePiece.BlockRandomizer {

    @Override
    public void setBlock(Random random, int x, int y, int z, boolean placeBlock) {
        if (!placeBlock) {
            this.block = Blocks.CAVE_AIR.getDefaultState();
            return;
        }

        float probability = random.nextFloat();
        if (probability < 0.2F) {
            this.block = Blocks.CRACKED_STONE_BRICKS.getDefaultState();
        } else if (probability < 0.5F) {
            this.block = Blocks.MOSSY_STONE_BRICKS.getDefaultState();
        } else {
            this.block = Blocks.STONE_BRICKS.getDefaultState();
        }
    }

}
