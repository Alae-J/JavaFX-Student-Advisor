package DeletedClasses;
import java.util.ArrayList;
import java.util.List;

public class Universite extends Institution implements Description {
	

	List<String> partenariats = new ArrayList<String>() ; 
	private String TypeUniversite  ; //sc ou li
	private boolean offreBourse ;
	
	public Universite(String nom, String ville, String pb, int c) {
		super(nom, ville, pb, c);
		// TODO Auto-generated constructor stub
	}
	public void ajouterPartenariats(String partenariat) {
		this.partenariats.add(partenariat);
	}
	
	
	
	
	
	
	
	@Override
	public String PeutAcceder(Etudiant E) {
		String r = "";
		
		for(int i=0;i<this.filieres.size();i++) {
			if (this.filieres.get(i).PeutAcceder(E)==true) {
				r = r +" "+this.filieres.get(i).nom;
				
			}
		}
		if (r=="") {
			
			
		}
		return r;
		
	}

	@Override
	public String afficherDetails(String E) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getTypeUniversite() {
		return TypeUniversite;
	}
	public void setTypeUniversite(String typeUniversite) {
		TypeUniversite = typeUniversite;
	}
	public boolean isOffreBourse() {
		return offreBourse;
	}
	public void setOffreBourse(boolean offreBourse) {
		this.offreBourse = offreBourse;
	} 

	
	
	

}
