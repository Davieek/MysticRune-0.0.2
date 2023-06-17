package me.ninjak.mysticrune.Commands.MRSubCommand

import me.ninjak.mysticrune.ChatUtils
import me.ninjak.mysticrune.Manager.Handlers.CommandHandler
import me.ninjak.mysticrune.Mysticrune
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class HelpCommand {
    @CommandHandler(name = "mysticrune")
    fun helpCommand(sender: CommandSender, args: Array<String>) {
        val player = sender as Player
        val configManager = Mysticrune().getConfigManager()
        val config = configManager?.getConfig("config")
        val locale = config?.getString("config.Language")
        val languageConfig = configManager?.getConfig("message/Locale_$locale")

        val helpList = languageConfig?.getStringList("helpCommand")!!
        for (helpString in helpList) {
            player.sendMessage(ChatUtils.fixColor(helpString))
        }
        return
    }
}