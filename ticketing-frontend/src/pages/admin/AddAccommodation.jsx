import {
    useState
} from "react";


import api from "../../api/axios";


import {
    useNavigate
} from "react-router-dom";



function AddAccommodation(){


const navigate = useNavigate();




const [form,setForm] = useState({

    name:"",

    type:"HOTEL",

    city:"",

    address:"",

    description:"",

    rating:"",

    pricePerNight:"",

    availableRooms:""

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

    "/accommodation",

    {

        ...form,

        rating:Number(form.rating),

        pricePerNight:Number(form.pricePerNight),

        availableRooms:Number(form.availableRooms)

    }

);




alert(
    "Accommodation added successfully"
);



navigate("/admin");



}

catch(error){


console.error(error);


alert(
    "Failed to add accommodation"
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
text-center
mb-8
">

Add Accommodation

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

name="name"

placeholder="Hotel Name"

value={form.name}

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


<option value="HOTEL">

Hotel

</option>


<option value="RESORT">

Resort

</option>


<option value="HOSTEL">

Hostel

</option>


<option value="VILLA">

Villa

</option>


</select>









<input

name="city"

placeholder="City"

value={form.city}

onChange={change}

className="
border
rounded-xl
p-3
"

/>









<input

name="address"

placeholder="Address"

value={form.address}

onChange={change}

className="
border
rounded-xl
p-3
"

/>









<textarea

name="description"

placeholder="Description"

value={form.description}

onChange={change}

className="
border
rounded-xl
p-3
md:col-span-2
"

/>









<input

name="rating"

type="number"

step="0.1"

placeholder="Rating"

value={form.rating}

onChange={change}

className="
border
rounded-xl
p-3
"

/>









<input

name="pricePerNight"

type="number"

placeholder="Price per night"

value={form.pricePerNight}

onChange={change}

className="
border
rounded-xl
p-3
"

/>









<input

name="availableRooms"

type="number"

placeholder="Available Rooms"

value={form.availableRooms}

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
bg-blue-700
text-white
py-3
rounded-xl
font-bold
hover:bg-blue-800
transition
"

>

Add Accommodation

</button>






</form>


</div>


</div>

);


}


export default AddAccommodation;