package useCases;
public class FileEmptyException extends Exception {

	private static final long serialVersionUID = 997L;

	public FileEmptyException() {
		super();
	}

	public FileEmptyException(String message) {
		super(message);
	}

	public FileEmptyException(String message, Throwable throwable) {
		super(message, throwable);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
