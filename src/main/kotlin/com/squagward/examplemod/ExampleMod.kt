package com.squagward.examplemod

import com.squagward.examplemod.command.ExampleCommand
import com.squagward.examplemod.config.ExampleConfig
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.MinecraftClient

object ExampleMod : ClientModInitializer {
    var config: ExampleConfig? = null

    override fun onInitializeClient() {
        MinecraftClient.getInstance().send {
            config = ExampleConfig()
            config?.preload()

            ExampleCommand.register()
        }
    }
}
