# TicketSphere Project Context Documentation

This document explains the complete structure, architecture, and functionality of the TicketSphere project.

Use this document as the primary context before making any future modifications.

---

# 1. Project Overview

TicketSphere is a full-stack travel booking platform.

The application allows:

Users:
- Register
- Login
- Search transportation
- Search accommodations
- Book services
- Cancel bookings

Admins:
- Login
- Access dashboard
- Add transportation
- Delete transportation
- Add accommodation
- Delete accommodation
- Manage users


---

# 2. Technology Stack


## Frontend

Location:

```
frontend/
```

Technologies:

- React
- Vite
- Tailwind CSS
- React Router
- Axios
- Lucide Icons


Purpose:

Responsible for:

- UI rendering
- Navigation
- User interaction
- Calling backend APIs
- Managing authentication state


---

## Backend

Location:

```
backend/
```

Technologies:

- Java Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- JWT


Purpose:

Responsible for:

- Business logic
- Database communication
- Authentication
- Authorization
- API handling


---

# 3. Frontend Structure


```
src
|
|-- api
|   |
|   |-- axios.js
|
|-- components
|   |
|   |-- Navbar.jsx
|   |-- Footer.jsx
|   |-- Button.jsx
|   |-- FeatureCard.jsx
|
|-- context
|   |
|   |-- AuthContext.jsx
|
|-- pages
|   |
|   |-- Home.jsx
|   |-- Login.jsx
|   |-- Signup.jsx
|   |
|   |-- Transportation.jsx
|   |-- Accommodation.jsx
|   |
|   |-- Bookings.jsx
|   |
|   |-- admin
|       |
|       |-- AdminDashboard.jsx
|       |-- ManageTransportation.jsx
|       |-- ManageAccommodation.jsx
|       |-- ManageUsers.jsx
|
|-- App.jsx
|-- main.jsx
|-- index.css

```


---

# 4. Frontend File Responsibilities


## App.jsx

Responsible for:

- Application routing
- Connecting pages
- Protected routes


Example routes:

```
/
 /login
 /signup
 /transportation
 /accommodation

/admin/dashboard
/admin/transportation
/admin/accommodation
/admin/users

```


---

# Navbar.jsx


Location:

```
components/Navbar.jsx
```


Purpose:

Global navigation component.


Responsibilities:

- Shows navigation links
- Checks login state
- Shows logout button
- Redirects users


Uses:

```
AuthContext
```


Current behaviour:


Normal user:

Shows:

- Home
- Transport
- Hotels
- Logout


Admin:

Shows:

- Home
- Transport
- Hotels
- Dashboard
- Logout


---

# AuthContext.jsx


Purpose:

Stores authentication state.


Responsibilities:

- Store JWT token
- Login user
- Logout user
- Provide authentication globally


Flow:


Login:

Frontend
|
POST /auth/login
|
Backend returns JWT
|
Token stored
|
User authenticated


---

# api/axios.js


Purpose:

Central API configuration.


Responsibilities:

- Backend URL
- Attach JWT token automatically
- Handle API requests


All frontend API calls use:

```
api.get()
api.post()
api.delete()
```


---

# Home.jsx


Purpose:

Landing page.


Contains:

- Hero section
- Navigation cards
- Features
- Destinations


Important:

Quick cards redirect users:

Transportation:

```
/transportation
```


Accommodation:

```
/accommodation
```


Admin should redirect to management pages.


---

# Login.jsx


Purpose:

User authentication.


Flow:


User enters:

- Email
- Password


Request:

```
POST /auth/login
```


Response:

```
JWT Token
```


Token stored through:

```
AuthContext
```


---

# Signup.jsx


Purpose:

Create user account.


Request:

```
POST /auth/signup
```


---

# AdminDashboard.jsx


Purpose:

Admin homepage.


Current cards:

1. Transportation

Route:

```
/admin/transportation
```


2. Accommodation

Route:

```
/admin/accommodation
```


3. Users

Route:

```
/admin/users
```


Removed:

Bookings section


---

# ManageTransportation.jsx


Purpose:

Admin transportation management.


Functions:

- Load transportation
- Add transportation
- Delete transportation


API:

Get:

```
GET /transportation
```


Delete:

```
DELETE /transportation/{id}
```


Delete flow:


Button click

↓

Confirmation popup

↓

API delete request

↓

Remove from React state

↓

UI refresh


---

# ManageAccommodation.jsx


Purpose:

Admin hotel management.


Functions:

- View hotels
- Add hotels
- Delete hotels


API:


Get:

```
GET /accommodation
```


Delete:

```
DELETE /accommodation/{id}
```


Delete flow:


Button click

↓

Confirmation

↓

Backend deletion

↓

Frontend state update


---

# 5. Backend Structure


```
backend/src/main/java/com/ticketing

|
|-- controller
|
|-- service
|
|-- repository
|
|-- entity
|
|-- dto
|
|-- security
|
|-- enums
|
|-- exception

```


---

# 6. Backend Package Responsibilities


# Controller


Location:

```
controller/
```


Purpose:

Receives HTTP requests.


Example:


```
POST /transportation

```


Controller calls:

```
Service
```


Controllers:

- AuthController
- TransportationController
- AccommodationController
- TransportBookingController
- AccommodationBookingController


---

# Service


Location:

```
service/
```


Purpose:

Contains business logic.


Examples:


TransportationService:

Handles:

- Creating transportation
- Searching transportation
- Fetching transportation


AccommodationService:

Handles:

- Creating hotels
- Searching hotels
- Deleting hotels


Booking Services:

Handle:

- Booking
- Cancellation
- Availability updates


---

# Repository


Location:

```
repository/
```


Purpose:

Database communication.


Examples:

```
TransportationRepository

AccommodationRepository

UserRepository

```


Uses:

Spring Data JPA


---

# Entity Layer


Location:

```
entity/
```


Contains database tables.


---

# User Entity


Stores:

- id
- name
- email
- password
- role


---

# Transportation Entity


Database table:

```
transportation
```


Stores:

- transport number
- type
- operator
- source
- destination
- seats
- price


Relationship:


Transportation

has many

TransportationBooking


---

# Accommodation Entity


Database table:

```
accommodation
```


Stores:

- name
- city
- address
- rating
- price
- rooms


Relationship:


Accommodation

has many

AccommodationBooking


---

# Booking Entities


## TransportationBooking


Stores:

- User
- Transportation
- Seats booked
- Amount
- Status


## AccommodationBooking


Stores:

- User
- Accommodation
- Rooms booked
- Dates
- Amount
- Status


---

# 7. Authentication Flow


Login request:

```
Frontend

POST /auth/login

        |

AuthController

        |

AuthService

        |

Database User Check

        |

JwtUtil

        |

JWT Returned

```


JWT contains:


```
email

userId

role

```


---

# 8. Admin Authorization Flow


Admin login:


↓

JWT generated with:

```
role=ADMIN
```


↓

Frontend reads role


↓

Admin routes available


---

# 9. Current Completed Features


Completed:


Authentication:

✓ Signup

✓ Login

✓ JWT

✓ Role support


Frontend:

✓ Homepage

✓ Navbar

✓ Routing

✓ Admin dashboard


Transportation:

✓ Add

✓ View

✓ Search

✓ Delete


Accommodation:

✓ Add

✓ View

✓ Search

✓ Delete


Bookings:

✓ Create

✓ Cancel

✓ Restore availability


Admin:

✓ Dashboard

✓ Manage transportation

✓ Manage accommodation


---

# 10. Current Development Rules


When modifying:


DO NOT:

- Break existing APIs
- Change working routes unnecessarily
- Remove authentication logic
- Rewrite working components completely


Always:

- Provide complete updated files
- Preserve existing functionality
- Mention required backend/frontend changes
- Keep naming conventions


---

# 11. Future Development Roadmap


Next possible features:


Admin:

- Edit transportation
- Edit accommodation
- Booking management
- Dashboard statistics


Users:

- Profile page
- Booking history
- Reviews


Platform:

- Payment system
- Email notifications
- Images
- AI recommendations


---

# 12. Future Session Starter Prompt


Use this prompt in a new ChatGPT session:


"I am continuing development of my TicketSphere project.

Read the attached PROJECT_CONTEXT.md and requirements.md.

Understand the complete architecture, frontend structure, backend structure, database relationships, existing APIs, authentication flow, and completed features.

Before modifying anything:
1. Analyze the existing implementation.
2. Preserve all working functionality.
3. Provide complete updated files only.
4. Mention if backend changes are required.
5. Do not remove existing features unless requested.

The current task is:
[WRITE NEW TASK HERE]
"
