package modeles;

public enum Piece {
	AUCUNE,
	NOIR,
	BLANC;
	
	public static Piece getOpposer(Piece piece) {
		return (piece.ordinal() == Piece.BLANC.ordinal())?
				Piece.NOIR:
				Piece.BLANC;
	}
}
