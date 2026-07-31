import {
    Link,
    useLocation,
    useNavigate
} from "react-router-dom";


import {
    useState
} from "react";


import {
    Menu,
    X,
    LogOut,
    UserCircle
} from "lucide-react";


import {
    useAuth
} from "../context/AuthContext";




function Navbar(){


const {
    token,
    logout
}=useAuth();



const navigate = useNavigate();


const location = useLocation();



const [mobileOpen,setMobileOpen]=useState(false);




const role =
    localStorage.getItem("role");






const logoutUser = () => {

    logout();

    localStorage.removeItem("role");

    navigate("/login");

};





const active = (path) => {


    return location.pathname === path

    ?

    "text-blue-600 font-semibold"

    :

    "text-gray-700 hover:text-blue-600 transition";


};






const userLinks = [

    {
        name:"Home",
        path:"/"
    },

    {
        name:"Transport",
        path:"/transportation"
    },

    {
        name:"Hotels",
        path:"/accommodation"
    },

    {
        name:"My Bookings",
        path:"/bookings"
    }

];






const adminLinks = [

    {
        name:"Dashboard",
        path:"/admin"
    },

   {
    name:"Transportation",
    path:"/admin/transportation/manage"
    },

    {
    name:"Accommodation",
    path:"/admin/accommodation/manage"
    },

    {
        name:"Bookings",
        path:"/admin/bookings"
    },

    {
        name:"Users",
        path:"/admin/users"
    }

];





const menuLinks =
    role === "ADMIN"
    ?
    adminLinks
    :
    userLinks;





const homePath =
    role === "ADMIN"
    ?
    "/admin"
    :
    "/";








return (

<nav

className="
sticky
top-0
z-50
bg-white/90
backdrop-blur-xl
border-b
shadow-sm
"

>


<div

className="
max-w-7xl
mx-auto
px-6
py-4
flex
items-center
justify-between
"

>





{/* LOGO */}


<Link

to={homePath}

className="
flex
items-center
text-2xl
font-extrabold
"

>


<span className="text-blue-600">

Ticket

</span>


<span className="text-purple-600">

Sphere

</span>


</Link>










{/* DESKTOP MENU */}


<div

className="
hidden
md:flex
items-center
gap-8
"

>





{

menuLinks.map(

(item)=>(


<Link

key={item.path}

to={item.path}

className={active(item.path)}

>

{item.name}

</Link>


)

)

}








<div

className="
h-6
w-px
bg-gray-300
"

>

</div>









{

token

?

<button

onClick={logoutUser}

className="
flex
items-center
gap-2
bg-red-500
text-white
px-5
py-2
rounded-full
hover:bg-red-600
transition
"

>


<LogOut size={18}/>


Logout


</button>


:


<div

className="
flex
gap-3
"

>


<Link

to="/login"

className="
flex
items-center
gap-2
border
border-blue-600
text-blue-600
px-5
py-2
rounded-full
hover:bg-blue-50
transition
"

>


<UserCircle size={18}/>


Login


</Link>





<Link

to="/signup"

className="
bg-blue-600
text-white
px-5
py-2
rounded-full
hover:bg-blue-700
transition
"

>

Create Account

</Link>


</div>


}



</div>









{/* MOBILE BUTTON */}


<button

className="
md:hidden
text-gray-700
"

onClick={()=>setMobileOpen(!mobileOpen)}

>


{

mobileOpen

?

<X size={28}/>

:

<Menu size={28}/>

}


</button>



</div>









{/* MOBILE MENU */}


{

mobileOpen &&


<div

className="
md:hidden
px-6
pb-6
flex
flex-col
gap-5
bg-white
"

>



{

menuLinks.map(

(item)=>(


<Link

key={item.path}

onClick={()=>setMobileOpen(false)}

to={item.path}

className={active(item.path)}

>

{item.name}

</Link>


)

)

}







{

token

?

<button

onClick={logoutUser}

className="
text-left
text-red-600
font-semibold
"

>

Logout

</button>


:

<>


<Link

onClick={()=>setMobileOpen(false)}

to="/login"

>

Login

</Link>





<Link

onClick={()=>setMobileOpen(false)}

to="/signup"

className="
text-blue-600
font-semibold
"

>

Create Account

</Link>


</>


}



</div>


}



</nav>

);


}


export default Navbar;