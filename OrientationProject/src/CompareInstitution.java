import java.util.Comparator;

public class CompareInstitution implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		
		// sorting the "filieres" Array
		
		((Institution)o1).sortFilieres();
		((Institution)o2).sortFilieres();
		
		//comparaison selon la 1er filiere 
		
		Filiere f1 = ((Institution)o1).filieres.get(0);
		Filiere f2 = ((Institution)o2).filieres.get(0);
		
		return f1.compareTo(f2);
		
	}
	

}
