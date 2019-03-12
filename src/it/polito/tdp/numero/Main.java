package it.polito.tdp.numero;
	
import it.polito.tdp.numero.model.NumeroModel;//IMPORTO PACKAGE MODEL
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	//APPLICATION è LA CLASSE CHE RIUNISCE TUTTI E 3 I PARAMETRI
	@Override
	public void start(Stage primaryStage) {
		try {
			//FXMLLOADER
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Numero.fxml")); //SI USA LOADER perchè lavora con SceneBuilder. Prende il file xml e crea per noi tutti i nodi
			
			//VIEW
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//CONTROLLER
			NumeroController controller = loader.getController();
			
			//MODELLO
			NumeroModel model = new NumeroModel();//CREAZIONE DEL MODELLO
			controller.setModel(model);
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
