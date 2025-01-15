package DeletedClasses;
public class Ecole extends Institution implements Description {
	

	private String TypeEcole; //ing , medcine / pharmacie ... 
	private boolean cantineDisponible; 
	private boolean internatDisponible;
	
	
	
	
	public Ecole(String nom, String ville, String pb, int c) {
		super(nom, ville, pb, c);
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

	public boolean isCantineDisponible() {
		return cantineDisponible;
	}

	public void setCantineDisponible(boolean cantineDisponible) {
		this.cantineDisponible = cantineDisponible;
	}

	public boolean isInternatDisponible() {
		return internatDisponible;
	}

	public void setInternatDisponible(boolean internatDisponible) {
		this.internatDisponible = internatDisponible;
	}

	public String getTypeEcole() {
		return TypeEcole;
	}

	public void setTypeEcole(String typeEcole) {
		TypeEcole = typeEcole;
	}
	
	
	
	
	
	

}
