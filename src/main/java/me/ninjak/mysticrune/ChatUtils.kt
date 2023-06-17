package me.ninjak.mysticrune

import org.bukkit.ChatColor

object ChatUtils {
    fun fixColor(message: String?): String {
        return ChatColor.translateAlternateColorCodes('&', message!!)
    }

}