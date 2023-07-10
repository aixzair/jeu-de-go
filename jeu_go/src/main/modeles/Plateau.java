package modeles;

import java.util.ArrayList;
import java.util.Random;

import application.Interface;

/** 
 * @author Alexandre Lerosier
 */
public class Plateau {
	private final byte taille = 3;
	private Piece[][] pieces = new Piece[this.taille][this.taille];
	
	private ArrayList<ArrayList<Coordonnee>> groupes;
	private ArrayList<ArrayList<Coordonnee>> groupesEntrourer;

	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>(2);
	private int joueurActuel;
	private Joueur gagnant;
	
	private int tour = 1;
	private int nbCasesLibres = this.taille * this.taille;
	private int scores[] = new int[2]; // 0 : Blanc, 1 : Noir

	/**
	 * Créé un pieces de jeu
	 */
	public Plateau(Joueur joueur1, Joueur joueur2) {
		joueur1.setCouleur(Piece.BLANC);
		joueur2.setCouleur(Piece.NOIR);
		
		this.joueurs.add(joueur1);
		this.joueurs.add(joueur2);
		
		this.initialisation();
	}
	
	// -------------- Getters --------------
	
	/**
	 * Renvoie vrai si l'emplacement est vide
	 * @param lg
	 * @param col
	 * @return boolean
	 */
	public boolean estCaseLibre(final int lg, final int col) {
		if (lg < 0
		|| lg >= this.pieces.length) {
			return false;
		} else if (col < 0
		|| col >= this.pieces[lg].length) {
			return false;
		}
		
		return this.pieces[lg][col] == Piece.AUCUNE;
	}
	
	/**
	 * Renvoie le joueur actuel.
	 * @return le joueur actuel
	 */
	public Joueur getJoueurActuel() {
		return this.joueurs.get(this.joueurActuel);
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
	 * Renvoie la taille du pieces
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
		return this.gagnant != null;
	}
	
	/**
	 * Renvoie la pièce sur le plateau
	 * @param x : colonne
	 * @param y : ligne
	 * @return la pièce 
	 */
	public Piece getPiece(int x, int y) {
		return this.pieces[y][x];
	}
	
	
	/**
	 * Renvoie le pieces de jeu
	 * @return
	 */
	public Piece[][] getPieces() {
		return this.pieces;
	}
	
	/**
	 * Renvoie le numéro du tour en cours.
	 * @return le numéro du tour en cours.
	 */
	public int getTour() {
		return this.tour;
	}
	
	// -------------- Fonctions publiques --------------
	
	/**
	 * Pose la pièce et commence un nouveau tour
	 * @param coordonnee
	 * @throws CaseNonVideException : si il y a déjà une pièce sur la case
	 */
	public void poserPiece(Coordonnee coordonnee)
	throws CaseNonVideException {
		// Vérifie que la case est vide.
		if (this.pieces[coordonnee.getY()][coordonnee.getX()].ordinal() != Piece.AUCUNE.ordinal()) {
			throw new CaseNonVideException();
		}
		
		Piece couleurAdverse = Piece.getOpposer(this.getJoueurActuel().getCouleur());
		
		// Pose la pièce
		this.pieces[coordonnee.getY()][coordonnee.getX()] = this.getJoueurActuel().getCouleur();
		this.nbCasesLibres--;
		
		// Enlève les pièces adverse entourées
		this.trouverGroupesEntoure();
		
		for (ArrayList<Coordonnee> groupe : this.groupesEntrourer) {
			if (this.pieces[groupe.get(0).getY()][groupe.get(0).getX()].ordinal() != couleurAdverse.ordinal()) {
				continue;
			}
						
			for (Coordonnee piece : groupe) {
				this.scores[1 - this.joueurActuel]--;
				this.pieces[piece.getY()][piece.getX()] = Piece.AUCUNE;
			}
		}
		
		this.groupes = null;
		this.groupesEntrourer = null;
		
		// Nouveau tour
		this.tourSuivant();
	}
	
	/**
	 * Ne fait rien puis commence un nouveau tour
	 */
	public void passerSonTour() {
		this.tourSuivant();
	}
	
	// -------------- Fonctions privées --------------
	
	/**
	 * Met les cases du pieces à Piece.VIDE
	 */
	private void initialisation() {
		for (byte lg = 0; lg < this.pieces.length; lg++) {
			for (byte col = 0; col < this.pieces[lg].length; col++) {
				this.pieces[lg][col] = Piece.AUCUNE;
			}
		}
	}
	
	/**
	 * Remplit aléatoirement le pieces
	 */
	private void remplissageAleatoire() {
		Random random = new Random();
		
		for (byte lg = 0; lg < this.pieces.length; lg++) {
			for (byte col = 0; col < this.pieces.length; col++) {
				switch(random.nextInt(3)) {
				case 0: this.pieces[lg][col] = Piece.AUCUNE; break;
				case 1: this.pieces[lg][col] = Piece.BLANC; break;
				case 2: this.pieces[lg][col] = Piece.NOIR; break;
				}
			}
		}
	}
	
	/**
	 * Indique si les deux coordonnee pointent vers une Piece identique
	 * @param pion
	 * @param autrePion
	 * @return true : Piece identique
	 */
	private boolean memePiece(Coordonnee pion, Coordonnee autrePion) {
		if (pion == null
		|| autrePion == null) {
			return false;
		}
		
		return this.getPiece(pion.getX(), pion.getY()).ordinal()
			== this.getPiece(autrePion.getX(), autrePion.getY()).ordinal();
	}
	
	/**
	 * Remplie le groupe de pion
	 * @param pions
	 * @param lg
	 */
	private void remplirGroupe(ArrayList<Coordonnee> pions, int lg) {
		Coordonnee pion;
		Coordonnee autrePion;
		
		for (int i = 0; i < pions.size(); i++) {
			pion = pions.get(i);
			
			// Pion au dessus
			if (pion.getY() - 1 >= 0) {
				autrePion = new Coordonnee(
					pion.getX(),
					pion.getY() - 1
				);
				
				if (this.memePiece(pion, autrePion)
				&& !pions.contains(autrePion)) {
					pions.add(autrePion);
				}
			}
			
			// Pion au dessous
			if (pion.getY() + 1 < this.pieces.length) {
				autrePion = new Coordonnee(
					pion.getX(),
					pion.getY() + 1
				);
				
				if (this.memePiece(pion, autrePion)
				&& !pions.contains(autrePion)) {
					pions.add(autrePion);
				}
			}
			
			// Pion à gauche
			if (pion.getX() - 1 >= 0) {
				autrePion = new Coordonnee(
					pion.getX() - 1,
					pion.getY()
				);
				
				if (this.memePiece(pion, autrePion)
				&& !pions.contains(autrePion)) {
					pions.add(autrePion);
				}
			}
			
			// Pion à droite
			if (pion.getX() + 1 < this.pieces[lg].length) {
				autrePion = new Coordonnee(
					pion.getX() + 1,
					pion.getY()
				);
				
				if (this.memePiece(pion, autrePion)
				&& !pions.contains(autrePion)) {
					pions.add(autrePion);

				}
			}
		}
	}
	
	/**
	 * Trouve les groupes de pion sur le pieces et les ajoute à une liste
	 */
	private void trouverGroupes() {
		Coordonnee pion;
		ArrayList<Coordonnee> pions;

		this.groupes = new ArrayList<ArrayList<Coordonnee>>();
				
		for (byte lg = 0; lg < this.pieces.length; lg++) {
			for (byte col = 0; col < this.pieces[lg].length; col++) {
				pion = new Coordonnee(col, lg);				
				
				if (this.estInclus(this.groupes, pion)
				|| this.getPiece(col, lg).ordinal() == Piece.AUCUNE.ordinal()) {
					continue;
				}
				
				// Création d'un nouveau groupe de pions
				pions = new ArrayList<Coordonnee>();
				pions.add(pion);
				
				this.remplirGroupe(pions, lg);
				
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
				if (this.pieces[coordonnee.getY() - 1][coordonnee.getX()].ordinal()
				== Piece.AUCUNE.ordinal()) {
					return false;
				}
			}
			
			// Bas
			if (coordonnee.getY() != this.pieces.length - 1) {
				if (this.pieces[coordonnee.getY() + 1][coordonnee.getX()].ordinal()
				== Piece.AUCUNE.ordinal()) {
					return false;
				}
			}
			
			// Gauche
			if (coordonnee.getX() != 0) {
				if (this.pieces[coordonnee.getY()][coordonnee.getX() - 1].ordinal()
				== Piece.AUCUNE.ordinal()) {
					return false;
				}
			}
			
			// Droite
			if (coordonnee.getX() != this.pieces.length - 1) {
				if (this.pieces[coordonnee.getY()][coordonnee.getX() + 1].ordinal()
				== Piece.AUCUNE.ordinal()) {
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
	
	/**
	 * Change le joueur actuel.
	 */
	private void nextJoueur() {
		this.joueurActuel = 1 - this.joueurActuel;
	}
	
	/**
	 * Termine le tour e créé un nouveau tour.
	 */
	private void tourSuivant() {
		this.nextJoueur();
		
		if (this.joueurActuel == 0) {
			this.tour++;
		}
	}
}
