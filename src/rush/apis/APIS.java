package rush.apis;

import rush.Main;

public class APIS {

	public static void load() 
	{
	
		CrashAPI.load();
		
		if (!Main.isOldVersion()) 
		{
			TablistAPI.loadAPI();
			ActionBarAPI.load();
			TitleAPI.load();
		}
		
	}
	
}