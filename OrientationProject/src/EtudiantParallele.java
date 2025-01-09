import java.util.List;

public class EtudiantParallele extends Etudiant {


	private int classementDansUniversitee;
	private float noteDiplome;
	private String filiereUniv;
	private float noteSemestre[] = new float[4]; // 4semestre 
	
	public EtudiantParallele(String nom, String prenom, String CIN, String CNE, int age, String niveauEtude,
			String nomInstitutionActuel, String TypeDiplome) {
		super(nom, prenom, CIN, CNE, age, niveauEtude, nomInstitutionActuel, TypeDiplome);
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




	public float getNoteDiplome() {
		return noteDiplome;
	}




	public void setNoteDiplome(float noteDiplome) throws NoteException {
		if (noteDiplome < 0 || noteDiplome > 20) throw new NoteException("La note doit être entre 0 et 20!");
		this.noteDiplome = noteDiplome;
	}




	public String getFiliereUniv() {
		return filiereUniv;
	}




	public void setFiliereUniv(String filiereUniv) {
		this.filiereUniv = filiereUniv;
	}




	public float[] getNoteSemestre() {
		return noteSemestre;
	}




	public void setNoteSemestre(float noteSemestre[]) throws NoteException {
		for (noteSemestree: noteSemestre)
			if (noteSemestree < 0 || noteSemestree > 20) throw new NoteException("Les note doivent être entre 0 et 20!");
		this.noteSemestre = noteSemestre;
		
	}




	public int getClassementDansUniversitee() {
		return classementDansUniversitee;
	}




	public void setClassementDansUniversitee(int classementDansUniversitee) throws ClassementNegatifException {
		if (classementDansUniversitee <= 0) throw new ClassementNegatifException ("Le classement doit être un entier positif.");
		this.classementDansUniversitee = classementDansUniversitee;
	}





	
	

}
