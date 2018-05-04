package rush.utils;

import java.util.concurrent.TimeUnit;

/**
 * 
 * © DevNatan, 2017. Todos os direitos reservados
 * 
 * @since 2017
 * @version 1.1
 * @author DevNatan
 *
 * Plugin de Bibliotecas Oficial.
 * Desenvolvido por DevNatan.
 * 
 * Conversor de milisegundos para linha legível.
 *   
 */

public class TimeFormatter {

	private long tempo;
	
	public TimeFormatter(long tempo) {
		this.tempo = tempo;
	}
	
	public String format() {
		if (tempo == 0)
			return "0 segundos";

		long dias = TimeUnit.MILLISECONDS.toDays(tempo);
		long horas = TimeUnit.MILLISECONDS.toHours(tempo) - (dias * 24);
		long minutos = TimeUnit.MILLISECONDS.toMinutes(tempo) - (TimeUnit.MILLISECONDS.toHours(tempo) * 60);
		long segundos = TimeUnit.MILLISECONDS.toSeconds(tempo) - (TimeUnit.MILLISECONDS.toMinutes(tempo) * 60);
		
		StringBuilder sb = new StringBuilder();
		
		if(dias > 0)
			sb.append(dias + (dias == 1 ? " dia" : " dias"));
		
		if(horas > 0)
			sb.append(dias > 0 ? (minutos > 0 ? ", " : " e ") : "").append(horas + (horas == 1 ? " hora" : " horas"));
		
		if(minutos > 0)
			sb.append(dias > 0 || horas > 0 ? (segundos > 0 ? ", " : " e ") : "").append(minutos + (minutos == 1 ? " minuto" : " minutos"));
		
		if(segundos > 0)
			sb.append(dias > 0 || horas > 0 || minutos > 0 ? " e " : (sb.length() > 0 ? ", " : "")).append(segundos + (segundos == 1 ? " segundo" : " segundos"));
		
		String s = sb.toString();
		return s.isEmpty() ? "0 segundos" : s;
	}
	
	public static String format(long tempo) {
		return new TimeFormatter(tempo).format();
	}
	
}
 