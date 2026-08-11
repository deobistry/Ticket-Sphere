import {
    useState
} from "react";


import api from "../api/axios";


import {
    useAuth
} from "../context/AuthContext";


import {
    useNavigate
} from "react-router-dom";


import Button from "../components/Button";



function Login(){


    const [form, setForm] = useState({

        email: "",

        password: ""

    });



    const {
        login
    } = useAuth();



    const navigate = useNavigate();





    const change = (e) => {


        setForm({

            ...form,

            [e.target.name]: e.target.value

        });


    };






    const submit = async (e) => {


        e.preventDefault();



        try {


            const response = await api.post(

                "/auth/login",

                form

            );




            const token =
                response.data.token;



            const role =
                response.data.role;





            login(
                token
            );





            localStorage.setItem(

                "role",

                role

            );








            if(role === "ADMIN"){


                navigate("/admin");


            }

            else {


                navigate("/");


            }





        }

        catch(error) {


            console.error(

                "Login failed:",

                error

            );


            alert(

                "Invalid email or password"

            );


        }


    };







    return (


        <div className="
            flex
            justify-center
            items-center
            min-h-screen
            bg-gray-100
        ">


            <form

                onSubmit={submit}

                className="
                    bg-white
                    shadow-xl
                    rounded-xl
                    p-8
                    w-96
                "

            >



                <h1 className="
                    text-3xl
                    font-bold
                    mb-6
                    text-center
                ">

                    Login

                </h1>






                <input


                    name="email"


                    type="email"


                    placeholder="Email"


                    value={form.email}


                    onChange={change}


                    required


                    className="
                        border
                        rounded-lg
                        p-3
                        w-full
                        mb-4
                    "


                />







                <input


                    name="password"


                    type="password"


                    placeholder="Password"


                    value={form.password}


                    onChange={change}


                    required


                    className="
                        border
                        rounded-lg
                        p-3
                        w-full
                        mb-6
                    "


                />







                <Button type="submit">


                    Login


                </Button>





            </form>



        </div>


    );


}



export default Login;