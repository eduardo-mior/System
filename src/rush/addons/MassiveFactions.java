package rush.addons;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

public class MassiveFactions {

	public static boolean isValidLocation(Location l, Player p) {
		
		MPlayer mp = MPlayer.get(p);
        BoardColl coll = BoardColl.get();
        Faction faction = coll.getFactionAt(PS.valueOf(l));
		if (!faction.getMPlayers().contains(mp) && !(faction.getRelationTo(mp.getFaction()) == Rel.ALLY)) {
			if (faction.getId().equals(Factions.ID_NONE) || faction.getId().equals(Factions.ID_WARZONE) || faction.getId().equals(Factions.ID_SAFEZONE)) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
}
