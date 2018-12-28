package rush.comandos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rush.configuracoes.Mensagens;

public class ComandoTerminal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		
		// Verificando se o player digitou o número de argumentos corretos
		if (args.length < 1) {
			s.sendMessage(Mensagens.Terminal_Comando_Incorreto);
			return true;
		}

		// Criando uma nova Theard para não rodar na principal
		new Thread() {
			@Override
			public void run() {
				// Pegando o comando a ser executado
				String command = String.join(" ", args);
				
				// Executnado o comando e mostrando a saida de retorno para o sender
				try {
					String line;
					Process process = Runtime.getRuntime().exec(command);
					BufferedReader retorno = new BufferedReader(new InputStreamReader(process.getInputStream()));
					while ((line = retorno.readLine()) != null) {
						s.sendMessage(line);
					}
				} catch (IOException e) {
					s.sendMessage(Mensagens.Terminal_Erro_Comando.replace("%erro%", e.getLocalizedMessage()));
				} catch (Throwable e) {
					s.sendMessage(Mensagens.Terminal_Erro_Desconhecido.replace("%erro%", e.getLocalizedMessage()));
				}
			}
			
		}.start();
		return true;
	}
}