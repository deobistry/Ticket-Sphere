import {
    useLocation,
    useNavigate
} from "react-router-dom";


import {
    useState
} from "react";


import api from "../api/axios";




function AccommodationBooking(){


const location = useLocation();

const navigate = useNavigate();



const accommodation =
location.state?.accommodation;




const [rooms,setRooms] =
useState(1);



const [dates,setDates] =
useState({

    checkInDate:"",
    checkOutDate:""

});








if(!accommodation){


return (

<div className="
text-center
mt-20
">


<h1 className="
text-3xl
font-bold
">

No accommodation selected

</h1>


</div>

);


}









const changeDate=(e)=>{


setDates({

    ...dates,

    [e.target.name]:
    e.target.value

});


};









const bookAccommodation = async()=>{


try{


const response = await api.post(

    "/accommodation-bookings",

    {

        accommodationId:
        accommodation.id,


        roomsBooked:
        Number(rooms),


        checkInDate:
        dates.checkInDate,


        checkOutDate:
        dates.checkOutDate

    }

);





const booking = response.data;





navigate(

    "/payment",

    {

        state:{

            bookingId:
            booking.id,


            amount:
            booking.totalAmount,


            type:
            "ACCOMMODATION"

        }

    }

);



}

catch(error){


console.error(

    "Accommodation booking failed",

    error

);


alert(

    "Unable to complete booking"

);


}



};









return (

<div className="
min-h-screen
bg-gray-50
py-16
">


<div className="
max-w-xl
mx-auto
bg-white
rounded-3xl
shadow-xl
p-8
">



<h1 className="
text-3xl
font-bold
mb-6
">

Confirm Stay

</h1>







<h2 className="
text-2xl
font-bold
">

{accommodation.name}

</h2>





<p className="
text-gray-500
mt-2
">

{accommodation.city}

</p>









<div className="
mt-6
space-y-4
">





<div>


<label className="
font-semibold
">

Check In

</label>


<input

type="date"

name="checkInDate"

onChange={changeDate}

className="
border
rounded-xl
p-3
w-full
mt-2
"

/>


</div>








<div>


<label className="
font-semibold
">

Check Out

</label>


<input

type="date"

name="checkOutDate"

onChange={changeDate}

className="
border
rounded-xl
p-3
w-full
mt-2
"

/>


</div>







<div>


<label className="
font-semibold
">

Rooms

</label>



<input

type="number"

min="1"

value={rooms}

onChange={
(e)=>setRooms(e.target.value)
}

className="
border
rounded-xl
p-3
w-full
mt-2
"

/>



</div>





</div>









<div className="
mt-8
flex
justify-between
">


<span>

Price Per Night

</span>


<span className="
font-bold
">

₹ {accommodation.pricePerNight}

</span>


</div>









<button

onClick={bookAccommodation}

className="
mt-8
w-full
bg-indigo-600
text-white
py-3
rounded-xl
font-bold
hover:bg-indigo-700
transition
"

>

Proceed To Payment

</button>





</div>


</div>


);


}


export default AccommodationBooking;