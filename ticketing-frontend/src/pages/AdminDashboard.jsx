import {
    Link
} from "react-router-dom";


import {
    Bus,
    Hotel,
    Users,
    Ticket,
    BarChart3
} from "lucide-react";




function AdminDashboard(){


return (

<div className="
min-h-screen
bg-gray-100
py-16
">


<div className="
max-w-7xl
mx-auto
px-6
">


<h1 className="
text-5xl
font-extrabold
text-center
mb-4
">

TicketSphere Admin Dashboard

</h1>



<p className="
text-center
text-gray-600
mb-12
text-lg
">

Manage your transportation, accommodation and users

</p>







<div className="
grid
grid-cols-1
md:grid-cols-2
lg:grid-cols-5
gap-8
">





<Link
to="/admin/transportation/manage"
className="
bg-white
rounded-3xl
shadow-lg
p-8
hover:-translate-y-2
transition
text-center
"
>


<Bus
size={55}
className="
mx-auto
text-blue-600
mb-5
"
/>


<h2 className="
text-2xl
font-bold
">

Transportation

</h2>


<p className="
text-gray-600
mt-3
">

Add and manage buses, trains and flights.

</p>


</Link>








<Link
to="/admin/accommodation/manage"
className="
bg-white
rounded-3xl
shadow-lg
p-8
hover:-translate-y-2
transition
text-center
"
>


<Hotel
size={55}
className="
mx-auto
text-green-600
mb-5
"
/>


<h2 className="
text-2xl
font-bold
">

Accommodation

</h2>


<p className="
text-gray-600
mt-3
">

Add and manage hotels.

</p>


</Link>









<Link
to="/admin/users"
className="
bg-white
rounded-3xl
shadow-lg
p-8
hover:-translate-y-2
transition
text-center
"
>


<Users
size={55}
className="
mx-auto
text-purple-600
mb-5
"
/>


<h2 className="
text-2xl
font-bold
">

Users

</h2>


<p className="
text-gray-600
mt-3
">

View registered users.

</p>


</Link>









<Link
to="/admin/bookings"
className="
bg-white
rounded-3xl
shadow-lg
p-8
hover:-translate-y-2
transition
text-center
"
>


<Ticket
size={55}
className="
mx-auto
text-red-600
mb-5
"
/>


<h2 className="
text-2xl
font-bold
">

Bookings

</h2>


<p className="
text-gray-600
mt-3
">

Monitor all bookings.

</p>


</Link>









<Link
to="/admin/insights"
className="
bg-white
rounded-3xl
shadow-lg
p-8
hover:-translate-y-2
transition
text-center
"
>


<BarChart3
size={55}
className="
mx-auto
text-yellow-600
mb-5
"
/>


<h2 className="
text-2xl
font-bold
">

Insights

</h2>


<p className="
text-gray-600
mt-3
">

View revenue, bookings and performance analytics.

</p>


</Link>






</div>


</div>


</div>


);


}


export default AdminDashboard;