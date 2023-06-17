package me.ninjak.mysticrune

import me.ninjak.mysticrune.Commands.MysticRuneCommand
import me.ninjak.mysticrune.Manager.CommandManager
import me.ninjak.mysticrune.Manager.ConfigManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level
import java.util.logging.Logger

class Mysticrune : JavaPlugin() {
    private var configManager : ConfigManager? = null

    override fun onEnable() {
        logger.log(Level.INFO, "test")
        configManager = ConfigManager()
        configManager?.setPlugin(this)
        configManager?.createConfig("config")
        configManager?.createConfig("message/Locale_EN")
        configManager?.createConfig("message/Locale_PL")

        val commandManager = CommandManager(this)
        commandManager.registerCommands(MysticRuneCommand())

    }

    override fun onDisable() {
        configManager?.saveConfig("config")
        configManager?.saveConfig("message/Locale_EN")
        configManager?.saveConfig("message/Locale_PL")
    }
    fun getConfigManager(): ConfigManager? {
        return configManager
    }
}
