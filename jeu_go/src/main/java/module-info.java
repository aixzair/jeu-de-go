/**
 * @author alexl
 *
 */
module jeu_go {
	// Java Fx
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;

	opens application to javafx.fxml;
	exports application;
	opens controleurs to javafx.fxml;
	exports controleurs;
	opens modeles to javafx.fxml;
	exports modeles;
  	
}