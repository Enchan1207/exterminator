package me.enchan.exterminator.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.structure.StrongholdGenerator.PortalRoom;

@Mixin(PortalRoom.class)
public abstract class PortalRoomMixin {

    @Shadow
    private boolean spawnerPlaced;

    @Inject(method = "generate", at = @At("HEAD"))
    private void beforeGeneratePortalRoom(CallbackInfo ci) {
        this.spawnerPlaced = true;
    }

}
