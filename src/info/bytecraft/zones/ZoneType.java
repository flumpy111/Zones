package info.bytecraft.zones;

public enum ZoneType{
	
	@ZoneHandler(settings = ZoneSettings.DEFAULT)
	DEFAULT(0),
	@ZoneHandler(settings = ZoneSettings.WHITELIST)
	RESTRICTED(1);
	
	
	private static int id;
	
	private static void setId(int arg0){
		id = arg0;
	}
	
	public static int getId(){
		return id;
	}
	
	ZoneType(int x){
		setId(x);
	}
	
	public static boolean isWhiteListed(){
		return ZoneSettings.isWhitelist();
	}
	
	public static boolean isPvp(){
		return ZoneSettings.isPvp();
	}
	
	public static boolean isFreeBreak(){
		return ZoneSettings.isFreeBreak();
	}
	
	public static boolean isFreePlace(){
		return ZoneSettings.isFreePlace();
	}
	
	public static boolean isFreeBuild(){
		return ZoneSettings.isFreeBuild();
	}
	
	
}
