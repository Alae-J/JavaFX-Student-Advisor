import java.util.ArrayList;
import java.util.List;

import Exceptions.filiereExistantException;

public abstract class Etudiant  {
	String nom;
	String prenom;
	String CIN;
	int age;
	String CNE;//code massar
	String niveauEtude ; //bac+2 ou bac+3
	String nomInstitutionActuel;
	String TypeDiplome;
	HashSet<Domaine> domainesPreferees = new HashSet<>();
	List<Filiere> filieresPreferees = new ArrayList<Filiere>();
	int EtudiantID;
	static int count = 0 ;
	
	
	public Etudiant (String nom, String prenom , String CIN , int age) {
		this.nom = nom;
		this.prenom = prenom;
		this.CIN = CIN;
		this.age = age;
	}
	
	public Etudiant(String nom, String prenom,String CIN,String CNE,int age,String niveauEtude,String nomInstitutionActuel,String TypeDiplome) {
		
		
		this.CNE=CNE;
		this.niveauEtude =niveauEtude;
		this.nomInstitutionActuel = nomInstitutionActuel;
		this.TypeDiplome = TypeDiplome;
		this.EtudiantID = ++count;
		
	}
	
	abstract public String institutionPermis(List<Institution> L);
	
	public void ajouterFilierePreferees(Filiere filiere) throws filiereExistantException{
		if (this.filieresPreferees.contains(filiere)) throw new domainExistantException("filiere deja existe!");
		this.filieresPreferees.add(filiere);
		
	}
	public void ajouterDomainesPreferees(Domaine domaine) {
        if (!domainesPreferees.add(domaine)) {
            // La méthode add retourne false si le domaine existe déjà
            System.out.println("Le domaine existe déjà !");
        }
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
	
	public void setAge(int age) throws AgeNegatifException {
	    if (age < 0) {
	        throw new AgeNegatifException("L'âge ne peut pas être négatif.");
	    }
	    this.age = age;
	}
}
