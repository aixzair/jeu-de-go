package modeles;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class JoueurTest {

	@Test
	public void testJoueur() {
		Joueur joueur = new Joueur("test");
		
		assertTrue(joueur instanceof Joueur);
	}

}
