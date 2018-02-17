package rush;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import rush.addons.LegendChat;
import rush.addons.MassiveFactions;
import rush.addons.McMMO;
import rush.comandos.ComandoAlerta;
import rush.comandos.ComandoClearChat;
import rush.comandos.ComandoCores;
import rush.comandos.ComandoDivulgar;
import rush.comandos.ComandoLixo;
import rush.comandos.ComandoLuz;
import rush.comandos.ComandoMundoVip;
import rush.comandos.ComandoOnline;
import rush.comandos.ComandoPing;
import rush.comandos.ComandoSGive;
import rush.comandos.ComandoSetmundovip;
import rush.comandos.ComandoSetspawn;
import rush.comandos.ComandoSlime;
import rush.comandos.ComandoSpawn;
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
import rush.sistemas.gerais.AnunciarMorte;
import rush.sistemas.gerais.AutoAnuncio;
import rush.sistemas.gerais.Motd;
import rush.sistemas.gerais.ScoreBoard;
import rush.sistemas.spawners.BloquearTrocarTipoDoSpawnerComOvo;
import rush.sistemas.spawners.DroparSpawnerAoExplodir;
import rush.sistemas.spawners.SistemaDeSpawners;
import rush.utils.Locations;

@SuppressWarnings("all")
public class Main extends JavaPlugin implements Listener {

   public static Main aqui;
   public List<String> mensagens = getConfig().getStringList("Lista-De-Anuncios");
   public static File customYml;
   public static FileConfiguration customConfig;
   
   public void onEnable() {
	    saveDefaultConfig();
        if (!new File(getDataFolder(), "mensagens.yml").exists()) {
        saveResource("mensagens.yml", false);}
        customYml = new File(this.getDataFolder() + "/mensagens.yml");
        customConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(customYml);
	    aqui = this;
	    Locations.loadLocations();
	    this.registrarEventos();
	    this.registrarComandos();
	   }

   
   public void onDisable() {
	    PluginManager pm = Bukkit.getServer().getPluginManager();
	   
	   if (pm.getPlugin("mcMMO") != null) {
	   McMMO.TTask.cancel();}
	   HandlerList.unregisterAll((Plugin) this);
   }
   
   public FileConfiguration getMensagens() {
       return customConfig;
   }
   
   public void registrarComandos() {
	    getCommand("alerta").setExecutor(new ComandoAlerta()); 
	    getCommand("clearchat").setExecutor(new ComandoClearChat()); 
	    getCommand("cores").setExecutor(new ComandoCores());
	    getCommand("divulgar").setExecutor(new ComandoDivulgar()); 
	    getCommand("lixo").setExecutor(new ComandoLixo());
	    getCommand("luz").setExecutor(new ComandoLuz());
	    getCommand("mundovip").setExecutor(new ComandoMundoVip()); 
	    getCommand("online").setExecutor(new ComandoOnline()); 
	    getCommand("ping").setExecutor(new ComandoPing());
	    getCommand("setmundovip").setExecutor(new ComandoSetmundovip());
	    getCommand("setspawn").setExecutor(new ComandoSetspawn());
	    getCommand("sgive").setExecutor(new ComandoSGive()); 
	    getCommand("slime").setExecutor(new ComandoSlime());
	    getCommand("spawn").setExecutor(new ComandoSpawn()); 

   }
	
    public void registrarEventos() {
	    PluginManager pm = Bukkit.getServer().getPluginManager();
	    
	    if (getConfig().getBoolean("Anunciar-Morte")){
	    pm.registerEvents(new AnunciarMorte(), this);}
	    
	    if (getConfig().getBoolean("Ativar-Cores-Na-Bigorna")){
	    pm.registerEvents(new CoresNaBigorna(), this);}
	    
	    if (getConfig().getBoolean("Ativar-Cores-Na-Placa")){
	    pm.registerEvents(new CoresNaPlaca(), this);}
	    
	    if (getConfig().getBoolean("Auto-Anuncio")){
	    BukkitTask AutoAnuncio = new AutoAnuncio(mensagens).runTaskTimer(this, 20 * getConfig().getInt("Delay-Entre-Anuncios") * 60, 20 * getConfig().getInt("Delay-Entre-Anuncios") * 60);}
	    
	    if (getConfig().getBoolean("Bigorna-Infinita")){
	    pm.registerEvents(new BigornaInfinita(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Abrir-Containers.Ativar")){
	    pm.registerEvents(new BloquearAbrirContainers(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Cair-No-Void")){
	    pm.registerEvents(new BloquearCairNoVoid(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Cama")){
	    pm.registerEvents(new BloquearCama(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Comandos")){
	    pm.registerEvents(new BloquearComandos(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Congelar-Agua")){
	    pm.registerEvents(new BloquearCongelarAgua(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Crafts")){
	    pm.registerEvents(new BloquearCrafts(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Criar-Portal")){
	    pm.registerEvents(new BloquearCriarPortal(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Derreter-Gelo-E-Neve")){
	    pm.registerEvents(new BloquearDerreterGeloENeve(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-NameTag")){
	    pm.registerEvents(new BloquearNameTag(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Nicks-Improprios")){
	    pm.registerEvents(new BloquearNicksImproprios(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Mobs-De-Pegarem-Fogo-Para-O-Sol")){
	    pm.registerEvents(new BloquearMobsDePegaremFogoParaOSol(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Passar-Da-Borda")){
	    pm.registerEvents(new BloquearPassarDaBorda(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Placas")){
	    pm.registerEvents(new BloquearPlacas(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Shift-Em-Containers.Ativar")){
	    pm.registerEvents(new BloquearShiftEmContainers(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Subir-Em-Veiculos")){
	    pm.registerEvents(new BloquearSubirEmVeiculos(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Subir-No-Teto-Nether")){
	    pm.registerEvents(new BloquearSubirNoTetoNether(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Teleport-Por-Portal.Ativar")){
	    pm.registerEvents(new BloquearTeleportPorPortal(), this);}
	    
	    if (getConfig().getBoolean("Bloquear-Trocar-Tipo-Do-Spawner-Com-Ovo")){
	    pm.registerEvents(new BloquearTrocarTipoDoSpawnerComOvo(), this);}
	    	    
	    if (getConfig().getBoolean("Bloquear-Xp-Ao-Quebrar-Mob-Spawners")){
	    pm.registerEvents(new BloquearXpAoQuebrarMobSpawners(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Chuva")){
	    pm.registerEvents(new DesativarChuva(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Ciclo-Do-Dia")){
	    pm.registerEvents(new DesativarCicloDoDia(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Dano-Do-EnderDragon")){
	    pm.registerEvents(new DesativarDanoDoEnderDragon(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Dano-Do-Whither")){
	    pm.registerEvents(new DesativarDanoDoWhiter(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Flow-Da-Agua-E-Lava")){
	    pm.registerEvents(new DesativarFlowDaAguaELava(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Fome-Nos-Mundos")){
	    pm.registerEvents(new DesativarFomeNosMundos(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Mensagem-De-Entrada")){
	    pm.registerEvents(new DesativarMensagemDeEntrada(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Mensagem-De-Morte")){
	    pm.registerEvents(new DesativarMensagemDeMorte(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Mensagem-De-Saida")){
	    pm.registerEvents(new DesativarMensagemDeSaida(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Mobs-Naturais")){
	    pm.registerEvents(new DesativarMobsNaturais(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Propagacao-Do-Fogo")){
	    pm.registerEvents(new DesativarPropagacaoDoFogo(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Queda-Da-Areia")){
	    pm.registerEvents(new DesativarQuedaDaAreia(), this);}
	    
	    if (getConfig().getBoolean("Desativar-Queda-Das-Folhas")){
	    pm.registerEvents(new DesativarQuedaDasFolhas(), this);}
	    
	    if (getConfig().getBoolean("Dropar-Spawner-Ao-Explodir")){
	    pm.registerEvents(new DroparSpawnerAoExplodir(), this);}
	    
	    if (getConfig().getBoolean("EnderPearl-Cooldown.Ativar")){
	    pm.registerEvents(new EnderPearlCooldown(), this);}
	    
	    if (getConfig().getBoolean("Entrar-No-Spawn-Ao-Logar")){
	    pm.registerEvents(new EntrarNoSpawnAoLogar(), this);}
	    
	    if (getConfig().getBoolean("Motd.Ativar")){
	    pm.registerEvents(new Motd(), this);}
	    
	    if (getConfig().getBoolean("ScoreBoard.Ativar")){
	    pm.registerEvents(new ScoreBoard(), this);}
	    
	    if (getConfig().getBoolean("Sistema-De-Spawners")){
	    pm.registerEvents(new SistemaDeSpawners(), this);}
	     
	    if (getConfig().getBoolean("AtivarAddons.Legendchat")){
	    if (pm.getPlugin("Legendchat") == null) {
	    getServer().getConsoleSender().sendMessage("§c[System] Legendchat nao encontrado, desativando addons!");
	    } else { 
		pm.registerEvents(new LegendChat(), this);
	    getServer().getConsoleSender().sendMessage("§a[System] Legendchat encontrado, ativando addons!");}}
	    
	    if (getConfig().getBoolean("AtivarAddons.MassiveFactions")){
	    if (pm.getPlugin("Factions") == null) {
	    getServer().getConsoleSender().sendMessage("§c[System] Factions nao encontrado, desativando addons!");
	    } else { 
		pm.registerEvents(new MassiveFactions(), this);
	    getServer().getConsoleSender().sendMessage("§a[System] Factions encontrado, ativando addons!");}}

	    if (getConfig().getBoolean("AtivarAddons.mcMMO")){
	    if (pm.getPlugin("mcMMO") == null) {
	    getServer().getConsoleSender().sendMessage("§c[System] mcMMO nao encontrado, desativando addons!");
	    } else { 
		pm.registerEvents(new McMMO(), this);
		McMMO.checkMCTop();
	    getServer().getConsoleSender().sendMessage("§a[System] mcMMO encontrado, ativando addons!");}}
	    
	    pm.registerEvents(new Outros(), this);    
   }
}