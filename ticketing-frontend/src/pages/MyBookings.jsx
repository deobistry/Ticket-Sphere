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

const [selectedBooking,setSelectedBooking]=useState(null);


const [loading,setLoading]=useState(true);





// DATE FORMATTERS

const formatDate = (date) => {

    if(!date) return "N/A";

    return new Date(date).toLocaleDateString(
        "en-IN"
    );

};



const formatDateTime = (date) => {

    if(!date) return "N/A";

    return new Date(date).toLocaleString(
        "en-IN"
    );

};









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

    transportResponse.data

    .filter(
        booking =>
        booking.status !== "CANCELLED"
    )

    .map(booking => ({

        ...booking,

        transport: {

            transportNumber:
            booking.transportNumber,

            source:
            booking.source,

            destination:
            booking.destination,

            departureTime:
            booking.departureTime

        },


        amount:
        booking.totalAmount

    }))

);






setAccommodationBookings(

    accommodationResponse.data

    .filter(
        booking =>
        booking.status !== "CANCELLED"
    )

    .map(booking => ({

        ...booking,


        accommodation: {

            name:
            booking.accommodationName,

            city:
            booking.city

        },


        amount:
        booking.totalAmount


    }))

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





{

selectedBooking && (

<div

className="
fixed
inset-0
bg-black/50
flex
items-center
justify-center
z-50
p-4
"

onClick={()=>setSelectedBooking(null)}

>


<div

className="
bg-white
rounded-3xl
shadow-2xl
max-w-xl
w-full
p-6
relative
"

onClick={(e)=>e.stopPropagation()}

>




<div className="
flex
justify-between
items-start
gap-4
mb-5
">


<div>


<h3 className="
text-2xl
font-bold
text-gray-900
">


{

selectedBooking.type === "TRANSPORT"

?

selectedBooking.data.transport?.transportNumber

:

selectedBooking.data.accommodation?.name

}


</h3>



<p className="
text-sm
text-gray-500
mt-1
">

Booking details

</p>


</div>




<button

onClick={()=>setSelectedBooking(null)}

className="
text-gray-500
hover:text-gray-700
"

>

<XCircle size={22}/>

</button>


</div>









{

selectedBooking.type === "TRANSPORT"

?

(


<div className="
space-y-3
text-gray-700
">


<p>

<span className="font-semibold">

Route:

</span>{" "}

{selectedBooking.data.transport?.source}

→

{selectedBooking.data.transport?.destination}

</p>



<p>

<span className="font-semibold">

Booking Date:

</span>{" "}

{formatDateTime(
selectedBooking.data.bookingDate
)}

</p>




<p>

<span className="font-semibold">

Seats:

</span>{" "}

{selectedBooking.data.seatsBooked}

</p>



<p>

<span className="font-semibold">

Status:

</span>{" "}

{selectedBooking.data.status}

</p>



<p>

<span className="font-semibold">

Amount:

</span>{" "}

₹ {selectedBooking.data.amount}

</p>




<p>

<span className="font-semibold">

Travel Date:

</span>{" "}

{formatDate(
selectedBooking.data.transport?.departureTime
)}

</p>



</div>


)

:


(


<div className="
space-y-3
text-gray-700
">


<p>

<span className="font-semibold">

City:

</span>{" "}

{selectedBooking.data.accommodation?.city}

</p>



<p>

<span className="font-semibold">

Check In:

</span>{" "}

{formatDate(
selectedBooking.data.checkInDate
)}

</p>



<p>

<span className="font-semibold">

Check Out:

</span>{" "}

{formatDate(
selectedBooking.data.checkOutDate
)}

</p>




<p>

<span className="font-semibold">

Rooms Booked:

</span>{" "}

{selectedBooking.data.roomsBooked}

</p>




<p>

<span className="font-semibold">

Status:

</span>{" "}

{selectedBooking.data.status}

</p>




<p>

<span className="font-semibold">

Amount:

</span>{" "}

₹ {selectedBooking.data.amount}

</p>



</div>


)


}





</div>


</div>


)

}









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

<p className="
text-gray-500
mb-10
">

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
cursor-pointer
transition-all
duration-300
hover:-translate-y-1
hover:shadow-2xl
"

onClick={()=>
setSelectedBooking({
type:"TRANSPORT",
data:booking
})
}

>


<div className="
flex
items-start
justify-between
gap-3
">


<h3 className="
text-xl
font-bold
">

{booking.transport?.transportNumber}

</h3>


<span className="
px-3
py-1
rounded-full
bg-green-100
text-green-700
text-xs
font-bold
">

CONFIRMED

</span>


</div>





<div className="
mt-4
space-y-3
text-sm
text-gray-700
">


<p>

<span className="font-semibold">

Route:

</span>{" "}

{booking.transport?.source}

→

{booking.transport?.destination}

</p>




<p>

<span className="font-semibold">

Amount:

</span>{" "}

₹ {booking.amount}

</p>




<p>

<span className="font-semibold">

Travel Date:

</span>{" "}

{formatDate(
booking.transport?.departureTime
)}

</p>



</div>






<p className="
flex
gap-2
mt-3
">


<Calendar size={18}/>


{formatDate(
booking.bookingDate
)}


</p>





<p className="
flex
gap-2
mt-3
">


<IndianRupee size={18}/>


{booking.amount}


</p>





<button

onClick={(e)=>{

e.stopPropagation();

cancelTransport(
booking.id
);

}}

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
cursor-pointer
transition-all
duration-300
hover:-translate-y-1
hover:shadow-2xl
"

onClick={()=>

setSelectedBooking({

type:"ACCOMMODATION",

data:booking

})

}

>





<div className="
flex
items-start
justify-between
gap-3
">


<h3

className="
text-xl
font-bold
"

>

{booking.accommodation?.name}

</h3>



<span

className="
px-3
py-1
rounded-full
bg-green-100
text-green-700
text-xs
font-bold
"

>

CONFIRMED

</span>


</div>









<div

className="
mt-4
space-y-3
text-sm
text-gray-700
"

>


<p>

<span className="font-semibold">

City:

</span>{" "}

{booking.accommodation?.city}

</p>




<p>

<span className="font-semibold">

Amount:

</span>{" "}

₹ {booking.amount}

</p>



</div>









<p className="
mt-3
">

Rooms:

<b>

{" "}

{booking.roomsBooked}

</b>

</p>









<p className="
mt-3
">

Check In:

<b>

{" "}

{formatDate(
booking.checkInDate
)}

</b>

</p>








<p className="
mt-3
">

Check Out:

<b>

{" "}

{formatDate(
booking.checkOutDate
)}

</b>

</p>









<p className="
flex
gap-2
mt-3
">


<Calendar size={18}/>


{formatDate(
booking.bookingDate
)}


</p>









<p className="
flex
gap-2
mt-3
">


<IndianRupee size={18}/>


{booking.amount}


</p>









<button

onClick={(e)=>{

e.stopPropagation();

cancelAccommodation(
booking.id
);

}}

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