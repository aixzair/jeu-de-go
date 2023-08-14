package application;

import modeles.Choix;
import modeles.Coordonnee;
import modeles.Joueur;
import modeles.Plateau;

/** 
 * @author Alexandre Lerosier
 */
public class Partie {
	private Plateau plateau;
	private Interface ihm;

	public Partie() {
		/* Joueur joueur1 = Interface.demanderJoueur(1);
		Joueur joueur2 = Interface.demanderJoueur(2);

		this.plateau = new Plateau(joueur1, joueur2);
		this.ihm = new Interface(this); */
	}
	
	public Plateau getPlateau() {
		return this.plateau;
	}
	
	public void jouer() {
		while (!this.plateau.estTerminee()) {
			Choix choix;
			Coordonnee coup;
			
			// Affiche le plateau de jeu
			System.out.println("");
			this.ihm.afficher();
			
			// Demande le choix
			choix = this.ihm.demanderChoix();
			System.out.println("");
			
			// Exécute le choix
			try {
				if (choix.ordinal() == Choix.PASSER_TOUR.ordinal()) {
					this.plateau.passerSonTour();
					
				} else if (choix.ordinal() == Choix.POSER_PIECE.ordinal()) {
					// Demande un coup valide.
					coup = this.ihm.demanderCoordonnee();
					
					while (!this.plateau.estCaseLibre(coup.getY(), coup.getX())) {
						Interface.message("Coup invalide.");
						coup = this.ihm.demanderCoordonnee();
					}
					
					// Pose la pièce
					this.plateau.poserPiece(coup);
					
				} else if (choix.ordinal() == Choix.ABANDONNER.ordinal()) {
					this.plateau.abandonner();
				}
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
