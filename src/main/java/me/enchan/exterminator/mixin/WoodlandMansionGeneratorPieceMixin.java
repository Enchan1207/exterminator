package me.enchan.exterminator.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.enchan.exterminator.processors.DisinfectionProcessor;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.WoodlandMansionGenerator;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;

@Mixin(WoodlandMansionGenerator.Piece.class)
public abstract class WoodlandMansionGeneratorPieceMixin {
    @Inject(method = "createPlacementData", at = @At("RETURN"), cancellable = true)
    private static void onCreatePlacementData(
            BlockMirror mirror, BlockRotation rotation,
            CallbackInfoReturnable<StructurePlacementData> cir) {

        // NOTE: 1.20.6 時点で、森の洋館に生成されるパーツのうち消毒が必要なブロックは 1x2_s2 のみ。
        // NOTE: ただし、これ以外に存在した場合に消毒漏れが発生すると大変なので、Processorも割り当てる

        // 消毒Processorを追加
        var data = cir.getReturnValue();
        data.addProcessor(DisinfectionProcessor.INSTANCE);
        cir.setReturnValue(data);
    }
}
