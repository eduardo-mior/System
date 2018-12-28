package rush.apis;

public class APIS {

	public static void load() 
	{
		try 
		{
			ItemAPI.load();
			CrashAPI.load();
			TitleAPI.load();
			AnvilAPI.load();
			TablistAPI.load();
			ActionBarAPI.load();
			ViewDistanceAPI.load();
		} 
		catch (Throwable e) {}
	}
	
}