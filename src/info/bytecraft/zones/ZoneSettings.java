package info.bytecraft.zones;

public enum ZoneSettings {
	
	/**
	 * The default zone settings, no whitelist, no pvp, freebreak/place and mobs are allowed
	 */
	DEFAULT(false, false, true, true, true),
	/**
	 * PVP zone settings, no whitelist, pvp is allowed, building is off, and mobs are not allowed
	 */
	PVP(false, true, false, false, false),
	/**
	 * WhiteList pvp settings, whitelist and pvp are on
	 */
	WHITELIST_PVP(true, true, false, false, false),
	/**
	 * Default whitelisting, whitelist is off, pvp is off, freebuild is off, and mobs are allowed
	 */
	WHITELIST(true, false, false, false, true);
	
	
	private static boolean whitelist;
	private static boolean pvp;
	private static boolean freebreak;
	private static boolean freeplace;
	private static boolean mobs;
	
	ZoneSettings(boolean arg0, boolean arg1, boolean arg2, boolean arg3, boolean arg4){
		settings(arg0, arg1, arg2, arg3, arg4);
	}
	
	private static void settings(boolean arg0, boolean arg1, boolean arg2, boolean arg3, boolean arg4){
		whitelist = arg0;
		pvp = arg1;
		freebreak = arg2;
		freeplace = arg3;
		mobs = arg4;
	}
	
	public static boolean isWhitelist(){
		return whitelist;
	}
	
	public static boolean isPvp(){
		return pvp;
	}
	
	public static boolean isFreeBreak(){
		return freebreak;
	}
	
	public static boolean isFreePlace(){
		return freeplace;
	}
	
	public static boolean isMobsAllowed(){
		return mobs;
	}
	
	public static boolean isFreeBuild(){
		return (((freeplace == freebreak) && freeplace == true) ? true: false);
	}

}
