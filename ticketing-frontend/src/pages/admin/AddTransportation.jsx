import {
    useState
} from "react";


import api from "../../api/axios";


import {
    useNavigate
} from "react-router-dom";



function AddTransportation(){


const navigate = useNavigate();



const [form,setForm] = useState({

    transportNumber:"",

    type:"BUS",

    operatorName:"",

    source:"",

    destination:"",

    departureTime:"",

    arrivalTime:"",

    duration:"",

    totalSeats:"",

    availableSeats:"",

    price:""

});





const change=(e)=>{


setForm({

    ...form,

    [e.target.name]:e.target.value

});


};








const submit=async(e)=>{


e.preventDefault();



try{


await api.post(

    "/transportation",

    {

        ...form,

        totalSeats:Number(form.totalSeats),

        availableSeats:Number(form.availableSeats),

        price:Number(form.price)

    }

);




alert(
    "Transportation added successfully"
);



navigate("/admin");



}

catch(error){


console.error(
    error
);


alert(
    "Failed to add transportation"
);


}


};








return (

<div className="
min-h-screen
bg-gray-100
py-12
">


<div className="
max-w-3xl
mx-auto
bg-white
rounded-3xl
shadow-xl
p-8
">


<h1 className="
text-4xl
font-bold
mb-8
text-center
">

Add Transportation

</h1>





<form

onSubmit={submit}

className="
grid
grid-cols-1
md:grid-cols-2
gap-5
"

>






<input

name="transportNumber"

placeholder="Transport Number"

value={form.transportNumber}

onChange={change}

className="
border
rounded-xl
p-3
"

/>








<select

name="type"

value={form.type}

onChange={change}

className="
border
rounded-xl
p-3
"

>


<option value="BUS">

Bus

</option>


<option value="TRAIN">

Train

</option>


<option value="FLIGHT">

Flight

</option>


</select>










<input

name="operatorName"

placeholder="Operator Name"

value={form.operatorName}

onChange={change}

className="
border
rounded-xl
p-3
"

/>







<input

name="source"

placeholder="Source"

value={form.source}

onChange={change}

className="
border
rounded-xl
p-3
"

/>







<input

name="destination"

placeholder="Destination"

value={form.destination}

onChange={change}

className="
border
rounded-xl
p-3
"

/>







<input

name="departureTime"

type="datetime-local"

value={form.departureTime}

onChange={change}

className="
border
rounded-xl
p-3
"

/>







<input

name="arrivalTime"

type="datetime-local"

value={form.arrivalTime}

onChange={change}

className="
border
rounded-xl
p-3
"

/>







<input

name="duration"

placeholder="Duration"

value={form.duration}

onChange={change}

className="
border
rounded-xl
p-3
"

/>







<input

name="totalSeats"

type="number"

placeholder="Total Seats"

value={form.totalSeats}

onChange={change}

className="
border
rounded-xl
p-3
"

/>







<input

name="availableSeats"

type="number"

placeholder="Available Seats"

value={form.availableSeats}

onChange={change}

className="
border
rounded-xl
p-3
"

/>







<input

name="price"

type="number"

placeholder="Price"

value={form.price}

onChange={change}

className="
border
rounded-xl
p-3
"

/>







<button

type="submit"

className="
md:col-span-2
bg-blue-600
text-white
py-3
rounded-xl
font-bold
hover:bg-blue-700
transition
"

>

Add Transportation

</button>





</form>


</div>


</div>

);


}


export default AddTransportation;