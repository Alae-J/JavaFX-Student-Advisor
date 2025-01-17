# 📚 Projet Orientation

## 📌 Description
**Orientation** est une application Java permettant aux étudiants de trouver la meilleure institution selon leur profil académique et leurs critères d’admission.

---

## ⚙️ Technologies Utilisées
- **Langage** : Java
- **Interface Graphique** : JavaFX
- **Base de Données** : MySQL
- **Outils** : GitHub

---

## 🏠 Structure du Projet
Le projet est organisé en plusieurs packages et fichiers :

### 📂 **Packages et Fichiers Principaux**
- **`MainClassesPackage`** → Contient les classes principales :
  - `Institution.java`
  - `Filiere.java`
  - `Etudiant.java` (classe abstraite)
  - `EtudiantPrepa.java`
  - `EtudiantParallele.java`
  - `Matiere.java`
  
- **`BD`** → Gestion des interactions avec MySQL :
  - `DatabaseTablesToObjects.java`
  - `DatabaseConnection1.java`
  - `ObjectInOutFile.java`

- **`view`** → Interface utilisateur JavaFX :
  - `Accueil.java`
  - `StudentsPage.java`
  - `InstitutionsPage.java`
  
- **`tests`** → Vérification des fonctionnalités :
  - `test.java` (chargement des données, tri, sauvegarde des objets)

---

## 🛠️ Installation et Exécution
### 📌 1. Prérequis
- Java **JDK 11+**
- MySQL **8.0+**
- Un IDE comme **IntelliJ IDEA** ou **Eclipse**
- MySQL Workbench (optionnel pour visualiser la base)

### 📌 2. Exécution du Projet
1. **Lancer le serveur MySQL**
   ```sh
   sudo systemctl start mysql
   ```
2. **Exécuter l'application**
   ```sh
   javac MainClassesPackage/test.java
   java MainClassesPackage.test
   ```
3. **Ou exécuter avec un IDE (Eclipse/IntelliJ)**

---

## 🐂 Base de Données
*(Ajoutez ici le lien vers votre schéma MCD si vous avez une image.)*

### **📌 Tables Principales**
| Table                  | Description |
|------------------------|-------------|
| `ETUDIANTPREPA`       | Étudiants des classes préparatoires |
| `ETUDIANPARALLELE`    | Étudiants des parcours parallèles |
| `INSTITUTION`         | Écoles et universités disponibles |
| `FILIERE`             | Formations proposées |
| `CONDITIONSDENTREES`  | Critères d’admission des filières |
| `MATIERE`            | Matières enseignées |

---

## 🔄 Fonctionnalités Clés
✔ **Gestion des étudiants** (prépa et parallèle)  
✔ **Consultation des institutions et filières**  
✔ **Validation des conditions d’entrée**  
✔ **Tri et recherche des filières par critères**  
✔ **Sauvegarde et chargement des données**  

---

## 📞 Contact et Contribution
💡 **Vous souhaitez contribuer ?**
- Forkez le projet 🍔
- Créez une branche 🚀
- Soumettez une pull request 🛠️

📧 **Contact** : [alaejahid8@gmail.com](mailto:alaejahid8@gmail.com)  
🔗 **GitHub** : [Projet Orientation](https://github.com/iichrome/Java-Project.git)
