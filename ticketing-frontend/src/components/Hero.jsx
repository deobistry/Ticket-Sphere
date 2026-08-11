import {
    Plane,
    Hotel,
    Ticket
} from "lucide-react";


function Hero(){

return (

<section
className="
bg-gradient-to-r
from-blue-700
via-indigo-700
to-purple-700
text-white
pt-24
pb-16
px-6
"
>


<div
className="
max-w-6xl
mx-auto
text-center
"
>


<h1
className="
text-5xl
md:text-6xl
font-extrabold
leading-tight
"
>

Explore The World
<br/>
Without Limits

</h1>


<p
className="
mt-6
text-lg
md:text-xl
max-w-3xl
mx-auto
text-blue-100
"
>

Book transportation, accommodation and manage your complete journey in one platform.

</p>




<div

className="
mt-10
bg-white
rounded-2xl
shadow-xl
p-4
grid
grid-cols-1
md:grid-cols-4
gap-4
"

>


<input

className="
h-12
rounded-xl
bg-gray-100
px-4
text-gray-700
outline-none
"

placeholder="From"

/>



<input

className="
h-12
rounded-xl
bg-gray-100
px-4
text-gray-700
outline-none
"

placeholder="Destination"

/>



<input

className="
h-12
rounded-xl
bg-gray-100
px-4
text-gray-700
outline-none
"

type="date"

/>



<button

className="
h-12
rounded-xl
bg-blue-700
hover:bg-blue-800
font-bold
"

>

Search

</button>


</div>





<div

className="
mt-12
grid
grid-cols-1
sm:grid-cols-3
gap-8
max-w-3xl
mx-auto
"

>


<div
className="
bg-white/20
rounded-3xl
p-6
flex
flex-col
items-center
gap-4
"
>

<Plane size={40}/>

<p className="text-lg font-semibold">
Transport
</p>


</div>





<div
className="
bg-white/20
rounded-3xl
p-6
flex
flex-col
items-center
gap-4
"
>

<Hotel size={40}/>

<p className="text-lg font-semibold">
Hotels
</p>

</div>





<div
className="
bg-white/20
rounded-3xl
p-6
flex
flex-col
items-center
gap-4
"
>


<Ticket size={40}/>


<p className="text-lg font-semibold">
Bookings
</p>


</div>


</div>


</div>


</section>

)

}


export default Hero;