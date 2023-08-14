package modeles;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author alexl
 *
 */
class JoueurTest {

	/**
	 * Test du constructeur
	 */
	@Test
	public void testJoueur() {
		Joueur joueur = new Joueur("test");
		assertTrue(joueur instanceof Joueur);
	}
	
	/**
	 * Test de la fonction getCouleur
	 */
	@Test
	public void testGetPrenom() {
		Joueur joueur = new Joueur("test");
		assertTrue("test".equals(joueur.getPrenom()));
		
		joueur = new Joueur("abc");
		assertTrue("abc".equals(joueur.getPrenom()));
	}
	
	/**
	 * Test de la fonction getCouleur, test par le même temps la fonction setCouleur
	 */
	@Test
	public void testGetCouleur() {
		Joueur joueur = new Joueur("test");
		assertNull(joueur.getCouleur());
		
		joueur.setCouleur(Piece.BLANC);
		assertEquals(Piece.BLANC, joueur.getCouleur());
	}

}
