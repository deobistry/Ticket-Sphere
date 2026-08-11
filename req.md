# Transport & Accommodation Booking System – Requirements & Implementation Plan

## Project Overview

A full-stack booking platform for transportation and accommodation services.

The backend will be developed using **Spring Boot REST APIs**, while the frontend will be developed later using **React + Vite** with a modern and responsive UI.

Users will be able to browse, filter, and book different types of services:

- Transportation
  - Train
  - Bus
  - Flight
- Accommodation
  - Hotel
  - Hostel
  - Apartment
  - Resort
  - Homestay

The system will be designed in a modular way so additional booking services can easily be added in the future.

---

# Tech Stack

## Backend

- Spring Boot
- Spring Data JPA
- MySQL
- JWT Authentication (jjwt)
- Maven

## Frontend (Phase 2)

- React
- Vite
- React Router
- Axios
- Tailwind CSS (recommended)
- React Icons

---

# Dependencies (pom.xml)

Include only:

- spring-boot-starter-web
- spring-boot-starter-data-jpa
- mysql-connector-j
- jjwt-api
- jjwt-impl (runtime)
- jjwt-jackson (runtime)

Optional:

- spring-boot-devtools

---

# Database Credentials

Database Name

```
ticketing_system
```

```
DB_USERNAME=root
DB_PASSWORD=admin123
```

Use these values inside `application.properties`.

---

# System Modules

## Authentication

- User Registration
- Login
- JWT Authentication
- Profile

---

## Transportation Module

Supports

- Train Booking
- Bus Booking
- Flight Booking

Users can

- Search routes
- Apply filters
- View available trips
- Book tickets

---

## Accommodation Module

Supports

- Hotels
- Hostels
- Apartments
- Resorts
- Homestays

Users can

- Search stays
- Apply filters
- View rooms
- Book rooms

---

# Data Model

---

## Users

| Column | Type |
|----------|------|
| id | Long |
| name | String |
| email | String |
| password | String |

---

## Transportation

Represents every transport option.

| Column | Type |
|----------|------|
| id | Long |
| type | TRAIN / BUS / FLIGHT |
| operatorName | String |
| source | String |
| destination | String |
| departureTime | LocalDateTime |
| arrivalTime | LocalDateTime |
| duration | String |
| availableSeats | Integer |
| price | BigDecimal |

---

## Accommodation

| Column | Type |
|----------|------|
| id | Long |
| type | HOTEL / HOSTEL / RESORT / APARTMENT / HOMESTAY |
| name | String |
| location | String |
| rating | Double |
| amenities | String |
| availableRooms | Integer |
| pricePerNight | BigDecimal |

---

## Transport Booking

| Column | Type |
|----------|------|
| id | Long |
| userId | Long |
| transportId | Long |
| travelDate | LocalDate |
| passengers | Integer |
| bookingDate | LocalDateTime |
| bookingStatus | String |

---

## Accommodation Booking

| Column | Type |
|----------|------|
| id | Long |
| userId | Long |
| accommodationId | Long |
| checkIn | LocalDate |
| checkOut | LocalDate |
| guests | Integer |
| bookingDate | LocalDateTime |
| bookingStatus | String |

---

# Entity Classes

Create plain JPA entities

- User
- Transportation
- Accommodation
- TransportBooking
- AccommodationBooking

No entity relationships initially.

Use only foreign key IDs.

---

# DTO Classes

## Authentication

- SignupRequest
- LoginRequest
- LoginResponse

---

## Transportation

- TransportCreateRequest
- TransportUpdateRequest
- TransportResponse
- TransportFilterRequest

---

## Accommodation

- AccommodationCreateRequest
- AccommodationUpdateRequest
- AccommodationResponse
- AccommodationFilterRequest

---

## Booking

### Transport

- TransportBookingRequest
- TransportBookingResponse

### Accommodation

- AccommodationBookingRequest
- AccommodationBookingResponse

---

# Transportation Filters

The transport search service should support dynamic filtering.

Available filters include:

- Source
- Destination
- Travel Date
- Transport Type
  - Train
  - Bus
  - Flight
- Minimum Price
- Maximum Price
- Departure Time
- Arrival Time
- Number of Stops (Flight)
- Available Seats
- Operator Name
- Duration

The filters should work together dynamically.

Example

```
Delhi → Mumbai

Date = 15 July

Type = Flight

Price <= 6000

Morning Flights
```

---

# Accommodation Filters

The accommodation search service should support filtering based on

- City
- Check-in Date
- Check-out Date
- Number of Guests
- Price Range
- Property Type
- Rating
- Amenities
- Room Availability

Examples

```
Location = Goa

Rating >= 4

Price < 5000

Pool

Free Wifi
```

---

# Booking Flow

Transportation

Search

↓

Apply Filters

↓

View Results

↓

View Details

↓

Book Ticket

↓

Booking Confirmation

---

Accommodation

Search

↓

Apply Filters

↓

View Properties

↓

View Rooms

↓

Book Stay

↓

Booking Confirmation

---

# API Endpoints

---

## Authentication

```
POST /auth/signup

POST /auth/login
```

---

## Transportation

```
GET /transport

GET /transport/{id}

GET /transport/filter

POST /transport

PUT /transport

DELETE /transport/{id}
```

---

## Accommodation

```
GET /accommodations

GET /accommodations/{id}

GET /accommodations/filter

POST /accommodations

PUT /accommodations

DELETE /accommodations/{id}
```

---

## Booking

### Transport

```
POST /bookings/transport

GET /bookings/transport

GET /bookings/transport/{id}

DELETE /bookings/transport/{id}
```

---

### Accommodation

```
POST /bookings/accommodation

GET /bookings/accommodation

GET /bookings/accommodation/{id}

DELETE /bookings/accommodation/{id}
```

---

# Frontend Overview (React + Vite)

The frontend should have a clean, modern, and mobile-responsive design inspired by booking applications such as ConfirmTkt, MakeMyTrip, and Booking.com.

## Home Page

The landing page contains two primary booking cards:

### Transportation

Users can choose

- Train
- Bus
- Flight

After selection they are redirected to the corresponding booking page.

---

### Accommodation

Users can choose

- Hotel
- Hostel
- Resort
- Apartment
- Homestay

After selection they are redirected to the accommodation booking page.

---

## Transportation Booking Page

Contains

- Source
- Destination
- Travel Date
- Passenger Count
- Search Button

Below the search form

Left Sidebar Filters

- Price
- Departure Time
- Arrival Time
- Stops
- Operator
- Duration

Right Side

Scrollable list of transport options displayed as responsive cards.

Each card displays

- Operator Name
- Source
- Destination
- Departure Time
- Arrival Time
- Duration
- Price
- Available Seats
- Book Button

---

## Accommodation Booking Page

Search section

- City
- Check-in
- Check-out
- Guests

Sidebar Filters

- Price
- Rating
- Property Type
- Amenities
- Availability

Property cards include

- Property Image
- Name
- Rating
- Price
- Amenities
- Room Availability
- Book Button

---

## Booking Confirmation Page

Displays

- Booking ID
- Booking Details
- Passenger/Guest Details
- Total Cost
- Booking Status

---

# Implementation Phases

---

## Phase 1 — Project Setup

- Initialize Spring Boot project
- Configure MySQL
- Configure JWT
- Create project structure
- Create entities
- Create repositories

**Exit Criteria**

Project boots successfully.

---

## Phase 2 — Authentication

- Signup
- Login
- JWT
- Authentication Filter

**Exit Criteria**

Protected APIs require a valid JWT.

---

## Phase 3 — Transportation Module

- CRUD APIs
- Search APIs
- Dynamic filters
- Booking APIs

**Exit Criteria**

Users can search and book transport tickets.

---

## Phase 4 — Accommodation Module

- CRUD APIs
- Search APIs
- Dynamic filters
- Booking APIs

**Exit Criteria**

Users can search and book accommodation.

---

## Phase 5 — Booking Management

- Booking history
- Cancel booking
- View booking details

**Exit Criteria**

Users can manage all bookings.

---

## Phase 6 — React Frontend

Develop the frontend using React + Vite.

Pages include

- Login
- Signup
- Home
- Transportation Search
- Accommodation Search
- Booking Details
- Booking History
- Profile

Responsive design for desktop, tablet, and mobile.

---

## Phase 7 — Final Integration & Testing

- Backend and frontend integration
- API testing
- UI testing
- Bug fixing
- Performance optimization

**Exit Criteria**

A complete transport and accommodation booking platform with modern UI, dynamic filtering, JWT authentication, and end-to-end booking functionality.