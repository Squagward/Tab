package com.squagward.tab.mixin;

import com.squagward.tab.Tab;
import com.squagward.tab.config.Config;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.text.LiteralText;
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
    public void changeUsername(
            MatrixStack matrices,
            int scaledWindowWidth,
            Scoreboard scoreboard,
            ScoreboardObjective objective,
            CallbackInfo ci
    ) {
        ClientPlayNetworkHandler networkHandler = Tab.getMc().getNetworkHandler();
        if (networkHandler == null) return;

        PlayerListEntry entry = networkHandler.getPlayerListEntry(Tab.getPlayerName());
        if (entry == null) return;

        entry.setDisplayName(
                Config.getToggleMod() && Config.getUseCustomName()
                        ? new LiteralText(Config.getTabName().replaceAll("&", "\u00A7"))
                        : null
        );
    }

    @ModifyVariable(
            method = "render",
            at = @At(
                    value = "STORE",
                    target = "Lcom/google/common/collect/Ordering;sortedCopy(Ljava/lang/Iterable;)Ljava/util/List;"
            )
    )
    public List<PlayerListEntry> setUserIndex(List<PlayerListEntry> value) {
        if (!Config.getToggleMod()) return value;

        ClientPlayNetworkHandler networkHandler = Tab.getMc().getNetworkHandler();
        if (networkHandler == null) return value;

        PlayerListEntry entry = networkHandler.getPlayerListEntry(Tab.getPlayerName());

        int originalIndex = Config.getTabIndex();
        if (originalIndex < 0) return value;

        int index = MathHelper.clamp(originalIndex, 0, value.size() - 1);

        value.remove(entry);
        value.add(index, entry);

        return value;
    }
}
