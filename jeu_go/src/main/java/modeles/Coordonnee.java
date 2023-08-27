package modeles;

import java.util.Objects;

public class Coordonnee {
	private int x;
	private int y;

	/** Créer une coordonnée
	 * @param x
	 * @param y
	 * @param piece
	 */
	public Coordonnee(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.x, this.y);
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
		
		return this.x == other.x
			   && this.y == other.y;
	}
	
	@Override
	public String toString() {
		return "x : " + this.x
			    + "; y : " + this.y;
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
