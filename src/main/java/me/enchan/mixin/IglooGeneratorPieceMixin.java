package me.enchan.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.enchan.processors.DisinfectionProcessor;
import net.minecraft.structure.IglooGenerator;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;

@Mixin(IglooGenerator.Piece.class)
public abstract class IglooGeneratorPieceMixin {
    @Inject(method = "createPlacementData", at = @At("RETURN"), cancellable = true)
    private static void onCreatePlacementData(
            BlockRotation rotation,
            Identifier identifier,
            CallbackInfoReturnable<StructurePlacementData> cir) {
        if (!identifier.getPath().equals("igloo/bottom")) {
            return;
        }

        // 地下室に消毒Processorを追加
        var data = cir.getReturnValue();
        data.addProcessor(DisinfectionProcessor.INSTANCE);
        cir.setReturnValue(data);
    }
}
