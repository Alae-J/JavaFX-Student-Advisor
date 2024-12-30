import java.util.ArrayList;
import java.util.List;

public class Domaine implements Evaluate {
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
			return "ne peut rien acceder";
		}
		
		return r;
	}
	
	
	
	
	

}
