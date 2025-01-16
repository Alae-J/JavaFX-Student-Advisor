package BD;
import MainClassesPackage.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTablesToObjects {

    

    // Charger les institutions
    public  ArrayList<Institution> loadInstitutions(Connection connection) throws SQLException {
        ArrayList<Institution> institutions = new ArrayList<>();
        String query = "SELECT * FROM institution";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String nom = rs.getString("nom");
                String ville = rs.getString("ville");
                String type = rs.getString("type");
                int capacite = rs.getInt("capacitee");
                boolean priveeOuPublique = rs.getBoolean("priveeOuPublique");

                // Créer un objet Institution
                Institution institution = new Institution(nom, ville, type, priveeOuPublique ? "Privée" : "Publique", capacite) {};
                institution.setFilieres(loadFilieres(connection, nom, ville));
                institutions.add(institution);
            }
        }
        return institutions;
    }

    // Charger les filières pour une institution donnée
    public  ArrayList<Filiere> loadFilieres(Connection connection, String institutionNom, String institutionVille) throws SQLException {
        ArrayList<Filiere> filieres = new ArrayList<>();
        String query = "SELECT * FROM Filiere WHERE nominstitution = ? AND villeinstitution = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, institutionNom);
            stmt.setString(2, institutionVille);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nom = rs.getString("nom");
                    int duree = rs.getInt("duree");
                    String typeDiplome = rs.getString("typeDiplome");
                    String domaineNom = rs.getString("domaine");

                    // Charger les conditions d'entrée pour la filière
                    ConditionEntree conditionEntree = loadConditions(connection, nom, institutionNom, institutionVille);

                    // Créer un objet Domaine
                    Domaine domaine = new Domaine();
                    domaine.setNom(domaineNom);

                    // Créer un objet Filiere
                    Filiere filiere = new Filiere(nom, duree, typeDiplome, domaine);
                    filiere.setCE(conditionEntree);

                    // Charger les matières pour la filière
                    filiere.setMatieres(loadMatieres(connection, nom, institutionNom, institutionVille));

                    filieres.add(filiere);
                }
            }
        }
        return filieres;
    }

    // Charger les conditions d'entrée pour une filière donnée
    public ConditionEntree loadConditions(Connection connection, String filiereNom, String institutionNom, String institutionVille) throws SQLException {
        String query = "SELECT * FROM ConditionsDentrees WHERE filierenom = ? AND filierenominstitution = ? AND filierevilleinstitution = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, filiereNom);
            stmt.setString(2, institutionNom);
            stmt.setString(3, institutionVille);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int ageMax = rs.getInt("ageMax");
                    float noteMinPara = rs.getFloat("noteMinPara");
                    int classementUnivMin = rs.getInt("classementUnivMin");
                    int classementMinMp = rs.getInt("classementMinMp");
                    int classementMinPsi = rs.getInt("classementMinPsi");
                    int classementMinTsi = rs.getInt("classementMinTsi");
                    int classementMinEc = rs.getInt("classementMinEc");

                    return new ConditionEntree(ageMax, noteMinPara, classementUnivMin, classementMinMp, classementMinPsi, classementMinTsi, classementMinEc);
                }
            }
        }
        return null;
    }

    // Charger les matières pour une filière donnée
    public  ArrayList<Matiere> loadMatieres(Connection connection, String filiereNom, String institutionNom, String institutionVille) throws SQLException {
        ArrayList<Matiere> matieres = new ArrayList<>();
        String query = "SELECT * FROM Matiere WHERE filierenom = ? AND filierenominstitution = ? AND filierevilleinstitution = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, filiereNom);
            stmt.setString(2, institutionNom);
            stmt.setString(3, institutionVille);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nom = rs.getString("nom");
                    int coefficient = rs.getInt("coefficient");
                    String enseignant = rs.getString("enseignant");

                    // Créer un objet Matiere
                    Matiere matiere = new Matiere(nom, coefficient, enseignant);
                    matieres.add(matiere);
                }
            }
        }
        return matieres;
    }

    // Charger les étudiants prépa
    public ArrayList<EtudiantPrepa> loadEtudiantPrepa(Connection connection) throws SQLException {
        ArrayList<EtudiantPrepa> etudiants = new ArrayList<>();
        String query = "SELECT * FROM ETUDIANTPREPA";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String cin = rs.getString("CIN");
                int age = rs.getInt("AGE");
                String niveauEtude = rs.getString("NIVEAUEtude");
                String filierePrepa = rs.getString("filierePrepa");
                int classement = rs.getInt("classement");

                EtudiantPrepa etudiant = new EtudiantPrepa(nom, prenom, cin,age, niveauEtude);
                etudiant.setFilierePrepa(filierePrepa);
                etudiant.setClassement(classement);
                etudiants.add(etudiant);
            }
        }
        return etudiants;
    }

    // Charger les étudiants parallèles
    public ArrayList<EtudiantParallele> loadEtudiantParallel(Connection connection) throws SQLException {
        ArrayList<EtudiantParallele> etudiants = new ArrayList<>();
        String query = "SELECT * FROM ETUDIANPARALLELe";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String cin = rs.getString("CIN");
                int age = rs.getInt("AGE");
                String niveauEtude = rs.getString("NIVEAUEtude");
                int classementDansUniversitee = rs.getInt("classementDansUniversitee");
                float noteDiplome = rs.getFloat("noteDiplome");
                String filiereUniv = rs.getString("filiereUniv");

                EtudiantParallele etudiant = new EtudiantParallele(nom, prenom, cin, age, niveauEtude);
                etudiant.setClassementDansUniversitee(classementDansUniversitee);
                etudiant.setNoteDiplome(noteDiplome);
                etudiant.setFiliereUniv(filiereUniv);
                etudiants.add(etudiant);
            }
        }
        return etudiants;
    }

    // Ajouter un étudiant prépa
    public void addEtudiantPrepa(Connection connection, EtudiantPrepa etudiant) throws SQLException {
        String query = "INSERT INTO `orientation`.`etudiantprepa` (NOM, PRENOM, CIN, AGE, NIVEAUEtude, filierePrepa, classement) VALUES (?, ?,?, ?, ?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setString(3, etudiant.getCIN());
            stmt.setInt(4, etudiant.getAge()); // Check this value is passed correctly
            stmt.setString(5, etudiant.getNiveauEtude());
            stmt.setString(6, etudiant.getFilierePrepa());
            stmt.setInt(7, etudiant.getClassement());
            stmt.executeUpdate();
        }
    }


    // Ajouter un étudiant parallèle
    public void addEtudiantParallele(Connection connection, EtudiantParallele etudiant) throws SQLException {
    	String query = "INSERT INTO `orientation`.`etudianparallele` "
                + "(`NOM`, `PRENOM`, `CIN`, `AGE`, `NIVEAUEtude`, "
                + "`classementDansUniversitee`, `noteDiplome`, `filiereUniv`, "
                + "`noteSemestre1`, `noteSemestre2`, `noteSemestre3`, `noteSemestre4`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setString(3, etudiant.getCIN());
            stmt.setInt(4, etudiant.getAge());
            stmt.setString(5, etudiant.getNiveauEtude());
            stmt.setInt(6, etudiant.getClassementDansUniversitee());
            stmt.setFloat(7, etudiant.getNoteDiplome());
            stmt.setString(8, etudiant.getFiliereUniv());
            stmt.setFloat(9, etudiant.getNoteSemestre()[0]);
            stmt.setFloat(10, etudiant.getNoteSemestre()[1]);
            stmt.setFloat(11, etudiant.getNoteSemestre()[2]);
            stmt.setFloat(12, etudiant.getNoteSemestre()[3]);
            stmt.executeUpdate();
        }
    }

    // Mettre à jour un étudiant prépa
    public void updateEtudiantPrepa(Connection connection, String cin, EtudiantPrepa etudiant) throws SQLException {
        String query = "UPDATE ETUDIANTPREPA SET NOM = ?, PRENOM = ?, AGE = ?, NIVEAUEtude = ?, filierePrepa = ?, classement = ? WHERE CIN = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setInt(3, etudiant.getAge());
            stmt.setString(4, etudiant.getNiveauEtude());
            stmt.setString(5, etudiant.getFilierePrepa());
            stmt.setInt(6, etudiant.getClassement());
            stmt.setString(7, cin);
            stmt.executeUpdate();
        }
    }

    // Mettre à jour un étudiant parallèle
    public void updateEtudiantParallele(Connection connection, String cin, EtudiantParallele etudiant) throws SQLException {
        String query = "UPDATE ETUDIANPARALLELe SET NOM = ?, PRENOM = ?, AGE = ?, NIVEAUEtude = ?, classementDansUniversitee = ?, noteDiplome = ?, filiereUniv = ? WHERE CIN = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setInt(3, etudiant.getAge());
            stmt.setString(4, etudiant.getNiveauEtude());
            stmt.setInt(5, etudiant.getClassementDansUniversitee());
            stmt.setFloat(6, etudiant.getNoteDiplome());
            stmt.setString(7, etudiant.getFiliereUniv());
            stmt.setString(8, cin);
            stmt.executeUpdate();
        }
    }

    // Supprimer un étudiant prépa
    public void deleteEtudiantPrepa(Connection connection, String cin) throws SQLException {
        String query = "DELETE FROM ETUDIANTPREPA WHERE CIN = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cin);
            stmt.executeUpdate();
        }
    }

    // Supprimer un étudiant parallèle
    public void deleteEtudiantParallele(Connection connection, String cin) throws SQLException {
        String query = "DELETE FROM ETUDIANPARALLELe WHERE CIN = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cin);
            stmt.executeUpdate();
        }
    }
    
    public EtudiantParallele loadUnEtudiantParallel(String cin, Connection connection) throws SQLException {
        String query = "SELECT * FROM `orientation`.`ETUDIANPARALLELe` WHERE CIN = ?";
        EtudiantParallele etudiant = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cin); // Set the CIN parameter
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    String cin1 = rs.getString("CIN");
                    int age = rs.getInt("AGE");
                    String niveauEtude = rs.getString("NIVEAUEtude");
                    int classementDansUniversitee = rs.getInt("classementDansUniversitee");
                    float noteDiplome = rs.getFloat("noteDiplome");
                    String filiereUniv = rs.getString("filiereUniv");
                    float[] notes = {
                        rs.getFloat("noteSemestre1"),
                        rs.getFloat("noteSemestre2"),
                        rs.getFloat("noteSemestre3"),
                        rs.getFloat("noteSemestre4")
                    };

                    etudiant = new EtudiantParallele(nom, prenom, cin1, age, niveauEtude);
                    etudiant.setClassementDansUniversitee(classementDansUniversitee);
                    etudiant.setNoteDiplome(noteDiplome);
                    etudiant.setFiliereUniv(filiereUniv);
                    etudiant.setNoteSemestre(notes);
                }
            }
        }
        return etudiant;
    }

    
    public EtudiantPrepa loadUnEtudiantPrepa(String cin, Connection connection) throws SQLException {
        String query = "SELECT * FROM `orientation`.`etudiantprepa` WHERE CIN = ?";
        EtudiantPrepa etudiant = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cin); // Set the CIN parameter
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    String cin1 = rs.getString("CIN");
                    int age = rs.getInt("AGE");
                    String niveauEtude = rs.getString("NIVEAUEtude");
                    String filierePrepa = rs.getString("filierePrepa");
                    int classement = rs.getInt("classement");

                    etudiant = new EtudiantPrepa(nom, prenom, cin1, age, niveauEtude);
                    etudiant.setFilierePrepa(filierePrepa);
                    etudiant.setClassement(classement);
                    etudiant.setAge(age);
                }
            }
        }
        return etudiant;
    }

    
    
    	

}

