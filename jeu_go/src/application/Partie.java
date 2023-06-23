package application;

import modeles.PlateauGo;

/** 
 * @author Alexandre Lerosier
 */
public class Partie {
	private PlateauGo plateauGo;
	private Interface ihm;

	public Partie(PlateauGo plateauGo) {
		this.plateauGo = plateauGo;
		this.ihm = new Interface(this);
	}
	
	public PlateauGo getPlateau() {
		return this.plateauGo;
	}
	
	public void jouer() {
		
		/*while (plateau.getGagnant() == null) {
			this.ihm.afficher();
			break;
		}*/
		
		this.ihm.afficher();
		this.plateauGo.afficherGroupes();
	}
}
