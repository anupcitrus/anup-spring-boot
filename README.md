# Shop API - Spring Boot REST Application

A complete Spring Boot REST API application that connects to MySQL database and provides CRUD operations for managing shops.

## 🚀 Features

- **Complete CRUD Operations**: Create, Read, Update, Delete shops
- **MySQL Database Integration**: Connects to MySQL database with JPA/Hibernate
- **RESTful API Design**: Following REST principles with proper HTTP status codes
- **Data Validation**: Input validation using Bean Validation annotations
- **Error Handling**: Comprehensive error handling with meaningful responses
- **Search Functionality**: Search shops by name or address
- **Swagger/OpenAPI Documentation**: Interactive API documentation with Swagger UI
- **Logging**: Detailed logging for debugging and monitoring
- **Transaction Management**: Proper transaction handling with Spring @Transactional

## 🛠️ Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Hibernate ORM**
- **MySQL 8.0**
- **Swagger/OpenAPI 3** (API Documentation)
- **Maven** (Build tool)
- **Tomcat** (Embedded web server)

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ running and accessible
- MySQL database named `shopdb`
- MySQL user with appropriate permissions

## 🗄️ Database Configuration

The application connects to MySQL with the following configuration:

```properties
spring.datasource.url=jdbc:mysql://10.10.10.124:3306/shopdb
spring.datasource.username=anup_mysql
spring.datasource.password=anup123
```

### Database Schema

The application automatically creates the `shops` table with the following structure:

```sql
CREATE TABLE shops (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    created_date DATETIME(6) NOT NULL,
    updated_date DATETIME(6),
    PRIMARY KEY (id)
);
```

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd anup-spring-boot
```

### 2. Ensure MySQL is Running
```bash
# Check if MySQL is running
mysqladmin -h 127.0.0.1 -P 3306 -u anup_mysql -p'anup123' ping
```

### 3. Build the Application
```bash
mvn clean compile
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 5. Insert Sample Data (Optional)
```bash
mysql -h 127.0.0.1 -P 3306 -u anup_mysql -p'anup123' shopdb < src/main/resources/data.sql
```

## 📚 API Documentation

### Interactive Documentation (Swagger UI)
🌐 **Access the interactive API documentation at:**
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI Spec**: [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

The Swagger UI provides:
- **Interactive Testing**: Try out API endpoints directly from the browser
- **Request/Response Examples**: See detailed examples for all endpoints
- **Schema Documentation**: Complete data model documentation
- **Authentication Testing**: Test with different authentication scenarios
- **Export Options**: Download OpenAPI specification in JSON format

### Base URL
```
http://localhost:8080/api/shops
```

### Endpoints

#### 1. Get All Shops
- **URL**: `GET /api/shops`
- **Description**: Retrieve all shops from the database
- **Response**: JSON array of shop objects

**Example Request:**
```bash
curl -X GET "http://localhost:8080/api/shops" -H "Accept: application/json"
```

**Example Response:**
```json
{
  "success": true,
  "message": "Shops retrieved successfully",
  "data": [
    {
      "id": 1,
      "name": "Tech World Electronics",
      "address": "123 Main Street, Downtown, City",
      "phone": "+1-555-0101",
      "email": "info@techworld.com",
      "createdDate": "2025-06-13T21:46:19",
      "updatedDate": "2025-06-13T21:46:19"
    }
  ],
  "count": 10
}
```

#### 2. Get Shop by ID
- **URL**: `GET /api/shops/{id}`
- **Description**: Retrieve a specific shop by its ID
- **Parameters**: `id` (path parameter) - Shop ID

**Example Request:**
```bash
curl -X GET "http://localhost:8080/api/shops/1" -H "Accept: application/json"
```

#### 3. Create New Shop
- **URL**: `POST /api/shops`
- **Description**: Create a new shop
- **Request Body**: JSON object with shop details

**Example Request:**
```bash
curl -X POST "http://localhost:8080/api/shops" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "name": "New Shop",
    "address": "123 New Street",
    "phone": "+1-555-0000",
    "email": "new@shop.com"
  }'
```

#### 4. Update Shop
- **URL**: `PUT /api/shops/{id}`
- **Description**: Update an existing shop
- **Parameters**: `id` (path parameter) - Shop ID
- **Request Body**: JSON object with updated shop details

#### 5. Delete Shop
- **URL**: `DELETE /api/shops/{id}`
- **Description**: Delete a shop by ID
- **Parameters**: `id` (path parameter) - Shop ID

#### 6. Search Shops
- **URL**: `GET /api/shops/search?q={searchTerm}`
- **Description**: Search shops by name or address
- **Parameters**: `q` (query parameter) - Search term

**Example Request:**
```bash
curl -X GET "http://localhost:8080/api/shops/search?q=tech" -H "Accept: application/json"
```

#### 7. Get Shop Count
- **URL**: `GET /api/shops/count`
- **Description**: Get total number of shops

**Example Request:**
```bash
curl -X GET "http://localhost:8080/api/shops/count" -H "Accept: application/json"
```

## 🧪 Testing

### Automated Testing Scripts

#### API Endpoints Testing
Run the provided test script to test all endpoints:

```bash
./test-api.sh
```

#### Swagger Documentation Testing
Test the Swagger/OpenAPI documentation:

```bash
./test-swagger.sh
```

### Manual Testing
You can test individual endpoints using:
- **Swagger UI**: Interactive testing at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **curl commands**: Command-line testing
- **Postman**: Import the OpenAPI spec from [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

### Sample Data
The application includes 10 sample shops:
1. Tech World Electronics
2. Green Garden Nursery
3. Fashion Forward Boutique
4. Books & Beyond
5. Fresh Market Grocery
6. Auto Parts Plus
7. Cozy Coffee Corner
8. Sports Zone
9. Home & Garden Center
10. Digital Solutions Store

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/anup/shopapi/
│   │   ├── ShopApiApplication.java      # Main application class
│   │   ├── config/
│   │   │   └── OpenApiConfig.java       # Swagger/OpenAPI configuration
│   │   ├── controller/
│   │   │   └── ShopController.java      # REST controller with Swagger annotations
│   │   ├── entity/
│   │   │   └── Shop.java                # JPA entity with Swagger schemas
│   │   ├── repository/
│   │   │   └── ShopRepository.java      # JPA repository
│   │   └── service/
│   │       └── ShopService.java         # Business logic service
│   └── resources/
│       ├── application.properties       # Configuration (includes Swagger settings)
│       └── data.sql                     # Sample data
├── test/
│   └── java/com/anup/shopapi/          # Test classes
├── test-api.sh                         # API testing script
└── test-swagger.sh                     # Swagger documentation testing script
```

## ⚙️ Configuration

### Application Properties
Key configuration settings in `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://10.10.10.124:3306/shopdb
spring.datasource.username=anup_mysql
spring.datasource.password=anup123

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Configuration
server.port=8080

# Swagger/OpenAPI Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## 🔧 Troubleshooting

### Common Issues

1. **Database Connection Failed**
   - Ensure MySQL is running: `mysqladmin ping`
   - Check database credentials and host
   - Verify database `shopdb` exists

2. **Port Already in Use**
   - Change server port in `application.properties`
   - Kill process using port 8080: `lsof -ti:8080 | xargs kill -9`

3. **Table Not Found**
   - Ensure `spring.jpa.hibernate.ddl-auto=update` is set
   - Check database permissions

### Logs
Application logs are available in the console output when running with `mvn spring-boot:run`

## 🚀 Deployment

### Building JAR
```bash
mvn clean package
java -jar target/shop-api-0.0.1-SNAPSHOT.jar
```

### Docker (Optional)
Create a Dockerfile for containerized deployment.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 📞 Support

For support or questions, please contact the development team.

---

**✅ Status: Production Ready**

The application has been tested and is ready for production use with proper error handling, validation, and database integration.