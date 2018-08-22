package rush.entidades;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public abstract class Tpa {

	public static HashMap<String, Long> cooldown = new HashMap<>();
	public static HashMap<String, LinkedHashSet<String>> TPs_enviados = new HashMap<>();
	public static HashMap<String, LinkedHashSet<String>> TPs_recebidos = new HashMap<>();
	public static HashSet<String> toggles = new HashSet<>();

}
