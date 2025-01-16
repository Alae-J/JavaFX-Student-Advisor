package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import BD.DatabaseTablesToObjects;
import MainClassesPackage.Etudiant;
import MainClassesPackage.EtudiantParallele;
import MainClassesPackage.EtudiantPrepa;
import MainClassesPackage.Institution;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InstitutionsPage extends Application {
	
	Etudiant etudiant;
	
	
	public InstitutionsPage(Etudiant etudiant) {
		this.etudiant = etudiant;
	};
	

    @Override
    public void start(Stage stage) {
    	
    	String url = "jdbc:mysql://localhost:3306/Orientation";
        String user = "root"; // Remplacez par votre nom d'utilisateur
        String password = "yassine124800"; // Remplacez par votre mot de passe

        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            Connection connection = DriverManager.getConnection(url, user, password);
            
            
        // Main layout container
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setAlignment(Pos.CENTER);
        
        
     // Barre d'informations de l'étudiant connecté (en haut à droite)
        HBox userInfoBox = new HBox(15);
        userInfoBox.setAlignment(Pos.TOP_RIGHT);
        userInfoBox.setPadding(new Insets(10));
        userInfoBox.setStyle("-fx-background-color: #333; -fx-padding: 10px;");

        Label nameLabel = new Label("Nom: " + etudiant.getNom());
        Label lastNameLabel = new Label("Prénom: " + etudiant.getPrenom());

        nameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");
        lastNameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");

        userInfoBox.getChildren().addAll(nameLabel, lastNameLabel);

        
        // Title for the page
        Text pageTitle = new Text("Institutions Permises");
        pageTitle.setFont(Font.font("Arial", 28));

        // Subtitle for the institution list
        Text institutionListTitle = new Text("Liste des Institutions Permises");
        institutionListTitle.setFont(Font.font("Arial", 20));

        Text institutionsSectionTitle = new Text("Liste des Institutions");
        institutionsSectionTitle.setFont(Font.font("Arial", 20)); // Matching size to other section titles

        // List of institutions
        ListView<Institution> institutionList = new ListView<>();

        //cree une hashmap <nomInstitution,Institution>
        
        Map<String[], Institution> institutionMap = new HashMap<>();


        DatabaseTablesToObjects dataBase = new DatabaseTablesToObjects();
        
        
        List<Institution> institutions = dataBase.loadInstitutions(connection);
        
        if(etudiant instanceof EtudiantParallele) {
        	  List<Institution> institutionsPermises = ((EtudiantParallele)this.etudiant).institutionPermises(institutions);
        	
        }
        else if(etudiant instanceof EtudiantPrepa) {
      	  List<Institution> institutionsPermises = ((EtudiantPrepa)this.etudiant).institutionPermises(institutions);
      	
      }
        List<Institution> institutionsPermises = this.etudiant.institutionPermises(institutions);
        
//        Comparator c = new CompareInstitution();
//		Collections.sort(institutions,c);
		
        for (Institution institution : institutionsPermises) {
            institutionList.getItems().add(institution); // Ajout direct de l'objet Institution
        }

        institutionList.setPrefHeight(200);
        institutionList.setPrefWidth(600);
        
        institutionList.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Institution institution, boolean empty) {
                super.updateItem(institution, empty);
                if (empty || institution == null) {
                    setText(null);
                } else {
                    setText(institution.getNom() + " - " + institution.getVille());
                }
            }
        });


        // Institution detail pop-up
        institutionList.setOnMouseClicked(event -> {
            Institution selectedInstitution = institutionList.getSelectionModel().getSelectedItem();
            
            if (selectedInstitution != null) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Détails de l'Institution");
                alert.setHeaderText(selectedInstitution.getNom());
                alert.setContentText(selectedInstitution.toString());
                alert.showAndWait();
            }
        });


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
        mainLayout.getChildren().addAll(userInfoBox, pageTitle, institutionListTitle, institutionList, buttonsContainer);


        // Create and set the scene
        Scene scene = new Scene(mainLayout, 850, 750);
        stage.setTitle("Institutions Permises");
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