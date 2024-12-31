import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class test {

	public static void main(String[] args) {
		
		

	}
	
	// trier une list d'institutions 
	
	public static void sortInstitution(ArrayList<Institution> L) {
		Comparator c = new CompareInstitution();
		Collections.sort(L,c);
		
		
	}
	public static void sortFilieres(ArrayList<Filiere> L) {
		Collections.sort(L);
			
	}
		

}
