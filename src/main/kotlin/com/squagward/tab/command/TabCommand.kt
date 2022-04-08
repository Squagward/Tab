package com.squagward.tab.command

import com.squagward.tab.config.Config
import gg.essential.api.EssentialAPI
import gg.essential.api.commands.Command
import gg.essential.api.commands.DefaultHandler

object TabCommand : Command("tab") {
    @DefaultHandler
    fun handle() {
        EssentialAPI.getGuiUtil().openScreen(Config.gui())
    }
}
