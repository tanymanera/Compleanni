package application;
	
import java.sql.SQLException;
import java.util.Optional;

import com.mchange.v2.c3p0.DataSources;

import db.DBConnect;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SplashScreen.fxml"));
			VBox root = (VBox)loader.load();
			SplashScreenController controller = loader.getController();
			
			//set Model
			Model model = new Model();
			controller.setModel(model);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop() {
		try {
			DataSources.destroy(DBConnect.getDataSource());
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}
