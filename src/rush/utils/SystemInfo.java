package rush.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;

import rush.Main;

public class SystemInfo {
	
	public static void createFullLog() {
		try {
			File file = new File(Main.get().getDataFolder() + File.separator + "systeminfo.txt");
			if (file.exists())
				file.delete();
		    Runtime runtime = Runtime.getRuntime();
			FileWriter arquivo = new FileWriter(file);
		    PrintWriter gravador = new PrintWriter(arquivo);
		    
		    gravador.println("+----------------------------------+");
		    gravador.println("| Variaveis de Ambiente do Sistema |");
		    gravador.println("+----------------------------------+\n\n");
		    Map<String, String> envs = System.getenv();
		    for (Entry<String, String> env : envs.entrySet()) {
		    	gravador.println(env.getKey() + " : " + env.getValue());
		    }
		    
		    gravador.println("\n\n+----------------------------------+");
		    gravador.println("|  Propriedades Gerais do Sistema  |");
		    gravador.println("+----------------------------------+\n\n");
		    Set<Entry<Object, Object>> properties = System.getProperties().entrySet();
		    for (Entry<Object, Object> property : properties) {
		    	gravador.println(property.getKey() + " : " + property.getValue());
		    }
		    
		    gravador.println("\n\n+----------------------------------+");
		    gravador.println("|   Informações Gerais da Maquina  |");
		    gravador.println("+----------------------------------+");
			String[] infoCommands  = {"systeminfo", "cmdinfo", "srvinfo" , "lshw", "lscpu", "hwinfo", "lspci", "free -m", "cat /proc/meminfo", "cat /proc/cpuinfo", "df -h"};
			for (String command : infoCommands) {
				try {
					String line;
					Process process = runtime.exec(command);
					BufferedReader retorno = new BufferedReader(new InputStreamReader(process.getInputStream()));
					gravador.println("\n**********************************");
					gravador.println("COMMAND: " + command);
					while ((line = retorno.readLine()) != null) {
						gravador.println(line);
					}
					gravador.println("**********************************");
				} catch (Exception e) {}
			}
		    
		    gravador.println("\n\n+----------------------------------+");
		    gravador.println("|  Informações de Rede da Maquina  |");
		    gravador.println("+----------------------------------+");
			String[] netCommands  = {"winipcfg", "ipconfig /all", "ifconfig", "ip a"};
			for (String command : netCommands) {
				try {
					String line;
					Process process = runtime.exec(command);
					BufferedReader retorno = new BufferedReader(new InputStreamReader(process.getInputStream()));
					gravador.println("\n**********************************");
					gravador.println("COMMAND: " + command);
					while ((line = retorno.readLine()) != null) {
						gravador.println(line);
					}
					gravador.println("**********************************");
				} catch (Exception e) {}
			}
			
		    gravador.close();
		    arquivo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getMinecraftVersion() {
		String info = Bukkit.getVersion();
		return info.split("MC: ")[1].split("\\)")[0];
	}
	
	public static String getApiVersion() {
		String info = Bukkit.getBukkitVersion();
		return info.split("-")[0]+"-"+info.split("-")[1];
	}
	
	public static String getJarType() {
		String info = Bukkit.getVersion();
		return info.split("git-")[1].split("-")[0];
	}
	
	public static long getFreeMemoryComputer(OperatingSystemMXBean system) {
		try {
			Method getFreeMemory = system.getClass().getMethod("getFreePhysicalMemorySize");
			getFreeMemory.setAccessible(true);
			return (long) getFreeMemory.invoke(system);
		} catch (Exception e) {
			return -1;
		}
	}
	
	public static long getTotalMemoryComputer(OperatingSystemMXBean system) {
		try {
			Method getTotalMemory = system.getClass().getMethod("getTotalPhysicalMemorySize");
			getTotalMemory.setAccessible(true);
			return (long) getTotalMemory.invoke(system);
		} catch (Exception e) {
			return -1;
		}
	}
	
	public static long getFreeSpaceComputer() {
		long space = 0;
		for (File f : File.listRoots()) {
			space += f.getFreeSpace();
		}
		return space;
	}
	
	public static long getTotalSpaceComputer() {
		long space = 0;
		for (File f : File.listRoots()) {
			space += f.getTotalSpace();
		}
		return space;
	}
	
}
