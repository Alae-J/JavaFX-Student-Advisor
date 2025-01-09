import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Domaine implements Evaluate {
	String nom;
	String dscriptionDomaine ;
	List<Filiere> filieres = new ArrayList<Filiere>();
	
	
	public String PeutAcceder(Etudiant E) {
	    String r = "";
	    // Conversion temporaire en liste triée par ordre alphabetique du nom (pour future features en ce qui concerne l'interface)
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
