package application;

import java.util.Scanner;

import modeles.Piece;

/** 
 * @author Alexandre Lerosier
 */
public class Interface {
	private Partie partie;
	private static final String BLANC = "B";
	private static final String NOIR = "N";
	private static final String VIDE = " ";
	
	// ----------- Méthodes publiques de classe -----------
	
	public static void afficher(final Piece[][] emplacements) {
		for (byte lg = 0; lg < emplacements.length; lg++) {
			if (lg == 0) {
				Interface.afficherLigne(emplacements[lg].length + 3);
			}
			
			for (byte col = 0; col < emplacements[lg].length; col++) {
				if (col == 0) {
					System.out.print("｜");	
				}
				
				if (emplacements[lg][col] == null) {
					System.out.print(Interface.VIDE);
				} else if (emplacements[lg][col].ordinal() == Piece.BLANC.ordinal()) {
					System.out.print(Interface.BLANC);
				} else if (emplacements[lg][col].ordinal() == Piece.NOIR.ordinal()) {
					System.out.print(Interface.NOIR);
				} else {
					System.out.print(Interface.VIDE);
				}
				
				if (col == emplacements[lg].length - 1) {
					System.out.print("｜");	
				}
			}
			
			System.out.println("");
			
			if (lg == emplacements[lg].length - 1) {
				Interface.afficherLigne(emplacements[lg].length + 3);
			}
		}
	}
	
	// ----------- Méthodes privées de classe -----------
	
	private static void afficherLigne(int longueur) {
		for (byte i = 0; i < longueur; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}
	
	// ----------- Méthodes d'instances -----------

	public Interface(Partie partie) {
		this.partie = partie;
	}
	
	// ----------- Getters -----------
	
	public String getString(String message) {
		System.out.print(message + " ");
		
		try (Scanner scanner = new Scanner(System.in)) {
			return scanner.next();
		}
	}
	
	// ----------- Méthodes publiques -----------
	
	public void afficher() {
		Interface.afficher(this.partie.getPlateau().getPlateau());
	}
}