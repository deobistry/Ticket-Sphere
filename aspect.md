# TicketSphere Project
## Complete Executable Development Summary

Version: 1.0  
Date: 2026  
Project Type: Full Stack Travel Booking Platform  

---

# 1. Project Overview

TicketSphere is a complete travel booking platform that allows users to:

- Search transportation services
- Book transportation
- Search accommodation
- Book accommodation
- Manage personal bookings
- Complete payment flow
- Manage user profile

The platform also provides an administrator panel where administrators can:

- View dashboard statistics
- Manage transportation
- Manage accommodation
- View registered users
- Manage user roles
- View all bookings

The project follows a modern full-stack architecture:

Frontend:
- React
- React Router
- Tailwind CSS
- Axios
- Lucide Icons

Backend:
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- JWT Authentication

---

# 2. System Architecture

The application follows a layered architecture.

## Backend Layers


Controller
|
|
Service
|
|
Repository
|
|
Database


Responsibilities:

Controller:
- Receives HTTP requests
- Returns responses

Service:
- Contains business logic
- Validates data
- Handles transactions

Repository:
- Communicates with database

Entity:
- Represents database tables

DTO:
- Transfers data between frontend and backend


---

# 3. Backend Package Structure

Current backend structure:


com.ticketing

├── controller
│
├── controller.admin
│
├── dto
│ ├── request
│ ├── response
│ └── admin
│
├── entity
│
├── enums
│
├── exception
│
├── repository
│
├── security
│
└── service
└── admin


---

# 4. Database Entities

The main database tables are:


users

transportation

transport_booking

accommodation

accommodation_booking


---

# 5. User Entity

Entity:


User


Database table:


users


Fields:

```java
Long id

String name

String email

String password

UserRole role

Roles:

USER

ADMIN
User Relationships

A user can have:

Many transport bookings:

@OneToMany
private List<TransportBooking> transportBookings;

Many accommodation bookings:

@OneToMany
private List<AccommodationBooking> accommodationBookings;

Relationship:

User
 |
 |
 +---- TransportBooking

 |
 |
 +---- AccommodationBooking
6. User Repository

Repository:

UserRepository

Extends:

JpaRepository<User,Long>

Methods:

findByEmail(String email)

countBy()

Used for:

Authentication
User lookup
Dashboard statistics
7. User Service

Service:

UserService

Responsibilities:

Fetch logged user
Return profile
Update profile

Methods:

getProfile()

updateProfile()

Uses:

UserContext

to identify currently logged-in user.

8. User Controller

Base URL:

/users

Endpoints:

Get Profile
GET /users/profile

Returns:

UserProfileResponse
Update Profile
PUT /users/profile

Updates:

Name
Email
Password
9. User Response DTO

Class:

UserProfileResponse

Contains:

id

name

email

role

Password is never returned.

10. Authentication System

The project uses JWT authentication.

Authentication flow:

User Login

      |
      |
Backend verifies credentials

      |
      |
JWT Token generated

      |
      |
Frontend stores token

      |
      |
Axios attaches token automatically

      |
      |
Backend validates token
11. Frontend Axios Configuration

File:

src/api/axios.js

Configuration:

baseURL:

http://localhost:8787

JWT attachment:

Authorization:
Bearer token

Every API request automatically receives authentication.

12. Frontend Structure

Current frontend:

src

├── api

├── components

├── context

├── layouts

├── pages

│   ├── admin
│
├── routes

└── App.jsx
13. React Routing

Main routing file:

AppRoutes.jsx

Uses:

react-router-dom

Routes include:

/

/login

/signup

/transportation

/accommodation

/transport-booking

/accommodation-booking

/payment

/bookings

/admin
14. Main Layout

Component:

MainLayout

Contains:

Navbar
Page content
Footer

All normal pages are rendered inside:

<Route element={<MainLayout/>}>
15. Admin Route Protection

Component:

AdminRoute.jsx

Purpose:

Only ADMIN users can access admin pages.

Logic:

if(role !== "ADMIN")
{
 redirect("/")
}

Role stored in:

localStorage
role = ADMIN

allows access.

role = USER

denies access.

16. Transportation Module

Transportation represents:

Bus
Train
Flight
Other travel options
Transportation Entity

Entity:

Transportation

Fields:

Long id

String transportNumber

TransportType type

String operatorName

String source

String destination

LocalDateTime departureTime

LocalDateTime arrivalTime

String duration

Integer totalSeats

Integer availableSeats

BigDecimal price
Transportation Types

Enum:

TransportType

Example:

BUS

TRAIN

FLIGHT
Transportation Relationships

One transportation can have many bookings:

Transportation

      |
      |
      +---- TransportBooking

Implemented:

@OneToMany(
mappedBy="transportation"
)
17. Transportation Repository

Repository:

TransportationRepository

Functions:

save()

findAll()

findById()

delete()
18. Transportation Service

Service:

TransportationService

Handles:

Create transportation
View transportation
Search transportation
Delete transportation
Create Transportation

Method:

createTransportation()

Validates:

Transport number required

Saves:

Transportation
Search Transportation

Method:

searchTransportation()

Supports:

source

destination

type

minimum price

maximum price

minimum seats

Uses:

Specification

for dynamic searching.

19. Transportation Controller

Base URL:

/transportation

Endpoints:

Create:

POST /transportation

Get all:

GET /transportation

Get by ID:

GET /transportation/{id}

Search:

GET /transportation/search

Delete:

DELETE /transportation/{id}

# 20. Accommodation Module

The accommodation module manages hotel and stay bookings.

Users can:

- Search accommodations
- Select rooms
- Choose check-in and check-out dates
- Book accommodation

---

# Accommodation Entity

Entity:


Accommodation


Represents:

- Hotels
- Resorts
- Other stays

Fields:

```java
Long id

String name

String city

String address

Integer totalRooms

Integer availableRooms

BigDecimal pricePerNight
Accommodation Relationships

One accommodation can have multiple bookings.

Relationship:

Accommodation

        |
        |
        +---- AccommodationBooking

Implemented:

@OneToMany(
mappedBy="accommodation"
)
Accommodation Repository

Repository:

AccommodationRepository

Extends:

JpaRepository<Accommodation,Long>

Provides:

Save accommodation
Find accommodation
Delete accommodation
Count accommodations
Accommodation Service

Service:

AccommodationService

Responsibilities:

Create accommodation
Get accommodations
Search accommodations
Delete accommodation
Accommodation Controller

Base URL:

/accommodation

Endpoints:

Create:

POST /accommodation

Get all:

GET /accommodation

Get by ID:

GET /accommodation/{id}

Search:

GET /accommodation/search

Delete:

DELETE /accommodation/{id}
21. Transport Booking Module

Transport booking allows users to reserve seats.

Entity:

TransportBooking
TransportBooking Fields
Long id

User user

Transportation transportation

Integer seatsBooked

BigDecimal totalAmount

BookingStatus status

LocalDateTime bookingDate
Transport Booking Relationship

User:

User

 |
 |
 +---- TransportBooking

Transportation:

Transportation

 |
 |
 +---- TransportBooking
Booking Status Enum

Enum:

BookingStatus

Values:

CONFIRMED

CANCELLED
Transport Booking Repository

Repository:

TransportBookingRepository

Methods:

findByUser(User user)

findByUserId(Long id)

countBy()
Transport Booking Service

Service:

TransportBookingService

Handles:

Creating booking
Viewing bookings
Cancelling booking
Book Transportation Flow

Method:

bookTransportation()

Steps:

Get logged user
Validate transportation ID
Find transportation
Check available seats
Calculate total price

Formula:

total amount =
transport price × seats booked
Reduce available seats
Create booking
Save booking

Example:

Transportation price = $50

Seats = 3


Total:

$50 × 3 = $150
Transport Booking Cancellation

Method:

cancelTransportBooking()

Steps:

Find booking
Verify ownership
Change status:
CONFIRMED

      ↓

CANCELLED
Restore seats

Example:

Before cancellation:

Available seats = 20

Booking:

5 seats

After cancellation:

Available seats = 25
Transport Booking Controller

Base URL:

/transport-bookings

Create booking:

POST /transport-bookings

Request:

{
 "transportationId":1,
 "seatsBooked":2
}

Get user bookings:

GET /transport-bookings/my

Get booking:

GET /transport-bookings/{id}

Cancel booking:

DELETE /transport-bookings/{id}
22. Accommodation Booking Module

Entity:

AccommodationBooking

Fields:

Long id

User user

Accommodation accommodation

Integer roomsBooked

LocalDate checkInDate

LocalDate checkOutDate

BigDecimal totalAmount

BookingStatus status

LocalDateTime bookingDate
Accommodation Booking Repository

Repository:

AccommodationBookingRepository

Methods:

findByUserId(Long id)

countBy()
Accommodation Booking Service

Service:

AccommodationBookingService

Handles:

Booking accommodation
Checking dates
Calculating amount
Cancelling booking
Accommodation Booking Calculation

Formula:

total amount =

price per night

×

number of nights

×

rooms booked

Example:

Room price = $100

Nights = 3

Rooms = 2


Total:

100 × 3 × 2

= $600
Date Validation

Before booking:

System checks:

Invalid:
Check-in after check-out

Example:

2026-08-10

to

2026-08-05

Rejected.

Invalid:

Same day booking:

Check-in:
2026-08-10


Check-out:
2026-08-10

Rejected.

Minimum:

1 night
Accommodation Cancellation

Flow:

Find booking
Verify user
Change status:
CONFIRMED

↓

CANCELLED
Restore rooms
Accommodation Booking Controller

Base URL:

/accommodation-bookings

Create:

POST /accommodation-bookings

Request:

{
"accommodationId":1,

"roomsBooked":2,

"checkInDate":"2026-08-10",

"checkOutDate":"2026-08-15"
}

User bookings:

GET /accommodation-bookings/my

Single booking:

GET /accommodation-bookings/{id}

Cancel:

DELETE /accommodation-bookings/{id}
23. Payment Module

Frontend payment system:

Payment.jsx

Currently simulated payment.

Flow:

Booking Page

      |

      |

Payment Page

      |

      |

Payment Successful

      |

      |

My Bookings
Payment State Transfer

Booking pages send:

navigate(
"/payment",
{
 state:{
 amount:value,

 type:"TRANSPORT"
 }
}
)

or:

type:"ACCOMMODATION"
Payment Page Features

Displays:

Booking type
Total amount
Card input fields
Payment button

States:

processing

success

Current implementation:

Fake payment delay

Future:

Integrate:

Stripe
Razorpay
PayPal
24. Frontend Transportation Search

Page:

Transportation.jsx

Purpose:

Search available transport.

Search Fields:

source

destination

API Call:

GET

/transportation/search

with:

source

destination

Results display:

Each card shows:

Transport number
Route
Type
Available seats
Price

Booking button:

Navigates:

/transport-booking

with:

state:{
 transport:item
}
25. Transport Booking Frontend

Page:

TransportBooking.jsx

Features:

Shows selected transportation
Select seats
Calculates total
Sends booking request

State:

const [seats,setSeats]=useState(1)

Booking API:

POST

/transport-bookings

Request:

{
"transportationId":id,

"seatsBooked":number
}

After success:

Redirect:

/payment
26. Accommodation Booking Frontend

Page:

AccommodationBooking.jsx

Features:

Select dates
Select rooms
Calculate amount
Create booking

Request:

{
"accommodationId":1,

"roomsBooked":2,

"checkInDate":"",

"checkOutDate":""
}

After success:

Redirect:

/payment

# 27. Admin Panel Overview

The administrator system provides management capabilities.

Admin can:

- View dashboard
- Manage transportation
- Manage accommodation
- Manage users
- View all bookings

Admin access is protected through:


AdminRoute.jsx


---

# 28. Admin Dashboard Frontend

File:


src/pages/AdminDashboard.jsx


Purpose:

Main control center for administrators.

---

Dashboard Cards:

## Transportation

Route:


/admin/transportation/manage


Allows:

- View transportation
- Edit transportation
- Delete transportation
- Add transportation


---

## Accommodation

Routes:


/admin/accommodation

/admin/accommodation/manage


Allows:

- Add accommodation
- Manage accommodation
- Delete accommodation


---

## Users

Route:


/admin/users


Allows:

- View registered users
- Manage roles
- Delete users


---

## Bookings

Route:


/admin/bookings


Allows:

- View all bookings
- Monitor booking activity


---

# 29. Admin Dashboard Backend

Controller:


AdminController


Package:


com.ticketing.controller


Base URL:


/admin


---

Endpoint:


GET /admin/dashboard


Returns:


AdminDashboardResponse


---

# 30. Admin Dashboard Response DTO

Class:


AdminDashboardResponse


Contains:

```java
Long totalUsers;

Long totalTransportBookings;

Long totalAccommodationBookings;

BigDecimal totalRevenue;

Long confirmedBookings;

Long cancelledBookings;

Dashboard Data Meaning:

Total Users

Counts:

users table
Total Transport Bookings

Counts:

transport_booking table
Total Accommodation Bookings

Counts:

accommodation_booking table
Total Revenue

Calculated from:

TransportBooking.totalAmount

+

AccommodationBooking.totalAmount
Confirmed Bookings

Counts:

BookingStatus.CONFIRMED
Cancelled Bookings

Counts:

BookingStatus.CANCELLED
31. Admin User Management

Backend package:

controller.admin

Controller:

AdminUserController

Base URL:

/admin/users
Get All Users

Endpoint:

GET /admin/users

Returns:

List<AdminUserResponse>
Get User By ID

Endpoint:

GET /admin/users/{id}
Delete User

Endpoint:

DELETE /admin/users/{id}
Update User Role

Endpoint:

PUT /admin/users/{id}/role

Request:

"ADMIN"

or:

"USER"
32. Admin User Service

Service:

AdminUserService

Responsibilities:

Fetch users
Convert user data
Delete users
Update roles

Methods:

getAllUsers()

getUserById()

deleteUser()

updateRole()
33. Admin User Response DTO

Class:

AdminUserResponse

Contains:

Long id;

String name;

String email;

UserRole role;

Password is excluded.

34. Frontend Admin User Page

File:

src/pages/admin/ManageUsers.jsx

(Currently created after implementation)

Purpose:

Display registered users.

Features:

Load all users
Display user cards/table
Show:
Name
Email
Role

Future:

Search users
Pagination
Block users
View user bookings
35. Admin Transportation Management

Frontend:

ManageTransportation.jsx

Location:

src/pages/admin/

Purpose:

Manage all transportation records.

Features:

Implemented:

View transportation list
Delete transportation
Navigate to add transportation
Transportation Display Information

Shows:

Transport Number

Type

Operator

Route

Seats

Price
Add Transportation

Route:

/admin/transportation

Component:

AddTransportation.jsx

Creates transportation through:

POST /transportation
36. Transportation Edit Feature

Added:

EditTransportation.jsx

Purpose:

Allow admin to modify existing transportation.

Flow:

Manage Transportation

        |

Click Edit

        |

EditTransportation Page

        |

PUT Request

        |

Update Database

Frontend Request:

PUT /transportation/{id}
Required Backend Update

TransportationController needs:

@PutMapping("/{id}")

Example:

@PutMapping("/{id}")
public ResponseEntity<String> updateTransportation(
@PathVariable Long id,
@RequestBody TransportationRequest request
)
{
    transportationService.updateTransportation(
        id,
        request
    );

    return ResponseEntity.ok(
        "Transportation updated successfully"
    );
}
Transportation Update Service

Required method:

updateTransportation()

Logic:

Find existing transportation
Update fields:
transportNumber

type

operatorName

source

destination

departureTime

arrivalTime

duration

totalSeats

availableSeats

price
Save entity
37. Admin Accommodation Management

Frontend pages:

AddAccommodation.jsx

ManageAccommodation.jsx

Features:

Add accommodation
View accommodation
Delete accommodation

Displayed:

Name

City

Address

Rooms

Price
38. Admin Booking Management

Purpose:

Allow admin to monitor all customer bookings.

Planned route:

/admin/bookings

Admin view should combine:

Transport Bookings

Information:

Booking ID

User

Transport Number

Route

Seats

Amount

Status

Booking Date
Accommodation Bookings

Information:

Booking ID

User

Hotel

Rooms

Check In

Check Out

Amount

Status

Booking Date
39. Required Backend For Admin Bookings

New package:

service.admin

Create:

AdminBookingService

Create Controller:

AdminBookingController

Base URL:

/admin/bookings
Required Endpoints

Get all transport bookings:

GET /admin/bookings/transport

Get all accommodation bookings:

GET /admin/bookings/accommodation

Get all bookings combined:

GET /admin/bookings
40. Admin Booking Response DTOs

Recommended:

AdminTransportBookingResponse

Fields:

Long bookingId;

String userName;

String userEmail;

String transportNumber;

String source;

String destination;

Integer seatsBooked;

BigDecimal amount;

BookingStatus status;

LocalDateTime bookingDate;
AdminAccommodationBookingResponse

Fields:

Long bookingId;

String userName;

String userEmail;

String accommodationName;

Integer roomsBooked;

LocalDate checkInDate;

LocalDate checkOutDate;

BigDecimal amount;

BookingStatus status;

LocalDateTime bookingDate;
41. Frontend Routing Summary

Current routes:

/

Home page

/login

Login

/signup

Registration

/transportation

Transportation search

/transport-booking

Seat booking

/accommodation

Accommodation search

/accommodation-booking

Room booking

/payment

Payment

/bookings

User bookings

Admin routes:

/admin

Dashboard

/admin/users

Users

/admin/transportation

Add transportation

/admin/transportation/manage

Manage transportation

/admin/accommodation

Add accommodation

/admin/accommodation/manage

Manage accommodation

/admin/bookings

Bookings

# 42. Complete Backend API Map

This section documents the current backend API structure.

---

# Authentication APIs

## Register User

Endpoint:


POST /auth/signup


Purpose:

Creates a new user account.

Request:

```json
{
"name":"John Doe",
"email":"john@gmail.com",
"password":"password"
}

Response:

User created successfully
Login User

Endpoint:

POST /auth/login

Purpose:

Authenticates user and generates JWT.

Response contains:

{
"token":"JWT_TOKEN",

"role":"USER"
}

Frontend stores:

localStorage.token

localStorage.role
User APIs

Base URL:

/users
Get Profile
GET /users/profile

Returns:

{
"id":1,

"name":"John",

"email":"john@gmail.com",

"role":"USER"
}
Update Profile
PUT /users/profile

Updates:

name

email

password
Transportation APIs

Base:

/transportation
Create Transportation
POST /transportation

Admin only.

Request:

{
"transportNumber":"AI101",

"type":"FLIGHT",

"operatorName":"Air India",

"source":"Delhi",

"destination":"Mumbai",

"departureTime":"2026-08-01T10:00:00",

"arrivalTime":"2026-08-01T12:00:00",

"duration":"2 Hours",

"totalSeats":200,

"availableSeats":200,

"price":5000
}
Get All Transportation
GET /transportation

Returns all available transportation.

Get Transportation By ID
GET /transportation/{id}
Search Transportation
GET /transportation/search

Parameters:

source

destination

type

minPrice

maxPrice

minSeats

Example:

/transportation/search?

source=Delhi

&destination=Mumbai

&type=FLIGHT
Delete Transportation
DELETE /transportation/{id}

Admin only.

Update Transportation

Required:

PUT /transportation/{id}

Admin only.

Accommodation APIs

Base:

/accommodation
Create Accommodation
POST /accommodation

Admin only.

Get Accommodation
GET /accommodation
Get By ID
GET /accommodation/{id}
Search Accommodation
GET /accommodation/search

Parameters:

city

minPrice

maxPrice

rooms
Delete Accommodation
DELETE /accommodation/{id}
Booking APIs
Transport Booking

Base:

/transport-bookings

Create:

POST /transport-bookings

Request:

{
"transportationId":5,

"seatsBooked":2
}

User Bookings:

GET /transport-bookings/my

Single Booking:

GET /transport-bookings/{id}

Cancel:

DELETE /transport-bookings/{id}
Accommodation Booking

Base:

/accommodation-bookings

Create:

POST /accommodation-bookings

Request:

{
"accommodationId":4,

"roomsBooked":2,

"checkInDate":"2026-08-10",

"checkOutDate":"2026-08-15"
}

User Bookings:

GET /accommodation-bookings/my

Single Booking:

GET /accommodation-bookings/{id}

Cancel:

DELETE /accommodation-bookings/{id}
Admin APIs

Base:

/admin

Dashboard:

GET /admin/dashboard

Users:

Base:

/admin/users

Get users:

GET /admin/users

Get user:

GET /admin/users/{id}

Delete user:

DELETE /admin/users/{id}

Update role:

PUT /admin/users/{id}/role
43. Frontend Data Flow

The general frontend flow:

React Component

        |

        |

Axios API Request

        |

        |

Spring Controller

        |

        |

Service Layer

        |

        |

Repository

        |

        |

Database
44. Transportation Booking Flow

Complete user journey:

User opens Transportation page

        |

        |

Enters source and destination

        |

        |

API search request

        |

        |

Transportation list displayed

        |

        |

User clicks Book Now

        |

        |

TransportBooking.jsx opens

        |

        |

User selects seats

        |

        |

POST /transport-bookings

        |

        |

Booking saved

        |

        |

Redirect Payment.jsx

        |

        |

Payment success

        |

        |

User views bookings
45. Accommodation Booking Flow

Complete flow:

User searches accommodation

        |

        |

Selects hotel

        |

        |

AccommodationBooking.jsx

        |

        |

Select dates

        |

        |

Select rooms

        |

        |

POST /accommodation-bookings

        |

        |

Booking saved

        |

        |

Payment page

        |

        |

My bookings
46. Current Features Completed
Authentication

Completed:

✅ User registration

✅ User login

✅ JWT authentication

✅ Role based access

User Module

Completed:

✅ View profile

✅ Update profile

✅ User entity relationships

Transportation Module

Completed:

✅ Add transportation

✅ View transportation

✅ Search transportation

✅ Delete transportation

✅ User booking

✅ Seat availability handling

Accommodation Module

Completed:

✅ Add accommodation

✅ View accommodation

✅ Search accommodation

✅ Delete accommodation

✅ Room availability handling

Booking Module

Completed:

✅ Transport booking

✅ Accommodation booking

✅ Booking cancellation

✅ Booking history

Payment Module

Completed:

✅ Payment UI

✅ Amount display

✅ Payment simulation

Admin Module

Completed:

✅ Admin dashboard

✅ Admin route protection

✅ User management backend

✅ Transportation management

✅ Accommodation management

47. Issues Solved During Development
Issue 1
Problem:

Booking allowed invalid seat numbers.

Solution:

Added validation:

if(seatsBooked <=0)
{
throw exception;
}
Issue 2
Problem:

Booking more seats than available.

Solution:

Added:

if(
availableSeats < requestedSeats
)
Issue 3
Problem:

User could access another user's booking.

Solution:

Added ownership check:

booking.getUser().getId()

compared with:

userContext.getUserId()
Issue 4
Problem:

Cancelled bookings did not restore inventory.

Solution:

Cancellation now:

Booking cancelled

+

Seats/Rooms restored
Issue 5
Problem:

Payment page lost booking amount.

Solution:

Used React Router state:

navigate(
"/payment",
{
state:{
amount:value
}
}
)
Issue 6
Problem:

Admin routes accessible without role.

Solution:

Created:

AdminRoute.jsx

Checks:

localStorage.role
Issue 7
Problem:

Transportation edit returned:

HTTP 500

Cause:

Frontend called:

PUT /transportation/{id}

but backend lacked update endpoint.

Required fix:

Add:

@PutMapping("/{id}")

and:

updateTransportation()

service method.

48. Current Development State

Current project status:

Authentication
        DONE


Users
        DONE


Transportation
        DONE


Accommodation
        DONE


Bookings
        DONE


Payment UI
        DONE


Admin Users
        DONE


Admin Transportation
        MOSTLY DONE


Admin Bookings
        NEXT IMPLEMENTATION
49. Current Priority Roadmap

Recommended next development order:

Complete Admin Booking Page

Add payment status

Connect real payment gateway

Improve dashboard analytics

Add search filters UI

Add booking receipts

Add email notifications

Deploy application


## Final Architecture, Database Design, Production Improvements, Security, Deployment & Future Roadmap

---

# 1. Final Database Relationship Diagram

## Entity Relationship Overview

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
          ┌──────────────────┴──────────────────┐
          │                                     │
          ▼                                     ▼

┌─────────────────────────┐ ┌────────────────────────────┐
│ TransportBooking │ │ AccommodationBooking │
├─────────────────────────┤ ├────────────────────────────┤
│ id │ │ id │
│ user_id │ │ user_id │
│ transportation_id │ │ accommodation_id │
│ seatsBooked │ │ roomsBooked │
│ totalAmount │ │ checkInDate │
│ status │ │ checkOutDate │
│ bookingDate │ │ totalAmount │
└───────────┬─────────────┘ │ status │
│ │ bookingDate │
▼ └────────────┬───────────────┘

┌─────────────────────────┐ ┌────────────────────────────┐
│ Transportation │ │ Accommodation │
├─────────────────────────┤ ├────────────────────────────┤
│ id │ │ id │
│ transportNumber │ │ name │
│ type │ │ city │
│ operatorName │ │ address │
│ source │ │ pricePerNight │
│ destination │ │ totalRooms │
│ departureTime │ │ availableRooms │
│ arrivalTime │ └────────────────────────────┘
│ duration │
│ totalSeats │
│ availableSeats │
│ price │
└─────────────────────────┘


---

# Database Relationships

## User → TransportBooking

One user can have multiple transport bookings.

Relationship:


User (1) -------- (*) TransportBooking


---

## User → AccommodationBooking

One user can have multiple accommodation bookings.


User (1) -------- (*) AccommodationBooking


---

## Transportation → TransportBooking

One transportation option can have multiple bookings.

Example:


Flight AI101

Booking 1
Booking 2
Booking 3


Relationship:


Transportation (1) -------- (*) TransportBooking


---

## Accommodation → AccommodationBooking

One accommodation can have multiple bookings.

Example:


Grand Hotel

Booking A
Booking B
Booking C


Relationship:


Accommodation (1) -------- (*) AccommodationBooking


---

# 2. Final Backend Project Structure


ticketing-backend

src/main/java/com/ticketing

│
├── controller
│
│ ├── AuthController
│ ├── UserController
│ ├── TransportationController
│ ├── AccommodationController
│ ├── TransportBookingController
│ ├── AccommodationBookingController
│ └── AdminController
│
├── controller/admin
│
│ └── AdminUserController
│
├── service
│
│ ├── AuthService
│ ├── UserService
│ ├── TransportationService
│ ├── AccommodationService
│ ├── TransportBookingService
│ ├── AccommodationBookingService
│ └── AdminService
│
├── service/admin
│
│ └── AdminUserService
│
├── repository
│
│ ├── UserRepository
│ ├── TransportationRepository
│ ├── AccommodationRepository
│ ├── TransportBookingRepository
│ └── AccommodationBookingRepository
│
├── entity
│
│ ├── User
│ ├── Transportation
│ ├── Accommodation
│ ├── TransportBooking
│ └── AccommodationBooking
│
├── dto
│
│ ├── request
│ └── response
│
├── security
│
│ ├── JWTFilter
│ ├── JWTService
│ └── UserContext
│
└── enums

├── UserRole
├── BookingStatus
└── TransportType

---

# 3. Final Frontend Structure


ticketing-frontend

src

│
├── api
│ └── axios.js
│
├── context
│ └── AuthContext.jsx
│
├── routes
│
│ ├── AppRoutes.jsx
│ └── AdminRoute.jsx
│
├── layouts
│
│ └── MainLayout.jsx
│
├── pages
│
│ ├── Home.jsx
│ ├── Login.jsx
│ ├── Signup.jsx
│ ├── Transportation.jsx
│ ├── Accommodation.jsx
│ ├── TransportBooking.jsx
│ ├── AccommodationBooking.jsx
│ ├── Payment.jsx
│ ├── MyBookings.jsx
│
│ ├── AdminDashboard.jsx
│
│ └── admin
│
│ ├── ManageTransportation.jsx
│ ├── AddTransportation.jsx
│ ├── EditTransportation.jsx
│ ├── ManageAccommodation.jsx
│ ├── AddAccommodation.jsx
│ └── ManageUsers.jsx
│
└── components

├── Navbar.jsx
├── Footer.jsx
└── Cards

---

# 4. Production Improvements

## Backend Improvements

---

## Password Encryption

Current:


User Password
|
↓
Database


Production:


User Password

  ↓

BCrypt Encryption

  ↓

Database


Implementation:


BCryptPasswordEncoder


Benefits:

- Passwords are never stored as plain text
- Database leaks do not expose passwords directly

---

# Global Exception Handling

Create:


exception

├── GlobalExceptionHandler
├── ResourceNotFoundException
├── UnauthorizedException
└── ValidationException


Example response:

```json
{
 "timestamp":"2026-07-30",
 "status":404,
 "message":"Transportation not found"
}
DTO Validation

Instead of:

if(name==null)

Use:

@NotBlank
private String name;

Benefits:

Cleaner validation
Automatic API error handling
Pagination

Current:

GET /transportation

returns all records.

Production:

GET /transportation?page=0&size=20

Required when:

Large number of users
Large booking history
Thousands of transportation records
Database Migration

Replace automatic schema updates with:

Flyway

Example:

db

└── migration

    V1_create_users.sql

    V2_create_bookings.sql

    V3_add_payment.sql

Benefits:

Version controlled database
Safer deployments
5. Security Improvements
JWT Improvements

Current:

JWT stored in localStorage

Recommended:

JWT

↓

HttpOnly Cookie

↓

Browser

Benefits:

Better XSS protection
Safer authentication
Backend Role Security

Current:

Frontend checks:

role === "ADMIN"

Production:

Backend should enforce:

@PreAuthorize("hasRole('ADMIN')")

Example:

@DeleteMapping("/{id}")

@PreAuthorize("hasRole('ADMIN')")

public void deleteUser()
API Security

Add:

HTTPS
Rate limiting
Strict CORS rules
Request validation
Secure headers
6. Deployment Checklist
Backend

Before deployment:

✓ Database configured

✓ Environment variables added

✓ JWT secret changed

✓ Password encryption enabled

✓ CORS configured

✓ Production profile created

✓ Logging configured
Environment Variables

Example:

DATABASE_URL=

DATABASE_USERNAME=

DATABASE_PASSWORD=

JWT_SECRET=

SERVER_PORT=

Never store:

Passwords
API keys
JWT secrets

inside source code.

Frontend Deployment

Checklist:

✓ Replace localhost API URL

✓ Production build created

✓ Environment variables configured

✓ Authentication tested

✓ Admin routes tested
Hosting Options
Backend

Possible platforms:

AWS
Azure
Google Cloud
Railway
Render
Database

Possible:

PostgreSQL
MySQL
AWS RDS
Frontend

Possible:

Vercel
Netlify
AWS S3
7. Future Development Roadmap
Phase 1 — Completed

Status:

✅ User Authentication

✅ JWT Security

✅ User Profile

✅ Transportation Search

✅ Accommodation Search

✅ Transport Booking

✅ Accommodation Booking

✅ Payment Flow

✅ User Booking History

✅ Admin Dashboard

✅ Admin User Management

✅ Admin Transportation Management

Phase 2 — Payment System

Add:

Payment Entity

Fields:

id

booking_id

amount

paymentStatus

transactionId

paymentDate

Payment Status:

PENDING

SUCCESS

FAILED

REFUNDED
Phase 3 — Advanced Admin Analytics

Add:

Total Revenue

Monthly Revenue

Popular Routes

Most Booked Hotels

Active Users

Charts:

Revenue Graph

Booking Graph

User Growth Graph
Phase 4 — User Features

Add:

Digital Tickets

Generate:

PDF Ticket

QR Code

Contains:

Passenger Name

Route

Date

Booking ID

Payment Status
Notifications

Email:

Booking Confirmed

Payment Received

Cancellation Completed
Reviews

Users can:

Rate Transportation

Rate Hotels

Leave Comments
Phase 5 — Advanced Search

Transportation filters:

Type

Price Range

Departure Time

Seats Available

Accommodation filters:

City

Price

Rating

Availability
Phase 6 — Mobile Application

Create:

TicketSphere Mobile App

Technology:

React Native
Flutter

Features:

Booking

Payment

Notifications

Digital Tickets
Final TicketSphere Status
User Features

✅ Signup/Login

✅ JWT Authentication

✅ Search Transportation

✅ Search Accommodation

✅ Book Transportation

✅ Book Accommodation

✅ Payment Flow

✅ View Bookings

Admin Features

✅ Admin Authentication

✅ Dashboard

✅ Manage Transportation

✅ Add Transportation

✅ Edit Transportation

✅ Delete Transportation

✅ Manage Accommodation

✅ Manage Users

✅ Change User Roles

✅ View Bookings

Final Architecture
React Frontend

        |
        |
        ↓

Spring Boot REST API

        |
        |
        ↓

JPA / Hibernate

        |
        |
        ↓

MySQL Database

Security:

JWT Authentication

+

Role Based Authorization

+

Protected Routes
Final Vision

TicketSphere has been structured as a scalable travel booking platform.

The current architecture supports expansion into:

Real payment processing
Mobile applications
Advanced analytics
Email notifications
Digital tickets
Reviews
Enterprise travel management

The foundation is ready for production-level improvements and future scaling.


