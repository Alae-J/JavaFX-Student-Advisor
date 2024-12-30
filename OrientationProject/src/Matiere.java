
public class Matiere {
	String nom;
	int coefficient;
	String enseignant;
	String description;
	
	
	
	
	public Matiere(String nom, int coefficient, String enseignant) {
		this.nom = nom;
		this.coefficient = coefficient;
		this.enseignant = enseignant;
	}
	
	
	
	
	public void ajouterDescription(String D) {
		this.description = D;
		
	}
	
}
