function Button({

    children,

    type="button",

    className="",

    ...props

}){


return (

<button

type={type}

className={`
px-6
py-3
rounded-xl
bg-blue-700
text-white
font-semibold
shadow-lg
hover:bg-blue-800
hover:scale-105
transition-all
duration-200

${className}

`}

{...props}

>


{children}


</button>

);


}


export default Button;