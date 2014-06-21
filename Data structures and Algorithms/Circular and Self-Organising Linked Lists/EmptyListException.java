//Carla de Beer
//Circular linked list and self-organising linked list
//Date created: 18/02/2014
//Date revised: 21/06/2014

public class EmptyListException extends RuntimeException {
	String reason;

	public EmptyListException() {
		super();
	}

	public EmptyListException(String s) {
		super(s);
		reason = s;
	}

	public String getMessage() {
		return super.getMessage();
	}

}

