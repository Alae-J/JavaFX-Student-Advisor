import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class test implements ObjectInOutFile {

	public static void main(String[] args) {
		
		try {
            // Step 1: Create Institutions
            Institution institution1 = new Institution("Ecole Centrale", "Casablanca", "Ecole", "Public", 100) {};
            Institution institution2 = new Institution("Université Cadi Ayyad", "Marrakech", "Université", "Public", 200) {};

            // Step 2: Create Filiere and Conditions
            Domaine domaine1 = new Domaine();
            domaine1.nom = "Informatique";

            ConditionEntree ce1 = new ConditionEntree(24, 15, 100, 50, 60, 70, 80);
            Filiere filiere1 = new Filiere("Informatique Appliquée", 3, "Master", domaine1);
            filiere1.ajouterCondition(ce1);

            ConditionEntree ce2 = new ConditionEntree(25, 14, 120, 55, 65, 75, 85);
            Filiere filiere2 = new Filiere("Génie Industriel", 2, "Licence", domaine1);
            filiere2.ajouterCondition(ce2);

            // Step 3: Add Filiere to Institutions
            institution1.ajouterFIliere(filiere1);
            institution2.ajouterFIliere(filiere2);

            // Step 4: Create Etudiant and Preferences
            EtudiantParallele etudiant1 = new EtudiantParallele("Ahmed", "Ben", "A123", "CNE123", 23, "Bac+3", "Université Hassan II", "Licence");
            etudiant1.setClassementDansUniversitee(80);
            etudiant1.setNoteDiplome(16);

            EtudiantPrepa etudiant2 = new EtudiantPrepa("Sara", "El", "B456", "CNE456", 22, "Bac+2", "Prépa ENSA", "Classe Prépa");
            etudiant2.setClassement(55);
            etudiant2.setFilierePrepa("mp");

            etudiant1.ajouterDomainesPreferees(domaine1);
            etudiant1.ajouterFilierePreferees(filiere1);

            // Step 5: Test Access and Sorting
            System.out.println(etudiant1.institutionPermis(List.of(institution1, institution2)));
            System.out.println(etudiant2.institutionPermis(List.of(institution1, institution2)));

            // Step 6: Test Sorting
            ArrayList<Institution> institutions = new ArrayList<>(List.of(institution1, institution2));
            sortInstitution(institutions);
            institutions.forEach(System.out::println);

            ArrayList<Filiere> filieres = new ArrayList<>(List.of(filiere1, filiere2));
            sortFilieres(filieres);
            filieres.forEach(f -> System.out.println(f.nom));

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		//testing the ObjectInOutFile
		try {
	        // Step 1: Create a Filiere object
	        Domaine domaine = new Domaine();
	        domaine.nom = "Engineering";
	        Filiere filiere = new Filiere("Computer Science", 3, "Bachelor", domaine);
	        filiere.ajouterCondition(new ConditionEntree(24, 12, 100, 10, 15, 20, 25));
	        System.out.println("Original Filiere object:");
	        System.out.println(filiere);

	        // Step 2: Save the Filiere object to a file
	        String fileName = "filiere.txt";
	        ObjectInOutFile.saveObject(filiere, fileName);
	        System.out.println("\nFiliere object saved to file: " + fileName);

	        // Step 3: Read the Filiere object back from the file
	        Filiere loadedFiliere = ObjectInOutFile.readObject(fileName);
	        System.out.println("\nLoaded Filiere object from file:");
	        System.out.println(loadedFiliere);

	        // Check if the saved and loaded objects are equivalent
	        System.out.println("\nAre the original and loaded objects equal?");
	        System.out.println(filiere.compareTo(loadedFiliere)==0 ? "Yes" : "No");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
		

	
	
	// trier une list d'institutions 
	
	public static void sortInstitution(ArrayList<Institution> L) {
		Comparator c = new CompareInstitution();
		Collections.sort(L,c);
		
		
	}
	public static void sortFilieres(ArrayList<Filiere> L) {
		Collections.sort(L);
			
	}
		

}
