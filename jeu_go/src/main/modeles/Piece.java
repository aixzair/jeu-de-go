package modeles;

public enum Piece {
	AUCUNE,
	NOIR,
	BLANC;
	
	public static Piece getOpposer(Piece piece) {
		if (piece == null
		|| piece.ordinal() == Piece.AUCUNE.ordinal()) {
			return null;
		}
		
		return (piece.ordinal() == Piece.BLANC.ordinal())?
				Piece.NOIR:
				Piece.BLANC;
	}
}
