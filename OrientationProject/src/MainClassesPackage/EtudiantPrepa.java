package MainClassesPackage;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EtudiantPrepa extends Etudiant implements Serializable {
	private String filierePrepa; //mp/psi/tsi/ecs
	private int classement ;
	
	
	
	

	
	
	
	public EtudiantPrepa(String nom, String prenom, String CIN, int age, String niveauEtude) {
		super(nom, prenom, CIN ,age, niveauEtude);
		
	}
	
	@Override
	public String institutionPermis(List<Institution> L) {
		String r = "";
		for (int i = 0; i <L.size(); i++) {
			if(L.get(i).PeutAcceder(this) != ""){
				
				r = r + " "+L.get(i).nom;
			}
		}
		if (r=="") { System.out.println("Aucune Institution n'est Permise!");}
		return "les ecoles permis sont :" + r;
	}
	
	
	@Override
	public List<Institution> institutionPermises(List<Institution> L) {
		List<Institution> institutions = new ArrayList<>();
		for (int i = 0; i <L.size(); i++) {
			if(L.get(i).PeutAcceder(this) != ""){
				
				institutions.add(L.get(i));
			}
		}
		
		return institutions;
	}

	@Override 
	public String toString() {
		return "nom :"+this.nom+"prenom : "+this.prenom+"filiere Prepa :"+this.filierePrepa + "Classement :"+this.classement;
	}

	
	
	
	
	
	
	
	//getters setters


	public String getFilierePrepa() {
		return filierePrepa;
	}





	public void setFilierePrepa(String filierePrepa) {
		this.filierePrepa = filierePrepa;
	}





	public int getClassement() {
		return classement;
	}





	public void setClassement(int classement) {
		this.classement = classement;
	}

}
