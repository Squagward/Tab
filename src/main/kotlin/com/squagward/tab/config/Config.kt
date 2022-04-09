package com.squagward.tab.config

import com.squagward.tab.Tab
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

object Config : Vigilant(File("./config/Tab.toml"), "Tab Settings") {
    @JvmStatic
    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Tab Mod",
        description = "Toggle the mod on or off.",
        category = "General"
    )
    var toggleMod = false

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
        name = "Toggle Custom Tab Name",
        description = "Set whether or not to use the custom tab name.",
        category = "General",
    )
    var useCustomName = false

    @Property(
        type = PropertyType.TEXT,
        name = "Custom Tab Name",
        description = "Set your custom name in tab.",
        category = "General",
    )
    var tabName: String = Tab.playerName

    @Property(
        type = PropertyType.SWITCH,
        name = "Shift Tab Down",
        description = "Shift the tab list below all boss bars.",
        category = "General"
    )
    var shiftTabDown = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Tab Header",
        description = "Toggle the tab header on or off.",
        category = "General"
    )
    var toggleTabHeader = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Tab Footer",
        description = "Toggle the tab footer on or off.",
        category = "General"
    )
    var toggleTabFooter = true

    init {
        initialize()

        addDependency("tabIndex", "toggleMod")
        addDependency("useCustomName", "toggleMod")
        addDependency("tabName", "toggleMod")
        addDependency("toggleTabHeader", "toggleMod")
        addDependency("toggleTabFooter", "toggleMod")
        addDependency("shiftTabDown", "toggleMod")
    }
}
