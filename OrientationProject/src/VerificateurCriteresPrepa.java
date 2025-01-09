
public class VerificateurCriteresPrepa implements VerificateurCriteres {
	
    @Override
    public boolean valider(Etudiant etudiant, ConditionAcces condition) {
    	
        if (etudiant instanceof EtudiantPrepa) {
            EtudiantPrepa etu = (EtudiantPrepa) etudiant;
            String filiere = etu.getFilierePrepa();
            int classement = etu.getClassement();

            return switch (filiere) {
                case "mp":
                	return classement <= condition.classementMinMp && etu.getAge() <= condition.ageMax;
                case "psi":
                	return classement <= condition.classementMinPsi && etu.getAge() <= condition.ageMax;
                case "tsi":
                	return classement <= condition.classementMinTsi && etu.getAge() <= condition.ageMax;
                case "ecs":
                	return classement <= condition.classementMinEc && etu.getAge() <= condition.ageMax;
                default:
                	return false;
            };
        }
        return false;
        
    }
    
}
