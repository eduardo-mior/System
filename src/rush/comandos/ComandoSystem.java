package rush.comandos;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import rush.apis.UltimateFancy;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;
import rush.utils.Backup;
import rush.utils.SystemInfo;
import rush.utils.manager.ConfigManager;

public class ComandoSystem implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
			
		// Verificando se o sender informou o comando correto
		if (args.length < 1 || args.length > 2) {
			s.sendMessage(Mensagens.System_Comando_Incorreto);
			return true;
		}
			
		// Caso o argumento seja 'reload' então recarregamos as configs do pl
		if (args[0].equalsIgnoreCase("reload")) {
			Settings.loadSettings();
			Mensagens.loadMensagens();
			s.sendMessage(Mensagens.Plugin_Recarregado_Sucesso);
			return true;
		}
			
		// Caso o argumento seja 'backup' então criamos 1 backup dos arquivos do plugin
		if (args[0].equalsIgnoreCase("backup")) {
			Backup.create();
			s.sendMessage(Mensagens.Backup_Com_Sucesso);
			return true;
		}
			
		// Caso o argumento seja 'contato' então é exibido os contatos do desenvolvodres
		if (args[0].equalsIgnoreCase("contato")) {
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
			
		// Caso o argumento seja 'help' então é exibido a lista de comandos do plugin
		if (args[0].equalsIgnoreCase("help")) {
			
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
			int maxPag =  (int) Math.ceil((double) cmds.length / 10);
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
				String sempermission =  config.getString("comandos." + cmds[i] + ".sem-permissao").replace('&', '§');
				String aliases = config.getStringList("comandos." + cmds[i] + ".aliases").toString();
				boolean enabled = config.getBoolean("comandos." + cmds[i] + ".ativar-comando");
				msg.text("§b/" + cmds[i] + " §7-§f " + description + "\n");
				msg.hoverShowText(
						"§eComando ativado: §f" + enabled +
						"\n§eAliases: §f" + aliases +
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
		
		// Caso o argumento seja 'info' então é exibido algumas informações do plugin
		if (args[0].equalsIgnoreCase("info")) {					
			s.sendMessage("§e*-=-=-=-=-=-=-* §bServer Info §e*-=-=-=-=-=-=-* ");
			s.sendMessage("§ePlugin Version: §61.8.1");
			s.sendMessage("§eJava version: §6" + System.getProperty("java.version"));
			s.sendMessage("§eMinecraft Version: §6" + SystemInfo.getMinecraftVersion());
			s.sendMessage("§eServerAPI Vesrion: §6" + SystemInfo.getApiVersion());
			s.sendMessage("§eServer JarType: §6" + SystemInfo.getJarType());
			s.sendMessage("§e*-=-=-=-=-=-=-* §bServer info §e*-=-=-=-=-=-=-* ");
			return true;
		}
		
		
		// Caso o argumento seja 'host' então é exibida algumas informações do server
		if (args[0].equalsIgnoreCase("host")) {
			
			if (args.length < 2) {
				s.sendMessage("§cComando incorreto, use:");
				s.sendMessage("§c/system host basico §8-§7 Mosta as informações mais basicas e importantes da host.");
				s.sendMessage("§c/system host avancado §8-§7 Gera um relatório super completo TODAS as informações sobre a host.");
				return true;
			}
			
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
				String usedRuntimeMemory = bytesToLegibleValue(machine.maxMemory() - machine.freeMemory());
				String freeComputerMemory = bytesToLegibleValue(SystemInfo.getFreeMemoryComputer(system));
				String totalComputerMemory = bytesToLegibleValue(SystemInfo.getTotalMemoryComputer(system));
				String usedComputerMemory = bytesToLegibleValue(SystemInfo.getTotalMemoryComputer(system) - SystemInfo.getFreeMemoryComputer(system));
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
				s.sendMessage("§eMemória RAM total da maquina: §6" + totalComputerMemory);
				s.sendMessage("§eMemória RAM livre da maquina: §6" + freeComputerMemory);
				s.sendMessage("§eMemória RAM usada na maquina: §6" + usedComputerMemory);
				s.sendMessage("§eArmazenamento total da maquina: §6" + totalComputerSpace);
				s.sendMessage("§eArmazenamento livre da maquina: §6" + freeComputerSpace);
				s.sendMessage("§eArmazenamento usado na maquina: §6" + usedComputerSpace);
				s.sendMessage("§eNúmero de processadores (nucleos): §6" + availableProcessors);
				s.sendMessage("§eArquitetura do processadore: §6" + processorArch);
				s.sendMessage("§eModelo do processador: §6" + (processor == null ? "Informação indisponivel! Veja o relatório avançado." : processor));
				s.sendMessage("§e*-=-=-=-=-=-=-=* §bHost Info §e*=-=-=-=-=-=-=-* ");
				return true;
			}
			
			if (args[1].equalsIgnoreCase("avancado")) {
				s.sendMessage("§aObtendo informações do sistema e da host...");
				SystemInfo.createFullLog();
				s.sendMessage("§aSucesso! O arquivo com todas as informações sobre o sistema e a host foram criados dentro da pasta principal do plugin.");
				return true;
			}

			s.sendMessage("§cComando incorreto, use:");
			s.sendMessage("§c/system host basico §8-§7 Para ver as informações basicas da host.");
			s.sendMessage("§c/system host avancado §8-§7 Para relatório um arquivo com TODAS as informações sobre a host.");
			return true;
		}
		
		// Caso nenhum dos argumentos acima for valido é dado com comando incorreto
		s.sendMessage(Mensagens.System_Comando_Incorreto);
		return true;
	}
	
	
	private String bytesToLegibleValue(long bytes) {
		if (bytes < 1024 * 1024)
			return String.format("%.2f KB", bytes);
		else if (bytes < Math.pow(2, 20) * 1024)
			return String.format("%.2f MB", bytes / Math.pow(2, 20));
		else if (bytes < Math.pow(2, 30) * 1024 )
			return String.format("%.2f GB", bytes / Math.pow(2, 30));
		else if (bytes < Math.pow(2, 40) * 1024)
			return String.format("%.2f TB", bytes / Math.pow(2, 40));
		else
			return "N/A (1TB?)";
	}
}