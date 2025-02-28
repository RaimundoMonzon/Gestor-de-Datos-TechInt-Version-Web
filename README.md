---

# TechInt Web - Data Management System 🌐

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-green)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)](https://www.mysql.com/)
[![Apache](https://img.shields.io/badge/Apache-2.4-red)](https://httpd.apache.org/)

A Spring Boot-based web application for managing company holdings, designed as part of **Programación 2** at INSPT-UTN. The system supports local and cloud deployment, with role-based access and secure API endpoints.

---

## 📋 Table of Contents
- [Features](#-features)
- [Technologies](#-technologies)
- [Installation](#-installation)
  - [Local Deployment](#local-deployment)
  - [Production Deployment](#production-deployment)
- [Configuration](#-configuration)
- [API Documentation](#-api-documentation)
- [Security](#-security)
- [Troubleshooting](#-troubleshooting)

---

## 🚀 Features
- **Multi-environment deployment**: Switch between local and cloud databases using Spring Profiles.
- **Role-based access**: Secure endpoints with Spring Security.
- **Apache integration**: Host the frontend via Apache (`port 80`).
- **Spring Boot backend**: REST API served on `port 8080`.
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

### Local Deployment
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/Gestor-de-Datos-TechInt-Version-Web.git
   ```
2. **Configure MySQL**:
   - Update `my.ini` to set `bind-address = 0.0.0.0`.
   - Create a MySQL user and grant privileges:
     ```sql
     CREATE USER 'rootRai'@'%' IDENTIFIED BY 'root';
     GRANT ALL PRIVILEGES ON holdingDataBase.* TO 'rootRai'@'%';
     FLUSH PRIVILEGES;
     ```
3. **Configure Spring Boot**:
   - Update `application.properties` for the `dev` profile:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/holdingDataBase
     spring.datasource.username=rootRai
     spring.datasource.password=root
     ```
4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

### Production Deployment
1. **AWS RDS Setup**:
   - Create a MySQL instance on AWS RDS.
   - Update the security group to allow inbound traffic on port `3306` from your IP or Render’s IP.
2. **Render Configuration**:
   - Set environment variables in Render:
     ```
     SPRING_PROFILES_ACTIVE=prod
     DATASOURCE_URL=jdbc:mysql://[RDS_ENDPOINT]:3306/holdingDataBase
     DATASOURCE_USERNAME=rootRai
     DATASOURCE_PASSWORD=your_production_password
     ```
3. **Deploy**:
   - Connect your GitHub repository to Render and trigger deployment.

---

## ⚙️ Configuration
### Spring Profiles
- **`dev` Profile**: Uses local MySQL database.
- **`prod` Profile**: Connects to AWS RDS. Activate via `SPRING_PROFILES_ACTIVE=prod`.

### Apache Setup
- Modify `httpd.conf` to:
  - Set `Listen 80`.
  - Configure document root to your frontend files.

---

## 🔒 Security
- **Spring Security**: Role-based access control for API endpoints.
- **JWT Authentication**: Securely authenticate users (example in Postman docs).
- **AWS Security Groups**: Restrict database access to trusted IPs.

---

## 📚 API Documentation
Endpoints are organized by entity (e.g., `holdings`, `companies`). Use Postman for testing:

| Endpoint               | Method | Description                |
|------------------------|--------|----------------------------|
| `/api/holdings`        | GET    | Fetch all holdings         |
| `/api/companies/{id}`  | POST   | Add a new company          |

For full details, import the Postman collection from `docs/postman/`.

---

## 🚨 Troubleshooting
- **MySQL Connection Issues**:
  - Verify `bind-address` in `my.ini` is `0.0.0.0`.
  - Check firewall rules for ports `80`, `8080`, and `3306`.
- **AWS RDS Access Denied**:
  - Whitelist your IP in the RDS security group.
  - Use `telnet [RDS_ENDPOINT] 3306` to test connectivity.

---

## 👥 Contributors
- **Raimundo Monzón** ([GitHub](https://github.com/RaimundoMonzon))

Supervised by **Prof. Diego P. Corsi**.
---

_📄 License: MIT_  
_🔗 Live Demo: ([Website](https://gestor-de-datos-techint-version-web.onrender.com/login))_

---
