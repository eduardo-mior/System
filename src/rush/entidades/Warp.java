package rush.entidades;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Warp {

	private String nome;
	private Location location;
	private String permissao;
	private String semPermissao;
	private int delay;
	private boolean delayParaVips;
	private String mensagemInicio;
	private String mensagemFinal;
	private boolean enviarTitle;
	private String title;
	private String subtitle;

	public Warp(String nome, String location, String permissao, String semPermissao, int delay, boolean delayVip,
				String mensagemInicio, String mensagemFinal, boolean enviarTitle, String title, String subtitle) {
		this.location = deserializeLocation(location);
		this.nome = nome;
		this.permissao = permissao;
		this.semPermissao = semPermissao;
		this.delay = delay;
		this.delayParaVips = delayVip;
		this.mensagemInicio = mensagemInicio.replace('&', '§');
		this.mensagemFinal = mensagemFinal.replace('&', '§');
		this.enviarTitle = enviarTitle;
		this.title = title.replace('&', '§');
		this.subtitle = subtitle.replace('&', '§');
	}

    
    private Location deserializeLocation(String s) {
    	String[] locationSplitted = s.split(",");
		return new Location(
			   Bukkit.getWorld(locationSplitted[0]),
			   Double.parseDouble(locationSplitted[1]),
			   Double.parseDouble(locationSplitted[2]),
			   Double.parseDouble(locationSplitted[3]),
			   Float.parseFloat(locationSplitted[4]),
			   Float.parseFloat(locationSplitted[5]));
    }


	public Location getLocation() {
		return location;
	}


	public String getPermissao() {
		return permissao;
	}


	public String getSemPermissao() {
		return semPermissao;
	}


	public int getDelay() {
		return delay;
	}


	public boolean delayParaVips() {
		return delayParaVips;
	}


	public String getMensagemInicio() {
		return mensagemInicio;
	}


	public String getMensagemFinal() {
		return mensagemFinal;
	}


	public boolean enviarTitle() {
		return enviarTitle;
	}


	public String getTitle() {
		return title;
	}


	public String getSubtitle() {
		return subtitle;
	}


	public String getNome() {
		return nome;
	}

}
