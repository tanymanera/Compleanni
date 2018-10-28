package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import db.DAOcompleanni;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import model.Person;

public class TableOfBirthdayController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Person> birthdayTable;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private TableColumn<Person, LocalDate> birthdayColumn;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private TextField birthdayInput;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;
    
    @FXML
    private Button backButton;
    
    private Model model = new Model();
    private DAOcompleanni dao = new DAOcompleanni();

    @FXML
    void addPushed(ActionEvent event) {
    	String nome = nameInput.getText();
    	String cognome = lastNameInput.getText();
    	if(nome.equals("") || cognome.equals("")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Nome e Cognome sono campi obbligatori.");
    		alert.showAndWait();
    		return;
    	}
    	if(model.isPutIn(nome, cognome)){
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Nel database è già presente un campo con " + nome + cognome + ".\n");
    		alert.showAndWait();
    		return;
    	}
    	LocalDate natoIl;
		try {
			natoIl = LocalDate.parse(birthdayInput.getText());
		} catch (DateTimeParseException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Errore nel formato della data.");
    		alert.showAndWait();
			return;
		}
    	Person p = new Person();
    	p.setNome(nome);
    	p.setCognome(cognome);
    	p.setNatoIl(natoIl);
    	dao.insertPerson(p);
    	birthdayTable.setItems(listPersone());
    	nameInput.clear();
    	lastNameInput.clear();
    	birthdayInput.clear();
    }

    @FXML
    void deletePushed(ActionEvent event) {
    	Person delenda = birthdayTable.getSelectionModel().getSelectedItem();
    	if(delenda == null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Selezionare una riga.");
    		alert.showAndWait();
    		return;
    	}
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Attenzione vuoi veramente eliminare " + delenda.toString() + " ?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
    		dao.delete(delenda);
        	birthdayTable.setItems(listPersone());
    	}
    	return;
    	
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	ObservableList<Person> persons = listPersone();
    	birthdayTable.setItems(persons);
    	
    }
    
    public ObservableList<Person> listPersone(){
    	ObservableList<Person> lista = FXCollections.observableArrayList();
    	lista.addAll(model.getPersons());
    	return lista;
    	
    }
    
    @FXML
    void backPushed(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SplashScreen.fxml"));
			VBox root = (VBox)loader.load();
			SplashScreenController controller = loader.getController();
			
			//set Model
			Model model = new Model();
			controller.setModel(model);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        assert birthdayTable != null : "fx:id=\"nameColunn\" was not injected: check your FXML file 'TableOfBirthday.fxml'.";
        assert firstNameColumn != null : "fx:id=\"firstNameColumn\" was not injected: check your FXML file 'TableOfBirthday.fxml'.";
        assert lastNameColumn != null : "fx:id=\"lastNameColumn\" was not injected: check your FXML file 'TableOfBirthday.fxml'.";
        assert birthdayColumn != null : "fx:id=\"birthdayColumn\" was not injected: check your FXML file 'TableOfBirthday.fxml'.";
        assert nameInput != null : "fx:id=\"nameInput\" was not injected: check your FXML file 'TableOfBirthday.fxml'.";
        assert lastNameInput != null : "fx:id=\"lastNameInput\" was not injected: check your FXML file 'TableOfBirthday.fxml'.";
        assert birthdayInput != null : "fx:id=\"birthdayInput\" was not injected: check your FXML file 'TableOfBirthday.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'TableOfBirthday.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'TableOfBirthday.fxml'.";

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("natoIl"));
        
    }
}

