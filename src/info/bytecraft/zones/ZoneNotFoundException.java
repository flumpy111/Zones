package info.bytecraft.zones;

@SuppressWarnings("serial")
public class ZoneNotFoundException extends Exception{
	private String input;
	
	public ZoneNotFoundException(String message){
		super(message);
	}
	
	public ZoneNotFoundException(String message, Exception ex){
		super(message, ex);
	}
	
	public ZoneNotFoundException(String message, Exception ex, String input){
		this(message, ex);
		this.input = input;
	}
	
	public String getTriedZone(){
		return this.input;
	}
}
