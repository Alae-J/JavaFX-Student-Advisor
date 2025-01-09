import java.util.List;

public class EtudiantPrepa extends Etudiant {
	private String filierePrepa; //mp/psi/tsi/ecs
	private int classement;
	
	
	
	public EtudiantPrepa(String nom, String prenom, String CIN, String CNE, int age, String niveauEtude,
			String nomInstitutionActuel, String TypeDiplome) throws AgeNegatifException {
		super(nom, prenom, CIN, CNE, age, niveauEtude, nomInstitutionActuel, TypeDiplome);
		if (age < 0) throw new AgeNegatifException ("L'âge ne peut pas être négatif.");
	}
	
	@Override
	public String institutionPermis(List<Institution> L) {
	    String r = "";
	    // Conversion temporaire en liste triée par nom
	    List<Institution> institutionsTriees = new ArrayList<>(L);
	    Collections.sort(institutionsTriees, new Comparator<Institution>() {
	    	@Override
	    	public int compare (Institution I1, Institusion I2	 ) {
	    		return I1.nom.compareTo(I2.nom);
	    	}
	    });

	    for (Institution institution : institutionsTriees) {
	        if (!institution.PeutAcceder(this).isEmpty()) {
	            r += " " + institution.nom;
	        }
	    }

	    if (r.trim().isEmpty()) {
	        return "Aucune Institution n'est Permise!";
	    }

	    return "les ecoles permises sont :" + r;
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


	public void setClassement(int classement) throws ClassementNegatifException {
		if (classement <= 0) throw new ClassementNegatifException ("Le classement doit être un entier positif.");
		this.classement = classement;
	}

}
