package info.bytecraft.zones;

@SuppressWarnings("serial")
public class ZoneAlreadyExistsException extends Exception{
	
	public ZoneAlreadyExistsException(String message){
		super(message);
	}
	
	public ZoneAlreadyExistsException(String message, Throwable ex){
		super(message,ex);
	}
	
}
