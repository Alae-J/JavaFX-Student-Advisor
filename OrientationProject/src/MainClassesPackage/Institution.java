package MainClassesPackage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Exceptions.filiereExistantException;
import Exceptions.matiereExistantException;

public class Institution implements Evaluate,Serializable {
	String nom;
	String ville;
	String type ; //Ecole ou Universite 
	List<Filiere> filieres = new ArrayList<Filiere>();
	int capacite;
	String priveeOuPublique ;
	
	
	public Institution(String nom,String ville,String type,String pb,int c) {
		this.nom = nom;
		this.ville = ville;
		this.type= type;
		this.priveeOuPublique = pb;
		this.capacite=c;
	}
	
	public void ajouterFIliere(Filiere filiere) throws filiereExistantException {
		if (this.filieres.contains(filiere)) throw new filiereExistantException("matiere deja existe!");
		this.filieres.add(filiere);
	}	
		
//	public String PeutAcceder(Etudiant E) {
//		String r = "";
//			
//		for(int i=0;i<this.filieres.size();i++) {
//				
//			if (this.filieres.get(i).PeutAccederFiliere(E)==true) {
//					
//				r = r +" "+this.filieres.get(i).nom;
//					
//			}
//		}
//		if(r.trim().isEmpty()){
//			System.out.println("ne peut pas acceder");
//		}
//		return r;
//	}
	
	

	
	//verifier si un etudiant peut acceder a l'institut
	
	public String PeutAcceder(Etudiant E) {
		String r = "";
		
		for(int i=0;i<this.filieres.size();i++) {
			
			if (this.filieres.get(i).PeutAccederFiliere(E)==true) {
				
				r = r +" "+this.filieres.get(i).nom;
				
			}
		}
		return r;
	}
	
	public void sortFilieres() {
		
		Collections.sort(this.filieres);	
		
	}
	
	@Override 
	public String toString() {
		return " institution nom : "+this.nom+"\n ville : "+this.ville+"\n type : "+this.type+"\n capacite :"+this.capacite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public String getPriveeOuPublique() {
		return priveeOuPublique;
	}

	public void setPriveeOuPublique(String priveeOuPublique) {
		this.priveeOuPublique = priveeOuPublique;
	}
	




	
}
