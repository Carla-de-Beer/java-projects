package useCases;
public class FileIncorrectDataException extends Exception {

	private static final long serialVersionUID = 999L;

	public FileIncorrectDataException() {
		super();
	}

	public FileIncorrectDataException(String message) {
		super(message);
	}

	public FileIncorrectDataException(String message, Throwable throwable) {
		super(message, throwable);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
