import {
    useState
} from "react";


import {
    useNavigate
} from "react-router-dom";


import {
    Bus,
    Search
} from "lucide-react";


import api from "../api/axios";




function Transportation(){


const navigate = useNavigate();



const [form,setForm]=useState({

    source:"",
    destination:""

});



const [results,setResults]=useState([]);


const [filteredResults,setFilteredResults]=useState([]);


const [types,setTypes]=useState([]);


const [selectedType,setSelectedType]=useState("ALL");


const [loading,setLoading]=useState(false);





const formatTravelDate=(value)=>{

    if(!value) return "N/A";

    const parsedDate = new Date(value);

    if(Number.isNaN(parsedDate.getTime())) return value;

    return parsedDate.toISOString().split("T")[0];

};


const formatTravelTime=(value)=>{

    if(!value) return "N/A";

    const parsedDate = new Date(value);

    if(Number.isNaN(parsedDate.getTime())) return value;

    return parsedDate.toLocaleTimeString([], {

        hour: "2-digit",

        minute: "2-digit",

        hour12: false

    });

};


const applyResults=(data)=>{

    setResults(data);

    setFilteredResults(data);

    const availableTypes = [

        "ALL",

        ...new Set(
            data.map(
                item=>item.type
            )
        )

    ];

    setTypes(availableTypes);

    setSelectedType("ALL");

};











const change=(e)=>{


setForm({

    ...form,

    [e.target.name]:e.target.value

});


};







const search=async(e)=>{


e.preventDefault();


try{


setLoading(true);



const response = await api.get(

"/transportation/search",

{

params:form

}

);



const data = response.data;


if(form.travelDate && data.length === 0){

    const shouldShowAll = window.confirm(

        "Do you want to see all the available options?"

    );


    if(shouldShowAll){

        await showAllAvailableOptions();

        return;

    }


    setResults([]);

    setFilteredResults([]);

    setTypes(["ALL"]);

    setSelectedType("ALL");

    return;

}


applyResults(data);





const availableTypes = [

    "ALL",

    ...new Set(
        data.map(
            item=>item.type
        )
    )

];



setTypes(availableTypes);


setSelectedType("ALL");



}

catch(error){

console.log(error);

}

finally{


setLoading(false);


}


};







const filterType=(type)=>{


setSelectedType(type);



if(type==="ALL"){

    setFilteredResults(results);

}

else{


    setFilteredResults(

        results.filter(

            item=>item.type===type

        )

    );

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

Find Your Journey

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
w-full
"

>




<input

name="source"

placeholder="From"

value={form.source}

onChange={change}

className="
border
rounded-xl
p-4
outline-none
"

/>






<input

name="destination"

placeholder="To"

value={form.destination}

onChange={change}

className="
border
rounded-xl
p-4
outline-none
"

/>













<button

className="
bg-blue-700
text-white
rounded-xl
font-bold
flex
items-center
justify-center
gap-2
hover:bg-blue-800
transition
"

>


<Search size={20}/>

Search

</button>





</form>









{

types.length > 0 && (


<div

className="
mt-10
flex
justify-center
gap-4
flex-wrap
"

>


{

types.map(

(type)=>(


<button

key={type}

onClick={()=>filterType(type)}

className={

`
px-6
py-3
rounded-xl
font-bold
transition
${
selectedType===type

?

"bg-blue-700 text-white"

:

"bg-white shadow text-gray-700"

}
`

}

>


{type}


</button>


)

)


}


</div>


)

}









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


filteredResults.length===0

?


<div

className="
text-center
col-span-full
space-y-4
"

>

<h2

className="
text-xl
font-bold
text-gray-500
"

>

No transportation available for this route

</h2>




</div>


:


filteredResults.map(

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
from-blue-500
to-indigo-700
flex
items-center
justify-center
text-white
mb-5
"

>

<Bus size={70}/>


</div>









<h2

className="
text-2xl
font-bold
"

>

{item.transportNumber}

</h2>





<p

className="
text-gray-500
mt-2
"

>

{item.source}

&nbsp; → &nbsp;

{item.destination}

</p>









<div

className="
mt-5
space-y-2
"

>


<p>

Type:

<b>

{" "}

{item.type}

</b>

</p>



<p>

Travel Date:

<b>

{" "}

{formatTravelDate(item.departureTime)}

</b>

</p>



<p>

Departure Time:

<b>

{" "}

{formatTravelTime(item.departureTime)}

</b>

</p>



<p>

Seats Available:

<b>

{" "}

{item.availableSeats}

</b>

</p>



<p

className="
text-blue-600
font-bold
text-xl
"

>

₹ {item.price}

</p>



</div>









<button


onClick={()=>navigate(

"/transport-booking",

{

state:{

transport:item

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
transition
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



export default Transportation;