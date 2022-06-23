package rush.comandos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPluginLoader;

import rush.Main;
import rush.apis.UltimateFancy;
import rush.configuracoes.Locations;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;
import rush.entidades.Kits;
import rush.entidades.Warps;
import rush.utils.Backup;
import rush.utils.SystemInfo;
import rush.utils.Utils;
import rush.utils.manager.ConfigManager;
import rush.utils.manager.DataManager;

import static rush.utils.Utils.bytesToLegibleValue;

public class ComandoSystem implements CommandExecutor {
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender s, Command cnd, String lbl, String[] args) {
			
		// Verificando se o sender informou o comando correto
		if (args.length < 1) {
			s.sendMessage(Mensagens.System_Comando_Incorreto);
			return true;
		}
		
		String cmd = args[0].toLowerCase();
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
			
		// Caso o argumento seja 'reload' então recarregamos as configs do pl
		if (cmd.equals("reload")) {
			try {
				Settings.loadSettings();
				Mensagens.loadMensagens();
				Locations.loadLocations();
				Warps.loadWarps();
				Kits.loadKits();
				s.sendMessage(Mensagens.System_Recarregado_Sucesso);	
			} catch (Throwable e) {
				e.printStackTrace();
				s.sendMessage(Mensagens.System_Erro_Ao_Recarregar);
			}
			return true;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
			
		// Caso o argumento seja 'backup' então criamos 1 backup dos arquivos do plugin
		if (cmd.equals("backup")) {
			// Criando uma nova Theard para não rodar na principal
			new Thread() {
				@Override
				public void run() {
					Backup.create();
					s.sendMessage(Mensagens.Backup_Com_Sucesso);
				}
			}.start();
			return true;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////

		// Caso o argumento seja 'contato' então é exibido os contatos do desenvolvodres
		if (cmd.equals("contato")) {
			s.sendMessage("");
			s.sendMessage("§6 §l* §eEduardo Mior - RUSHyoutuber");
			s.sendMessage("");
			s.sendMessage("§aWhatsApp: §f(54) 991343192");
			s.sendMessage("§9Facebook: §fhttp://fb.com/eduardo.mior.3");
			s.sendMessage("§bTwitter: §fhttps://twitter.com/CanalDaRUSH");
			s.sendMessage("§bSkype: §flive:eduardo-mior");
			s.sendMessage("§3Discord: §fEduardo Mior#5793");
			s.sendMessage("§cE-Mail: §feduardo-mior@hotmail.com");
			s.sendMessage("§2TeamSpeak: §frush.ts3elite.com");
			s.sendMessage("§eSpigot: §fhttps://spigotmc.org/members/mior.344828");
			return true;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////

		// Caso o argumento seja 'help' então é exibido a lista de comandos do plugin
		if (cmd.equals("help")) {
			
			// Verificando se a versão suporta JSON
			if (Main.isVeryOldVersion()) {
				s.sendMessage(Mensagens.Erro_Versao_Nao_Suportada);
				return true;
			}
			
			// Verificando se o player é o console
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode);
				return true;
			}
			
			// Verificando se o número da página informada é valido
			int pag = 1;
			if (args.length == 2) {
				try {
					pag = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
					return true;
				}
			}

			// Pegando o arquivo dos comandos e alista dos comandos
			FileConfiguration config = ConfigManager.getConfig("comandos");
			Set<String> keys = config.getConfigurationSection("comandos").getKeys(false);
			String[] cmds = keys.toArray(new String[keys.size()]);
				
			// Pegando o total de páginas, e administrando as paginas
			int maxPag =  (int) Math.ceil((double) cmds.length / 10.0);
			int inicio = (pag - 1) * 10;
			int fim = ((pag - 1) * 10) + 10;
				
			// Verificando se a página solicitada existe
			if (pag < 1 || pag > maxPag) {
				s.sendMessage("§cPágina invalida!");
				return true;
			}
				
			// Criando a mensagem JSON com a lista de comandos
			UltimateFancy msg = new UltimateFancy();
			s.sendMessage("§e§lLista de comandos do §nSystem§e: ");
			for (int i = inicio; i < fim && i < cmds.length; i++) {
				String description = config.getString("comandos." + cmds[i] + ".descricao");
				String permission = "system." + cmds[i];
				String sempermission = config.getString("comandos." + cmds[i] + ".sem-permissao").replace('&', '§');
				String aliases = config.getStringList("comandos." + cmds[i] + ".aliases").toString();
				boolean enabled = config.getBoolean("comandos." + cmds[i] + ".ativar-comando");
				msg.text("§b/" + cmds[i] + " §7-§f " + description + "\n");
				msg.hoverShowText(
						"§eComando: §f/" + cmds[i] +
						"\n§eComando ativado: §f" + enabled +
						"\n§eAliases: §f" + aliases +
						"\n§eDescrição: §f" + description +
						"\n§ePermissão: §f" + permission +
						"\n§eMensagem de erro: " + sempermission
						);
				msg.clickSuggestCmd("/" + cmds[i]);
				msg.next();
			}
				
			// Criando a mensagem JSON para passar e volta de página
			msg.text("§ePagina ");
			msg.next();
			msg.text("§l[§b<§e] §r§e");
			msg.hoverShowText("§bVoltar página");
			msg.clickRunCmd("/system help " + (pag - 1));
			msg.next();
			msg.text(pag + "§7/§e" + maxPag);
			msg.next();
			msg.text(" §l[§b>§e]");
			msg.hoverShowText("§bPróxima página");
			msg.clickRunCmd("/system help " + (pag + 1));
				
			// Enviando a mensagem para o player
			msg.send(s);
			return true;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////

		// Caso o argumento seja 'info' então é exibido algumas informações do plugin
		if (cmd.equals("info")) {					
			s.sendMessage("§e*-=-=-=-=-=-=-* §bServer Info §e*-=-=-=-=-=-=-* ");
			s.sendMessage("§ePlugin Version: §61.14.19");
			s.sendMessage("§eJava Version: §6" + System.getProperty("java.version"));
			s.sendMessage("§eMinecraft Version: §6" + SystemInfo.getMinecraftVersion());
			s.sendMessage("§eServerAPI Vesrion: §6" + SystemInfo.getApiVersion());
			s.sendMessage("§eServer JarType: §6" + SystemInfo.getJarType());
			s.sendMessage("§e*-=-=-=-=-=-=-* §bServer Info §e*-=-=-=-=-=-=-* ");
			return true;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Caso o argumento seja 'debug' então são exibidas algumas informações do system
		if (cmd.equals("debug")) {
			s.sendMessage("§e*-=-=-=-=-=-=-* §bSystem Debug §e*-=-=-=-=-=-=-* ");
			s.sendMessage("§eSystem JarType detectada: §6" + Main.getTypeJar());
			s.sendMessage("§eSystem Bukkit Version detectada: §6" + Main.getVersion());
			s.sendMessage("§eNúmero de Kits: §6" + Kits.getAll().size());
			s.sendMessage("§eNúmero de Warps: §6" + Warps.getAll().size());
			s.sendMessage("§eNúmero de Players cadastrados: §6" + DataManager.getFolder("playerdata").listFiles().length);
			s.sendMessage("§eMinecraft Raw Version: §6" +  Bukkit.getVersion());
			s.sendMessage("§eMinecraft Bukkit Raw Version: §6" + Bukkit.getBukkitVersion());
			s.sendMessage("§e*-=-=-=-=-=-=-* §bSystem Debug §e*-=-=-=-=-=-=-* ");
			return true;
		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////

		// Caso o argumento seja 'host' então é exibida algumas informações do server
		if (cmd.equals("host")) {
			
			// Verificando os argumentos
			if (args.length < 2) {
				s.sendMessage("§cComando incorreto, use:");
				s.sendMessage("§c/system host basico §8-§7 Mosta as informações mais basicas e importantes da host.");
				s.sendMessage("§c/system host avancado §8-§7 Gera um relatório super completo TODAS as informações sobre a host.");
				return true;
			}
			
			// Caso o argumento seja 'basico' então são exibidas das informações basicas
			if (args[1].equalsIgnoreCase("basico")) {
				// Pegando o runtime onde o programa esta sendo rodado, e pegando o sistema operacional (base)
				Runtime machine = Runtime.getRuntime();
				OperatingSystemMXBean system = ManagementFactory.getOperatingSystemMXBean();
				
				// Pegando os dados do sistema operacional etc..
				String so = system.getName();
				String soVersion = system.getVersion();
				String availableProcessors = String.valueOf(system.getAvailableProcessors());
				String freeRuntimeMemory = bytesToLegibleValue(machine.freeMemory());
				String totalRuntimeMemory = bytesToLegibleValue(machine.maxMemory());
				String usedRuntimeMemory = bytesToLegibleValue(machine.totalMemory() - machine.freeMemory());
				String freeComputerMemory = bytesToLegibleValue(SystemInfo.getFreeMemoryComputer());
				String totalComputerMemory = bytesToLegibleValue(SystemInfo.getTotalMemoryComputer());
				String usedComputerMemory = bytesToLegibleValue(SystemInfo.getTotalMemoryComputer() - SystemInfo.getFreeMemoryComputer());
				String freeComputerSpace = bytesToLegibleValue(SystemInfo.getFreeSpaceComputer());
				String totalComputerSpace = bytesToLegibleValue(SystemInfo.getTotalSpaceComputer());
				String usedComputerSpace = bytesToLegibleValue(SystemInfo.getTotalSpaceComputer() - SystemInfo.getFreeSpaceComputer());
				String processorArch = System.getProperty("os.arch");
				String processor = System.getenv("PROCESSOR_IDENTIFIER");
				
				// Exibindo os dados
				s.sendMessage("§e*-=-=-=-=-=-=-=* §bHost Info §e*=-=-=-=-=-=-=-* ");
				s.sendMessage("§eSistema Operacional: §6" + so + " §8-§6 " + soVersion);
				s.sendMessage("§eMemória RAM total do servidor: §6" + totalRuntimeMemory);
				s.sendMessage("§eMemória RAM livre do servidor: §6" + freeRuntimeMemory);
				s.sendMessage("§eMemória RAM usada no servidor: §6" + usedRuntimeMemory);
				s.sendMessage("§eMemória RAM total da máquina: §6" + totalComputerMemory);
				s.sendMessage("§eMemória RAM livre da máquina: §6" + freeComputerMemory);
				s.sendMessage("§eMemória RAM usada na máquina: §6" + usedComputerMemory);
				s.sendMessage("§eArmazenamento total da máquina: §6" + totalComputerSpace);
				s.sendMessage("§eArmazenamento livre da máquina: §6" + freeComputerSpace);
				s.sendMessage("§eArmazenamento usado na máquina: §6" + usedComputerSpace);
				s.sendMessage("§eNúmero de processadores (núcleos): §6" + availableProcessors);
				s.sendMessage("§eArquitetura do processador: §6" + processorArch);
				s.sendMessage("§eModelo do processador: §6" + (processor == null ? "Informação indisponivel! Veja o relatório avançado." : processor));
				s.sendMessage("§e*-=-=-=-=-=-=-=* §bHost Info §e*=-=-=-=-=-=-=-* ");
				return true;
			}
			
			// Caso o argumento seja 'avancado' então são exibidas das informações avancadas
			if (args[1].equalsIgnoreCase("avancado") || args[1].equalsIgnoreCase("avançado")) {
				// Criando uma nova Theard para não rodar na principal
				new Thread() {
					@Override
					public void run() {
						s.sendMessage("§aObtendo informações do sistema e da host...");
						SystemInfo.createFullLog();
						s.sendMessage("§aSucesso! O arquivo com todas as informações sobre o sistema e a host foram criados dentro da pasta principal do plugin.");
					}
				}.start();
				return true;
			}
			
			s.sendMessage("§cComando incorreto, use:");
			s.sendMessage("§c/system host basico §8-§7 Para ver as informações basicas da host.");
			s.sendMessage("§c/system host avancado §8-§7 Para relatório um arquivo com TODAS as informações sobre a host.");
			return true;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////

		// Caso o argumento seja 'plugin' então é gerenciado os plugins do servidor
		if (cmd.startsWith("pl")) {
			
			// Verificando se ele digitou os argumentos minimos
			if (args.length < 2) {
				s.sendMessage(Mensagens.Plugin_Comando_Incorreto);
				return true;
			}
			
			String subcmd = args[1].toLowerCase();
			
			// Caso o argumento seja 'help' então é exibido o menu de ajuda
			if (subcmd.equals("help") || subcmd.equals("ajuda") || subcmd.equals("?")) {
				s.sendMessage("§e*-=-=-=-=-=-=-=* §bMenu de Ajuda §e*=-=-=-=-=-=-=-* ");
				s.sendMessage("§e§lImportante: §eOs plugins desligados aparece em §cvermelho §ena lista do /plugins, os plugins não carregados não aparecem na lista do /plugins.");
				s.sendMessage(" ");
				s.sendMessage("§bQual a diferença entre §nDisable §be §nUnload§b?");
				s.sendMessage("§eA função §6disable§e serve para desligar um plugin do servidor. O plugin continuara carregado no servidor, porém ele estara desligado.");
				s.sendMessage("§eA função §6unload§e serve para descarregar totalmente um plugin do servidor. O plugin não continuara carregado no servidor, ele sera desligado e removido.");
				s.sendMessage(" ");
				s.sendMessage("§bQual a diferença entre §nEnable §be §nLoad§b?");
				s.sendMessage("§eA função §6enable§e serve para ligar um plugin do servidor que esta desligado.");
				s.sendMessage("§eA função §6load§e serve para ligar um plugin do servidor que ainda não foi carregado, que esta descarregado.");
				s.sendMessage(" ");
				s.sendMessage("§bQual a diferença entre §nRe-Enable §be §nRe-Load§b?");
				s.sendMessage("§eA função §6Re-Enable§e serve para ligar e desligar um plugin do servidor.");
				s.sendMessage("§eA função §6Re-Load§e serve para descarregar de pois carregar novamente um plugin no servidor.");
				s.sendMessage("§e*-=-=-=-=-=-=-=* §bMenu de Ajuda §e*=-=-=-=-=-=-=-* ");
				return true;
			}
			
			
			// Caso o argumento seja 'list' então é listado todos os plugins
			if (subcmd.startsWith("list")) {
				// Pegando o plugin loader para carregar os PDF
				PluginLoader loader = Main.get().getPluginLoader();
				
				// Criando os sets para guardar os plugins
				SortedSet<String> enableds = new TreeSet<>();
				SortedSet<String> disableds = new TreeSet<>();
				SortedSet<String> unloadeds = new TreeSet<>();
				
				// Fazendo um loop por todas as jars e fazendo as devidas verificações
				for (File jar : Utils.getAllPluginsJar()) {
					try {
						PluginDescriptionFile pdf = loader.getPluginDescription(jar);
						String pluginName = pdf.getName();
						Plugin plugin = Utils.getPluginByName(pluginName);
						if (plugin == null) 
							unloadeds.add(pluginName);
						else if (plugin.isEnabled()) 
							enableds.add(pluginName);
						else
							disableds.add(pluginName);
					} catch (Throwable e) {
						String fileName = jar.getName().replace(".jar", "");
						unloadeds.add(fileName);
					}
				}
				
				// Informando o sender
				s.sendMessage("Plugins Ligados (" + enableds.size() + "): §a" + enableds.toString().replace("[", "").replace("]", "").replace(",", "§f,§a"));
				s.sendMessage("Plugins Desligados (" + disableds.size() + "): §c" + disableds.toString().replace("[", "").replace("]", "").replace(",", "§f,§c"));
				s.sendMessage("Plugins Descarregados (" + unloadeds.size() + "): §d" + unloadeds.toString().replace("[", "").replace("]", "").replace(",", "§f,§d"));
				return true;
			}
			
			
			// Caso o argumento seja 'enable' então é ligado um plugin
			if (subcmd.startsWith("enable") || subcmd.equals("ligar") || subcmd.equals("habilitar") || subcmd.equals("on")) {
				
				// Verificando se o número de argumentos é valido
				if (args.length < 3) {
					s.sendMessage(Mensagens.Plugin_Sintaxe_Incorreto.replace("%comando%", "enable"));
					return true;
				}
				
				// Pegando o plugin e verificando se é valido
				Plugin plugin = Utils.getPluginByName(args[2]);
				if (plugin == null) {
					s.sendMessage(Mensagens.Plugin_Nao_Encontrado.replace("%plugin%", args[2]));
					return true;
				}
				
				// Verificando se o plugin já não esta ligado
				if (plugin.isEnabled()) {
					s.sendMessage(Mensagens.Plugin_Ja_Ligado.replace("%plugin%", plugin.getName()));
					return true;
				}
				
				// Ligando o plugin
				Bukkit.getPluginManager().enablePlugin(plugin);
				s.sendMessage(Mensagens.Plugin_Ligado_Com_Sucesso.replace("%plugin%", plugin.getName()));
				return true;
			}
			
			
			// Caso o argumento seja 'disable' então é desligado um plugin
			if (subcmd.startsWith("disable") || subcmd.equals("desligar") || subcmd.equals("desabilitar") || subcmd.equals("off")) {
				
				// Verificando se o número de argumentos é valido
				if (args.length < 3) {
					s.sendMessage(Mensagens.Plugin_Sintaxe_Incorreto.replace("%comando%", "disable"));
					return true;
				}
				
				// Pegando o plugin e verificando se é valido
				Plugin plugin = Utils.getPluginByName(args[2]);
				if (plugin == null) {
					s.sendMessage(Mensagens.Plugin_Nao_Encontrado.replace("%plugin%", args[2]));
					return true;
				}
				
				// Verificando se o plugin já não esta ligado
				if (!plugin.isEnabled()) {
					s.sendMessage(Mensagens.Plugin_Ja_Desligado.replace("%plugin%", plugin.getName()));
					return true;
				}
				
				// Ligando o plugin
				Bukkit.getPluginManager().disablePlugin(plugin);
				s.sendMessage(Mensagens.Plugin_Desligado_Com_Sucesso.replace("%plugin%", plugin.getName()));
				return true;
			}
			
			
			// Caso o argumento seja 'load' então é carregado um plugin
			if (subcmd.startsWith("load") || subcmd.equals("carregar")) {

				// Verificando se o número de argumentos é valido
				if (args.length < 3) {
					s.sendMessage(Mensagens.Plugin_Sintaxe_Incorreto.replace("%comando%", "load"));
					return true;
				}
				
				// Pegando o plugin e verificando se ele já esta carregado
				String pluginName = String.join(" ", Arrays.copyOfRange(args, 2, args.length)).replace(".jar", "");
				Plugin enabledPlugin = Utils.getPluginByName(pluginName);
				if (enabledPlugin != null) {
					s.sendMessage(Mensagens.Plugin_Ja_Carregado.replace("%plugin%", enabledPlugin.getName()));
					return true;
				}
				
				// Verificando se o arquivo do plugin realmente existe
				File filePlugin = Utils.getPluginJar(pluginName);
				if (filePlugin == null) {
					s.sendMessage(Mensagens.Plugin_Ou_Arquivo_Nao_Encontrado.replace("%plugin%", pluginName));
					return true;
				}
				
				try
				{
					// Verificando se já existe um plugin com esse nome
					PluginDescriptionFile pdf = Main.get().getPluginLoader().getPluginDescription(filePlugin);
					Plugin loadedPlugin = Utils.getPluginByName(pdf.getName());
					if (loadedPlugin != null) {
						s.sendMessage(Mensagens.Plugin_Erro_Ao_Carregar.replace("%plugin%", loadedPlugin.getName()).replace("%motivo%", "Já existe um plugin carregado com este nome."));
						return true;
					}
					
					// Ligando o plugin
					Plugin plugin = Bukkit.getPluginManager().loadPlugin(filePlugin);
								    Bukkit.getPluginManager().enablePlugin(plugin);
					s.sendMessage(Mensagens.Plugin_Carregado_Com_Sucesso.replace("%plugin%", plugin.getName()));
				}
				catch (UnknownDependencyException e) {
					s.sendMessage(Mensagens.Plugin_Erro_Ao_Carregar.replace("%plugin%", pluginName).replace("%motivo%", "As dependencias do plugin não foram encontradas."));
					s.sendMessage("§c§nPara mais informações sobre o erro verifique o console.");
					e.printStackTrace();
				}
				catch (InvalidPluginException e) {
					s.sendMessage(Mensagens.Plugin_Erro_Ao_Carregar.replace("%plugin%", pluginName).replace("%motivo%", "Erro interno do plugin."));
					s.sendMessage("§c§nPara mais informações sobre o erro verifique o console.");
					e.printStackTrace();
				}
				catch (InvalidDescriptionException e) {
					s.sendMessage(Mensagens.Plugin_Erro_Ao_Carregar.replace("%plugin%", pluginName).replace("%motivo%", "Erro no arquivo no plugin.yml do plugin."));
					s.sendMessage("§c§nPara mais informações sobre o erro verifique o console.");
					e.printStackTrace();
				}
				catch (Throwable e) {
					s.sendMessage(Mensagens.Plugin_Erro_Ao_Carregar.replace("%plugin%", pluginName).replace("%motivo%", "Erro totalmente desconhecido."));
					s.sendMessage("§c§nPara mais informações sobre o erro verifique o console.");
					e.printStackTrace();
				}
				return true;
			}

			
			// Caso o argumento seja 'unload' então é descarregado um plugin
			if (subcmd.startsWith("unload") || subcmd.equals("descarregar")) {

				// Verificando se o número de argumentos é valido
				if (args.length < 3) {
					s.sendMessage(Mensagens.Plugin_Sintaxe_Incorreto.replace("%comando%", "unload"));
					return true;
				}
				
				// Pegando o plugin e verificando se ele já esta carregado
				Plugin plugin = Utils.getPluginByName(args[2]);
				if (plugin == null) {
					s.sendMessage(Mensagens.Plugin_Nao_Encontrado.replace("%plugin%", args[2]));
					return true;
				}
				
				// Cancelando as tasks, os serviços e os listeners do plugin 
				Bukkit.getPluginManager().disablePlugin(plugin);
				Bukkit.getScheduler().cancelTasks(plugin);
				Bukkit.getServicesManager().unregisterAll(plugin);
				HandlerList.unregisterAll(plugin);
				
				// Desregistrando e removendo os comandos do plugin
				try 
				{
		            Field commandMapField = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
					Field commandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
		            
					commandMapField.setAccessible(true);
					commandsField.setAccessible(true);
					
		            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getPluginManager());
					Map<String, Command> commands = (Map<String, Command>) commandsField.get(commandMap);
					Set<Entry<String, Command>> removes = new HashSet<>();
					
					for (Entry<String, Command> entry : commands.entrySet()) {
						if (entry.getValue() instanceof PluginCommand) {
							PluginCommand command = (PluginCommand) entry.getValue();
							if (command.getPlugin().equals(plugin)) {
								removes.add(entry);
							}
						}
					}
					
					for (Entry<String, Command> entry : removes) {
						entry.getValue().unregister(commandMap);
						commands.remove(entry.getKey(), entry.getValue());
					}
				} 
				catch (Throwable e) {
					s.sendMessage("§cNão foi possível descarregar completamente os comandos do plugin. Para mais informações verifique o console.");
					e.printStackTrace();
				}
				
				/**
				 * Código criado por iPyronic
				 * Link: https://github.com/r-clancy/PlugMan/blob/master/src/main/java/com/rylinaux/plugman/util/PluginUtil.java
				 */
				// Removendo o plugin da lista de plugins.
				try 
				{
		            Field pluginsField = Bukkit.getPluginManager().getClass().getDeclaredField("plugins");
					Field lookupNamesField = Bukkit.getPluginManager().getClass().getDeclaredField("lookupNames");
					
					pluginsField.setAccessible(true);
					lookupNamesField.setAccessible(true);
					
					List<Plugin> plugins = (List<Plugin>) pluginsField.get(Bukkit.getPluginManager());
					Map<String, Plugin> names = (Map<String, Plugin>) lookupNamesField.get(Bukkit.getPluginManager());
					
					plugins.remove(plugin);
					names.remove(plugin.getName());
					names.remove(plugin.getName().toLowerCase()); // PaperSpigot
				} 
				catch (Throwable e) {
					s.sendMessage("§cNão foi possível remover o plugin da lista de plugins. Para mais informações verifique o console.");
					e.printStackTrace();
				}
				
				/**
				 * Código criado por iPyronic
				 * Link: https://github.com/r-clancy/PlugMan/blob/master/src/main/java/com/rylinaux/plugman/util/PluginUtil.java
				 */
				// Eu realmente não sei se isso é necessario... Removendo e desativando a classloader do plugin.
		        ClassLoader cl = plugin.getClass().getClassLoader();
		        if (cl instanceof URLClassLoader) {
		            try {
		                Field pluginField = cl.getClass().getDeclaredField("plugin");
		                Field pluginInitField = cl.getClass().getDeclaredField("pluginInit");

		                pluginField.setAccessible(true);
		                pluginInitField.setAccessible(true);

		                pluginField.set(cl, null);
		                pluginInitField.set(cl, null);
		            } catch (Throwable e) {}

		            try {
		                ((URLClassLoader) cl).close();
		            } catch (Throwable e) {}
		        }
				
				/**
				 * Código criado por QuarterCode
				 * Link: https://dev.bukkit.org/projects/pluginmanager
				 */
				// Eu realmente não sei se isso é necessario... Removendo o plugin da lista de loaders.
		        try 
		        {
		        	JavaPluginLoader jpl = (JavaPluginLoader) plugin.getPluginLoader();
		        	Field loadersField = jpl.getClass().getDeclaredField("loaders");
		            loadersField.setAccessible(true);
		            Map<String, ?> loadersMap = (Map<String, ?>) loadersField.get(jpl);
		            loadersMap.remove(plugin.getName());
		        } catch (Throwable e) {}
		        
		        System.gc();
				
				// Enviando mensagem de sucesso
				s.sendMessage(Mensagens.Plugin_Descarregado_Com_Sucesso.replace("%plugin%", plugin.getName()));
				return true;
			}			
			
			
			// Caso o argumento seja 're-enable' então é habilitado um plugin
			if (subcmd.equals("re-enable") || subcmd.equals("reenable")) {

				// Verificando se o número de argumentos é valido
				if (args.length < 3) {
					s.sendMessage(Mensagens.Plugin_Sintaxe_Incorreto.replace("%comando%", "re-enable"));
					return true;
				}
				
				// Pegando o plugin e verificando se é valido
				Plugin plugin = Utils.getPluginByName(args[2]);
				if (plugin == null) {
					s.sendMessage(Mensagens.Plugin_Nao_Encontrado.replace("%plugin%", args[2]));
					return true;
				}
				
				// Verificando se o plugin esta ligado
				if (!plugin.isEnabled()) {
					s.sendMessage(Mensagens.Plugin_Nao_Ligado);
					return true;
				}
				
				// Verificando se o plugi não é o System.
				if (plugin.getName().equalsIgnoreCase("system")) {
					s.sendMessage("§cOps! §nBad Vibes§c :( Você não pode usar este comando com o system. Caso queira use /system reload.");
					return true;
				}
				
				// Desligando e ligando os plugins
				Bukkit.dispatchCommand(s, "system plugin disable " + args[2]);
				Bukkit.dispatchCommand(s, "system plugin enable " + args[2]);
				s.sendMessage(Mensagens.Plugin_Religado_Com_Sucesso.replace("%plugin%", plugin.getName()));
				return true;
			}			
			
			
			// Caso o argumento seja 're-load' então é recarregado um plugin
			if (subcmd.equals("re-load") || subcmd.equals("reload") || subcmd.equals("restart")) {

				// Verificando se o número de argumentos é valido
				if (args.length < 3) {
					s.sendMessage(Mensagens.Plugin_Sintaxe_Incorreto.replace("%comando%", "re-load"));
					return true;
				}
				
				// Pegando o plugin e verificando se é valido
				Plugin plugin = Utils.getPluginByName(args[2]);
				if (plugin == null) {
					s.sendMessage(Mensagens.Plugin_Nao_Encontrado.replace("%plugin%", args[2]));
					return true;
				}
				
				// Verificando se o plugin esta ligado
				if (!plugin.isEnabled()) {
					s.sendMessage(Mensagens.Plugin_Nao_Ligado);
					return true;
				}
				
				// Verificando se o plugi não é o System.
				if (plugin.getName().equalsIgnoreCase("system")) {
					s.sendMessage("§cOps! §nBad Vibes§c :( Você não pode usar este comando com o system. Caso queira use /system reload.");
					return true;
				}
				
				// Desligando e ligando os plugins
				Bukkit.dispatchCommand(s, "system plugin unload " + args[2]);
				Bukkit.dispatchCommand(s, "system plugin load " + args[2]);
				s.sendMessage(Mensagens.Plugin_Recarregado_Com_Sucesso.replace("%plugin%", plugin.getName()));
				return true;
			}
			
			
			// Caso o argumento seja 'info' então é habilitado um plugin
			if (subcmd.startsWith("info")) {

				// Verificando se o número de argumentos é valido
				if (args.length < 3) {
					s.sendMessage(Mensagens.Plugin_Sintaxe_Incorreto.replace("%comando%", "load"));
					return true;
				}
				
				// Pegando o plugin e verificando se ele já esta carregado
				String pluginName = String.join(" ", Arrays.copyOfRange(args, 2, args.length)).replace(".jar", "");
				File filePlugin = Utils.getPluginJar(pluginName);
				if (filePlugin == null) {
					s.sendMessage(Mensagens.Plugin_Ou_Arquivo_Nao_Encontrado.replace("%plugin%", pluginName));
					return true;
				}
				
				try {
					// Pegando o arquivo com as informações (plugin.yml)
					PluginDescriptionFile pdf = Main.get().getPluginLoader().getPluginDescription(filePlugin);
					
					// Pegando todas as informações
					String name = pdf.getName();
					String prefix = pdf.getPrefix();
					String version = pdf.getVersion();
					String description = pdf.getDescription();
					String website = pdf.getWebsite();
					List<String> autores = pdf.getAuthors();
					List<String> depends = pdf.getDepend();
					Map<String, Map<String, Object>> commands = pdf.getCommands();
					
					// Printando as informações
					s.sendMessage("§e*-=-=-=-=-=-=-=* §bInformações do Plugin §e*=-=-=-=-=-=-=-* ");
					
					if (name != null && !name.isEmpty()) 
						s.sendMessage("§eNome: §6" + name);
					
					if (prefix != null && !prefix.isEmpty()) 
						s.sendMessage("§ePrefixo: §6" + prefix);
					
					if (version != null && !version.isEmpty()) 
						s.sendMessage("§eVersão: §6" + version);
					
					if (description != null && !description.isEmpty())
						s.sendMessage("§eDescrição: §6" + description);
					
					if (website != null && !website.isEmpty())
						s.sendMessage("§eWebsite: §6" + website);
					
					if (autores != null && !autores.isEmpty())
						s.sendMessage(autores.size() == 1 ? "§eAutor: §6" : "§eAutores: §6" + autores.toString().replace(",", "§8,§6"));
					
					if (depends != null && !depends.isEmpty())
						s.sendMessage("§eDependências: §6" + depends.toString());
					
					if (commands != null && !commands.isEmpty())
						s.sendMessage("§eComandos: §6" + commands.keySet().toString().replace(",", "§8,§6"));

					s.sendMessage("§e*-=-=-=-=-=-=-=* §bInformações do Plugin §e*=-=-=-=-=-=-=-* ");
				} catch (Throwable e) {
					s.sendMessage("§cNão foi possível carregar as informações do plugin.");
				}
				return true;
			}
			
			s.sendMessage(Mensagens.Plugin_Comando_Incorreto);
			return true;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////

		// Caso o argumento seja 'terminal' então é executado um comando no terminal
		if (cmd.equals("terminal") || cmd.equals("cmd") || cmd.equals("prompt")) {
			
			// Verificando se o player digitou o número de argumentos corretos
			if (args.length < 2) {
				s.sendMessage(Mensagens.Terminal_Comando_Incorreto);
				return true;
			}

			// Criando uma nova Theard para não rodar na principal
			new Thread() {
				@Override
				public void run() {
					// Pegando o comando a ser executado
					String command = String.join(" ",  Arrays.copyOfRange(args, 1, args.length));
						
					// Executando o comando e mostrando a saida de retorno para o sender
					try {
						String line;
						Process process = Runtime.getRuntime().exec(command);
						BufferedReader retorno = new BufferedReader(new InputStreamReader(process.getInputStream()));
						while ((line = retorno.readLine()) != null) {
							s.sendMessage(line);
						}
					} catch (IOException e) {
						s.sendMessage(Mensagens.Terminal_Erro_Comando.replace("%erro%", e.getLocalizedMessage()));
					} catch (Throwable e) {
						s.sendMessage(Mensagens.Terminal_Erro_Desconhecido.replace("%erro%", e.getLocalizedMessage()));
					}
				}	
			}.start();
			return true;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Caso o argumento seja 'console' então executamos um comando no console do servidor
		if (cmd.equals("console") || cmd.equals("@console")) {
			
			// Verificando se o player digitou o número de argumentos corretos
			if (args.length < 2) {
				s.sendMessage(Mensagens.Terminal_Comando_Incorreto);
				return true;
			}
			
			// Pegando o comando a ser executado
			String command = String.join(" ",  Arrays.copyOfRange(args, 1, args.length));
		
			// Executando o comando e informando o sender
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
			s.sendMessage(Mensagens.Console_Comando_Executado.replace("%comando%", command));
			return true;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Caso o argumento seja 'desligar' então o servidor é desligado
		if (cmd.equals("disable") || cmd.equals("desligar") || cmd.equals("stop")) {
			System.exit(0);
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////

		// Caso nenhum dos argumentos acima for valido é dado com comando incorreto
		s.sendMessage(Mensagens.System_Comando_Incorreto);
		return true;
	}
	
}