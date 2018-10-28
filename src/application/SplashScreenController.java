package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import model.Model;

public class SplashScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextArea resulttxt;

    @FXML
    private Button databaseButton;
    
    private Model model;

    @FXML
    void databasePushed(ActionEvent event) {

    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TableOfBirthday.fxml"));
			VBox root = (VBox)loader.load();
			TableOfBirthdayController controller = loader.getController();
			
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
        assert resulttxt != null : "fx:id=\"resulttxt\" was not injected: check your FXML file 'SplashScreen.fxml'.";
        assert databaseButton != null : "fx:id=\"databaseButton\" was not injected: check your FXML file 'SplashScreen.fxml'.";

    }
    
	public void setModel(Model model) {
		this.model = model;
		resulttxt.setText(model.festeggiati());
	}
}

