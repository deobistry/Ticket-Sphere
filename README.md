# 🎫 TicketSphere

> A full-stack travel booking platform for transportation and accommodation reservations, with JWT authentication and a role-based admin management system.

TicketSphere is a modern full-stack travel booking application that allows users to search and book transportation and accommodation, manage their bookings and profiles, and go through a simulated payment flow.

The platform also includes an administrator dashboard for managing users, transportation, accommodations, and booking activity.

---

## ✨ Features

### 👤 User Features

- User registration and login
- JWT-based authentication
- Role-based authorization
- User profile management
- Search transportation
- Search accommodation
- Book transportation
- Book accommodation
- Seat availability management
- Room availability management
- Booking cancellation
- Booking history
- Simulated payment flow

### 🚌 Transportation

- Add transportation
- View transportation
- Search by:
  - Source
  - Destination
  - Type
  - Price range
  - Available seats
- Transportation booking
- Seat availability tracking
- Edit transportation
- Delete transportation

Supported transportation types can include:

- Bus
- Train
- Flight
- Other travel services

### 🏨 Accommodation

- Add accommodation
- View accommodation
- Search by:
  - City
  - Price range
  - Room availability
- Select check-in/check-out dates
- Select number of rooms
- Automatic booking amount calculation
- Room availability management
- Booking cancellation
- Delete accommodation

### 💳 Payment

The current application includes a simulated payment flow.

Payment flow:

```text
Booking
   ↓
Payment Page
   ↓
Payment Processing
   ↓
Payment Success
   ↓
My Bookings
