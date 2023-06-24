package application;

import modeles.Plateau;

/** 
 * @author Alexandre Lerosier
 */
public class Main {

	public static void main(String[] args) {
		Plateau plateau = new Plateau();
		Partie partie = new Partie(plateau);
		
		partie.jouer();
	}

}
