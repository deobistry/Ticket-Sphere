import {
    Plane,
    Hotel,
    Ticket,
    CreditCard
} from "lucide-react";


import {
    useNavigate
} from "react-router-dom";


import FeatureCard from "../components/FeatureCard";
import Footer from "../components/Footer";



function Home(){


const navigate = useNavigate();



return (

<div className="min-h-screen bg-gray-50">


{/* HERO */}

<section
className="
bg-gradient-to-r
from-blue-600
via-indigo-600
to-purple-700
text-white
py-24
"
>


<div
className="
max-w-7xl
mx-auto
px-6
text-center
"
>


<h1
className="
text-6xl
font-extrabold
leading-tight
mb-6
"
>

Explore The World
<br/>
Without Limits

</h1>


<p
className="
text-xl
max-w-3xl
mx-auto
opacity-90
"
>

Book transportation, accommodation and manage your complete journey in one platform.

</p>


</div>

</section>






{/* QUICK LINKS */}

<section
className="
max-w-7xl
mx-auto
px-6
py-20
"
>


<div
className="
grid
grid-cols-1
md:grid-cols-3
gap-8
"
>





{/* TRANSPORT */}

<div

onClick={() => navigate("/transportation")}

className="
bg-white
rounded-3xl
shadow-lg
p-8
text-center
hover:-translate-y-2
transition
cursor-pointer
"

>

<Plane
size={50}
className="mx-auto text-blue-600 mb-5"
/>


<h3 className="
text-2xl
font-bold
">

Transport

</h3>


<p className="
mt-3
text-gray-600
">

Search and reserve buses, trains and flights easily.

</p>


</div>







{/* HOTELS */}

<div

onClick={() => navigate("/accommodation")}

className="
bg-white
rounded-3xl
shadow-lg
p-8
text-center
hover:-translate-y-2
transition
cursor-pointer
"

>

<Hotel
size={50}
className="mx-auto text-blue-600 mb-5"
/>


<h3 className="
text-2xl
font-bold
">

Hotels

</h3>


<p className="
mt-3
text-gray-600
">

Find comfortable stays at your destination.

</p>


</div>








{/* BOOKINGS */}

<div

onClick={() => navigate("/bookings")}

className="
bg-white
rounded-3xl
shadow-lg
p-8
text-center
hover:-translate-y-2
transition
cursor-pointer
"

>

<Ticket
size={50}
className="mx-auto text-blue-600 mb-5"
/>


<h3
className="
text-2xl
font-bold
"
>

Bookings

</h3>


<p
className="
mt-3
text-gray-600
"
>

Manage all your travel bookings.

</p>


</div>





</div>


</section>








{/* FEATURES */}

<section
className="
py-20
bg-gray-100
"
>


<h2
className="
text-5xl
font-extrabold
text-center
mb-14
"
>

Everything You Need For Travel

</h2>




<div
className="
max-w-7xl
mx-auto
px-6
grid
grid-cols-1
sm:grid-cols-2
lg:grid-cols-4
gap-8
"
>


<FeatureCard
icon={<Plane/>}
title="Transportation"
text="Search and reserve travel options."
/>


<FeatureCard
icon={<Hotel/>}
title="Accommodation"
text="Book comfortable stays."
/>


<FeatureCard
icon={<Ticket/>}
title="Bookings"
text="Manage your bookings."
/>


<FeatureCard
icon={<CreditCard/>}
title="Payments"
text="Simple simulated payments."
/>



</div>


</section>







{/* DESTINATIONS */}

<section
className="
py-20
max-w-7xl
mx-auto
px-6
"
>


<h2
className="
text-5xl
font-extrabold
text-center
mb-12
"
>

Popular Destinations

</h2>



<div
className="
grid
grid-cols-2
md:grid-cols-5
gap-6
"
>


{
[
"Mumbai",
"Delhi",
"Goa",
"Jaipur",
"Bangalore"
]
.map(
(city)=>


<div
key={city}
className="
h-40
rounded-3xl
bg-gradient-to-br
from-blue-500
to-indigo-600
flex
items-center
justify-center
text-white
text-xl
font-bold
shadow-xl
"
>

{city}

</div>


)

}



</div>


</section>






<Footer/>


</div>

);


}


export default Home;