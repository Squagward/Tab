package com.squagward.tab.hooks

import com.squagward.tab.Tab
import com.squagward.tab.config.Config
import com.squagward.tab.mixin.BossBarHudAccessor
import com.squagward.tab.util.Utils
import net.minecraft.text.LiteralText

object PlayerListHudHook {
    @JvmStatic
    fun setPlayerName() {
        val entry = Tab.mc.networkHandler?.getPlayerListEntry(Tab.playerUUID)

        entry?.displayName = if (Config.toggleMod && Config.useCustomName) {
            LiteralText(Config.tabName.replace("&".toRegex(), "\u00A7"))
        } else null
    }

    @JvmStatic
    fun disableHeader() {
        if (!Config.toggleTabHeader) {
            Utils.getTabHud().setHeader(null)
        }
    }

    @JvmStatic
    fun disableFooter() {
        if (!Config.toggleTabFooter) {
            Utils.getTabHud().setFooter(null)
        }
    }

    @JvmStatic
    fun shiftTabDown(original: Int): Int {
        if (!Config.shiftTabDown) {
            return original
        }

        val bossBarHud = Tab.mc.inGameHud.bossBarHud as BossBarHudAccessor

        val totalHeight = 12 + 19 * bossBarHud.bossBars.size - 9

        return totalHeight.coerceIn(original, Tab.mc.window.height / 3)
    }
}
