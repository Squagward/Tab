package com.squagward.tab.hooks

import com.squagward.tab.Tab
import com.squagward.tab.config.Config
import com.squagward.tab.mixin.BossBarHudAccessor
import com.squagward.tab.mixin.PlayerListHudAccessor
import com.squagward.tab.util.Utils
import net.minecraft.text.LiteralText
import net.minecraft.text.Text

object PlayerListHudHook {
    @JvmStatic
    var previousHeader: Text? = null

    @JvmStatic
    var previousFooter: Text? = null

    @JvmStatic
    var bossBarHeights: Int? = null

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
            val tabHud = Utils.getTabHud()
            val tabHudAccessor = tabHud as PlayerListHudAccessor

            if (tabHudAccessor.header != null) {
                previousHeader = tabHudAccessor.header
            }

            tabHud.setHeader(null)
        } else if (previousHeader != null) {
            Utils.getTabHud().setHeader(previousHeader)
            previousHeader = null
        }
    }

    @JvmStatic
    fun disableFooter() {
        if (!Config.toggleTabFooter) {
            val tabHud = Utils.getTabHud()
            val tabHudAccessor = tabHud as PlayerListHudAccessor

            if (tabHudAccessor.footer != null) {
                previousFooter = tabHudAccessor.footer
            }

            tabHud.setFooter(null)
        } else if (previousFooter != null) {
            Utils.getTabHud().setFooter(previousFooter)
            previousFooter = null
        }
    }

    @JvmStatic
    fun shiftTabDown(y: Int): Int {
        val bossBarHud = Tab.mc.inGameHud.bossBarHud as BossBarHudAccessor

        if (!Config.shiftTabDown || bossBarHud.bossBars.isEmpty()) {
            return y
        }

        return bossBarHeights!! - 10
    }
}
