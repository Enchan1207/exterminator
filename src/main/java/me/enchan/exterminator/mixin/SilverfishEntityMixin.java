package me.enchan.exterminator.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.mob.SilverfishEntity;

@Mixin(SilverfishEntity.class)
public abstract class SilverfishEntityMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void beforeTick(CallbackInfo ci) {
        ((SilverfishEntity) (Object) this).discard();
    }

}
