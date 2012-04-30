package info.bytecraft.zones;

public class ZoneNotFoundException extends Exception{
	private static final long serialVersionUID = -8460800454978657204L;
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
	
	public ZoneNotFoundException() {
		super();
	}

	public String getTriedZone(){
		return (input == null ? "Unkown": input);
	}
}
