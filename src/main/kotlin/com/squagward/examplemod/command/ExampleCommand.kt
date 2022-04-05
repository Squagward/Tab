package com.squagward.examplemod.command

import com.squagward.examplemod.ExampleMod
import gg.essential.api.EssentialAPI
import gg.essential.api.commands.Command
import gg.essential.api.commands.DefaultHandler

object ExampleCommand : Command("examplemod") {
    @DefaultHandler
     fun handle() {
        EssentialAPI.getGuiUtil().openScreen(ExampleMod.config?.gui())
    }
}
