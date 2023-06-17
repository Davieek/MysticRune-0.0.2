package me.ninjak.mysticrune.Manager

import me.ninjak.mysticrune.Manager.Handlers.CommandHandler
import me.ninjak.mysticrune.Mysticrune
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import java.awt.Label
import java.lang.reflect.Method

class CommandManager(private val plugin : Mysticrune) : CommandExecutor {
    private val commandMap: MutableMap<String, Method>

    init {
        commandMap = HashMap()
    }

    fun registerCommands(vararg commandInstances: Any) {
        for (commandInstance in commandInstances) {
            val commandClass: Class<*> = commandInstance.javaClass
            for (method in commandClass.declaredMethods) {
                if (method.isAnnotationPresent(CommandHandler::class.java)) {
                    val annotation = method.getAnnotation(CommandHandler::class.java)
                    val commandName = annotation.name
                    commandMap[commandName] = method
                    plugin.getCommand(commandName)!!.setExecutor(this)
                }
            }
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val commandName = command.name
        val method = commandMap[commandName]
        if (method != null) {
            try {
                method.invoke(method.declaringClass.newInstance(), sender, args)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return true
        }
        return false
    }
}