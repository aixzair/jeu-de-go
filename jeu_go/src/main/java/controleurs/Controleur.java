package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import application.Partie;
import javafx.fxml.Initializable;

/**
 * Controleur principal de l'application
 * @author alexl
 *
 */
public class Controleur
implements Initializable {
	private final Partie partie;
	
	public Controleur(Partie partie) {
		this.partie = partie;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
