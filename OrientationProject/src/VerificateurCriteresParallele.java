public class VerificateurCriteresParallele implements VerificateurCriteres {

    @Override
    public boolean valider(Etudiant etudiant, ConditionEntree condition) {
        if (etudiant instanceof EtudiantParallele) {
            EtudiantParallele etu = (EtudiantParallele) etudiant;
            return etu.getClassementDansUniversitee() <= condition.classementUnivMin
                && etu.getNoteDiplome() >= condition.noteMinPara
                && etu.age <= condition.ageMax;
        }
        return false;
    }
}
