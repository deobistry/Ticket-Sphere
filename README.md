# 🎫 TicketSphere — Full Stack Travel Booking Platform

TicketSphere is a full-stack travel booking platform that allows users to search, book, and manage **transportation and accommodation services** through a modern web application.

The platform also includes a secure **administrator dashboard** for managing transportation, accommodations, users, roles, and bookings.

Built using **React, Spring Boot, MySQL, JPA/Hibernate, JWT, Tailwind CSS, and Axios**, TicketSphere follows a layered and scalable architecture designed for future production enhancements.

---

## 📌 Features

### 👤 User Features

* User registration and login
* JWT-based authentication
* Role-based authorization
* User profile management
* Search transportation
* Search accommodations
* Book transportation
* Book accommodation
* Seat availability management
* Room availability management
* Booking cancellation
* Booking history
* Simulated payment flow
* Protected user routes

### 🚌 Transportation

Supports multiple transportation types:

* Bus
* Train
* Flight
* Other transportation options

Users can search transportation using:

* Source
* Destination
* Transport type
* Price range
* Available seats

Transportation information includes:

* Transport number
* Operator
* Source
* Destination
* Departure time
* Arrival time
* Duration
* Total seats
* Available seats
* Price

### 🏨 Accommodation

Users can search and book accommodations such as:

* Hotels
* Resorts
* Other stays

Accommodation search supports:

* City
* Price range
* Available rooms

Accommodation information includes:

* Name
* City
* Address
* Total rooms
* Available rooms
* Price per night

### 📅 Booking Management

#### Transportation Booking

Users can:

* Select transportation
* Choose number of seats
* View calculated total amount
* Confirm booking
* Cancel booking

Booking amount:

```text
Transport Price × Seats Booked
```

#### Accommodation Booking

Users can:

* Select accommodation
* Choose check-in date
* Choose check-out date
* Select rooms
* View calculated total
* Confirm booking
* Cancel booking

Booking amount:

```text
Price Per Night × Number of Nights × Rooms Booked
```

### 💳 Payment

TicketSphere currently provides a simulated payment flow.

The payment page displays:

* Booking type
* Total amount
* Card input fields
* Payment processing state
* Payment success state

Future payment integrations can include:

* Stripe
* Razorpay
* PayPal

---

# 🛠️ Technology Stack

## Frontend

| Technology   | Purpose             |
| ------------ | ------------------- |
| React        | Frontend framework  |
| React Router | Client-side routing |
| Tailwind CSS | Styling             |
| Axios        | API communication   |
| Lucide React | Icons               |

## Backend

| Technology      | Purpose                          |
| --------------- | -------------------------------- |
| Java            | Backend language                 |
| Spring Boot     | REST API                         |
| Spring Data JPA | Data access                      |
| Hibernate       | ORM                              |
| Spring Security | Authentication and authorization |
| JWT             | Token-based authentication       |
| MySQL           | Database                         |
| Maven           | Dependency management            |

---

# 🏗️ System Architecture

TicketSphere follows a layered backend architecture:

```text
                    React Frontend
                          |
                          |
                        Axios
                          |
                          ↓
                Spring Boot REST API
                          |
                          ↓
                     Controller
                          |
                          ↓
                       Service
                          |
                          ↓
                     Repository
                          |
                          ↓
                  JPA / Hibernate
                          |
                          ↓
                     MySQL Database
```

### Backend Responsibilities

**Controller**

* Handles HTTP requests
* Validates request flow
* Returns API responses

**Service**

* Contains business logic
* Performs validations
* Handles booking calculations
* Manages transactions

**Repository**

* Communicates with the database
* Provides CRUD operations

**Entity**

* Represents database tables

**DTO**

* Transfers data between frontend and backend
* Prevents sensitive entity information from being exposed

---

# 📂 Project Structure

## Backend

```text
ticketing-backend
└── src
    └── main
        └── java
            └── com
                └── ticketing
                    ├── controller
                    │   ├── AuthController
                    │   ├── UserController
                    │   ├── TransportationController
                    │   ├── AccommodationController
                    │   ├── TransportBookingController
                    │   ├── AccommodationBookingController
                    │   └── AdminController
                    │
                    ├── controller
                    │   └── admin
                    │       └── AdminUserController
                    │
                    ├── dto
                    │   ├── request
                    │   ├── response
                    │   └── admin
                    │
                    ├── entity
                    │   ├── User
                    │   ├── Transportation
                    │   ├── Accommodation
                    │   ├── TransportBooking
                    │   └── AccommodationBooking
                    │
                    ├── enums
                    │   ├── UserRole
                    │   ├── BookingStatus
                    │   └── TransportType
                    │
                    ├── exception
                    │
                    ├── repository
                    │   ├── UserRepository
                    │   ├── TransportationRepository
                    │   ├── AccommodationRepository
                    │   ├── TransportBookingRepository
                    │   └── AccommodationBookingRepository
                    │
                    ├── security
                    │   ├── JWTFilter
                    │   ├── JWTService
                    │   └── UserContext
                    │
                    └── service
                        ├── AuthService
                        ├── UserService
                        ├── TransportationService
                        ├── AccommodationService
                        ├── TransportBookingService
                        ├── AccommodationBookingService
                        ├── admin
                        │   └── AdminUserService
                        └── AdminService
```

## Frontend

```text
ticketing-frontend
└── src
    ├── api
    │   └── axios.js
    │
    ├── components
    │   ├── Navbar.jsx
    │   ├── Footer.jsx
    │   └── Cards
    │
    ├── context
    │   └── AuthContext.jsx
    │
    ├── layouts
    │   └── MainLayout.jsx
    │
    ├── routes
    │   ├── AppRoutes.jsx
    │   └── AdminRoute.jsx
    │
    └── pages
        ├── Home.jsx
        ├── Login.jsx
        ├── Signup.jsx
        ├── Transportation.jsx
        ├── Accommodation.jsx
        ├── TransportBooking.jsx
        ├── AccommodationBooking.jsx
        ├── Payment.jsx
        ├── MyBookings.jsx
        ├── AdminDashboard.jsx
        │
        └── admin
            ├── ManageTransportation.jsx
            ├── AddTransportation.jsx
            ├── EditTransportation.jsx
            ├── ManageAccommodation.jsx
            ├── AddAccommodation.jsx
            └── ManageUsers.jsx
```

---

# 🗄️ Database Design

TicketSphere uses MySQL with the following primary tables:

```text
users
transportation
transport_booking
accommodation
accommodation_booking
```

## Entity Relationship Diagram

```text
                         ┌───────────────┐
                         │     User      │
                         ├───────────────┤
                         │ id            │
                         │ name          │
                         │ email         │
                         │ password      │
                         │ role          │
                         └───────┬───────┘
                                 │
                  ┌──────────────┴──────────────┐
                  │                             │
                  ▼                             ▼
       ┌─────────────────────┐       ┌────────────────────────┐
       │ TransportBooking    │       │ AccommodationBooking   │
       ├─────────────────────┤       ├────────────────────────┤
       │ id                  │       │ id                     │
       │ user_id             │       │ user_id                │
       │ transportation_id   │       │ accommodation_id       │
       │ seatsBooked         │       │ roomsBooked            │
       │ totalAmount         │       │ checkInDate            │
       │ status              │       │ checkOutDate           │
       │ bookingDate         │       │ totalAmount             │
       └──────────┬──────────┘       │ status                  │
                  │                  │ bookingDate             │
                  ▼                  └──────────┬─────────────┘
       ┌─────────────────────┐                 │
       │ Transportation      │                 ▼
       ├─────────────────────┤       ┌────────────────────────┐
       │ id                  │       │ Accommodation          │
       │ transportNumber     │       ├────────────────────────┤
       │ type                │       │ id                     │
       │ operatorName        │       │ name                   │
       │ source              │       │ city                   │
       │ destination         │       │ address                │
       │ departureTime       │       │ totalRooms             │
       │ arrivalTime         │       │ availableRooms         │
       │ duration            │       │ pricePerNight          │
       │ totalSeats          │       └────────────────────────┘
       │ availableSeats      │
       │ price               │
       └─────────────────────┘
```

### Relationships

```text
User (1) ───────── (*) TransportBooking

User (1) ───────── (*) AccommodationBooking

Transportation (1) ───────── (*) TransportBooking

Accommodation (1) ───────── (*) AccommodationBooking
```

---

# 🔐 Authentication & Authorization

TicketSphere uses JWT-based authentication.

## Authentication Flow

```text
User Login
    |
    ↓
Backend verifies credentials
    |
    ↓
JWT generated
    |
    ↓
Frontend stores authentication data
    |
    ↓
Axios attaches JWT
    |
    ↓
Spring Security validates JWT
    |
    ↓
Protected API accessed
```

The frontend Axios configuration automatically sends:

```http
Authorization: Bearer <JWT_TOKEN>
```

### Roles

```text
USER
ADMIN
```

Admin-only functionality is protected through both frontend route protection and backend authorization.

---

# 👤 User API

Base URL:

```text
/users
```

| Method | Endpoint         | Description                |
| ------ | ---------------- | -------------------------- |
| GET    | `/users/profile` | Get logged-in user profile |
| PUT    | `/users/profile` | Update user profile        |

Password information is never returned through the profile response.

---

# 🔑 Authentication API

| Method | Endpoint       | Description       |
| ------ | -------------- | ----------------- |
| POST   | `/auth/signup` | Register user     |
| POST   | `/auth/login`  | Authenticate user |

### Signup Request

```json
{
  "name": "John Doe",
  "email": "john@gmail.com",
  "password": "password"
}
```

### Login Response

```json
{
  "token": "JWT_TOKEN",
  "role": "USER"
}
```

---

# 🚌 Transportation API

Base URL:

```text
/transportation
```

| Method | Endpoint                 | Access        | Description              |
| ------ | ------------------------ | ------------- | ------------------------ |
| POST   | `/transportation`        | ADMIN         | Create transportation    |
| GET    | `/transportation`        | Authenticated | Get all transportation   |
| GET    | `/transportation/{id}`   | Authenticated | Get transportation by ID |
| GET    | `/transportation/search` | Authenticated | Search transportation    |
| PUT    | `/transportation/{id}`   | ADMIN         | Update transportation    |
| DELETE | `/transportation/{id}`   | ADMIN         | Delete transportation    |

### Example Request

```json
{
  "transportNumber": "AI101",
  "type": "FLIGHT",
  "operatorName": "Air India",
  "source": "Delhi",
  "destination": "Mumbai",
  "departureTime": "2026-08-01T10:00:00",
  "arrivalTime": "2026-08-01T12:00:00",
  "duration": "2 Hours",
  "totalSeats": 200,
  "availableSeats": 200,
  "price": 5000
}
```

### Search Parameters

```text
source
destination
type
minPrice
maxPrice
minSeats
```

Example:

```text
GET /transportation/search?source=Delhi&destination=Mumbai&type=FLIGHT
```

---

# 🏨 Accommodation API

Base URL:

```text
/accommodation
```

| Method | Endpoint                | Access        | Description             |
| ------ | ----------------------- | ------------- | ----------------------- |
| POST   | `/accommodation`        | ADMIN         | Create accommodation    |
| GET    | `/accommodation`        | Authenticated | Get accommodations      |
| GET    | `/accommodation/{id}`   | Authenticated | Get accommodation by ID |
| GET    | `/accommodation/search` | Authenticated | Search accommodations   |
| DELETE | `/accommodation/{id}`   | ADMIN         | Delete accommodation    |

### Search Parameters

```text
city
minPrice
maxPrice
rooms
```

---

# 🎟️ Transport Booking API

Base URL:

```text
/transport-bookings
```

| Method | Endpoint                   | Description         |
| ------ | -------------------------- | ------------------- |
| POST   | `/transport-bookings`      | Create booking      |
| GET    | `/transport-bookings/my`   | Get user's bookings |
| GET    | `/transport-bookings/{id}` | Get booking         |
| DELETE | `/transport-bookings/{id}` | Cancel booking      |

### Create Booking

```json
{
  "transportationId": 5,
  "seatsBooked": 2
}
```

### Booking Calculation

```text
Total Amount = Transportation Price × Seats Booked
```

Before creating a booking, the backend verifies:

* Seats booked > 0
* Requested seats are available
* Transportation exists
* User is authenticated

When a booking is cancelled, reserved seats are restored.

---

# 🏨 Accommodation Booking API

Base URL:

```text
/accommodation-bookings
```

| Method | Endpoint                       | Description         |
| ------ | ------------------------------ | ------------------- |
| POST   | `/accommodation-bookings`      | Create booking      |
| GET    | `/accommodation-bookings/my`   | Get user's bookings |
| GET    | `/accommodation-bookings/{id}` | Get booking         |
| DELETE | `/accommodation-bookings/{id}` | Cancel booking      |

### Create Booking

```json
{
  "accommodationId": 4,
  "roomsBooked": 2,
  "checkInDate": "2026-08-10",
  "checkOutDate": "2026-08-15"
}
```

### Booking Calculation

```text
Total Amount =
Price Per Night × Number Of Nights × Rooms Booked
```

The backend validates:

* Check-in date is before check-out date
* Minimum one night
* Number of rooms is valid
* Accommodation exists
* Required rooms are available
* User owns the booking during cancellation

---

# 👑 Admin API

Base URL:

```text
/admin
```

## Dashboard

```http
GET /admin/dashboard
```

Returns:

```text
Total Users
Total Transport Bookings
Total Accommodation Bookings
Total Revenue
Confirmed Bookings
Cancelled Bookings
```

## User Management

Base URL:

```text
/admin/users
```

| Method | Endpoint                 | Description      |
| ------ | ------------------------ | ---------------- |
| GET    | `/admin/users`           | Get all users    |
| GET    | `/admin/users/{id}`      | Get user         |
| DELETE | `/admin/users/{id}`      | Delete user      |
| PUT    | `/admin/users/{id}/role` | Update user role |

Roles:

```text
USER
ADMIN
```

## Admin Transportation

Admin functionality includes:

* Add transportation
* View transportation
* Edit transportation
* Delete transportation

Frontend routes:

```text
/admin/transportation
/admin/transportation/manage
```

## Admin Accommodation

Admin functionality includes:

* Add accommodation
* View accommodation
* Delete accommodation

Frontend routes:

```text
/admin/accommodation
/admin/accommodation/manage
```

## Admin Bookings

Planned/extended endpoints:

```http
GET /admin/bookings
GET /admin/bookings/transport
GET /admin/bookings/accommodation
```

These endpoints provide administrators with a combined view of customer booking activity.

---

# 🖥️ Frontend Routes

## Public/User Routes

| Route                    | Page                  |
| ------------------------ | --------------------- |
| `/`                      | Home                  |
| `/login`                 | Login                 |
| `/signup`                | Signup                |
| `/transportation`        | Transportation Search |
| `/accommodation`         | Accommodation Search  |
| `/transport-booking`     | Transport Booking     |
| `/accommodation-booking` | Accommodation Booking |
| `/payment`               | Payment               |
| `/bookings`              | My Bookings           |

## Admin Routes

| Route                          | Page                  |
| ------------------------------ | --------------------- |
| `/admin`                       | Admin Dashboard       |
| `/admin/users`                 | Manage Users          |
| `/admin/transportation`        | Add Transportation    |
| `/admin/transportation/manage` | Manage Transportation |
| `/admin/accommodation`         | Add Accommodation     |
| `/admin/accommodation/manage`  | Manage Accommodation  |
| `/admin/bookings`              | Manage Bookings       |

---

# 🔄 Booking Flow

## Transportation

```text
User
  |
  ↓
Transportation Search
  |
  ↓
Select Transportation
  |
  ↓
Transport Booking
  |
  ↓
Select Seats
  |
  ↓
POST /transport-bookings
  |
  ↓
Booking Created
  |
  ↓
Payment
  |
  ↓
Payment Success
  |
  ↓
My Bookings
```

## Accommodation

```text
User
  |
  ↓
Accommodation Search
  |
  ↓
Select Accommodation
  |
  ↓
Accommodation Booking
  |
  ↓
Select Dates & Rooms
  |
  ↓
POST /accommodation-bookings
  |
  ↓
Booking Created
  |
  ↓
Payment
  |
  ↓
Payment Success
  |
  ↓
My Bookings
```

---

# 💳 Payment Flow

The current implementation uses a simulated payment process.

```text
Booking
   |
   ↓
Payment Page
   |
   ↓
Enter Payment Details
   |
   ↓
Processing
   |
   ↓
Payment Successful
   |
   ↓
My Bookings
```

The amount is transferred to the payment page through React Router state.

Future versions can introduce a dedicated `Payment` entity:

```text
Payment
├── id
├── bookingId
├── amount
├── paymentStatus
├── transactionId
└── paymentDate
```

Possible payment states:

```text
PENDING
SUCCESS
FAILED
REFUNDED
```

---

# 🛡️ Validation & Security

The application includes several important validations.

### Booking Validation

* Prevents zero or negative seat bookings
* Prevents booking more seats than available
* Prevents invalid room counts
* Prevents invalid accommodation dates
* Prevents users from accessing other users' bookings
* Restores seats after transport cancellation
* Restores rooms after accommodation cancellation

### Recommended Production Security

For production deployment, the following improvements are recommended:

* BCrypt password hashing
* HttpOnly cookies for JWT
* Backend role enforcement
* `@PreAuthorize("hasRole('ADMIN')")`
* HTTPS
* Strict CORS configuration
* Rate limiting
* Secure HTTP headers
* Request validation
* Environment-based secrets

---

# ⚙️ Local Development Setup

## Prerequisites

Install the following:

* Java 17+
* Maven
* Node.js 18+
* npm
* MySQL
* Git

---

# 🚀 Backend Setup

Clone the repository:

```bash
git clone <YOUR_REPOSITORY_URL>
cd ticketing-backend
```

Create a MySQL database:

```sql
CREATE DATABASE ticket_sphere;
```

Configure the backend database and JWT settings in your application configuration.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ticket_sphere
spring.datasource.username=root
spring.datasource.password=YOUR_DATABASE_PASSWORD

spring.jpa.hibernate.ddl-auto=update

server.port=8787

jwt.secret=YOUR_JWT_SECRET
```

> Do not commit real passwords or JWT secrets to Git.

Start the backend:

```bash
mvn spring-boot:run
```

Backend API:

```text
http://localhost:8787
```

---

# 💻 Frontend Setup

Navigate to the frontend:

```bash
cd ticketing-frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The frontend will normally be available at:

```text
http://localhost:5173
```

---

# 🔗 API Configuration

Axios is configured in:

```text
src/api/axios.js
```

Development API base URL:

```text
http://localhost:8787
```

Authentication requests automatically include:

```http
Authorization: Bearer <token>
```

For production, replace the development API URL with the deployed backend URL.

---

# 🧪 Testing Checklist

Before deployment, verify the following.

### Authentication

* [ ] User signup works
* [ ] User login works
* [ ] JWT is generated
* [ ] Protected routes reject unauthenticated users
* [ ] Admin routes reject normal users

### Transportation

* [ ] Transportation can be added
* [ ] Transportation can be searched
* [ ] Transportation can be edited
* [ ] Transportation can be deleted
* [ ] Seat availability is updated correctly

### Accommodation

* [ ] Accommodation can be added
* [ ] Accommodation can be searched
* [ ] Accommodation can be deleted
* [ ] Room availability is updated correctly
* [ ] Invalid dates are rejected

### Bookings

* [ ] Transport booking works
* [ ] Accommodation booking works
* [ ] Booking totals are calculated correctly
* [ ] Users can view their bookings
* [ ] Users cannot access another user's bookings
* [ ] Cancellation works
* [ ] Inventory is restored after cancellation

### Admin

* [ ] Dashboard loads correctly
* [ ] Users can be viewed
* [ ] User roles can be updated
* [ ] Users can be deleted
* [ ] Transportation can be managed
* [ ] Accommodation can be managed
* [ ] Bookings can be viewed

### Payment

* [ ] Amount is transferred correctly
* [ ] Payment page loads
* [ ] Processing state works
* [ ] Success state works

---

# 📈 Production Improvements

## 1. Password Encryption

Passwords should be hashed using BCrypt:

```text
Plain Password
      |
      ↓
BCrypt
      |
      ↓
Hashed Password
      |
      ↓
Database
```

Never store plain-text passwords.

---

## 2. Global Exception Handling

Recommended structure:

```text
exception
├── GlobalExceptionHandler
├── ResourceNotFoundException
├── UnauthorizedException
└── ValidationException
```

Example API response:

```json
{
  "timestamp": "2026-07-30T10:30:00",
  "status": 404,
  "message": "Transportation not found"
}
```

---

## 3. DTO Validation

Use Bean Validation annotations such as:

```java
@NotBlank
private String name;
```

Other useful annotations include:

```java
@NotNull
@Email
@Size
@Min
@Max
@Positive
```

---

## 4. Pagination

Instead of returning thousands of records:

```http
GET /transportation
```

Use pagination:

```http
GET /transportation?page=0&size=20
```

This becomes important as the database grows.

---

## 5. Database Migration

For production environments, consider replacing automatic schema updates with **Flyway**.

Example:

```text
db
└── migration
    ├── V1__create_users.sql
    ├── V2__create_transportation.sql
    ├── V3__create_bookings.sql
    └── V4__add_payment.sql
```

---

# 🌍 Deployment

## Backend Hosting Options

Possible platforms include:

* AWS
* Azure
* Google Cloud
* Railway
* Render

## Database Options

* MySQL
* PostgreSQL
* AWS RDS

## Frontend Hosting Options

* Vercel
* Netlify
* AWS S3

---

# 🔐 Production Environment Variables

Recommended environment variables:

```text
DATABASE_URL
DATABASE_USERNAME
DATABASE_PASSWORD
JWT_SECRET
SERVER_PORT
```

Never commit:

```text
Database passwords
JWT secrets
API keys
Payment gateway secrets
```

to source control.

---

# 📱 Future Roadmap

## Phase 1 — Core Platform

* [x] User Authentication
* [x] JWT Authentication
* [x] User Profile
* [x] Transportation Search
* [x] Accommodation Search
* [x] Transport Booking
* [x] Accommodation Booking
* [x] Payment UI
* [x] Booking History
* [x] Admin Dashboard
* [x] Admin User Management
* [x] Admin Transportation Management
* [x] Admin Accommodation Management

## Phase 2 — Real Payments

* [ ] Payment Entity
* [ ] Stripe integration
* [ ] Razorpay integration
* [ ] PayPal integration
* [ ] Payment status tracking
* [ ] Refund support
* [ ] Transaction IDs

## Phase 3 — Admin Analytics

* [ ] Revenue analytics
* [ ] Monthly revenue
* [ ] Booking statistics
* [ ] Popular routes
* [ ] Popular accommodations
* [ ] Active user statistics
* [ ] Revenue charts
* [ ] Booking charts
* [ ] User growth charts

## Phase 4 — User Experience

* [ ] Digital tickets
* [ ] PDF ticket generation
* [ ] QR code tickets
* [ ] Email notifications
* [ ] Booking confirmation emails
* [ ] Payment confirmation emails
* [ ] Cancellation notifications
* [ ] Reviews and ratings

## Phase 5 — Advanced Search

### Transportation

* [ ] Transport type
* [ ] Price range
* [ ] Departure time
* [ ] Available seats
* [ ] Operator

### Accommodation

* [ ] City
* [ ] Price range
* [ ] Rating
* [ ] Room availability
* [ ] Amenities

## Phase 6 — Mobile Application

Future mobile application:

```text
TicketSphere Mobile
        |
        ├── React Native
        │
        └── Flutter
```

Potential mobile features:

* Search
* Booking
* Payment
* Booking history
* Digital tickets
* QR codes
* Push notifications

---

# 📊 Current Project Status

| Module                   | Status           |
| ------------------------ | ---------------- |
| User Registration        | ✅ Completed      |
| User Login               | ✅ Completed      |
| JWT Authentication       | ✅ Completed      |
| Role-Based Access        | ✅ Completed      |
| User Profile             | ✅ Completed      |
| Transportation Search    | ✅ Completed      |
| Transportation Booking   | ✅ Completed      |
| Seat Availability        | ✅ Completed      |
| Accommodation Search     | ✅ Completed      |
| Accommodation Booking    | ✅ Completed      |
| Room Availability        | ✅ Completed      |
| Booking Cancellation     | ✅ Completed      |
| Booking History          | ✅ Completed      |
| Payment UI               | ✅ Completed      |
| Admin Dashboard          | ✅ Completed      |
| Admin User Management    | ✅ Completed      |
| Admin Transportation     | ✅ Completed      |
| Admin Accommodation      | ✅ Completed      |
| Admin Booking Management | 🚧 Next Priority |
| Real Payment Gateway     | 🔜 Planned       |
| Analytics                | 🔜 Planned       |
| Digital Tickets          | 🔜 Planned       |
| Notifications            | 🔜 Planned       |
| Reviews                  | 🔜 Planned       |
| Mobile App               | 🔜 Planned       |

---

# 🐛 Known Development Notes

### Transportation Update

The transportation edit functionality requires:

```http
PUT /transportation/{id}
```

The backend should provide:

```java
@PutMapping("/{id}")
public ResponseEntity<String> updateTransportation(
        @PathVariable Long id,
        @RequestBody TransportationRequest request
) {
    transportationService.updateTransportation(id, request);

    return ResponseEntity.ok(
        "Transportation updated successfully"
    );
}
```

The service should:

1. Find the existing transportation
2. Update its fields
3. Save the entity
4. Return the updated result

---

# 🤝 Contributing

Contributions are welcome.

### Development Workflow

```bash
git checkout -b feature/your-feature
```

Make your changes, test them, then commit:

```bash
git add .
git commit -m "Add your feature"
```

Push your branch:

```bash
git push origin feature/your-feature
```

Then open a Pull Request.

---

# 📄 License

This project is currently intended for educational and development purposes.

Add an appropriate open-source license such as MIT if you plan to distribute the project publicly.

---

# 👨‍💻 Project Summary

TicketSphere provides a complete foundation for a modern travel booking system.

```text
                    TicketSphere
                         |
          ┌──────────────┴──────────────┐
          │                             │
          ▼                             ▼
    User Platform                 Admin Platform
          │                             │
    ┌─────┼─────┐              ┌────────┼────────┐
    │     │     │              │        │        │
    ▼     ▼     ▼              ▼        ▼        ▼
Transport Hotel Booking     Dashboard Users  Management
    │     │     │              │        │        │
    └─────┴─────┘              └────────┴────────┘
          │
          ▼
       Payment
          │
          ▼
      My Bookings
```

The architecture is designed to support future expansion into:

* Real payment processing
* Digital tickets
* QR-code validation
* Advanced analytics
* Email notifications
* Reviews and ratings
* Mobile applications
* Enterprise travel management

---

## ⭐ TicketSphere

**A scalable full-stack travel booking platform built with React, Spring Boot, and MySQL.**
