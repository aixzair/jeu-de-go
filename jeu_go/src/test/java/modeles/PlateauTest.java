/**
 * 
 */
package modeles;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
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
		try {
			plateau.passerSonTour();
		} catch (PartieTerminerException e) {
			e.printStackTrace();
		}
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
		} catch (Exception exception) {
			exception.printStackTrace();
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
		try {
			plateau.passerSonTour();
		} catch (PartieTerminerException exception) {
			exception.printStackTrace();
		}
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
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		assertEquals(Piece.BLANC, plateau.getPiece(x, y));
	}

	/**
	 * Test method for {@link modeles.Plateau#getPieces()}.
	 */
	@Test
	public void testGetPieces()
	throws Exception {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		Piece pieces[][] = plateau.getPieces().clone();
		
		plateau.poserPiece(new Coordonnee(0, 0));
		assertNotEquals(pieces, plateau.getPieces());
	}

	/**
	 * Test method for {@link modeles.Plateau#getTour()}.
	 * @throws PartieTerminerException 
	 */
	@Test
	public void testGetTour()
	throws PartieTerminerException {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		
		assertEquals(1, plateau.getTour());
		plateau.passerSonTour();
		assertEquals(1, plateau.getTour());
		plateau.passerSonTour();
		assertEquals(2, plateau.getTour());
		
	}

	/**
	 * Test method for {@link modeles.Plateau#poserPiece(modeles.Coordonnee)}.
	 * @throws PartieTerminerException 
	 */
	@Test
	public void testPoserPiece()
	throws Exception {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		
		int x = 1;
		int y = 2;
		
		assertEquals(Piece.AUCUNE, plateau.getPiece(x, y));
				
		plateau.poserPiece(new Coordonnee(x, y));
		assertEquals(Piece.BLANC, plateau.getPiece(x, y));
	}

	/**
	 * Test method for {@link modeles.Plateau#passerSonTour()}.
	 * @throws PartieTerminerException 
	 */
	@Test
	public void testPasserSonTour()
	throws PartieTerminerException {
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
        
        Coordonnee blanc1 = new Coordonnee(0, 0);
        Coordonnee blanc2 = new Coordonnee(0, 1);
        Coordonnee noire1 = new Coordonnee(1, 0);
        Coordonnee noire2 = new Coordonnee(1, 1);
        
        memePiece.setAccessible(true);
        
        // T1 : aucune et aucune
        assertTrue( (boolean) memePiece.invoke(plateau, new Coordonnee(0, 0), new Coordonnee(0, 1)));
        
        // T2 : blanc et aucune
        try {
			plateau.poserPiece(blanc1);
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        assertFalse( (boolean) memePiece.invoke(plateau, blanc1, new Coordonnee(0, 1)));
        
        // T3 : noire et aucune
        try {
			plateau.poserPiece(noire1);
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        assertFalse( (boolean) memePiece.invoke(plateau, noire1, new Coordonnee(0, 1)));
        
        // T4 : noire et blanc
        assertFalse( (boolean) memePiece.invoke(plateau, blanc1, noire1));
        
        // T5 : blanc et noire
        assertFalse( (boolean) memePiece.invoke(plateau, noire1, blanc1));
        
        // T6 : noire et noire
        try {
			plateau.poserPiece(blanc2);
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        assertTrue( (boolean) memePiece.invoke(plateau, blanc1, blanc2));
       
        // T7 : noire et noire
        try {
			plateau.poserPiece(noire2);
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        assertTrue( (boolean) memePiece.invoke(plateau, noire1, noire2));
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
        
        // T4 : Plateau avec deux pièces blanches et une pièce noire
        try {
			plateau.poserPiece(new Coordonnee(1, 0));
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        remplirGroupe.invoke(plateau, liste, 0);
        assertEquals(2, liste.size());
        assertTrue(liste.contains(coordonnee2));
	}
	
	/**
	 * Test method for {@link modeles.Plateau#trouverGroupes()}.
	 * @throws Exception
	 */
	@Test
	public void testTrouverGroupes_private()
	throws Exception {
		Plateau plateau = new Plateau(new Joueur("a"), new Joueur("b"));
		ArrayList<ArrayList<Coordonnee>> groupes;
		
		Coordonnee blanc1 = new Coordonnee(0, 0);
        Coordonnee blanc2 = new Coordonnee(0, 1);
        Coordonnee blanc3 = new Coordonnee(2, 2);
        Coordonnee noire1 = new Coordonnee(1, 0);
        Coordonnee noire2 = new Coordonnee(1, 1);
		
		Class<Plateau> plateau_class = Plateau.class;
		Field groupes_field = plateau_class.getDeclaredField("groupes");
        Method trouverGroupe = plateau_class.getDeclaredMethod("trouverGroupes");
        
        groupes_field.setAccessible(true);
        trouverGroupe.setAccessible(true);
        
        // T1 : Plateau vide.
        trouverGroupe.invoke(plateau);
        groupes = (ArrayList<ArrayList<Coordonnee>>) groupes_field.get(plateau);
        assertTrue(groupes.isEmpty());
        
        // T2 : Plateau avec une pièce blanche
        try {
			plateau.poserPiece(blanc1);
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        trouverGroupe.invoke(plateau);
        groupes = (ArrayList<ArrayList<Coordonnee>>) groupes_field.get(plateau);
        assertEquals(blanc1, groupes.get(0).get(0));
        
        // T3 : Plateau avec une pièce blanche et une pièce noire
        try {
			plateau.poserPiece(noire1);
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        trouverGroupe.invoke(plateau);
        groupes = (ArrayList<ArrayList<Coordonnee>>) groupes_field.get(plateau);
        assertEquals(blanc1, groupes.get(0).get(0));
        assertEquals(noire1, groupes.get(1).get(0));
        
        // T4 : Plateau -> blanc 1, noire 1, blanc 1
        try {
			plateau.poserPiece(blanc3);
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        trouverGroupe.invoke(plateau);
        groupes = (ArrayList<ArrayList<Coordonnee>>) groupes_field.get(plateau);
        assertEquals(blanc1, groupes.get(0).get(0));
        assertEquals(noire1, groupes.get(1).get(0));
        assertEquals(blanc3, groupes.get(2).get(0));
        
        // T5 : Plateau -> blanc 1, noire 2, blanc 1
        try {
			plateau.poserPiece(noire2);
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        trouverGroupe.invoke(plateau);
        groupes = (ArrayList<ArrayList<Coordonnee>>) groupes_field.get(plateau);
        assertEquals(blanc1, groupes.get(0).get(0));
        assertEquals(noire1, groupes.get(1).get(0));
        assertEquals(noire2, groupes.get(1).get(1));
        assertEquals(blanc3, groupes.get(2).get(0));
        
        // T6 : Plateau -> blanc 2, noire 2, blanc 1
        try {
			plateau.poserPiece(blanc2);
		} catch (CaseNonVideException e) {
			e.printStackTrace();
		}
        trouverGroupe.invoke(plateau);
        groupes = (ArrayList<ArrayList<Coordonnee>>) groupes_field.get(plateau);
        assertEquals(blanc1, groupes.get(0).get(0));
        assertEquals(blanc2, groupes.get(0).get(1));
        assertEquals(noire1, groupes.get(1).get(0));
        assertEquals(noire2, groupes.get(1).get(1));
        assertEquals(blanc3, groupes.get(2).get(0));
	}

}
