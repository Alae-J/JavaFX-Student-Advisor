package view;
import BD.*;
import MainClassesPackage.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Accueil extends Application {
	

    @Override
    public void start(Stage stage) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Orientation";
        String user = "root"; // Remplacez par votre nom d'utilisateur
        String password = "yassine124800"; // Remplacez par votre mot de passe

        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            Connection connection = DriverManager.getConnection(url, user, password);

        
        
        // Main layout container
        VBox mainLayout = new VBox(20); // Increased vertical spacing for clarity
        mainLayout.setPadding(new Insets(30));
        mainLayout.setAlignment(Pos.CENTER);

        // Title for the page
        Text pageTitle = new Text("Accueil");
        pageTitle.setFont(Font.font("Arial", 28)); // Larger font size for main title

        // Welcome section title
        Text welcomeSectionTitle = new Text("Introduction");
        welcomeSectionTitle.setFont(Font.font("Arial", 20)); // Slightly larger font for section title

        // Welcome text
        Text welcomeText = new Text(
                "Bienvenue à l'application Orientation.\n\n" +
                "Cette application aide les étudiants à explorer les institutions et les filières disponibles " +
                "en fonction de leurs critères et performances.\n\n" +
                "Utilisez le bouton ci-dessous pour accéder à la page des étudiants ou cliquez sur une institution dans " +
                "la liste pour voir ses détails."
        );
        welcomeText.setFont(Font.font("Arial", 16)); // Larger font for readability
        welcomeText.setTextAlignment(TextAlignment.CENTER); // Center the text horizontally
        TextFlow welcomeTextFlow = new TextFlow(welcomeText);
        welcomeTextFlow.setTextAlignment(TextAlignment.CENTER);
        welcomeTextFlow.setPrefWidth(600);
        welcomeTextFlow.setMaxWidth(600);

        // Institutions section title
        Text institutionsSectionTitle = new Text("Liste des Institutions");
        institutionsSectionTitle.setFont(Font.font("Arial", 20)); // Matching size to other section titles

        // List of institutions
        ListView<String> institutionList = new ListView<>();
        //cree une hashmap <nomInstitution,Institution>
        
        Map<String, Institution> institutionMap = new HashMap<>();


        DatabaseTablesToObjects dataBase = new DatabaseTablesToObjects();
        List<Institution> institutions = dataBase.loadInstitutions(connection);
        for (Institution institution : institutions) {
            institutionList.getItems().add(institution.getNom());
            institutionMap.put(institution.getNom(), institution);
        }

        institutionList.setPrefHeight(200);
        institutionList.setPrefWidth(600);

        // Institution detail pop-up
        institutionList.setOnMouseClicked(event -> {
            if (!institutionList.getSelectionModel().isEmpty()) {
                String selectedInstitutionName = institutionList.getSelectionModel().getSelectedItem();
                Institution selectedInstitution = institutionMap.get(selectedInstitutionName);

                if (selectedInstitution != null) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Détails de l'Institution");
                    alert.setHeaderText(selectedInstitution.getNom());
                    alert.setContentText(selectedInstitution.toString());
                    alert.showAndWait();
                }
            }
        });

        // Button to navigate to the students page
        Button goToStudentsPage = new Button("Aller à la page des étudiants");
        goToStudentsPage.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        goToStudentsPage.setOnMouseEntered(e -> goToStudentsPage.setStyle("-fx-font-size: 18px; -fx-background-color: #45A049; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        goToStudentsPage.setOnMouseExited(e -> goToStudentsPage.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 15px 30px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        goToStudentsPage.setOnAction(e -> {
            StudentsPage studentsPage = new StudentsPage();
            studentsPage.start(stage);
        });

        // Add components to layout
        mainLayout.getChildren().addAll(pageTitle, welcomeSectionTitle, welcomeTextFlow, institutionsSectionTitle, institutionList, goToStudentsPage);

        // Create and set the scene
        Scene scene = new Scene(mainLayout, 850, 750); // Larger window size for better layout
        stage.setTitle("Accueil");
        stage.setScene(scene);
        stage.show();
        
        
        
        
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
