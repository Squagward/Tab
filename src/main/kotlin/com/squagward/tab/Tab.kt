package com.squagward.tab

import com.squagward.tab.command.TabCommand
import com.squagward.tab.config.Config
import com.squagward.tab.util.Utils
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.MinecraftClient
import java.util.*

object Tab : ClientModInitializer {
    @JvmStatic
    val mc: MinecraftClient = MinecraftClient.getInstance()

    @JvmStatic
    val playerName: String = mc.session.username

    @JvmStatic
    val playerUUID: UUID = mc.session.profile.id

    override fun onInitializeClient() {
        MinecraftClient.getInstance().send {
            Config.preload()

            TabCommand.register()
        }
    }
}
