package com.squagward.tab.mixin;

import com.squagward.tab.Tab;
import com.squagward.tab.config.Config;
import com.squagward.tab.hooks.PlayerListHudHook;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerListHud.class)
public class MixinPlayerListHud {
    @Inject(method = "render", at = @At("HEAD"))
    public void onRender(
            MatrixStack matrices,
            int scaledWindowWidth,
            Scoreboard scoreboard,
            ScoreboardObjective objective,
            CallbackInfo ci
    ) {
        PlayerListHudHook.setPlayerName();
        PlayerListHudHook.disableHeader();
        PlayerListHudHook.disableFooter();
    }

    @ModifyVariable(
            method = "render",
            at = @At("STORE"),
            index = 6
    )
    public List<PlayerListEntry> setUserIndex(List<PlayerListEntry> value) {
        ClientPlayNetworkHandler networkHandler = Tab.getMc().getNetworkHandler();

        if (!Config.getToggleMod() || networkHandler == null) return value;

        PlayerListEntry entry = networkHandler.getPlayerListEntry(Tab.getPlayerUUID());

        if (entry == null) return value;

        int originalIndex = Config.getTabIndex();
        if (originalIndex < 0) return value;

        int index = MathHelper.clamp(originalIndex, 0, value.size() - 1);

        value.remove(entry);
        value.add(index, entry);

        return value;
    }

    @ModifyVariable(
            method = "render",
            at = @At("STORE"),
            index = 16
    )
    public int setYOffset(int value) {
        return PlayerListHudHook.shiftTabDown(value);
    }
}
