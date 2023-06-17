package me.ninjak.mysticrune.Manager

import me.ninjak.mysticrune.Mysticrune
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException
import java.lang.Exception

class ConfigManager {

    private var plugin : Mysticrune? = null;
    private var configMap : HashMap<String, FileConfiguration>? = null;

    fun createConfig(configName : String?) {
        if (configName == null) {
            return
        }
        val configFile = File(plugin?.dataFolder, "$configName.yml")
        if (!configFile.exists() && configFile.length() < 0) {
            plugin?.saveResource("$configName.yml", false)
        }
        val config = YamlConfiguration.loadConfiguration(configFile)
        configMap?.put(configName, config)
    }
    fun getConfig(configName: String?): FileConfiguration {
        return configMap?.get(configName)!!
    }

    fun reloadConfig(configName: String?) {
        if (configName == null) {
            return
        }
        val configFile = File(plugin?.dataFolder, "$configName.yml")
        val config : FileConfiguration
        if (configFile.exists() && configFile.length() > 0) {
            try {
                config = YamlConfiguration.loadConfiguration(configFile)
                configMap?.put(configName, config)

            } catch (e : Exception) {
                createConfig(configName)
            }
        } else {
            createConfig(configName)
        }

    }
    fun saveConfig(configName: String) {
        val configFile = File(plugin?.getDataFolder(), "$configName.yml")
        val config: FileConfiguration = YamlConfiguration.loadConfiguration(configFile)
        try {
            config.save(configFile)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun setPlugin(plugin : Mysticrune?) {
        this.plugin = plugin
        this.configMap = HashMap()
    }
}