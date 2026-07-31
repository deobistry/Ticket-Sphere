# 🎫 TicketSphere

> A full-stack travel booking platform built with **Spring Boot**, **React**, **JWT Authentication**, **MySQL**, and **Tailwind CSS**.

TicketSphere allows users to search and book transportation and accommodations while providing administrators with a complete management dashboard.

---

# 📸 Features

## 👤 User Features

- User Registration
- User Login
- JWT Authentication
- User Profile Management
- Search Transportation
- Search Accommodation
- Book Transportation
- Book Accommodation
- Payment Page (Simulation)
- View Booking History
- Cancel Bookings

---

## 🛠 Admin Features

- Dashboard Statistics
- Manage Transportation
- Add Transportation
- Edit Transportation
- Delete Transportation
- Manage Accommodation
- Add Accommodation
- Delete Accommodation
- Manage Users
- Update User Roles
- Delete Users
- View All Bookings

---

# 🏗 Tech Stack

## Frontend

- React
- React Router DOM
- Axios
- Tailwind CSS
- Lucide React

## Backend

- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL

---

# 📂 Project Structure

```
TicketSphere

├── backend
│
│   ├── controller
│   ├── controller/admin
│   ├── service
│   ├── service/admin
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── security
│   ├── enums
│   └── exception
│
└── frontend
    │
    ├── api
    ├── components
    ├── context
    ├── layouts
    ├── pages
    ├── pages/admin
    ├── routes
    └── assets
```

---

# 🗄 Database Design

```
User
│
├───────────────┐
│               │
▼               ▼

TransportBooking      AccommodationBooking
│                     │
│                     │
▼                     ▼

Transportation      Accommodation
```

### Entities

- User
- Transportation
- Accommodation
- TransportBooking
- AccommodationBooking

---

# 🔐 Authentication

The project uses **JWT Authentication**.

### Login Flow

```
User Login
      │
      ▼
Spring Security
      │
      ▼
JWT Generated
      │
      ▼
Stored in Local Storage
      │
      ▼
Axios sends Bearer Token
      │
      ▼
Protected Backend APIs
```

---

# 🚍 Transportation Module

Users can

- Search by source
- Search by destination
- Filter by transportation type
- View available seats
- View price
- Book transportation

Supported transportation types:

- ✈ Flight
- 🚆 Train
- 🚌 Bus

---

# 🏨 Accommodation Module

Users can

- Search hotels
- Select rooms
- Choose check-in/check-out dates
- Book accommodation

---

# 🎟 Booking System

## Transportation Booking

Stores

- User
- Transportation
- Seats Booked
- Total Amount
- Booking Status
- Booking Date

---

## Accommodation Booking

Stores

- User
- Accommodation
- Rooms Booked
- Check-in Date
- Check-out Date
- Total Amount
- Booking Status

---

# 💳 Payment

Current implementation

- Simulated payment page
- Displays booking amount
- Redirects after successful payment

Future integration

- Stripe
- Razorpay
- PayPal

---

# 📊 Admin Dashboard

Dashboard displays

- Total Users
- Total Transport Bookings
- Total Accommodation Bookings
- Total Revenue
- Confirmed Bookings
- Cancelled Bookings

---

# 👨‍💼 Admin Management

## Transportation

- View
- Add
- Edit
- Delete

---

## Accommodation

- View
- Add
- Delete

---

## Users

- View all users
- Update user role
- Delete users

---

## Bookings

Admin can view

### Transportation Bookings

- Booking ID
- Customer
- Route
- Seats
- Amount
- Status
- Booking Date

### Accommodation Bookings

- Booking ID
- Customer
- Hotel
- Rooms
- Check-in
- Check-out
- Amount
- Status

---

# 🌐 REST API Overview

## Authentication

```
POST /auth/signup
POST /auth/login
```

---

## Users

```
GET /users/profile
PUT /users/profile
```

---

## Transportation

```
GET    /transportation
GET    /transportation/{id}
GET    /transportation/search
POST   /transportation
PUT    /transportation/{id}
DELETE /transportation/{id}
```

---

## Accommodation

```
GET    /accommodation
GET    /accommodation/{id}
GET    /accommodation/search
POST   /accommodation
DELETE /accommodation/{id}
```

---

## Transport Booking

```
POST   /transport-bookings
GET    /transport-bookings/my
GET    /transport-bookings/{id}
DELETE /transport-bookings/{id}
```

---

## Accommodation Booking

```
POST   /accommodation-bookings
GET    /accommodation-bookings/my
GET    /accommodation-bookings/{id}
DELETE /accommodation-bookings/{id}
```

---

## Admin

```
GET /admin/dashboard

GET /admin/users
GET /admin/users/{id}
PUT /admin/users/{id}/role
DELETE /admin/users/{id}

GET /admin/bookings
```

---

# 🚀 Getting Started

## Clone Repository

```bash
git clone https://github.com/your-username/TicketSphere.git
```

---

## Backend

```bash
cd backend

mvn clean install

mvn spring-boot:run
```

Runs on

```
http://localhost:8787
```

---

## Frontend

```bash
cd frontend

npm install

npm run dev
```

Runs on

```
http://localhost:5173
```

---

# ⚙ Environment Variables

## Backend

Create an `.env` or configure your application properties:

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=

jwt.secret=
```

---

## Frontend

```
VITE_API_URL=http://localhost:8787
```

---

# 🔮 Future Improvements

- ✅ Real Payment Gateway
- ✅ Payment Status Tracking
- ✅ Email Notifications
- ✅ Booking Receipts
- ✅ QR Code Tickets
- ✅ PDF Ticket Generation
- ✅ Booking Analytics
- ✅ Revenue Dashboard
- ✅ Reviews & Ratings
- ✅ Mobile Application
- ✅ Docker Deployment
- ✅ CI/CD Pipeline
- ✅ Cloud Deployment

---

# 📈 Future Deployment

Recommended production stack

- **Frontend:** React + Vite → Netlify / Vercel
- **Backend:** Spring Boot → Render
- **Database:** MySQL / PostgreSQL (Render or Railway)
- **Storage:** Cloudinary (images)
- **CI/CD:** GitHub Actions

---

# 📷 Screenshots

You can add screenshots here after deployment.

```
Home Page

Login

Transportation Search

Accommodation Search

Booking Page

Payment Page

My Bookings

Admin Dashboard

Manage Transportation

Manage Accommodation

Manage Users

Admin Bookings
```

---

# 👨‍💻 Author

Developed by **Divyanshu Bansal**

GitHub: **https://github.com/deobistry**

---

# 📄 License

This project is intended for educational and portfolio purposes.
