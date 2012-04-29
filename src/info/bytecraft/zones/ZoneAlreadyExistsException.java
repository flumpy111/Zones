package info.bytecraft.zones;

import info.bytecraft.zones.info.Zone;

@SuppressWarnings("serial")
public class ZoneAlreadyExistsException extends Exception{
	private static Zone zone;
	
	public ZoneAlreadyExistsException(String message){
		super(message);
	}
	
	public ZoneAlreadyExistsException(String message, Throwable ex){
		super(message,ex);
	}
	
	public ZoneAlreadyExistsException(String message, Zone zone){
		super(message);
		ZoneAlreadyExistsException.zone = zone;
	}
	
	public static Zone getZone(){
		return zone;
	}
	
}
