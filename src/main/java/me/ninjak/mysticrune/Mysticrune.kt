package me.ninjak.mysticrune

import me.ninjak.mysticrune.Manager.ConfigManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level
import java.util.logging.Logger

class Mysticrune : JavaPlugin() {
    private var configManager : ConfigManager? = null
    override fun onEnable() {
        configManager?.setPlugin(this)
        configManager?.createConfig("config")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
    public fun getConfigManager(): ConfigManager? {
        return configManager
    }
}
