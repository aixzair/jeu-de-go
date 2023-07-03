package modeles;

public class Joueur {
	private String prenom;
	private Piece couleur;

	/**
	 * Créer un joueur avec un prenom.
	 * @param prenom du joueur
	 */
	public Joueur(String prenom) {
		this.prenom = prenom;
	}
	
	// --------------- Setters ---------------
	
	// -- Setter protected --
	
	/**
	 * Change la couleur du joueur.
	 * @param couleur
	 */
	protected void setCouleur(Piece couleur) {
		this.couleur = couleur;
	}
	
	// --------------- Getters ---------------
	
	/**
	 * Renvoie la couleur du joueur
	 * @return la couleur du joueur
	 */
	public Piece getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Renvoie le prenom du joueur
	 * @return le prenom du joueur
	 */
	public String getPrenom() {
		return this.prenom;
	}

}
