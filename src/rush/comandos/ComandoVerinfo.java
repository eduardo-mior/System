package rush.comandos;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import rush.apis.GodModeAPI;
import rush.apis.HealthAPI;
import rush.apis.PingAPI;
import rush.configuracoes.Mensagens;
import rush.sistemas.comandos.VanishListener;

@SuppressWarnings("all")
public class ComandoVerinfo implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.Verinfo_Comando_Incorreto);
			return true;
		}

		// Pegando o player e verificando se ele esta online
		Player p = Bukkit.getPlayer(args[0]);
		if (p == null) {
			s.sendMessage(Mensagens.Player_Offline);
			return true;
		}

		// Pegando T O D A S as informações do player
		Location loc = p.getLocation();
		String efeitos = getEffects(p.getActivePotionEffects());
		String customName = p.getCustomName() == null ? "Não possui" : p.getCustomName();
		String playerName = p.getName();
		String displayName = p.getDisplayName();
		String health = String.format("%.0f", HealthAPI.getHealth(p));
		String maxHealth = String.format("%.0f", HealthAPI.getMaxHealth(p));
		String food = String.format("%d", p.getFoodLevel());
		String maxFood = String.format("%d", 20);
		String saturation = String.format("%.1f", p.getSaturation());
		String maxSaturation = String.format("%d", p.getFoodLevel());
		String exhaustion = String.format("%.1f", p.getExhaustion());
		String maxExhaustion = String.format("%.1f", 4.0f);
		String air = String.format("%d", p.getRemainingAir());
		String maxAir = String.format("%d", p.getMaximumAir());
		String xpTotal = String.format("%d", p.getTotalExperience());
		String xpLevel = String.format("%d", p.getLevel());
		String flySpeed = String.format("%.1f", p.getFlySpeed());
		String walkSpeed = String.format("%.1f", p.getWalkSpeed());
		String yaw = String.format("%.1f", loc.getYaw());
		String pitch = String.format("%.1f", loc.getPitch());
		String x = String.format("%.0f", loc.getX());
		String y = String.format("%.0f", loc.getY());
		String z = String.format("%.0f", loc.getZ());
		String chunk_z = String.format("%d", loc.getChunk().getZ());
		String chunk_x = String.format("%d", loc.getChunk().getZ());
		String world = loc.getWorld().getName();
		String gamemode = p.getGameMode().name();
		String ping = PingAPI.getPlayerPing(p);
		String hasCustomName = translateBoolean(p.isCustomNameVisible());
		String dead = translateBoolean(p.isDead());
		String fly = translateBoolean(p.getAllowFlight());
		String vanish = translateBoolean(VanishListener.VANISH.contains(p));
		String god = translateBoolean(GodModeAPI.getGodMode(p));
		String vehicle = translateBoolean(p.isInsideVehicle());
		String vehicleType = p.getVehicle() == null ? "Nenhum" : p.getVehicle().getType().name();
		String sleep = translateBoolean(p.isSleeping());
		String op = translateBoolean(p.isOp() || p.hasPermission("*"));
		String whitelist = translateBoolean(p.isWhitelisted());
		String ip = p.getAddress().getAddress().getHostAddress();
		String locale = getLocale(p);

		// Enviando a mensagens com as informações para o curioso (sender)
		s.sendMessage(Mensagens.Verinfo_Informacoes
				.replace("%player-name%", playerName)
				.replace("%display-name%", displayName)
				.replace("%custom-name%", customName)
				.replace("%ar%", air)
				.replace("%max-ar%", maxAir)
				.replace("%vida%", health)
				.replace("%max-vida%", maxHealth)
				.replace("%fome%", food)
				.replace("%max-fome%", maxFood)
				.replace("%saturacao%", saturation)
				.replace("%max-saturacao%", maxSaturation)
				.replace("%exaustao%", exhaustion)
				.replace("%max-exaustao%", maxExhaustion)
				.replace("%efeitos%", efeitos)
				.replace("%xp-total%", xpTotal)
				.replace("%xp-level%", xpLevel)
				.replace("%ping%", ping)
				.replace("%speed-fly%", flySpeed)
				.replace("%speed-walk%", walkSpeed)
				.replace("%world%", world)
				.replace("%yaw%", yaw)
				.replace("%pitch%", pitch)
				.replace("%x%", x)
				.replace("%y%", y)
				.replace("%z%", z)
				.replace("%chunk-x%", chunk_x)
				.replace("%chunk-z%", chunk_z)
				.replace("%gamemode%", gamemode)
				.replace("%custom-name-visivel%", hasCustomName)
				.replace("%fly%", fly)
				.replace("%god%", god)
				.replace("%vanish%", vanish)
				.replace("%morto%", dead)
				.replace("%dormindo%", sleep)
				.replace("%veiculo%", vehicle)
				.replace("%veiculo-tipo%", vehicleType)
				.replace("%op%", op)
				.replace("%whitelist%", whitelist)
				.replace("%ip%", ip)
				.replace("%locale%", locale));
		return true;
	}

	// Método para traduzir o true e o false
	private String translateBoolean(boolean bool) {
		return bool ? "§2Sim" : "§4Não";
	}

	// Método para pegar a lista de efeitos
	private String getEffects(Collection<PotionEffect> effects) {
		if (!effects.isEmpty()) {
			String str = "";
			for (PotionEffect effect : effects) {
				str += effect.getType().getName() + ", ";
			}
			return str.substring(0, str.length() -2);
		} else
			return "Nenhum";
	}
	
	// Método para pegar a linguagem do player
	private String getLocale(Player player) {
		try {
			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			Field ping = entityPlayer.getClass().getField("locale");
			return String.valueOf(ping.get(entityPlayer));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException	| SecurityException | NoSuchFieldException e) {
			return "§cIndisponivel";
		}
	}
	
}