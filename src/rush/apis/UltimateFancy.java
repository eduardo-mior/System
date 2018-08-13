package rush.apis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import rush.Main;

/**Class to generate JSON elements to use with UltimateChat.
 * @author FabioZumbi12
 *
 */

@SuppressWarnings("unchecked")
public class UltimateFancy {

	private ChatColor lastColor = ChatColor.WHITE;
	private JSONArray constructor;
	private HashMap<String, Boolean> lastformats;
	private List<JSONObject> workingGroup;
	private List<ExtraElement> pendentElements;
	
	/**
	 * Creates a new instance of UltimateFancy.
	 */
	public UltimateFancy(){
		constructor = new JSONArray();
		workingGroup = new ArrayList<JSONObject>();
		lastformats = new HashMap<String, Boolean>();
		pendentElements = new ArrayList<ExtraElement>();
	}
	
	/**Creates a new instance of UltimateFancy with an initial text.
	 * @param text {@code String}
	 */
	public UltimateFancy(String text){
		constructor = new JSONArray();
		workingGroup = new ArrayList<JSONObject>();
		lastformats = new HashMap<String, Boolean>();
		pendentElements = new ArrayList<ExtraElement>();
		text(text);
	}
	
	/**
	 * @param text
	 * @return instance of same {@link UltimateFancy}.
	 */
	public UltimateFancy text(String text){
		for (String part:text.split("(?="+ChatColor.COLOR_CHAR+")")){
			JSONObject workingText = new JSONObject();	
			
			//fix colors before
			filterColors(workingText);
			
			Matcher match = Pattern.compile("^"+ChatColor.COLOR_CHAR+"([0-9a-fk-or]).*$").matcher(part);
			if (match.find()){
				lastColor = ChatColor.getByChar(match.group(1).charAt(0));
				//fix colors from latest
				filterColors(workingText);
				if (part.length() == 2) continue;
			}
			//continue if empty
			if (ChatColor.stripColor(part).isEmpty()){
				continue;
			}
			workingText.put("text", ChatColor.stripColor(part));
						
			//fix colors after
			filterColors(workingText);
			
			if (!workingText.containsKey("color")){
				workingText.put("color", "white");
			}
			workingGroup.add(workingText);
		}			
		return this;
	}
	
	private JSONObject filterColors(JSONObject obj){
		for (Entry<String, Boolean> format:lastformats.entrySet()){
			obj.put(format.getKey(), format.getValue());
		}
		if (lastColor.isFormat()){
			String formatStr = lastColor.name().toLowerCase();
			if (lastColor.equals(ChatColor.MAGIC)){
				formatStr = "obfuscated";
			}
			if (lastColor.equals(ChatColor.UNDERLINE)){
				formatStr = "underlined";
			}
			lastformats.put(formatStr, true);
			obj.put(formatStr, true);
		}			
		if (lastColor.isColor()){
			obj.put("color", lastColor.name().toLowerCase());
		}
		if (lastColor.equals(ChatColor.RESET)){
			obj.put("color", "white");
			for (String format:lastformats.keySet()){
				lastformats.put(format, false);
				obj.put(format, false);
			}
		}
		return obj;
	}
	
	/**Send the JSON message to a {@link CommandSender} via {@code tellraw}.
	 * @param to {@link CommandSender}
	 */
	public void send(CommandSender to){
		next();
		if (to instanceof Player){
			performCommand(Bukkit.getConsoleSender(), "tellraw " + to.getName() + " " + toJson());			
		} else {
			to.sendMessage(toOldFormat());
		}		
	}
	
	private String toJson(){
		return "[\"\","+constructor.toJSONString().substring(1);
	}
	
	/**Close the last text properties and start a new text block.
	 * @return instance of same {@link UltimateFancy}.
	 */
	public UltimateFancy next(){
		if (workingGroup.size() > 0){
			for (JSONObject obj:workingGroup){
				if (obj.containsKey("text") && obj.get("text").toString().length() > 0){					
					for (ExtraElement element:pendentElements){							
						obj.put(element.getAction(), element.getJson());
					}
					constructor.add(obj);
				}
			}
		}		
		workingGroup = new ArrayList<JSONObject>();
		pendentElements = new ArrayList<ExtraElement>();		
		return this;
	}
	
	/**Add a command to execute on click the text.
	 * @param cmd {@link String}
	 * @return instance of same {@link UltimateFancy}.
	 */
	public UltimateFancy clickRunCmd(String cmd){
		pendentElements.add(new ExtraElement("clickEvent",parseJson("run_command", cmd)));
		return this;
	}
	
	/**
	 * @param cmd {@link String}
	 * @return instance of same {@link UltimateFancy}.
	 */
	public UltimateFancy clickSuggestCmd(String cmd){
		pendentElements.add(new ExtraElement("clickEvent",parseJson("suggest_command", cmd)));
		return this;
	}
	
	/**URL to open on external browser when click this text.
	 * @param url {@link String}
	 * @return instance of same {@link UltimateFancy}.
	 */
	public UltimateFancy clickOpenURL(String url){
		pendentElements.add(new ExtraElement("clickEvent",parseJson("open_url", url)));
		return this;
	}
	
	/**Text to show on hover the mouse under this text.
	 * @param text {@link String}
	 * @return instance of same {@link UltimateFancy}.
	 */
	public UltimateFancy hoverShowText(String text){
		pendentElements.add(new ExtraElement("hoverEvent",parseHoverText(text)));
		return this;
	}
	
	/**Convert JSON string to Minecraft string.
	 * @return {@code String} with traditional Minecraft colors.
	 */
	public String toOldFormat(){
		StringBuilder result = new StringBuilder();
		for (Object mjson:constructor){	
			JSONObject json = (JSONObject)mjson;
			if (!json.containsKey("text")) continue;	
			
			//get color
			String colorStr = json.get("color").toString();
			if (ChatColor.valueOf(colorStr.toUpperCase()) != null){				
				ChatColor color = ChatColor.valueOf(colorStr.toUpperCase());
				if (color.equals(ChatColor.WHITE)){
					result.append(String.valueOf(ChatColor.RESET));
				} else {
					result.append(String.valueOf(color));
				}
			}
			
			//get format
			for (ChatColor frmt:ChatColor.values()){
				if (frmt.isColor()) continue;
				String frmtStr = frmt.name().toLowerCase();
				if (frmt.equals(ChatColor.MAGIC)){
					frmtStr = "obfuscated";
				}
				if (frmt.equals(ChatColor.UNDERLINE)){
					frmtStr = "underlined";
				}
				if (json.containsKey(frmtStr)){
					result.append(String.valueOf(frmt));
				}
			}
			result.append(json.get("text").toString());				
		}
		return result.toString();
	}
	
	private JSONObject parseHoverText(String text){
		JSONArray extraArr = addColorToArray(text);		
		JSONObject objExtra = new JSONObject();
		objExtra.put("text", "");
		objExtra.put("extra", extraArr);
		JSONObject obj = new JSONObject();
		obj.put("action", "show_text");	
		obj.put("value", objExtra);	
		return obj;
	}
	
	private JSONObject parseJson(String action, String value){
		JSONObject obj = new JSONObject();
		obj.put("action", action);
		obj.put("value", value);
		return obj;
	}
	
	private JSONArray addColorToArray(String text){
		JSONArray extraArr = new JSONArray();
		ChatColor color = ChatColor.WHITE;
		for (String part:text.split("(?="+ChatColor.COLOR_CHAR+"[0-9a-fk-or])")){	
			JSONObject objExtraTxt = new JSONObject();
			Matcher match = Pattern.compile("^"+ChatColor.COLOR_CHAR+"([0-9a-fk-or]).*$").matcher(part);			
			if (match.find()){
				color = ChatColor.getByChar(match.group(1).charAt(0));
				if (part.length() == 2) continue;
			} 		
			objExtraTxt.put("text", ChatColor.stripColor(part));
			if (color.isColor()){	
				objExtraTxt.put("color", color.name().toLowerCase());			
			}
			if (color.equals(ChatColor.RESET)){
				objExtraTxt.put("color", "white");								
			}		
			if (color.isFormat()){
				if (color.equals(ChatColor.MAGIC)){
					objExtraTxt.put("obfuscated", true);
				} else {
					objExtraTxt.put(color.name().toLowerCase(), true);
				}				
			}
			extraArr.add(objExtraTxt);
		}		
		return extraArr;
	}
	
	private void performCommand(final CommandSender sender, final String command) {
		Bukkit.getScheduler().runTask(Main.get(), new Runnable(){
			@Override
			public void run() {
				Bukkit.getServer().dispatchCommand(sender, command);
	        	}
			}			
		);
	}
	
	/**An imutable pair of actions and {@link JSONObject} values.
	 * @author FabioZumbi12
	 *
	 */
	public class ExtraElement{
		private String action;
		private JSONObject json;
		
		public ExtraElement(String action, JSONObject json){
			this.action = action;
			this.json = json;
		}		
		public String getAction(){
			return this.action;
		}
		public JSONObject getJson(){
			return this.json;
		}
	}

}