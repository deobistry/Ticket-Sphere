import {
    useLocation,
    useNavigate
} from "react-router-dom";


import {
    useState
} from "react";


import {
    CreditCard,
    CheckCircle
} from "lucide-react";


import api from "../api/axios";



function Payment(){


const location = useLocation();

const navigate = useNavigate();



const {
    bookingId,
    amount,
    type
} = location.state || {};



const [paymentMethod,setPaymentMethod] =
    useState("CARD");


const [processing,setProcessing] =
    useState(false);


const [success,setSuccess] =
    useState(false);


const [transaction,setTransaction] =
    useState(null);







if(!bookingId || !amount){


return (

<div className="
text-center
mt-20
">


<h1 className="
text-3xl
font-bold
">

No payment details found

</h1>


</div>

);


}









const processPayment = async()=>{


try{


setProcessing(true);





const response = await api.post(

    "/payments",

    {

        bookingId: bookingId,

        bookingType: type,

        amount: amount,

        paymentMethod: paymentMethod

    }

);





setTransaction(

    response.data

);




setSuccess(true);



}

catch(error){


console.error(
    "Payment failed",
    error
);


alert(
    "Payment failed"
);


}

finally{


setProcessing(false);


}


};








const continueBooking=()=>{


navigate("/bookings");


};









return (

<div className="
min-h-screen
bg-gray-50
py-16
">


<div className="
max-w-lg
mx-auto
bg-white
rounded-3xl
shadow-xl
p-8
">


{


success


?


<div className="
text-center
">


<CheckCircle

size={80}

className="
mx-auto
text-green-500
"

/>



<h1 className="
text-3xl
font-bold
mt-5
">

Payment Successful

</h1>




<p className="
text-gray-500
mt-3
">

Your booking has been confirmed.

</p>





{

transaction &&

<div className="
bg-green-50
rounded-xl
p-4
mt-6
text-left
">


<p>

Transaction ID:

</p>


<p className="
font-bold
break-all
">

{transaction.transactionId}

</p>


</div>


}







<button

onClick={continueBooking}

className="
mt-8
w-full
bg-blue-600
text-white
py-3
rounded-xl
font-bold
"

>

View My Bookings

</button>



</div>



:


<>


<h1 className="
text-3xl
font-bold
mb-8
flex
items-center
gap-3
">


<CreditCard/>

Payment


</h1>







<div className="
bg-blue-50
rounded-2xl
p-5
mb-6
">


<p className="
text-gray-600
">

Booking Type

</p>


<h2 className="
font-bold
text-xl
">

{type}

</h2>


</div>







<div className="
flex
justify-between
text-xl
font-bold
mb-8
">


<span>

Total Amount

</span>


<span className="
text-blue-600
">

₹ {amount}

</span>


</div>







<div className="
mb-6
">


<label className="
font-semibold
">

Payment Method

</label>



<select

value={paymentMethod}

onChange={
    e=>setPaymentMethod(e.target.value)
}

className="
border
rounded-xl
p-3
w-full
mt-2
"

>


<option value="CARD">

Card

</option>


<option value="UPI">

UPI

</option>


<option value="NET_BANKING">

Net Banking

</option>


<option value="CASH">

Cash

</option>



</select>


</div>







{

paymentMethod==="CARD"

&&

<>

<input

placeholder="Card Number"

className="
border
rounded-xl
p-4
w-full
mb-4
"

/>





<div className="
grid
grid-cols-2
gap-4
">


<input

placeholder="Expiry"

className="
border
rounded-xl
p-4
"

/>


<input

placeholder="CVV"

className="
border
rounded-xl
p-4
"

/>


</div>

</>


}








<button

disabled={processing}

onClick={processPayment}

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


{

processing

?

"Processing Payment..."

:

"Pay Now"

}


</button>




</>

}



</div>


</div>


);


}


export default Payment;