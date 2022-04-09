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
        description = "The index of the tab in the tab list. Set to -1 for normal position.",
        category = "General",
        min = -1,
        max = 80,
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

    @Property(
        type = PropertyType.TEXT,
        name = "Tab Name",
        description = "The custom name of you in tab",
        category = "General",
    )
    var tabName: String = Tab.playerName

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Tab Header",
        description = "Toggle the tab header on or off",
        category = "General"
    )
    var toggleTabHeader = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Tab Footer",
        description = "Toggle the tab footer on or off",
        category = "General"
    )
    var toggleTabFooter = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Shift Tab List Down",
        description = "Shift the tab list down until below all bossbars.",
        category = "General"
    )
    var shiftTabDown = false

    init {
        initialize()

        addDependency("useCustomName", "toggleMod")
        addDependency("tabName", "toggleMod")
        addDependency("toggleTabHeader", "toggleMod")
        addDependency("toggleTabFooter", "toggleMod")

        addDependency("tabName", "useCustomName")
    }
}
