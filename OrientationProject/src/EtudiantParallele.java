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
		for (int i = 0; i <L.size(); i++) {
			if(L.get(i).PeutAcceder(this) != ""){
				
				r = r + " "+L.get(i).nom;
			}
			
		}
		return "les ecoles permis sont :" + r;
	}




	public float getNoteDiplome() {
		return noteDiplome;
	}




	public void setNoteDiplome(float noteDiplome) {
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




	public void setNoteSemestre(float noteSemestre[]) {
		this.noteSemestre = noteSemestre;
		
		
		
	}




	public int getClassementDansUniversitee() {
		return classementDansUniversitee;
	}




	public void setClassementDansUniversitee(int classementDansUniversitee) {
		this.classementDansUniversitee = classementDansUniversitee;
	}





	
	

}
