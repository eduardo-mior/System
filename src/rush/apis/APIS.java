package rush.apis;

public class APIS {

	public static void load() 
	{
		try 
		{
			ItemAPI.load();
			PingAPI.load();
			CrashAPI.load();
			TitleAPI.load();
			AnvilAPI.load();
			GodModeAPI.load();
			TablistAPI.load();
			ActionBarAPI.load();
			ViewDistanceAPI.load();
			OfflinePlayerAPI.load();
			SkullAPI.load();
		} 
		catch (Throwable e) {}
	}
	
}