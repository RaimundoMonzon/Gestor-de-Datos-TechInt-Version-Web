---

# TechInt Web - Data Management System 🌐

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-green)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)](https://www.mysql.com/)
[![Apache](https://img.shields.io/badge/Apache-2.4-red)](https://httpd.apache.org/)

A role-based web application developed with Spring Boot for managing companies, users, and contracts, created as part of **Programación II** at INSPT-UTN. The system supports local and cloud deployment, with role-based access and secure API endpoints.

---

## 📋 Table of Contents  
- [Features](#-features)  
- [Technologies](#-technologies)  
- [Installation](#-installation)  
- [Configuration](#-configuration)  
- [Security](#-security)  
- [Troubleshooting](#-troubleshooting)  

---

## 🚀 Features  
- **Role-based access control**:  
  - **Admins**: Create users, countries, operational areas (e.g., Marketing, Finance), and companies.  
  - **Vendors**: Create sub-vendors and track sales income.  
  - **Advisors**: Establish consulting contracts with companies.  
- **Multi-environment support**: Switch between local and production databases using Spring Profiles.  
- **Thymeleaf integration**: Dynamic HTML templates for server-side rendering.  
- **AWS RDS support**: Scalable MySQL database for production.

---

## 🛠 Technologies
- **Backend**: 
  - Spring Boot 3
  - Spring Data JPA
  - Spring Security
- **Database**: 
  - MySQL 8.0 (Local & AWS RDS)
- **Server**: 
  - Apache HTTP Server (Frontend)
  - Embedded Tomcat (Spring Boot)
- **Deployment**: 
  - AWS RDS (Database)
  - Render (Backend Hosting)

---

## 📥 Installation  
1. **Clone the repository**:  
   ```bash  
   git clone https://github.com/yourusername/Gestor-de-Datos-TechInt-Version-Web.git  
   ```  

2. **Database Setup**:  
   - Install MySQL and XAMPP.  
   - Create a database named `holdingDataBase`.  
   - Update `src/main/resources/application.properties`:  
     ```properties  
     # Local development (dev profile)  
     spring.datasource.url=jdbc:mysql://localhost:3306/holdingDataBase  
     spring.datasource.username=root  
     spring.datasource.password=your_password  
     ```  

3. **Run the application**:  
   ```bash  
   mvn spring-boot:run  
   ```  

4. **Access the app**:  
   - Frontend: `http://localhost:80` (Apache)  
   - Backend: `http://localhost:8080` (Spring Boot)  

---

## ⚙️ Configuration  
### Spring Profiles  
- **Local Development (`dev` profile)**:  
  Uses the local MySQL database. Activate via:  
  ```properties  
  spring.profiles.active=dev  
  ```  

- **Production**:  
  Configure AWS RDS and set environment variables:  
  ```properties  
  spring.datasource.url=${DATASOURCE_URL}  
  spring.datasource.username=${DATASOURCE_USERNAME}  
  spring.datasource.password=${DATASOURCE_PASSWORD}  
  ```  

### Apache Setup  
- Modify `httpd.conf` to:  
  - Bind to `0.0.0.0:80`.  
  - Allow access to port `3306` (MySQL).  

---

## 🔒 Security  
- **Spring Security**: Restricts access based on roles (`ADMIN`, `VENDOR`, `ADVISOR`).  
- **Password Encryption**: BCrypt password hashing.  
- **Session Management**: HTTP session-based authentication.  

---

## 🚨 Troubleshooting  
- **MySQL Connection Issues**:  
  - Verify `bind-address = 0.0.0.0` in `my.ini`.  
  - Check firewall rules for ports `80` and `3306`.  
- **Profile Activation**:  
  Ensure the correct profile is set in `application.properties`.  

---

## 👤 Credits  
- **Developer**: [Your Name] ([GitHub](https://github.com/yourusername))  
- **Supervisor**: Prof. Diego P. Corsi (Programación II, INSPT-UTN)  

---
