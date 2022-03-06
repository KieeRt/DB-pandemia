package application;

	



/* 
 * Statement stmt; stmt = conn.createStatement(); ResultSet rset = 
 * stmt.executeQuery("SELECT Nome,Cognome FROM Persona"); while (rset.next()) {
 * 
 * }
 */

import javafx.application.Application;
import java.io.FileInputStream;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {	
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("LoginController.fxml"));
			Scene firstScene = new Scene(root,300,400);
			primaryStage.setScene(firstScene);
			primaryStage.getIcons().add(new Image(new FileInputStream("src\\application\\Immagini\\logocolorato.png")));
			primaryStage.setTitle("Login");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getStageFromEvent(ActionEvent event) {
		return (Stage)((Node)event.getSource()).getScene().getWindow();
	}
	
}
