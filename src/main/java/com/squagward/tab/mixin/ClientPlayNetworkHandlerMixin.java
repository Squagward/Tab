package com.squagward.tab.mixin;

import com.squagward.tab.hooks.PlayerListHudHook;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow
    private ClientWorld world;

    @Inject(
            method = "clearWorld",
            at = @At("HEAD")
    )
    private void onWorldUnload(CallbackInfo ci) {
        if (world != null) {
            PlayerListHudHook.setPreviousHeader(null);
            PlayerListHudHook.setPreviousFooter(null);
            PlayerListHudHook.setBossBarHeights(null);
        }
    }
}
