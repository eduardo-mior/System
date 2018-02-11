package rush.recursos.antibug;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;

import rush.Main;

public class BloquearPassarDaBorda implements Listener {
	
    @EventHandler
    public void aoLancarEnderPearl(PlayerTeleportEvent e) {
       if (e.getPlayer().getWorld().getWorldBorder() != null && e.getCause() == TeleportCause.ENDER_PEARL) {
          double worldborder = e.getPlayer().getWorld().getWorldBorder().getSize() / 2.0D;
          if (e.getPlayer().getWorld().getWorldBorder().getCenter().getX() + worldborder < e.getTo().getX()) {
             e.setCancelled(true);
             e.getPlayer().getInventory().addItem(new ItemStack[]{new ItemStack(Material.ENDER_PEARL, 1)});
             e.getPlayer().sendMessage(Main.aqui.getMensagens().getString("Tentou-Passar-Da-Borda").replaceAll("&", "§"));
          } else if (e.getPlayer().getWorld().getWorldBorder().getCenter().getX() - worldborder > e.getTo().getX()) {
             e.setCancelled(true);
             e.getPlayer().getInventory().addItem(new ItemStack[]{new ItemStack(Material.ENDER_PEARL, 1)});
             e.getPlayer().sendMessage(Main.aqui.getMensagens().getString("Tentou-Passar-Da-Borda").replaceAll("&", "§"));
          } else if (e.getPlayer().getWorld().getWorldBorder().getCenter().getZ() + worldborder < e.getTo().getZ()) {
             e.setCancelled(true);
             e.getPlayer().getInventory().addItem(new ItemStack[]{new ItemStack(Material.ENDER_PEARL, 1)});
             e.getPlayer().sendMessage(Main.aqui.getMensagens().getString("Tentou-Passar-Da-Borda").replaceAll("&", "§"));
          } else if (e.getPlayer().getWorld().getWorldBorder().getCenter().getZ() - worldborder > e.getTo().getZ()) {
             e.setCancelled(true);
             e.getPlayer().getInventory().addItem(new ItemStack[]{new ItemStack(Material.ENDER_PEARL, 1)});
             e.getPlayer().sendMessage(Main.aqui.getMensagens().getString("Tentou-Passar-Da-Borda").replaceAll("&", "§"));
          }
       }
 }
	
}
