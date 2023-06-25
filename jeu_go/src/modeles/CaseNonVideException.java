package modeles;

public class CaseNonVideException
extends Exception {

	public CaseNonVideException() {
		super("La case doit être vide");
	}

	public CaseNonVideException(String message) {
		super(message);
	}

	public CaseNonVideException(Throwable cause) {
		super(cause);
	}

	public CaseNonVideException(String message, Throwable cause) {
		super(message, cause);
	}

	public CaseNonVideException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
