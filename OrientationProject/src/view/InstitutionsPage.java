package view;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InstitutionsPage extends Application {

    @Override
    public void start(Stage stage) {
        // Main layout container
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setAlignment(Pos.CENTER);

        // Title for the page
        Text pageTitle = new Text("Institutions Permises");
        pageTitle.setFont(Font.font("Arial", 28));

        // Subtitle for the institution list
        Text institutionListTitle = new Text("Liste des Institutions Permises");
        institutionListTitle.setFont(Font.font("Arial", 20));

        // GridPane for institution list with details
        GridPane institutionGrid = new GridPane();
        institutionGrid.setAlignment(Pos.CENTER);
        institutionGrid.setHgap(20);
        institutionGrid.setVgap(10);

        String[] institutions = {"INPT", "ENSIAS", "EMI", "EHTP UNIVERSITE HEHE ALAE IS THE BEST"};
        for (int i = 0; i < institutions.length; i++) {
            String institutionNameText = institutions[i]; // Final variable for lambda expressions

            Text institutionName = new Text(institutionNameText);
            institutionName.setFont(Font.font("Arial", 16));

            Button detailsButton = new Button("Détails");
            detailsButton.setStyle("-fx-font-size: 14px; -fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 5px 15px;");
            detailsButton.setOnMouseEntered(e -> detailsButton.setStyle("-fx-font-size: 14px; -fx-background-color: #1976D2; -fx-text-fill: white; -fx-padding: 5px 15px;"));
            detailsButton.setOnMouseExited(e -> detailsButton.setStyle("-fx-font-size: 14px; -fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 5px 15px;"));
            detailsButton.setOnAction(e -> System.out.println("Afficher les détails de: " + institutionNameText));

            institutionGrid.add(institutionName, 0, i);
            institutionGrid.add(detailsButton, 1, i);
        }

        // Buttons container for navigation
        HBox buttonsContainer = new HBox(20);
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.setPadding(new Insets(20, 0, 0, 0));

        // Button to go back to the student page
        Button backButton = new Button("Retour à la Page Étudiants");
        backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #45A049; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        backButton.setOnAction(e -> {
            StudentsPage pageEtudients = new StudentsPage();
            pageEtudients.start(stage);
        });

        // Button to return to the home page
        Button homeButton = new Button("Retour à l'Accueil");
        homeButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        homeButton.setOnMouseEntered(e -> homeButton.setStyle("-fx-font-size: 18px; -fx-background-color: #45A049; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        homeButton.setOnMouseExited(e -> homeButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        homeButton.setOnAction(e -> {
            Accueil pageAccueil = new Accueil();
            try {
				pageAccueil.start(stage);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });


        // Add buttons to the container
        buttonsContainer.getChildren().addAll(backButton, homeButton);

        // Add components to layout
        mainLayout.getChildren().addAll(pageTitle, institutionListTitle, institutionGrid, buttonsContainer);

        // Create and set the scene
        Scene scene = new Scene(mainLayout, 850, 750);
        stage.setTitle("Institutions Permises");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
