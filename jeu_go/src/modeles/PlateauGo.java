package modeles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import application.Interface;

/** 
 * @author Alexandre Lerosier
 */
public class PlateauGo {
	private final byte taille = 3;
	private Piece[][] plateau = new Piece[this.taille][this.taille];
	private ArrayList<ArrayList<Coordonnee>> groupes;
	private ArrayList<ArrayList<Coordonnee>> groupesEntrourer;

	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private int tourJoueur = 1;
	private Joueur gagnant;

	/**
	 * Créé un plateau de jeu
	 */
	public PlateauGo() {
		this.initialisation();
	}
	
	// -------------- Getters --------------
	
	/**
	 * Renvoie vrai si l'emplacement est vide
	 * @param lg
	 * @param col
	 * @return boolean
	 */
	public boolean estEmplacementLibre(int lg, int col) {
		return this.plateau[lg][col] == Piece.AUCUN;
	}
	
	/**
	 * Renvoie le joueur gagnant
	 * @return null : pas de gagant
	 * @return Joueur : gagnant
	 */
	public Joueur getGagnant() {
		return this.gagnant;
	}
	
	/**
	 * Renvoie la taille du plateau
	 * @return taille
	 */
	public byte getTaille() {
		return this.taille;
	}

	/**
	 * Indique si la partie est terminé
	 * @return boolean
	 */
	public boolean aGagant() {
		return this.gagnant == null;
	}
	
	
	/**
	 * Renvoie le plateau de jeu
	 * @return
	 */
	public Piece[][] getPlateau() {
		return this.plateau;
	}
	
	// -------------- Setters --------------

	
	
	// -------------- Fonctions publiques --------------
	
	/**
	 * Affiche les groupes de pion du plateau
	 */
	public void afficherGroupes() {
		Piece plateauGroupe[][];		
		this.trouverGroupes();
		
		for (ArrayList<Coordonnee> groupe : this.groupes) {
			plateauGroupe = new Piece[this.plateau.length][this.plateau.length];
			
			for (Coordonnee pion : groupe) {
				plateauGroupe[pion.getY()][pion.getX()] = pion.getEmplacement();
			}
			
			Interface.afficher(plateauGroupe);
		}
	}
	
	/**
	 * Change et renvoie le joueur qui doit jouer.
	 * @return joueur
	 */
	public Joueur nextJoueur() {
		this.tourJoueur = 1 - this.tourJoueur;
		return this.joueurs.get(this.tourJoueur);
	}
	
	// -------------- Fonctions privées --------------
	
	/**
	 * Met les cases du plateau à Piece.VIDE
	 */
	private void initialisation() {
		for (byte lg = 0; lg < this.plateau.length; lg++) {
			for (byte col = 0; col < this.plateau[lg].length; col++) {
				this.plateau[lg][col] = Piece.AUCUN;
			}
		}
		this.remplissageTest();
	}
	
	/** Fonction de test
	 */
	private void remplissageTest() {
		for (byte lg = 0; lg < this.plateau.length; lg++) {
			for (byte col = 0; col < this.plateau.length; col++) {
				this.plateau[lg][col] = Piece.BLANC;
			}
		}
		
		this.plateau[1][1] = Piece.NOIR;
	}
	
	/**
	 * Remplit aléatoirement le plateau
	 */
	private void remplissageAleatoire() {
		Random random = new Random();
		
		for (byte lg = 0; lg < this.plateau.length; lg++) {
			for (byte col = 0; col < this.plateau.length; col++) {
				switch(random.nextInt(3)) {
				case 0: this.plateau[lg][col] = Piece.AUCUN; break;
				case 1: this.plateau[lg][col] = Piece.BLANC; break;
				case 2: this.plateau[lg][col] = Piece.NOIR; break;
				}
			}
		}
	}
	
	/**
	 * Trouve les groupes de pion sur le plateau et les ajoute à une liste
	 */
	private void trouverGroupes() {
		Coordonnee pion;
		Coordonnee autrePion;
		ArrayList<Coordonnee> pions;
		int pionsIterateur;
		this.groupes = new ArrayList<ArrayList<Coordonnee>>();
				
		for (byte lg = 0; lg < this.plateau.length; lg++) {
			for (byte col = 0; col < this.plateau[lg].length; col++) {
				if (this.plateau[lg][col].ordinal() == Piece.AUCUN.ordinal()) {
					continue;
				}
				
				pion = new Coordonnee(col, lg, this.plateau[lg][col]);				
				
				if (this.estInclus(this.groupes, pion)) {
					continue;
				}
				
				// Création d'un nouveau groupe de pions
				pions = new ArrayList<Coordonnee>();
				pions.add(pion);
				
				for (pionsIterateur = 0; pionsIterateur < pions.size(); pionsIterateur++) {
					pion = pions.get(pionsIterateur);
					
					// Coordonnee au dessus
					if (pion.getY() - 1 >= 0) {
						autrePion = new Coordonnee(
							pion.getX(),
							pion.getY() - 1,
							this.plateau[pion.getY() - 1][pion.getX()]
						);
						
						if (pion.getEmplacement().ordinal() == autrePion.getEmplacement().ordinal()
						&& !pions.contains(autrePion)) {
							pions.add(autrePion);
						}
					}
					
					// Coordonnee au dessous
					if (pion.getY() + 1 < this.plateau.length) {
						autrePion = new Coordonnee(
							pion.getX(),
							pion.getY() + 1,
							this.plateau[pion.getY() + 1][pion.getX()]
						);
						
						if (pion.getEmplacement().ordinal() == autrePion.getEmplacement().ordinal()
						&& !pions.contains(autrePion)) {
							pions.add(autrePion);
						}
					}
					
					// Coordonnee à gauche
					if (pion.getX() - 1 >= 0) {
						autrePion = new Coordonnee(
							pion.getX() - 1,
							pion.getY(),
							this.plateau[pion.getY()][pion.getX() - 1]
						);
						
						if (pion.getEmplacement().ordinal() == autrePion.getEmplacement().ordinal()
						&& !pions.contains(autrePion)) {
							pions.add(autrePion);
						}
					}
					
					// Coordonnee à droite
					if (pion.getX() + 1 < this.plateau[lg].length) {
						autrePion = new Coordonnee(
							pion.getX() + 1,
							pion.getY(),
							this.plateau[pion.getY()][pion.getX() + 1]
						);
						
						if (pion.getEmplacement().ordinal() == autrePion.getEmplacement().ordinal()
						&& !pions.contains(autrePion)) {
							pions.add(autrePion);

						}
					}
				}
				
				this.groupes.add(pions);
			}
		}
	}

	/** Trouve les groupes entourés
	 */
	private void trouverGroupesEntourer() {
		for (ArrayList<Coordonnee> groupe : this.groupes) {
			Iterator<Coordonnee> iterateur = groupe.iterator();
			
			while(iterateur.hasNext()) {
				Coordonnee coordonnee = iterateur.next();
				
				/*// Haut
				if (coordonnee.getY() != 0) {
					if (this.) {
						
					}
				}*/
			}
		}
	}
	
	/**
	 * Indique si la valeur est inclus
	 * @param listes
	 * @param pion
	 * @return boolean
	 */
	private boolean estInclus(ArrayList<ArrayList<Coordonnee>> listes, Coordonnee pion) {
		for (ArrayList<Coordonnee> liste : listes) {
			if (liste.contains(pion)) {
				return true;
			}
		}
		
		return false;
	}
}
