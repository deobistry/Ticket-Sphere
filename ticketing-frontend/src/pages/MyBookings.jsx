import {
    useEffect,
    useState
} from "react";


import {
    Bus,
    Hotel,
    Calendar,
    IndianRupee,
    XCircle
} from "lucide-react";


import api from "../api/axios";





function MyBookings(){



const [transportBookings,setTransportBookings]=useState([]);

const [accommodationBookings,setAccommodationBookings]=useState([]);


const [loading,setLoading]=useState(true);









const loadBookings=async()=>{


try{


setLoading(true);



const transportResponse =
await api.get(
"/transport-bookings/my"
);



const accommodationResponse =
await api.get(
"/accommodation-bookings/my"
);




setTransportBookings(

    transportResponse.data.filter(
        booking =>
        booking.status !== "CANCELLED"
    )

);



setAccommodationBookings(

    accommodationResponse.data.filter(
        booking =>
        booking.status !== "CANCELLED"
    )

);





}

catch(error){

console.log(error);

}

finally{


setLoading(false);


}


};








useEffect(()=>{


loadBookings();


},[]);









const cancelTransport=async(id)=>{


try{


await api.delete(

`/transport-bookings/${id}`

);


loadBookings();


}

catch(error){

console.log(error);

}


};








const cancelAccommodation=async(id)=>{


try{


await api.delete(

`/accommodation-bookings/${id}`

);


loadBookings();


}

catch(error){

console.log(error);

}


};










if(loading){


return (

<div

className="
min-h-screen
flex
items-center
justify-center
"

>


<h1

className="
text-3xl
font-bold
"

>

Loading bookings...

</h1>


</div>

);


}









return (

<div

className="
min-h-screen
bg-gray-50
py-16
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
text-5xl
font-extrabold
mb-12
text-center
"

>

My Bookings

</h1>









{/* TRANSPORT BOOKINGS */}



<section>


<h2

className="
text-3xl
font-bold
mb-6
flex
items-center
gap-3
"

>


<Bus/>

Transport Bookings


</h2>







{

transportBookings.length===0

?


<p

className="
text-gray-500
mb-10
"

>

No transport bookings yet.

</p>


:


<div

className="
grid
md:grid-cols-2
lg:grid-cols-3
gap-8
mb-14
"

>



{

transportBookings.map(

(booking)=>(



<div

key={booking.id}

className="
bg-white
rounded-3xl
shadow-lg
p-6
"

>



<h3

className="
text-xl
font-bold
"

>

{booking.transport?.transportNumber}

</h3>





<p className="mt-3">

Route:

<b>

{" "}

{booking.transport?.source}

→

{booking.transport?.destination}

</b>

</p>






<p className="flex gap-2 mt-3">

<Calendar size={18}/>

{booking.bookingDate}

</p>





<p className="flex gap-2 mt-3">

<IndianRupee size={18}/>

{booking.amount}

</p>









<button

onClick={()=>cancelTransport(booking.id)}

className="
mt-6
w-full
bg-red-500
text-white
py-3
rounded-xl
font-bold
flex
justify-center
items-center
gap-2
hover:bg-red-600
"

>


<XCircle size={18}/>

Cancel Booking


</button>






</div>



)

)



}


</div>


}


</section>









{/* ACCOMMODATION BOOKINGS */}





<section>


<h2

className="
text-3xl
font-bold
mb-6
flex
items-center
gap-3
"

>


<Hotel/>

Accommodation Bookings


</h2>








{

accommodationBookings.length===0

?


<p

className="
text-gray-500
"

>

No accommodation bookings yet.

</p>



:


<div

className="
grid
md:grid-cols-2
lg:grid-cols-3
gap-8
"

>


{

accommodationBookings.map(

(booking)=>(



<div

key={booking.id}

className="
bg-white
rounded-3xl
shadow-lg
p-6
"

>



<h3

className="
text-xl
font-bold
"

>

{booking.accommodation?.name}

</h3>





<p className="mt-3">

City:

<b>

{" "}

{booking.accommodation?.city}

</b>

</p>






<p className="mt-3">

Rooms:

<b>

{" "}

{booking.roomsBooked}

</b>

</p>





<p className="mt-3">

Check In:

<b>

{" "}

{booking.checkInDate}

</b>

</p>





<p className="mt-3">

Check Out:

<b>

{" "}

{booking.checkOutDate}

</b>

</p>









<button

onClick={()=>cancelAccommodation(booking.id)}

className="
mt-6
w-full
bg-red-500
text-white
py-3
rounded-xl
font-bold
flex
justify-center
items-center
gap-2
hover:bg-red-600
"

>


<XCircle size={18}/>

Cancel Booking


</button>







</div>



)


)


}


</div>


}



</section>







</div>


</div>


);


}



export default MyBookings;