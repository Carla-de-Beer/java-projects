// Carla de Beer
// List class implemented in terms of a Queue implemented in terms of a Stack
// No native Java libraries were used in this project
// Date created: 15/02/2014

public class MyException extends RuntimeException {

	private String reason;

	public MyException() {
		super();
	}

	public MyException(String s) {
		super(s);
		reason = s;
	}

	public MyException(String s, Throwable throwable) {
		super(s, throwable);
		reason = s;
	}

	public String getMessage() {
		return super.getMessage();
	}

}

