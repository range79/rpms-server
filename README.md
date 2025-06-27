# 🌟 Range Package Manager - Open Source Backend

**Range Package Manager** is an open-source backend project built for managing software packages. Designed with a scalable and modular architecture, this backend serves as the server-side component of the Range Package Manager ecosystem. It leverages technologies like **MongoDB**, **MapStruct**, **Thymeleaf**, and **Gradle** to provide a reliable, flexible, and developer-friendly environment.

---

## 🔧 About the Project

The backend system of **Range Package Manager** enables users to upload, search, retrieve, and delete packages via a RESTful API and an admin panel interface. The project is under active development and aims to become a robust and modern package management platform.

Feedback and contributions are always welcome to help improve the project and accelerate its progress.

---

## 💻 Technologies Used

- **MongoDB** – Flexible and scalable NoSQL database
- **Spring Boot** – Framework for building robust web and REST APIs
- **Thymeleaf** – Server-side rendering engine for dynamic HTML templates
- **MapStruct** – Java bean mapping framework for DTO <-> entity conversion
- **Gradle** – Build tool for dependency and project management
- **Spring Security & JWT** – Secure authentication and authorization system
- **Swagger (OpenAPI)** – Interactive and auto-generated API documentation

---

## 📈 Project Status

The project is currently in **active development**. Existing features include:

- Package upload and metadata storage
- REST API and admin panel integration
- Swagger-based API documentation
- Basic authentication via JWT
- Error handling and exception mapping

For a detailed list of updates, see [CHANGELOG.md](CHANGELOG.md).

---

## 🤝 Contributing

Community contributions are highly appreciated!  
To contribute:

1. Fork the repository 🍴  
2. Create a new branch (`feature/your-feature` or `bugfix/your-fix`)  
3. Commit your changes with clear messages  
4. Submit a Pull Request 📤  

Make sure your code is readable, tested (where applicable), and well-documented.

---


## ⚙️ Configuration Example

Below is an example `application.yml` configuration:

```yaml
spring:
  datasource:
    url: #(your-database-url) like jdbc:postgresql://localhost:5432/users
    username: #your-database-user-usernamename in compose.yaml
    password: #your-database-password in compose.yaml
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  data:
    mongodb:
      host: localhost
      port: 27017
      database: something

  thymeleaf:
    enabled: true
    prefix: classpath:/templates/
    suffix: .html

  servlet:
    multipart:
      max-file-size: 1000MB
      max-request-size: 1000MB

springdoc:
  api-docs:
    enabled: true

app:
  jwt-secret: #you need it base64 format
  jwt-duration: 22
  https: true

````

---

## 🐳 Docker Compose Support

A basic `docker-compose.yml` file is included for setting up MongoDB:

```yaml
services:
  mongodb:
    image: 'mongo:latest'
    environment:
      - MONGO_INITDB_DATABASE=something

    ports:
      - '27017:27017'

  postgres:
    image: 'postgres:latest'
    environment:
      - POSTGRES_DB=users
      - POSTGRES_USER=  #postgress user name  you need fil  it
      - POSTGRES_PASSWORD= #postgress password
    ports:
      - '5432:5432'

```

To start MongoDB locally, run:

```bash
docker-compose up -d
```

---

## 📬 Contact

For feedback, contributions, or questions, feel free to open an issue or submit a pull request. All help is welcome!

---

## 📝 Changelog

See [CHANGELOG.md](CHANGELOG.md) for full version history and updates.


# Contributors

👥 See the full list of amazing contributors [here](CONTRIBUTORS.md).



