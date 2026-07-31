import {
    Routes,
    Route
} from "react-router-dom";


import MainLayout from "../layouts/MainLayout";
import TransportBooking from "../pages/TransportBooking";
import Accommodation from "../pages/Accommodation";
import Home from "../pages/Home";
import Login from "../pages/Login";
import Signup from "../pages/Signup";
import Transportation from "../pages/Transportation";
import Payment from "../pages/Payment";
import AccommodationBooking from "../pages/AccommodationBooking";
import MyBookings from "../pages/MyBookings";
import ManageAccommodation from "../pages/admin/ManageAccommodation";
import AdminDashboard from "../pages/AdminDashboard";
import ManageTransportation from "../pages/admin/ManageTransportation";
import AdminRoute from "./AdminRoute";
import AddTransportation from "../pages/admin/AddTransportation";
import AddAccommodation from "../pages/admin/AddAccommodation";
import ManageUsers from "../pages/admin/ManageUsers";
import ManageBookings from "../pages/admin/ManageBookings";


function AppRoutes(){


return (

<Routes>


<Route element={<MainLayout/>}>


<Route
path="/"
element={<Home/>}
/>

<Route
path="/transportation"
element={<Transportation/>}
/>

<Route

path="/admin/bookings"

element={

<AdminRoute>

<ManageBookings/>

</AdminRoute>

}

/>

<Route

path="/admin/transportation/manage"

element={

<AdminRoute>

<ManageTransportation/>

</AdminRoute>

}

/>

<Route

path="/admin/users"

element={

<AdminRoute>

<ManageUsers/>

</AdminRoute>

}

/>

<Route

path="/admin/accommodation"

element={

<AdminRoute>

<AddAccommodation/>

</AdminRoute>

}

/>

<Route

path="/admin/accommodation/manage"

element={

<AdminRoute>

<ManageAccommodation/>

</AdminRoute>

}

/>

<Route

path="/admin/transportation"

element={

<AdminRoute>

<AddTransportation/>

</AdminRoute>

}

/>

<Route
path="/accommodation"
element={<Accommodation/>}
/>

<Route

path="/accommodation-booking"

element={<AccommodationBooking/>}

/>

<Route

path="/bookings"

element={<MyBookings/>}

/>


<Route

path="/admin"

element={

<AdminRoute>

<AdminDashboard/>

</AdminRoute>

}

/>

<Route
path="/login"
element={<Login/>}
/>

<Route

path="/payment"

element={<Payment/>}

/>

<Route

path="/transport-booking"

element={<TransportBooking/>}

/>

<Route
path="/signup"
element={<Signup/>}
/>


</Route>


</Routes>

);


}


export default AppRoutes;