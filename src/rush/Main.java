package rush;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import rush.addons.LegendChat;
import rush.addons.Mcmmo;
import rush.addons.Vault;
import rush.addons.legendchat.MagnataTag;
import rush.addons.legendchat.McTopTag;
import rush.apis.APIS;
import rush.comandos.ComandoAlerta;
import rush.comandos.ComandoAlertaOLD;
import rush.comandos.ComandoBack;
import rush.comandos.ComandoBigorna;
import rush.comandos.ComandoChapeu;
import rush.comandos.ComandoClear;
import rush.comandos.ComandoClearChat;
import rush.comandos.ComandoCompactar;
import rush.comandos.ComandoCompactarOLD;
import rush.comandos.ComandoCores;
import rush.comandos.ComandoCraft;
import rush.comandos.ComandoCrashar;
import rush.comandos.ComandoCriarkit;
import rush.comandos.ComandoDarkit;
import rush.comandos.ComandoDelhome;
import rush.comandos.ComandoDelkit;
import rush.comandos.ComandoDelwarp;
import rush.comandos.ComandoDerreter;
import rush.comandos.ComandoDivulgar;
import rush.comandos.ComandoEchest;
import rush.comandos.ComandoEditaritem;
import rush.comandos.ComandoEditaritemOLD;
import rush.comandos.ComandoEditarkit;
import rush.comandos.ComandoEditarplaca;
import rush.comandos.ComandoEnchant;
import rush.comandos.ComandoEstatisticas;
import rush.comandos.ComandoExecutarSom;
import rush.comandos.ComandoFeed;
import rush.comandos.ComandoFly;
import rush.comandos.ComandoGamemode;
import rush.comandos.ComandoGod;
import rush.comandos.ComandoHeal;
import rush.comandos.ComandoHome;
import rush.comandos.ComandoHomes;
import rush.comandos.ComandoInvsee;
import rush.comandos.ComandoKit;
import rush.comandos.ComandoKits;
import rush.comandos.ComandoLixo;
import rush.comandos.ComandoLuz;
import rush.comandos.ComandoMundoVip;
import rush.comandos.ComandoOnline;
import rush.comandos.ComandoParticular;
import rush.comandos.ComandoPing;
import rush.comandos.ComandoPotion;
import rush.comandos.ComandoPublica;
import rush.comandos.ComandoRenderizacao;
import rush.comandos.ComandoReparar;
import rush.comandos.ComandoSGive;
import rush.comandos.ComandoSethome;
import rush.comandos.ComandoSetmundovip;
import rush.comandos.ComandoSetspawn;
import rush.comandos.ComandoSetwarp;
import rush.comandos.ComandoSkull;
import rush.comandos.ComandoSlime;
import rush.comandos.ComandoSpawn;
import rush.comandos.ComandoSpeed;
import rush.comandos.ComandoSudo;
import rush.comandos.ComandoSystem;
import rush.comandos.ComandoTitle;
import rush.comandos.ComandoTp;
import rush.comandos.ComandoTpa;
import rush.comandos.ComandoTpaccept;
import rush.comandos.ComandoTpall;
import rush.comandos.ComandoTpcancel;
import rush.comandos.ComandoTpdeny;
import rush.comandos.ComandoTphere;
import rush.comandos.ComandoTptoggle;
import rush.comandos.ComandoVanish;
import rush.comandos.ComandoVerinfo;
import rush.comandos.ComandoVerkit;
import rush.comandos.ComandoWarp;
import rush.comandos.ComandoWarpOLD;
import rush.comandos.ComandoWarps;
import rush.configuracoes.Locations;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;
import rush.entidades.Command;
import rush.entidades.Kits;
import rush.entidades.Warps;
import rush.enums.JarType;
import rush.enums.Version;
import rush.recursos.bloqueadores.BloquearAbrirContainers;
import rush.recursos.bloqueadores.BloquearCairNoVoid;
import rush.recursos.bloqueadores.BloquearCama;
import rush.recursos.bloqueadores.BloquearComandos;
import rush.recursos.bloqueadores.BloquearCongelarAgua;
import rush.recursos.bloqueadores.BloquearCrafts;
import rush.recursos.bloqueadores.BloquearCriarPortal;
import rush.recursos.bloqueadores.BloquearDerreterGeloENeve;
import rush.recursos.bloqueadores.BloquearExplodirItens;
import rush.recursos.bloqueadores.BloquearKickPorDuploLogin;
import rush.recursos.bloqueadores.BloquearKickPorDuploLoginSuper;
import rush.recursos.bloqueadores.BloquearMobsDePegaremFogoParaOSol;
import rush.recursos.bloqueadores.BloquearMobsDePegaremItensDoChao;
import rush.recursos.bloqueadores.BloquearNameTag;
import rush.recursos.bloqueadores.BloquearNicksImproprios;
import rush.recursos.bloqueadores.BloquearPassarDaBorda;
import rush.recursos.bloqueadores.BloquearPlacas;
import rush.recursos.bloqueadores.BloquearQuebrarPlantacoesPulando;
import rush.recursos.bloqueadores.BloquearShiftEmContainers;
import rush.recursos.bloqueadores.BloquearSubirEmVeiculos;
import rush.recursos.bloqueadores.BloquearSubirNoTetoNether;
import rush.recursos.bloqueadores.BloquearTeleportPorPortal;
import rush.recursos.desativadores.DesativarChuva;
import rush.recursos.desativadores.DesativarCicloDoDia;
import rush.recursos.desativadores.DesativarDanoDoBlaze;
import rush.recursos.desativadores.DesativarDanoDoCacto;
import rush.recursos.desativadores.DesativarDanoDoEnderDragon;
import rush.recursos.desativadores.DesativarDanoDoGhast;
import rush.recursos.desativadores.DesativarDanoDoWither;
import rush.recursos.desativadores.DesativarFlowDaAguaELava;
import rush.recursos.desativadores.DesativarFomeNosMundos;
import rush.recursos.desativadores.DesativarMensagemDeEntrada;
import rush.recursos.desativadores.DesativarMensagemDeMorte;
import rush.recursos.desativadores.DesativarMensagemDeSaida;
import rush.recursos.desativadores.DesativarMobsNaturais;
import rush.recursos.desativadores.DesativarMobsNaturaisOLD;
import rush.recursos.desativadores.DesativarPropagacaoDoFogo;
import rush.recursos.desativadores.DesativarQuedaDaAreia;
import rush.recursos.desativadores.DesativarQuedaDaBigorna;
import rush.recursos.desativadores.DesativarQuedaDasFolhas;
import rush.recursos.gerais.BigornaInfinita;
import rush.recursos.gerais.BloquearMoneyInvalido;
import rush.recursos.gerais.ComandosPrimeiroLogin;
import rush.recursos.gerais.CoresNaBigorna;
import rush.recursos.gerais.CoresNaPlaca;
import rush.recursos.gerais.EnderPearlCooldown;
import rush.recursos.gerais.EntrarNoSpawnAoLogar;
import rush.recursos.gerais.InvencibilidadeAoTeleportar;
import rush.recursos.gerais.LimiteDePlayers;
import rush.recursos.gerais.ManterXpAoMorrer;
import rush.recursos.gerais.MensagemDeBoasVindas;
import rush.recursos.gerais.Outros;
import rush.recursos.gerais.TitleDeBoasVindas;
import rush.sistemas.comandos.BackListener;
import rush.sistemas.comandos.EnderChestListener;
import rush.sistemas.comandos.FlyListener;
import rush.sistemas.comandos.InvseeListener;
import rush.sistemas.comandos.KitsListener;
import rush.sistemas.comandos.VanishListener;
import rush.sistemas.gerais.AnunciarMorte;
import rush.sistemas.gerais.AutoAnuncio;
import rush.sistemas.gerais.CooldownComandos;
import rush.sistemas.gerais.DelayComandos;
import rush.sistemas.gerais.DeletarComandos;
import rush.sistemas.gerais.DroparCabecaAoMorrer;
import rush.sistemas.gerais.Motd;
import rush.sistemas.gerais.PlayerData;
import rush.sistemas.gerais.ScoreBoard;
import rush.sistemas.gerais.ScoreBoardOLD;
import rush.sistemas.gerais.StackMobs;
import rush.sistemas.gerais.Tablist;
import rush.sistemas.spawners.BloquearTrocarTipoDoSpawnerComOvo;
import rush.sistemas.spawners.DroparSpawnerAoExplodir;
import rush.sistemas.spawners.DroparSpawnerAoExplodirOLD;
import rush.sistemas.spawners.SistemaDeSpawners;
import rush.sistemas.spawners.SistemaDeSpawnersOLD;
import rush.utils.ReflectionUtils;
import rush.utils.manager.ConfigManager;
import rush.utils.manager.DataManager;

public class Main extends JavaPlugin {

	private static Main main;
	private static Version version;
	private static JarType jarType;
	public static boolean setupFactions;

	@Override
	public void onEnable() {
		enablePlugin();
		gerarConfigs();
		carregarConfigs();
		registrarEventos();
		registrarComandos();
	}

	@Override
	public void onDisable() {
		disablePlugin();
	}

	private void enablePlugin() {
		main = this;
		version = Version.getServerVersion();
		jarType = JarType.getJarType();
		ReflectionUtils.loadUtils();
		APIS.load();
	}

	private void gerarConfigs() {
		DataManager.createFolder("kits");
		DataManager.createFolder("warps");
		DataManager.createFolder("playerdata");
		ConfigManager.createConfig("comandos");
		ConfigManager.createConfig("settings");
		ConfigManager.createConfig("mensagens");
		ConfigManager.createConfig("ajuda");
		ConfigManager.createConfig("locations");
	}

	private void carregarConfigs() {
		Kits.loadKits();
		Warps.loadWarps();
		Settings.loadSettings();
		Mensagens.loadMensagens();
		Locations.loadLocations();
	}

	private void registrarComandos() {
		new Command("back", "system.back", new ComandoBack());
		new Command("chapeu", "system.chapeu", new ComandoChapeu());
		new Command("clear", "system.clear", new ComandoClear());
		new Command("clearchat", "system.clearchat", new ComandoClearChat());
		new Command("cores", "system.cores", new ComandoCores());
		new Command("craft", "system.craft", new ComandoCraft());
		new Command("crashar", "system.crashar", new ComandoCrashar());
		new Command("criarkit", "system.criarkit", new ComandoCriarkit());
		new Command("darkit", "system.darkit", new ComandoDarkit());
		new Command("delhome", "system.delhome", new ComandoDelhome());
		new Command("delkit", "system.delkit", new ComandoDelkit());
		new Command("delwarp", "system.delwarp", new ComandoDelwarp());
		new Command("derreter", "system.derreter", new ComandoDerreter());
		new Command("divulgar", "system.divulgar", new ComandoDivulgar());
		new Command("echest", "system.echest", new ComandoEchest());
		new Command("editarkit", "system.editarkit", new ComandoEditarkit());
		new Command("editarplaca", "system.editarplaca", new ComandoEditarplaca());
		new Command("enchant", "system.enchant", new ComandoEnchant());
		new Command("executarsom", "system.executarsom", new ComandoExecutarSom());
		new Command("feed", "system.feed", new ComandoFeed());
		new Command("fly", "system.fly", new ComandoFly());
		new Command("gamemode", "system.gamemode", new ComandoGamemode());
		new Command("god", "system.god", new ComandoGod());
		new Command("heal", "system.heal", new ComandoHeal());
		new Command("home", "system.home", new ComandoHome());
		new Command("homes", "system.home", new ComandoHomes());
		new Command("invsee", "system.invsee", new ComandoInvsee());
		new Command("kit", "system.kit", new ComandoKit());
		new Command("kits", "system.kits", new ComandoKits());
		new Command("lixo", "system.lixo", new ComandoLixo());
		new Command("luz", "system.luz", new ComandoLuz());
		new Command("mundovip", "system.mundovip", new ComandoMundoVip());
		new Command("online", "system.online", new ComandoOnline());
		new Command("particular", "system.particular", new ComandoParticular());
		new Command("ping", "system.ping", new ComandoPing());
		new Command("potion", "system.potion", new ComandoPotion());
		new Command("publica", "system.publica", new ComandoPublica());
		new Command("reparar", "system.reparar", new ComandoReparar());
		new Command("sethome", "system.sethome", new ComandoSethome());
		new Command("setmundovip", "system.setmundovip", new ComandoSetmundovip());
		new Command("setspawn", "system.setspawn", new ComandoSetspawn());
		new Command("setwarp", "system.setwarp", new ComandoSetwarp());
		new Command("slime", "system.slime", new ComandoSlime());
		new Command("spawn", "system.spawn", new ComandoSpawn());
		new Command("speed", "system.speed", new ComandoSpeed());
		new Command("sudo", "system.sudo", new ComandoSudo());
		new Command("system", "system.system", new ComandoSystem());
		new Command("tp", "system.tp", new ComandoTp());
		new Command("tpa", "system.tpa", new ComandoTpa());
		new Command("tpaccept", "system.tpaccept", new ComandoTpaccept());
		new Command("tpall", "system.tpall", new ComandoTpall());
		new Command("tpcancel", "system.tpcancel", new ComandoTpcancel());
		new Command("tpdeny", "system.tpdeny", new ComandoTpdeny());
		new Command("tphere", "system.tphere", new ComandoTphere());
		new Command("tptoggle", "system.tptoggle", new ComandoTptoggle());
		new Command("vanish", "system.vanish", new ComandoVanish());
		new Command("verkit", "system.verkit", new ComandoVerkit());
		new Command("verinfo", "system.verinfo", new ComandoVerinfo());
		new Command("warps", "system.warps", new ComandoWarps());

		if (isOldVersion()) {
			new Command("alerta", "system.alerta", new ComandoAlertaOLD());
			new Command("compactar", "system.compactar", new ComandoCompactarOLD());
			new Command("editaritem", "system.editaritem", new ComandoEditaritemOLD());
			new Command("warp", "system.warp", new ComandoWarpOLD());
		} else {
			new Command("alerta", "system.alerta", new ComandoAlerta());
			new Command("compactar", "system.compactar", new ComandoCompactar());
			new Command("editaritem", "system.editaritem", new ComandoEditaritem());
			new Command("skull", "system.skull", new ComandoSkull());
			new Command("title", "system.title", new ComandoTitle());
			new Command("warp", "system.warp", new ComandoWarp());
		}
		
		if (!isOldVersion() && !isVeryFuckingNewVersion()) {
			new Command("renderizacao", "system.renderizacao", new ComandoRenderizacao());
		}
		
		if (!isVeryOldVersion()) {
			new Command("estatisticas", "system.estatisticas", new ComandoEstatisticas());
		}
		
		if (!isVeryNewVersion()) {
			new Command("sgive", "system.sgive", new ComandoSGive());
		}
		
		if (!isVeryFuckingNewVersion()) {
			new Command("bigorna", "system.bigorna", new ComandoBigorna());
		}
		
	}

	private void registrarEventos() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		FileConfiguration commands = ConfigManager.getConfig("comandos");
				
		if (Settings.Anunciar_Morte) {
			pm.registerEvents(new AnunciarMorte(), this);
		}

		if (Settings.Ativar_Cores_Na_Bigorna) {
			pm.registerEvents(new CoresNaBigorna(), this);
		}

		if (Settings.Ativar_Cores_Na_Placa) {
			pm.registerEvents(new CoresNaPlaca(), this);
		}

		if (Settings.Auto_Anuncio) {
			if (Settings.Lista_De_Anuncios != null && Settings.Lista_De_Anuncios.size() > 0) {
				AutoAnuncio.runMensagens();	
			}
		}

		if (Settings.Bigorna_Infinita && !isVeryFuckingNewVersion()) {
			pm.registerEvents(new BigornaInfinita(), this);
		}

		if (Settings.Bloquear_Abrir_Containers_Ativar) {
			if (Settings.Bloquear_Abrir_Containers_Containers != null && Settings.Bloquear_Abrir_Containers_Containers.size() > 0) {
				pm.registerEvents(new BloquearAbrirContainers(), this);	
			}
		}

		if (Settings.Bloquear_Cair_No_Void) {
			pm.registerEvents(new BloquearCairNoVoid(), this);
		}

		if (Settings.Bloquear_Cama) {
			pm.registerEvents(new BloquearCama(), this);
		}

		if (Settings.Bloquear_Comandos) {
			if (Settings.Lista_Dos_Comandos_Bloqueados != null && Settings.Lista_Dos_Comandos_Bloqueados.size() > 0) {
				pm.registerEvents(new BloquearComandos(), this);
			}
		}

		if (Settings.Bloquear_Congelar_Agua) {
			pm.registerEvents(new BloquearCongelarAgua(), this);
		}

		if (Settings.Bloquear_Crafts) {
			if (Settings.Lista_Dos_Crafts_Bloqueados != null && Settings.Lista_Dos_Crafts_Bloqueados.size() > 0) {
				pm.registerEvents(new BloquearCrafts(), this);	
			}
		}

		if (Settings.Bloquear_Criar_Portal) {
			pm.registerEvents(new BloquearCriarPortal(), this);
		}

		if (Settings.Bloquear_Derreter_Gelo_E_Neve) {
			pm.registerEvents(new BloquearDerreterGeloENeve(), this);
		}
		
		if (Settings.Bloquear_Explodir_Itens) {
			pm.registerEvents(new BloquearExplodirItens(), this);
		}
		
		if (Settings.Bloquear_Kick_Por_Duplo_Login_Super) {
			pm.registerEvents(new BloquearKickPorDuploLoginSuper(), this);
		} else if (Settings.Bloquear_Kick_Por_Duplo_Login) {
			if (jarType == JarType.BUKKIT) {
				pm.registerEvents(new BloquearKickPorDuploLoginSuper(), this);
			} else {
				pm.registerEvents(new BloquearKickPorDuploLogin(), this);
			}
		}

		if (Settings.Bloquear_NameTag) {
			if (!isOldVersion()) {
				pm.registerEvents(new BloquearNameTag(), this);
			}
		}

		if (Settings.Bloquear_Nicks_Improprios) {
			if (Settings.Nicks_Bloqueados != null && Settings.Nicks_Bloqueados.size() > 0) {
				pm.registerEvents(new BloquearNicksImproprios(), this);				
			}
		}

		if (Settings.Bloquear_Mobs_De_Pegarem_Fogo_Para_O_Sol) {
			pm.registerEvents(new BloquearMobsDePegaremFogoParaOSol(), this);
		}
		
		if (Settings.Bloquear_Mobs_De_Pegarem_Itens_Do_Chao) {
			pm.registerEvents(new BloquearMobsDePegaremItensDoChao(), this);
		}
		
		if (Settings.Bloquear_Money_Invalido) {
			pm.registerEvents(new BloquearMoneyInvalido(), this);
		}

		if (Settings.Bloquear_Palavras_Em_Placas_Ativar) {
			pm.registerEvents(new BloquearPlacas(), this);
		}

		if (Settings.Bloquear_Passar_Da_Borda) {
			if (!isOldVersion()) {
				pm.registerEvents(new BloquearPassarDaBorda(), this);
			}
		}

		if (Settings.Bloquear_Quebrar_Plantacoes_Pulando) {
			pm.registerEvents(new BloquearQuebrarPlantacoesPulando(), this);
		}
		
		if (Settings.Bloquear_Shift_Em_Containers_Ativar) {
			if (Settings.Bloquear_Shift_Em_Containers_Containers != null && Settings.Bloquear_Shift_Em_Containers_Containers.size() > 0) {
				pm.registerEvents(new BloquearShiftEmContainers(), this);	
			}
		}

		if (Settings.Bloquear_Subir_Em_Veiculos) {
			pm.registerEvents(new BloquearSubirEmVeiculos(), this);
		}

		if (Settings.Bloquear_Subir_No_Teto_Nether) {
			pm.registerEvents(new BloquearSubirNoTetoNether(), this);
		}

		if (Settings.Bloquear_Teleport_Por_Portal_Ativar) {
			pm.registerEvents(new BloquearTeleportPorPortal(), this);
		}
		
		if (Settings.Comandos_Com_Cooldown) {
			if (Settings.Lista_Dos_Comandos_Com_Cooldown != null && Settings.Lista_Dos_Comandos_Com_Cooldown.size() > 0) {
				pm.registerEvents(new CooldownComandos(), this);	
			}
		}
		
		if (Settings.Comandos_Com_Delay) {
			if (Settings.Lista_Dos_Comandos_Com_Delay != null && Settings.Lista_Dos_Comandos_Com_Delay.size() > 0) {
				pm.registerEvents(new DelayComandos(), this);	
			}
		}
		
		if (Settings.Deletar_Comandos) {
			if (Settings.Lista_Dos_Comandos_Deletados != null && Settings.Lista_Dos_Comandos_Deletados.size() > 0) {
				DeletarComandos.deleteCommands();
			}
		}
		
		if (Settings.Desativar_Chuva) {
			pm.registerEvents(new DesativarChuva(), this);
		}

		if (Settings.Desativar_Ciclo_Do_Dia) {
			if (version == Version.v1_5) {
				DesativarCicloDoDia.stopDaylightCycleOLD();
			} else {
				DesativarCicloDoDia.stopDaylightCycle();
			}
		}

		if (Settings.Desativar_Dano_Do_Blaze) {
			if (!isVeryOldVersion()) {
				pm.registerEvents(new DesativarDanoDoBlaze(), this);
			}
		}
		
		if (Settings.Desativar_Dano_Do_Cacto) {
			pm.registerEvents(new DesativarDanoDoCacto(), this);
		}
		
		if (Settings.Desativar_Dano_Do_EnderDragon) {
			pm.registerEvents(new DesativarDanoDoEnderDragon(), this);
		}

		if (Settings.Desativar_Dano_Do_Ghast) {
			if (!isVeryOldVersion()) {
				pm.registerEvents(new DesativarDanoDoGhast(), this);
			}
		}
		
		if (Settings.Desativar_Dano_Do_Wither) {
			pm.registerEvents(new DesativarDanoDoWither(), this);
		}

		if (Settings.Desativar_Flow_Da_Agua_E_Lava) {
			pm.registerEvents(new DesativarFlowDaAguaELava(), this);
		}

		if (Settings.Desativar_Fome_Nos_Mundos) {
			pm.registerEvents(new DesativarFomeNosMundos(), this);
		}

		if (Settings.Desativar_Mensagem_De_Entrada) {
			pm.registerEvents(new DesativarMensagemDeEntrada(), this);
		}

		if (Settings.Desativar_Mensagem_De_Morte) {
			pm.registerEvents(new DesativarMensagemDeMorte(), this);
		}

		if (Settings.Desativar_Mensagem_De_Saida) {
			pm.registerEvents(new DesativarMensagemDeSaida(), this);
		}

		if (Settings.Desativar_Mobs_Naturais) {
			if (isOldVersion()) {
				pm.registerEvents(new DesativarMobsNaturaisOLD(), this);				
			} else {
				pm.registerEvents(new DesativarMobsNaturais(), this);
			}
		}

		if (Settings.Desativar_Propagacao_Do_Fogo) {
			pm.registerEvents(new DesativarPropagacaoDoFogo(), this);
		}

		if (Settings.Desativar_Queda_Da_Areia) {
			pm.registerEvents(new DesativarQuedaDaAreia(), this);
		}

		if (Settings.Desativar_Queda_Da_Bigorna) {
			pm.registerEvents(new DesativarQuedaDaBigorna(), this);
		}
		
		if (Settings.Desativar_Queda_Das_Folhas) {
			pm.registerEvents(new DesativarQuedaDasFolhas(), this);
		}

		if (Settings.Dropar_Cabeca_Ao_Morrer) {
			pm.registerEvents(new DroparCabecaAoMorrer(), this);
		}

		if (Settings.EnderPearl_Cooldown_Ativar) {
			if (!isVeryOldVersion()) {
				pm.registerEvents(new EnderPearlCooldown(), this);
			}
		}

		if (Settings.Entrar_No_Spawn_Ao_Logar) {
			pm.registerEvents(new EntrarNoSpawnAoLogar(), Main.this);
		}

		if (Settings.Executar_Comandos_No_Primeiro_Login) {
			if (Settings.Lista_Dos_Comandos_Executados_No_Primeiro_Login != null && Settings.Lista_Dos_Comandos_Executados_No_Primeiro_Login.size() > 0) {
				pm.registerEvents(new ComandosPrimeiroLogin(), Main.this);	
			}
		}
		
		if (Settings.Invencibilidade_Ao_Teleportar) {
			pm.registerEvents(new InvencibilidadeAoTeleportar(), this);
		}

		if (Settings.Limitador_De_Players) {
			if (!isOldVersion()) {
				pm.registerEvents(new LimiteDePlayers(), this);
			}
		}

		if (Settings.Mensagem_De_Boas_Vindas_Ativar) {
			pm.registerEvents(new MensagemDeBoasVindas(), this);
		}

		if (Settings.Motd_Ativar) {
			pm.registerEvents(new Motd(), this);
		}

		if (Settings.ScoreBoard_Ativar) {
			if (isOldVersion()) {
				pm.registerEvents(new ScoreBoardOLD(), this);
				ScoreBoardOLD.loadScoreBoard();
			} else {
				pm.registerEvents(new ScoreBoard(), this);
				ScoreBoard.loadScoreBoard();
			}
		}

		if (Settings.Sistema_De_Fly_Para_Players) {
			if (commands.getBoolean("comandos.fly.ativar-comando")) {
				pm.registerEvents(new FlyListener(), this);
			}
		}
		
		if (Settings.Sistema_De_Stack_Mobs) {
			pm.registerEvents(new StackMobs(), this);
		}

		if (Settings.Sistema_De_Spawners) {
			if (commands.getBoolean("comandos.sgive.ativar-comando")) {
				if (!isVeryNewVersion()) {
					
					if (Settings.Bloquear_Trocar_Tipo_Do_Spawner_Com_Ovo) {
						pm.registerEvents(new BloquearTrocarTipoDoSpawnerComOvo(), this);
					}
					
					if (isOldVersion()) {
						pm.registerEvents(new SistemaDeSpawnersOLD(), this);
						if (Settings.Dropar_Spawner_Ao_Explodir) {
							pm.registerEvents(new DroparSpawnerAoExplodirOLD(), this);
						}
					} else {
						pm.registerEvents(new SistemaDeSpawners(), this);
						if (Settings.Dropar_Spawner_Ao_Explodir) {
							pm.registerEvents(new DroparSpawnerAoExplodir(), this);
						}
					}
				}
			}
		}

		if (Settings.Title_De_Boas_Vindas_Ativar) {
			if (!isOldVersion()) {
				pm.registerEvents(new TitleDeBoasVindas(), this);
			}
		}

		if (Settings.Ativar_Tablist) {
			if (!isOldVersion()) {
				pm.registerEvents(new Tablist(), this);
			}
		}

		if (Settings.AtivarAddons_Legendchat) {
			if (pm.getPlugin("Legendchat") == null) {
				getServer().getConsoleSender().sendMessage("§c[System] Legendchat nao encontrado, desativando addons!");
			} else if (Settings.CorAutomatica != null) {
				pm.registerEvents(new LegendChat(), this);
			}
		}

		if (Settings.AtivarAddons_McMMO) {
			if (!isOldVersion()) {
				if (pm.getPlugin("mcMMO") == null) {
					getServer().getConsoleSender().sendMessage("§c[System] McMMO nao encontrado, desativando addons!");
				} else {
					pm.registerEvents(new Mcmmo(), this);
					if (pm.getPlugin("Legendchat") != null) {
						pm.registerEvents(new McTopTag(), this);
						McTopTag.checkMCTop();
					}
				}
			}
		}

		if (Settings.AtivarAddons_MassiveFactions) {
			if (pm.getPlugin("MassiveCore") == null || pm.getPlugin("Factions") == null) {
				getServer().getConsoleSender().sendMessage("§c[System] Factions nao encontrado, desativando addons!");
			} else {
				setupFactions = true;
			}
		}
		
		if (Settings.AtivarAddons_Vault) {
			if (pm.getPlugin("Vault") == null) {
				getServer().getConsoleSender().sendMessage("§c[System] Vault nao encontrado, desativando addons!");
			} else {
				if (Vault.setupEconomy()) {
					if (pm.getPlugin("Legendchat") != null) {
						pm.registerEvents(new MagnataTag(), this);
						MagnataTag.checkMagnata();
					}
				} else {
					getServer().getConsoleSender().sendMessage("§c[System] Nenhum plugin valido de economia encontrado!");
				}
			}
		}

		if (commands.getBoolean("comandos.vanish.ativar-comando")) {
			pm.registerEvents(new VanishListener(), this);
		}
		
		if (commands.getBoolean("comandos.echest.ativar-comando")) {
			pm.registerEvents(new EnderChestListener(), this);
		}
		
		if (commands.getBoolean("comandos.invsee.ativar-comando")) {
			pm.registerEvents(new InvseeListener(), this);
		}
		
		if (commands.getBoolean("comandos.kit.ativar-comando")) {
			pm.registerEvents(new KitsListener(), this);
		}
		
		if (commands.getBoolean("comandos.back.ativar-comando")) {
			pm.registerEvents(new BackListener(), this);
		}

		pm.registerEvents(new PlayerData(), this);
		pm.registerEvents(new ManterXpAoMorrer(), this);
		pm.registerEvents(new Outros(), this);
	}

	private void disablePlugin() {
		try {
			HandlerList.unregisterAll(this);
			Bukkit.getScheduler().cancelTasks(this);
	
			if (Settings.AtivarAddons_McMMO && McTopTag.TTask != null) {
				McTopTag.TTask.cancel();
			}
			
			if (Settings.AtivarAddons_Vault && MagnataTag.MTask != null) {
				MagnataTag.MTask.cancel();
			}
	
			if (Settings.Auto_Anuncio && AutoAnuncio.XTask != null) {
				AutoAnuncio.XTask.cancel();
			}
		} catch (Throwable e) {}
	}

	public static boolean isOldVersion() {
		if (version == Version.v1_7)
			return true;
		if (version == Version.v1_6)
			return true;
		if (version == Version.v1_5)
			return true;
		return false;
	}
	
	public static boolean isVeryOldVersion() {
		if (version == Version.v1_6)
			return true;
		if (version == Version.v1_5)
			return true;
		return false;
	}

	public static boolean isNewVersion() {
		if (version == Version.v1_17)
			return true;
		if (version == Version.v1_16_5)
			return true;
		if (version == Version.v1_16_4)
			return true;
		if (version == Version.v1_16_3)
			return true;
		if (version == Version.v1_16_2)
			return true;
		if (version == Version.v1_16)
			return true;
		if (version == Version.v1_15)
			return true;
		if (version == Version.v1_14)
			return true;
		if (version == Version.v1_13)
			return true;
		if (version == Version.v1_12)
			return true;
		if (version == Version.v1_11)
			return true;
		return false;
	}
	
	public static boolean isVeryNewVersion() {
		if (version == Version.v1_17)
			return true;
		if (version == Version.v1_16_5)
			return true;
		if (version == Version.v1_16_4)
			return true;
		if (version == Version.v1_16_3)
			return true;
		if (version == Version.v1_16_2)
			return true;
		if (version == Version.v1_16)
			return true;
		if (version == Version.v1_15)
			return true;
		if (version == Version.v1_14)
			return true;
		if (version == Version.v1_13)
			return true;
		return false;
	}
	
	public static boolean isVeryFuckingNewVersion() {
		if (version == Version.v1_17)
			return true;
		if (version == Version.v1_16_5)
			return true;
		if (version == Version.v1_16_4)
			return true;
		if (version == Version.v1_16_3)
			return true;
		if (version == Version.v1_16_2)
			return true;
		if (version == Version.v1_16)
			return true;
		if (version == Version.v1_15)
			return true;
		if (version == Version.v1_14)
			return true;
		return false;
	}
	
	public static boolean isMotherFuckerVersion() {
		if (version == Version.v1_17)
			return true;
		if (version == Version.v1_16_5)
			return true;
		if (version == Version.v1_16_4)
			return true;
		if (version == Version.v1_16_3)
			return true;
		if (version == Version.v1_16_2)
			return true;
		return false;
	}

	public static JarType getTypeJar() {
		return jarType;
	}

	public static Version getVersion() {
		return version;
	}

	public static Main get() {
		return main;
	}

}