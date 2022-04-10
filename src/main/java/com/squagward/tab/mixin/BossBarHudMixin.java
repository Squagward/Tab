package com.squagward.tab.mixin;

import com.squagward.tab.hooks.PlayerListHudHook;
import net.minecraft.client.gui.hud.BossBarHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BossBarHud.class)
public class BossBarHudMixin {
    @ModifyVariable(
            method = "render",
            at = @At("TAIL"),
            ordinal = 1
    )
    private int setBossBarTotalHeight(int j) {
        PlayerListHudHook.setBossBarHeights(j);
        return j;
    }
}
