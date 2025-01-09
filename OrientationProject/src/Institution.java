import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import Exceptions.matiereExistantException;


public abstract class Institution implements Evaluate {
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
	
	

	
	//verifier si un etudiant peut acceder a l'institut
	
	public String PeutAcceder(Etudiant E) {
	    String r = "";

	    // Conversion temporaire en liste triée par nom
	    List<Filiere> filieresTriees = new ArrayList<>(this.filieres);
	    Collections.sort(filieresTriees, new Comparator<Filiere>() {
	        @Override
	        public int compare(Filiere f1, Filiere f2) {
	            return f1.nom.compareTo(f2.nom);
	        }
	    });

	    for (Filiere filiere : filieresTriees) {
	        if (filiere.PeutAccederFiliere(E)) {
	            r += " " + filiere.nom;
	        }
	    }

	    if (r.trim().isEmpty()) {
	        return "ne peut rien acceder";
	    }

	    return r;
	}
	




	
}
