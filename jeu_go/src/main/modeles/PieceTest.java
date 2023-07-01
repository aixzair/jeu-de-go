/**
 * 
 */
package modeles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author alexl
 *
 */
class PieceTest {

	/**
	 * Test method for {@link modeles.Piece#getOpposer(modeles.Piece)}.
	 */
	@Test
	void testGetOpposer() {
		assertNull(Piece.getOpposer(null));
		assertNull(Piece.getOpposer(Piece.AUCUNE));
		
		assertEquals(Piece.BLANC, Piece.getOpposer(Piece.NOIR));
		assertEquals(Piece.NOIR, Piece.getOpposer(Piece.BLANC));
	}

}
