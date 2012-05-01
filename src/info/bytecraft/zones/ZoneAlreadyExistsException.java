package info.bytecraft.zones;

import info.bytecraft.zones.info.Zone;

@SuppressWarnings("serial")
public class ZoneAlreadyExistsException extends Exception{
	private Zone zone;
	
	public ZoneAlreadyExistsException(String message){
		super(message);
	}
	
	public ZoneAlreadyExistsException(String message, Throwable ex){
		super(message,ex);
	}
	
	public ZoneAlreadyExistsException(String message, Zone zone){
		super(message);
		this.zone = zone;
	}
	
	public Zone getZone(){
		return zone;
	}
	
}
