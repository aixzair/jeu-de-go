package application;

import modeles.PlateauGo;

/** 
 * @author Alexandre Lerosier
 */
public class Main {

	public static void main(String[] args) {
		PlateauGo plateauGo = new PlateauGo();
		Partie partie = new Partie(plateauGo);
		
		partie.jouer();
	}

}
