package rush.entidades;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public abstract class Tpa {

	protected static HashMap<String, Long> COOLDOWN = new HashMap<>();
	protected static HashMap<String, LinkedHashSet<String>> TP_ENVIADOS = new HashMap<>();
	protected static HashMap<String, LinkedHashSet<String>> TP_RECEBIDOS = new HashMap<>();
	protected static HashSet<String> TOGGLE = new HashSet<>();

}
