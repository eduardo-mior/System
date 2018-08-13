package rush;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import rush.addons.LegendChat;
import rush.addons.McMMO;
import rush.comandos.ComandoAlerta;
import rush.comandos.ComandoBack;
import rush.comandos.ComandoChapeu;
import rush.comandos.ComandoClear;
import rush.comandos.ComandoClearChat;
import rush.comandos.ComandoCompactar;
import rush.comandos.ComandoCores;
import rush.comandos.ComandoCraft;
import rush.comandos.ComandoCrashar;
import rush.comandos.ComandoCriarkit;
import rush.comandos.ComandoDelhome;
import rush.comandos.ComandoDelkit;
import rush.comandos.ComandoDelwarp;
import rush.comandos.ComandoDerreter;
import rush.comandos.ComandoDivulgar;
import rush.comandos.ComandoEchest;
import rush.comandos.ComandoEditaritem;
import rush.comandos.ComandoEditarkit;
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
import rush.comandos.ComandoPublica;
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
import rush.comandos.ComandoTitle;
import rush.comandos.ComandoTp;
import rush.comandos.ComandoTpa;
import rush.comandos.ComandoTpaccept;
import rush.comandos.ComandoTpall;
import rush.comandos.ComandoTpcancel;
import rush.comandos.ComandoTpdeny;
import rush.comandos.ComandoTphere;
import rush.comandos.ComandoTptoggle;
import rush.comandos.ComandoWarp;
import rush.comandos.ComandoWarps;
import rush.configuracoes.Locations;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;
import rush.entidades.Kits;
import rush.recursos.bloqueadores.BloquearAbrirContainers;
import rush.recursos.bloqueadores.BloquearCairNoVoid;
import rush.recursos.bloqueadores.BloquearCama;
import rush.recursos.bloqueadores.BloquearComandos;
import rush.recursos.bloqueadores.BloquearCongelarAgua;
import rush.recursos.bloqueadores.BloquearCrafts;
import rush.recursos.bloqueadores.BloquearCriarPortal;
import rush.recursos.bloqueadores.BloquearDerreterGeloENeve;
import rush.recursos.bloqueadores.BloquearMobsDePegaremFogoParaOSol;
import rush.recursos.bloqueadores.BloquearNameTag;
import rush.recursos.bloqueadores.BloquearNicksImproprios;
import rush.recursos.bloqueadores.BloquearPassarDaBorda;
import rush.recursos.bloqueadores.BloquearPlacas;
import rush.recursos.bloqueadores.BloquearShiftEmContainers;
import rush.recursos.bloqueadores.BloquearSubirEmVeiculos;
import rush.recursos.bloqueadores.BloquearSubirNoTetoNether;
import rush.recursos.bloqueadores.BloquearTeleportPorPortal;
import rush.recursos.desativadores.DesativarChuva;
import rush.recursos.desativadores.DesativarCicloDoDia;
import rush.recursos.desativadores.DesativarDanoDoEnderDragon;
import rush.recursos.desativadores.DesativarDanoDoWhiter;
import rush.recursos.desativadores.DesativarFlowDaAguaELava;
import rush.recursos.desativadores.DesativarFomeNosMundos;
import rush.recursos.desativadores.DesativarMensagemDeEntrada;
import rush.recursos.desativadores.DesativarMensagemDeMorte;
import rush.recursos.desativadores.DesativarMensagemDeSaida;
import rush.recursos.desativadores.DesativarMobsNaturais;
import rush.recursos.desativadores.DesativarPropagacaoDoFogo;
import rush.recursos.desativadores.DesativarQuedaDaAreia;
import rush.recursos.desativadores.DesativarQuedaDasFolhas;
import rush.recursos.gerais.BigornaInfinita;
import rush.recursos.gerais.BloquearMoneyInvalido;
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
import rush.sistemas.gerais.AnunciarMorte;
import rush.sistemas.gerais.AutoAnuncio;
import rush.sistemas.gerais.DroparCabecaAoMorrer;
import rush.sistemas.gerais.Motd;
import rush.sistemas.gerais.PlayerData;
import rush.sistemas.gerais.ScoreBoard;
import rush.sistemas.gerais.Tablist;
import rush.sistemas.spawners.BloquearTrocarTipoDoSpawnerComOvo;
import rush.sistemas.spawners.DroparSpawnerAoExplodir;
import rush.sistemas.spawners.SistemaDeSpawners;
import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class Main extends JavaPlugin implements Listener {

   private static Main main;

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
	   desativarRecursos();
   }
   
   private void enablePlugin() {
	   main = this;
   }
   
   private void gerarConfigs() {
	   DataManager.createFolder("kits");
	   DataManager.createFolder("warps");
	   DataManager.createFolder("playerdata");
	   ConfigManager.createConfig("settings");
	   ConfigManager.createConfig("mensagens");
	   ConfigManager.createConfig("permissions");
	   ConfigManager.createConfig("locations");
   }
   
   private void carregarConfigs() {
	   Kits.loadKits();
	   Settings.loadSettings();
	   Locations.loadLocations();
	   Mensagens.loadMensagens();
   }
   
   private void registrarComandos() {
	   getCommand("alerta").setExecutor(new ComandoAlerta());
	   getCommand("back").setExecutor(new ComandoBack());
	   getCommand("chapeu").setExecutor(new ComandoChapeu()); 
	   getCommand("clear").setExecutor(new ComandoClear()); 
	   getCommand("clearchat").setExecutor(new ComandoClearChat()); 
	   getCommand("compactar").setExecutor(new ComandoCompactar());
	   getCommand("cores").setExecutor(new ComandoCores());
	   getCommand("craft").setExecutor(new ComandoCraft());
	   getCommand("crashar").setExecutor(new ComandoCrashar());
	   getCommand("criarkit").setExecutor(new ComandoCriarkit());
	   getCommand("delhome").setExecutor(new ComandoDelhome());
	   getCommand("delkit").setExecutor(new ComandoDelkit()); 
	   getCommand("delwarp").setExecutor(new ComandoDelwarp()); 
	   getCommand("derreter").setExecutor(new ComandoDerreter());
	   getCommand("divulgar").setExecutor(new ComandoDivulgar()); 
	   getCommand("echest").setExecutor(new ComandoEchest());
	   getCommand("editaritem").setExecutor(new ComandoEditaritem());
	   getCommand("editarkit").setExecutor(new ComandoEditarkit());
	   getCommand("executarsom").setExecutor(new ComandoExecutarSom());
	   getCommand("feed").setExecutor(new ComandoFeed());
	   getCommand("fly").setExecutor(new ComandoFly());
	   getCommand("gamemode").setExecutor(new ComandoGamemode());
	   getCommand("god").setExecutor(new ComandoGod());
	   getCommand("heal").setExecutor(new ComandoHeal());
	   getCommand("home").setExecutor(new ComandoHome());
	   getCommand("homes").setExecutor(new ComandoHomes());
	   getCommand("invsee").setExecutor(new ComandoInvsee());
	   getCommand("kit").setExecutor(new ComandoKit());
	   getCommand("kits").setExecutor(new ComandoKits());
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
	   getCommand("skull").setExecutor(new ComandoSkull()); 
	   getCommand("slime").setExecutor(new ComandoSlime());
	   getCommand("spawn").setExecutor(new ComandoSpawn());
	   getCommand("speed").setExecutor(new ComandoSpeed()); 
	   getCommand("sudo").setExecutor(new ComandoSudo()); 
	   getCommand("title").setExecutor(new ComandoTitle()); 
	   getCommand("tp").setExecutor(new ComandoTp());
	   getCommand("tpa").setExecutor(new ComandoTpa());
	   getCommand("tpaccept").setExecutor(new ComandoTpaccept());
	   getCommand("tpall").setExecutor(new ComandoTpall());
	   getCommand("tpcancel").setExecutor(new ComandoTpcancel());
	   getCommand("tpdeny").setExecutor(new ComandoTpdeny());
	   getCommand("tphere").setExecutor(new ComandoTphere()); 
	   getCommand("tptoggle").setExecutor(new ComandoTptoggle()); 
	   getCommand("warp").setExecutor(new ComandoWarp()); 
	   getCommand("warps").setExecutor(new ComandoWarps());
   }
	
   private void registrarEventos() {
	   PluginManager pm = Bukkit.getServer().getPluginManager();
	   
	   if (Settings.Anunciar_Morte){
	   pm.registerEvents(new AnunciarMorte(), this);}
	   
	   if (Settings.Ativar_Cores_Na_Bigorna){
	   pm.registerEvents(new CoresNaBigorna(), this);}
	   
	   if (Settings.Ativar_Cores_Na_Placa){
	   pm.registerEvents(new CoresNaPlaca(), this);}
	   
	   if (Settings.Auto_Anuncio){
	   AutoAnuncio.runMensagens();}
	   	
	   if (Settings.Bigorna_Infinita){
	   pm.registerEvents(new BigornaInfinita(), this);}
	   
	   if (Settings.Bloquear_Abrir_Containers_Ativar){
	   pm.registerEvents(new BloquearAbrirContainers(), this);}
	   
	   if (Settings.Bloquear_Cair_No_Void){
	   pm.registerEvents(new BloquearCairNoVoid(), this);}
	   
	   if (Settings.Bloquear_Cama){
	   pm.registerEvents(new BloquearCama(), this);}
	   
	   if (Settings.Bloquear_Comandos){
	   pm.registerEvents(new BloquearComandos(), this);}
	   
	   if (Settings.Bloquear_Congelar_Agua){
	   pm.registerEvents(new BloquearCongelarAgua(), this);}
	   
	   if (Settings.Bloquear_Crafts){
	   pm.registerEvents(new BloquearCrafts(), this);}
	   
	   if (Settings.Bloquear_Criar_Portal){
	   pm.registerEvents(new BloquearCriarPortal(), this);}
	   
	   if (Settings.Bloquear_Derreter_Gelo_E_Neve){
	   pm.registerEvents(new BloquearDerreterGeloENeve(), this);}
	   
	   if (Settings.Bloquear_NameTag){
	   pm.registerEvents(new BloquearNameTag(), this);}
	   
	   if (Settings.Bloquear_Nicks_Improprios){
	   pm.registerEvents(new BloquearNicksImproprios(), this);}
	   
	   if (Settings.Bloquear_Mobs_De_Pegarem_Fogo_Para_O_Sol){
	   pm.registerEvents(new BloquearMobsDePegaremFogoParaOSol(), this);}
	   
	   if (Settings.Bloquear_Money_Invalido){
	   pm.registerEvents(new BloquearMoneyInvalido(), this);}
	   
	   if (Settings.Bloquear_Passar_Da_Borda){
	   pm.registerEvents(new BloquearPassarDaBorda(), this);}
	   
	   if (Settings.Bloquear_Palavras_Em_Placas_Ativar){
	   pm.registerEvents(new BloquearPlacas(), this);}
	   
	   if (Settings.Bloquear_Shift_Em_Containers_Ativar){
	   pm.registerEvents(new BloquearShiftEmContainers(), this);}
	   
	   if (Settings.Bloquear_Subir_Em_Veiculos){
	   pm.registerEvents(new BloquearSubirEmVeiculos(), this);}
	   
	   if (Settings.Bloquear_Subir_No_Teto_Nether){
	   pm.registerEvents(new BloquearSubirNoTetoNether(), this);}
	   
	   if (Settings.Bloquear_Teleport_Por_Portal_Ativar){
	   pm.registerEvents(new BloquearTeleportPorPortal(), this);}
	   
	   if (Settings.Bloquear_Trocar_Tipo_Do_Spawner_Com_Ovo){
	   pm.registerEvents(new BloquearTrocarTipoDoSpawnerComOvo(), this);}
	   
	   if (Settings.Desativar_Chuva){
	   pm.registerEvents(new DesativarChuva(), this);}
	   
	   if (Settings.Desativar_Ciclo_Do_Dia){
	   DesativarCicloDoDia.stopDaylightCycle() ;}
	   
	   if (Settings.Desativar_Dano_Do_EnderDragon){
	   pm.registerEvents(new DesativarDanoDoEnderDragon(), this);}
	   
	   if (Settings.Desativar_Dano_Do_Whither){
	   pm.registerEvents(new DesativarDanoDoWhiter(), this);}
	   
	   if (Settings.Desativar_Flow_Da_Agua_E_Lava){
	   pm.registerEvents(new DesativarFlowDaAguaELava(), this);}
	   
	   if (Settings.Desativar_Fome_Nos_Mundos){
	   pm.registerEvents(new DesativarFomeNosMundos(), this);}
	   
	   if (Settings.Desativar_Mensagem_De_Entrada){
	   pm.registerEvents(new DesativarMensagemDeEntrada(), this);}
	   
	   if (Settings.Desativar_Mensagem_De_Morte){
	   pm.registerEvents(new DesativarMensagemDeMorte(), this);}
	   
	   if (Settings.Desativar_Mensagem_De_Saida){
	   pm.registerEvents(new DesativarMensagemDeSaida(), this);}
	   
	   if (Settings.Desativar_Mobs_Naturais){
	   pm.registerEvents(new DesativarMobsNaturais(), this);}
	   
	   if (Settings.Desativar_Propagacao_Do_Fogo){
	   pm.registerEvents(new DesativarPropagacaoDoFogo(), this);}
	   
	   if (Settings.Desativar_Queda_Da_Areia){
	   pm.registerEvents(new DesativarQuedaDaAreia(), this);}
	   
	   if (Settings.Desativar_Queda_Das_Folhas){
	   pm.registerEvents(new DesativarQuedaDasFolhas(), this);}
	   
	   if (Settings.Dropar_Cabeca_Ao_Morrer){
	   pm.registerEvents(new DroparCabecaAoMorrer(), this);}
	   
	   if (Settings.Dropar_Spawner_Ao_Explodir){
	   pm.registerEvents(new DroparSpawnerAoExplodir(), this);}
	   
	   if (Settings.EnderPearl_Cooldown_Ativar){
	   pm.registerEvents(new EnderPearlCooldown(), this);}
	   
	   if (Settings.Entrar_No_Spawn_Ao_Logar){
	   pm.registerEvents(new EntrarNoSpawnAoLogar(), this);}
	   
	   if (Settings.Invencibilidade_Ao_Teleportar){
	   pm.registerEvents(new InvencibilidadeAoTeleportar(), this);}
	   
	   if (Settings.Limitador_De_Players){
	   pm.registerEvents(new LimiteDePlayers(), this);}
	   
	   if (Settings.Mensagem_De_Boas_Vindas_Ativar){
	   pm.registerEvents(new MensagemDeBoasVindas(), this);}
	    
	   if (Settings.Motd_Ativar){
	   pm.registerEvents(new Motd(), this);}
	    
	   if (Settings.ScoreBoard_Ativar){
	   pm.registerEvents(new ScoreBoard(), this);}
	   
	   if (Settings.Sistema_De_Fly_Para_Players) {
	   pm.registerEvents(new FlyListener(), this);}
	    
	   if (Settings.Sistema_De_Spawners){
	   pm.registerEvents(new SistemaDeSpawners(), this);}
	   
	   if (Settings.Title_De_Boas_Vindas_Ativar){
	   pm.registerEvents(new TitleDeBoasVindas(), this);}

	   if (Settings.Ativar_Tablist){
	   pm.registerEvents(new Tablist(), this);}
	     
	   if (Settings.AtivarAddons_Legendchat){
	   if (pm.getPlugin("Legendchat") == null) {
	   getServer().getConsoleSender().sendMessage("§c[System] Legendchat nao encontrado, desativando addons!");
	   } else { 
	   pm.registerEvents(new LegendChat(), this);
	   getServer().getConsoleSender().sendMessage("§a[System] Legendchat encontrado, ativando addons!");}}

	   if (Settings.AtivarAddons_mcMMO){
	   if (pm.getPlugin("mcMMO") == null) {
	   getServer().getConsoleSender().sendMessage("§c[System] mcMMO nao encontrado, desativando addons!");
	   } else { 
	   pm.registerEvents(new McMMO(), this);
	   McMMO.checkMCTop();
	   getServer().getConsoleSender().sendMessage("§a[System] mcMMO encontrado, ativando addons!");}}
	   
	   pm.registerEvents(new PlayerData(), this);
	   pm.registerEvents(new EnderChestListener(), this);
	   pm.registerEvents(new InvseeListener(), this);
	   pm.registerEvents(new KitsListener(), this); 
	   pm.registerEvents(new BackListener(), this);
	   pm.registerEvents(new ManterXpAoMorrer(), this);
	   pm.registerEvents(new Outros(), this);
   }
   
   private void desativarRecursos() {
	   PluginManager pm = Bukkit.getServer().getPluginManager();
	   HandlerList.unregisterAll((Listener) this);
	   
	   if (pm.getPlugin("mcMMO") != null){
	   McMMO.TTask.cancel();}
	   
	   if (Settings.Auto_Anuncio){
	   AutoAnuncio.XTask.cancel();}
   }
   
   public static Main get() {
	   return main;
   }
   
}