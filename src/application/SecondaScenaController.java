package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;




public class SecondaScenaController {
	//ToggleButtonSchermateEsterne
	@FXML 	private ToggleGroup toggleGroupClasseGenerica;
	@FXML 	private ToggleButton toggleButtonPersona;
	@FXML 	private ToggleButton toggleButtonLuogo;
	@FXML 	private ToggleButton toggleButtonControlloMedico;
	@FXML 	private ToggleButton toggleButtonIncontri;
	@FXML 	private ToggleButton toggleButtonVaccino;
	
	//RadioButtonSchermateInterne
	@FXML	private ToggleGroup toggleGroupSelezioneCampiPersonaGenerica;
	@FXML	private RadioButton radioBDatiPersonali;
	@FXML	private RadioButton radioBParentela;
	@FXML	private RadioButton radioBStatoDellaSalute;
	@FXML	private RadioButton radioBDatiPersonaliStudente;
	@FXML	private RadioButton radioBCompagniStudente;
	@FXML	private RadioButton radioBDatiPersonaliLavoratore;
	@FXML	private RadioButton radioBColleghiLavoratore;
	@FXML	private RadioButton radioBDatiLuogo;
	@FXML	private RadioButton radioBGestioneLuogo;
	@FXML	private RadioButton radioBAmbienteLavoro;
	@FXML	private RadioButton radioBAmbienteEventoOccasionale;
	@FXML	private RadioButton radioBAmbienteFamiliare;
	@FXML	private RadioButton radioBDatiControlloMedico;
	@FXML	private RadioButton radioBDatiIncontroVisita;
	@FXML	private RadioButton radioBLuoghiAbitudiniLavorative;
	@FXML	private RadioButton radioBLuoghiIstituzionaliFrequentati;
	@FXML	private RadioButton radioBAmbienteStudio;
	
	
	//TabPaneEsterni
	@FXML 	private TabPane schermataPersona;
	@FXML	private TabPane schermataLuogo;
	@FXML	private TabPane schermataControlloMedico;
	@FXML	private TabPane schermataGestioneIncontri;
	@FXML	private TabPane schermataVaccino;
	
	//Campi Persona
	@FXML 	private DatePicker textDataNascitaPersona;
	@FXML 	private TextField textFieldCognomePersona;
	@FXML	private TextField textFieldNomePersona;
	@FXML	private TextField textComuneNascitaPersona;
	@FXML	private TextField textFieldCFPersona;
	@FXML	private ToggleGroup toggleGroupSessoPersonaGenerico;
	@FXML	private RadioButton tbMaschio;
	@FXML	private RadioButton tbFemmina;
	@FXML	private TextField textFieldMail2Persona;
	@FXML	private TextField textFieldMail1Persona;
	@FXML	private TextField textFieldTelefono1Persona;
	@FXML	private TextField textFieldTelefono2Persona;
	
	//Campi Parentela
	@FXML	private TextField textFieldPersonaCognomeParentela1;
	@FXML	private TextField textFieldPersonaNomeParentela1;
	@FXML	private TextField textFieldPersonaCognomeParentela2;
	@FXML	private TextField textFieldPersonaNomeParentela2;
	@FXML	private ChoiceBox<String> sceltaParentela;
	@FXML	private TextField textFieldPersonaCFParentela2;
	@FXML	private TextField textFieldPersonaCFParentela1;
	
	//Campi stato salute
	@FXML	private TextField textFieldPersonaCognomeStatoSalute;
	@FXML	private TextField textFieldPersonaNomeStatoSalute;
	@FXML	private TextField textFieldPersonaCFStatoSalute;
	@FXML	private TextField textFieldPersonaMalattieCronicheStatoSalute;
	@FXML	private DatePicker dataSintomiDatePickerStatoSalute;
	@FXML	private DatePicker textDataTamponeEseguito;
	@FXML	private ToggleGroup toggleGroupTamponeEseguito;
	@FXML	private RadioButton tbTamponeEseguitoSi;
	@FXML	private RadioButton tbTamponeEseguitoNo;
	@FXML	private ToggleGroup toggleGroupQuarantena;
	@FXML	private RadioButton tbQuarantenaSi;
	@FXML	private RadioButton tbQuarantenaNo;
	@FXML	private ChoiceBox<String> sceltaSintomi;
	
	
	//Campi Studente
	@FXML	private ToggleGroup toggleGroupFrequentaInPresenza;
	@FXML	private RadioButton tbFrequentaInPresenzaSi;
	@FXML	private RadioButton tbFrequentaInPresenzaNo;
	@FXML	private TextField textCodiceFiscaleStudente;
	@FXML	private TextField textNomeStudente;
	@FXML	private TextField textCognomeStudente;
	
	//Campi compagno
	@FXML	private TextField textCodiceFiscaleCompagno1;
	@FXML	private TextField textNomeCompagno1;
	@FXML	private TextField textCognomeCompagno1;
	@FXML	private TextField textCodiceFiscaleCompagno2;
	@FXML	private TextField textNomeCompagno2;
	@FXML	private TextField textCognomeCompagno2;
	
	//Campi lavoratore
	@FXML	private TextField textCodiceFiscaleLavoratore;
	@FXML	private TextField textNomeLavoratore;
	@FXML	private TextField textCognomeLavoratore;
	@FXML	private TextField textNomeSocieta;
	@FXML	private ChoiceBox<String> tipoContrattoChoiceBox;
	@FXML	private CheckBox checkBoxLunediLavoratore;
	@FXML	private CheckBox checkBoxVenerdiLavoratore;
	@FXML	private CheckBox checkBoxGiovediLavoratore;
	@FXML	private CheckBox checkBoxMercolediLavoratore;
	@FXML	private CheckBox checkBoxMartediLavoratore;
	@FXML	private CheckBox checkBoxSabatoLavoratore;
	@FXML	private CheckBox checkBoxDomenicaLavoratore;
	
	//Campi colleghi
	@FXML	private TextField textCodiceFiscaleCollega1;
	@FXML	private TextField textNomeCollega1;
	@FXML	private TextField textCognomeCollega1;
	@FXML	private TextField textCodiceFiscaleCollega2;
	@FXML	private TextField textNomeCollega2;
	@FXML	private TextField textCognomeCollega2;
	
	//Campi luogo
	@FXML	private TextField textCityDatiLuogo;
	@FXML	private TextField textProvinciaDatiLuogo;
	@FXML	private TextField textViaDatiLuogo;
	@FXML	private TextField textNumeroCivicoDatiLuogo;
	@FXML	private TextField textCAPDatiLuogo;
	@FXML	private TextField textIDLuogo;
	
	//Campi gestione luogo
	@FXML	private TextField textNomeGestioneLuogo;
	@FXML	private TextField textCognomeGestioneLuogo;
	@FXML	private TextField textCodiceFiscaleGestioneLuogo;
	@FXML	private TextField textViaGestioneLuogo;
	@FXML	private TextField textNumCivicoGestioneLuogo;
	@FXML	private TextField textIDLuogoGestioneLuogo;
	
	//Campi Ambiente lavoro
	@FXML	private TextField textViaAmbieneLavoro;
	@FXML	private TextField textNumCivicoAmbienteLavoro;
	@FXML	private TextField textIDLuogoAmbienteLavoro;
	@FXML	private TextField textIDAmbienteLavoro;
	@FXML	private TextField textOrarioAAmbienteLavoro;
	@FXML	private TextField textOrarioCAmbienteLavoro;
	@FXML	private ComboBox<Integer> choicheBoxNumDipendentiAmbieneLavoro;
	
	//Campi Ambiente studio
	@FXML	private TextField textViaAmbienteStudio;
	@FXML	private TextField textNumCivicoAmbienteStudio;
	@FXML	private TextField textIDLuogoAmbienteStudio;
	@FXML	private ChoiceBox<String> choicheBoxTipoAmbienteStudio;
	@FXML	private TextField textIDAmbienteStudio;
	
	//Campi aula
	@FXML	private TextField textIDAmbienteStudioAula;
	@FXML	private RadioButton radioBAula;
	@FXML	private TextField textID_Aula;
	@FXML	private ComboBox<Integer> choicheBoxNumPostiAula;
	
	//Campi Ambiente evento occasionale
	@FXML	private TextField textIDAmbienteEventoOccasionale;
	@FXML	private TextField textViaAmbienteEventoOccasionale;
	@FXML	private TextField textNumCivicoAmbienteEventoOccasionale;
	@FXML	private TextField textIDLuogoAmbienteEventoOccasionale;
	@FXML	private TextField textDimensioniAmbienteEventoOccasionale;
	@FXML	private ToggleGroup toggleGroupLuogoApertoEventoOccasionale;
	@FXML	private RadioButton radioBLuogoApertoSi;
	@FXML	private RadioButton radioBLuogoApertoNo;
	@FXML	private ComboBox<Integer> choicheBoxNumPartecipantiAmbienteEventoOccasionale;
	
	//Campi Ambiente familiare
	@FXML	private TextField textViaAmbienteFamiliare;
	@FXML	private TextField textNumCivicoAmbienteFamiliare;
	@FXML	private TextField textIDLuogoAmbienteFamiliare;
	@FXML	private TextField textDimensioneAmbienteFamiliare;
	@FXML	private ChoiceBox<String> choicheBoxTipoAmbienteFamiliare;
	@FXML	private TextField textIDAmbienteFamiliare;
	
	//Campi Controllo medico
	@FXML	private TextField textNomeMedicoControlloMedico;
	@FXML	private TextField textNomeOspedaleControlloMedico;
	@FXML	private TextField textNomeControlloMedico;
	@FXML	private TextField textCognomeControlloMedico;
	@FXML	private TextField textCodiceFiscaleControlloMedico;
	@FXML	private TextField textViaControlloMedico;
	@FXML	private TextField textNumCivicoControlloMedico;
	@FXML	private TextField textIDLuogoControlloMedico;
	@FXML	private DatePicker datePickerDataControlloMedico;
	@FXML	private ChoiceBox<String> choiceBoxTipoControlloMedico;
	@FXML	private ChoiceBox<String> choiceBoxEsitoControlloMedico;
	@FXML	private TextField textIDControlloMedico;
	
	//Campi incontro
	@FXML	private TextField textNomeDatiIncontro;
	@FXML	private TextField textCognomeDatiIncontro;
	@FXML	private TextField textCodiceFiscaleDatiIncontro;
	@FXML	private TextField textViaDatiIncontro;
	@FXML	private TextField textNumCivicoDatiIncontro;
	@FXML	private TextField textIDLuogoDatiIncontro;
	@FXML	private TextField textorarioDatiIncontro;
	@FXML	private DatePicker datePickerDataDatiIncontro;
	
	// CAMPI frequentazione istituzionale 
	@FXML	private TextField textNomeLuoghiIST;
	@FXML	private TextField textCognomeLuoghiIST;
	@FXML	private TextField textIDStudenteLuoghiIST;
	@FXML 	private TextField textViaLuoghiIST;
	@FXML 	private TextField textNumeroCivicoLuoghiIST;
	@FXML 	private TextField textIDAmbienteStudioLuoghiIST;


	// Campi Abitudini lavoro
	@FXML 	private TextField textNomeLuoghiLAV;
	@FXML 	private TextField textCognomeLuoghiLAV;
	@FXML 	private TextField textIDLavoratoreLuoghiLAV;
	@FXML 	private TextField textViaLuoghiLAV;
	@FXML 	private TextField textNumeroCivicoLuoghiLAV;
	@FXML 	private TextField textIDAmbienteLavorativoLuoghiLAV;
	@FXML 	private TextField textOrarioIngressoLuoghiLAV;
	@FXML 	private TextField textOrarioUscitaLuoghiLAV;
	@FXML	private DatePicker datePickerLuoghiLAV;

	//Campi vaccino
	@FXML 	private RadioButton radioBDatiVaccino;
	@FXML 	private TextField textIDVaccinoDatiVaccino;
	@FXML 	private TextField textEnteRilascioDatiVaccino;
	@FXML 	private TextField textControindicazioniDatiVaccino;
	@FXML 	private ChoiceBox<String> tipoVaccinoChoiceBox;
	@FXML 	private ChoiceBox<Integer> periodoUtileChoiceBox;
	@FXML 	private Label labelHelpIDVaccinoDatiVaccino;
	@FXML 	private TextArea textHelpIDVaccinoDatiVaccino;

	//Campi Somministrazione Vaccino
	@FXML 	private RadioButton radioBSomministrazioneVaccino;
	@FXML 	private TextField textNomeSomministrazioneVaccino;
	@FXML 	private TextField textCognomeSomministrazioneVaccino;
	@FXML 	private TextField textCodiceFiscaleSomministrazioneVaccino;
	@FXML 	private TextField textIDVaccinoSomministrazioneVaccino;
	@FXML 	private TextField textIDStudenteStudente;
	@FXML 	private TextField textIDLavoratoreDatiPersonali;
	@FXML 	private TextField textFieldResidenza;
	@FXML 	private TextField textComplicazioniVaccino;
	@FXML 	private DatePicker dataSomministrazioneVaccino;
	
	//Menu bar
	@FXML	private MenuItem logoutButton;
	@FXML	private MenuItem helpButton;
	
	//console
	@FXML	private TextArea console2;
	
	
	// VARIABILI USATE PE Tooltip
	@FXML	private Tooltip tooltip;
	@FXML	private TextArea textHelpCFPersona;
	@FXML	private Label labelHelpCFPersona;
	@FXML	private TextArea textHelpCFStatoSalute;
	@FXML	private Label labelHelpCFStatoSalute;
	@FXML	private Label labelHelpIDStudente;
	@FXML	private TextArea textHelpIDStudente;
	@FXML	private Label labelHelpIDLavoratore;
	@FXML	private TextArea textHelpIDLavoratore;
	@FXML	private Label labelHelpIDLuogo;
	@FXML	private TextArea textHelpIDLuogo;
	@FXML	private Label labelHelpIDAmbienteLavoro;
	@FXML	private TextArea textHelpIDAmbienteLavoro;
	@FXML	private Label labelHelpIDAmbienteStudio;
	@FXML	private TextArea textHelpIDAmbienteStudio;
	@FXML	private Label labelHelpIDAula;
	@FXML	private TextArea textHelpIDAula;
	@FXML	private Label labelHelpIDAmbienteEventoOcc;
	@FXML	private TextArea textHelpIDAmbienteEventoOcc;
	@FXML	private Label labelHelpIDAmbienteFamiliare;
	@FXML	private TextArea textHelpIDAmbienteFamiliare;
	@FXML	private Label labelHelpIDControlloMedico;
	@FXML	private TextArea textHelpIDControlloMedico;
	
	// BUTTON PRINCIPALI
	@FXML 	private Button buttonAggiungi;
	@FXML	private Button buttonModifica;
	@FXML	private Button buttonRicerca;
	@FXML	private Button buttonSalva;
	@FXML	private Button buttonAnnulla;
	@FXML	private Button buttonPulisci;

	//Connessione
	Connection connection = LoginController.getConnection();

	@FXML 
	public void initialize() {
		//Inizializza cambio scena generico 
		toggleButtonPersona.setOnAction(this::apriPersonaGenerica);
		toggleButtonLuogo.setOnAction(this::apriLuogoGenerico);
		toggleButtonControlloMedico.setOnAction(this::apriControlloMedico);
		toggleButtonIncontri.setOnAction(this::apriGestioneIncontri);
		toggleButtonVaccino.setOnAction(this::apriVaccino);
		logoutButton.setOnAction(this::logout);
		helpButton.setOnAction(this::openHelp);
		setAllInvisible();

		//Inizializza scena con PERSONA GENERICA Selezionato
		radioBDatiPersonali.setOnAction(this::abilitaCampiDatiPersonali);
		radioBParentela.setOnAction(this::abilitaCampiParentela);
		radioBStatoDellaSalute.setOnAction(this::abilitaCampiStatoSalute);
		radioBDatiPersonaliStudente.setOnAction(this::abilitaCampiDatiPersonaliStudente);	
		radioBCompagniStudente.setOnAction(this::abilitaCampiDatiCompagni);
		radioBDatiPersonaliLavoratore.setOnAction(this::abilitaCampiDatiPersonaliLavoratore);
		radioBColleghiLavoratore.setOnAction(this::abilitaCampiDatiColleghi);


		//Inizializza scena con LUOGO GENERICO Selezionato
		radioBDatiLuogo.setOnAction(this::abilitaCampiLuogo);
		radioBAmbienteLavoro.setOnAction(this::abilitaCampiAmbienteLavoro);
		radioBAmbienteStudio.setOnAction(this::abilitaCampiAmbienteStudio);
		radioBAmbienteFamiliare.setOnAction(this::abilitaCampiAmbienteFamiliare);
		radioBAmbienteEventoOccasionale.setOnAction(this::abilitaCampiAmbienteEventoOccasionale);
		radioBGestioneLuogo.setOnAction(this::abilitaCampiGestione);
		radioBAula.setOnAction(this::abilitaCampiAula);

		//Inizializza scena con CONTROLLO MEDICO Selezionato
		radioBDatiControlloMedico.setOnAction(this::abilitaCampiControlloMedico);

		//Inizializza scena con INCONTRI Selezionato
		radioBDatiIncontroVisita.setOnAction(this::abilitaCampiVisita);
		radioBLuoghiIstituzionaliFrequentati.setOnAction(this::abilitaCampiFrequentazioneIstituzionale);
		radioBLuoghiAbitudiniLavorative.setOnAction(this::abilitaCampiAbitudiniLavorativi);

		//Inizializza scena con VACCINO
		radioBDatiVaccino.setOnAction(this::abilitaCampiDatiVaccino);
		radioBSomministrazioneVaccino.setOnAction(this::abilitaCampiSomministrazioneVaccino);

		//Enumerazione
		inizializzaEnumerazioni();

		//Vincoli di Ennupla
		inizializzaVincoliEnnupla();

		//Vincoli di Dominio
		inizializzaTextFieldNumerico();


		inizializzaDate();
		inizializzaTooltip();  	    

		//textOrarioAAmbienteLavoro.setTextFormatter(new TextFormatter(rejectChange));
	
		buttonAggiungi.setOnAction(this::aggiungi);
		buttonModifica.setOnAction(this::modifica);
		buttonSalva.setOnAction(this::salva);
		buttonRicerca.setOnAction(this::ricerca);
		buttonAnnulla.setOnAction(this::annulla);
		buttonPulisci.setOnAction(this::pulisci);
		

	}



	// APERTURA FINESTRE  

	//Apertura Persona
	void apriPersonaGenerica(ActionEvent event) {
		if(!toggleButtonPersona.isSelected()) {
			schermataPersona.setVisible(false);
			radioBDatiPersonali.setSelected(false);

		}
		else {
			setAllInvisible();
			schermataPersona.setVisible(true);
			radioBDatiPersonali.setSelected(true);
			abilitaCampiDatiPersonali(event);

		}
	}

	//Apertura Luogo
	void apriLuogoGenerico(ActionEvent event) {
		if(!toggleButtonLuogo.isSelected()) {
			schermataLuogo.setVisible(false);
			radioBDatiLuogo.setSelected(false);
		}
		else {
			setAllInvisible();
			schermataLuogo.setVisible(true);
			radioBDatiLuogo.setSelected(true);
			abilitaCampiLuogo(event);	

		}
	}

	//Apertura Controllo medico
	void apriControlloMedico(ActionEvent event) {
		if(!toggleButtonControlloMedico.isSelected()) {
			schermataControlloMedico.setVisible(false);
			radioBDatiControlloMedico.setSelected(false);
		}
		else {
			setAllInvisible();
			schermataControlloMedico.setVisible(true);
			radioBDatiControlloMedico.setSelected(true);
			abilitaCampiControlloMedico(event);

		}
	}

	//Apertura Incontri
	void apriGestioneIncontri(ActionEvent event) {
		if(!toggleButtonIncontri.isSelected()) {
			schermataGestioneIncontri.setVisible(false);
			radioBDatiIncontroVisita.setSelected(false);
		}
		else {
			setAllInvisible();
			schermataGestioneIncontri.setVisible(true);
			radioBDatiIncontroVisita.setSelected(true);
			abilitaCampiVisita(event);
		}
	}

	//Apertura Vaccino
	void apriVaccino(ActionEvent event) {
		if(!toggleButtonVaccino.isSelected()) {
			schermataVaccino.setVisible(false);
			radioBDatiVaccino.setSelected(false);
		}
		else {
			setAllInvisible();
			schermataVaccino.setVisible(true);
			radioBDatiVaccino.setSelected(true);
			abilitaCampiDatiVaccino(event);
		}
	}

	// NASCODE FINESTRE
	public void setAllInvisible() {
		schermataPersona.setVisible(false);
		schermataLuogo.setVisible(false);
		schermataGestioneIncontri.setVisible(false);
		schermataControlloMedico.setVisible(false);    
		schermataVaccino.setVisible(false);
	}



	// SCELTA ESCLUSIVA CAMPI PERSONA - STUDENTE - LAVORATORE	

	public void abilitaCampiStatoSalute(ActionEvent actionevent) {
		disableAll();
		setSituazioneStandard();
		ableCampiStatoSalute();
	
	}


	public void abilitaCampiParentela(ActionEvent actionevent) {
		disableAll();
		setSituazioneStandard();
		buttonModifica.setDisable(true);
		ableCampiParentela();
	}


	public void abilitaCampiDatiPersonali(ActionEvent actionevent) {
		disableAll();
		setSituazioneStandard();
		ableCampiDatiPersonali();
	}

	public void abilitaCampiDatiPersonaliStudente(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		ableCampiDatiPersonaliStudente();

	}

	public void abilitaCampiDatiCompagni(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		buttonModifica.setDisable(true);
		ableCampiCompagni();

	}

	public void abilitaCampiDatiPersonaliLavoratore(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		ableCampiDatiPersonaliLavoratore();

	}
	public void abilitaCampiDatiColleghi(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		buttonModifica.setDisable(true);
		ableCampiDatiColleghi();

	}


	// SCELTA ESCLUSIVA CAMPI LUOGO 
	public void abilitaCampiLuogo(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		ableCampiDatiLuogo();

	}

	public void abilitaCampiAmbienteLavoro(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		ableCampiDatiAmbienteLavoro();

	}

	public void abilitaCampiAmbienteStudio(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		ableCampiDatiAmbienteStudio();

	}

	public void abilitaCampiAula(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		ableCampiAula();

	}

	public void abilitaCampiAmbienteFamiliare(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		ableCampiDatiAmbienteFamiliare();

	}

	public void abilitaCampiAmbienteEventoOccasionale(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		ableCampiDatiEventoOccasionale();

	}

	public void abilitaCampiGestione(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		buttonModifica.setDisable(true);
		ableCampiGestione();

	}


	//SCELTA CAMPI CONTROLLO MEDICO
	public void abilitaCampiControlloMedico(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		ableCampiControlloMedico();
	}

	//SCELTA CAMPI VISITA
	public void abilitaCampiVisita(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		buttonModifica.setDisable(true);
		ableCampiVisita();
	}
	//SCELTA CAMPI Frequentazione Istituzionale
	public void abilitaCampiFrequentazioneIstituzionale(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		buttonModifica.setDisable(true);
		ableCampiFrequentazioneIstituzionale();
	}
	//Scelta campi Abitudini Lavorativi
	public void abilitaCampiAbitudiniLavorativi(ActionEvent event) {
		disableAll();
		setSituazioneStandard();
		buttonModifica.setDisable(true);
		ableCampiAbitudiniLavorativi();
	}
	//Scekta campi Dati Vaccino
	public void abilitaCampiDatiVaccino(ActionEvent event){
		disableAll();
		setSituazioneStandard();
		ableCampiDatiVaccino();

	}
	//Scelta campi Somministrazione Vaccino
	public void abilitaCampiSomministrazioneVaccino(ActionEvent event){
		disableAll();
		setSituazioneStandard();
		buttonModifica.setDisable(true);
		ableCampiSomministrazioneVaccino();
	}
	
	// DISABILITA CAMPI 
	private void disableAll() {
		buttonSalva.setDisable(true);
		buttonAnnulla.setDisable(true);
		//CAMPI PERSONA - STUDENTE - LAVORATORE


		//Campi dati personali persona
		textFieldNomePersona.setDisable(true);
		textFieldCognomePersona.setDisable(true);
		textComuneNascitaPersona.setDisable(true);
		textDataNascitaPersona.setDisable(true);
		textFieldCFPersona.setDisable(true);
		textFieldTelefono1Persona.setDisable(true);
		textFieldMail1Persona.setDisable(true);
		textFieldTelefono2Persona.setDisable(true);
		textFieldMail2Persona.setDisable(true);
		textFieldResidenza.setDisable(true);
		tbFemmina.setDisable(true);
		tbMaschio.setDisable(true);

		//Campi parentela persona
		sceltaParentela.setDisable(true);
		textFieldPersonaCFParentela1.setDisable(true);
		textFieldPersonaNomeParentela1.setDisable(true);
		textFieldPersonaCognomeParentela1.setDisable(true);
		textFieldPersonaCFParentela2.setDisable(true);
		textFieldPersonaNomeParentela2.setDisable(true);
		textFieldPersonaCognomeParentela2.setDisable(true);

		//Campi stato salute persona
		dataSintomiDatePickerStatoSalute.setDisable(true);
		textFieldPersonaCFStatoSalute.setDisable(true);
		textFieldPersonaCognomeStatoSalute.setDisable(true);
		textFieldPersonaNomeStatoSalute.setDisable(true);
		textFieldPersonaMalattieCronicheStatoSalute.setDisable(true);
		sceltaSintomi.setDisable(true);
		tbTamponeEseguitoSi.setDisable(true);
		tbTamponeEseguitoNo.setDisable(true);
		textDataTamponeEseguito.setDisable(true);
		tbQuarantenaSi.setDisable(true);
		tbQuarantenaNo.setDisable(true);

		//Campi dati personali studente
		textNomeStudente.setDisable(true);
		textCognomeStudente.setDisable(true);
		textCodiceFiscaleStudente.setDisable(true);
		tbFrequentaInPresenzaNo.setDisable(true);
		tbFrequentaInPresenzaSi.setDisable(true);
		textIDStudenteStudente.setDisable(true);

		//Campi compagni studente 
		textNomeCompagno1.setDisable(true);
		textCognomeCompagno1.setDisable(true);
		textCodiceFiscaleCompagno1.setDisable(true);
		textNomeCompagno2.setDisable(true);
		textCognomeCompagno2.setDisable(true);
		textCodiceFiscaleCompagno2.setDisable(true);

		//Campi dati personali lavoratore
		textNomeLavoratore.setDisable(true);
		textCognomeLavoratore.setDisable(true);
		textCodiceFiscaleLavoratore.setDisable(true);
		tipoContrattoChoiceBox.setDisable(true);
		checkBoxLunediLavoratore.setDisable(true);
		checkBoxMartediLavoratore.setDisable(true);
		checkBoxMercolediLavoratore.setDisable(true);
		checkBoxGiovediLavoratore.setDisable(true);
		checkBoxDomenicaLavoratore.setDisable(true);
		checkBoxVenerdiLavoratore.setDisable(true);
		checkBoxSabatoLavoratore.setDisable(true);
		textNomeSocieta.setDisable(true);

		//Campi colleghi lavoratore
		textNomeCollega1.setDisable(true);
		textCognomeCollega1.setDisable(true);
		textCodiceFiscaleCollega1.setDisable(true);
		textNomeCollega2.setDisable(true);
		textCognomeCollega2.setDisable(true);
		textCodiceFiscaleCollega2.setDisable(true);
		textIDLavoratoreDatiPersonali.setDisable(true);

		// CAMPI LUOGO GENERICO
		// Campi luogo
		textCityDatiLuogo.setDisable(true);
		textProvinciaDatiLuogo.setDisable(true);
		textViaDatiLuogo.setDisable(true);
		textNumeroCivicoDatiLuogo.setDisable(true);
		textCAPDatiLuogo.setDisable(true);
		textIDLuogo.setDisable(true);

		//Campi gestione luogo
		textCodiceFiscaleGestioneLuogo.setDisable(true);
		textCognomeGestioneLuogo.setDisable(true);
		textIDLuogoGestioneLuogo.setDisable(true);
		textNomeGestioneLuogo.setDisable(true);
		textNumCivicoGestioneLuogo.setDisable(true);
		textViaGestioneLuogo.setDisable(true);

		//Campi dati ambiente di lavoro 
		textOrarioAAmbienteLavoro.setDisable(true);
		textOrarioCAmbienteLavoro.setDisable(true);
		choicheBoxNumDipendentiAmbieneLavoro.setDisable(true);
		textIDLuogoAmbienteLavoro.setDisable(true);
		textViaAmbieneLavoro.setDisable(true);
		textNumCivicoAmbienteLavoro.setDisable(true);
		textIDAmbienteLavoro.setDisable(true);

		//Campi dati ambiente di studio
		choicheBoxTipoAmbienteStudio.setDisable(true);
		textIDAmbienteStudio.setDisable(true);
		textIDLuogoAmbienteStudio.setDisable(true);
		textNumCivicoAmbienteStudio.setDisable(true);
		textViaAmbienteStudio.setDisable(true);

		//Campi dati ambiente familiare
		textDimensioneAmbienteFamiliare.setDisable(true);
		textIDAmbienteFamiliare.setDisable(true);
		textIDLuogoAmbienteFamiliare.setDisable(true);
		textNumCivicoAmbienteFamiliare.setDisable(true);
		textViaAmbienteFamiliare.setDisable(true);
		choicheBoxTipoAmbienteFamiliare.setDisable(true);

		//Campi dati aula
		textID_Aula.setDisable(true);
		choicheBoxNumPostiAula.setDisable(true);
		textIDAmbienteStudioAula.setDisable(true);

		//Campi dati ambiente evento occasionale
		choicheBoxNumPartecipantiAmbienteEventoOccasionale.setDisable(true);
		textDimensioniAmbienteEventoOccasionale.setDisable(true);
		textIDAmbienteEventoOccasionale.setDisable(true);
		textIDLuogoAmbienteEventoOccasionale.setDisable(true);
		textNumCivicoAmbienteEventoOccasionale.setDisable(true);
		textViaAmbienteEventoOccasionale.setDisable(true);
		radioBLuogoApertoSi.setDisable(true);
		radioBLuogoApertoNo.setDisable(true);

		//CAMPI CONTROLLO MEDICO	
		//Campi controllo medico
		textIDControlloMedico.setDisable(true);
		textIDLuogoControlloMedico.setDisable(true);
		textNomeControlloMedico.setDisable(true);
		textNomeMedicoControlloMedico.setDisable(true);
		textNumCivicoControlloMedico.setDisable(true);
		textNomeOspedaleControlloMedico.setDisable(true);
		textViaControlloMedico.setDisable(true);
		textCodiceFiscaleControlloMedico.setDisable(true);
		textCognomeControlloMedico.setDisable(true);
		choiceBoxEsitoControlloMedico.setDisable(true);
		choiceBoxTipoControlloMedico.setDisable(true);
		datePickerDataControlloMedico.setDisable(true);
		//CAMPI INCOTRI
		//Campi visita
		textCodiceFiscaleDatiIncontro.setDisable(true);
		datePickerDataDatiIncontro.setDisable(true);
		textNomeDatiIncontro.setDisable(true);
		textCognomeDatiIncontro.setDisable(true);
		textorarioDatiIncontro.setDisable(true);
		textIDLuogoDatiIncontro.setDisable(true);
		textNumCivicoDatiIncontro.setDisable(true);
		textViaDatiIncontro.setDisable(true);

		//Campi Frequentazione Istituzionale
		textNomeLuoghiIST.setDisable(true);
		textCognomeLuoghiIST.setDisable(true);
		textIDStudenteLuoghiIST.setDisable(true);
		textViaLuoghiIST.setDisable(true);
		textNumeroCivicoLuoghiIST.setDisable(true);
		textIDAmbienteStudioLuoghiIST.setDisable(true);

		//Campi Abitudini Lavorativi
		textNomeLuoghiLAV.setDisable(true);
		textCognomeLuoghiLAV.setDisable(true);
		textIDLavoratoreLuoghiLAV.setDisable(true);
		textViaLuoghiLAV.setDisable(true);
		textNumeroCivicoLuoghiLAV.setDisable(true);
		textIDAmbienteLavorativoLuoghiLAV.setDisable(true);
		textOrarioIngressoLuoghiLAV.setDisable(true);
		textOrarioUscitaLuoghiLAV.setDisable(true);
		datePickerLuoghiLAV.setDisable(true);

		//Canpi Dati Vaccino
		textIDVaccinoDatiVaccino.setDisable(true);
		textEnteRilascioDatiVaccino.setDisable(true);
		textControindicazioniDatiVaccino.setDisable(true);
		tipoVaccinoChoiceBox.setDisable(true);
		periodoUtileChoiceBox.setDisable(true);

		// Campi Somministrazione Vaccino
		textNomeSomministrazioneVaccino.setDisable(true);
		textCognomeSomministrazioneVaccino.setDisable(true);
		textCodiceFiscaleSomministrazioneVaccino.setDisable(true);
		textIDVaccinoSomministrazioneVaccino.setDisable(true);
		dataSomministrazioneVaccino.setDisable(true);
		textComplicazioniVaccino.setDisable(true);
	}

	// ABILITAZIONE/SCELTA DEL CAMPO
	// CAMPI PERSONA - STUDENTE - LAVORATORE

	/********* PERSONA *********/

	private void ableCampiDatiPersonali() {
		textFieldNomePersona.setDisable(false);
		textFieldCognomePersona.setDisable(false);
		textComuneNascitaPersona.setDisable(false);
		textDataNascitaPersona.setDisable(false);
		textFieldCFPersona.setDisable(false);
		textFieldTelefono1Persona.setDisable(false);
		textFieldMail1Persona.setDisable(false);
		textFieldTelefono2Persona.setDisable(false);
		textFieldMail2Persona.setDisable(false);
		textFieldResidenza.setDisable(false);
		tbFemmina.setDisable(false);
		tbMaschio.setDisable(false);
	}


	private void ableCampiParentela() {
		sceltaParentela.setDisable(false);
		textFieldPersonaCFParentela1.setDisable(false);
		textFieldPersonaNomeParentela1.setDisable(false);
		textFieldPersonaCognomeParentela1.setDisable(false);
		textFieldPersonaCFParentela2.setDisable(false);
		textFieldPersonaNomeParentela2.setDisable(false);
		textFieldPersonaCognomeParentela2.setDisable(false);
	}


	private void ableCampiStatoSalute() {
		if(sceltaSintomi.getValue()!="Asintomatico" && sceltaSintomi.getValue() != null)
			dataSintomiDatePickerStatoSalute.setDisable(false);
		textFieldPersonaCFStatoSalute.setDisable(false);
		textFieldPersonaCognomeStatoSalute.setDisable(false);
		textFieldPersonaNomeStatoSalute.setDisable(false);
		textFieldPersonaMalattieCronicheStatoSalute.setDisable(false);
		sceltaSintomi.setDisable(false);
		tbTamponeEseguitoSi.setDisable(false);
		tbTamponeEseguitoNo.setDisable(false);

		if(tbTamponeEseguitoSi.isSelected())
			textDataTamponeEseguito.setDisable(false);
		tbQuarantenaSi.setDisable(false);
		tbQuarantenaNo.setDisable(false);
	}

	/********* STUDENTE *********/

	private void ableCampiDatiPersonaliStudente() {
		textNomeStudente.setDisable(false);
		textCognomeStudente.setDisable(false);
		textCodiceFiscaleStudente.setDisable(false);
		tbFrequentaInPresenzaNo.setDisable(false);
		tbFrequentaInPresenzaSi.setDisable(false);
		textIDStudenteStudente.setDisable(false);

	}


	private void ableCampiCompagni() {
		textNomeCompagno1.setDisable(false);
		textCognomeCompagno1.setDisable(false);
		textCodiceFiscaleCompagno1.setDisable(false);
		textNomeCompagno2.setDisable(false);
		textCognomeCompagno2.setDisable(false);
		textCodiceFiscaleCompagno2.setDisable(false);

	}

	/********* LAVORATORE *********/

	private void ableCampiDatiPersonaliLavoratore() {
		textNomeLavoratore.setDisable(false);
		textCognomeLavoratore.setDisable(false);
		textCodiceFiscaleLavoratore.setDisable(false);
		tipoContrattoChoiceBox.setDisable(false);
		checkBoxLunediLavoratore.setDisable(false);
		checkBoxMartediLavoratore.setDisable(false);
		checkBoxMercolediLavoratore.setDisable(false);
		checkBoxGiovediLavoratore.setDisable(false);
		checkBoxVenerdiLavoratore.setDisable(false);
		checkBoxSabatoLavoratore.setDisable(false);
		checkBoxDomenicaLavoratore.setDisable(false);
		if(tipoContrattoChoiceBox.getValue() != "Libero Professionista" && tipoContrattoChoiceBox.getValue() != null)
			textNomeSocieta.setDisable(false);
		textIDLavoratoreDatiPersonali.setDisable(false);
	}

	private void ableCampiDatiColleghi() {
		textNomeCollega1.setDisable(false);
		textCognomeCollega1.setDisable(false);
		textCodiceFiscaleCollega1.setDisable(false);
		textNomeCollega2.setDisable(false);
		textCognomeCollega2.setDisable(false);
		textCodiceFiscaleCollega2.setDisable(false);

	}

	// CAMPI LUOGO GENERICO - AMBIENTE DI LAVORO - AMBIENTE DI STUDIO - AMBIENTE EVENTO OCCASIONALE - AMBIENTE FAMILIARE 

	/********* LUOGO *********/
	private void ableCampiDatiLuogo() {
		textCityDatiLuogo.setDisable(false);
		textProvinciaDatiLuogo.setDisable(false);
		textViaDatiLuogo.setDisable(false);
		textNumeroCivicoDatiLuogo.setDisable(false);
		textCAPDatiLuogo.setDisable(false);
		textIDLuogo.setDisable(false);
	}

	private void ableCampiGestione() {
		textCodiceFiscaleGestioneLuogo.setDisable(false);
		textCognomeGestioneLuogo.setDisable(false);
		textIDLuogoGestioneLuogo.setDisable(false);
		textNomeGestioneLuogo.setDisable(false);
		textNumCivicoGestioneLuogo.setDisable(false);
		textViaGestioneLuogo.setDisable(false);
	}

	/********* AMBIENTE DI LAVORO *********/
	private void ableCampiDatiAmbienteLavoro() {
		textOrarioAAmbienteLavoro.setDisable(false);
		textOrarioCAmbienteLavoro.setDisable(false);
		choicheBoxNumDipendentiAmbieneLavoro.setDisable(false);
		textIDLuogoAmbienteLavoro.setDisable(false);
		textViaAmbieneLavoro.setDisable(false);
		textNumCivicoAmbienteLavoro.setDisable(false);
		textIDAmbienteLavoro.setDisable(false);
	}

	/********* AMBIENTE DI STUDIO *********/
	private void ableCampiDatiAmbienteStudio() {
		choicheBoxTipoAmbienteStudio.setDisable(false);
		textIDAmbienteStudio.setDisable(false);
		textIDLuogoAmbienteStudio.setDisable(false);
		textNumCivicoAmbienteStudio.setDisable(false);
		textViaAmbienteStudio.setDisable(false);
	}

	//Campi aula 
	private void ableCampiAula() {
		textID_Aula.setDisable(false);
		choicheBoxNumPostiAula.setDisable(false);
		textIDAmbienteStudioAula.setDisable(false);
	}

	/********* AMBIENTE EVENTO OCCASIONALE *********/
	private void ableCampiDatiEventoOccasionale() {
		choicheBoxNumPartecipantiAmbienteEventoOccasionale.setDisable(false);
		if(radioBLuogoApertoNo.isSelected())
			textDimensioniAmbienteEventoOccasionale.setDisable(false);
		textIDAmbienteEventoOccasionale.setDisable(false);
		textIDLuogoAmbienteEventoOccasionale.setDisable(false);
		textNumCivicoAmbienteEventoOccasionale.setDisable(false);
		textViaAmbienteEventoOccasionale.setDisable(false);
		radioBLuogoApertoSi.setDisable(false);
		radioBLuogoApertoNo.setDisable(false);
	}

	/********* AMBIENTE FAMILIARE *********/
	private void ableCampiDatiAmbienteFamiliare() {
		textDimensioneAmbienteFamiliare.setDisable(false);
		textIDAmbienteFamiliare.setDisable(false);
		textIDLuogoAmbienteFamiliare.setDisable(false);
		textNumCivicoAmbienteFamiliare.setDisable(false);
		textViaAmbienteFamiliare.setDisable(false);
		choicheBoxTipoAmbienteFamiliare.setDisable(false);
	}

	// CAMPI CONTROLLO MEDICO - VISITA 

	/********* CONTROLLO MEDICO *********/
	private void ableCampiControlloMedico() {
		textIDControlloMedico.setDisable(false);
		textIDLuogoControlloMedico.setDisable(false);
		textNomeControlloMedico.setDisable(false);
		textNomeMedicoControlloMedico.setDisable(false);
		textNumCivicoControlloMedico.setDisable(false);
		textNomeOspedaleControlloMedico.setDisable(false);
		textViaControlloMedico.setDisable(false);
		textCodiceFiscaleControlloMedico.setDisable(false);
		textCognomeControlloMedico.setDisable(false);
		if(choiceBoxTipoControlloMedico.getValue() != "Altro" && choiceBoxTipoControlloMedico.getValue() != null )
			choiceBoxEsitoControlloMedico.setDisable(false);
		choiceBoxTipoControlloMedico.setDisable(false);
		datePickerDataControlloMedico.setDisable(false);

	}

	/********* VISITA *********/
	private void ableCampiVisita() {
		textCodiceFiscaleDatiIncontro.setDisable(false);
		datePickerDataDatiIncontro.setDisable(false);
		textNomeDatiIncontro.setDisable(false);
		textCognomeDatiIncontro.setDisable(false);
		textorarioDatiIncontro.setDisable(false);
		textIDLuogoDatiIncontro.setDisable(false);
		textNumCivicoDatiIncontro.setDisable(false);
		textViaDatiIncontro.setDisable(false);
	}

	/**** FREQUENTAZIONE ISTITUZIONALE ****/	
	private void ableCampiFrequentazioneIstituzionale() {
		textNomeLuoghiIST.setDisable(false);
		textCognomeLuoghiIST.setDisable(false);
		textIDStudenteLuoghiIST.setDisable(false);
		textViaLuoghiIST.setDisable(false);
		textNumeroCivicoLuoghiIST.setDisable(false);
		textIDAmbienteStudioLuoghiIST.setDisable(false);

	}

	/***** ABITUDINI LAVORATIVI  *****/
	private void ableCampiAbitudiniLavorativi() {
		textNomeLuoghiLAV.setDisable(false);
		textCognomeLuoghiLAV.setDisable(false);
		textIDLavoratoreLuoghiLAV.setDisable(false);
		textViaLuoghiLAV.setDisable(false);
		textNumeroCivicoLuoghiLAV.setDisable(false);
		textIDAmbienteLavorativoLuoghiLAV.setDisable(false);
		textOrarioIngressoLuoghiLAV.setDisable(false);
		textOrarioUscitaLuoghiLAV.setDisable(false);
		datePickerLuoghiLAV.setDisable(false);
	}

	/******* DATI VACCINO *****/
	private void ableCampiDatiVaccino()
	{
		textIDVaccinoDatiVaccino.setDisable(false);
		textEnteRilascioDatiVaccino.setDisable(false);
		textControindicazioniDatiVaccino.setDisable(false);
		tipoVaccinoChoiceBox.setDisable(false);
		periodoUtileChoiceBox.setDisable(false);
	}

	/***** SOMMINISTRAZIONE VACCINO ****/
	private void ableCampiSomministrazioneVaccino()
	{
		textNomeSomministrazioneVaccino.setDisable(false);
		textCognomeSomministrazioneVaccino.setDisable(false);
		textCodiceFiscaleSomministrazioneVaccino.setDisable(false);
		textIDVaccinoSomministrazioneVaccino.setDisable(false);
		dataSomministrazioneVaccino.setDisable(false);
		textComplicazioniVaccino.setDisable(false);
	}




	//VINCOLI PERSONA - STUDENTE - LAVORATORE
	public void checkVincoloTipoContratto(ActionEvent event) {
		if(tipoContrattoChoiceBox.getValue() == "Libero Professionista") {
			textNomeSocieta.setDisable(true);
		}
		else {
			textNomeSocieta.setDisable(false);
		}
	}  

	public void checkVincoloTamponeEseguito(ActionEvent event) {
		tbTamponeEseguitoNo.setOnAction(this::checkVincoloTamponeEseguito);


		if(tbTamponeEseguitoSi.isSelected()) {	
			textDataTamponeEseguito.setDisable(false);

		}
		else {
			textDataTamponeEseguito.setDisable(true);
		}
	}

	public void checkVincoloSintomatologia(ActionEvent event) {
		if(sceltaSintomi.getValue() == "Asintomatico") {
			dataSintomiDatePickerStatoSalute.setDisable(true);
		}
		else {
			dataSintomiDatePickerStatoSalute.setDisable(false);
		}
	}

	//VINCOLI LUOGO 
	public void checkVincoloLuogoAperto(ActionEvent event) {

		if(radioBLuogoApertoNo.isSelected()) {
			textDimensioniAmbienteEventoOccasionale.setDisable(false);
		}
		else {
			textDimensioniAmbienteEventoOccasionale.setDisable(true);
		}
	}

	public void checkVincoloTipoControlloMedico(ActionEvent event) {
		if(choiceBoxTipoControlloMedico.getValue() == "Altro") {
			choiceBoxEsitoControlloMedico.setDisable(true);
		}
		else {
			choiceBoxEsitoControlloMedico.setDisable(false);
		}
	}

	public void inizializzaEnumerazioni() {
		ObservableList<Integer> values = FXCollections.observableArrayList();
		for(int i = 1 ; i<=500 ; i++) {
			values.add(i);
		}

		ObservableList<Integer> values1 = FXCollections.observableArrayList();
		for(int i = 1 ; i<=200; i++) {
			values1.add(i);
		}

		ObservableList<Integer> values2 = FXCollections.observableArrayList();
		for(int i = 150 ; i<=400; i = i + 30) {
			values2.add(i);
		}


		//Persona
		sceltaParentela.setItems(FXCollections.observableArrayList("E' Marito di", "E' Moglie di", "E' Figlio di", "E' Figlia di", "E' Padre di", "E' Madre di", "E' Zia di", "E' Zio di", "E' Nonno di",	"E' Nonna di", "E' Cugino di", "E' Cugina di", "E' Sorella di", "E' Fratello di"));
		sceltaSintomi.setItems(FXCollections.observableArrayList("Asintomatico", "Pauci-Sintomatico", "Lieve", "Severe", "Critico", "Guarito"));
		tipoContrattoChoiceBox.setItems(FXCollections.observableArrayList("Determinato", "Indeterminato", "A Chiamata", "A Progetto", "Libero Professionista"));

		//Luogo
		choicheBoxNumPartecipantiAmbienteEventoOccasionale.setItems(values1);
		choicheBoxNumPostiAula.setItems(values1);
		choicheBoxNumDipendentiAmbieneLavoro.setItems(values);
		choicheBoxTipoAmbienteStudio.setItems(FXCollections.observableArrayList("Scuola infanzia", "Scuola elementare", "Scuola media", "Scuola superiore", "Università"));
		choicheBoxTipoAmbienteFamiliare.setItems(FXCollections.observableArrayList("Casa", "Appartamento"));

		//Controllo Medico
		choiceBoxTipoControlloMedico.setItems(FXCollections.observableArrayList("Tampone", "Test Sierologico", "Altro"));
		choiceBoxEsitoControlloMedico.setItems(FXCollections.observableArrayList("Positivo", "Negativo", "In attesa"));

		//Vaccino
		tipoVaccinoChoiceBox.setItems(FXCollections.observableArrayList("SARS-CoV 2", "Anti-Influenzale", "Altro"));
		periodoUtileChoiceBox.setItems(values2);
	}

	public void inizializzaVincoliEnnupla() {
		//Persona
		sceltaSintomi.setOnAction(this::checkVincoloSintomatologia);
		tbTamponeEseguitoSi.setOnAction(this::checkVincoloTamponeEseguito);
		tipoContrattoChoiceBox.setOnAction(this::checkVincoloTipoContratto);

		//Luogo
		radioBLuogoApertoSi.setOnAction(this::checkVincoloLuogoAperto);
		radioBLuogoApertoNo.setOnAction(this::checkVincoloLuogoAperto);

		//Controllo Medico
		choiceBoxTipoControlloMedico.setOnAction(this::checkVincoloTipoControlloMedico);
	}

	public void inizializzaDate() {
		limitaData(LocalDate.of(1900, 1, 1),LocalDate.now(), textDataNascitaPersona);
		limitaData(LocalDate.of(2019, 1, 1),LocalDate.now(), dataSintomiDatePickerStatoSalute);
		limitaData(LocalDate.of(2019, 1, 1),LocalDate.now(), textDataTamponeEseguito);
		limitaData(LocalDate.of(2019, 1, 1),LocalDate.now(), datePickerDataControlloMedico);
		limitaData(LocalDate.of(2019, 1, 1),LocalDate.now(), datePickerDataDatiIncontro);	
		limitaData(LocalDate.of(2019, 1, 1),LocalDate.now(), datePickerLuoghiLAV);
		limitaData(LocalDate.of(2019, 1, 1),LocalDate.now(), dataSomministrazioneVaccino);
		
	}

	public void limitaData(LocalDate minDate, LocalDate maxDate , DatePicker datePicker) {
		datePicker.setDayCellFactory(x ->
		new DateCell() {
			@Override 
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);
				setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
			}
		});
	}


	public void inizializzaTextFieldNumerico() {
		//Studente - dati personali
		formattaTextFieldInNumerico(textIDStudenteStudente);
		// Lavoratore - dati personali
		formattaTextFieldInNumerico(textIDLavoratoreDatiPersonali);
		// Luogo - luogo
		formattaTextFieldInNumerico(textIDLuogo);
		// Luogo - gestione luogo
		formattaTextFieldInNumerico(textIDLuogoGestioneLuogo);

		// Luogo - dati ambiente di lavoro
		formattaTextFieldInNumerico(textIDLuogoAmbienteLavoro);
		formattaTextFieldInNumerico(textIDAmbienteLavoro);
		formattaTextFieldOrario(textOrarioAAmbienteLavoro);
		formattaTextFieldOrario(textOrarioCAmbienteLavoro);

		// Luogo - dati ambiente di studio
		formattaTextFieldInNumerico(textIDLuogoAmbienteStudio);
		formattaTextFieldInNumerico(textIDAmbienteStudio);

		// Luogo - Aula
		formattaTextFieldInNumerico(textIDAmbienteStudioAula);
		formattaTextFieldInNumerico(textID_Aula);

		// Luogo -  dati ambiente evento occasionale
		formattaTextFieldInNumerico(textIDLuogoAmbienteEventoOccasionale);
		formattaTextFieldInNumerico(textIDAmbienteEventoOccasionale);
		formattaTextFieldInNumerico(textDimensioniAmbienteEventoOccasionale);

		// Luogo - dati ambiente familaire
		formattaTextFieldInNumerico(textIDLuogoAmbienteFamiliare);
		formattaTextFieldInNumerico(textIDAmbienteFamiliare);
		formattaTextFieldInNumerico(textDimensioneAmbienteFamiliare);

		// Controllo Medico - Dati visita medica
		formattaTextFieldInNumerico(textIDControlloMedico);
		formattaTextFieldInNumerico(textIDLuogoControlloMedico);

		//Incontri - Dati luoghi visitati
		formattaTextFieldInNumerico(textIDLuogoDatiIncontro);

		//Luoghi istituzionali frequentati
		formattaTextFieldInNumerico(textIDStudenteLuoghiIST);
		formattaTextFieldInNumerico(textIDAmbienteStudioLuoghiIST);

		//Abitudini lavorative
		formattaTextFieldInNumerico(textIDLavoratoreLuoghiLAV);
		formattaTextFieldInNumerico(textIDAmbienteLavorativoLuoghiLAV);

		//Dati Vaccino
		formattaTextFieldInNumerico(textIDVaccinoDatiVaccino);

		//Somministrazione vaccino
		formattaTextFieldInNumerico(textIDVaccinoSomministrazioneVaccino);
		
		//Incontri
		formattaTextFieldOrario(textorarioDatiIncontro);
		
		//Frequentazione Lavorativa
		formattaTextFieldOrario(textOrarioIngressoLuoghiLAV);
		formattaTextFieldOrario(textOrarioUscitaLuoghiLAV);
		
	}
	


	public void formattaTextFieldInNumerico(TextField field) {

		DecimalFormat format = new DecimalFormat( "#.0" );
		field.setTextFormatter( new TextFormatter<>(c ->
		{
			if ( c.getControlNewText().isEmpty() )
			{
				return c;
			}

			ParsePosition parsePosition = new ParsePosition( 0 );
			Object object = format.parse( c.getControlNewText(), parsePosition );

			if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
			{
				return null;
			}
			else
			{
				return c;
			}
		}));
		
	}
	

	
	
	public void formattaTextFieldOrario(TextField field) {

		DecimalFormat format = new DecimalFormat( "#.0" );
		
		field.setTextFormatter( new TextFormatter<>(c ->
		{
			if ( c.getControlNewText().isEmpty() ){
				return c;
			}

			ParsePosition parsePosition = new ParsePosition( 0 );
			Object object = format.parse( c.getControlNewText(), parsePosition );

			ParsePosition parsePosition1 = new ParsePosition( 3 );
			Object object1 = format.parse( c.getControlNewText(), parsePosition1 );

			if( c.getControlNewText().length() < 3) {
				
				if ( object == null || parsePosition.getIndex()  < c.getControlNewText().length() ){
					return null;
				}
				else{
					return c;
				}
			}
			else if ( c.getControlNewText().length() > 3 ){
				if ( object1 == null || parsePosition1.getIndex() < c.getControlNewText().length() || c.getControlNewText().length()>5 ){
					return null;
				}
				else{
					return c;
				}
			}
			else {
				if(c.getText().equals(":") || c.getText().equals(""))
					return c;

				else {
					return null;
				}
			}


		}));

	}
	

	public void logout(ActionEvent event) {
		Stage currentStage = (Stage)radioBDatiPersonali.getScene().getWindow();
		currentStage.hide();

		Stage primaryStage = new Stage();
		AnchorPane	root = new AnchorPane();
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("LoginController.fxml"));
		} catch (IOException e) {
			Messaggi.MessaggioErroreGenerico();
			e.printStackTrace();
		}
		Scene firstScene = new Scene(root,300,400);
		primaryStage.setScene(firstScene);
		try {
			primaryStage.getIcons().add(new Image(new FileInputStream("src\\application\\Immagini\\logocolorato.png")));
		} catch (FileNotFoundException e) {
			Messaggi.MessaggioErroreGenerico();
			e.printStackTrace();
		}
		primaryStage.setTitle("Login");
		primaryStage.show();
		LoginController.logoutDataBase();
	}

	public void inizializzaTooltip() {

		setTooltip(labelHelpCFPersona, textHelpCFPersona);
		setTooltip(labelHelpIDLuogo, textHelpIDLuogo);
		setTooltip(labelHelpIDAmbienteLavoro, textHelpIDAmbienteLavoro);
		setTooltip(labelHelpIDAmbienteStudio, textHelpIDAmbienteStudio);
		setTooltip(labelHelpIDAula, textHelpIDAula);
		setTooltip(labelHelpIDAmbienteEventoOcc, textHelpIDAmbienteEventoOcc);
		setTooltip(labelHelpIDAmbienteFamiliare, textHelpIDAmbienteFamiliare);
		setTooltip(labelHelpIDControlloMedico, textHelpIDControlloMedico);
		setTooltip(labelHelpIDStudente, textHelpIDStudente);
		setTooltip(labelHelpIDLavoratore, textHelpIDLavoratore);
		setTooltip(labelHelpIDVaccinoDatiVaccino, textHelpIDVaccinoDatiVaccino);
		setTooltip(labelHelpCFStatoSalute, textHelpCFStatoSalute);
	}


	public void setTooltip(Label label, TextArea textArea) {
		label.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				textArea.setVisible(true);

			}

		});

		label.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				textArea.setVisible(false);

			}

		});
	}
	public void pulisci(ActionEvent e) {
		console2.clear();
		clearSelected();
	}
	
	public void annulla(ActionEvent e) {
		setSituazioneStandard();
		manipolaDatabase(4);
	}

	public void modifica(ActionEvent e) {
		//console2.clear();
		manipolaDatabase(3);
		//console2.home();
	}

	public void ricerca(ActionEvent e) {
		//console2.clear();
		manipolaDatabase(2);
		//console2.home();
		
	}
	public void salva(ActionEvent e) {
		//console2.clear();
		setSituazioneStandard();
		manipolaDatabase(1);
		//console2.home();
		
	}

	public void aggiungi(ActionEvent e) {
		//console2.clear();
		manipolaDatabase(0);
		//console2.home();
	}
	
	

	public void manipolaDatabase(int mod) {
		Toggle toggleSelected = toggleGroupSelezioneCampiPersonaGenerica.getSelectedToggle();
		if(toggleSelected!=null) {
			//PERSONA - PERSONA
			if(toggleSelected.equals(radioBDatiPersonali)) {
				String nome = textFieldNomePersona.getText().toUpperCase();
				String cognome = textFieldCognomePersona.getText().toUpperCase();
				String comuneNascita = textComuneNascitaPersona.getText().toUpperCase();
				String codiceFiscale = textFieldCFPersona.getText().toUpperCase();
				String numeroTelefono1 = textFieldTelefono1Persona.getText().toUpperCase();
				String mail1 = textFieldMail1Persona.getText().toUpperCase();
				String numeroTelefono2 = textFieldTelefono2Persona.getText().toUpperCase();
				String mail2 = textFieldMail2Persona.getText().toUpperCase();
				String dataNascita = formattaDataPerQuery(textDataNascitaPersona);  
				String sesso = null;
				String residenza = textFieldResidenza.getText().toUpperCase();
				if(tbMaschio.isSelected()) 
					sesso = "M";
				else if(tbFemmina.isSelected())
					sesso = "F";

				if(mod == 0)
					queryAggiungiPersona(nome,cognome,comuneNascita,codiceFiscale,numeroTelefono1,mail1,numeroTelefono2,mail2,dataNascita,sesso,residenza);	
				else if(mod == 1)
					querySalvaModificaPersona(nome,cognome,comuneNascita,codiceFiscale,numeroTelefono1,mail1,numeroTelefono2,mail2,dataNascita,sesso,residenza);
				else if(mod == 2)
					queryRicercaPersona(nome,cognome,comuneNascita,codiceFiscale,numeroTelefono1,mail1,numeroTelefono2,mail2,dataNascita,sesso,residenza);
				else if(mod == 3)
					recuperaPersonaDB(codiceFiscale);
				else if(mod == 4)
					ableCampiDatiPersonali();
				
			}

			// PERSONA - STUDENTE
			else if(toggleSelected.equals(radioBDatiPersonaliStudente)) {
				String nome = textNomeStudente.getText().toUpperCase();
				String cognome = textCognomeStudente.getText().toUpperCase();
				String codiceFiscale = textCodiceFiscaleStudente.getText().toUpperCase();
				Integer frequentaInPresenza = null;
				if(tbFrequentaInPresenzaSi.isSelected())
					frequentaInPresenza = 1 ;
				else if (tbFrequentaInPresenzaNo.isSelected())
					frequentaInPresenza = 0;
				String idStudente = textIDStudenteStudente.getText();
				

				if(mod == 0)
					queryAggiungiStudente(nome,cognome,codiceFiscale,frequentaInPresenza,idStudente);
				else if(mod == 1)
					querySalvaModificaStudente(frequentaInPresenza, idStudente);
				else if(mod == 2)
					queryRicercaStudente(nome, cognome, codiceFiscale, frequentaInPresenza, idStudente);
				else if(mod == 3)
					recuperaStudenteDB(idStudente);
				else if(mod == 4)
					ableCampiDatiPersonaliStudente();
			}

			else if(toggleSelected.equals(radioBDatiPersonaliLavoratore)) {
				String nome = textNomeLavoratore.getText().toUpperCase();
				String cognome = textCognomeLavoratore.getText().toUpperCase();
				String codiceFiscale = textCodiceFiscaleLavoratore.getText().toUpperCase();
				String giorniLavorativi = formattaGiorniLavorativi(); 
				String tipoContratto = tipoContrattoChoiceBox.getValue();
				String nomeSocietà = textNomeSocieta.getText().toUpperCase();
				String idLavoratore = textIDLavoratoreDatiPersonali.getText().toUpperCase();

				if(mod == 0)
					queryAggiungiLavoratore(nome,cognome,codiceFiscale,giorniLavorativi,tipoContratto,nomeSocietà,idLavoratore);
				else if(mod == 1)
					querySalvaModificaLavoratore(giorniLavorativi,tipoContratto,nomeSocietà,idLavoratore);
				else if(mod == 2)
					queryRicercaLavoratore(nome,cognome,codiceFiscale,giorniLavorativi,tipoContratto,nomeSocietà,idLavoratore);
				else if(mod == 3)
					recuperaLavoratoreDB(idLavoratore);
				else if(mod == 4)
					ableCampiDatiPersonaliLavoratore();

			}

			else if(toggleSelected.equals(radioBParentela)) {
				String nome1 = textFieldPersonaNomeParentela1.getText().toUpperCase();
				String cognome1 = textFieldPersonaCognomeParentela1.getText().toUpperCase();
				String codiceFiscale1 = textFieldPersonaCFParentela1.getText().toUpperCase();
				String tipoParentela = formattaParentela();
				String nome2 = textFieldPersonaNomeParentela2.getText().toUpperCase();
				String cognome2 = textFieldPersonaCognomeParentela2.getText().toUpperCase();
				String codiceFiscale2 = textFieldPersonaCFParentela2.getText().toUpperCase();
				
				if(mod == 0)
				queryAggiungiParentela(nome1, cognome1, codiceFiscale1, tipoParentela, nome2, cognome2, codiceFiscale2);
				else if(mod == 2)
				queryRicercaParentela(nome1, cognome1, codiceFiscale1, tipoParentela, nome2, cognome2, codiceFiscale2);
				else if(mod == 4)
				ableCampiParentela();	
			}

			else if(toggleSelected.equals(radioBStatoDellaSalute)) {
				String nome = textFieldPersonaNomeStatoSalute.getText().toUpperCase();
				String cognome = textFieldPersonaCognomeStatoSalute.getText().toUpperCase();
				String codiceFiscale = textFieldPersonaCFStatoSalute.getText().toUpperCase();
				String sintomatologia = sceltaSintomi.getValue();
				String dataInizioSintomi = "";
				if(sintomatologia!=null && !sintomatologia.equals("Asintomatico")) {
					dataInizioSintomi = formattaDataPerQuery(dataSintomiDatePickerStatoSalute);
				}

				Integer quarantena = null;
				
				if(tbQuarantenaSi.isSelected())
					quarantena = 1;
				else if(tbQuarantenaNo.isSelected())
					quarantena = 0;
				
				Integer tamponeEseguito = null;
				
				if(tbTamponeEseguitoSi.isSelected())
					tamponeEseguito = 1;
				else if(tbTamponeEseguitoNo.isSelected())
					tamponeEseguito = 0;
				
				String dataTamponeEseguito="";
				if(tamponeEseguito!=null && tamponeEseguito == 1)
					dataTamponeEseguito = formattaDataPerQuery(textDataTamponeEseguito);
					
				String malattieCroniche = textFieldPersonaMalattieCronicheStatoSalute.getText().toUpperCase();
				
				if(mod == 0)
					queryAggiungiStatoSalute(nome, cognome, codiceFiscale, sintomatologia, dataInizioSintomi, quarantena, tamponeEseguito, dataTamponeEseguito, malattieCroniche);
				else if(mod == 1)
					querySalvaModificaStatoSalute(codiceFiscale, sintomatologia, dataInizioSintomi, quarantena, tamponeEseguito, dataTamponeEseguito, malattieCroniche);
				else if(mod == 2)
					queryRicercaStatoSalute(nome, cognome, codiceFiscale, sintomatologia, dataInizioSintomi, quarantena, tamponeEseguito, dataTamponeEseguito, malattieCroniche);
				else if(mod == 3)
					recuperaStatoSalute(codiceFiscale);
				else if(mod == 4)
					ableCampiStatoSalute();
					
			}

			else if(toggleSelected.equals(radioBCompagniStudente)) {
				String nome1 = textNomeCompagno1.getText().toUpperCase();
				String cognome1 = textCognomeCompagno1.getText().toUpperCase();
				String codiceFiscale1 = textCodiceFiscaleCompagno1.getText().toUpperCase();
				String nome2 = textNomeCompagno2.getText().toUpperCase();
				String cognome2 = textCognomeCompagno2.getText().toUpperCase();
				String codiceFiscale2 = textCodiceFiscaleCompagno2.getText().toUpperCase();

				if(mod == 0)
					queryAggiungiCompagni(nome1, cognome1, codiceFiscale1, nome2, cognome2, codiceFiscale2);
				else if(mod == 2)
					queryRicercaCompagni(nome1, cognome1, codiceFiscale1, nome2, cognome2, codiceFiscale2);
				
			}

			else if(toggleSelected.equals(radioBColleghiLavoratore)) {
				String nome1 = textNomeCollega1.getText().toUpperCase();
				String cognome1 = textCognomeCollega1.getText().toUpperCase();
				String codiceFiscale1 = textCodiceFiscaleCollega1.getText().toUpperCase();
				String nome2 = textNomeCollega2.getText().toUpperCase();
				String cognome2 = textCognomeCollega2.getText().toUpperCase();
				String codiceFiscale2 = textCodiceFiscaleCollega2.getText().toUpperCase();
				
				if(mod == 0)
					queryAggiungiColleghi(nome1, cognome1, codiceFiscale1, nome2, cognome2, codiceFiscale2);
				else if(mod == 2)
					queryRicercaColleghi(nome1, cognome1, codiceFiscale1, nome2, cognome2, codiceFiscale2);
			}
			//*** LUOGO ***
			else if(toggleSelected.equals(radioBDatiLuogo)) {
				String citta = textCityDatiLuogo.getText().toUpperCase();
				String provincia = textProvinciaDatiLuogo.getText().toUpperCase();
				String via = textViaDatiLuogo.getText().toUpperCase();
				String numeroCivico = textNumeroCivicoDatiLuogo.getText().toUpperCase();
				String cap = textCAPDatiLuogo.getText().toUpperCase();
				String idLuogo = textIDLuogo.getText().toUpperCase();
				
				if(mod == 0)
                    queryAggiungiLuogo(citta, provincia,via,numeroCivico,cap,idLuogo);
                else if(mod == 1)
                    querySalvaModificaLuogo(citta, provincia,via,numeroCivico,cap,idLuogo);
                else if(mod == 2)
                    queryRicercaLuogo(citta, provincia,via,numeroCivico,cap,idLuogo);
                else if(mod == 3)
                    recuperaLuogo(idLuogo);
                else if(mod == 4)
                    ableCampiDatiLuogo();
			}
			
			//*** GESTIONE LUOGO ***
			else if(toggleSelected.equals(radioBGestioneLuogo)) {
                String nomeGestore = textNomeGestioneLuogo.getText().toUpperCase();
                String cognomeGestore = textCognomeGestioneLuogo.getText().toUpperCase();
                String codiceFiscaleGestore = textCodiceFiscaleGestioneLuogo.getText().toUpperCase();
                String viaGestita = textViaGestioneLuogo.getText().toUpperCase();
                String numCivicoGestito = textNumCivicoGestioneLuogo.getText().toUpperCase();
                String idLuogoGestito = textIDLuogoGestioneLuogo.getText().toUpperCase();

                if(mod == 0)
                	queryAggiungiGestioLuogo(nomeGestore,cognomeGestore,codiceFiscaleGestore,viaGestita,numCivicoGestito,idLuogoGestito);
                else if(mod == 2)
                	queryRicercaGestioneLuogo(nomeGestore,cognomeGestore,codiceFiscaleGestore,viaGestita,numCivicoGestito,idLuogoGestito);
                else if(mod == 4)
                	ableCampiGestione();

			}

			// AMBIENTE LAVORO
            else if(toggleSelected.equals(radioBAmbienteLavoro)) {
                String via = textViaAmbieneLavoro.getText().toUpperCase();
                String numeroCivico = textNumCivicoAmbienteLavoro.getText().toUpperCase();
                String idLuogo = textIDLuogoAmbienteLavoro.getText().toUpperCase();
                String orarioA = textOrarioAAmbienteLavoro.getText().toUpperCase();
                String orarioC = textOrarioCAmbienteLavoro.getText().toUpperCase();
                Integer dipendentiMax = choicheBoxNumDipendentiAmbieneLavoro.getValue();
                String idAmbienteLavoro = textIDAmbienteLavoro.getText().toUpperCase();

                if(mod == 0)
                queryAggiungiAmbienteLavoro(via,numeroCivico,idLuogo,orarioA,orarioC,dipendentiMax,idAmbienteLavoro);
                else if(mod == 1)
                	querySalvaModificaAmbienteLavoro(via,numeroCivico,idLuogo,orarioA,orarioC,dipendentiMax,idAmbienteLavoro);
                else if(mod == 2)
                	queryRicercaAmbienteLavoro(via,numeroCivico,idLuogo,orarioA,orarioC,dipendentiMax,idAmbienteLavoro);
                else if(mod == 3)
                    recuperaAmbienteLavoro(idAmbienteLavoro);
                else if(mod == 4)
                    ableCampiDatiAmbienteLavoro();

            }
			// AMBIENTE STUDIO
            else if(toggleSelected.equals(radioBAmbienteStudio)) {
            	String via = textViaAmbienteStudio.getText().toUpperCase();
            	String numeroCivico = textNumCivicoAmbienteStudio.getText().toUpperCase();
            	String idLuogo = textIDLuogoAmbienteStudio.getText().toUpperCase();
            	String tipoAmbiente = choicheBoxTipoAmbienteStudio.getValue();
            	String idAmbienteStudio = textIDAmbienteStudio.getText().toUpperCase();
            	if(mod == 0)
            		queryAggiungiAmbienteStudio(via,numeroCivico,idLuogo,tipoAmbiente,idAmbienteStudio);
            	else if(mod == 1)
            		querySalvaModificaAmbienteStudio(via,numeroCivico,idLuogo,tipoAmbiente,idAmbienteStudio);
            	else if(mod == 2)
            		queryRicercaAmbienteStudio(via,numeroCivico,idLuogo,tipoAmbiente,idAmbienteStudio);
            	else if(mod == 3)
            		recuperaAmbienteStudio(idAmbienteStudio);
            	else if(mod == 4)
            		ableCampiDatiAmbienteStudio();

            }
			// AULA
			else if(toggleSelected.equals(radioBAula)) {
				String idAmbienteStudioAula = textIDAmbienteStudioAula.getText().toUpperCase();
				Integer numeroPostiMax = choicheBoxNumPostiAula.getValue();
				String idAula = textID_Aula.getText().toUpperCase();
				if(mod == 0)
					queryAggiungiAula(idAmbienteStudioAula,numeroPostiMax,idAula);
				else if(mod == 1)
            		querySalvaModificaAmbienteStudioAula(idAmbienteStudioAula,numeroPostiMax,idAula);
            	else if(mod == 2)
            		queryRicercaAmbienteStudioAula(idAmbienteStudioAula,numeroPostiMax,idAula);
            	else if(mod == 3)
            		recuperaAmbienteStudioAula(idAula);
            	else if(mod == 4)
            		ableCampiAula();

				

			}
			//  AMBIENTE EVENTO OCCASIONALE
			else if(toggleSelected.equals(radioBAmbienteEventoOccasionale)) {
				String via = textViaAmbienteEventoOccasionale.getText().toUpperCase();
				String numeroCivico = textNumCivicoAmbienteEventoOccasionale.getText().toUpperCase();
				String idLuogo = textIDLuogoAmbienteEventoOccasionale.getText().toUpperCase();
				Integer luogoAperto = null;
					if(radioBLuogoApertoSi.isSelected())luogoAperto=1;
					else if(radioBLuogoApertoNo.isSelected())luogoAperto=0;
					
				String dimensione = "";
				if(luogoAperto != null && luogoAperto == 0)
					dimensione = textDimensioniAmbienteEventoOccasionale.getText().toUpperCase();
				Integer numPartecipanti = null;
				if(choicheBoxNumPartecipantiAmbienteEventoOccasionale.getValue() != null)
					numPartecipanti = choicheBoxNumPartecipantiAmbienteEventoOccasionale.getValue();
				String idAmbienteEventoOccasionale = textIDAmbienteEventoOccasionale.getText().toUpperCase();
				
				
				if(mod == 0)
					queryAggiungiAmbienteEventoOccasionale(via,numeroCivico,idLuogo,luogoAperto,dimensione,numPartecipanti,idAmbienteEventoOccasionale);
				else if(mod == 1)
            		querySalvaModificaAmbienteEventoOccassionale(via,numeroCivico,idLuogo,luogoAperto,dimensione,numPartecipanti,idAmbienteEventoOccasionale);
            	else if(mod == 2)
            		queryRicercaAmbienteEventoOccasionale(via,numeroCivico,idLuogo,luogoAperto,dimensione,numPartecipanti,idAmbienteEventoOccasionale);
            	else if(mod == 3)
            		recuperaAmbienteEventoOccasionale(idAmbienteEventoOccasionale);
            	else if(mod == 4)
            		ableCampiDatiEventoOccasionale();


			}
			// AMBIENTE FAMILIARE
			else if(toggleSelected.equals(radioBAmbienteFamiliare)) {
				String via = textViaAmbienteFamiliare.getText().toUpperCase();
				String numeroCivico = textNumCivicoAmbienteFamiliare.getText().toUpperCase();
				String idLuogo = textIDLuogoAmbienteFamiliare.getText().toUpperCase();
				String tipo = choicheBoxTipoAmbienteFamiliare.getValue();
				String dimensione = textDimensioneAmbienteFamiliare.getText().toUpperCase();
				String idAmbienteFamiliare = textIDAmbienteFamiliare.getText().toUpperCase();
				if(mod == 0)
					queryAggiungiAmbienteFamiliare(via, numeroCivico, idLuogo, tipo, dimensione, idAmbienteFamiliare);
				else if(mod == 1)
            		querySalvaModificaAmbienteFamiliare(via, numeroCivico, idLuogo, tipo, dimensione, idAmbienteFamiliare);
            	else if(mod == 2)
            		queryRicercaAmbienteFamiliare(via, numeroCivico, idLuogo, tipo, dimensione, idAmbienteFamiliare);
            	else if(mod == 3)
            		recuperaAmbienteFamiliare(idAmbienteFamiliare);
            	else if(mod == 4)
            		ableCampiDatiAmbienteFamiliare();
			}
			
			// CONTROLLO MEDICO
			else if(toggleSelected.equals(radioBDatiControlloMedico)) {
				String nomeMedico = textNomeMedicoControlloMedico.getText().toUpperCase();
				String nomeStruttura = textNomeOspedaleControlloMedico.getText().toUpperCase();
				String tipo = formattaTipoControlloMedico();
				String esito = null;
				
				if(!tipo.equals("") && !tipo.equals("Altro"))
					esito = choiceBoxEsitoControlloMedico.getValue();
				
				String data = formattaDataPerQuery(datePickerDataControlloMedico);
				String IDControlloMedico = textIDControlloMedico.getText().toUpperCase();
				String nome = textNomeControlloMedico.getText().toUpperCase();
				String cognome = textCognomeControlloMedico.getText().toUpperCase();
				String codicefiscale = textCodiceFiscaleControlloMedico.getText().toUpperCase();
				String via = textViaControlloMedico.getText().toUpperCase();
				String numeroCivico = textNumCivicoControlloMedico.getText().toUpperCase();
				String idLuogo = textIDLuogoControlloMedico.getText().toUpperCase();

				if(mod == 0)
					queryAggiungiDatiVisitaMedica(nomeMedico, nomeStruttura, tipo, esito, data, IDControlloMedico, nome, cognome, codicefiscale, via, numeroCivico, idLuogo);
				else if(mod == 1)
					querySalvaModificaDatiVisitaMedica(nomeMedico, nomeStruttura, tipo, esito, data, IDControlloMedico, nome, cognome, codicefiscale, via, numeroCivico, idLuogo);
				else if(mod == 2)
					queryRicercaDatiVisitaMedica(nomeMedico, nomeStruttura, tipo, esito, data, IDControlloMedico, nome, cognome, codicefiscale, via, numeroCivico, idLuogo);					
				else if(mod == 3)
					recuperaDatiVisitaMedica(IDControlloMedico);
				else if(mod == 4)
					ableCampiControlloMedico();
			
			}

			else if(toggleSelected.equals(radioBDatiIncontroVisita)) {
				String nome = textNomeDatiIncontro.getText().toUpperCase();
				String cognome = textCognomeDatiIncontro.getText().toUpperCase();
				String codicefiscale = textCodiceFiscaleDatiIncontro.getText().toUpperCase();
				String via = textViaDatiIncontro.getText().toUpperCase();
				String numero_civico = textNumCivicoDatiIncontro.getText().toUpperCase();
				String id_luogo = textIDLuogoDatiIncontro.getText().toUpperCase();
				String data = formattaDataPerQuery(datePickerDataDatiIncontro);
				String orario = textorarioDatiIncontro.getText().toUpperCase();

				if(mod==0)
					queryAggiungiDatiVisita(nome, cognome, codicefiscale, via, numero_civico, id_luogo, data, orario);
				else if(mod==2)
					queryRicercaDatiVisita(nome, cognome, codicefiscale, via, numero_civico, id_luogo, data, orario);
			}

			else if(toggleSelected.equals(radioBLuoghiIstituzionaliFrequentati)) {
				String nome = textNomeLuoghiIST.getText().toUpperCase();
				String cognome = textCognomeLuoghiIST.getText().toUpperCase();
				String id_studente = textIDStudenteLuoghiIST.getText().toUpperCase();
				String via = textViaLuoghiIST.getText().toUpperCase();
				String numero_civico = textNumeroCivicoLuoghiIST.getText().toUpperCase();
				String id_ambiente_studio = textIDAmbienteStudioLuoghiIST.getText().toUpperCase();
				
				if(mod == 0)
					queryAggiungiFrequentazioneIstituzionale(nome, cognome, id_studente, via, numero_civico, id_ambiente_studio);
				else if(mod == 2)
					queryRicercaFrequentazioneIstituzionale(nome, cognome, id_studente, via, numero_civico, id_ambiente_studio);
					
			}

			else if(toggleSelected.equals(radioBLuoghiAbitudiniLavorative)) {
				String nome = textNomeLuoghiLAV.getText().toUpperCase();
				String cognome = textCognomeLuoghiLAV.getText().toUpperCase();
				String id_lavoratore = textIDLavoratoreLuoghiLAV.getText().toUpperCase();
				String via = textViaLuoghiLAV.getText().toUpperCase();
				String numero_civico = textNumeroCivicoLuoghiLAV.getText().toUpperCase();
				String idAmbienteLavoro = textIDAmbienteLavorativoLuoghiLAV.getText().toUpperCase();
				String data = formattaDataPerQuery(datePickerLuoghiLAV);
				String orarioIngresso = textOrarioIngressoLuoghiLAV.getText().toUpperCase();
				String orarioUscita = textOrarioUscitaLuoghiLAV.getText().toUpperCase();
				if(mod == 0)
					queryAggiungiAbitudiniLavorative(nome, cognome, id_lavoratore, via, numero_civico,idAmbienteLavoro, data, orarioIngresso, orarioUscita);
				else if(mod == 2)
					queryRicercaAbitudiniLavorative(nome, cognome, id_lavoratore, via, numero_civico, idAmbienteLavoro, data, orarioIngresso, orarioUscita);

			}
			else if(toggleSelected.equals(radioBDatiVaccino)) {
				String idVaccino = textIDVaccinoDatiVaccino.getText().toUpperCase();
				String enteRilascio = textEnteRilascioDatiVaccino.getText().toUpperCase();
				String controindicazioni = textControindicazioniDatiVaccino.getText().toUpperCase();
				String tipoVaccino = tipoVaccinoChoiceBox.getValue();
				Integer periodoUtile = periodoUtileChoiceBox.getValue();
				
				
				if(mod == 0 )
					queryAggiungiVaccino(idVaccino,enteRilascio,controindicazioni,tipoVaccino,periodoUtile);
				else if(mod == 1)
					querySalvaModificaVaccino(idVaccino,enteRilascio,controindicazioni,tipoVaccino,periodoUtile);
				else if(mod == 2)
					queryRicercaVaccino(idVaccino,enteRilascio,controindicazioni,tipoVaccino,periodoUtile);
				else if(mod == 3)
					recuperaVaccino(idVaccino);
				else if(mod == 4)
					ableCampiDatiVaccino();
			}

			else if(toggleSelected.equals(radioBSomministrazioneVaccino)) {
				String nome = textNomeSomministrazioneVaccino.getText().toUpperCase();
				String cognome = textCognomeSomministrazioneVaccino.getText().toUpperCase();
				String codiceFiscale = textCodiceFiscaleSomministrazioneVaccino.getText().toUpperCase();
				String idVaccino = textIDVaccinoSomministrazioneVaccino.getText().toUpperCase();
				String complicazioni = textComplicazioniVaccino.getText().toUpperCase();
				String data = formattaDataPerQuery(dataSomministrazioneVaccino);
				
				if(mod == 0)
					queryAggiungiSomministrazione(nome,cognome,codiceFiscale,idVaccino,data,complicazioni);
				else if(mod == 2)
					queryRicercaSomministrazione(nome,cognome,codiceFiscale,idVaccino,data,complicazioni);
					
			}
		}
		else {
			console2.appendText("Nessuna classe selezionata\n");
			separatore();
		}
	}

	private void queryAggiungiPersona(String nome, String cognome, String comuneNascita, String codiceFiscale, String numeroTelefono1, String mail1, String numeroTelefono2, String mail2, String dataNascita, String sesso, String residenza) {
		
		String query = "INSERT INTO persona ( nome,  cognome,  comuneNascita, residenza, sesso,  codiceFiscale, Telefono1,  mail1,  Telefono2,  mail2,  dataNascita) VALUES ("
				+formattaStringaConApici(nome)+","
				+formattaStringaConApici(cognome)+","
				+formattaStringaConApici(comuneNascita)+","
				+formattaStringaConApici(residenza)+","
				+formattaStringaConApici(sesso)+","
				+formattaStringaConApici(codiceFiscale)+","
				+formattaStringaConApici(numeroTelefono1)+","
				+formattaStringaConApici(mail1)+","
				+formattaStringaConApici(numeroTelefono2)+","
				+formattaStringaConApici(mail2)+","
				+"TO_DATE("
				+formattaStringaConApici(dataNascita)+","
				+"'DD/MM/YYYY')"
				+")"
				;
		eseguiQueryAggiunta(query);
		
		

	}

	private void querySalvaModificaPersona(String nome, String cognome, String comuneNascita, String codiceFiscale,
			String numeroTelefono1, String mail1, String numeroTelefono2, String mail2, String dataNascita, String sesso,
			String residenza) {
		
			String query = "UPDATE Persona SET nome = "
					+formattaStringaConApici(nome)+",cognome="
					+formattaStringaConApici(cognome)+",comuneNascita="
					+formattaStringaConApici(comuneNascita)+",residenza="
					+formattaStringaConApici(residenza)+",sesso="
					+formattaStringaConApici(sesso)+",telefono1="
					+formattaStringaConApici(numeroTelefono1)+",mail1="
					+formattaStringaConApici(mail1)+",telefono2="
					+formattaStringaConApici(numeroTelefono2)+",mail2="
					+formattaStringaConApici(mail2)+",dataNascita=TO_DATE("
					+formattaStringaConApici(dataNascita) + ", 'DD/MM/YYYY')"
					+ " WHERE CodiceFiscale = "+formattaStringaConApici(codiceFiscale)
					;
			eseguiQueryModifica(query);
		
		textFieldCFPersona.setDisable(false);


	}

	private void queryRicercaPersona(String nome, String cognome, String comuneNascita, String codiceFiscale,
			String telefono1, String mail1, String telefono2, String mail2, String dataNascita, String sesso,
			String residenza) {
		
		String select = "select nome,cognome,codicefiscale,datanascita,comunenascita,residenza,sesso,mail1,telefono1,mail2,telefono2 ";
		String target = "from persona ";
		String where = "where ";
		String query = "";

		where = where.concat(controllaAttributoPerQueryRicerca(nome, "nome ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(cognome, "cognome ", 2));	
		where = where.concat(controllaAttributoPerQueryRicerca(comuneNascita, "comuneNascita ", 2));	
		where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale, "codiceFiscale ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(telefono1, "telefono1 ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(telefono2, "telefono2 ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(mail1, "mail1 ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(mail2, "mail2 ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(dataNascita, "dataNascita ", 3));
		where = where.concat(controllaAttributoPerQueryRicerca(sesso, "sesso ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(residenza, "residenza", 2));	
		where = where.substring(0, where.length() - 4);

		query = query.concat(select.concat(target.concat(where)));
		String titolo = String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-35s%-25s%-35s%-25s\n","nome", "cognome","codice Fiscale","data nascita","residenza","comune Nascita","sesso","mail1","telefono1","mail2","telefono2").toUpperCase();
		stampa("Query di ricerca : "+query+"\n");
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()) {
				console2.appendText("Ricerca effettuata con successo, di seguito sono riportati i risultati\n\n");	
				console2.appendText(titolo.toUpperCase());
				console2.appendText(String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-35s%-25s%-35s%-25s\n",rs.getString("nome"), rs.getString("cognome"),rs.getString("codicefiscale"),rs.getDate("DataNascita"),rs.getString("Residenza"),rs.getString("comunenascita"),rs.getString("sesso"),rs.getString("mail1"),rs.getString("telefono1"),rs.getString("mail2"),rs.getString("telefono2")));
				while(rs.next())
					console2.appendText(String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-35s%-25s%-35s%-25s\n",rs.getString("nome"), rs.getString("cognome"),rs.getString("codicefiscale"),rs.getDate("DataNascita"),rs.getString("Residenza"),rs.getString("comunenascita"),rs.getString("sesso"),rs.getString("mail1"),rs.getString("telefono1"),rs.getString("mail2"),rs.getString("telefono2")));
				separatore(titolo);
			}
			else{
				console2.appendText("Nessuna risultato trovato\n");
				separatore();
			}
		} catch (SQLException e) {
			console2.appendText("Ricerca non riuscita\n");
			e.printStackTrace();
			console2.appendText(e.getMessage());
			separatore();
		}
		
	}


	private void recuperaPersonaDB(String codiceFiscale) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM PERSONA WHERE codicefiscale = "+formattaStringaConApici(codiceFiscale));
			if(  rs.next() ) {
				textFieldNomePersona.setText(rs.getString("nome"));
				textFieldCognomePersona.setText(rs.getString("cognome"));
				textComuneNascitaPersona.setText(rs.getString("comuneNascita"));
				textFieldTelefono1Persona.setText(rs.getString("telefono1"));
				textFieldMail1Persona.setText(rs.getString("mail1"));
				textFieldTelefono2Persona.setText(rs.getString("telefono2"));
				textFieldMail2Persona.setText(rs.getString("mail2"));
				textFieldResidenza.setText(rs.getString("residenza"));
				textDataNascitaPersona.setValue(rs.getDate("dataNascita").toLocalDate());	
				//DELETE 
				System.out.println("ciao");
				if(rs.getString("sesso").equals("M")){
					tbMaschio.setSelected(true);
				}
				else {
					tbFemmina.setSelected(true);
				}

				setSituzioneAnnullaSalva();
				textFieldCFPersona.setDisable(true);

			}
			else {
				console2.appendText("Impossibile recuperare una persona, provare ad inserire il codice fiscale correttamente\n");
				separatore();
			}

		} 
		catch (SQLException e) {
			console2.appendText(e.getMessage());
		}

	}


	private void queryAggiungiParentela(String nome1, String cognome1, String codiceFiscale1, String tipoParentela,
			String nome2, String cognome2, String codiceFiscale2) {
		
			codiceFiscale1 = selectCodiceFiscaleQuery(nome1, cognome1, codiceFiscale1);
			codiceFiscale2 = selectCodiceFiscaleQuery(nome2, cognome2, codiceFiscale2);
			if(!codiceFiscale1.equals("") && !codiceFiscale2.equals("")) {
				
				String query = "INSERT INTO PARENTELA (codicefiscale1,codicefiscale2,tipo) VALUES ( "
				+formattaStringaConApici(codiceFiscale1)+" ,"
				+formattaStringaConApici(codiceFiscale2)+" ,"
				+formattaStringaConApici(tipoParentela)+")";
				
				eseguiQueryAggiunta(query);
			}
	}
	
	private void queryRicercaParentela(String nome1, String cognome1, String codiceFiscale1, String tipoParentela,
			String nome2, String cognome2, String codiceFiscale2) {
		
		String select = "select p1.nome,p1.cognome,p1.codicefiscale,gradodiparentela,p2.nome,p2.cognome,p2.codicefiscale ";
		String target = "from parentela,persona p1, persona p2 ";
		String where = "where p1.codicefiscale=fk_idpersona AND p2.codicefiscale=fk_idparente AND ";
		String query = "";	
		
			
			
			where = where.concat(controllaAttributoPerQueryRicerca(nome1, "p1.nome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(cognome1, "p1.cognome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(nome2, "p2.nome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(cognome2, "p2.cognome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale1, "fk_idpersona ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale2, "fk_idparente ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(tipoParentela, "gradodiparentela ", 2));	
			where = where.substring(0, where.length() - 4);
			query = query.concat(select.concat(target.concat(where)));
			
			String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n","nome", "cognome","codiceFiscale","Grado","nome", "cognome","codiceFiscale"));
			
			eseguiQueryRicerca(query, 7 , titolo);
			
	}


	private void queryAggiungiStatoSalute(String nome, String cognome, String codiceFiscale, String sintomatologia,
			String dataInizioSintomi, Integer quarantena, Integer tamponeEseguito, String dataTamponeEseguito,
			String malattieCroniche) {
		codiceFiscale = selectCodiceFiscaleQuery(nome, cognome, codiceFiscale);
		if(!codiceFiscale.equals("")) {
			
			String query = "INSERT INTO StatoSalute (sintomatologia,datainiziosintomi,quarantena,testtamponeEseguito,dataTamponeEseguito,malattiecroniche, fk_idpersona) "
					+ "Values( "+ formattaStringaConApici(sintomatologia)+","
					+ "TO_DATE("+ formattaStringaConApici(dataInizioSintomi)+","+formattaStringaConApici("DD/MM/YYYY")+"),"
					+ quarantena +","
					+ tamponeEseguito +","
					+ "TO_DATE("+ formattaStringaConApici(dataTamponeEseguito)+","+formattaStringaConApici("DD/MM/YYYY")+"),"
					+ formattaStringaConApici(malattieCroniche)+","
					+formattaStringaConApici(codiceFiscale)
					+ ")";  
			
			
			eseguiQueryAggiunta(query);
		}

	}
	
	private void querySalvaModificaStatoSalute(String codiceFiscale, String sintomatologia, String dataInizioSintomi,
			Integer quarantena, Integer tamponeEseguito, String dataTamponeEseguito, String malattieCroniche) {
		
		String query = "UPDATE STATOSALUTE SET "
				+ "sintomatologia = " +formattaStringaConApici(sintomatologia)+","
				+ "quarantena = "+quarantena+","
				+ "datainiziosintomi = TO_DATE("+formattaStringaConApici(dataInizioSintomi)+","+formattaStringaConApici("DD/MM/YYYY")+"),"
				+ "DataTamponeEseguito = TO_DATE("+formattaStringaConApici(dataTamponeEseguito)+","+formattaStringaConApici("DD/MM/YYYY")+"),"
				+ "Malattiecroniche = "+formattaStringaConApici(malattieCroniche)+","
				+ "TestTamponeEseguito = "+tamponeEseguito
				+") WHERE FK_IDPERSONA = "+formattaStringaConApici(codiceFiscale);
		
		eseguiQueryModifica(query);
		
		setSituazioneStandard();
		textFieldPersonaCFStatoSalute.setDisable(false);
		textFieldPersonaCognomeStatoSalute.setDisable(false);
		textFieldPersonaNomeStatoSalute.setDisable(false);
		
	}


	private void queryRicercaStatoSalute(String nome, String cognome, String codiceFiscale, String sintomatologia,
			String dataInizioSintomi, Integer quarantena, Integer tamponeEseguito, String dataTamponeEseguito,
			String malattieCroniche) {
		
		String select = "Select nome,cognome,codicefiscale,sintomatologia,datainiziosintomi,quarantena,TesttamponeEseguito,datatamponeeseguito,malattiecroniche ";
		String target = "from persona,statosalute ";
		String where = "where fk_idpersona = codicefiscale AND ";
		String query = "";
		
			
			where = where.concat(controllaAttributoPerQueryRicerca(nome, "nome", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(cognome, "cognome", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale, "codiceFiscale", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(sintomatologia, "sintomatologia", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(dataInizioSintomi, "dataInizioSintomi", 3));
			if(quarantena!=null)
				where = where.concat(controllaAttributoPerQueryRicerca(quarantena.toString(), "quarantena", 2));
			if(tamponeEseguito!=null)
				where = where.concat(controllaAttributoPerQueryRicerca(tamponeEseguito.toString(), "TesttamponeEseguito", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(dataTamponeEseguito, "dataTamponeEseguito", 3));
			where = where.concat(controllaAttributoPerQueryRicerca(malattieCroniche, "malattieCroniche", 2));
			where = where.substring(0, where.length() - 4);
			query = query.concat(select.concat(target.concat(where)));
			String titolo = String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n","NOME", "COGNOME","CODICE FISCALE","SINTOMATOLOGIA","DATA INIZIO SINTOMI","QUARANTENA","TAMPONE ESEGUITO","DATA TAMPONE ESEGUITO","MALATTIE CRONICHE");
			//eseguiQueryRicerca(query, 9, titolo);	
			
			try {
				stampa("Query di ricerca : "+query+"\n");
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(query);
				if(rs.next()) {
					console2.appendText("Ricerca effettuata con successo, di seguito sono riportati i risultati\n\n");
					console2.appendText(titolo.toUpperCase());
					console2.appendText(String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n",rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getDate(8),rs.getString(9)));
					while(rs.next())
						console2.appendText(String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n",rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getDate(8),rs.getString(9)));
					separatore(titolo);
				}
				else{
					console2.appendText("Nessuna risultato trovato\n");
					separatore();
				}
			} catch (SQLException e) {
				console2.appendText("Ricerca non riuscita\n");
				e.printStackTrace();
				console2.appendText(e.getMessage());
				separatore();
			}
	}

	private void recuperaStatoSalute(String codiceFiscale) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM PERSONA,statosalute WHERE fk_idpersona = "+formattaStringaConApici(codiceFiscale));
			if(  rs.next() ) {
				textFieldPersonaNomeStatoSalute.setText(rs.getString("nome"));
				textFieldPersonaCognomeStatoSalute.setText(rs.getString("cognome"));
				textFieldPersonaCFStatoSalute.setText(rs.getString("codicefiscale"));
				textFieldPersonaMalattieCronicheStatoSalute.setText(rs.getString("malattiecroniche"));
				dataSintomiDatePickerStatoSalute.setValue(rs.getDate("datainizioSintomi").toLocalDate());
				textDataTamponeEseguito.setValue(rs.getDate("datatamponeeseguito").toLocalDate());
				
				if(rs.getInt("quarantena")==1) {
					tbQuarantenaSi.setSelected(true);
				}
				else if(rs.getInt("quarantena")==0) {
					tbQuarantenaNo.setSelected(true);
				}
				if(rs.getInt("testtamponeeseguito")==1) {
					tbTamponeEseguitoSi.setSelected(true);
				}
				else if(rs.getInt("testtamponeeseguito")==0) {
					tbTamponeEseguitoNo.setSelected(false);
				}
				
				
				setSituzioneAnnullaSalva();
				textFieldPersonaCFStatoSalute.setDisable(true);
				textFieldPersonaCognomeStatoSalute.setDisable(true);
				textFieldPersonaNomeStatoSalute.setDisable(true);
				

			}
			else {
				console2.appendText("Impossibile recuperare uno stato di salute, provare ad inserire il codice fiscale correttamente\n");
				separatore();
			}
		
		}
		catch (SQLException e) {
			console2.appendText(e.getMessage());
		}
		
	}



	


	private void queryAggiungiStudente(String nome, String cognome, String codiceFiscale, Integer frequentaInPresenza, String idStudente)  {

		codiceFiscale = selectCodiceFiscaleQuery(nome, cognome, codiceFiscale);
		if(!codiceFiscale.equals("")) {
			String query = "INSERT INTO Studente(FK_IDPersona, FrequentaInPresenza, idStudente) VALUES( " + formattaStringaConApici(codiceFiscale) + "," + frequentaInPresenza +","+formattaStringaConApici(idStudente) + ")"; 
			eseguiQueryAggiunta(query);
		}

	}


	private void querySalvaModificaStudente( Integer frequentaInPresenza,
			String idStudente) {
		String query = "UPDATE Studente SET frequentaInPresenza = " + frequentaInPresenza+" WHERE IDStudente = " + idStudente;
		eseguiQueryModifica(query);			
		textCodiceFiscaleStudente.setDisable(false); 
		textIDStudenteStudente.setDisable(false);
		textNomeStudente.setDisable(false);
		textCognomeStudente.setDisable(false);
	}




	private void queryRicercaStudente(String nome, String cognome, String codiceFiscale, Integer frequentaInPresenza, String idStudente) {
		String select = "select nome, cognome, codicefiscale, idstudente, frequentainpresenza ";
		String target = "from studente,persona ";
		String where = "where FK_Idpersona = codiceFiscale AND ";
		String query = "";

		where = where.concat(controllaAttributoPerQueryRicerca(nome, "nome ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(cognome, "cognome ", 2));  
		where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale, "codiceFiscale ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(idStudente, "idStudente", 2));
		
		if(frequentaInPresenza != null)
			where = where.concat(controllaAttributoPerQueryRicerca(frequentaInPresenza.toString(), "frequentaInPresenza ", 2));
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
		
		String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s\n", "Nome" , "Cognome" , "Codice Fiscale", "IDSTUDENTE", "Frequenta In Presenza"));
		eseguiQueryRicerca(query, 5 , titolo);
		
	}


	private void recuperaStudenteDB(String idStudente) {

		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Studente, Persona  WHERE FK_IDPERSONA = CodiceFiscale AND IDSTUDENTE = "+formattaStringaConApici(idStudente));
			if( rs.next()) {
				textCodiceFiscaleStudente.setText(rs.getString("FK_IDPERSONA"));
				if(rs.getInt("FrequentaInPresenza") == 1) {
					tbFrequentaInPresenzaSi.setSelected(true);
				}
				else {
					tbFrequentaInPresenzaNo.setSelected(true);
				} 
				textNomeStudente.setText(rs.getString("nome"));
				textCognomeStudente.setText(rs.getString("cognome"));

				setSituzioneAnnullaSalva();
				textIDStudenteStudente.setDisable(true);
				textNomeStudente.setDisable(true);
				textCognomeStudente.setDisable(true);
				textCodiceFiscaleStudente.setDisable(true);
			}
			else {
				console2.appendText("Impossibile recuperare uno studente, provare ad inserire idStudente correttamente\n");
				separatore();
			}
		} catch (SQLException e) {
			console2.appendText(e.getMessage()+"\n");
			separatore();
		}


	}



	private void queryAggiungiCompagni(String nome1, String cognome1, String codiceFiscale1, String nome2, String cognome2,
			String codiceFiscale2) {
		
		codiceFiscale1 = selectCodiceFiscaleQuery(nome1, cognome1, codiceFiscale1);
		codiceFiscale2 = selectCodiceFiscaleQuery(nome2, cognome2, codiceFiscale2);
		if(!codiceFiscale1.equals("") && !codiceFiscale2.equals("")) {
			try {
				Statement stm = connection.createStatement();
				String queryRecuperaIDStudente1 = "Select idstudente FROM studente,persona WHERE fk_idpersona = "+formattaStringaConApici(codiceFiscale1);
				String queryRecuperaIDStudente2 = "Select idstudente FROM studente,persona WHERE fk_idpersona = "+formattaStringaConApici(codiceFiscale2);
				
				ResultSet rs1 = stm.executeQuery(queryRecuperaIDStudente1);
				rs1.next();
				String idStudente1 = rs1.getString("idstudente");
				
				ResultSet rs2 = stm.executeQuery(queryRecuperaIDStudente2);
				rs2.next();
				String idStudente2 = rs2.getString("idstudente");
				
				String query = "INSERT INTO COMPAGNI (FK_IDSTUDENTE,FK_IDCOMPAGNO) VALUES ("
						+formattaStringaConApici(idStudente1)+", "
						+formattaStringaConApici(idStudente2)+")";
				eseguiQueryAggiunta(query);
				
			} catch (SQLException e) {
				console2.appendText("Studente non trovato! riprovare specificando uno studente\n");
				//console2.appendText(e.getMessage());
				separatore();
				e.printStackTrace();
			}
					
		}

	}
	
	private void queryRicercaCompagni(String nome1, String cognome1, String codiceFiscale1, String nome2,
			String cognome2, String codiceFiscale2) {
		String select = "select p1.nome,p1.cognome,p1.codicefiscale,p2.nome,p2.cognome,p2.codicefiscale ";
		String target = "from compagni c,persona p1, persona p2,studente s1, studente s2 ";
		String where = "where s1.idstudente=fk_idstudente AND s1.fk_idpersona = p1.codicefiscale AND s2.idstudente=fk_idcompagno AND s2.fk_idpersona = p2.codicefiscale AND ";
		String query = "";	
		
		
			where = where.concat(controllaAttributoPerQueryRicerca(nome1, "p1.nome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(cognome1, "p1.cognome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(nome2, "p2.nome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(cognome2, "p2.cognome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale1, "p1.codicefiscale ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale2, "p2.codicefiscale ", 2));
			where = where.substring(0, where.length() - 4);
			query = query.concat(select.concat(target.concat(where)));
			
			String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s%-25s\n","nome", "cognome","codiceFiscale","nome compagno", "cognome compagno","codiceFiscale compagno"));
			
			eseguiQueryRicerca(query, 6 , titolo);
		
	}

	private void queryAggiungiLavoratore(String nome, String cognome, String codiceFiscale, String giorniLavorativi,
			String tipoContratto, String nomeSocietà, String idLavoratore) {
		
			//console2.clear();
			codiceFiscale = selectCodiceFiscaleQuery(nome, cognome, codiceFiscale);
			if(!codiceFiscale.equals("")) {
				
				String query = ("INSERT INTO Lavoratore(FK_IDPersona,giorniLavorativi,tipo,nomesocietà,idlavoratore) VALUES( " 
				+ formattaStringaConApici(codiceFiscale) + "," 
				+ formattaStringaConApici(giorniLavorativi) + ","
				+ formattaStringaConApici(tipoContratto) + "," 
				+ formattaStringaConApici(nomeSocietà) + ","
				+ formattaStringaConApici(idLavoratore) + ")"
				);
				
				eseguiQueryAggiunta(query);				
				
			}
			
	}

	private void querySalvaModificaLavoratore(String giorniLavorativi,
			String tipoContratto, String nomeSocietà, String idLavoratore) {
			String query = "UPDATE Lavoratore "
						 + "SET tipo = " +formattaStringaConApici(tipoContratto)+ " ,nomesocietà = "+formattaStringaConApici(nomeSocietà)+" ,giornilavorativi = "+ formattaStringaConApici(giorniLavorativi)
						 + " WHERE IDLavoratore = "+idLavoratore; 
			
		eseguiQueryModifica(query);

		textCodiceFiscaleLavoratore.setDisable(false); 
		textIDLavoratoreDatiPersonali.setDisable(false);
		textNomeLavoratore.setDisable(false);
		textCognomeLavoratore.setDisable(false);

	}

	private void queryRicercaLavoratore(String nome, String cognome, String codiceFiscale, String giorniLavorativi,
			String tipoContratto, String nomeSocietà, String idLavoratore) {
		String select = "select nome,cognome,codicefiscale,idlavoratore,nomesocietà,tipo,giornilavorativi ";
		String target = "from lavoratore,persona ";
		String where = "where FK_Idpersona = codiceFiscale AND ";
		String query = "";
		
		where = where.concat(controllaAttributoPerQueryRicerca(nome, "nome", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(cognome, "cognome", 2));  
		where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale, "codiceFiscale", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(idLavoratore, "idLavoratore", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(giorniLavorativi, "giorniLavorativi", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(tipoContratto, "tipo", 2));  
		where = where.concat(controllaAttributoPerQueryRicerca(nomeSocietà, "nomeSocietà", 2));
		
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));

		String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n", "Nome" , "Cognome" , "Codice Fiscale", "idLavoratore" , "Nome società", "Tipo di contratto" , "Giorni lavorativi"));
		eseguiQueryRicerca(query, 7, titolo);
		


	}


	private void recuperaLavoratoreDB(String idLavoratore) {
		Statement statement;
		try {
			statement = connection.createStatement();
			//DELETE 
			//System.out.println(idStudente+" "+formattaStringaConApici(idStudente));
			ResultSet rs = statement.executeQuery("SELECT * FROM Lavoratore, Persona  WHERE FK_IDPERSONA = CodiceFiscale AND IDlavoratore = "+formattaStringaConApici(idLavoratore));
			if( rs.next()) {
				textCodiceFiscaleLavoratore.setText(rs.getString("FK_IDPERSONA"));
				textNomeLavoratore.setText(rs.getString("nome"));
				textCognomeLavoratore.setText(rs.getString("cognome"));
				textNomeSocieta.setText(rs.getString("nomesocietà"));
				tipoContrattoChoiceBox.setValue(rs.getString("tipo"));
				textIDLavoratoreDatiPersonali.setText(rs.getString("idLavoratore"));
				setGiorniLavorativi(rs.getString("GiorniLavorativi"));
				
				
				
				setSituzioneAnnullaSalva();
				textIDLavoratoreDatiPersonali.setDisable(true);
				textNomeLavoratore.setDisable(true);
				textCognomeLavoratore.setDisable(true);
				textCodiceFiscaleLavoratore.setDisable(true);
			}
			else {
				console2.appendText("Impossibile  recuperare un lavoratore, provare ad inserire idLavoratore correttamente\n");
				separatore();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			console2.appendText(e.getMessage()+"\n");
			separatore();
		}

	}

	private void queryAggiungiColleghi(String nome1, String cognome1, String codiceFiscale1, String nome2, String cognome2,
			String codiceFiscale2) {
		codiceFiscale1 = selectCodiceFiscaleQuery(nome1, cognome1, codiceFiscale1);
		codiceFiscale2 = selectCodiceFiscaleQuery(nome2, cognome2, codiceFiscale2);
		if(!codiceFiscale1.equals("") && !codiceFiscale2.equals("")) {
			try {
				Statement stm = connection.createStatement();
				String queryRecuperaIDLavoratore1 = "Select idLavoratore FROM Lavoratore,persona WHERE fk_idpersona = "+formattaStringaConApici(codiceFiscale1);
				String queryRecuperaIDLavoratore2 = "Select idLavoratore FROM Lavoratore,persona WHERE fk_idpersona = "+formattaStringaConApici(codiceFiscale2);
				
				ResultSet rs1 = stm.executeQuery(queryRecuperaIDLavoratore1);
				rs1.next();
				String idLavoratore1 = rs1.getString("idLavoratore");
				
				ResultSet rs2 = stm.executeQuery(queryRecuperaIDLavoratore2);
				rs2.next();
				String idLavoratore2 = rs2.getString("idLavoratore");
				
				String query = "INSERT INTO COLLEGHI (FK_IDLavoratore,FK_IDCollega) VALUES ("
						+formattaStringaConApici(idLavoratore1)+", "
						+formattaStringaConApici(idLavoratore2)+")";
				eseguiQueryAggiunta(query);
				
			} catch (SQLException e) {
				console2.appendText("Lavoratore non trovato! riprovare specificando uno studente\n");
				//console2.appendText(e.getMessage());
				separatore();
				e.printStackTrace();
			}
					
		}

	}
	

	private void queryRicercaColleghi(String nome1, String cognome1, String codiceFiscale1, String nome2,
			String cognome2, String codiceFiscale2) {
		String select = "select p1.nome,p1.cognome,p1.codicefiscale,p2.nome,p2.cognome,p2.codicefiscale ";
		String target = "from colleghi c,persona p1, persona p2,lavoratore s1, lavoratore s2 ";
		String where = "where s1.idLavoratore=fk_idlavoratore AND s1.fk_idpersona = p1.codicefiscale AND s2.idlavoratore=fk_idcollega AND s2.fk_idpersona = p2.codicefiscale AND ";
		String query = "";	
		
		
			where = where.concat(controllaAttributoPerQueryRicerca(nome1, "p1.nome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(cognome1, "p1.cognome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(nome2, "p2.nome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(cognome2, "p2.cognome ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale1, "p1.codicefiscale ", 2));
			where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale2, "p2.codicefiscale ", 2));
			where = where.substring(0, where.length() - 4);
			query = query.concat(select.concat(target.concat(where)));
			
			String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s%-25s\n","nome", "cognome","codiceFiscale","nome collega", "cognome collega","codiceFiscale collega"));
			
			eseguiQueryRicerca(query, 6 , titolo);
	}



	private void queryAggiungiLuogo(String citta, String provincia, String via, String numeroCivico, String cap,
			String idLuogo) {

		String query = "INSERT INTO Luogo (Città,   Provincia,  Via, N_Civico,  CAP,  IDLUOGO) VALUES ("
				+formattaStringaConApici(citta)+","
				+formattaStringaConApici(provincia)+","
				+formattaStringaConApici(via)+","
				+formattaStringaConApici(numeroCivico)+","
				+formattaStringaConApici(cap)+","
				+formattaStringaConApici(idLuogo)
				+")" ;

		eseguiQueryAggiunta(query);

	}

	private void querySalvaModificaLuogo(String citta, String provincia, String via, String numeroCivico, String cap,
			String idLuogo) {
			String query = "UPDATE Luogo SET città = "
				+ formattaStringaConApici(citta ) + ", provincia = " + formattaStringaConApici(provincia) + ", via = " + formattaStringaConApici(via) + ", n_civico =" + formattaStringaConApici(numeroCivico) + ", cap =" + formattaStringaConApici(cap)
				+ " WHERE IDluogo = " + idLuogo;
			eseguiQueryModifica(query);
			
			ableCampiDatiLuogo();
	}

	
	private void queryRicercaLuogo(String citta, String provincia, String via, String numeroCivico, String cap,
			String idLuogo) {

		String select = "select città, provincia, cap, idluogo, n_civico, via ";
		String target = "from Luogo ";
		String where = "where ";
		String query = "";

		where = where.concat(controllaAttributoPerQueryRicerca(citta, "città ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(provincia, "provincia ", 2));  
		where = where.concat(controllaAttributoPerQueryRicerca(via, "via ", 2));  
		where = where.concat(controllaAttributoPerQueryRicerca(numeroCivico, "n_civico ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(cap, "cap ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(idLuogo, "idLuogo ", 2));

		where = where.substring(0, where.length() - 4);

		query = query.concat(select.concat(target.concat(where)));
		
		String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s%-25s\n","città", "provincia","cap","idLuogo", "numeroCivico", "via"));
		eseguiQueryRicerca(query, 6 , titolo);
		
	}


	private void recuperaLuogo(String idLuogo) {
		Statement statement;
		try {
			statement = connection.createStatement();
			//DELETE 
			System.out.println("idLuogoRicercato: " + formattaStringaConApici(idLuogo));
			ResultSet rs = statement.executeQuery("SELECT * FROM Luogo  WHERE idluogo = "+formattaStringaConApici(idLuogo));
			if( rs.next()) {
				textCityDatiLuogo.setText(rs.getString("città"));		
				textProvinciaDatiLuogo.setText(rs.getString("provincia"));
				textViaDatiLuogo.setText(rs.getString("via"));
				textNumeroCivicoDatiLuogo.setText(rs.getString("n_civico"));
				textCAPDatiLuogo.setText(rs.getString("cap"));
				textIDLuogo.setText(rs.getString("idluogo"));
				
				setSituzioneAnnullaSalva();
				textIDLuogo.setDisable(true);
				
			}
			else {
				console2.appendText("Impossibile recuperare un luogo, provare ad inserire idLuogo correttamente\n");
				separatore();
			}
		} catch (SQLException e) {
			console2.appendText(e.getMessage()+"\n");
			separatore();
		}
		
	}




	private void queryAggiungiGestioLuogo(String nomeGestore, String cognomeGestore, String codiceFiscaleGestore,
		String viaGestita, String numCivicoGestito, String idLuogoGestito) {
		String codiceFiscale = selectCodiceFiscaleQuery(nomeGestore, cognomeGestore, codiceFiscaleGestore);
		String idLuogo = selectIdLuogoQuery(viaGestita, numCivicoGestito, idLuogoGestito);
		String query = "INSERT INTO Gestione(FK_IDGESTORE, FK_IDLUOGOGESTITO) VALUES(" + formattaStringaConApici(codiceFiscale) + ", " + formattaStringaConApici(idLuogo) + ")";
		eseguiQueryAggiunta(query);


	}
	

	private void queryRicercaGestioneLuogo(String nomeGestore, String cognomeGestore, String codiceFiscaleGestore,
			String viaGestita, String numCivicoGestito, String idLuogoGestito) {
		String select = "select nome, cognome, codicefiscale, città, idluogo, n_civico, via ";
		String target = "from Luogo, persona, gestione ";
		String where = "where fk_idgestore = codicefiscale AND fk_idluogogestito = idluogo AND ";
		String query = "";

		where = where.concat(controllaAttributoPerQueryRicerca(nomeGestore, "nome ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(cognomeGestore, "cognome ", 2));  
		where = where.concat(controllaAttributoPerQueryRicerca(viaGestita, "via ", 2));  
		where = where.concat(controllaAttributoPerQueryRicerca(numCivicoGestito, "n_civico ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(idLuogoGestito, "idluogo ", 2));

		where = where.substring(0, where.length() - 4);

		query = query.concat(select.concat(target.concat(where)));
		System.out.println(query);
		
		String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n","nome", "cognome","codicefiscale","città", "idluogo", "n_civico", "via"));
		eseguiQueryRicerca(query, 7 , titolo);
		
	}



	private void queryAggiungiAmbienteLavoro(String via, String numeroCivico, String idLuogo, String orarioA,
			String orarioC, Integer dipendentiMax, String idAmbienteLavoro) {
		String idLuogo_tmp = selectIdLuogoQuery(via, numeroCivico, idLuogo);
		if(!idLuogo_tmp.equals("")) {
			if(orarioA.length() == 5 && orarioC.length() == 5) {
				String query = "insert into ambientelavoro(idambientelavoro, capienzadipendentimax, orarioapertura, orariochiusura, fk_idluogo) "
						+ "VALUES(" + formattaStringaConApici(idAmbienteLavoro) + ","
						+ dipendentiMax + ","
						+ "TO_TIMESTAMP('2000-01-01 " + orarioA +":00.000000000', 'YYYY-MM-DD HH24:MI:SS.FF'),"
						+ "TO_TIMESTAMP('2000-01-01 " + orarioC +":00.000000000', 'YYYY-MM-DD HH24:MI:SS.FF'),"
						+ formattaStringaConApici(idLuogo_tmp) + ")";
				eseguiQueryAggiunta(query);
			}
			else {
				console2.appendText("Impossibile effettuare l' aggiunta, formato dell'orario errato\n");
	            console2.appendText("Formato corretto : Orario [HH:MM]\n");
	            separatore();
			}

		}

	}

	private void querySalvaModificaAmbienteLavoro(String via, String numeroCivico, String idLuogo, String orarioA,
			String orarioC, Integer dipendentiMax, String idAmbienteLavoro) {
		if(orarioA.length() == 5 && orarioC.length() == 5) { 
			String query = "UPDATE ambientelavoro SET orarioapertura = "
					+ "TO_TIMESTAMP('2000-01-01 " + orarioA +":00.000000000', 'YYYY-MM-DD HH24:MI:SS.FF'), "
					+ "orariochiusura = "
					+ "TO_TIMESTAMP('2000-01-01 " + orarioC +":00.000000000', 'YYYY-MM-DD HH24:MI:SS.FF'), "
					+ "capienzadipendentimax = "
					+ dipendentiMax 
					+ " WHERE idAmbienteLavoro = " + idAmbienteLavoro;
			eseguiQueryModifica(query);
		}
		else {
			console2.appendText("Impossibile salvare la modifica, formato dell'orario errato\n");
            console2.appendText("Formato corretto : Orario [HH:MM]\n");
            separatore();
		}

	
		setSituazioneStandard();
		textViaAmbieneLavoro.setDisable(false);
		textNumCivicoAmbienteLavoro.setDisable(false);
		textIDLuogoAmbienteLavoro.setDisable(false);
		textIDAmbienteLavoro.setDisable(false);
	}



	private void queryRicercaAmbienteLavoro(String via, String numeroCivico, String idLuogo, String orarioA,
			String orarioC, Integer dipendentiMax, String idAmbienteLavoro) {
		String select = "Select l.idluogo, a.idambientelavoro, a.orarioapertura, a.orariochiusura, a.capienzadipendentimax, l.n_civico, l.via ";
		String target = "from luogo l, ambientelavoro a ";
		String where = "where a.fk_idluogo = l.idluogo AND ";
		String query = "";

		where =	where.concat(controllaAttributoPerQueryRicerca(via, "via", 2));
		where =	where.concat(controllaAttributoPerQueryRicerca(numeroCivico, "n_civico", 2));
		where =	where.concat(controllaAttributoPerQueryRicerca(idLuogo, "idluogo", 2));
		if(!orarioA.equals(""))
			where =	where.concat(" a.orarioapertura = TO_TIMESTAMP('2000-1-1 " + orarioA +":00.000000000',  'YYYY-MM-DD HH24:MI:SS.FF') AND ");
		if(!orarioC.equals(""))
			where =	where.concat(" a.orariochiusura = TO_TIMESTAMP('2000-1-1 " + orarioC +":00.000000000',  'YYYY-MM-DD HH24:MI:SS.FF') AND ");
		if(dipendentiMax != null)
			where = where.concat(controllaAttributoPerQueryRicerca(dipendentiMax.toString(), "capienzaDipendentiMax", 2));
		where =	where.concat(controllaAttributoPerQueryRicerca(idAmbienteLavoro, "idAmbienteLavoro", 2));
		
		where = where.substring(0, where.length() - 4);
		stampa("Query di where : "+where+"\n");
		query = query.concat(select.concat(target.concat(where)));
		String titolo = String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n","ID LUOGO", "ID AMBIENTE LAVORATIVO ","ORARIO APERTURA","ORARIO CHIUSURA","CAPIENZA DIP. MASSIMA","NUMERO CIVICO","VIA");

		if((orarioA.length()== 0 || orarioA.length()== 5) && (orarioC.length()== 0 || orarioC.length()== 5)) { 
			try {
				stampa("Query di ricerca: "+query+"\n");
				Statement stat = connection.createStatement();
				ResultSet rs = stat.executeQuery(query);

				if(rs.next()) {
					console2.appendText("Ricerca effettuata con successo, di seguito sono riportati i risultati\n\n");
					console2.appendText(titolo);
					console2.appendText(String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n", rs.getString("idluogo"), rs.getString("idambienteLavoro"), rs.getTime("OrarioApertura").toString().substring(0, 5), rs.getTime("OrarioChiusura").toString().substring(0, 5), rs.getString("capienzadipendentimax"), rs.getString("n_civico"), rs.getString("via")) );
					while(rs.next()) {
						console2.appendText(String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n", rs.getString("idluogo"), rs.getString("idambienteLavoro"), rs.getTime("OrarioApertura").toString().substring(0, 5), rs.getTime("OrarioChiusura").toString().substring(0, 5), rs.getString("capienzadipendentimax"), rs.getString("n_civico"), rs.getString("via")) );
					}
					separatore(titolo);
				}
				else {
					console2.appendText("Nessun elemento trovato \n");
					separatore();
				}
			}

			catch (SQLException e) {
				console2.appendText("Ricerca non riuscita\n");
				e.printStackTrace();
				console2.appendText(e.getMessage());
				separatore();

			}
		} 
		else {
			console2.appendText("Impossibile effettuare la ricerca, formato dell'orario errato\n");
            console2.appendText("Formato corretto : Orario [HH:MM]\n");
            separatore();
		
		}


	}

	private void recuperaAmbienteLavoro(String idAmbienteLavoro) {
	
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Luogo, Ambientelavoro  WHERE fk_idluogo = idluogo AND idambientelavoro = " + formattaStringaConApici(idAmbienteLavoro));
			if(rs.next()) {
				textViaAmbieneLavoro.setText(rs.getString("via"));
				textNumCivicoAmbienteLavoro.setText(rs.getString("n_civico"));
				textIDLuogoAmbienteLavoro.setText(rs.getString("idluogo"));
				textOrarioAAmbienteLavoro.setText(rs.getString("orarioapertura").substring(11, 16));
				textOrarioCAmbienteLavoro.setText(rs.getString("orariochiusura").substring(11, 16));
				choicheBoxNumDipendentiAmbieneLavoro.setValue(rs.getInt("capienzadipendentimax"));
				textIDAmbienteLavoro.setText(rs.getString("idambientelavoro"));
				
				textViaAmbieneLavoro.setDisable(true);
				textNumCivicoAmbienteLavoro.setDisable(true);
				textIDLuogoAmbienteLavoro.setDisable(true);
				textIDAmbienteLavoro.setDisable(true);
				
				setSituzioneAnnullaSalva();
			}
			else {
				console2.appendText("Impossibile recuperare uno ambiente lavorativo, provare ad inserire ID Ambiente di lavoro correttamente\n");
				separatore();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			separatore();
		}
    }
	
	private void queryAggiungiAmbienteStudio(String via, String numeroCivico, String idLuogo, String tipoAmbiente,
			String idAmbienteStudio) {
		String idLuogo_tmp = selectIdLuogoQuery(via, numeroCivico, idLuogo);
		if(!idLuogo_tmp.equals("")) {
				String query = "INSERT INTO AmbienteDiStudio (IDAMBIENTEDISTUDIO, TIPO, FK_IDLUOGO) VALUES("
						+ formattaStringaConApici(idAmbienteStudio) + ", "
						+ formattaStringaConApici(tipoAmbiente) + ", "
						+ formattaStringaConApici(idLuogo_tmp) + ")";
				eseguiQueryAggiunta(query);

		}

	}
	
	private void querySalvaModificaAmbienteStudio(String via, String numeroCivico, String idLuogo, String tipoAmbiente,
			String idAmbienteStudio) {
		String query = "UPDATE ambientedistudio SET tipo = "
				+ formattaStringaConApici(tipoAmbiente)
				+ " WHERE idAmbienteDiStudio = " + idAmbienteStudio;
			eseguiQueryModifica(query);
			
			ableCampiDatiAmbienteStudio();
	}

	private void queryRicercaAmbienteStudio(String via, String numeroCivico, String idLuogo, String tipoAmbiente,
			String idAmbienteStudio) {
		String select = "Select l.idluogo, a.idambientedistudio, a.tipo,  l.n_civico, l.via ";
		String target = "from luogo l, ambientediStudio a ";
		String where = "where a.fk_idluogo = l.idluogo AND ";
		String query = "";
		

		where =	where.concat(controllaAttributoPerQueryRicerca(via, "via", 2));
		where =	where.concat(controllaAttributoPerQueryRicerca(numeroCivico, "n_civico", 2));
		where =	where.concat(controllaAttributoPerQueryRicerca(idLuogo, "idluogo", 2));
		where  = where.concat(controllaAttributoPerQueryRicerca(tipoAmbiente, "tipo", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(idAmbienteStudio, "idAmbientediStudio", 2));
		where = where.substring(0, where.length() - 4);
		stampa("Query di where : "+where+"\n");
		query = query.concat(select.concat(target.concat(where)));
		String titolo = String.format("%-25s%-25s%-25s%-25s%-25s\n","ID LUOGO", "ID AMBIENTE DI STUDIO ","TIPO","NUMERO CIVICO","VIA");
		
		eseguiQueryRicerca(query, 5, titolo);
		
	}

	private void recuperaAmbienteStudio(String idAmbienteStudio) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Luogo, AmbientediStudio  WHERE fk_idluogo = idluogo AND idambientedistudio = "+formattaStringaConApici(idAmbienteStudio));
			if(rs.next()) {
				textViaAmbienteStudio.setText(rs.getString("via"));
				textNumCivicoAmbienteStudio.setText(rs.getString("n_civico"));
				textIDLuogoAmbienteStudio.setText(rs.getString("idluogo"));
				choicheBoxTipoAmbienteStudio.setValue(rs.getString("tipo"));
				textIDAmbienteStudio.setText(rs.getString("idambientedistudio"));
				
				setSituzioneAnnullaSalva();
				textViaAmbienteStudio.setDisable(true);
				textNumCivicoAmbienteStudio.setDisable(true);
				textIDLuogoAmbienteStudio.setDisable(true);
				textIDAmbienteStudio.setDisable(true);
			}
			else {
				console2.appendText("Impossibile recuperare uno ambiente di studio, provare ad inserire ID_Ambiente di studio correttamente\n");
				separatore();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			separatore();
		}
		
	}

	private void queryAggiungiAula(String idAmbienteStudio, Integer numeroPostiMax, String idAula) {
		String query = "INSERT INTO AULA(fk_idambientedistudio, numeromaxposti, idaula) VALUES (" 	+ formattaStringaConApici(idAmbienteStudio) + ", " 	+ numeroPostiMax + ", " + formattaStringaConApici(idAula) + ")";		
		eseguiQueryAggiunta(query);
	}

	private void querySalvaModificaAmbienteStudioAula(String idAmbienteStudioAula, Integer numeroPostiMax,
			String idAula) {
		String query = "UPDATE aula SET numeromaxposti = " + numeroPostiMax + " WHERE idaula = " + formattaStringaConApici(idAula);		
		eseguiQueryModifica(query);
		ableCampiAula();
	}



	private void queryRicercaAmbienteStudioAula(String idAmbienteStudioAula, Integer numeroPostiMax, String idAula) {
		String select = "Select l.idluogo, a.idambientedistudio, au.idaula, au.numeromaxposti, a.tipo,  l.n_civico, l.via ";
		String target = "from luogo l, ambientediStudio a, aula au ";
		String where = "where a.fk_idluogo = l.idluogo AND au.fk_idAmbienteDiStudio = a.idAmbienteDiStudio AND ";
		String query = "";
		
		where =	where.concat(controllaAttributoPerQueryRicerca(idAmbienteStudioAula, "idambientedistudio", 2));
		if(numeroPostiMax != null)
			where = where.concat("numeroMaxPosti = " + numeroPostiMax + " AND ");
		where =	where.concat(controllaAttributoPerQueryRicerca(idAula, "idaula", 2));
			
			
		
		where = where.substring(0, where.length() - 4);
		stampa("Query di where : "+where+"\n");
		query = query.concat(select.concat(target.concat(where)));
		String titolo = String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n","ID LUOGO", "ID AMBIENTE DI STUDIO ", "ID AULA", "NUMERO POSTI MAX", "TIPO","NUMERO CIVICO","VIA");
		eseguiQueryRicerca(query, 7, titolo);
		
		
	}



	private void recuperaAmbienteStudioAula(String idAula) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM AmbienteDiStudio, Aula  WHERE fk_idAmbienteDiStudio = idAmbienteDiStudio AND idaula  = " + formattaStringaConApici(idAula));
			if(rs.next()) {
				textIDAmbienteStudioAula.setText(rs.getString("idambientedistudio"));
				choicheBoxNumPostiAula.setValue(rs.getInt("numeromaxposti"));
				textID_Aula.setText(rs.getString("idaula"));
				
				setSituzioneAnnullaSalva();
				textIDAmbienteStudioAula.setDisable(true);
				textID_Aula.setDisable(true);
			}
			else {
				console2.appendText("Impossibile recuperare un'aula, provare ad inserire ID_Aula correttamente\n");
				separatore();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			separatore();
		}
		
	}



	private void queryAggiungiAmbienteEventoOccasionale(String via, String numeroCivico, String idLuogo,
			Integer luogoAperto, String dimensione, Integer numPartecipanti, String idAmbienteEventoOccasionale) {
		String idLuogo_tmp = selectIdLuogoQuery(via, numeroCivico, idLuogo);
		if(!idLuogo_tmp.equals("")) {
			String query = "insert into ambienteEventoOccasionale(idEventoOccasionale, numeroPartecipanti, dimensione, luogoAperto, fk_idluogo) "
					+ "VALUES(" + formattaStringaConApici(idAmbienteEventoOccasionale) + ", "
					+ numPartecipanti + ", "
					+ formattaStringaConApici(dimensione) + ", "
					+ luogoAperto + ", "
					+ formattaStringaConApici(idLuogo_tmp)
					+ ")";
			eseguiQueryAggiunta(query);
		}

	}
	private void querySalvaModificaAmbienteEventoOccassionale(String via, String numeroCivico, String idLuogo,
			Integer luogoAperto, String dimensione, Integer numPartecipanti, String idAmbienteEventoOccasionale) {
		String query = "UPDATE ambienteEventoOccasionale SET luogoAperto = "
				+ luogoAperto + ", "
				+ "dimensione = " + formattaStringaConApici(dimensione) + ", "
				+  "numeropartecipanti = " + numPartecipanti 
				+ " WHERE idEventoOccasionale = " + idAmbienteEventoOccasionale;
			eseguiQueryModifica(query);
		
			ableCampiDatiEventoOccasionale();
		
	}



	private void queryRicercaAmbienteEventoOccasionale(String via, String numeroCivico, String idLuogo,
			Integer luogoAperto, String dimensione, Integer numPartecipanti, String idAmbienteEventoOccasionale) {
		String select = "Select l.idluogo, a.idEventoOccasionale, a.luogoAperto, a.dimensione, a.numeropartecipanti, l.n_civico, l.via ";
		String target = "from luogo l, ambienteEventoOccasionale a ";
		String where = "where a.fk_idluogo = l.idluogo AND ";
		String query = "";
		
		where =	where.concat(controllaAttributoPerQueryRicerca(via, "via", 2));
		where =	where.concat(controllaAttributoPerQueryRicerca(numeroCivico, "n_civico", 2));
		where =	where.concat(controllaAttributoPerQueryRicerca(idLuogo, "idluogo", 2));
		if(luogoAperto != null) 
			where  = where.concat(controllaAttributoPerQueryRicerca(luogoAperto.toString(), "luogoAperto", 2));			
		where = where.concat(controllaAttributoPerQueryRicerca(dimensione, "dimensione", 2));
		if(numPartecipanti != null) 
			where = where.concat(controllaAttributoPerQueryRicerca(numPartecipanti.toString(), "numeropartecipanti", 2));
		where =	where.concat(controllaAttributoPerQueryRicerca(idAmbienteEventoOccasionale, "idEventoOccasionale", 2));
		
		where = where.substring(0, where.length() - 4);
		stampa("Query di where : "+where+"\n");
		query = query.concat(select.concat(target.concat(where)));
		String titolo = String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n","ID LUOGO", "ID A.Evento Occasionale","LUOGO APERTO", "DIMENSIONE", "NUMERO PARTECIPANTI", "NUMERO CIVICO","VIA");
		
		eseguiQueryRicerca(query, 7, titolo);
		
	}



	private void recuperaAmbienteEventoOccasionale(String idAmbienteEventoOccasionale) {
	
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Luogo, ambienteEventoOccasionale  WHERE fk_idluogo = idluogo AND ideventooccasionale  = "+formattaStringaConApici(idAmbienteEventoOccasionale));		
			if(rs.next()) {
				textViaAmbienteEventoOccasionale.setText(rs.getString("via"));
				textNumCivicoAmbienteEventoOccasionale.setText(rs.getString("n_civico"));
				textIDLuogoAmbienteEventoOccasionale.setText(rs.getString("idluogo"));
				if(rs.getInt("luogoAperto") == 0) {
					radioBLuogoApertoNo.setSelected(true);
					textDimensioniAmbienteEventoOccasionale.setDisable(false);
				}
				else {
					radioBLuogoApertoSi.setSelected(true);
					textDimensioniAmbienteEventoOccasionale.setDisable(true);
				}
				textDimensioniAmbienteEventoOccasionale.setText(rs.getString("dimensione"));
				if(rs.getInt("numeropartecipanti")==0)
					choicheBoxNumPartecipantiAmbienteEventoOccasionale.setValue(null);
				else
					choicheBoxNumPartecipantiAmbienteEventoOccasionale.setValue(rs.getInt("numeropartecipanti"));
				textIDAmbienteEventoOccasionale.setText(rs.getString("idEventoOccasionale"));
	
				setSituzioneAnnullaSalva();
				textViaAmbienteEventoOccasionale.setDisable(true);
				textNumCivicoAmbienteEventoOccasionale.setDisable(true);
				textIDLuogoAmbienteEventoOccasionale.setDisable(true);
				textIDAmbienteEventoOccasionale.setDisable(true);
			
			}
			else {
				console2.appendText("Impossibile recuperare un ambiente dell'evento occasionale, provare ad inserire ID_Ambiente evento occasionale correttamente\n");
				separatore();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			separatore();
		}
		
	}



	private void queryAggiungiAmbienteFamiliare(String via, String numeroCivico, String idLuogo, String tipo,
			String dimensione, String idAmbienteFamiliare) {
		String idLuogo_tmp = selectIdLuogoQuery(via, numeroCivico, idLuogo);
		if(!idLuogo_tmp.equals("")) {
			String query = "insert into ambienteFamiliare(idAmbienteFamiliare, dimensione, tipo, fk_idluogo) "
					+ "VALUES(" + formattaStringaConApici(idAmbienteFamiliare) + ", "
					+ formattaStringaConApici(dimensione) + ", "
					+ formattaStringaConApici(tipo) + ", "
					+ formattaStringaConApici(idLuogo_tmp)
					+ ")";
			eseguiQueryAggiunta(query);		
		}

	}
	private void querySalvaModificaAmbienteFamiliare(String via, String numeroCivico, String idLuogo, String tipo,
			String dimensione, String idAmbienteFamiliare) {
		String query = "UPDATE ambienteFamiliare SET dimensione = " + formattaStringaConApici(dimensione) + " WHERE idEventoOccasionale = " + idAmbienteFamiliare;	
		eseguiQueryModifica(query);
		ableCampiDatiAmbienteFamiliare();

	}



	private void queryRicercaAmbienteFamiliare(String via, String numeroCivico, String idLuogo, String tipo,
			String dimensione, String idAmbienteFamiliare) {
		String select = "Select l.idluogo, a.idAmbienteFamiliare, a.tipo, a.dimensione, l.n_civico, l.via ";
		String target = "from luogo l, ambienteFamiliare a ";
		String where = "where a.fk_idluogo = l.idluogo AND ";
		String query = "";
		
		where =	where.concat(controllaAttributoPerQueryRicerca(via, "via", 2));
		where =	where.concat(controllaAttributoPerQueryRicerca(numeroCivico, "n_civico", 2));
		where =	where.concat(controllaAttributoPerQueryRicerca(idLuogo, "idluogo", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(tipo, "tipo", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(dimensione, "dimensione", 2));
				
		where = where.concat(controllaAttributoPerQueryRicerca(idAmbienteFamiliare, "idAmbienteFamiliare", 2));
		where = where.substring(0, where.length() - 4);
		stampa("Query di where : "+where+"\n");
		query = query.concat(select.concat(target.concat(where)));
		String titolo = String.format("%-25s%-25s%-25s%-25s%-25s%-25s\n","ID LUOGO", "ID AMBIENTE FAMILIARE ", "TIPO", "DIMENSIONE", "NUMERO CIVICO","VIA");
		
		eseguiQueryRicerca(query, 6, titolo);
		
	}



	private void recuperaAmbienteFamiliare(String idAmbienteFamiliare) {

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Luogo, ambienteFamiliare  WHERE fk_idluogo = idluogo AND idAmbienteFamiliare  = "+formattaStringaConApici(idAmbienteFamiliare));
			
			if(rs.next()) {
				textViaAmbienteFamiliare.setText(rs.getString("via"));
				textNumCivicoAmbienteFamiliare.setText(rs.getString("n_civico"));
				textIDLuogoAmbienteFamiliare.setText(rs.getString("idluogo"));
				choicheBoxTipoAmbienteFamiliare.setValue(rs.getString("tipo"));
				textDimensioneAmbienteFamiliare.setText(rs.getString("dimensione"));
				textIDAmbienteFamiliare.setText(rs.getString("idAmbienteFamiliare"));
				
				setSituzioneAnnullaSalva();
				textViaAmbienteFamiliare.setDisable(true);
				textNumCivicoAmbienteFamiliare.setDisable(true);
				textIDLuogoAmbienteFamiliare.setDisable(true);
				choicheBoxTipoAmbienteFamiliare.setDisable(true);
				textIDAmbienteFamiliare.setDisable(true);
				
			}
			else {
				console2.appendText("Impossibile recuperare un ambiente familiare, provare ad inserire ID_Ambiene familiare correttamente\n");
				separatore();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			separatore();
		}
		
	}

	private void queryAggiungiDatiVisitaMedica(String nomeMedico, String nomeStruttura, String tipo, String esito,
			String data, String iDControlloMedico, String nome, String cognome, String codiceFiscale, String via,
			String numeroCivico, String idLuogo) {
		codiceFiscale = selectCodiceFiscaleQuery(nome, cognome, codiceFiscale);
		idLuogo = selectIdLuogoQuery(via, numeroCivico, idLuogo);
		
		if(!idLuogo.equals("") && !codiceFiscale.equals("")) {
			String query = "INSERT INTO CONTROLLOMEDICO (Nomemedico,nomeStruttura,tipo,esito,data,fk_idluogo,fk_idpersona,idcontrollomedico) VALUES ("
		+formattaStringaConApici(nomeMedico)+","
		+formattaStringaConApici(nomeStruttura)+","
		+formattaStringaConApici(tipo)+","
		+formattaStringaConApici(esito)+","
		+"TO_DATE("+formattaStringaConApici(data)+","+formattaStringaConApici("DD/MM/YYYY")+"),"
		+formattaStringaConApici(idLuogo)+","
		+formattaStringaConApici(codiceFiscale)+","
		+formattaStringaConApici(iDControlloMedico)+")";
			
			eseguiQueryAggiunta(query);
		
		}
	}
	
	
	private void querySalvaModificaDatiVisitaMedica(String nomeMedico, String nomeStruttura, String tipo, String esito,
			String data, String IDControlloMedico, String nome, String cognome, String codiceFiscale, String via, String numeroCivico,
			String idLuogo) {
		
		//codiceFiscale = selectCodiceFiscaleQuery(nome, cognome, codiceFiscale);
		//idLuogo = selectIdLuogoQuery(via, numeroCivico, idLuogo);
		if(!codiceFiscale.equals("") && !idLuogo.equals("")) {
			String query ="UPDATE CONTROLLOMEDICO SET "
					+ "nomemedico = "+formattaStringaConApici(nomeMedico)+","
					+ "nomestruttura = "+formattaStringaConApici(nomeStruttura)+","
					+ "tipo = "+formattaStringaConApici(tipo)+","
					+ "esito = "+formattaStringaConApici(esito)+","
					//+ "fk_idpersona = "+formattaStringaConApici(codiceFiscale)+","
					//+ "fk_idluogo = "+formattaStringaConApici(idLuogo)+","
					+ "data = TO_DATE("+formattaStringaConApici(data)+","+formattaStringaConApici("DD/MM/YYYY")+") "
					+ "WHERE IdControlloMedico = "+formattaStringaConApici(IDControlloMedico)
					;
			eseguiQueryModifica(query);
			
			textViaControlloMedico.setDisable(false);
			textNumCivicoControlloMedico.setDisable(false);
			textIDLuogoControlloMedico.setDisable(false);
			
			textNomeControlloMedico.setDisable(false);
			textCognomeControlloMedico.setDisable(false);
			textCodiceFiscaleControlloMedico.setDisable(false);
			
		}
		
	}
	

	private void queryRicercaDatiVisitaMedica(String nomeMedico, String nomeStruttura, String tipo, String esito,
			String data, String iDControlloMedico, String nome, String cognome, String codicefiscale, String via,
			String numeroCivico, String idLuogo) {
		String select = "Select idcontrollomedico, nome, cognome, fk_idpersona, tipo, esito, data, FK_IDLuogo, nomemedico, nomestruttura,  n_Civico, via ";
		String target = "from persona,controllomedico,luogo ";
		String where = "where fk_idpersona = codicefiscale AND IDLuogo = FK_IDLuogo AND ";
		String query = "";
		
		
		where=where.concat(controllaAttributoPerQueryRicerca(nome, "nome", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(cognome, "cognome", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(codicefiscale, "codicefiscale", 2));
		
		where=where.concat(controllaAttributoPerQueryRicerca(via, "via", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(numeroCivico, "N_Civico", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(idLuogo, "idLuogo", 2));
		
		where=where.concat(controllaAttributoPerQueryRicerca(tipo, "tipo", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(esito, "esito", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(nomeMedico, "nomeMedico", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(nomeStruttura, "nomestruttura", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(data, "data", 3));
		where=where.concat(controllaAttributoPerQueryRicerca(iDControlloMedico, "iDControlloMedico", 2));
		
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
		
		String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-35s%-25s%-25s\n","id controllomedico", "nome","cognome","codice fiscale","tipo", "esito","data",  "IDLuogo", "nome medico", "nome struttura", "numeroCivico", "via"));
		
		
		//eseguiQueryRicerca(query, 12 , titolo);
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()) {
				console2.appendText("Ricerca effettuata con successo, di seguito sono riportati i risultati\n\n");
				console2.appendText(titolo.toUpperCase());
				console2.appendText(String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-35s%-25s%-25s\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)));
				while(rs.next())
					console2.appendText(String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-35s%-25s%-25s\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)));
				
				separatore(titolo);
			}
			else{
				console2.appendText("Nessuna risultato trovato\n");
				separatore();	
			}
		} catch (SQLException e) {
			console2.appendText("Ricerca non riuscita\n");
			e.printStackTrace();
			console2.appendText(e.getMessage());
			separatore();	
		}	

		
	}



	private void recuperaDatiVisitaMedica(String iDControlloMedico) {
		String select = "Select idcontrollomedico, nome, cognome, fk_idpersona, tipo, esito, data, via, n_Civico, FK_IDLuogo, nomemedico, nomestruttura ";
		String target = "from persona,controllomedico,luogo ";
		String where = "where fk_idpersona = codicefiscale AND IDLuogo = FK_IDLuogo AND IDControllomedico ="+formattaStringaConApici(iDControlloMedico);
		String query = "";
		query = query.concat(select.concat(target.concat(where)));
		stampa(query);
		
		try {
			Statement stat = connection.createStatement();
			ResultSet rs = stat.executeQuery(query);
			if(rs.next()) {
				textIDControlloMedico.setText(rs.getString(1));
				textNomeControlloMedico.setText(rs.getString(2));
				textCognomeControlloMedico.setText(rs.getString(3));
				textCodiceFiscaleControlloMedico.setText(rs.getString(4));
				choiceBoxTipoControlloMedico.setValue(rs.getString(5));
				choiceBoxEsitoControlloMedico.setValue(rs.getString(6));
				datePickerDataControlloMedico.setValue(rs.getDate(7).toLocalDate());
				textViaControlloMedico.setText(rs.getString(8));
				textNumCivicoControlloMedico.setText(rs.getString(9));
				textIDLuogoControlloMedico.setText(rs.getString(10));
				textNomeMedicoControlloMedico.setText(rs.getString(11));
				textNomeOspedaleControlloMedico.setText(rs.getString(12));
			
				
				
				textViaControlloMedico.setDisable(true);
				textNumCivicoControlloMedico.setDisable(true);
				textIDLuogoControlloMedico.setDisable(true);
				textIDControlloMedico.setDisable(true);
				textNomeControlloMedico.setDisable(true);
				textCognomeControlloMedico.setDisable(true);
				textCodiceFiscaleControlloMedico.setDisable(true);
				
				setSituzioneAnnullaSalva();
			}
			else {
				console2.appendText("Impossibile recuperare una visita medica, provare ad inserire il codice della visita medica correttamente\n");
				separatore();
			}
		} catch (SQLException e) {
			console2.appendText(e.getMessage());
		}
	}


	


	private void queryAggiungiDatiVisita(String nome, String cognome, String codicefiscale, String via,
			String numero_civico, String id_luogo, String data, String orario) {
		codicefiscale = selectCodiceFiscaleQuery(nome, cognome, codicefiscale);
		id_luogo = selectIdLuogoQuery(via, numero_civico, id_luogo);
		
		if(!codicefiscale.equals("") && !id_luogo.equals("")) {
			String query = "INSERT INTO AFFLUENZA (fk_idpersona,fk_idluogo, data) VALUES ("
			+formattaStringaConApici(codicefiscale)+","
			+formattaStringaConApici(id_luogo)+","
			+"TO_TIMESTAMP('"+data +" " +orario +":00.000000000', 'DD-MM-YYYY HH24:MI:SS.FF'))";
			
				
			if(data != null && orario.length()==5)	
				eseguiQueryAggiunta(query);
			
			else {
				console2.appendText("Impossibile effettuare l'aggiunta, campi orario-data errati o mancanti\n");
				console2.appendText("Formato corretto : Data   [DD/MM/YYYY]\n");
				console2.appendText("Formato corretto : Orario [HH:MM]\n");
				separatore();
				
			}
		}		
	}
	

	private void queryRicercaDatiVisita(String nome, String cognome, String codicefiscale, String via,
			String numero_civico, String id_luogo, String data, String orario) {
		String select = "Select nome, cognome, fk_idpersona, data, FK_IDLuogo, n_Civico ,via ";
		String target = "from persona,luogo,affluenza ";
		String where = "where fk_idpersona = codicefiscale AND IDLuogo = FK_IDLuogo AND ";
		String query = "";
		
		where=where.concat(controllaAttributoPerQueryRicerca(nome, "nome", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(cognome, "cognome", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(codicefiscale, "codicefiscale", 2));
		
		where=where.concat(controllaAttributoPerQueryRicerca(via, "via", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(numero_civico, "N_Civico", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(id_luogo, "idLuogo", 2));
		
		if(data != null) {
			where = where.concat("EXTRACT(YEAR FROM TO_DATE(' "+data+"','DD-MM-YYYY')) = EXTRACT(YEAR FROM data) AND ");
			where = where.concat("EXTRACT(MONTH FROM TO_DATE(' "+data+"','DD-MM-YYYY')) = EXTRACT(MONTH FROM data) AND ");
			where = where.concat("EXTRACT(DAY FROM TO_DATE(' "+data+"','DD-MM-YYYY')) = EXTRACT(DAY FROM data) AND ");
		}
		
		if(!orario.equals("") && orario.length()==5){
			where = where.concat("EXTRACT(HOUR FROM data) = "+orario.substring(0,2)+" AND ");
			where = where.concat("EXTRACT(MINUTE FROM data) = "+orario.substring(3,5)+" AND ");
		}
		
	
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
		
		
		if(orario.length() == 0 || orario.length() == 5) {
			String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n","nome","cognome","Codice fiscale ","data", "IDLuogo", "numeroCivico", "via"));
			eseguiQueryRicerca(query, 7 , titolo);	
		}
		else {
			console2.appendText("Impossibile effettuare la ricerca, formato dell'orario errato\n");
			console2.appendText("Formato corretto : Orario [HH:MM]\n");
			
			separatore();
		}
	}



	private void queryAggiungiFrequentazioneIstituzionale(String nome, String cognome, String id_studente, String via,
			String numero_civico, String id_ambiente_studio) {
		id_studente = selectIDStudenteQuery(nome, cognome, id_studente);
		id_ambiente_studio = selectIDAmbienteDiStudio(via, numero_civico, id_ambiente_studio);
		if(!id_studente.equals("") && !id_ambiente_studio.equals("")) {
			String query = "INSERT INTO FREQUENTAZIONE (FK_IDStudente,FK_idAmbientediStudio) VALUES ("
					+formattaStringaConApici(id_studente)+","
					+formattaStringaConApici(id_ambiente_studio)+")";
			eseguiQueryAggiunta(query);
		}
			
	}

	
	private void queryRicercaFrequentazioneIstituzionale(String nome, String cognome, String id_studente, String via,
			String numero_civico, String id_ambiente_studio) {
		String select = "Select  idStudente, nome, cognome, idAmbienteDiStudio, n_Civico, via ";
		String target = "from persona,studente,Frequentazione, AmbienteDiStudio, Luogo ";
		String where = "where fk_idpersona = codicefiscale "
				+ "AND IDLuogo = FK_IDLuogo "
				+ "AND Fk_IDstudente = IDStudente "
				+ "AND Fk_IDAmbienteDistudio = IDAmbienteDiStudio "
				+ "AND ";
		String query = "";
		where=where.concat(controllaAttributoPerQueryRicerca(nome, "nome", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(cognome, "cognome", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(id_studente, "idstudente", 2));
		
		where=where.concat(controllaAttributoPerQueryRicerca(via, "via", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(numero_civico, "N_Civico", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(id_ambiente_studio, "IDAmbienteDiStudio", 2));

		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
		
		String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s%-25s\n","ID Studente ","nome","cognome",  "ID ambiente di studio" , "Numero Civico" ,  "via"));
		eseguiQueryRicerca(query, 6, titolo);
	}

	
	private void queryAggiungiAbitudiniLavorative(String nome, String cognome, String id_lavoratore, String via,
			String numero_civico,String idAmbienteLavoro , String data, String orarioIngresso, String orarioUscita) {
		id_lavoratore = selectIDLavoratore(nome, cognome, id_lavoratore);
		idAmbienteLavoro = selectIDAmbienteLavoroQuery(via, numero_civico, idAmbienteLavoro);
		if(!id_lavoratore.equals("") && !idAmbienteLavoro.equals("")) {
			String query = "INSERT INTO Lavoro (FK_IDLavoratore,FK_idAmbienteDiLavoro, orarioIngresso, OrarioUscita) VALUES ("
					+formattaStringaConApici(id_lavoratore)+","
					+formattaStringaConApici(idAmbienteLavoro)+","
					+"TO_TIMESTAMP('"+data +" " +orarioIngresso +":00.000000000', 'DD-MM-YYYY HH24:MI:SS.FF')"+","
					+"TO_TIMESTAMP('"+data +" " +orarioUscita+":00.000000000', 'DD-MM-YYYY HH24:MI:SS.FF'))";
			
			if(data != null && orarioIngresso.length()==5 && orarioUscita.length()==5)	
				eseguiQueryAggiunta(query);
			
			else {
				console2.appendText("Impossibile effettuare l'aggiunta, campi orario-data errati o mancanti\n");
				console2.appendText("Formato corretto : Data   [DD/MM/YYYY]\n");
				console2.appendText("Formato corretto : Orario [HH:MM]\n");
				separatore();
				
			}
		}
		
		
	}

	private void queryRicercaAbitudiniLavorative(String nome, String cognome, String id_lavoratore, String via,
			String numero_civico, String idAmbienteLavoro ,String data, String orarioIngresso, String orarioUscita) {
		
		String select = "Select  idLavoratore, nome, cognome, idAmbienteLavoro, n_Civico, via, orarioingresso, orariouscita ";
		String target = "from persona,lavoratore, Lavoro , AmbienteLavoro, Luogo ";
		String where = "where fk_idpersona = codicefiscale "
				+ "AND IDLuogo = FK_IDLuogo "
				+ "AND Fk_IDLavoratore = IDlavoratore "
				+ "AND Fk_IDAmbienteDiLavoro = IDAmbienteLavoro "
				+ "AND ";
		String query = "";
		where=where.concat(controllaAttributoPerQueryRicerca(nome, "nome", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(cognome, "cognome", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(id_lavoratore, "IDlavoratore", 2));
		
		where=where.concat(controllaAttributoPerQueryRicerca(via, "via", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(numero_civico, "N_Civico", 2));
		where=where.concat(controllaAttributoPerQueryRicerca(idAmbienteLavoro, "idAmbienteLavoro", 2));
		
		if(data != null) {
			where = where.concat("EXTRACT(YEAR FROM TO_DATE(' "+data+"','DD-MM-YYYY')) = EXTRACT(YEAR FROM ORARIOINGRESSO) AND ");
			where = where.concat("EXTRACT(MONTH FROM TO_DATE(' "+data+"','DD-MM-YYYY')) = EXTRACT(MONTH FROM ORARIOINGRESSO) AND ");
			where = where.concat("EXTRACT(DAY FROM TO_DATE(' "+data+"','DD-MM-YYYY')) = EXTRACT(DAY FROM ORARIOINGRESSO) AND ");
		}
		
		if(!orarioIngresso.equals("")){
			if(orarioIngresso.length()==5) {
			where = where.concat("EXTRACT(HOUR FROM ORARIOINGRESSO) = "+orarioIngresso.substring(0,2)+" AND ");
			where = where.concat("EXTRACT(MINUTE FROM ORARIOINGRESSO) = "+orarioIngresso.substring(3,5)+" AND ");
			}
		}
		
		if(!orarioUscita.equals("")){
			if(orarioUscita.length()==5) {
			where = where.concat("EXTRACT(HOUR FROM ORARIOUSCITA) = "+orarioUscita.substring(0,2)+" AND ");
			where = where.concat("EXTRACT(MINUTE FROM ORARIOUSCITA) = "+orarioUscita.substring(3,5)+" AND ");
			}
		}
	
		
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
		
		if((orarioUscita.length() == 5 || orarioUscita.length() == 0)  && (orarioIngresso.length() == 5 || orarioIngresso.length() == 0)) {
			String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n","ID Lavoratore ","nome","cognome",  "ID ambiente di lavoro" , "Numero Civico" ,  "via", "Orario di ingresso", "Orario di uscita"));
			eseguiQueryRicerca(query, 8, titolo);
		}
		else {
			console2.appendText("Impossibile effettuare la ricerca, formato dell'orario errato\n");
			console2.appendText("Formato corretto : Orario [HH:MM]\n");
			separatore();
		}
	}

	private void queryAggiungiVaccino(String idVaccino, String enteRilascio, String controindicazioni, String tipoVaccino,
			Integer periodoUtile) {
		String query = "INSERT INTO VACCINO (IDVACCINO, ENTEDIRILASCIO, CONTROINDICAZIONI, TIPO, PERIODOUTILE) VALUES ("
				+formattaStringaConApici(idVaccino)+","
				+formattaStringaConApici(enteRilascio)+","
				+formattaStringaConApici(controindicazioni)+","
				+formattaStringaConApici(tipoVaccino)+","
				+periodoUtile+")";
				
		eseguiQueryAggiunta(query);

	}


	private void querySalvaModificaVaccino(String idVaccino, String enteRilascio, String controindicazioni,
			String tipoVaccino, Integer periodoUtile) {
		String query = "UPDATE VACCINO SET "
				+ "enteDIRilascio=" + formattaStringaConApici(enteRilascio)+","
				+ "controindicazioni=" + formattaStringaConApici(controindicazioni)+","
				+ "tipo=" + formattaStringaConApici(tipoVaccino)+","
				+ "periodoUtile=" + periodoUtile 
				+ " WHERE idVaccino = " +formattaStringaConApici(idVaccino);
		
		eseguiQueryModifica(query);
		textIDVaccinoDatiVaccino.setDisable(false);
	}
	
	private void queryRicercaVaccino(String idVaccino, String enteRilascio, String controindicazioni,
			String tipoVaccino, Integer periodoUtile) {
		String select = "Select  idVaccino,  enteDIRilascio,  controindicazioni, periodoutile , tipo ";
		String target = "from VACCINO ";
		String where = "where "; 
		String query = "";
		
		where = where.concat(controllaAttributoPerQueryRicerca(idVaccino, "idVaccino", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(enteRilascio, "enteDIRilascio", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(controindicazioni, "controindicazioni", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(tipoVaccino, "tipo", 2));
		if(periodoUtile != null)
		where = where.concat("PeriodoUtile = "+ periodoUtile +"AND ");
		
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
		String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s\n","ID Vaccino ","Ente di rilascio","controindicazioni",  "periodo utile" , "Tipo di Vaccino"));
		
		eseguiQueryRicerca(query, 5, titolo);
	}

	private void recuperaVaccino(String idVaccino) {
		String query = "SELECT * FROM VACCINO WHERE IDVACCINO = "+formattaStringaConApici(idVaccino);
		
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()) {
				textIDVaccinoDatiVaccino.setText(rs.getString("IDVACCINO"));
				textEnteRilascioDatiVaccino.setText(rs.getString("ENTEDIRILASCIO"));
				textControindicazioniDatiVaccino.setText(rs.getString("CONTROINDICAZIONI"));
				periodoUtileChoiceBox.setValue(rs.getInt("PERIODOUTILE"));
				tipoVaccinoChoiceBox.setValue(rs.getString("TIPO"));
			
				textIDVaccinoDatiVaccino.setDisable(true);
				setSituzioneAnnullaSalva();
			}
			else {
				console2.appendText("Impossibile recuperare un vaccino, provare ad inserire il codice del vaccino correttamente\n");
				separatore();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			console2.appendText(e.getMessage());
		}
		
		
	}


	private void queryAggiungiSomministrazione(String nome, String cognome, String codiceFiscale, String idVaccino, String data, String complicazioni) {
		codiceFiscale = selectCodiceFiscaleQuery(nome, cognome, codiceFiscale);
		
		if(!codiceFiscale.equals("")) {
			String query="INSERT INTO SOMMINISTRAZIONE (FK_IDPERSONA,FK_IDVACCINO,COMPLICAZIONI,DATA) VALUES ("
					+ formattaStringaConApici(codiceFiscale)+","
					+ formattaStringaConApici(idVaccino)+","
					+ formattaStringaConApici(complicazioni)+","
					+"TO_DATE("+formattaStringaConApici(data)+",'DD-MM-YYYY'))"
					;
		
			eseguiQueryAggiunta(query);
		}

	}
	
	private void queryRicercaSomministrazione(String nome, String cognome, String codiceFiscale, String idVaccino, String data, String complicazioni) {
		String select = "Select  IDVACCINO , NOME, COGNOME, CODICEFISCALE, COMPLICAZIONI, DATA ";
		String target = "from VACCINO , SOMMINISTRAZIONE, PERSONA ";
		String where = "where IDVACCINO = FK_IDVACCINO AND CODICEFISCALE = FK_IDPERSONA AND "; 
		String query = "";
		
		where = where.concat(controllaAttributoPerQueryRicerca(nome, "nome", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(cognome, "cognome", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale, "codiceFiscale", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(idVaccino, "idVaccino", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(complicazioni, "complicazioni", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(data, "data", 3));
		

		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
		String titolo = (String.format("%-25s%-25s%-25s%-25s%-25s%-25s\n","IDVACCINO","NOME","COGNOME",  "CODICEFISCALE" , "COMPLICAZIONI","DATA" ));

		//eseguiQueryRicerca(query, 6, titolo);
		stampa("Query di ricerca : "+query+"\n");
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()) {
				console2.appendText("Ricerca effettuata con successo, di seguito sono riportati i risultati\n\n");
				console2.appendText(titolo.toUpperCase());
				console2.appendText(String.format("%-25s%-25s%-25s%-25s%-25s%-25s\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6)));
				while(rs.next())
					console2.appendText(String.format("%-25s%-25s%-25s%-25s%-25s%-25s\n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6)));				
				separatore(titolo);
			}
			else{
				console2.appendText("Nessuna risultato trovato\n");
				separatore();	
			}
		} catch (SQLException e) {
			console2.appendText("Ricerca non riuscita\n");
			e.printStackTrace();
			console2.appendText(e.getMessage());
			separatore();	
		}	
		
		

	}



	public String selectCodiceFiscaleQuery( String nome, String cognome, String codiceFiscale)  {
		String select = "select * ";
		String target = "from persona ";
		String where = "where ";
		String query = "";
		where = where.concat(controllaAttributoPerQueryRicerca(nome, "nome ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(cognome, "cognome ", 2));	
		where = where.concat(controllaAttributoPerQueryRicerca(codiceFiscale, "codiceFiscale ", 2));
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
		Statement statement;
		ResultSet rs ;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs =  statement.executeQuery(query);
			rs.last();

			if(rs.getRow() == 0) {
				console2.appendText("-- Attenzione: nessuna persona trovata corrispondente ai dati indicati--\n");
				separatore();
				return "";
			}
			else if(rs.getRow() >= 2 ) {
				
				console2.appendText("-- Attenzione ambiguità: Ci sono più persone con i dati inseriti, ripetere l'operazione specificando anche il Codice Fiscale --\n");
				console2.appendText("-- Di seguito sono riportate le ambiguità riscontrate --\n");
				separatore();
				rs.beforeFirst();
				String titolo = String.format("%-15s %-15s %-20s %-25s %-18s %-10s %-30s %-15s %-30s %-15s\n","nome", "cognome","codice Fiscale","comune di Nascita","residenza","sesso","mail1","telefono1","mail2","telefono2");
				console2.appendText(titolo.toUpperCase());
				while(rs.next()) {
					String string = String.format("%-15s %-15s %-20s %-25s %-18s %-10s %-30s %-15s %-30s %-15s\n",rs.getString("nome"), rs.getString("cognome"),rs.getString("codiceFiscale"),rs.getString("comuneNascita"),rs.getString("residenza"),rs.getString("sesso"),rs.getString("mail1"),rs.getString("telefono1"),rs.getString("mail2"),rs.getString("telefono2"));
					console2.appendText(string);
				}
				separatore(titolo);
			
				return "";
			}
			else {
				return rs.getString("codicefiscale");
			}
			
		} catch (SQLException e) {
			console2.appendText("Errore select codice fiscale\n");
			separatore();

			e.printStackTrace();
		}
		
		return "";
	}

	public String selectIdLuogoQuery(String via, String n_civico, String idluogo) {
		String select = "select * ";
		String target = "from luogo ";
		String where = "where ";
		String query = "";
		where = where.concat(controllaAttributoPerQueryRicerca(via, "via ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(n_civico, "n_civico ", 2));	
		where = where.concat(controllaAttributoPerQueryRicerca(idluogo, "idluogo ", 2));
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
		Statement statement;
		ResultSet rs ;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs =  statement.executeQuery(query);
			rs.last();

			if(rs.getRow() == 0) {
				console2.appendText("-- Attenzione: nessun luogo trovato corrispondente ai dati indicati --\n");
				separatore();
				return "";
			}
			else if(rs.getRow() >= 2 ) {
				console2.appendText("-- Attenzione ambiguità: Ci sono più Luoghi con i dati inseriti, ripetere l'operazione specificando anche ID_Luogo --\n");
				console2.appendText("-- Di seguito sono riportate le ambiguità riscontrate --\n");
				
				separatore();
				rs.beforeFirst();
				String titolo = String.format("%-25s%-25s%-25s%-25s%-25s%-25s\n","idLuogo","città", "provincia","cap", "numero Civico", "via");
				console2.appendText(titolo.toUpperCase());
				while(rs.next()) {
					String string = String.format("%-25s%-25s%-25s%-25s%-25s%-25s\n",rs.getString("idLuogo"),rs.getString("città"), rs.getString("provincia"),rs.getString("cap"), rs.getString("n_Civico"), rs.getString("via"));
					console2.appendText(string);
				}
				separatore(titolo);
				return "";
			}
			else {
				return rs.getString("idLuogo");
			}
			
		} catch (SQLException e) {
			console2.appendText("Errore select idLuogo\n");
			separatore();

			e.printStackTrace();
		}
		
		return "";
	}
	
	public String selectIDStudenteQuery(String nome, String cognome, String idStudente){
		String select = "select idStudente, nome , cognome  ";
		String target = "from studente,persona ";
		String where = "where fk_idpersona = codicefiscale AND ";
		String query= "";
		where = where.concat(controllaAttributoPerQueryRicerca(nome, "nome ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(cognome , "cognome ", 2));	
		where = where.concat(controllaAttributoPerQueryRicerca(idStudente, "idStudente ", 2));
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
	
		Statement statement;
		ResultSet rs ;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs =  statement.executeQuery(query);
			rs.last();

			if(rs.getRow() == 0) {
				console2.appendText("-- Attenzione: nessuno studente trovato corrispondente ai dati indicati --\n");
				separatore();
				return "";
			}
			else if(rs.getRow() >= 2 ) {
				console2.appendText("-- Attenzione ambiguità: Ci sono più studenti con i dati inseriti, ripetere l'operazione specificando anche ID_Studente --\n");
				console2.appendText("-- Di seguito sono riportate le ambiguità riscontrate --\n");
				separatore();
				rs.beforeFirst();
				String titolo = String.format("%-25s%-25s%-25s\n","idStudente","nome", "cognome");
				console2.appendText(titolo.toUpperCase());
				while(rs.next()) {
					String string = String.format("%-25s%-25s%-25s\n",rs.getString("idstudente"),rs.getString("nome"), rs.getString("cognome"));
					console2.appendText(string);
				}
				separatore(titolo);
				return "";
			}
			else {
				return rs.getString("idStudente");
			}
			
		} catch (SQLException e) {
			console2.appendText("Errore select idStudente\n");
			separatore();
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	public String selectIDLavoratore(String nome, String cognome, String idLavoratore) {
		String select = "select idLavoratore, nome , cognome  ";
		String target = "from lavoratore,persona ";
		String where = "where fk_idpersona = codicefiscale AND ";
		String query= "";
		where = where.concat(controllaAttributoPerQueryRicerca(nome, "nome ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(cognome , "cognome ", 2));	
		where = where.concat(controllaAttributoPerQueryRicerca(idLavoratore, "idLavoratore ", 2));
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
	
		Statement statement;
		ResultSet rs ;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs =  statement.executeQuery(query);
			rs.last();

			if(rs.getRow() == 0) {
				console2.appendText("-- Attenzione: nessun lavoratore trovato corrispondente ai dati indicati --\n");
				separatore();
				return "";
			}
			else if(rs.getRow() >= 2 ) {
				console2.appendText("-- Attenzione ambiguità: Ci sono più lavoratori con i dati inseriti, ripetere l'operazione specificando anche ID_Lavoratore --\n");
				console2.appendText("-- Di seguito sono riportate le ambiguità riscontrate --\n");
				separatore();
				rs.beforeFirst();
				String titolo = String.format("%-25s%-25s%-25s\n","idLavoratore","nome", "cognome");
				console2.appendText(titolo.toUpperCase());
				while(rs.next()) {
					String string = String.format("%-25s%-25s%-25s\n",rs.getString("idLavoratore"),rs.getString("nome"), rs.getString("cognome"));
					console2.appendText(string);
				}
				separatore(titolo);
				return "";
			}
			else {
				return rs.getString("idLavoratore");
			}
			
		} catch (SQLException e) {
			console2.appendText("Errore select idLavoratore\n");
			separatore();
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String selectIDAmbienteLavoroQuery(String via , String numeroCivico, String idAmbienteLavoro){
		String select = "select idAmbienteLavoro , via , n_civico ";
		String target = "from luogo,ambienteLavoro ";
		String where = "where fk_idluogo = idLuogo AND ";
		String query= "";
		where = where.concat(controllaAttributoPerQueryRicerca(via, "via ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(numeroCivico , "n_civico ", 2));	
		where = where.concat(controllaAttributoPerQueryRicerca(idAmbienteLavoro, "IDambienteLavoro ", 2));
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
		stampa(query);
		Statement statement;
		ResultSet rs ;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs =  statement.executeQuery(query);
			rs.last();

			if(rs.getRow() == 0) {
				console2.appendText("-- Attenzione: nessun ambiente di lavoro trovato corrispondente ai dati indicati --\n");
				separatore();
				return "";
			}
			else if(rs.getRow() >= 2 ) {
				console2.appendText("-- Attenzione ambiguità: Ci sono più ambiente di lavoro con i dati inseriti, ripetere l'operazione specificando anche ID_AmbienteLavorativo --\n");
				console2.appendText("-- Di seguito sono riportate le ambiguità riscontrate --\n");
				separatore();
				rs.beforeFirst();
				String titolo = String.format("%-25s%-25s%-25s\n","IDambienteLavoro","via", "numero civico");
				console2.appendText(titolo.toUpperCase());
				while(rs.next()) {
					String string = String.format("%-25s%-25s%-25s\n",rs.getString("IDambienteLavoro"),rs.getString("via"), rs.getString("n_civico"));
					console2.appendText(string);
				}
				separatore(titolo);
				return "";
			}
			else {
				return rs.getString("IDambienteLavoro");
			}
			
		} catch (SQLException e) {
			console2.appendText("Errore select IDAmbienteLavoro\n");
			separatore();
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String selectIDAmbienteDiStudio(String via , String numeroCivico, String idAmbienteDiStudio){
		String select = "select idAmbienteDiStudio , via , n_Civico ";
		String target = "from luogo,AmbienteDiStudio ";
		String where = "where fk_idluogo = idLuogo AND ";
		String query= "";
		where = where.concat(controllaAttributoPerQueryRicerca(via, "via ", 2));
		where = where.concat(controllaAttributoPerQueryRicerca(numeroCivico , "n_civico ", 2));	
		where = where.concat(controllaAttributoPerQueryRicerca(idAmbienteDiStudio, "idAmbienteDiStudio ", 2));
		where = where.substring(0, where.length() - 4);
		query = query.concat(select.concat(target.concat(where)));
	
		Statement statement;
		ResultSet rs ;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs =  statement.executeQuery(query);
			rs.last();

			if(rs.getRow() == 0) {
				console2.appendText("-- Attenzione: nessun ambiente di studio trovato corrispondente ai dati indicati --\n");
				separatore();
				return "";
			}
			else if(rs.getRow() >= 2 ) {
				console2.appendText("-- Attenzione ambiguità: Ci sono più ambiente di studio con i dati inseriti, ripetere l'operazione specificando anche ID_Ambiente Studio --\n");
				console2.appendText("-- Di seguito sono riportate le ambiguità riscontrate --\n");
				separatore();
				rs.beforeFirst();
				String titolo = String.format("%-25s%-25s%-25s\n","ID Ambiente Di Studio","via", "numero civico");
				console2.appendText(titolo.toUpperCase());
				while(rs.next()) {
					String string = String.format("%-25s%-25s%-25s\n",rs.getString("idAmbienteDiStudio"),rs.getString("via"), rs.getString("n_civico"));
					console2.appendText(string);
				}
				separatore(titolo);
				return "";
			}
			else {
				return rs.getString("idAmbienteDiStudio");
			}
			
		} catch (SQLException e) {
			console2.appendText("Errore select idAmbienteDiStudio\n");
			separatore();
			e.printStackTrace();
		}
		
		return "";
	}


	public String formattaDataPerQuery(DatePicker dataDaFormattare) {
		String dataFormattata = null; 
		if(dataDaFormattare.getValue()!=null) {
			int numeroGiorno = dataDaFormattare.getValue().getDayOfMonth();  
			int numeroAnno = dataDaFormattare.getValue().getYear();
			//String nomeMeseNascita = dataDaFormattare.getValue().getMonth().name().substring(0, 3); //Mese in inglese 
			int numeroMese = dataDaFormattare.getValue().getMonthValue();
			dataFormattata =numeroGiorno+"-"+numeroMese+"-"+numeroAnno;
			//dataFormattata = numeroAnno+"-"+numeroMese+"-"+numeroGiorno;
		}
		return dataFormattata;
	}

	public String formattaStringaConApici(String stringaDaFormattare) {
		if(stringaDaFormattare==null || stringaDaFormattare.equals(""))
			return "null";
		else
			return "'"+controllaStringaApice(stringaDaFormattare)+"'";		  
			
	}
	
	public String controllaStringaApice(String stringa) {
		String stringaDaRestituire = "";
		String tmp;
		if(stringa.contains("'")) {
			for(int i = 0 ; i<stringa.length() ; i++) {
				tmp = stringa.substring(i, i+1) ; 
				if(tmp.equals("'")) {
					stringaDaRestituire = stringaDaRestituire.concat("''");
				}
				else{
					stringaDaRestituire = stringaDaRestituire.concat(tmp);
				}
			}
			return stringaDaRestituire;
		}
		else {
			return stringa;
		}
	}
	
	public String formattaTipoControlloMedico() {
		if(choiceBoxTipoControlloMedico.getValue()!=null) {
			if(choiceBoxTipoControlloMedico.getValue().equals("Test Sierologico"))
				return "TestSierologico";
			else {
				return choiceBoxTipoControlloMedico.getValue();
			}
		}
		return "";
	}

	public String formattaGiorniLavorativi() {
		String giorniLavorativi = "";
		
		if (checkBoxLunediLavoratore.isSelected()) giorniLavorativi = giorniLavorativi.concat("Lunedì, ");
		if (checkBoxMartediLavoratore.isSelected()) giorniLavorativi = giorniLavorativi.concat("Martedì, ");
		if (checkBoxMercolediLavoratore.isSelected()) giorniLavorativi = giorniLavorativi.concat("Mercoledì, ");
		if (checkBoxGiovediLavoratore.isSelected()) giorniLavorativi = giorniLavorativi.concat("Giovedì, ");
		if (checkBoxVenerdiLavoratore.isSelected()) giorniLavorativi = giorniLavorativi.concat("Venerdì, ");
		if (checkBoxSabatoLavoratore.isSelected()) giorniLavorativi = giorniLavorativi.concat("Sabato, ");
		if (checkBoxDomenicaLavoratore.isSelected()) giorniLavorativi = giorniLavorativi.concat("Domenica, ");
		
		//Toglie la virgola se necessario
		if(!giorniLavorativi.equals(""))
			giorniLavorativi = giorniLavorativi.substring(0, giorniLavorativi.length()-2);
		
		return giorniLavorativi;
	}

	public String formattaParentela() {
		String tipoParentela =""; 
		if(sceltaParentela.getValue()!=null) {

			if(sceltaParentela.getValue().equals("E' Marito di")){
				tipoParentela = "Marito";
			}
			else if(sceltaParentela.getValue().equals("E' Moglie di")) {
				tipoParentela = "Moglie";
			}
			else if(sceltaParentela.getValue().equals("E' Figlio di")) {
				tipoParentela = "Figlio";
			}
			else if(sceltaParentela.getValue().equals("E' Figlia di")) {
				tipoParentela = "Figlia";
			}
			else if(sceltaParentela.getValue().equals("E' Padre di")) {
				tipoParentela = "Padre";
			}
			else if(sceltaParentela.getValue().equals("E' Madre di")) {
				tipoParentela = "Madre";
			}
			else if(sceltaParentela.getValue().equals("E' Zia di")) {
				tipoParentela = "Zia";
			}
			else if(sceltaParentela.getValue().equals("E' Zio di")) {
				tipoParentela = "Zio";
			}
			else if(sceltaParentela.getValue().equals("E' Nonno di")) {
				tipoParentela = "Nonno";
			}
			else if(sceltaParentela.getValue().equals("E' Nonna di")) {
				tipoParentela = "Nonna";
			}
			else if(sceltaParentela.getValue().equals("E' Cugino di")) {
				tipoParentela = "Cugino";
			}
			else if(sceltaParentela.getValue().equals("E' Cugina di")) {
				tipoParentela = "Cugina";
			}
			else if(sceltaParentela.getValue().equals("E' Sorella di")) {
				tipoParentela = "Sorella";
			}
			else if(sceltaParentela.getValue().equals("E' Fratello di")) {
				tipoParentela = "Fratello";
			}

		}			return tipoParentela;
	}


	//1 per select 
	//2 per where
	//3 per where con date
	public String controllaAttributoPerQueryRicerca(String stringaDaControllare,String attributoDaConfrontare,int mod) {
		if(stringaDaControllare!=null && !stringaDaControllare.equals("")) {
			if(mod == 1)
				return attributoDaConfrontare+" ," ;
			if(mod == 2)
				return attributoDaConfrontare +"= "+formattaStringaConApici(stringaDaControllare)+ " AND " ;
			if(mod == 3)
				return attributoDaConfrontare +"= "+ "TO_DATE(" +formattaStringaConApici(stringaDaControllare) + ", 'DD/MM/YYYY') AND "; 
			else 
				return "";
		}
		else
			return "";
	}

	public void separatore() {
		console2.appendText("-----------------------------------------------------------------------------------------------------------------------------\n");
	}
	public void separatore(String titolo) {
		String linea = "";
		while(linea.length() < titolo.length())
			linea = linea + "-";
		linea = linea + "\n";
		console2.appendText(linea);
	}

	public void setSituzioneAnnullaSalva() {
		buttonSalva.setDisable(false);
		buttonAnnulla.setDisable(false);
		buttonModifica.setDisable(true);
		buttonRicerca.setDisable(true);
		buttonAggiungi.setDisable(true);
		buttonPulisci.setDisable(true);
	}

	public void setSituazioneStandard() {
		buttonSalva.setDisable(true);
		buttonAnnulla.setDisable(true);
		buttonModifica.setDisable(false);
		buttonRicerca.setDisable(false);
		buttonAggiungi.setDisable(false);
		buttonPulisci.setDisable(false);
	}
	
	
	public void setGiorniLavorativi(String giorniLavorativi) {
        if(giorniLavorativi.contains("Lunedì") == true) 
            checkBoxLunediLavoratore.setSelected(true);
         if(giorniLavorativi.contains("Martedì") == true)
            checkBoxMartediLavoratore.setSelected(true);
         if(giorniLavorativi.contains("Mercoledì") == true)
            checkBoxMercolediLavoratore.setSelected(true);
         if(giorniLavorativi.contains("Giovedì") == true)
            checkBoxGiovediLavoratore.setSelected(true);
         if(giorniLavorativi.contains("Venerdì") == true)
            checkBoxVenerdiLavoratore.setSelected(true);
         if(giorniLavorativi.contains("Sabato") == true)
            checkBoxSabatoLavoratore.setSelected(true);
         if(giorniLavorativi.contains("Domenica") == true)
            checkBoxDomenicaLavoratore.setSelected(true);

    }

	public static void stampa(Object o) {
		if (o != null) 
			System.out.println(o.toString());	
		else
			System.out.println(o);
	}
	
	public void eseguiQueryAggiunta(String query) {
		try {	
			Statement statement = connection.createStatement();
			stampa("Query da inserimento:" + query); 
			statement.executeQuery(query);
			console2.appendText("Inserimento è avvenuto con successo\n");
			separatore();
		}


		catch (SQLException e) {
			console2.appendText("Inserimento NON è avvenuto con successo\n");
			console2.appendText(e.getMessage());
			e.printStackTrace();
			separatore();
		}
	}

	public void eseguiQueryModifica(String query) {
		try {

			Statement statement = connection.createStatement();
			stampa("Query da modifica:"+ query);
			statement.executeUpdate(query);
			console2.appendText("Modifica avvenuta con successo \n");
			separatore();

		} catch (SQLException e) {
			console2.appendText("Modifica NON è avvenuta con successo \n");
			console2.appendText(e.getMessage());
			separatore();
		}
	}

	public void eseguiQueryRicerca(String query, int k , String titolo) {
		try {

			stampa("Query di ricerca : "+query+"\n");
			Statement stat = connection.createStatement();
			ResultSet rs = stat.executeQuery(query);

			if(rs.next()==false) {
				console2.appendText("Nessuna risultato trovato\n");
				separatore();
			}

			else {
				console2.appendText("Ricerca effettuata con successo, di seguito sono riportati i risultati\n\n");	
				console2.appendText(titolo.toUpperCase());
				for(int i = 1 ; i<=k ; i++  ) {
					console2.appendText(String.format("%-25s",rs.getString(i)));
				}
				console2.appendText("\n");

				while (rs.next()) {
					for(int i = 1 ; i<=k ; i++  ) {
						console2.appendText(String.format("%-25s",rs.getString(i)));
					}	 
					console2.appendText("\n");
				}
				separatore(titolo);
			}
		}

		catch (SQLException e) {
			console2.appendText("Ricerca non riuscita\n");
			e.printStackTrace();
			console2.appendText(e.getMessage());
			separatore();
		}
	}

	private void clearSelected() {
		Toggle toggleSelected = toggleGroupSelezioneCampiPersonaGenerica.getSelectedToggle();
		if(toggleSelected!=null) {
			//PERSONA - PERSONA
			if(toggleSelected.equals(radioBDatiPersonali)) {
				textFieldNomePersona.clear();
				textFieldCognomePersona.clear();
				textComuneNascitaPersona.clear();
				textFieldCFPersona.clear();
				textFieldTelefono1Persona.clear();
				textFieldMail1Persona.clear();
				textFieldTelefono2Persona.clear();
				textFieldMail2Persona.clear();
				textDataNascitaPersona.setValue(null);
				tbMaschio.setSelected(false);
				tbFemmina.setSelected(false);
				textFieldResidenza.clear();				
			}

			// PERSONA - STUDENTE
			else if(toggleSelected.equals(radioBDatiPersonaliStudente)) {
				textNomeStudente.clear();
				textCognomeStudente.clear();
				textCodiceFiscaleStudente.clear();
				tbFrequentaInPresenzaSi.setSelected(false);
				tbFrequentaInPresenzaNo.setSelected(false);
				textIDStudenteStudente.clear();
			}

			else if(toggleSelected.equals(radioBDatiPersonaliLavoratore)) {
				textNomeLavoratore.clear();
				textCognomeLavoratore.clear();
				textCodiceFiscaleLavoratore.clear();
				checkBoxLunediLavoratore.setSelected(false);
				checkBoxMartediLavoratore.setSelected(false);
				checkBoxMercolediLavoratore.setSelected(false);
				checkBoxGiovediLavoratore.setSelected(false);
				checkBoxVenerdiLavoratore.setSelected(false);
				checkBoxSabatoLavoratore.setSelected(false);
				checkBoxDomenicaLavoratore.setSelected(false);
				tipoContrattoChoiceBox.setValue(null);
				textNomeSocieta.clear();
				textIDLavoratoreDatiPersonali.clear();

			}

			else if(toggleSelected.equals(radioBParentela)) {
				textFieldPersonaNomeParentela1.clear();
				textFieldPersonaCognomeParentela1.clear();
				textFieldPersonaCFParentela1.clear();
				sceltaParentela.setValue(null);
				textFieldPersonaNomeParentela2.clear();
				textFieldPersonaCognomeParentela2.clear();
				textFieldPersonaCFParentela2.clear();
			}

			else if(toggleSelected.equals(radioBStatoDellaSalute)) {
				textFieldPersonaNomeStatoSalute.clear();
				textFieldPersonaCognomeStatoSalute.clear();
				textFieldPersonaCFStatoSalute.clear();
				sceltaSintomi.setValue(null);
				dataSintomiDatePickerStatoSalute.setValue(null);
				tbQuarantenaSi.setSelected(false);
				tbQuarantenaNo.setSelected(false);
				tbTamponeEseguitoSi.setSelected(false);
				tbTamponeEseguitoNo.setSelected(false);
				textDataTamponeEseguito.setValue(null);
				textFieldPersonaMalattieCronicheStatoSalute.clear();		
			}

			else if(toggleSelected.equals(radioBCompagniStudente)) {
				textNomeCompagno1.clear();
				textCognomeCompagno1.clear();
				textCodiceFiscaleCompagno1.clear();
				textNomeCompagno2.clear();
				textCognomeCompagno2.clear();
				textCodiceFiscaleCompagno2.clear();	
			}

			else if(toggleSelected.equals(radioBColleghiLavoratore)) {
				textNomeCollega1.clear();
				textCognomeCollega1.clear();
				textCodiceFiscaleCollega1.clear();
				textNomeCollega2.clear();
				textCognomeCollega2.clear();
				textCodiceFiscaleCollega2.clear();
			}
			//*** LUOGO ***
			else if(toggleSelected.equals(radioBDatiLuogo)) {
				textCityDatiLuogo.clear();
				textProvinciaDatiLuogo.clear();
				textViaDatiLuogo.clear();
				textNumeroCivicoDatiLuogo.clear();
				textCAPDatiLuogo.clear();
				textIDLuogo.clear();
			}

			//*** GESTIONE LUOGO ***
			else if(toggleSelected.equals(radioBGestioneLuogo)) {
				textNomeGestioneLuogo.clear();
				textCognomeGestioneLuogo.clear();
				textCodiceFiscaleGestioneLuogo.clear();
				textViaGestioneLuogo.clear();
				textNumCivicoGestioneLuogo.clear();
				textIDLuogoGestioneLuogo.clear();
			}

			// AMBIENTE LAVORO
			else if(toggleSelected.equals(radioBAmbienteLavoro)) {
				textViaAmbieneLavoro.clear();
				textNumCivicoAmbienteLavoro.clear();
				textIDLuogoAmbienteLavoro.clear();
				textOrarioAAmbienteLavoro.clear();
				textOrarioCAmbienteLavoro.clear();
				choicheBoxNumDipendentiAmbieneLavoro.setValue(null);
				textIDAmbienteLavoro.clear();
			}
			// AMBIENTE STUDIO
			else if(toggleSelected.equals(radioBAmbienteStudio)) {
				textViaAmbienteStudio.clear();
				textNumCivicoAmbienteStudio.clear();
				textIDLuogoAmbienteStudio.clear();
				choicheBoxTipoAmbienteStudio.setValue(null);
				textIDAmbienteStudio.clear();

			}
			// AULA
			else if(toggleSelected.equals(radioBAula)) {
				textIDAmbienteStudioAula.clear();
				choicheBoxNumPostiAula.setValue(null);
				textID_Aula.clear();

			}
			//  AMBIENTE EVENTO OCCASIONALE
			else if(toggleSelected.equals(radioBAmbienteEventoOccasionale)) {
				textViaAmbienteEventoOccasionale.clear();
				textNumCivicoAmbienteEventoOccasionale.clear();
				textIDLuogoAmbienteEventoOccasionale.clear();
				radioBLuogoApertoSi.setSelected(false);
				radioBLuogoApertoNo.setSelected(false);
				textDimensioniAmbienteEventoOccasionale.clear();	
				choicheBoxNumPartecipantiAmbienteEventoOccasionale.setValue(null);
				textIDAmbienteEventoOccasionale.clear();

			}
			// AMBIENTE FAMILIARE
			else if(toggleSelected.equals(radioBAmbienteFamiliare)) {
				textViaAmbienteFamiliare.clear();
				textNumCivicoAmbienteFamiliare.clear();
				textIDLuogoAmbienteFamiliare.clear();
				choicheBoxTipoAmbienteFamiliare.setValue(null);
				textDimensioneAmbienteFamiliare.clear();
				textIDAmbienteFamiliare.clear();
			}

			else if(toggleSelected.equals(radioBDatiControlloMedico)) {
				textNomeMedicoControlloMedico.clear();
				textNomeOspedaleControlloMedico.clear();
				choiceBoxEsitoControlloMedico.setValue(null);
				choiceBoxTipoControlloMedico.setValue(null);
				datePickerDataControlloMedico.setValue(null);
				textIDControlloMedico.clear();
				textNomeControlloMedico.clear();
				textCognomeControlloMedico.clear();
				textCodiceFiscaleControlloMedico.clear();
				textViaControlloMedico.clear();
				textNumCivicoControlloMedico.clear();
				textIDLuogoControlloMedico.clear();
			}

			else if(toggleSelected.equals(radioBDatiIncontroVisita)) {
				textNomeDatiIncontro.clear();
				textCognomeDatiIncontro.clear();
				textCodiceFiscaleDatiIncontro.clear();
				textViaDatiIncontro.clear();
				textNumCivicoDatiIncontro.clear();
				textIDLuogoDatiIncontro.clear();
				datePickerDataDatiIncontro.setValue(null);
				textorarioDatiIncontro.clear();
			}

			else if(toggleSelected.equals(radioBLuoghiIstituzionaliFrequentati)) {
				textNomeLuoghiIST.clear();
				textCognomeLuoghiIST.clear();
				textIDStudenteLuoghiIST.clear();
				textViaLuoghiIST.clear();
				textNumeroCivicoLuoghiIST.clear();
				textIDAmbienteStudioLuoghiIST.clear();		
			}

			else if(toggleSelected.equals(radioBLuoghiAbitudiniLavorative)) {
				textNomeLuoghiLAV.clear();
				textCognomeLuoghiLAV.clear();
				textIDLavoratoreLuoghiLAV.clear();
				textViaLuoghiLAV.clear();
				textNumeroCivicoLuoghiLAV.clear();
				textIDAmbienteLavorativoLuoghiLAV.clear();
				datePickerLuoghiLAV.setValue(null);
				textOrarioIngressoLuoghiLAV.clear();
				textOrarioUscitaLuoghiLAV.clear();
			}
			else if(toggleSelected.equals(radioBDatiVaccino)) {
				textIDVaccinoDatiVaccino.clear();
				textEnteRilascioDatiVaccino.clear();
				textControindicazioniDatiVaccino.clear();
				tipoVaccinoChoiceBox.setValue(null);
				periodoUtileChoiceBox.setValue(null);	
			}

			else if(toggleSelected.equals(radioBSomministrazioneVaccino)) {
				textNomeSomministrazioneVaccino.clear();
				textCognomeSomministrazioneVaccino.clear();
				textCodiceFiscaleSomministrazioneVaccino.clear();
				textIDVaccinoSomministrazioneVaccino.clear();
				textComplicazioniVaccino.clear();
				dataSomministrazioneVaccino.setValue(null);
			}
		}
		else {
			console2.appendText("Nessuna classe selezionata\n");
			separatore();
		}
	
	}
	
	public void openHelp(ActionEvent e) {
		try {
			Stage root = new Stage();
			ScrollPane finestraHelp = FXMLLoader.<ScrollPane>load(getClass().getResource("help.fxml"));
			Scene helpScene = new Scene(finestraHelp,600,800);
			helpScene.getStylesheets().add("application.css");
			root.getIcons().add(new Image(new FileInputStream("src\\application\\Immagini\\logocolorato.png")));
			root.setScene(helpScene);
			root.setTitle("HELP");
			root.show();	
			
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
}




