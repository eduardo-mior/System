package rush.comandos;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

public class ComandoSlime implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("slime")) {
			
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode); 
				return false;
			}
		       
			// Pegando o player e executando o arlgoritmo para verificar se é uma slime chunk
			Player p = (Player)s;
			long worldSeed = p.getPlayer().getWorld().getSeed();
			Chunk playerChunk;
			int xChunk;
			int zChunk;
			Random random = new Random(worldSeed + (xChunk = (playerChunk = p.getPlayer().getWorld().getChunkAt(p.getLocation())).getX()) * xChunk * 4987142 + xChunk * 5947611 + (zChunk = playerChunk.getZ()) * zChunk * 4392871L + zChunk * 389711 ^ 0x3AD8025F);
			if (random.nextInt(10) == 0) {
				p.sendMessage(Mensagens.SlimeChunk_Esta);
				p.playSound(p.getLocation(), Sound.valueOf(Settings.Commando_Slime_Esta), 1, 1);
			} else {
				p.sendMessage(Mensagens.SlimeChunk_NaoEsta);
				p.playSound(p.getLocation(), Sound.valueOf(Settings.Commando_Slime_Nao_esta), 1, 1);
			}
		}
		return false;
	}
}
