package modeles;

public class Joueur {
	private String prenom;
	private Piece couleur;

	/**
	 * Créer un joueur avec un prenom.
	 * @param prenom
	 */
	public Joueur(String _prenom) {
		this.prenom = _prenom;
	}
	
	// --------------- Setters ---------------
	
	// -- Setter protected --
	
	/**
	 * Chnage la couleur du joueur.
	 * @param couleur
	 */
	protected void setCouleur(Piece couleur) {
		this.couleur = couleur;
	}

}
