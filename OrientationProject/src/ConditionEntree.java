import java.util.ArrayList;
import java.util.List;

public  class ConditionEntree {
	
	int ageMax = 0;
	
	int noteMinPara = 0;//pour parallele
	int classementUnivMin; //pour bac+3
	
	//pour prepa
	int classementMinMp=0;
	int classementMinPsi=0;
	int classementMinTsi=0;
	int classementMinEc=0;
	
	
	public ConditionEntree(int ageMin, int noteMinPara, int classementUnivMin,int classementMinMp, int classementMinPsi, int classementMinTsi, int classementMinEc) {
		this.ageMax = ageMin;
		this.noteMinPara = noteMinPara;
		this.classementUnivMin = classementUnivMin;
		this.classementMinMp = classementMinMp;
		this.classementMinPsi = classementMinPsi;
		this.classementMinTsi = classementMinTsi;
		this.classementMinEc = classementMinEc;
	}

}
	