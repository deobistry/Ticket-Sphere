import {
    useEffect,
    useState
} from "react";


import api from "../../api/axios";





function ManageBookings(){



const [bookings,setBookings] =
useState([]);



const [loading,setLoading] =
useState(true);








const loadBookings = async()=>{


try{


setLoading(true);



const response =
await api.get("/admin/bookings");



setBookings(
    response.data
);



}

catch(error){


console.error(
    "Failed loading bookings",
    error
);


}

finally{


setLoading(false);


}


};









useEffect(()=>{


loadBookings();


},[]);









return (

<div

className="
min-h-screen
bg-gray-100
py-12
"

>


<div

className="
max-w-7xl
mx-auto
px-6
"

>



<h1

className="
text-4xl
font-extrabold
mb-10
"

>

Manage Bookings

</h1>








{

loading

?

<h2

className="
text-center
text-xl
"

>

Loading bookings...

</h2>


:

bookings.length===0

?

<h2

className="
text-center
text-xl
text-gray-600
"

>

No bookings found

</h2>


:


<div

className="
grid
grid-cols-1
md:grid-cols-2
lg:grid-cols-3
gap-8
"

>


{


bookings.map(

(booking)=>(


<div

key={
booking.bookingType +
booking.bookingId
}

className="
bg-white
rounded-3xl
shadow-lg
p-6
hover:shadow-xl
transition
"

>




<div

className={

booking.bookingType==="TRANSPORT"

?

`
inline-block
bg-blue-100
text-blue-700
px-4
py-2
rounded-full
font-semibold
`

:

`

inline-block
bg-green-100
text-green-700
px-4
py-2
rounded-full
font-semibold

`

}

>

{booking.bookingType}

</div>








<h2

className="
text-2xl
font-bold
mt-5
"

>

{booking.itemName}

</h2>








<div

className="
mt-5
space-y-2
"

>


<p>

User:

<b>

{" "}
{booking.userName}

</b>

</p>





<p>

Email:

<b>

{" "}
{booking.userEmail}

</b>

</p>





<p>

Amount:

<b
className="
text-blue-600
"
>

{" "}
₹ {booking.amount}

</b>

</p>





<p>

Status:

<b>

{" "}
{booking.status}

</b>

</p>





<p>

Date:

<b>

{" "}
{

new Date(
booking.bookingDate
)
.toLocaleDateString()

}

</b>

</p>




</div>









</div>


)


)


}




</div>


}



</div>


</div>


);


}



export default ManageBookings;