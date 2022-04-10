package com.squagward.tab.mixin;

import com.squagward.tab.hooks.PlayerListHudHook;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BossBarHud.class)
public class BossBarHudMixin {
    @Inject(
            method = "render",
            at = @At("TAIL"),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    public void setBossBarTotalHeight(MatrixStack matrices, CallbackInfo ci, int i, int j) {
        PlayerListHudHook.setBossBarHeights(j);
    }
}
