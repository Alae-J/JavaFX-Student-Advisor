 import java.util.List;

public class EtudiantPrepa extends Etudiant {
	private String filierePrepa; //mp/psi/tsi/ecs
	private int classement ;
	
	
	
	

	
	
	
	public EtudiantPrepa(String nom, String prenom, String CIN, String CNE, int age, String niveauEtude,
			String nomInstitutionActuel, String TypeDiplome) {
		super(nom, prenom, CIN, CNE, age, niveauEtude, nomInstitutionActuel, TypeDiplome);
		// TODO Auto-generated constructor stub  
	}
	
	@Override
	public String institutionPermis(List<Institution> L) {
		String r = "";
		for (int i = 0; i <L.size(); i++) {
			if(L.get(i).PeutAcceder(this) != ""){
				
				r = r + " "+L.get(i).nom;
			}
		}
		if (r.trim().isEmpty()) return "Aucune Institution n'est Permise!";
		return "les ecoles permis sont :" + r;
	}





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
