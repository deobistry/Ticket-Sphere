import {
    useEffect,
    useState
} from "react";


import api from "../../api/axios";





function ManageUsers(){



const [users,setUsers]=useState([]);

const [loading,setLoading]=useState(true);








const loadUsers=async()=>{


try{


setLoading(true);


const response =
await api.get("/admin/users");


setUsers(
    response.data
);


}

catch(error){

console.error(
    "Failed loading users",
    error
);


}

finally{


setLoading(false);


}


};









useEffect(()=>{


loadUsers();


},[]);









const deleteUser=async(id)=>{


const confirmDelete =
window.confirm(
    "Are you sure you want to delete this user?"
);



if(!confirmDelete)
return;





try{


await api.delete(
    `/admin/users/${id}`
);





setUsers(

users.filter(
    user=>user.id!==id
)

);




alert(
    "User deleted successfully"
);



}

catch(error){


console.error(
    error
);


alert(
    "Unable to delete user"
);


}


};









const changeRole=async(id,role)=>{


try{


await api.put(

`/admin/users/${id}/role`,

role

);




setUsers(

users.map(
(user)=>

user.id===id

?

{
...user,
role:role
}

:

user

)

);




}

catch(error){


console.error(error);


alert(
"Unable to update role"
);


}


};










return (


<div

className="
min-h-screen
bg-gray-100
py-12
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
text-4xl
font-extrabold
mb-10
"

>

Registered Users

</h1>








{

loading

?

<h2 className="
text-center
text-xl
">

Loading users...

</h2>


:

users.length===0

?

<h2 className="
text-center
text-xl
text-gray-600
">

No users found

</h2>


:


<div

className="
grid
grid-cols-1
md:grid-cols-2
lg:grid-cols-3
gap-8
"

>


{

users.map(

(user)=>(


<div

key={user.id}

className="
bg-white
rounded-3xl
shadow-lg
p-6
"

>


<h2

className="
text-2xl
font-bold
text-purple-600
"

>

{user.name}

</h2>





<p className="
mt-3
">

Email:

<b>
{" "}
{user.email}
</b>

</p>





<p className="
mt-2
">

Role:

<b>
{" "}
{user.role}
</b>

</p>







<select

value={user.role}

onChange={
(e)=>
changeRole(
user.id,
e.target.value
)
}

className="
mt-5
border
rounded-xl
p-3
w-full
"

>


<option value="USER">

USER

</option>


<option value="ADMIN">

ADMIN

</option>


</select>









<button

onClick={
()=>deleteUser(user.id)
}

className="
mt-5
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

Delete User

</button>







</div>


)


)


}


</div>


}



</div>


</div>


);


}



export default ManageUsers;