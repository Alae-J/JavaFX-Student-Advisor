package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class app extends Application {

    @Override
    public void start(Stage stage) {
        VBox racine = new VBox();
        GridPane grid = new GridPane();
        
        HBox h1 = new HBox();
        HBox h2 = new HBox();

        TextField name= new TextField();
        PasswordField pass= new PasswordField();
        
        Text bv = new Text("Bienvenue");
        Text ut = new Text("Utilisateur");
        Text pw = new Text("Password");
        
        Button btn = new Button("Se connecter");


        h1.getChildren().addAll(ut , name);
        h2.getChildren().addAll(pw , pass);
        
        racine.getChildren().addAll(bv,h1,h2,btn);
        
        

        
        Scene scene = new Scene(racine, 400, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}