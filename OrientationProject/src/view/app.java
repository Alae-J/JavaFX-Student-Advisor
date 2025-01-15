package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class app extends Application {
    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(4);

        
        TextField name= new TextField();
        PasswordField pass= new PasswordField();
        
        Text bv = new Text("Bienvenue");
        Text ut = new Text("Utilisateur :");
        Text pw = new Text("Mot de Passe :");
        
        Button btn = new Button("Se connecter");

        grid.add(bv, 0, 0, 3, 1);
        grid.add(ut, 0, 2); 
        grid.add(pw, 0, 4);
        grid.add(name, 2, 2,3,1); 
        grid.add(pass, 2, 4,3,1);
        grid.add(btn, 3, 6,2,1);
        
        bv.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));

        /*h1.getChildren().addAll(ut , name);
        h2.getChildren().addAll(pw , pass);
        
        racine.getChildren().addAll(bv,h1,h2,btn);*/
        
        

        
        Scene scene = new Scene(grid, 350, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
