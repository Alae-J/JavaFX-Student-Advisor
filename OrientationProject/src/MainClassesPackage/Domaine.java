package MainClassesPackage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Domaine implements Evaluate,Serializable {
	String nom;
	String dscriptionDomaine ;
	List<Filiere> filieres = new ArrayList<Filiere>();
	
	
	public String PeutAcceder(Etudiant E) {
		String r = "";
		
		for(int i=0;i<this.filieres.size();i++) {
			
			if (this.filieres.get(i).PeutAccederFiliere(E)==true) {
				
				r = r +" "+this.filieres.get(i).nom;
				
			}
		}
		if(r.trim().isEmpty()){
			System.out.println("ne peut rien acceder");
		}
		
		return r;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public List<Filiere> getFilieres() {
		return filieres;
	}


	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}
	
	
	
	
	

}
