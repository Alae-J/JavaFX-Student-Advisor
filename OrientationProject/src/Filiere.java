import java.util.ArrayList;
import java.util.List;

import Exceptions.matiereExistantException;

public class Filiere {
	
	String nom; 
	int duree;
	String typeDiplome; //license master ine 
	String DescriptionF;
	Domaine domaine ;
	List<Matiere> matieres = new ArrayList<Matiere>();
	ConditionEntree CE;
	
	public Filiere(String nom,int duree,String typeDiplome,Domaine domaine) {
		 this.nom= nom;
		 this.duree = duree ;
		 this.typeDiplome = typeDiplome;
		 this.domaine=domaine;
	}
	
	
	
	public void ajouterMatiere(Matiere M) throws matiereExistantException {
		if (this.matieres.contains(M)) throw new matiereExistantException("matiere deja existe!");
		this.matieres.add(M);
		
	}
	public void ajouterCondition(ConditionEntree CE) {
		this.CE=CE;
	}
	
	public boolean peutAccederFiliere(Etudiant etudiant) {
		List<VerificateurCriteres> verificateurs = new ArrayList<>();
	    verificateurs.add(new VerificateurCriteresParallele());
	    verificateurs.add(new VerificateurCriteresPrepa());

        for (VerificateurCriteres verificateur : verificateurs) {
            if (verificateur.valider(etudiant, this.conditionAcces)) {
                return true;
            }
        }
        return false;
    }



	
	
	


}
