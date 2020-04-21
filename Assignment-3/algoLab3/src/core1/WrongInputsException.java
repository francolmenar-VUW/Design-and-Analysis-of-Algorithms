package core1;

public class WrongInputsException extends Exception{
	private static final long serialVersionUID = 1L;
	String message;

	public WrongInputsException() {
		message = "Invalid data\n";
	}

	public String getMessage(){
		return this.message;
	}
}
