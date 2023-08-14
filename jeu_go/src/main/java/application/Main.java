package application;

import java.io.IOException;

import controleurs.Controleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** 
 * @author Alexandre Lerosier
 */
public class Main
extends Application {
	private static Scene scene;
	
	private static Parent loadFXML(String fichier, Controleur controleur)
	throws IOException {
	    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fichier + ".fxml"));
	    fxmlLoader.setController(controleur);
	    return fxmlLoader.load();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage)
	throws Exception {
		Partie partie = new Partie();
		Controleur controleur = new Controleur(partie);
		
		stage.setTitle("Jeu de Go");
		stage.setScene(new Scene(Main.loadFXML("go", controleur), 640, 480));
		stage.show();
	}

}
