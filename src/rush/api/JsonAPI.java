package rush.api;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Bukkit.getConsoleSender;

/**
 * @author Mior
 * @version 1.0
 * @category utils
 */

public class JsonAPI {
	
	/*
	 * Texts with extra text in the message.
	 */
	
	public static void sendText(String player, String message, String extra) {
		String rawText = "{\"text\":\""+message+"\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\""+extra+"\"}}";
		sendJsonMessage(player,rawText);
	}
	
	public static void sendTextWithUrl(String player, String message, String extra, String url) {
		String rawText = "{\"text\":\""+message+"\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\""+url+"\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\""+extra+"\"}}";
		sendJsonMessage(player,rawText);
	}
	
	public static void sendTextWithCommand(String player, String message, String extra, String command) {
		String rawText = "{\"text\":\""+message+"\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\""+command+"\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\""+extra+"\"}}";
		sendJsonMessage(player,rawText);
	}
	
	public static void sendTextWithSuggestCommand(String player, String message, String extra, String suggestCommand) {
		String rawText = "{\"text\":\""+message+"\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\""+suggestCommand+"\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\""+extra+"\"}}";
		sendJsonMessage(player,rawText);
	}
	
	/*
	 * Texts without extra text in the message.
	 */
	
	public static void sendTextWithUrl(String player, String message, String url) {
		String rawText = "{\"text\":\""+message+"\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\""+url+"\"}}";
		sendJsonMessage(player,rawText);
	}
	
	public static void sendTextWithCommand(String player, String message, String command) {
		String rawText = "{\"text\":\""+message+"\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\""+command+"\"}}";
		sendJsonMessage(player,rawText);
	}
	
	public static void sendTextWithSuggestCommand(String player, String message, String suggestCommand) {
		String rawText = "{\"text\":\""+message+"\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\""+suggestCommand+"\"}}";
		sendJsonMessage(player,rawText);
	}
	
	/*
	 * Method used to send a message
	 */
	
	static void sendJsonMessage(String player, String rawText) {
		getServer().dispatchCommand(getConsoleSender(), "tellraw " + player + " " + rawText);
	}
}