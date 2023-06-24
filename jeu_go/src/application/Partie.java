package application;

import modeles.Plateau;

/** 
 * @author Alexandre Lerosier
 */
public class Partie {
	private Plateau plateau;
	private Interface ihm;

	public Partie(Plateau plateau) {
		this.plateau = plateau;
		this.ihm = new Interface(this);
	}
	
	public Plateau getPlateau() {
		return this.plateau;
	}
	
	public void jouer() {
		
		/*while (plateau.getGagnant() == null) {
			this.ihm.afficher();
			break;
		}*/
		
		this.ihm.afficher();
		this.plateau.afficherGroupes();
	}
}
