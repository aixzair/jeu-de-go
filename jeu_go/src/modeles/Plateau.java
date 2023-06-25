package modeles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import application.Interface;

/** 
 * @author Alexandre Lerosier
 */
public class Plateau {
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
	public Plateau() {
		this.initialisation();
		// this.remplissageAleatoire();
	}
	
	// -------------- Getters --------------
	
	/**
	 * Renvoie vrai si l'emplacement est vide
	 * @param lg
	 * @param col
	 * @return boolean
	 */
	public boolean estCaseLibre(int lg, int col) {
		return this.plateau[lg][col] == Piece.AUCUNE;
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
	 * Indique si la partie est terminée
	 * @return si la partie est terminée
	 */
	public boolean estTerminee() {
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
			
			for (Coordonnee coordonnee : groupe) {
				plateauGroupe[coordonnee.getY()][coordonnee.getX()] = coordonnee.getPiece();
			}
			
			Interface.afficher(plateauGroupe);
		}
	}
	
	/**
	 * Affiche les groupes de pion entourés de pion du plateau
	 */
	public void afficherGroupesEtoure() {
		Piece plateauGroupe[][];	
		
		this.trouverGroupesEntoure();
		
		for (ArrayList<Coordonnee> groupe : this.groupesEntrourer) {
			plateauGroupe = new Piece[this.plateau.length][this.plateau.length];
			
			for (Coordonnee coordonnee : groupe) {
				plateauGroupe[coordonnee.getY()][coordonnee.getX()] = coordonnee.getPiece();
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
	
	public void jouer(Coordonnee coordonnee)
	throws CaseNonVideException {
		if (this.plateau[coordonnee.getY()][coordonnee.getX()].ordinal() != Piece.AUCUNE.ordinal()) {
			throw new CaseNonVideException();
		}
	}
	
	// -------------- Fonctions privées --------------
	
	/**
	 * Met les cases du plateau à Piece.VIDE
	 */
	private void initialisation() {
		for (byte lg = 0; lg < this.plateau.length; lg++) {
			for (byte col = 0; col < this.plateau[lg].length; col++) {
				this.plateau[lg][col] = Piece.AUCUNE;
			}
		}
	}
	
	/**
	 * Remplit aléatoirement le plateau
	 */
	private void remplissageAleatoire() {
		Random random = new Random();
		
		for (byte lg = 0; lg < this.plateau.length; lg++) {
			for (byte col = 0; col < this.plateau.length; col++) {
				switch(random.nextInt(3)) {
				case 0: this.plateau[lg][col] = Piece.AUCUNE; break;
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
				if (this.plateau[lg][col].ordinal() == Piece.AUCUNE.ordinal()) {
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
						
						if (pion.getPiece().ordinal() == autrePion.getPiece().ordinal()
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
						
						if (pion.getPiece().ordinal() == autrePion.getPiece().ordinal()
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
						
						if (pion.getPiece().ordinal() == autrePion.getPiece().ordinal()
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
						
						if (pion.getPiece().ordinal() == autrePion.getPiece().ordinal()
						&& !pions.contains(autrePion)) {
							pions.add(autrePion);

						}
					}
				}
				
				this.groupes.add(pions);
			}
		}
	}

	/**
	 * Trouve les groupes entourés par les pions adverse
	 */
	private void trouverGroupesEntoure() {
		this.groupesEntrourer = new ArrayList<ArrayList<Coordonnee>>();
		this.trouverGroupes();
		
		for (ArrayList<Coordonnee> groupe : this.groupes) {
			if (!this.estGroupeEntoure(groupe)) {
				continue;
			}
			
			this.groupesEntrourer.add(groupe);
		}
	}
	
	/**
	 * Indique si le groupe est entouré par l'adverssaire ou non
	 * @param groupe
	 * @return boolean
	 */
	private boolean estGroupeEntoure(ArrayList<Coordonnee> groupe) {
		for (Coordonnee coordonnee : groupe) {
			
			// Haut
			if (coordonnee.getY() != 0) {
				if (this.plateau[coordonnee.getY() - 1][coordonnee.getX()].ordinal()
				!= Piece.getOpposer(coordonnee.getPiece()).ordinal()) {
					return false;
				}
			}
			
			// Bas
			if (coordonnee.getY() != this.plateau.length - 1) {
				if (this.plateau[coordonnee.getY() + 1][coordonnee.getX()].ordinal()
				!= Piece.getOpposer(coordonnee.getPiece()).ordinal()) {
					return false;
				}
			}
			
			// Gauche
			if (coordonnee.getX() != 0) {
				if (this.plateau[coordonnee.getY()][coordonnee.getX() - 1].ordinal()
				!= Piece.getOpposer(coordonnee.getPiece()).ordinal()) {
					return false;
				}
			}
			
			// Droite
			if (coordonnee.getX() != this.plateau.length - 1) {
				if (this.plateau[coordonnee.getY()][coordonnee.getX() + 1].ordinal()
				!= Piece.getOpposer(coordonnee.getPiece()).ordinal()) {
					return false;
				}
			}
		}
		
		return true;
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
