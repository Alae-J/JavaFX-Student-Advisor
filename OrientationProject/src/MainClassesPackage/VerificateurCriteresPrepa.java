package MainClassesPackage;
public class VerificateurCriteresPrepa implements VerificateurCriteres {

    @Override
    public boolean valider(Etudiant etudiant, ConditionEntree condition) {

        if (etudiant instanceof EtudiantPrepa) {
            EtudiantPrepa etu = (EtudiantPrepa) etudiant;
            String filiere = etu.getFilierePrepa();
            int classement = etu.getClassement();

            return switch (filiere) {
                case "mp" -> classement <= condition.classementMinMp && etu.age <= condition.ageMax;
                case "psi" -> classement <= condition.classementMinPsi && etu.age <= condition.ageMax;
                case "tsi" -> classement <= condition.classementMinTsi && etu.age <= condition.ageMax;
                case "ecs" -> classement <= condition.classementMinEc && etu.age <= condition.ageMax;
                default -> false;
            };
        }
        return false;
    }
}
