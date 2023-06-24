package modeles;

import java.util.Objects;

public class Coordonnee {
	private Piece piece;
	private int x;
	private int y;

	/** Créer une coordonnée
	 * @param x
	 * @param y
	 * @param piece
	 */
	public Coordonnee(int x, int y, Piece piece) {
		this.piece = piece;
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.piece, this.x, this.y);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Coordonnee)) {
			return false;
		}
		
		Coordonnee other = (Coordonnee) obj;
		
		return this.piece == other.piece
			   && this.x == other.x
			   && this.y == other.y;
	}
	
	@Override
	public String toString() {
		return "x : " + this.x
			    + "; y : " + this.y
			    + "; " + this.piece;
	}
	
	// ------------ Getters ------------

	/** Renvoi l'piece
	 * @return the piece
	 */
	public Piece getPiece() {
		return this.piece;
	}

	/** Renvoi la propriété x
	 * @return the x
	 */
	public int getX() {
		return this.x;
	}

	/** Renvoi la coordonnée y
	 * @return the y
	 */
	public int getY() {
		return this.y;
	}
}
