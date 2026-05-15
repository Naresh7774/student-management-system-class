# Student Management System

A complete beginner-to-intermediate level full-stack web application built using **Java, Spring Boot, Spring Data JPA, Hibernate, MySQL, and Thymeleaf**. It features a modern and premium responsive frontend using HTML, CSS, and Font Awesome.

---

## 🚀 Features
- Add new students with validation.
- View a list of all students in an elegant table.
- Update existing student details.
- Delete students.
- Modern UI with gradients, shadows, micro-animations, and empty states.
- Automated CI pipeline using **GitHub Actions**.

---

## 🗄️ Database Setup (MySQL)

1. Open your MySQL client (e.g., MySQL Workbench or Command Line).
2. Create the database using the following command:
   ```sql
   CREATE DATABASE studentdb;
   ```
3. Update the credentials in `src/main/resources/application.properties` if they are different from `root`/`root`:
   ```properties
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   ```

---

## 💻 How to Run the Project Locally

**Prerequisites:**
- Java 17
- Maven
- MySQL

**Steps:**
1. Clone the repository to your local machine.
2. Ensure your MySQL server is running and the database `studentdb` is created.
3. Open the project in your favorite IDE (IntelliJ IDEA, Eclipse, VS Code).
4. Run the main class `StudentManagementSystemApplication.java` OR use Maven in the terminal:
   ```bash
   mvn spring-boot:run
   ```
5. Open your browser and navigate to: `http://localhost:8080/students`

---

## 🛠️ How to Push to GitHub

To push this project to your GitHub repository, run the following commands in your terminal from the project root directory:

```bash
# Initialize a new Git repository
git init

# Add all files to staging
git add .

# Commit the changes
git commit -m "Initial commit: Completed Student Management System"

# Add your remote repository URL
git remote add origin https://github.com/your-username/your-repository-name.git

# Push to the main branch
git push -u origin main
```

---

## ⚙️ How GitHub Actions Works in this Project

This project includes a CI pipeline defined in `.github/workflows/main.yml`. 
- **Trigger**: Every time you push code to the `main` branch or create a pull request.
- **Environment**: It spins up a fresh `ubuntu-latest` virtual machine.
- **Setup**: It installs Java 17 (Temurin distribution).
- **Build & Test**: It runs the `mvn -B package` command to download dependencies, compile the code, run tests, and build the final executable `.jar` file.
- **Artifact**: Finally, it uploads the `.jar` file as an artifact that you can download directly from the GitHub Actions tab.

---

## 📝 Resume Description

*Consider adding the following to your resume under "Projects":*

**Student Management System (Full-Stack Web App)**
*Tech Stack: Java, Spring Boot, Hibernate/JPA, MySQL, Thymeleaf, HTML/CSS, Maven, GitHub Actions*
- Designed and developed a full-stack web application to perform CRUD operations on student records using Spring Boot layered architecture (Controller, Service, Repository).
- Integrated Spring Data JPA and Hibernate for seamless object-relational mapping (ORM) and efficient database interactions with MySQL.
- Built a premium, responsive frontend using Thymeleaf and custom CSS featuring modern UI/UX principles (glassmorphism, micro-animations).
- Implemented robust backend form validation using Hibernate Validator (`@Valid`, `@NotEmpty`, `@Email`) to ensure data integrity.
- Engineered an automated CI/CD pipeline using GitHub Actions to automatically build, test, and package the application on every push to the `main` branch.

---

## 🎤 Interview Questions & Answers

**Q1: What is the benefit of using Spring Boot over standard Spring?**
> **A:** Spring Boot simplifies Spring application development by providing auto-configuration (reducing XML/Java config), embedded servers (like Tomcat, eliminating the need to deploy WAR files manually), and "starter" dependencies that bundle common libraries together.

**Q2: What is the role of `@Entity` and `@Table` in JPA?**
> **A:** `@Entity` marks a Java class as a JPA entity, meaning it will be mapped to a database table. `@Table` is optional but allows you to specify the exact name of the table in the database if it differs from the class name.

**Q3: Explain the layered architecture used in this project.**
> **A:** The project uses a 3-tier architecture:
> 1. **Controller Layer**: Handles HTTP requests, processes input, and returns views (Thymeleaf templates).
> 2. **Service Layer**: Contains business logic. It sits between the controller and repository to decouple logic from request handling.
> 3. **Repository Layer**: Interfaces with the database (extending `JpaRepository`) to perform CRUD operations without writing boilerplate SQL.

**Q4: How did you handle data validation?**
> **A:** I used the `spring-boot-starter-validation` dependency. In the `Student` entity, I added constraints like `@NotEmpty` and `@Email`. In the controller, I used the `@Valid` annotation next to `@ModelAttribute` and checked for errors using the `BindingResult` object. If errors exist, the user is redirected back to the form with error messages.

**Q5: What does `spring.jpa.hibernate.ddl-auto=update` do?**
> **A:** It tells Hibernate to automatically update the database schema (create/modify tables and columns) based on the entity classes when the application starts. It is great for development but generally avoided in production (where tools like Flyway or Liquibase are used).
