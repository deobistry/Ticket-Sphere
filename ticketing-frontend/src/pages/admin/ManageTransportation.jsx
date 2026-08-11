import {
    useEffect,
    useState
} from "react";


import api from "../../api/axios";


import {
    Link,
    useNavigate
} from "react-router-dom";



function ManageTransportation(){

const navigate = useNavigate();
const [transportations,setTransportations] =
    useState([]);



const [loading,setLoading] =
    useState(true);



const [deleteId,setDeleteId] =
    useState(null);









const loadTransportation = async()=>{


    try{


        setLoading(true);



        const response =
            await api.get("/transportation");



        setTransportations(
            response.data
        );


    }

    catch(error){


        console.error(
            "Failed loading transportation:",
            error.response?.data || error.message
        );


    }


    finally{


        setLoading(false);


    }


};









useEffect(()=>{


    loadTransportation();


},[]);









const deleteTransportation = async()=>{


    try{


        console.log(
            "DELETING TRANSPORTATION:",
            deleteId
        );



        await api.delete(

            `/transportation/${deleteId}`

        );





        setTransportations(

            (previous)=>

                previous.filter(

                    (item)=>

                        item.id !== deleteId

                )

        );



        alert(
            "Transportation deleted successfully"
        );



        setDeleteId(null);



    }


    catch(error){


        console.error(

            "DELETE FAILED:",

            error.response?.data || error.message

        );



        alert(
            "Unable to delete transportation"
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
max-w-7xl
mx-auto
px-6
">





<div className="
flex
justify-between
items-center
mb-10
">


<h1 className="
text-4xl
font-extrabold
">

Manage Transportation

</h1>






<Link

to="/admin/transportation"

className="
bg-blue-600
text-white
px-5
py-3
rounded-xl
hover:bg-blue-700
transition
"

>

+ Add Transportation

</Link>



</div>









{

loading

?

<h2 className="
text-center
text-xl
">

Loading transportation...

</h2>



:


transportations.length === 0


?

<h2 className="
text-center
text-xl
text-gray-600
">

No transportation available

</h2>



:



<div className="
grid
grid-cols-1
md:grid-cols-2
lg:grid-cols-3
gap-8
">


{

transportations.map(

(item)=>(


<div

key={item.id}

className="
bg-white
rounded-3xl
shadow-lg
p-6
hover:shadow-xl
transition
"

>



<h2 className="
text-2xl
font-bold
text-blue-600
mb-4
">

{item.transportNumber}

</h2>







<p>

Type:

{" "}

<b>

{item.type}

</b>

</p>







<p>

Operator:

{" "}

<b>

{item.operatorName}

</b>

</p>







<p>

Route:

{" "}

<b>

{item.source}

</b>

{" → "}

<b>

{item.destination}

</b>

</p>







<p>

Available Seats:

{" "}

<b>

{item.availableSeats}

</b>

</p>







<p>

Price:

{" "}

<b>

₹ {item.price}

</b>

</p>









<button

type="button"

onClick={()=>setDeleteId(item.id)}

className="
mt-6
w-full
bg-red-500
text-white
py-3
rounded-xl
font-semibold
hover:bg-red-600
transition
"

>
    

Delete Transportation

</button>





</div>


)

)


}



</div>


}





{

deleteId &&


<div className="
fixed
inset-0
bg-black/50
flex
items-center
justify-center
z-50
">


<div className="
bg-white
rounded-2xl
p-8
shadow-xl
w-96
text-center
">


<h2 className="
text-2xl
font-bold
mb-4
">

Delete Transportation?

</h2>





<p className="
text-gray-600
mb-6
">

This action cannot be undone.

</p>







<div className="
flex
justify-center
gap-4
">



<button

onClick={()=>setDeleteId(null)}

className="
px-5
py-3
rounded-xl
bg-gray-300
hover:bg-gray-400
"

>

Cancel

</button>








<button

onClick={deleteTransportation}

className="
px-5
py-3
rounded-xl
bg-red-600
text-white
hover:bg-red-700
"

>

Delete

</button>



</div>



</div>


</div>


}





</div>


</div>


);


}


export default ManageTransportation;