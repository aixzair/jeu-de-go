/**
 * 
 */
package modeles;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * @author alexl
 *
 */
class PlateauTest {

	/**
	 * Test method for {@link modeles.Plateau#Plateau(modeles.Joueur, modeles.Joueur)}.
	 */
	@Test
	public void testPlateau() {
		Joueur j1 = new Joueur("a");
		Joueur j2 = new Joueur("b");
		Plateau plateau = new Plateau(j1, j2);
		
		assertNotNull(plateau);
		assertTrue(plateau instanceof Plateau);
		
		assertEquals(j1, plateau.getJoueurActuel());
		plateau.passerSonTour();
		assertEquals(j2, plateau.getJoueurActuel());
	}

	/**
	 * Test method for {@link modeles.Plateau#estCaseLibre(int, int)}.
	 */
	@Test
	public void testEstCaseLibre() {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		
		int x = 1;
		int y = 2;
		
		assertTrue(plateau.estCaseLibre(y, x));
		
		try {
			plateau.poserPiece(new Coordonnee(x, y));
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
		
		assertFalse(plateau.estCaseLibre(y, x));
	}

	/**
	 * Test method for {@link modeles.Plateau#getJoueurActuel()}.
	 */
	@Test
	public void testGetJoueurActuel() {
		Joueur j1 = new Joueur("a");
		Joueur j2 = new Joueur("b");
		Plateau plateau = new Plateau(j1, j2);
		
		assertNotNull(plateau);
		assertTrue(plateau instanceof Plateau);
		
		assertEquals(j1, plateau.getJoueurActuel());
		plateau.passerSonTour();
		assertEquals(j2, plateau.getJoueurActuel());
	}

	/**
	 * Test method for {@link modeles.Plateau#getGagnant()}.
	 */
	@Test
	public void testGetGagnant() {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		
		assertNull(plateau.getGagnant());
	}

	/**
	 * Test method for {@link modeles.Plateau#getTaille()}.
	 */
	@Test
	public void testGetTaille() {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		
		assertEquals(3, plateau.getTaille());
		
		// TODO Continuer lors d'ajout de la méthodes setTaille
	}

	/**
	 * Test method for {@link modeles.Plateau#estTerminee()}.
	 */
	@Test
	public void testEstTerminee() {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		
		assertFalse(plateau.estTerminee());
		
		// TODO à compléter lorsque les fonctions de fin de partie seront implémenté
	}

	/**
	 * Test method for {@link modeles.Plateau#getPiece(int, int)}.
	 */
	@Test
	public void testGetPiece() {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		
		int x = 1;
		int y = 2;
		
		assertEquals(Piece.AUCUNE, plateau.getPiece(x, y));
		
		try {
			plateau.poserPiece(new Coordonnee(x, y));
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
		
		assertEquals(Piece.BLANC, plateau.getPiece(x, y));
	}

	/**
	 * Test method for {@link modeles.Plateau#getPieces()}.
	 */
	@Test
	public void testGetPieces() {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		Piece pieces[][] = plateau.getPieces().clone();
		
		try {
			plateau.poserPiece(new Coordonnee(0, 0));
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
				
		assertNotEquals(pieces, plateau.getPieces());
	}

	/**
	 * Test method for {@link modeles.Plateau#getTour()}.
	 */
	@Test
	public void testGetTour() {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		
		assertEquals(1, plateau.getTour());
		plateau.passerSonTour();
		assertEquals(1, plateau.getTour());
		plateau.passerSonTour();
		assertEquals(2, plateau.getTour());
		
	}

	/**
	 * Test method for {@link modeles.Plateau#poserPiece(modeles.Coordonnee)}.
	 */
	@Test
	public void testPoserPiece() {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		
		int x = 1;
		int y = 2;
		
		assertEquals(Piece.AUCUNE, plateau.getPiece(x, y));
				
		try {
			plateau.poserPiece(new Coordonnee(x, y));
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
		
		assertEquals(Piece.BLANC, plateau.getPiece(x, y));
	}

	/**
	 * Test method for {@link modeles.Plateau#passerSonTour()}.
	 */
	@Test
	public void testPasserSonTour() {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		Piece pieces [][] = plateau.getPieces();
		
		plateau.passerSonTour();
		assertEquals(pieces, plateau.getPieces());
	}
	
	// -- Test méthodes privées --
	
	/**
	 * Test method for {@link modeles.Plateau#memePiece()}.
	 * @throws Exception
	 */
	@Test
	public void testMemePiece_private()
	throws Exception {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		Class<Plateau> plateau_class = Plateau.class;
        Method memePiece = plateau_class.getDeclaredMethod( "memePiece", Coordonnee.class, Coordonnee.class );
        
        Coordonnee coordonnee1 = new Coordonnee(0, 0);
        Coordonnee coordonnee2 = new Coordonnee(0, 1);
        
        memePiece.setAccessible(true);
        
        // Plateau vide -> même pièce
        assertTrue( (boolean) memePiece.invoke(plateau, coordonnee1, coordonnee2));
        
        try {
			plateau.poserPiece(new Coordonnee(0, 0));
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        
        // Une pièce blanche et une autre vide
        assertFalse( (boolean) memePiece.invoke(plateau, coordonnee1, coordonnee2));
	}
	
	
	/**
	 * Test method for {@link modeles.Plateau#remplirGroupe()}.
	 * @throws Exception
	 */
	@Test
	public void testRemplirGroupe_private()
	throws Exception{
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		
		ArrayList<Coordonnee> liste = new ArrayList<Coordonnee>();
        Coordonnee coordonnee1 = new Coordonnee(0, 0);
        Coordonnee coordonnee2 = new Coordonnee(0, 1);
		
		Class<Plateau> plateau_class = Plateau.class;
        Method remplirGroupe = plateau_class.getDeclaredMethod(
        	"remplirGroupe",
        	ArrayList.class,
        	int.class
        );
        remplirGroupe.setAccessible(true);
        
        // T1 : Plateau vide
        remplirGroupe.invoke(plateau, liste, 0);
        assertTrue(liste.isEmpty());
        
        // T2 : Plateau avec une seule pièce
        try {
			plateau.poserPiece(coordonnee1);
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        liste.add(coordonnee1);
        remplirGroupe.invoke(plateau, liste, 0);
        assertEquals(1, liste.size());
        
        // T3 : Plateau avec deux pièces blanches
        plateau.passerSonTour();
        try {
			plateau.poserPiece(coordonnee2);
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        remplirGroupe.invoke(plateau, liste, 0);
        assertEquals(2, liste.size());
        assertTrue(liste.contains(coordonnee2));
	}

}
