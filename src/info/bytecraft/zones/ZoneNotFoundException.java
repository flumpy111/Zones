package info.bytecraft.zones;

@SuppressWarnings("serial")
public class ZoneNotFoundException extends Exception{
	
	public ZoneNotFoundException(String message){
		super(message);
	}
	
	public ZoneNotFoundException(String message, Exception ex){
		super(message, ex);
	}
}
