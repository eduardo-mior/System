package rush.apis;

public class APIS {

	public static void load() 
	{
		try 
		{
			CrashAPI.load();
			TablistAPI.load();
			ActionBarAPI.load();
			TitleAPI.load();	
		} 
		catch (Exception e) {}
		
	}
	
}