# Temple Database Management System

A full-stack temple management application built with Spring Boot and React for managing temple operations including members, staff, volunteers, poojas, halls, and bookings.

## Tech Stack

### Backend
- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Security** - Authentication & Authorization
- **Spring Data JPA** - Database ORM
- **MySQL** - Database
- **Maven** - Build tool

### Frontend
- **React 19.2** - UI Framework
- **React Router** - Navigation
- **Axios** - HTTP Client
- **Vite** - Build tool

## Features

- 🔐 User authentication (register/login)
- 👥 Member management with pooja bookings
- 👔 Staff management with roles and salaries
- 🙏 Pooja (prayer service) management
- 🏛️ Hall management and booking system
- 🤝 Volunteer tracking
- 📊 RESTful API architecture

## Project Structure

```
temple-database/
├── src/main/java/com/temple/temple_database/
│   ├── config/          # Security configuration
│   ├── controller/      # REST controllers
│   ├── dto/            # Data transfer objects
│   ├── exception/      # Exception handlers
│   ├── model/          # JPA entities
│   ├── repository/     # Data repositories
│   └── service/        # Business logic
├── frontend/           # React application
└── pom.xml            # Maven dependencies
```

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+
- Node.js 18+ and npm (for frontend)

## Setup Instructions

### 1. Database Setup

Create a MySQL database:
```sql
CREATE DATABASE temple_db;
```

### 2. Backend Configuration

Copy and configure the application properties:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/temple_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run Backend

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or using Maven
mvn spring-boot:run
```

Backend will start on `http://localhost:8080`

### 4. Run Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend will start on `http://localhost:5173`

## API Documentation

See [API_ENDPOINTS.md](API_ENDPOINTS.md) for complete API documentation.

### Quick API Overview

- **Authentication**: `/api/auth/*` - Register and login
- **Members**: `/api/members/*` - Member CRUD operations
- **Staff**: `/api/staff/*` - Staff management
- **Poojas**: `/api/poojas/*` - Pooja services
- **Halls**: `/api/halls/*` - Hall management
- **Hall Bookings**: `/api/hall-bookings/*` - Booking management
- **Volunteers**: `/api/volunteers/*` - Volunteer tracking

## Development

### Build Backend
```bash
mvn clean package
```

### Build Frontend
```bash
cd frontend
npm run build
```

### Run Tests
```bash
mvn test
```

## Security

- Passwords are encrypted using Spring Security
- Authentication required for all endpoints except `/api/auth/*`
- Phone numbers are used as unique identifiers for login

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License.

## Contact

For questions or support, please open an issue in the repository.
