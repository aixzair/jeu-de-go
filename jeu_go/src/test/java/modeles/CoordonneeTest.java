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
class CoordonneeTest {
	/**
	 * Test method for {@link modeles.Coordonnee#Coordonnee(int, int)}.
	 */
	@Test
	void testCoordonnee() {
		int x = 1;
		int y = 2;
		
		Coordonnee coordonnee = new Coordonnee(x, y);
		
		assertNotNull(coordonnee);
		assertTrue(coordonnee instanceof Coordonnee);
		
		assertEquals(x, coordonnee.getX());
		assertEquals(y, coordonnee.getY());
	}
	
	/**
	 * Test method for {@link modeles.Coordonnee#hashCode()}.
	 */
	@Test
	void testHashCode() {
		int x = 1;
		int y = 2;
		
		Coordonnee coordonnee1 = new Coordonnee(x, y);
		Coordonnee coordonnee2 = new Coordonnee(x, y);
		
		assertEquals(coordonnee1.hashCode(), coordonnee2.hashCode());
	}

	/**
	 * Test method for {@link modeles.Coordonnee#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		int x = 1;
		int y = 2;
		
		Coordonnee coordonnee1 = new Coordonnee(x, y);
		Coordonnee coordonnee2 = new Coordonnee(x, y);
		
		assertTrue(coordonnee1.equals(coordonnee2));
	}

	/**
	 * Test method for {@link modeles.Coordonnee#toString()}.
	 */
	@Test
	void testToString() {
		int x = 1;
		int y = 2;
		
		Coordonnee coordonnee = new Coordonnee(x, y);
		
		assertEquals("x : " + x + "; y : " + y, coordonnee.toString());
	}

	/**
	 * Test method for {@link modeles.Coordonnee#getX()}.
	 */
	@Test
	void testGetX() {
		int x = 12;
		int y = 25;
		
		Coordonnee coordonnee = new Coordonnee(x, y);
		
		assertEquals(x, coordonnee.getX());
	}

	/**
	 * Test method for {@link modeles.Coordonnee#getY()}.
	 */
	@Test
	void testGetY() {
		int x = 12;
		int y = 25;
		
		Coordonnee coordonnee = new Coordonnee(x, y);
		
		assertEquals(y, coordonnee.getY());
	}

}
