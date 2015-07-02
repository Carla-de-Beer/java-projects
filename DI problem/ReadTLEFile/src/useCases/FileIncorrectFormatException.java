package useCases;
public class FileIncorrectFormatException extends Exception {

	private static final long serialVersionUID = 998L;

	public FileIncorrectFormatException() {
		super();
	}

	public FileIncorrectFormatException(String message) {
		super(message);
	}

	public FileIncorrectFormatException(String message, Throwable throwable) {
		super(message, throwable);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
