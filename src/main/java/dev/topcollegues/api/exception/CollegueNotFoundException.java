package dev.topcollegues.api.exception;

public class CollegueNotFoundException extends CollegueException {

	private static final long serialVersionUID = -6648200852653746508L;

	public CollegueNotFoundException() {
		super();
	}

	public CollegueNotFoundException(String message) {
		super(message);
	}

}
