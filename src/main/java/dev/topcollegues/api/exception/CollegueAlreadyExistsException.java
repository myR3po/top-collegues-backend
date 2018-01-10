package dev.topcollegues.api.exception;

public class CollegueAlreadyExistsException extends CollegueException {

	private static final long serialVersionUID = -8809367864620947038L;

	public CollegueAlreadyExistsException() {
		super();
	}

	public CollegueAlreadyExistsException(String message) {
		super(message);
	}

}
