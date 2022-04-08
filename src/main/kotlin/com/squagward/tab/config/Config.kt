package com.squagward.tab.config

import com.squagward.tab.Tab
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

object Config : Vigilant(File("./config/ExampleMod.toml"), "Example Mod Settings") {
    @JvmStatic
    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Mod",
        description = "Toggle the Example Mod on or off",
        category = "General"
    )
    var toggleMod = true

    @JvmStatic
    @Property(
        type = PropertyType.NUMBER,
        name = "Tab Index",
        description = "The index of the tab in the tab list",
        category = "General",
        min = -1,
        max = 1000,
        hidden = true,
    )
    var tabIndex = -1

    @JvmStatic
    @Property(
        type = PropertyType.SWITCH,
        name = "Use Custom Tab Name",
        description = "Should the tab name be custom?",
        category = "General",
    )
    var useCustomName = false

    @JvmStatic
    @Property(
        type = PropertyType.TEXT,
        name = "Tab Name",
        description = "The custom name of you in tab",
        category = "General",
    )
    var tabName: String = Tab.playerName

    init {
        initialize()

        addDependency("useCustomName", "toggleMod")
        addDependency("tabName", "toggleMod")
        addDependency("tabName", "useCustomName")
    }
}
