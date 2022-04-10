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
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerListHud.class)
public class PlayerListHudMixin {
    @Inject(
            method = "render",
            at = @At("HEAD")
    )
    private void onRender(
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

    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyVariable(
            method = "render",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 0
    )
    private List<PlayerListEntry> setUserIndex(List<PlayerListEntry> playerList) {
        int originalIndex = Config.getTabIndex();
        ClientPlayNetworkHandler networkHandler = Tab.getMc().getNetworkHandler();

        if (!Config.getToggleMod() || networkHandler == null || originalIndex < 0) return playerList;

        PlayerListEntry entry = networkHandler.getPlayerListEntry(Tab.getPlayerUUID());

        if (entry == null) return playerList;

        int index = MathHelper.clamp(originalIndex, 0, playerList.size() - 1);

        playerList.remove(entry);
        playerList.add(index, entry);

        return playerList;
    }

    @ModifyVariable(
            method = "render",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 9
    )
    private int setYOffset(int q) {
        return PlayerListHudHook.shiftTabDown(q);
    }
}
