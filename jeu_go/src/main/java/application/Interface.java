package application;

import java.util.Scanner;

import modeles.Choix;
import modeles.Coordonnee;
import modeles.Joueur;
import modeles.Piece;

/** 
 * @author Alexandre Lerosier
 */
public class Interface {
	private static final String BLANC = "B";
	private static final String NOIR = "N";
	private static final String VIDE = " ";
	
	private Partie partie;
		
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
	
	/**
	 * Demande un joueur à l'utilisateur
	 * @param numero du joueur
	 * @return joueur
	 */
	public static Joueur demanderJoueur(int numero) {
		Scanner scanner = new Scanner(System.in);
		String prenom;
		
		System.out.print("Entré le prénom du joueur " + numero + " : ");
		prenom = scanner.next();
				
		return new Joueur(prenom);
	}
	
	/**
	 * Affiche un message
	 * @param message
	 */
	public static void message(final String message) {
		System.out.println(message);
	}
		
	private static void afficherLigne(final int longueur) {
		for (byte i = 0; i < longueur; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}
	
	/**
	 * Créer une interface homme machine
	 * @param partie
	 */
	public Interface(Partie partie) {
		if (partie == null) {
            throw new NullPointerException();
        }
		this.partie = partie;
	}
		
	public String getString(String message) {
		System.out.print(message + " ");
		
		try (Scanner scanner = new Scanner(System.in)) {
			return scanner.next();
		}
	}
		
	/**
	 * Affiche le plateau.
	 */
	public void afficher() {
		Interface.afficher(this.partie.getPlateau().getPieces());
	}
	
	/**
	 * Demande une coordonnée.
	 * @return coordonnée
	 */
	public Coordonnee demanderCoordonnee() {
		Scanner scanner = new Scanner(System.in);
		int y;
		int x;
		
		System.out.print("En quelle ligne voullez-vous jouer ? ");
		y = scanner.nextInt() - 1;
		
		System.out.print("En quelle colonne voullez-vous jouer ? ");
		x = scanner.nextInt() - 1;
		
		System.out.println("");
		return new Coordonnee(x, y);
	}
	
	/**
	 * Renvoie le choix de l'utilisateur
	 * @return	- passer son tour,
	 * 			- poser une pièce,
	 * 			- abandonner;
	 */
	public Choix demanderChoix() {
		Joueur joueur = this.partie.getPlateau().getJoueurActuel();
		Scanner scanner = new Scanner(System.in);
		int choix = 0;
		
		System.out.println("");
		System.out.println(joueur.getPrenom());
		System.out.println("Passer votre tour : 1");
		System.out.println("Poser une pièce : 2");
		System.out.println("Abandonner : 3");
		System.out.print("Quel est votre choix : ");
		
		choix = scanner.nextInt();
		
		while (choix < 1 || choix > 3) {
			System.out.println("Votre choix n'est pas valide.");
			choix = scanner.nextInt();
		}
		
		return Choix.values()[choix - 1];
	}
}
