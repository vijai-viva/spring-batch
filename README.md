# Spring Batch CSV to MySQL Writer

This is a simple Spring Batch application that demonstrates how to read data from a CSV file and write it to a MySQL database using Spring Boot and Spring Batch.

## 🛠️ Technologies Used

- Java 17
- Spring Boot (version 3.4.5)
- Spring Batch
- Spring Data JPA
- MySQL Database 5.7
- Gradle 8.12
- Lombok v1.18.38 "Envious Ferret"
- SLF4J / Logback

## 📁 Project Structure

```
src
├── main
│   ├── java
│   │   └── com.viva.batch
│   │       ├── config           # Batch job and step configuration
│   │       ├── entity           # Entity or DTO classes
│   │       ├── controller       
│   │       ├── repository       
│   │       └── SpringBatchprocessingDemoApplication.java
│   └── resources
│       ├── application.properties
│       ├── randon_users.csv            # Sample input file
│       └── tbl_cutomer.sql             # Table DDL (if applicable)
│       └── curl_test.sh                # shell script file to execute curl command
```

## 📝 Description

This project demonstrates how to:

- Set up a Spring Batch job that reads data from a CSV file.
- Map the CSV fields to a Java model class.
- Write the data into a MySQL database using a repository item writer.

The batch job is automatically triggered on application startup.

## ⚙️ How It Works

1. The batch job reads data from a CSV file using `FlatFileItemReader`.
2. It optionally processes the data using `ItemProcessor`.
3. Finally, it writes the processed data to a MySQL database using `RepositoryItemWriter` 

## 🧪 Sample CSV Format

```
id,name,emailid,age
1,Vijai Srirangan,vijai.sri@example.com,35
2,Vanitha KP,Vani@example.com,30
...
```

## 🐬 Database Configuration

Add the following to your `application.properties` 

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<<<<your_database_name>>>>
spring.datasource.username=<<your_username>>
spring.datasource.password=<<your_password>>
spring.jpa.hibernate.ddl-auto=update
```

## ✅ How to Run

### 1. Clone the repository

```bash
git clone -b csv-to-mysql-databasewrite https://github.com/vijai-viva/spring-batch.git
cd csv-to-mysql-databasewrite
```

### 2. Update database credentials

Modify `application.properties` with your MySQL credentials.

### 3. Build the project

For Gradle:

```bash
./gradlew build
```

if you ue Maven, (POM.xml)  use maven command to build hte project.

### 4. Run the application

```bash
java -jar target/csv-to-mysql-databasewrite.jar
```

or run from your ide as a java application or hte way your are comfortable.

### 5. Check the database

Verify that the data from the CSV has been inserted into the MySQL table.

## 📌 Things to Customize

- CSV file path or name
- Entity/model fields
- Table name and DDL
- Job parameters or scheduling
- Batch error handling and logging

## 🧩 Possible Enhancements

- Add unit and integration tests
- Add a REST API to trigger the batch job
- Add multi-threading or chunk tuning
- Export job execution logs

## 📄 License

No License.. its just a project creating during learning spring batch.. you can refer or reuse. Vijai Srirangan give you complete freedom to use it.

---

## 🙋‍♂️ Author

**Vijai Srirangan**  
LinkedIn: [Vijaikumar Srirangan ](https://www.linkedin.com/in/vijaisrirangan/)  
GitHub: [@vijai-viva](https://github.com/vijai-viva)

---

> Feel free to fork or clone this project and use it as a base for your batch processing needs!
