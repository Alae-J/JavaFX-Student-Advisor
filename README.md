# 🎓 JavaFX Student Advisor

A JavaFX desktop application that helps students find the most suitable institutions and majors based on their academic profile and admission criteria.

---

## 📌 Project Overview

This was developed as part of an academic project to practice Java, JavaFX, and MySQL integration. The application allows students to browse institutions, explore available programs, and evaluate their eligibility based on personalized conditions.

---

## ⚙️ Technologies Used

- **Language:** Java  
- **GUI Framework:** JavaFX  
- **Database:** MySQL  
- **Tools:** IntelliJ IDEA / Eclipse, MySQL Workbench, Git & GitHub

---

## 🗂️ Project Structure

The codebase is organized into clear packages:

### 🔹 `MainClassesPackage` – Core Models
- `Institution.java`
- `Filiere.java`
- `Etudiant.java` *(abstract)*
- `EtudiantPrepa.java`
- `EtudiantParallele.java`
- `Matiere.java`

### 🔹 `BD` – Database Handling
- `DatabaseConnection1.java`
- `DatabaseTablesToObjects.java`
- `ObjectInOutFile.java`

### 🔹 `view` – JavaFX User Interface
- `Accueil.java`
- `StudentsPage.java`
- `InstitutionsPage.java`

### 🔹 `tests` – Testing Logic
- `test.java` *(loads and saves data, sorts and filters results)*

---

## 💠 Getting Started

### ✅ Prerequisites
- Java JDK **11+**
- MySQL **8.0+**
- JavaFX SDK (included in modern IDEs like IntelliJ or Eclipse)
- MySQL Workbench *(optional, for schema visualization)*

### ▶️ Running the Project

1. Start your MySQL server:
   ```bash
   sudo systemctl start mysql
   ```

2. Compile and run from CLI:
   ```bash
   javac MainClassesPackage/test.java
   java MainClassesPackage.test
   ```

3. Or run the project via your IDE (IntelliJ, Eclipse, etc.)

---

## 🗃️ Database Overview

### Key Tables

| Table Name           | Description                          |
|----------------------|--------------------------------------|
| `ETUDIANTPREPA`      | Students from preparatory classes    |
| `ETUDIANTPARALLELE`  | Students from parallel pathways      |
| `INSTITUTION`        | Registered universities/schools      |
| `FILIERE`            | Available academic programs          |
| `CONDITIONSDENTREES` | Program admission requirements       |
| `MATIERE`            | Subjects associated with programs    |

---

## ✨ Key Features

- 🧑‍🎓 Student profile management (Prepa & Parallel)  
- 🏩 Institution and major browsing  
- ✅ Admission condition validation  
- 🔍 Program filtering and sorting  
- 💾 Data saving and loading (file-based)

---

## 🤝 Contribution

Contributions are welcome!

1. Fork this repository  
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)  
3. Commit your changes (`git commit -m 'feat: add amazing feature'`)  
4. Push to the branch (`git push origin feature/AmazingFeature`)  
5. Open a pull request

---

## 💌 Contact

- Email: [alaejahid8@gmail.com](mailto:alaejahid8@gmail.com)  
- GitHub: [Alae-J](https://github.com/Alae-J)

---

> This project was one of my early steps in full-stack Java development. While it may not be perfect, it represents hands-on experience with backend, frontend, and database integration — and that’s what matters most.
