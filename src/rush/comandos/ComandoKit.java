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
import rush.utils.DataManager;
import rush.utils.TimeFormatter;

public class ComandoKit implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.Kit_Comando_Incorreto);
			return true;
		}

		// Pegando o argumento e verificando se o kit existe
		String nomeKit = args[0].toLowerCase();
		if (!Kits.contains(nomeKit)) {
			s.sendMessage(Mensagens.Kit_Nao_Existe.replace("%kit%", nomeKit));
			ComandoKits.ListKits(s);
			return true;
		}
		
		// Pegando o kit e verificando se o player possui permissão para pegar fala
		Kit kit = Kits.get(nomeKit);
		String perm = kit.getPermissao();
		if (!s.hasPermission(perm) && !s.hasPermission("system.kit.all")) {
			s.sendMessage(Mensagens.Kit_Sem_Permissao.replace("%kit%", nomeKit));
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
		if (!KITS.contains(nomeKit)) {
			
			// Verificando se o player tem espaço no inventario para pegar o kit
			if (getFreeSpaceInInventory(p) < kit.getAmountItens()) {
				s.sendMessage(Mensagens.Kit_Sem_Espaco_Pra_Pegar);
				return true;
			}
			
			// Adicionando os itens no inventario do player e salvando na config
			configPlayer.set("Kits." + nomeKit, millisKit);
			try {
				configPlayer.save(filePlayer);
				addItensToInventory(p, ITENS);
				s.sendMessage(Mensagens.Kit_Pego.replace("%kit%", nomeKit));
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", filePlayer.getName()));
			}
			return true;
		}

		// Caso o player nunca tenha pegado o kit verificamos se ele já pode pegar o kit novamente
		long millisPlayer = configPlayer.getLong("Kits." + nomeKit);
		if (millisPlayer > System.currentTimeMillis() && !p.hasPermission("system.bypass.delaykit")) {
			long millis = millisPlayer - System.currentTimeMillis();
			String tempo = TimeFormatter.format(millis);
			s.sendMessage(Mensagens.Kit_Aguarde.replace("%kit%", nomeKit).replace("%tempo%", tempo));
			return true;
		}

		// Verificando se o player tem espaço no inventario para pegar o kit
		if (getFreeSpaceInInventory(p) < kit.getAmountItens()) {
			s.sendMessage(Mensagens.Kit_Sem_Espaco_Pra_Pegar);
			return true;
		}
		
		// Caso ele já possa pegar o kit novamente então o delay é setado e os itens são enviados para o player
		configPlayer.set("Kits." + nomeKit, millisKit);
		try {
			configPlayer.save(filePlayer);
			addItensToInventory(p, ITENS);
			s.sendMessage(Mensagens.Kit_Pego.replace("%kit%", nomeKit));
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", filePlayer.getName()));
		}
		return true;
	}

	// Método para adicionar os itens no inv do player
	private void addItensToInventory(Player p, ItemStack[] itens) {
		PlayerInventory inv = p.getInventory();
		for (ItemStack item : itens) {
			if (item != null) inv.addItem(item);
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