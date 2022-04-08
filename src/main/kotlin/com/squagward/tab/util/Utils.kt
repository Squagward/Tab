package com.squagward.tab.util

import com.squagward.tab.mixin.ChatHudAccessor
import net.minecraft.client.MinecraftClient
import net.minecraft.text.LiteralText

object Utils {
    @JvmStatic
    fun chat(message: String) {
        val mc = MinecraftClient.getInstance()
        val chatHud = mc.inGameHud.chatHud as ChatHudAccessor

        chatHud.callAddMessage(LiteralText(message), 9876)
    }
}
