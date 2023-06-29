package application;

import modeles.CaseNonVideException;
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
		Joueur joueur1 = Interface.demanderJoueur(1);
		Joueur joueur2 = Interface.demanderJoueur(2);
		
		System.out.println("a");
		
		this.plateau = new Plateau(joueur1, joueur2);
		this.ihm = new Interface(this);
	}
	
	public Plateau getPlateau() {
		return this.plateau;
	}
	
	public void jouer() {
		System.out.println("b");
		
		while (!this.plateau.estTerminee()) {
			Coordonnee coup;
			
			this.ihm.afficher();
			
			coup = this.ihm.demanderCoordonnee();
			
			while (!this.plateau.estCaseLibre(coup.getY(), coup.getX())) {
				Interface.message("Coup invalide.");
				coup = this.ihm.demanderCoordonnee();
			}
			
			try {
				this.plateau.poserPiece(coup);
			} catch (CaseNonVideException e) {
				e.printStackTrace();
			}
		}
	}
}
