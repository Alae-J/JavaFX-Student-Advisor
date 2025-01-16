package MainClassesPackage;
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
	
	
	
	
	
	
	
	
	
	
	
//	public boolean PeutAccederFiliere(Etudiant E) {
//		if(E instanceof EtudiantParallele) {
//			if (((EtudiantParallele) E).getClassementDansUniversitee() <= this.CE.classementUnivMin 
//					&& ((EtudiantParallele) E).getNoteDiplome() >= this.CE.noteMinPara 
//					&& E.age <= this.CE.ageMax) {
//				return true;
//			}
//			return false;
//			
//			
//		}
//		if(E instanceof EtudiantPrepa) {
//			if ( ((EtudiantPrepa)E).getFilierePrepa() == "mp" && E.age <= this.CE.ageMax) {
//				return ((EtudiantPrepa) E).getClassement() <= this.CE.classementMinMp;
//				
//			}
//			if ( ((EtudiantPrepa)E).getFilierePrepa() == "psi" && E.age <= this.CE.ageMax ) {
//				return ((EtudiantPrepa) E).getClassement() <= this.CE.classementMinPsi;
//
//				
//			}
//			if ( ((EtudiantPrepa)E).getFilierePrepa() == "tsi" && E.age <= this.CE.ageMax) {
//				return ((EtudiantPrepa) E).getClassement() <= this.CE.classementMinTsi;
//
//	
//			}
//			if ( ((EtudiantPrepa)E).getFilierePrepa() == "ecs" && E.age <= this.CE.ageMax) {
//				return ((EtudiantPrepa) E).getClassement() <= this.CE.classementMinEc;
//
//	
//			}
//			return false;
//			
//			
//		}
//	return false;
//	}
	
	public boolean PeutAccederFiliere(Etudiant etudiant) {
	    List<VerificateurCriteres> verificateurs = new ArrayList<>();
	    verificateurs.add(new VerificateurCriteresParallele());
	    verificateurs.add(new VerificateurCriteresPrepa());

	    for (VerificateurCriteres verificateur : verificateurs) {
	        if (verificateur.valider(etudiant, this.CE)) {
	            return true;
	        }
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
		
		return " nom :"+this.nom + "  duree : "+this.duree+"  Conditions : \n"+this.CE;
		
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public int getDuree() {
		return duree;
	}



	public void setDuree(int duree) {
		this.duree = duree;
	}



	public String getTypeDiplome() {
		return typeDiplome;
	}



	public void setTypeDiplome(String typeDiplome) {
		this.typeDiplome = typeDiplome;
	}



	public Domaine getDomaine() {
		return domaine;
	}



	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}



	public List<Matiere> getMatieres() {
		return matieres;
	}



	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}



	public ConditionEntree getCE() {
		return CE;
	}



	public void setCE(ConditionEntree cE) {
		CE = cE;
	}



	
	
	


}
