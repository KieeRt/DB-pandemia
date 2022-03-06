package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class Messaggi{

	public static void MessaggioErroriCredenziali() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Messaggio di warning");
		String s = "Username o password non corretti, riprovare ";
		alert.setContentText(s);
		alert.show();

	}

	public static void MessaggioErroriConnessioneNonRiuscita() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Errore");
		alert.setHeaderText("Messaggio di errore");
		String s = "Connessione non riuscita, riprovare ";
		alert.setContentText(s);
		alert.show();
	}

	public static void MessaggioErroreGenerico() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Errore");
		alert.setHeaderText("Messaggio di errore");
		String s = "Errore !!";
		alert.setContentText(s);
		alert.show();
	}
	
	public static void MessaggioConnessioneRiuscita() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("OK");
		alert.setHeaderText("INFO");
		String s = "Connessione riuscita !!";
		alert.setContentText(s);
		alert.show();
	}
	
	public static void MessaggioErroreException(Exception e) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Warning");
		alert.setHeaderText("Messaggio di warning");
		String s = e.getMessage();
		alert.setContentText(s);
		alert.show();
	}
}
