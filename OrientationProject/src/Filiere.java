import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Exceptions.matiereExistantException;

public class Filiere implements Comparable,Serializable{
	
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
	
	
	
	
	
	
	
	
	
	
	
	public boolean PeutAccederFiliere(Etudiant E) {
		if(E instanceof EtudiantParallele) {
			if (((EtudiantParallele) E).getClassementDansUniversitee() <= this.CE.classementUnivMin 
					&& ((EtudiantParallele) E).getNoteDiplome() >= this.CE.noteMinPara 
					&& E.age <= this.CE.ageMax) {
				return true;
			}
			return false;
			
			
		}
		if(E instanceof EtudiantPrepa) {
			if ( ((EtudiantPrepa)E).getFilierePrepa() == "mp" && E.age <= this.CE.ageMax) {
				return ((EtudiantPrepa) E).getClassement() <= this.CE.classementMinMp;
				
			}
			if ( ((EtudiantPrepa)E).getFilierePrepa() == "psi" && E.age <= this.CE.ageMax ) {
				return ((EtudiantPrepa) E).getClassement() <= this.CE.classementMinPsi;

				
			}
			if ( ((EtudiantPrepa)E).getFilierePrepa() == "tsi" && E.age <= this.CE.ageMax) {
				return ((EtudiantPrepa) E).getClassement() <= this.CE.classementMinTsi;

	
			}
			if ( ((EtudiantPrepa)E).getFilierePrepa() == "ecs" && E.age <= this.CE.ageMax) {
				return ((EtudiantPrepa) E).getClassement() <= this.CE.classementMinEc;

	
			}
			return false;
			
			
		}
	return false;
	}

	
	

	@Override
	public int compareTo(Object o) {
		if(this.CE.classementMinEc + this.CE.classementMinMp + this.CE.classementMinPsi + this.CE.classementMinTsi + (20-this.CE.noteMinPara) < ((Filiere)o).CE.classementMinEc + ((Filiere)o).CE.classementMinMp + ((Filiere)o).CE.classementMinPsi + ((Filiere)o).CE.classementMinTsi + (20-((Filiere)o).CE.noteMinPara)) {
			return 1;
		}
		else if(this.CE.classementMinEc + this.CE.classementMinMp + this.CE.classementMinPsi + this.CE.classementMinTsi + (20-this.CE.noteMinPara) > ((Filiere)o).CE.classementMinEc + ((Filiere)o).CE.classementMinMp + ((Filiere)o).CE.classementMinPsi + ((Filiere)o).CE.classementMinTsi + (20-((Filiere)o).CE.noteMinPara)) {
			return -1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "nom :"+this.nom + "  duree : "+this.duree+"  Conditions : \n"+this.CE;
	}



	
	
	


}
