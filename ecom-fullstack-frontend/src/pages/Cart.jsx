// import { useEffect } from "react";
// import { useNavigate } from "react-router-dom";
// import { useUser } from "../services/UserContext.jsx";

// function Cart() {
//     const { user } = useUser();
//     const navigate = useNavigate();

//     useEffect(() => {
//         if (!user) {
//             navigate("/auth/login");
//         }
//     }, [user, navigate]); // Dependency array ensures effect runs when user or navigate changes

//     if (!user) {
//         return null; // Prevent rendering until navigation happens
//     }

//     return (
//         <div>
//             <h1>Welcome to Your Cart, {user.username}!</h1>
//             <div>
//                 <ul>
                    
//                 </ul>
//             </div>
//         </div>
//     );
// }

// export default Cart;
