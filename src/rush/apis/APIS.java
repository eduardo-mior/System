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
			ItemAPI.load();
			ViewDistanceAPI.load();
		} 
		catch (Error | Exception e) {}
		
	}
	
}