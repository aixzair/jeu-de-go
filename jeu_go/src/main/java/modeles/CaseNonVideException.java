package modeles;

/**
 * @author alexl
 */
public class CaseNonVideException
extends Exception {

	/**
	 * Généré automatiquement
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 */
	public CaseNonVideException() {
		super("La case doit être vide");
	}

	/**
	 * Constructeur
	 * @param message
	 */
	public CaseNonVideException(String message) {
		super(message);
	}

	/**
	 * Constructeur
	 * @param cause
	 */
	public CaseNonVideException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructeur
	 * @param message
	 * @param cause
	 */
	public CaseNonVideException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructeur
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CaseNonVideException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
