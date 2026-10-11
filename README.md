# ModVault Backend – Full-Stack Java Garage Management System

ModVault is a production-grade vehicle lifecycle, aftermarket build tracking, and fleet management system built with Java 21, Spring Boot 3, and MySQL.

---

## 🛠 Tech Stack
- **Framework:** Spring Boot 3.3.4 (REST API)
- **Language:** Java 21 (LTS)
- **Database:** MySQL 8.0 (with zero-configuration H2 embedded fallback)
- **ORM & Data:** Spring Data JPA / Hibernate
- **Security:** Spring Security & JWT (JSON Web Tokens)
- **Validation:** Jakarta Validation
- **Build Tool:** Apache Maven 3.9.6

---

## 📁 Project Structure
```
modvault-backend/
├── src/
│   ├── main/
│   │   ├── java/com/modvault/
│   │   │   ├── config/          # SecurityConfig, CorsConfig, DataInitializer
│   │   │   ├── controller/      # REST Controllers (Auth, Dashboard, Vehicle, Mod, Tool, etc.)
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── entity/          # JPA Entities (Vehicle, Modification, Fuel, Expense, etc.)
│   │   │   ├── repository/      # Spring Data JPA Repositories
│   │   │   ├── security/        # JwtUtil, JwtFilter
│   │   │   └── service/         # Business Logic Services
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-mysql.properties
│   │       ├── schema.sql       # Production MySQL Schema
│   │       └── data.sql         # Seed Data
├── Dockerfile                   # Multi-stage production container build
├── .env.example                 # Environment variables template
└── pom.xml                      # Maven dependencies and build configuration
```

---

## 🔌 API Endpoints Reference

### 1. Dashboard & Core
- `GET /api/dashboard` – Fetches complete garage metrics, cars, charts, activities, and community feed.
- `GET /api/health` – Health check endpoint.

### 2. Authentication
- `POST /api/auth/login` – Login with email and password (`geethika@gmail.com` / `password123`), returns JWT token.
- `POST /api/auth/register` – Register a new garage account.
- `GET /api/auth/me` – Retrieve current user profile.

### 3. Vehicles (My Garage)
- `GET /api/vehicles` – List all garage vehicles sorted by primary status.
- `POST /api/vehicles` – Register new vehicle.
- `GET /api/vehicles/{id}` – Get vehicle by ID.
- `PUT /api/vehicles/{id}` – Update vehicle details.
- `DELETE /api/vehicles/{id}` – Remove vehicle from garage.

### 4. Modifications (Build Planner)
- `GET /api/modifications` – List all aftermarket parts and modifications.
- `POST /api/modifications` – Add a new planned or installed modification.
- `PUT /api/modifications/{id}` – Update part status or cost.
- `DELETE /api/modifications/{id}` – Remove part entry.

### 5. Maintenance & Reminders
- `GET /api/maintenance/reminders` – List upcoming service reminders.
- `POST /api/maintenance/reminders` – Add service alert rule.
- `GET /api/maintenance/logs` – List past service receipts.
- `POST /api/maintenance/logs` – Record maintenance completed.

### 6. Fuel & Expenses
- `GET /api/fuel` – Fuel refuel logs.
- `POST /api/fuel` – Log fuel fill-up with odometer and mileage calculation.
- `GET /api/expenses` – General vehicle expense ledger.
- `POST /api/expenses` – Record expense.

### 7. Garage Tools
- `POST /api/tools/cost-calculator` – Financial projection calculator (running cost/km, total cost of ownership).
- `POST /api/tools/compatibility-check` – Part fitment validator with compatibility clearance score.
- `GET /api/tools/build-comparison` – Stock vs Modified build comparative specs.

### 8. Community
- `GET /api/community/posts` – Enthusiast feed.
- `POST /api/community/posts` – Share a new build update.
- `POST /api/community/posts/{id}/like` – Like a community post.
- `POST /api/community/posts/{id}/bookmark` – Bookmark a post.

---

## 🚀 Running Locally

### Option 1: Automatic / Embedded Mode (Zero setup required)
```bash
mvn clean compile
mvn spring-boot:run
```
The application will automatically boot up on `http://localhost:8080`, populate in-memory database with the exact garage dataset (Honda Civic 2019, BMW 330i 2022, modifications, reminders, and charts), and expose all REST APIs.

### Option 2: With Local or Production MySQL
1. Ensure MySQL Server is running on port 3306.
2. Run the `src/main/resources/schema.sql` and `src/main/resources/data.sql` scripts in MySQL Workbench or MySQL CLI:
   ```bash
   mysql -u root -p < src/main/resources/schema.sql
   mysql -u root -p < src/main/resources/data.sql
   ```
3. Run the Spring Boot application with MySQL environment variables:
   ```bash
   SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/modvault?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC" \
   SPRING_DATASOURCE_USERNAME="root" \
   SPRING_DATASOURCE_PASSWORD="your_password" \
   mvn spring-boot:run
   ```

---

## 🌐 Production Deployment
The backend includes a production Dockerfile and can be deployed directly to:
- **Render / Railway / Fly.io / AWS ECS / Elastic Beanstalk**
- Just set the following environment variables in your hosting provider's dashboard:
  - `SPRING_DATASOURCE_URL` = `jdbc:mysql://<your_host>:3306/<db_name>?createDatabaseIfNotExist=true`
  - `SPRING_DATASOURCE_USERNAME` = `<your_username>`
  - `SPRING_DATASOURCE_PASSWORD` = `<your_password>`
  - `PORT` = `8080`
