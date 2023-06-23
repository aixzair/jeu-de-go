package modeles;

public enum Piece {
	AUCUN,
	NOIR,
	BLANC;
	
	public static Piece getOpposer(Piece piece) {
		return (piece.ordinal() == Piece.BLANC.ordinal())?
				Piece.NOIR:
				Piece.BLANC;
	}
}
