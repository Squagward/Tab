package com.squagward.tab.command

import com.squagward.tab.config.Config
import gg.essential.api.EssentialAPI
import gg.essential.api.commands.Command
import gg.essential.api.commands.DefaultHandler
import gg.essential.api.commands.SubCommand

object TabCommand : Command("tab") {
    @DefaultHandler
    fun handle() {
        EssentialAPI.getGuiUtil().openScreen(Config.gui())
    }

    @SubCommand(value = "setNameSlot", description = "Set your tab name slot, 0 indexed")
    fun setPriorityField(index: Int) {
        Config.tabIndex = index
        Config.markDirty()
    }

    @SubCommand(value = "resetNameSlot", description = "Reset your tab name index")
    fun resetPriorityField() {
        Config.tabIndex = -1
        Config.markDirty()
    }
}
