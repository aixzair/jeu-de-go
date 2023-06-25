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
		
		/*while (!this.plateau.estTerminee()) {
			
		}*/
		
		this.ihm.afficher();
	}
}
