import {
    useLocation,
    useNavigate
} from "react-router-dom";


import {
    useState
} from "react";


import api from "../api/axios";



function TransportBooking(){


const location = useLocation();

const navigate = useNavigate();



const transport =
location.state?.transport;



const [seats,setSeats] =
useState(1);





if(!transport){


return (

<div className="
text-center
mt-20
">


<h1 className="
text-3xl
font-bold
">

No transportation selected

</h1>


</div>

);


}









const book = async()=>{


try{


const response = await api.post(

    "/transport-bookings",

    {

        transportationId:
        transport.id,


        seatsBooked:
        Number(seats)

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
            "TRANSPORT"

        }

    }

);



}

catch(error){


console.error(

    "Booking failed",

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

Confirm Journey

</h1>





<h2 className="
text-xl
font-semibold
">

{transport.transportNumber}

</h2>





<p className="
text-gray-500
">

{transport.source}

&nbsp; →

&nbsp;

{transport.destination}

</p>









<div className="
mt-6
">


<label className="
font-semibold
">

Number of Seats

</label>



<input


type="number"


min="1"


value={seats}



onChange={

(e)=>

setSeats(
    e.target.value
)

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









<div className="
mt-8
flex
justify-between
">


<span>

Estimated Total

</span>


<span className="
font-bold
text-blue-600
">

₹ {transport.price * seats}

</span>


</div>








<button

onClick={book}

className="
mt-8
w-full
bg-blue-600
text-white
py-3
rounded-xl
font-bold
hover:bg-blue-700
transition
"

>

Proceed To Payment

</button>





</div>


</div>


);


}


export default TransportBooking;