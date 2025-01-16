package view;
import BD.*;
import MainClassesPackage.Etudiant;
import MainClassesPackage.EtudiantPrepa;
import MainClassesPackage.EtudiantParallele;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentsPage extends Application {

    private Set<Etudiant> mockDatabase = new HashSet<>(); // Avoid duplicates
    private List<Object[]> studentChanges = new ArrayList<>();
    DatabaseTablesToObjects DB = new DatabaseTablesToObjects(); // pour utiliser les fcts du classe DatabaseTablesToObjects
  
     

    @Override
    public void start(Stage stage) {
        String url = "jdbc:mysql://localhost:3306/Orientation";
        String user = "root"; // Remplacez par votre nom d'utilisateur
        String password = "yassine124800";
        
        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            Connection connection = DriverManager.getConnection(url, user, password);
      
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

        // Render students dynamically
        refreshStudentGrid(studentGrid, stage, connection);

        // Button to add a new student
        Button addStudentButton = new Button("Ajouter un(e) étudiant(e)");
        addStudentButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        addStudentButton.setOnAction(e -> showAddStudentDialog(stage, studentGrid,connection));

        // Navigation button to go back to the home page
        Button backButton = new Button("Retour à l'Accueil");
        backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        backButton.setOnAction(e -> {
            Accueil accueilPage = new Accueil();
            try {
				accueilPage.start(stage);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
        });

        HBox buttonsBox = new HBox(20, addStudentButton, backButton);
        buttonsBox.setAlignment(Pos.CENTER);

        // Add components to layout
        mainLayout.getChildren().addAll(pageTitle, studentListTitle, studentGrid, buttonsBox);

        // Create and set the scene
        Scene scene = new Scene(mainLayout, 850, 750);
        stage.setTitle("Page des Étudiants");
        stage.setScene(scene);
        stage.show();
        

        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshStudentGrid(GridPane studentGrid, Stage stage, Connection connection) throws SQLException {
        studentGrid.getChildren().clear(); // Clear previous data
        ArrayList<Etudiant> Etudiants = new ArrayList<Etudiant>();
        ArrayList<EtudiantPrepa> EudiantsPrepa = new ArrayList<EtudiantPrepa>();
        ArrayList<EtudiantParallele> EtudiantsParallel = new ArrayList<EtudiantParallele>();
        
        EudiantsPrepa = DB.loadEtudiantPrepa(connection);
        EtudiantsParallel = DB.loadEtudiantParallel(connection);
        
        Etudiants.addAll(EudiantsPrepa);
        Etudiants.addAll(EtudiantsParallel);
       
        int row = 0;
        for (Etudiant student : Etudiants) {
            Text studentName = new Text(student.getNom() + " " + student.getPrenom());
            studentName.setFont(Font.font("Arial", 16));

            Button editButton = new Button("Modifier");
            editButton.setStyle("-fx-font-size: 14px; -fx-background-color: #2196F3; -fx-text-fill: white;");
            editButton.setOnAction(e -> showEditStudentForm(student, stage, studentGrid,connection));

            Button deleteButton = new Button("Supprimer");
            deleteButton.setStyle("-fx-font-size: 14px; -fx-background-color: #F44336; -fx-text-fill: white;");
            deleteButton.setOnAction(e -> {
                mockDatabase.remove(student);
                try {
					refreshStudentGrid(studentGrid, stage, connection);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            });

            Button connectButton = new Button("Se connecter en tant que");
            connectButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
            connectButton.setOnAction(e -> {
                InstitutionsPage institutionsPage = new InstitutionsPage(); 
                institutionsPage.start(stage); // Navigate to InstitutionsPage
            });


            studentGrid.add(studentName, 0, row);
            studentGrid.add(editButton, 1, row);
            studentGrid.add(deleteButton, 2, row);
            studentGrid.add(connectButton, 3, row);
            row++;
        }
    }

    private void showAddStudentDialog(Stage stage, GridPane studentGrid, Connection connection) {
        Stage dialog = new Stage();
        dialog.setTitle("Ajouter un(e) étudiant(e)");

        VBox dialogLayout = new VBox(10);
        dialogLayout.setPadding(new Insets(20));
        dialogLayout.setAlignment(Pos.CENTER);

        // Options for student type
        ToggleGroup studentTypeGroup = new ToggleGroup();
        RadioButton prepaButton = new RadioButton("Étudiant Prépa");
        RadioButton paralleleButton = new RadioButton("Étudiant Parallèle");
        prepaButton.setToggleGroup(studentTypeGroup);
        paralleleButton.setToggleGroup(studentTypeGroup);

        Button continueButton = new Button("Continuer");
        continueButton.setOnAction(e -> {
            if (prepaButton.isSelected()) {
                dialog.close();
                showAddPrepaStudentForm(stage, studentGrid,connection);
            } else if (paralleleButton.isSelected()) {
                dialog.close();
                showAddParalleleStudentForm(stage, studentGrid,connection);
            }
        });

        dialogLayout.getChildren().addAll(new Text("Type d'Étudiant"), prepaButton, paralleleButton, continueButton);

        Scene dialogScene = new Scene(dialogLayout, 400, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void showAddPrepaStudentForm(Stage stage, GridPane studentGrid, Connection connection) {
        Stage formStage = new Stage();
        formStage.setTitle("Ajouter Étudiant Prépa");

        VBox formLayout = new VBox(10);
        formLayout.setPadding(new Insets(20));
        formLayout.setAlignment(Pos.CENTER);

        TextField nomField = new TextField();
        nomField.setPromptText("Nom");
        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");
        TextField cinField = new TextField();
        cinField.setPromptText("CIN");
        TextField ageField = new TextField();
        ageField.setPromptText("Âge");
        ComboBox<String> filiereComboBox = new ComboBox<>();
        filiereComboBox.getItems().addAll("MP", "PSI", "TSI", "ECS");
        filiereComboBox.setPromptText("Filière Prépa");
        TextField classementField = new TextField();
        classementField.setPromptText("Classement");

        Button submitButton = new Button("Ajouter");
        submitButton.setOnAction(e -> {
        	String nom = nomField.getText();
            String prenom =prenomField.getText();
            String CIN = cinField.getText();
            int age = Integer.parseInt(ageField.getText());
            String filiere = filiereComboBox.getValue();
            int classement = Integer.parseInt(classementField.getText());
        	
            Object[] studentData = {
                nomField.getText(),
                prenomField.getText(),
                cinField.getText(),
                Integer.parseInt(ageField.getText()),
                "Prépa",
                filiereComboBox.getValue(),
                Integer.parseInt(classementField.getText())
            };
            EtudiantPrepa E = new EtudiantPrepa(nom,prenom, CIN, age,"BAC+2");
            E.setFilierePrepa(filiere);
            E.setClassement(classement);
            
            try {
				DB.addEtudiantPrepa(connection, E);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        
            studentChanges.add(studentData); // Store student data
            formStage.close();
            try {
				refreshStudentGrid(studentGrid, stage,connection);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        formLayout.getChildren().addAll(new Text("Ajouter Étudiant Prépa"), nomField, prenomField, cinField, ageField, filiereComboBox, classementField, submitButton);

        Scene formScene = new Scene(formLayout, 400, 400);
        formStage.setScene(formScene);
        formStage.show();
    }

    
	private void showEditStudentForm(Etudiant student, Stage stage, GridPane studentGrid, Connection connection) {
	    Stage editStage = new Stage();
	    editStage.setTitle("Modifier l'Étudiant");
	
	    VBox formLayout = new VBox(10);
	    formLayout.setPadding(new Insets(20));
	    formLayout.setAlignment(Pos.CENTER);
	
	    TextField nomField = new TextField(student.getNom());
	    TextField prenomField = new TextField(student.getPrenom());
	    TextField cinField = new TextField(student.getCIN());
	    cinField.setEditable(false); // CIN should not be editable
	    TextField ageField = new TextField(String.valueOf(student.getAge()));
	
	    formLayout.getChildren().addAll(
	        new Text("Nom"), nomField,
	        new Text("Prénom"), prenomField,
	        new Text("CIN"), cinField,
	        new Text("Âge"), ageField
	    );
	
	    if (student instanceof EtudiantPrepa) {
	        EtudiantPrepa prepa;
	        try {
	            prepa = (EtudiantPrepa) DB.loadUnEtudiantPrepa(student.getCIN(), connection);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return;
	        }
	
	        ComboBox<String> filiereComboBox = new ComboBox<>();
	        filiereComboBox.getItems().addAll("mp", "psi", "tsi", "ecs");
	        filiereComboBox.setValue(prepa.getFilierePrepa());
	
	        TextField classementField = new TextField(String.valueOf(prepa.getClassement()));
	
	        formLayout.getChildren().addAll(
	            new Text("Filière Prépa"), filiereComboBox,
	            new Text("Classement"), classementField
	        );
	
	        Button saveButton = new Button("Enregistrer");
	        saveButton.setOnAction(e -> {
	            try {
	                prepa.setNom(nomField.getText());
	                prepa.setPrenom(prenomField.getText());
	                prepa.setAge(Integer.parseInt(ageField.getText()));
	                prepa.setFilierePrepa(filiereComboBox.getValue());
	                prepa.setClassement(Integer.parseInt(classementField.getText()));
	
	                DB.updateEtudiantPrepa(connection, prepa.getCIN(), prepa);
	                refreshStudentGrid(studentGrid, stage, connection);
	                editStage.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        });
	
	        formLayout.getChildren().add(saveButton);
	
	    } else if (student instanceof EtudiantParallele) {
	    	
	        EtudiantParallele parallele ;
	        try {
	            parallele = (EtudiantParallele) DB.loadUnEtudiantParallel(student.getCIN(), connection);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return;
	        }
	
	        TextField classementField = new TextField(String.valueOf(parallele.getClassementDansUniversitee()));
	        TextField noteDiplomeField = new TextField(String.valueOf(parallele.getNoteDiplome()));
	        TextField filiereField = new TextField(parallele.getFiliereUniv());
	        TextField note1Field = new TextField(String.valueOf(parallele.getNoteSemestre()[0]));
	        TextField note2Field = new TextField(String.valueOf(parallele.getNoteSemestre()[1]));
	        TextField note3Field = new TextField(String.valueOf(parallele.getNoteSemestre()[2]));
	        TextField note4Field = new TextField(String.valueOf(parallele.getNoteSemestre()[3]));
	
	        formLayout.getChildren().addAll(
	            new Text("Classement Universitaire"), classementField,
	            new Text("Note Diplôme"), noteDiplomeField,
	            new Text("Filière Universitaire"), filiereField,
	            new Text("Note Semestre 1"), note1Field,
	            new Text("Note Semestre 2"), note2Field,
	            new Text("Note Semestre 3"), note3Field,
	            new Text("Note Semestre 4"), note4Field
	        );
	
	        Button saveButton = new Button("Enregistrer");
	        saveButton.setOnAction(e -> {
	            try {
	                parallele.setNom(nomField.getText());
	                parallele.setPrenom(prenomField.getText());
	                parallele.setAge(Integer.parseInt(ageField.getText()));
	                parallele.setClassementDansUniversitee(Integer.parseInt(classementField.getText()));
	                parallele.setNoteDiplome(Float.parseFloat(noteDiplomeField.getText()));
	                parallele.setFiliereUniv(filiereField.getText());
	                parallele.setNoteSemestre(new float[]{
	                    Float.parseFloat(note1Field.getText()),
	                    Float.parseFloat(note2Field.getText()),
	                    Float.parseFloat(note3Field.getText()),
	                    Float.parseFloat(note4Field.getText())
	                });
	
	                DB.updateEtudiantParallele(connection, parallele.getCIN(), parallele);
	                refreshStudentGrid(studentGrid, stage, connection);
	                editStage.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        });
	
	        formLayout.getChildren().add(saveButton);
	    }
	
	    Scene editScene = new Scene(formLayout, 400, 600);
	    editStage.setScene(editScene);
	    editStage.show();
	}


    private void showAddParalleleStudentForm(Stage stage, GridPane studentGrid, Connection connection) {
        Stage formStage = new Stage();
        formStage.setTitle("Ajouter Étudiant Parallèle");

        VBox formLayout = new VBox(10);
        formLayout.setPadding(new Insets(20));
        formLayout.setAlignment(Pos.CENTER);

        TextField nomField = new TextField();
        nomField.setPromptText("Nom");
        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");
        TextField cinField = new TextField();
        cinField.setPromptText("CIN");
        TextField ageField = new TextField();
        ageField.setPromptText("Âge");
        TextField classementField = new TextField();
        classementField.setPromptText("Classement Universitaire");
        TextField noteDiplomeField = new TextField();
        noteDiplomeField.setPromptText("Note Diplôme");
        TextField filiereField = new TextField();
        filiereField.setPromptText("Filière Universitaire");
        TextField note1Field = new TextField();
        note1Field.setPromptText("Note Semestre 1");
        TextField note2Field = new TextField();
        note2Field.setPromptText("Note Semestre 2");
        TextField note3Field = new TextField();
        note3Field.setPromptText("Note Semestre 3");
        TextField note4Field = new TextField();
        note4Field.setPromptText("Note Semestre 4");

        Button submitButton = new Button("Ajouter");
        
        
        submitButton.setOnAction(e -> {
            Object[] studentData = {
                nomField.getText(),
                prenomField.getText(),
                cinField.getText(),
                Integer.parseInt(ageField.getText()),
                "Parallèle",
                Integer.parseInt(classementField.getText()),
                Float.parseFloat(noteDiplomeField.getText()),
                filiereField.getText(),
                Float.parseFloat(note1Field.getText()),
                Float.parseFloat(note2Field.getText()),
                Float.parseFloat(note3Field.getText()),
                Float.parseFloat(note4Field.getText())
                
            };
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String CIN = cinField.getText();
            int age =  Integer.parseInt(ageField.getText());
            int classement = Integer.parseInt(classementField.getText());
            float noteDiplome = Float.parseFloat(noteDiplomeField.getText());
            String filiereDansUniv = filiereField.getText();
            float s1 = Float.parseFloat(note1Field.getText());
            float s2 =Float.parseFloat(note2Field.getText());
            float s3 =Float.parseFloat(note3Field.getText());
            float s4 =Float.parseFloat(note4Field.getText());
            float notes[] = {s1,s2,s3,s4};

            
            
            EtudiantParallele E = new EtudiantParallele(nom, prenom, CIN, age, "BAC+2");
            
            E.setNoteDiplome(noteDiplome);
            E.setClassementDansUniversitee(classement);
            E.setNoteSemestre(notes);
            
            try {
				DB.addEtudiantParallele(connection, E);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
            studentChanges.add(studentData); // Store student data
            formStage.close();
            try {
				refreshStudentGrid(studentGrid, stage, connection);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        formLayout.getChildren().addAll(
            new Text("Ajouter Étudiant Parallèle"),
            nomField, prenomField, cinField, ageField,
            classementField, noteDiplomeField, filiereField,
            note1Field, note2Field, note3Field, note4Field,
            submitButton
        );

        Scene formScene = new Scene(formLayout, 400, 600);
        formStage.setScene(formScene);
        formStage.show();
    }

}
