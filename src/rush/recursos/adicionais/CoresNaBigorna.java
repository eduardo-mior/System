package rush.recursos.adicionais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permissible;

public class CoresNaBigorna implements Listener {
	
	   private String cores = "0123456789abcdefklmnor";
	
    @EventHandler
    public void aoUsarBigorna(final InventoryClickEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (!e.getWhoClicked().hasPermission("system.cornabigorna")) {
            return;
        }
        if (e.getInventory().getType() != InventoryType.ANVIL) {
            return;
        }
        if (e.getSlotType() == InventoryType.SlotType.RESULT) {
            final ItemMeta meta = e.getCurrentItem().getItemMeta();
            final String name = translateColors((Permissible)e.getWhoClicked(), meta.getDisplayName());
            meta.setDisplayName(name);
            e.getCurrentItem().setItemMeta(meta);
        }
    }
    
    private String translateColors(final Permissible p, final String texto) {
        String textoColorido = "";
        for (int i = 0; i < texto.length(); ++i) {
            final char c = texto.charAt(i);
            boolean sucesso  = false;
            if (c == '&') {
                final char cor = texto.charAt(i + 1);
                if (cores.contains(String.valueOf(cor))) {
                	textoColorido = String.valueOf(textoColorido) + "§" + cor;
                    sucesso  = true;
                    ++i;
                }
            }
            if (!sucesso) {
            	textoColorido = String.valueOf(textoColorido) + c;
            }
        }
        return textoColorido;
    }
}
