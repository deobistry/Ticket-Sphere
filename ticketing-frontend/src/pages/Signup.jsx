import {
    useState
}
from "react";


import api from "../api/axios";


import {
    useNavigate
}
from "react-router-dom";


import Button from "../components/Button";


import toast from "react-hot-toast";




function Signup(){



const navigate =
    useNavigate();




const [form,setForm]=useState({

    name:"",
    email:"",
    password:""

});





const change=(e)=>{


setForm({

    ...form,

    [e.target.name]:
        e.target.value

});


};






const submit=async(e)=>{


e.preventDefault();


try{


await api.post(

    "/auth/signup",

    form

);



toast.success(
    "Account created successfully"
);



navigate("/login");



}

catch(error){


toast.error(

    error.response?.data?.message ||

    "Signup failed"

);


}


};








return (

<div className="
min-h-screen
flex
items-center
justify-center
bg-gradient-to-br
from-blue-50
to-indigo-100
">


<form

onSubmit={submit}

className="
bg-white
shadow-2xl
rounded-3xl
p-10
w-full
max-w-md
"


>


<h1 className="
text-4xl
font-bold
text-gray-800
mb-8
text-center
">

Create Account

</h1>





<input

name="name"

value={form.name}

onChange={change}

placeholder="Full Name"

className="
w-full
mb-4
px-4
py-3
border
rounded-xl
focus:outline-none
focus:ring-2
focus:ring-blue-500
"

/>





<input

name="email"

value={form.email}

onChange={change}

placeholder="Email"

type="email"

className="
w-full
mb-4
px-4
py-3
border
rounded-xl
focus:outline-none
focus:ring-2
focus:ring-blue-500
"

/>






<input

name="password"

value={form.password}

onChange={change}

placeholder="Password"

type="password"

className="
w-full
mb-6
px-4
py-3
border
rounded-xl
focus:outline-none
focus:ring-2
focus:ring-blue-500
"

/>






<Button

type="submit"

className="
w-full
"

>

Create Account

</Button>




<p className="
text-center
mt-6
text-gray-600
">


Already have an account?


<button

type="button"

onClick={()=>navigate("/login")}

className="
text-blue-600
ml-2
font-semibold
"

>

Login

</button>


</p>



</form>


</div>

);


}



export default Signup;