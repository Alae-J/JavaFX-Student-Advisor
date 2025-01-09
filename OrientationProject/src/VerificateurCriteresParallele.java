

public class VerificateurCriteresParallele implements VerificateurCriteres {
	
    @Override
    public boolean valider(Etudiant etudiant, ConditionAcces condition) {
    	
        if (etudiant instanceof EtudiantParallele) {
            EtudiantParallele etu = (EtudiantParallele) etudiant;
            return etu.getClassementDansUniversite() <= condition.classementUniversiteMin
                && etu.getNoteDiplome() >= condition.noteMinParallele
                && etu.getAge() <= condition.ageMax;
        }
        return false;
        
    }
    
}
