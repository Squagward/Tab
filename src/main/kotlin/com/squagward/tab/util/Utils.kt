package com.squagward.tab.util

import com.squagward.tab.Tab
import net.minecraft.client.gui.hud.PlayerListHud

object Utils {
    @JvmStatic
    fun getTabHud(): PlayerListHud = Tab.mc.inGameHud.playerListHud
}
