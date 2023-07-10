package modeles;

public class PartieTerminerException
extends Exception {

	public PartieTerminerException() {
		super("La partie est terminé, méthode inutile.");
	}
}
