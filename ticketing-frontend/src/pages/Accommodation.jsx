import {
    useState
} from "react";


import {
    useNavigate
} from "react-router-dom";


import {
    Search,
    Hotel
} from "lucide-react";


import api from "../api/axios";



function Accommodation(){


const navigate = useNavigate();



const [form,setForm]=useState({

    city:"",
    type:""

});



const [results,setResults]=useState([]);


const [loading,setLoading]=useState(false);







const change=(e)=>{


setForm({

    ...form,

    [e.target.name]:
    e.target.value

});


};








const search=async(e)=>{


e.preventDefault();


try{


setLoading(true);



const response = await api.get(

"/accommodations/search",

{

params:{

city:form.city,

type:form.type

}

}

);



setResults(response.data);



}

catch(error){

console.log(error);

}

finally{

setLoading(false);

}


};









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
text-center
mb-10
"

>

Find Your Perfect Stay

</h1>









<form

onSubmit={search}

className="
bg-white
shadow-xl
rounded-3xl
p-8
grid
grid-cols-1
md:grid-cols-3
gap-5
"

>





<input

name="city"

placeholder="Select City"

value={form.city}

onChange={change}

className="
border
rounded-xl
p-4
"

/>








<select

name="type"

value={form.type}

onChange={change}

className="
border
rounded-xl
p-4
"

>


<option value="">

Select Accommodation Type

</option>


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








<button

className="
bg-blue-600
text-white
rounded-xl
font-bold
flex
items-center
justify-center
gap-2
hover:bg-blue-700
"

>


<Search size={20}/>

Search


</button>





</form>









<div

className="
mt-12
grid
grid-cols-1
md:grid-cols-2
lg:grid-cols-3
gap-8
"

>



{

loading

?

<h2>

Loading...

</h2>


:


results.map(

(item)=>(



<div

key={item.id}

className="
bg-white
rounded-3xl
shadow-lg
p-6
hover:shadow-2xl
transition
"

>



<div

className="
h-40
rounded-2xl
bg-gradient-to-br
from-purple-500
to-blue-600
flex
items-center
justify-center
text-white
mb-5
"

>

<Hotel size={70}/>

</div>







<h2

className="
text-2xl
font-bold
"

>

{item.name}

</h2>




<p className="text-gray-500">

{item.city}

</p>




<p className="mt-2">

Type:

<b>

{" "}

{item.type}

</b>

</p>





<p className="
text-blue-600
font-bold
text-xl
mt-4
">

₹ {item.pricePerNight}

 / night

</p>







<button

onClick={()=>navigate(

"/accommodation-booking",

{

state:{

accommodation:item

}

}

)}

className="
mt-6
w-full
bg-indigo-600
text-white
py-3
rounded-xl
font-bold
hover:bg-indigo-700
"

>

Book Now

</button>




</div>


)

)


}



</div>



</div>


</div>


);


}


export default Accommodation;