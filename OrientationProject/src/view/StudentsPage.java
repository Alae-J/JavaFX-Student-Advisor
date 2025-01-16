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

public class StudentsPage extends Application {

    @Override
    public void start(Stage stage) {
        // Main layout container
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setAlignment(Pos.CENTER);

        // Title for the page
        Text pageTitle = new Text("Page des Étudiants");
        pageTitle.setFont(Font.font("Arial", 28));

        // Subtitle for the student list
        Text studentListTitle = new Text("Liste des Étudiants");
        studentListTitle.setFont(Font.font("Arial", 20));

        // GridPane for student list with actions
        GridPane studentGrid = new GridPane();
        studentGrid.setAlignment(Pos.CENTER);
        studentGrid.setHgap(20);
        studentGrid.setVgap(10);

        String[] students = {"Étudiant Prépa 1", "Étudiant Prépa 2", "Étudiant Parallèle 1", "Étudiant Parallèle 2"};
        for (int i = 0; i < students.length; i++) {
            String studentNameText = students[i]; // Create a final variable for each student

            Text studentName = new Text(studentNameText);
            studentName.setFont(Font.font("Arial", 16));

            Button editButton = new Button("Modifier");
            editButton.setStyle("-fx-font-size: 14px; -fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 5px 15px;");
            editButton.setOnMouseEntered(e -> editButton.setStyle("-fx-font-size: 14px; -fx-background-color: #1976D2; -fx-text-fill: white; -fx-padding: 5px 15px;"));
            editButton.setOnMouseExited(e -> editButton.setStyle("-fx-font-size: 14px; -fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 5px 15px;"));
            editButton.setOnAction(e -> System.out.println("Modifier l'étudiant: " + studentNameText));

            Button deleteButton = new Button("Supprimer");
            deleteButton.setStyle("-fx-font-size: 14px; -fx-background-color: #F44336; -fx-text-fill: white; -fx-padding: 5px 15px;");
            deleteButton.setOnMouseEntered(e -> deleteButton.setStyle("-fx-font-size: 14px; -fx-background-color: #D32F2F; -fx-text-fill: white; -fx-padding: 5px 15px;"));
            deleteButton.setOnMouseExited(e -> deleteButton.setStyle("-fx-font-size: 14px; -fx-background-color: #F44336; -fx-text-fill: white; -fx-padding: 5px 15px;"));
            deleteButton.setOnAction(e -> System.out.println("Supprimer l'étudiant: " + studentNameText));

            Button connectButton = new Button("Se connecter en tant que");
            connectButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5px 15px;");
            connectButton.setOnMouseEntered(e -> connectButton.setStyle("-fx-font-size: 14px; -fx-background-color: #45A049; -fx-text-fill: white; -fx-padding: 5px 15px;"));
            connectButton.setOnMouseExited(e -> connectButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5px 15px;"));
            connectButton.setOnAction(e -> {
                InstitutionsPage institutionsPage = new InstitutionsPage();
                institutionsPage.start(stage); // Navigate to Institutions Page
            });

            studentGrid.add(studentName, 0, i);
            studentGrid.add(editButton, 1, i);
            studentGrid.add(deleteButton, 2, i);
            studentGrid.add(connectButton, 3, i);
        }

        // Button to add a new student
        Button addStudentButton = new Button("Ajouter un(e) étudiant(e)");
        addStudentButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        addStudentButton.setOnMouseEntered(e -> addStudentButton.setStyle("-fx-font-size: 18px; -fx-background-color: #45A049; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        addStudentButton.setOnMouseExited(e -> addStudentButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        addStudentButton.setOnAction(e -> System.out.println("Adding a new student..."));

        // Navigation button to go back to the home page
        Button backButton = new Button("Retour à l'Accueil");
        backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #45A049; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        backButton.setOnAction(e -> {
            Accueil pageAccueil = new Accueil();
            try {
				pageAccueil.start(stage);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // Navigate to Home Page
        });

        // Add components to layout
        mainLayout.getChildren().addAll(pageTitle, studentListTitle, studentGrid, addStudentButton, backButton);

        // Create and set the scene
        Scene scene = new Scene(mainLayout, 850, 750);
        stage.setTitle("Page des Étudiants");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
