package modeles;

public final class PartieTerminerException
extends Exception {

	public PartieTerminerException() {
		super("La partie est terminé, méthode inutile.");
	}
}
