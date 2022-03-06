package application;




import java.io.FileInputStream;
import java.io.IOException; 
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

public class LoginController {
	OracleDataSource ods;
	static Connection conn = null;    
		@FXML
	    private TextField username;
	    
	    @FXML
	    private PasswordField password;

	    @FXML
	    private Button pulsanteLogin;
	    
	    @FXML
	    void login(ActionEvent event) throws IOException {
	    	
	    	String usernameInserito = username.getText(); 
	    	String passwordInserita = password.getText();
	    	if(inizializzaConnessione(usernameInserito, passwordInserita))
	    		cambiaScena(Main.getStageFromEvent(event));
	    }
	    
	    
	public boolean inizializzaConnessione(String username, String password)  {
		
		
		try {
			ods = new OracleDataSource();
		} catch (SQLException e1) {
			Messaggi.MessaggioErroriConnessioneNonRiuscita();
			return false;
		}
		
		String url="jdbc:oracle:thin:@//localhost:1521/xe";
		ods.setURL(url);
		ods.setUser(username);
		ods.setPassword(password);
				
		try {
			conn = ods.getConnection();
		} catch (SQLException e) {
			Messaggi.MessaggioErroreException(e);
			pulisciCampoPassword();
			pulisciCampoUsername();
			return false;
		}
 
		return true;	
	}
	
	public static void logoutDataBase() {
		try {
			conn.close();
		} catch (SQLException e) {
			Messaggi.MessaggioErroreGenerico();
			e.printStackTrace();
		}
	}
	
	public void pulisciCampoUsername() {
		username.clear();
	}
	
	public void pulisciCampoPassword() {
		password.clear();
	}
	
	
	public void cambiaScena(Stage stage) throws IOException {
		stage.hide();
		Stage root = new Stage();
		AnchorPane secondaFinestra = FXMLLoader.<AnchorPane>load(getClass().getResource("SecondaScena.fxml"));
		Scene secondScene = new Scene(secondaFinestra,1000,600);
		root.setScene(secondScene);
		root.getIcons().add(new Image(new FileInputStream("src\\application\\Immagini\\logocolorato.png")));
		root.setTitle("Database monitoraggio pandemia");
		root.show();
	}
	
	public static Connection getConnection() {
        return conn;
    }
	
	
}

