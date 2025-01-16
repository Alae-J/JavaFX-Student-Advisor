package BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection1 {
    public static void main(String[] args) {
        // URL de la base de données
        String url = "jdbc:mysql://localhost:3306/Orientation";
        String user = "root"; // Remplacez par votre nom d'utilisateur MySQL
        String password = "yassine124800"; // Remplacez par votre mot de passe MySQL

        try {
            // Étape 1 : Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Étape 2 : Établir une connexion
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie avec succès !");

            // Étape 3 : Créer une requête
            String sql = "SELECT * FROM institution";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Étape 4 : Exécuter la requête
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Institution: " + resultSet.getString("nom") + ", Ville: " + resultSet.getString("ville"));
            }

            // Étape 5 : Fermer la connexion
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

