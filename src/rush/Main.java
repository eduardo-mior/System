package rush;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import rush.addons.LegendChat;
import rush.addons.McMMO;
import rush.comandos.ComandoAlerta;
import rush.comandos.ComandoClearChat;
import rush.comandos.ComandoCores;
import rush.comandos.ComandoDelhome;
import rush.comandos.ComandoDelwarp;
import rush.comandos.ComandoDivulgar;
import rush.comandos.ComandoEchest;
import rush.comandos.ComandoExecutarSom;
import rush.comandos.ComandoHome;
import rush.comandos.ComandoHomes;
import rush.comandos.ComandoLixo;
import rush.comandos.ComandoLuz;
import rush.comandos.ComandoMundoVip;
import rush.comandos.ComandoOnline;
import rush.comandos.ComandoParticular;
import rush.comandos.ComandoPing;
import rush.comandos.ComandoPublica;
import rush.comandos.ComandoSGive;
import rush.comandos.ComandoSethome;
import rush.comandos.ComandoSetmundovip;
import rush.comandos.ComandoSetspawn;
import rush.comandos.ComandoSetwarp;
import rush.comandos.ComandoSlime;
import rush.comandos.ComandoSpawn;
import rush.comandos.ComandoTitle;
import rush.comandos.ComandoWarp;
import rush.comandos.ComandoWarps;
import rush.recursos.adicionais.BigornaInfinita;
import rush.recursos.adicionais.BloquearComandos;
import rush.recursos.adicionais.BloquearCrafts;
import rush.recursos.adicionais.BloquearNicksImproprios;
import rush.recursos.adicionais.BloquearPlacas;
import rush.recursos.adicionais.CoresNaBigorna;
import rush.recursos.adicionais.CoresNaPlaca;
import rush.recursos.antibug.BloquearAbrirContainers;
import rush.recursos.antibug.BloquearCama;
import rush.recursos.antibug.BloquearNameTag;
import rush.recursos.antibug.BloquearPassarDaBorda;
import rush.recursos.antibug.BloquearShiftEmContainers;
import rush.recursos.antibug.BloquearSubirEmVeiculos;
import rush.recursos.antibug.BloquearSubirNoTetoNether;
import rush.recursos.antibug.BloquearXpAoQuebrarMobSpawners;
import rush.recursos.antibug.EnderPearlCooldown;
import rush.recursos.antibug.Outros;
import rush.recursos.antilag.BloquearCongelarAgua;
import rush.recursos.antilag.BloquearDerreterGeloENeve;
import rush.recursos.antilag.DesativarChuva;
import rush.recursos.antilag.DesativarFlowDaAguaELava;
import rush.recursos.antilag.DesativarMobsNaturais;
import rush.recursos.antilag.DesativarPropagacaoDoFogo;
import rush.recursos.antilag.DesativarQuedaDaAreia;
import rush.recursos.antilag.DesativarQuedaDasFolhas;
import rush.recursos.gerais.BloquearCairNoVoid;
import rush.recursos.gerais.BloquearCriarPortal;
import rush.recursos.gerais.BloquearMobsDePegaremFogoParaOSol;
import rush.recursos.gerais.BloquearTeleportPorPortal;
import rush.recursos.gerais.DesativarCicloDoDia;
import rush.recursos.gerais.DesativarDanoDoEnderDragon;
import rush.recursos.gerais.DesativarDanoDoWhiter;
import rush.recursos.gerais.DesativarFomeNosMundos;
import rush.recursos.gerais.DesativarMensagemDeEntrada;
import rush.recursos.gerais.DesativarMensagemDeMorte;
import rush.recursos.gerais.DesativarMensagemDeSaida;
import rush.recursos.gerais.EntrarNoSpawnAoLogar;
import rush.recursos.gerais.LimiteDePlayers;
import rush.recursos.gerais.MensagemDeBoasVindas;
import rush.recursos.gerais.TitleDeBoasVindas;
import rush.sistemas.gerais.AnunciarMorte;
import rush.sistemas.gerais.AutoAnuncio;
import rush.sistemas.gerais.EnderChest;
import rush.sistemas.gerais.Motd;
import rush.sistemas.gerais.ScoreBoard;
import rush.sistemas.gerais.Tablist;
import rush.sistemas.spawners.BloquearTrocarTipoDoSpawnerComOvo;
import rush.sistemas.spawners.DroparSpawnerAoExplodir;
import rush.sistemas.spawners.SistemaDeSpawners;
import rush.utils.ConfigManager;
import rush.utils.DataManager;
import rush.utils.Locations;
import rush.utils.Playerdata;

public class Main extends JavaPlugin implements Listener {

   public static Main aqui;

   public void onEnable() {
	    instanceMain();
	    gerarConfigs();
	    Locations.loadLocations();
	    registrarEventos();
	    registrarComandos();
   }
   
   public void onDisable() {
	   PluginManager pm = Bukkit.getServer().getPluginManager();
	   
	   if (pm.getPlugin("mcMMO") != null) {
	   McMMO.TTask.cancel(); }
	   
	   if (ConfigManager.getConfig("settings").getBoolean("Auto-Anuncio")) {
	   AutoAnuncio.XTask.cancel(); }
	   
	   HandlerList.unregisterAll();
   }
   
   public void instanceMain() {
	   aqui = this;
   }
   
   public void gerarConfigs() {
	   DataManager.createFolder("warps");
	   DataManager.createFolder("playerdata");
	   ConfigManager.createNewConfig("mensagens");
	   ConfigManager.createNewConfig("settings");
	   ConfigManager.createNewConfig("permissions");
	   ConfigManager.createNewConfig("locations");
   }
   
   public void registrarComandos() {
	    getCommand("alerta").setExecutor(new ComandoAlerta()); 
	    getCommand("clearchat").setExecutor(new ComandoClearChat()); 
	    getCommand("cores").setExecutor(new ComandoCores());
	    getCommand("delhome").setExecutor(new ComandoDelhome()); 
	    getCommand("delwarp").setExecutor(new ComandoDelwarp()); 
	    getCommand("divulgar").setExecutor(new ComandoDivulgar()); 
	    getCommand("echest").setExecutor(new ComandoEchest());
	    getCommand("executarsom").setExecutor(new ComandoExecutarSom());
	    getCommand("home").setExecutor(new ComandoHome());
	    getCommand("homes").setExecutor(new ComandoHomes()); 
	    getCommand("lixo").setExecutor(new ComandoLixo());
	    getCommand("luz").setExecutor(new ComandoLuz());
	    getCommand("mundovip").setExecutor(new ComandoMundoVip()); 
	    getCommand("online").setExecutor(new ComandoOnline()); 
	    getCommand("particular").setExecutor(new ComandoParticular());
	    getCommand("ping").setExecutor(new ComandoPing());
	    getCommand("publica").setExecutor(new ComandoPublica());
	    getCommand("sethome").setExecutor(new ComandoSethome()); 
	    getCommand("setmundovip").setExecutor(new ComandoSetmundovip()); 
	    getCommand("setspawn").setExecutor(new ComandoSetspawn()); 
	    getCommand("setwarp").setExecutor(new ComandoSetwarp()); 
	    getCommand("sgive").setExecutor(new ComandoSGive()); 
	    getCommand("slime").setExecutor(new ComandoSlime());
	    getCommand("spawn").setExecutor(new ComandoSpawn()); 
	    getCommand("title").setExecutor(new ComandoTitle()); 
	    getCommand("warp").setExecutor(new ComandoWarp()); 
	    getCommand("warps").setExecutor(new ComandoWarps());
   }
	
   public void registrarEventos() {
	    PluginManager pm = Bukkit.getServer().getPluginManager();
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Anunciar-Morte")){
	    pm.registerEvents(new AnunciarMorte(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Ativar-Cores-Na-Bigorna")){
	    pm.registerEvents(new CoresNaBigorna(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Ativar-Cores-Na-Placa")){
	    pm.registerEvents(new CoresNaPlaca(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Auto-Anuncio")){
	    AutoAnuncio.runMensagens();	}
	    	
	    if (ConfigManager.getConfig("settings").getBoolean("Bigorna-Infinita")){
	    pm.registerEvents(new BigornaInfinita(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Abrir-Containers.Ativar")){
	    pm.registerEvents(new BloquearAbrirContainers(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Cair-No-Void")){
	    pm.registerEvents(new BloquearCairNoVoid(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Cama")){
	    pm.registerEvents(new BloquearCama(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Comandos")){
	    pm.registerEvents(new BloquearComandos(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Congelar-Agua")){
	    pm.registerEvents(new BloquearCongelarAgua(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Crafts")){
	    pm.registerEvents(new BloquearCrafts(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Criar-Portal")){
	    pm.registerEvents(new BloquearCriarPortal(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Derreter-Gelo-E-Neve")){
	    pm.registerEvents(new BloquearDerreterGeloENeve(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-NameTag")){
	    pm.registerEvents(new BloquearNameTag(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Nicks-Improprios")){
	    pm.registerEvents(new BloquearNicksImproprios(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Mobs-De-Pegarem-Fogo-Para-O-Sol")){
	    pm.registerEvents(new BloquearMobsDePegaremFogoParaOSol(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Passar-Da-Borda")){
	    pm.registerEvents(new BloquearPassarDaBorda(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Placas")){
	    pm.registerEvents(new BloquearPlacas(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Shift-Em-Containers.Ativar")){
	    pm.registerEvents(new BloquearShiftEmContainers(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Subir-Em-Veiculos")){
	    pm.registerEvents(new BloquearSubirEmVeiculos(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Subir-No-Teto-Nether")){
	    pm.registerEvents(new BloquearSubirNoTetoNether(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Teleport-Por-Portal.Ativar")){
	    pm.registerEvents(new BloquearTeleportPorPortal(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Trocar-Tipo-Do-Spawner-Com-Ovo")){
	    pm.registerEvents(new BloquearTrocarTipoDoSpawnerComOvo(), this);}
	    	    
	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Xp-Ao-Quebrar-Mob-Spawners")){
	    pm.registerEvents(new BloquearXpAoQuebrarMobSpawners(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Chuva")){
	    pm.registerEvents(new DesativarChuva(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Ciclo-Do-Dia")){
	    pm.registerEvents(new DesativarCicloDoDia(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Dano-Do-EnderDragon")){
	    pm.registerEvents(new DesativarDanoDoEnderDragon(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Dano-Do-Whither")){
	    pm.registerEvents(new DesativarDanoDoWhiter(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Flow-Da-Agua-E-Lava")){
	    pm.registerEvents(new DesativarFlowDaAguaELava(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Fome-Nos-Mundos")){
	    pm.registerEvents(new DesativarFomeNosMundos(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Mensagem-De-Entrada")){
	    pm.registerEvents(new DesativarMensagemDeEntrada(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Mensagem-De-Morte")){
	    pm.registerEvents(new DesativarMensagemDeMorte(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Mensagem-De-Saida")){
	    pm.registerEvents(new DesativarMensagemDeSaida(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Mobs-Naturais")){
	    pm.registerEvents(new DesativarMobsNaturais(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Propagacao-Do-Fogo")){
	    pm.registerEvents(new DesativarPropagacaoDoFogo(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Queda-Da-Areia")){
	    pm.registerEvents(new DesativarQuedaDaAreia(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Desativar-Queda-Das-Folhas")){
	    pm.registerEvents(new DesativarQuedaDasFolhas(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Dropar-Spawner-Ao-Explodir")){
	    pm.registerEvents(new DroparSpawnerAoExplodir(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("EnderPearl-Cooldown.Ativar")){
	    pm.registerEvents(new EnderPearlCooldown(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Entrar-No-Spawn-Ao-Logar")){
	    pm.registerEvents(new EntrarNoSpawnAoLogar(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Limitador-De-Players")){
	    pm.registerEvents(new LimiteDePlayers(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Mensagem-De-Boas-Vindas.Ativar")){
		pm.registerEvents(new MensagemDeBoasVindas(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Motd.Ativar")){
	    pm.registerEvents(new Motd(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("ScoreBoard.Ativar")){
	    pm.registerEvents(new ScoreBoard(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Sistema-De-Spawners")){
	    pm.registerEvents(new SistemaDeSpawners(), this);}
	    
	    if (ConfigManager.getConfig("settings").getBoolean("Title-De-Boas-Vindas.Ativar")){
		pm.registerEvents(new TitleDeBoasVindas(), this);}

		if (ConfigManager.getConfig("settings").getBoolean("Ativar-Tablist")){
	    pm.registerEvents(new Tablist(), this);}
	     
	    if (ConfigManager.getConfig("settings").getBoolean("AtivarAddons.Legendchat")){
	    if (pm.getPlugin("Legendchat") == null) {
	    getServer().getConsoleSender().sendMessage("§c[System] Legendchat nao encontrado, desativando addons!");
	    } else { 
		pm.registerEvents(new LegendChat(), this);
	    getServer().getConsoleSender().sendMessage("§a[System] Legendchat encontrado, ativando addons!");}}

	    if (ConfigManager.getConfig("settings").getBoolean("AtivarAddons.mcMMO")){
	    if (pm.getPlugin("mcMMO") == null) {
	    getServer().getConsoleSender().sendMessage("§c[System] mcMMO nao encontrado, desativando addons!");
	    } else { 
		pm.registerEvents(new McMMO(), this);
		McMMO.checkMCTop();
	    getServer().getConsoleSender().sendMessage("§a[System] mcMMO encontrado, ativando addons!");}}
	    
	    pm.registerEvents(new Playerdata(), this);
	    pm.registerEvents(new EnderChest(), this);
	    pm.registerEvents(new Outros(), this);
   }
}