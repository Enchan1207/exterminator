package me.enchan.exterminator.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.enchan.exterminator.ExterminatorMod;
import net.minecraft.util.math.random.Random;

@Mixin(targets = "net.minecraft.structure.WoodlandMansionGenerator$FirstFloorRoomPool")
public abstract class FirstFloorRoomPoolMixin {
    @Inject(method = "getMediumSecretRoom", at = @At("RETURN"), cancellable = true)
    public void onGetMediumSecretRoom(Random random, CallbackInfoReturnable<String> cir) {
        ExterminatorMod.Logger.info("構造物消毒: 森の洋館 偽エンドポータル部屋の生成を抑止しました");
        cir.setReturnValue("1x2_s1");
    }
}
