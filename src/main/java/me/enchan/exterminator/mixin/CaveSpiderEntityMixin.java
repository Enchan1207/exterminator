package me.enchan.exterminator.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.mob.CaveSpiderEntity;

@Mixin(CaveSpiderEntity.class)
public abstract class CaveSpiderEntityMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void beforeTick(CallbackInfo ci) {
        ((CaveSpiderEntity) (Object) this).discard();
    }

}
