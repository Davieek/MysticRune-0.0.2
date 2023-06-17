package me.ninjak.mysticrune.Commands

import me.ninjak.mysticrune.ChatUtils
import me.ninjak.mysticrune.Commands.MRSubCommand.HelpCommand
import me.ninjak.mysticrune.Manager.ConfigManager
import me.ninjak.mysticrune.Manager.Handlers.CommandHandler
import me.ninjak.mysticrune.Mysticrune
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class MysticRuneCommand() {
    private var plugin: Mysticrune? = null

    fun setMysticrune(plugin: Mysticrune) {
        this.plugin = plugin
    }

    @CommandHandler(name = "mysticrune")
    fun createMysticRuneCommand(sender: CommandSender, args: Array<String>) {
        val configManager: ConfigManager? = plugin?.getConfigManager()
        val config = configManager?.getConfig("config")
        val prefix = config?.getString("config.prefix")
        val locale = config?.getString("config.Language")
        val languageConfig = configManager?.getConfig("message/Locale_$locale")
        val onlyPlayerExecuteCommand = languageConfig!!.getString("onlyPlayerExecuteCommand_error")
        val playerNoPermission = languageConfig.getString("playerNoPerms_error")
        sender.sendMessage("test")

        if (sender !is Player) {
            sender.sendMessage(ChatUtils.fixColor("$prefix $onlyPlayerExecuteCommand"))
            return
        }

        val player = sender

        if (!player.hasPermission("mysticrune.admin")) {
            player.sendMessage(ChatUtils.fixColor("$prefix $playerNoPermission"))
            return
        }
        if (args.size > 0) {
            val subCommand = args[0].lowercase(Locale.getDefault())
            val commandNotExists = languageConfig.getString("commandNotExists")
            when (subCommand) {
                "reload", "rl" -> {
                    val helpCommand = HelpCommand()
                    helpCommand.helpCommand(sender, args)
                    return
                }
                else -> sender.sendMessage(
                    ChatUtils.fixColor(
                        "$prefix $commandNotExists"
                    )
                )
            }
        } else {
            val helpCommand = HelpCommand()
            helpCommand.helpCommand(sender, args)
        }
    }
}
