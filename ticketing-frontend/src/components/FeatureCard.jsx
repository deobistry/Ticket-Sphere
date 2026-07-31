import {motion} from "framer-motion";


function FeatureCard({

icon,
title,
description

}){


return (

<motion.div

whileHover={{
y:-8
}}

className="
bg-white
rounded-3xl
shadow-lg
p-8
flex
flex-col
items-center
text-center
"

>


<div

className="
w-20
h-20
rounded-3xl
bg-blue-100
text-blue-600
flex
items-center
justify-center
mb-6
"

>

{icon}

</div>




<h2

className="
text-xl
font-bold
mb-3
"

>

{title}

</h2>




<p

className="
text-gray-600
leading-relaxed
"

>

{description}

</p>


</motion.div>


)

}


export default FeatureCard;