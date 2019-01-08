package rush.sistemas.gerais;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.configuracoes.Settings;

public class DeletarComandos {

	@SuppressWarnings("unchecked")
	public static void deleteCommands() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				try 
				{
		            Field commandMapField = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
					Field commandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
		            commandMapField.setAccessible(true);
					commandsField.setAccessible(true);
					
		            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getPluginManager());
					Map<String, Command> commands = (Map<String, Command>) commandsField.get(commandMap);
					Set<Entry<String, Command>> removes = new HashSet<>();
					
					for (String line : Settings.Lista_Dos_Comandos_Deletados) 
					{	
						String plugin  = line.split(":")[0].toLowerCase().trim();
						String command = line.split(":")[1].toLowerCase().trim();
						
						if (command.equals("*")) {
							for (Entry<String, Command> entry : commands.entrySet()) {
								if (entry.getKey().contains(":") && entry.getKey().split(":")[0].equals(plugin)) {
									removes.add(entry);
								}
							}
						}
						
						else if (plugin.equals("*")) {
							for (Entry<String, Command> entry : commands.entrySet()) {
								if (entry.getValue().getName().equals(command)) {
									removes.add(entry);
								}
							}
						}
						
						else {
							for (Entry<String, Command> entry : commands.entrySet()) {
								if (entry.getKey().contains(":") && entry.getKey().split(":")[0].equals(plugin) && entry.getValue().getName().equals(command)) {
									removes.add(entry);
								}
							}
						}
					}
					
					for (Entry<String, Command> entry : removes) {
						commands.remove(entry.getKey(), entry.getValue());
					}
					
				} 
				catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}.runTaskLaterAsynchronously(Main.get(), 20L * 15L);

	}
	
}