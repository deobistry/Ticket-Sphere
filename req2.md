# TicketSphere - Software Requirements Document

## 1. Project Overview

### Project Name
TicketSphere

### Description

TicketSphere is a full-stack travel management and booking platform that allows users to search, book, and manage transportation and accommodation services.

The platform provides:

- User authentication
- Transportation management
- Accommodation management
- Booking management
- Admin management dashboard
- Role-based access control


## Technology Stack

### Frontend

- React.js
- Vite
- Tailwind CSS
- React Router
- Axios
- Lucide React


### Backend

- Java Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- JWT Authentication


### Database

- Relational Database
- JPA/Hibernate ORM


---

# 2. User Roles

The system contains two types of users.


## 2.1 Normal User

Users can:

- Register an account
- Login
- Browse transportation
- Search transportation
- Browse accommodations
- Search hotels
- Book transportation
- Book accommodation
- View personal bookings
- Cancel bookings


---

## 2.2 Administrator

Administrators can:

- Login as admin
- Access admin dashboard
- Add transportation services
- Delete transportation services
- Add accommodation services
- Delete accommodation services
- View users


Future admin features:

- Edit services
- Booking management
- Reports and analytics


---

# 3. Authentication Requirements


## User Registration

Users can create an account using:

- Name
- Email
- Password
- Role


API:

```
POST /auth/signup
```


---

## User Login

Users login using:

- Email
- Password


API:

```
POST /auth/login
```


After successful authentication:

- Backend generates JWT token
- Token is stored on frontend
- Token is sent with protected requests


---

# 4. JWT Security


JWT contains:

- User email
- User ID
- User role
- Expiration time


The system validates:

- Token authenticity
- Token expiration
- User permissions


---

# 5. Authorization


## User Access

Users can access:

- Home page
- Transportation page
- Accommodation page
- Personal bookings


## Admin Access

Admins can access:

- Admin dashboard
- Transportation management
- Accommodation management
- User management


Unauthorized users must not access admin routes.


---

# 6. Homepage Requirements


The homepage provides:


## Hero Section

Contains:

- Platform introduction
- Travel description
- Navigation options


The unused search bar functionality was removed.


## Quick Navigation Cards


### Transportation Card

Redirects users to:

```
/transportation
```


Admin users:

```
/admin/transportation
```


---

### Accommodation Card

Redirects users to:

```
/accommodation
```


Admin users:

```
/admin/accommodation
```


---

# 7. Transportation Module


## Transportation Entity


Stores:

- Transport number
- Transport type
- Operator name
- Source
- Destination
- Departure time
- Arrival time
- Duration
- Total seats
- Available seats
- Price


---

## Transportation User Features


Users can:

- View transportation
- Search transportation
- Book transportation


Search supports:

- Source
- Destination
- Type
- Price range
- Available seats


---

## Transportation Admin Features


Admins can:

- Add transportation
- View transportation
- Delete transportation


Delete functionality:

- Shows confirmation warning
- Removes record from database
- Updates frontend list


---

# 8. Accommodation Module


## Accommodation Entity


Stores:

- Hotel name
- Accommodation type
- City
- Address
- Description
- Rating
- Price per night
- Available rooms


---

## Accommodation User Features


Users can:

- View hotels
- Search hotels
- Book accommodation


---

## Accommodation Admin Features


Admins can:

- Add hotels
- View hotels
- Delete hotels


Delete functionality:

- Confirmation before deletion
- Database removal
- UI refresh


---

# 9. Booking Module


## Transportation Booking


Users can:

- Select transportation
- Select seats
- Confirm booking


Stores:

- User
- Transportation
- Seats booked
- Total amount
- Booking status
- Booking date


---

## Accommodation Booking


Users can:

- Select hotel
- Select rooms
- Provide check-in date
- Provide check-out date


Stores:

- User
- Accommodation
- Rooms booked
- Total amount
- Booking status
- Booking date


---

# 10. Booking Cancellation


Users can cancel bookings.


Transportation cancellation:

- Booking status changes
- Seats are returned


Accommodation cancellation:

- Booking status changes
- Rooms are returned


---

# 11. Admin Dashboard


Route:

```
/admin/dashboard
```


Dashboard provides:


## Transportation Management

Route:

```
/admin/transportation
```


Functions:

- View transportation
- Add transportation
- Delete transportation


---

## Accommodation Management

Route:

```
/admin/accommodation
```


Functions:

- View hotels
- Add hotels
- Delete hotels


---

## User Management

Route:

```
/admin/users
```


Functions:

- View registered users


---

# 12. Navigation Requirements


Navbar supports:


Public Users:

- Home
- Transportation
- Accommodation
- Login
- Signup


Authenticated Users:

- Home
- Transportation
- Accommodation
- Logout


Admins:

- Home
- Transportation
- Accommodation
- Dashboard
- Logout


Removed:

- Empty bookings navigation


---

# 13. Backend API Requirements


## Authentication APIs


Register:

```
POST /auth/signup
```


Login:

```
POST /auth/login
```



---

# Transportation APIs


Create:

```
POST /transportation
```


Get All:

```
GET /transportation
```


Get By ID:

```
GET /transportation/{id}
```


Search:

```
GET /transportation/search
```


Delete:

```
DELETE /transportation/{id}
```



---

# Accommodation APIs


Create:

```
POST /accommodation
```


Get All:

```
GET /accommodation
```


Get By ID:

```
GET /accommodation/{id}
```


Search:

```
GET /accommodation/search
```


Delete:

```
DELETE /accommodation/{id}
```


---

# 14. Database Requirements


## User Table

Stores:

- User information
- Credentials
- Role


---

## Transportation Table

Stores:

- Transport details


---

## Accommodation Table

Stores:

- Hotel details


---

## Transportation Booking Table

Stores:

- Transportation reservations


---

## Accommodation Booking Table

Stores:

- Hotel reservations


---

# 15. Entity Relationships


```
User

 |

 |---- TransportationBooking

 |

 |---- AccommodationBooking



Transportation

 |

 |---- TransportationBooking



Accommodation

 |

 |---- AccommodationBooking

```


---

# 16. Security Requirements


The system must:

- Secure passwords
- Validate JWT tokens
- Restrict admin operations
- Validate booking ownership
- Prevent unauthorized actions


---

# 17. Completed Features


Completed:


Authentication:

- User signup
- User login
- JWT authentication
- Role handling


Frontend:

- Homepage
- Navigation bar
- User pages
- Admin dashboard


Transportation:

- Add transportation
- View transportation
- Search transportation
- Book transportation
- Delete transportation


Accommodation:

- Add hotels
- View hotels
- Search hotels
- Book accommodation
- Delete hotels


Bookings:

- Create bookings
- Cancel bookings
- Restore available resources


Admin:

- Dashboard
- Transportation management
- Accommodation management
- User management page


---

# 18. Future Enhancements


## Admin

- Edit transportation
- Edit hotels
- Booking monitoring
- Revenue dashboard
- Analytics


## User

- Profile management
- Booking history improvements
- Reviews
- Ratings
- Favorites


## Platform

- Payment gateway
- Email notifications
- Image uploads
- AI travel recommendations
- Advanced search


---

# 19. Project Goal


The goal of TicketSphere is to create a complete travel management platform where users can easily plan journeys and administrators can efficiently manage transportation and accommodation services.