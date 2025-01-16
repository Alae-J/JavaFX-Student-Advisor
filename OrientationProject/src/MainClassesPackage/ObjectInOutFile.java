package MainClassesPackage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface ObjectInOutFile {
	public static <T> void saveObject(T obj,String fichier) throws Exception{
		File f = new File(fichier);
		ObjectOutputStream out;
		out = new ObjectOutputStream(new FileOutputStream(f));
		out.writeObject(obj);
		out.close();
		
		
	}
	
	public static <T> T readObject(String fichier) throws Exception {
		File f = new File(fichier);
		ObjectInputStream in;
		in = new ObjectInputStream(new FileInputStream(f));
		T obj = (T)in.readObject();
		in.close();
		return obj;
		
		
	}

}
