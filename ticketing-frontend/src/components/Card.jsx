function Card({
    children
}) {


    return (

        <div

            className="
            bg-white
            rounded-xl
            shadow-lg
            p-6
            hover:shadow-xl
            transition
            "

        >

            {children}


        </div>

    );


}


export default Card;