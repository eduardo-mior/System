package rush.entidades;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import rush.Main;
import rush.utils.manager.ConfigManager;

public class Command {
	
	private static final FileConfiguration CONFIG = ConfigManager.getConfig("comandos");
	
    private CommandExecutor executor;
    private String command;
    private List<String> aliases;
    private String description;
	private String permissionMessage;
    private String permission;
    private PluginCommand pluginCommand;
    
    public Command(String command, String permission, CommandExecutor executor) {
    	
    	boolean enable = CONFIG.getBoolean("comandos." + command + ".ativar-comando");
    	
    	if (enable) {
        	this.executor = executor;
        	this.command = command;
        	this.aliases = CONFIG.getStringList("comandos." + command + ".aliases");
        	this.description = CONFIG.getString("comandos." + command + ".descricao");
        	this.permissionMessage = CONFIG.getString("comandos." + command + ".sem-permissao").replace('&', '§');
        	this.permission = permission;
        	this.pluginCommand = createPluginCommand();
        	registerPluginCommand();
    	}
    }
    
    private PluginCommand createPluginCommand() {
        try {
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);

            PluginCommand cmd = c.newInstance(command, Main.get());
            cmd.setAliases(aliases);
            cmd.setPermission(permission);
            cmd.setPermissionMessage(permissionMessage);
            cmd.setDescription(description);
            cmd.setExecutor(executor);

            return cmd;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    private void registerPluginCommand() {
        if (pluginCommand == null) return;

        try {
            Field f = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            CommandMap commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
            commandMap.register("system", pluginCommand);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
	
}
