package MainClassesPackage;
import java.util.ArrayList;
import java.util.List;

import Exceptions.domaineExistantException;
import Exceptions.filiereExistantException;

public abstract class Etudiant  {
	String nom;
	String prenom;
	String CIN;
	int age;
	String niveauEtude ; //bac+2 ou bac+3
	List<Domaine> domainesPreferees = new ArrayList<Domaine>();
	List<Filiere> filieresPreferees = new ArrayList<Filiere>();
	int EtudiantID;
	static int count = 0 ;
	
	
	public Etudiant(String nom, String prenom,String CIN,int age,String niveauEtude) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.CIN = CIN;
		this.niveauEtude =niveauEtude;
		this.EtudiantID = ++count;
		
	}
	
	abstract public String institutionPermis(List<Institution> L);
	
	public void ajouterFilierePreferees(Filiere filiere) throws filiereExistantException{
		if (this.filieresPreferees.contains(filiere)) throw new filiereExistantException("filiere deja existe!");
		this.filieresPreferees.add(filiere);
		
	}
	public void ajouterDomainesPreferees(Domaine domaine) throws domaineExistantException {
		if (this.domainesPreferees.contains(domaine)) throw new domaineExistantException("domaine deja existe!");
		this.domainesPreferees.add(domaine);
	}
	
	public String filieresPrefereesPermis() {
		String r="";
		for(Filiere F:this.filieresPreferees) {
			if(F.PeutAccederFiliere(this)) {
				r=r + " " +F.nom;
			}
		}
		return r;
	}
	
	public String DomainesPrefereesPermis() {
		String r="";
		for(Domaine D:this.domainesPreferees) {
			if(D.PeutAcceder(this)=="") {
				r=r + " " +D.nom;
			}
		}
		return r;
	}
	
	
	public abstract List<Institution> institutionPermises(List<Institution> L);

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCIN() {
		return CIN;
	}

	public void setCIN(String cIN) {
		CIN = cIN;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNiveauEtude() {
		return niveauEtude;
	}

	public void setNiveauEtude(String niveauEtude) {
		this.niveauEtude = niveauEtude;
	}

	public List<Domaine> getDomainesPreferees() {
		return domainesPreferees;
	}

	public void setDomainesPreferees(List<Domaine> domainesPreferees) {
		this.domainesPreferees = domainesPreferees;
	}

	public List<Filiere> getFilieresPreferees() {
		return filieresPreferees;
	}

	public void setFilieresPreferees(List<Filiere> filieresPreferees) {
		this.filieresPreferees = filieresPreferees;
	}
	
	
	

}
