package rush.comandos;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import rush.configuracoes.Mensagens;
import rush.entidades.Kit;
import rush.entidades.Kits;
import rush.utils.TimeFormatter;
import rush.utils.manager.DataManager;

@SuppressWarnings("all")
public class ComandoKit implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			
			// Verificando se o player digitou o número de argumentos corretos
			if (args.length != 2) {
				s.sendMessage(Mensagens.DarKit_Comando_Incorreto);
				return true;
			}
			
			// Pegando o argumento e verificando se o kit existe
			String id = args[0].toLowerCase();
			if (!Kits.contains(id)) {
				s.sendMessage(Mensagens.Kit_Nao_Existe.replace("%kit-id%", id));
				ComandoKits.ListKitsForStaff(s);
				return true;
			}

			// Pegando o player e verificando se ele esta online
			Player p = Bukkit.getPlayer(args[1]);
			if (p == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}
			
			// Pegando o kit e adicionando para o player
			Kit kit = Kits.get(id);
			ItemStack[] ITENS = kit.getItens();
			forceAddItensToInventory(p, ITENS);
			s.sendMessage(Mensagens.Kit_Enviado.replace("%player%", p.getName()).replace("%kit-id%", id).replace("%kit-nome%", kit.getNome()));
			return true;
		}

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.Kit_Comando_Incorreto);
			return true;
		}

		// Pegando o argumento e verificando se o kit existe
		String id = args[0].toLowerCase();
		if (!Kits.contains(id)) {
			s.sendMessage(Mensagens.Kit_Nao_Existe.replace("%kit-id%", id));
			if (!s.hasPermission("system.kit.all") && !s.isOp()) {
				ComandoKits.ListKits(s);
			} else {
				ComandoKits.ListKitsForStaff(s);
			}
			return true;
		}
		
		// Pegando o kit e verificando se o player possui permissão para pegar
		Kit kit = Kits.get(id);
		String perm = kit.getPermissao();
		String nomeKit = kit.getNome();
		if (!s.hasPermission(perm) && !s.hasPermission("system.kit.all") && !s.isOp()) {
			s.sendMessage(Mensagens.Kit_Sem_Permissao.replace("%kit-id%", id).replace("%kit-nome%",	nomeKit));
			return true;
		}

		// Pegando o player, a lista de kits do players, os itens do kit e o delay do kit
		Player p = (Player) s;
		File filePlayer = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
		FileConfiguration configPlayer = DataManager.getConfiguration(filePlayer);
		Set<String> KITS = configPlayer.getConfigurationSection("Kits").getKeys(false);
		ItemStack[] ITENS = kit.getItens();
		long millisKit = System.currentTimeMillis() + (kit.getDelay() * 60000);

		// Verificando se o player já pegou este kit alguma vez na vida
		if (!KITS.contains(id)) {
			
			// Verificando se o player tem espaço no inventario para pegar o kit
			if (getFreeSpaceInInventory(p) < kit.getAmountItens()) {
				s.sendMessage(Mensagens.Kit_Sem_Espaco_Pra_Pegar);
				return true;
			}
			
			// Adicionando os itens no inventario do player e salvando na config
			configPlayer.set("Kits." + id, millisKit);
			try {
				configPlayer.save(filePlayer);
				addItensToInventory(p, ITENS);
				s.sendMessage(Mensagens.Kit_Pego.replace("%kit-id%", id).replace("%kit-nome%", nomeKit));
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", filePlayer.getName()));
			}
			return true;
		}

		// Caso o player nunca tenha pegado o kit verificamos se ele já pode pegar o kit novamente
		long millisPlayer = configPlayer.getLong("Kits." + id);
		if (millisPlayer > System.currentTimeMillis() && !p.hasPermission("system.bypass.delaykit")) {
			long millis = millisPlayer - System.currentTimeMillis();
			String tempo = TimeFormatter.format(millis);
			s.sendMessage(Mensagens.Kit_Aguarde.replace("%kit-id%", id).replace("%kit-nome%", nomeKit).replace("%tempo%", tempo));
			return true;
		}

		// Verificando se o player tem espaço no inventario para pegar o kit
		if (getFreeSpaceInInventory(p) < kit.getAmountItens()) {
			s.sendMessage(Mensagens.Kit_Sem_Espaco_Pra_Pegar);
			return true;
		}
		
		// Caso ele já possa pegar o kit novamente então o delay é setado e os itens são enviados para o player
		configPlayer.set("Kits." + id, millisKit);
		try {
			configPlayer.save(filePlayer);
			addItensToInventory(p, ITENS);
			s.sendMessage(Mensagens.Kit_Pego.replace("%kit-id%", id).replace("%kit-nome%", nomeKit));
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", filePlayer.getName()));
		}
		return true;
	}

	// Método para adicionar os itens no inv do player
	private void addItensToInventory(Player p, ItemStack[] itens) {
		PlayerInventory inv = p.getInventory();
		for (ItemStack item : itens) {
			if (item != null) inv.addItem(item.clone());
		}
	}
	
	// Método para adicionar os itens no inv do player
	private void forceAddItensToInventory(Player p, ItemStack[] itens) {
		PlayerInventory inv = p.getInventory();
		for (ItemStack item : itens) {
			if (item != null) {
				for (ItemStack drop : inv.addItem(item.clone()).values()) {
					p.getWorld().dropItem(p.getLocation(), drop);
				}
			}
		}
	}
	
	// Método para pegar o número de slots livres no inv do player
	private int getFreeSpaceInInventory(Player p) {
		int free = 0;
		ItemStack[] itens = p.getInventory().getContents();
		for (ItemStack item : itens) {
			if (item == null || item.getType() == Material.AIR) free++;
		}
		return free;
	}
}