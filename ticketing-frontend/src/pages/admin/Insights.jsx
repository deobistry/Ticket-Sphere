import {
    useEffect,
    useState
} from "react";


import {
    PieChart,
    Pie,
    Cell,
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    ResponsiveContainer,
    LineChart,
    Line,
    AreaChart,
    Area
} from "recharts";


import {
    motion
} from "framer-motion";


import api from "../../api/axios";





function Insights(){


const [bookings,setBookings] =
useState([]);


const [loading,setLoading] =
useState(true);





useEffect(()=>{


const loadData = async()=>{


try{


const response =
await api.get("/admin/bookings");


setBookings(response.data);


}

catch(error){

console.error(error);

}

finally{

setLoading(false);

}


};


loadData();


},[]);






if(loading)

return (

<div className="
flex
justify-center
items-center
h-screen
text-3xl
font-bold
">

Loading analytics...

</div>

);






const totalBookings =
bookings.length;




const totalRevenue =
bookings.reduce(
(sum,item)=>
sum + Number(item.amount),
0
);





const accommodation =
bookings.filter(
b=>b.bookingType==="ACCOMMODATION"
);



const transport =
bookings.filter(
b=>b.bookingType==="TRANSPORT"
);






const categoryData=[

{
name:"Accommodation",
value:accommodation.length
},

{
name:"Transport",
value:transport.length
}

];







const revenue={};



bookings.forEach(item=>{


const month =
new Date(item.bookingDate)
.toLocaleString(
"default",
{
month:"short"
}
);



revenue[month] =
(revenue[month] || 0)
+
Number(item.amount);



});




const revenueData =
Object.keys(revenue)
.map(month=>({

month,

amount:
revenue[month]

}));







const COLORS=[

"#22c55e",
"#3b82f6"

];






const cardAnimation={

initial:{
opacity:0,
y:40
},

animate:{
opacity:1,
y:0
},

transition:{
duration:.6
}

};







return (


<div className="
min-h-screen
bg-gradient-to-br
from-gray-100
via-white
to-blue-100
py-12
">



<div className="
max-w-7xl
mx-auto
px-6
">





<motion.h1

initial={{
opacity:0,
y:-30
}}

animate={{
opacity:1,
y:0
}}

className="
text-5xl
font-extrabold
mb-12
"

>

Analytics Dashboard

</motion.h1>







<div className="
grid
grid-cols-1
md:grid-cols-4
gap-6
mb-10
">





{

[

{
title:"Revenue",
value:`₹ ${totalRevenue}`,
color:"blue"
},

{
title:"Bookings",
value:totalBookings,
color:"purple"
},

{
title:"Accommodation",
value:accommodation.length,
color:"green"
},

{
title:"Transport",
value:transport.length,
color:"orange"
}

].map((item,index)=>(


<motion.div

key={item.title}

{...cardAnimation}

transition={{
delay:index*.1
}}

className="
bg-white/80
backdrop-blur
rounded-3xl
shadow-xl
p-6
hover:scale-105
transition
"

>


<p className="
text-gray-500
">

{item.title}

</p>


<h2 className="
text-4xl
font-extrabold
mt-3
">

{item.value}

</h2>


</motion.div>


))


}



</div>








<div className="
grid
md:grid-cols-2
gap-8
">







<motion.div

{...cardAnimation}

className="
bg-white
rounded-3xl
shadow-xl
p-6
"

>


<h2 className="
text-xl
font-bold
mb-5
">

Booking Distribution

</h2>



<ResponsiveContainer
width="100%"
height={320}
>


<PieChart>


<Pie

data={categoryData}

dataKey="value"

cx="50%"

cy="50%"

outerRadius={110}

animationDuration={1200}

>

{

categoryData.map(
(_,index)=>(

<Cell
key={index}
fill={COLORS[index]}
/>

)

)

}

</Pie>



<Tooltip/>

</PieChart>


</ResponsiveContainer>


</motion.div>








<motion.div

{...cardAnimation}

className="
bg-white
rounded-3xl
shadow-xl
p-6
"

>


<h2 className="
text-xl
font-bold
mb-5
">

Revenue Growth

</h2>



<ResponsiveContainer
width="100%"
height={320}
>


<AreaChart
data={revenueData}
>


<defs>

<linearGradient
id="colorRevenue"
x1="0"
y1="0"
x2="0"
y2="1"
>

<stop
offset="5%"
stopColor="#2563eb"
stopOpacity={0.8}
/>


<stop
offset="95%"
stopColor="#2563eb"
stopOpacity={0}
/>


</linearGradient>


</defs>



<XAxis
dataKey="month"
/>


<YAxis/>


<Tooltip/>


<Area

type="monotone"

dataKey="amount"

stroke="#2563eb"

fill="url(#colorRevenue)"

animationDuration={1500}

/>



</AreaChart>


</ResponsiveContainer>


</motion.div>








<motion.div

{...cardAnimation}

className="
bg-white
rounded-3xl
shadow-xl
p-6
md:col-span-2
"

>


<h2 className="
text-xl
font-bold
mb-5
">

Booking Performance

</h2>




<ResponsiveContainer
width="100%"
height={350}
>


<BarChart

data={categoryData}

>


<XAxis
dataKey="name"
/>


<YAxis/>


<Tooltip/>


<Bar

dataKey="value"

radius={[
10,
10,
0,
0
]}

fill="#22c55e"

animationDuration={1200}

/>



</BarChart>


</ResponsiveContainer>



</motion.div>







</div>



</div>


</div>


);


}


export default Insights;