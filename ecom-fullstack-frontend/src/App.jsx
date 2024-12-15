import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Grocery from "./pages/Grocery";
import Utensils from "./pages/Utensils";
import Cleaning from "./pages/Cleaning";
import Decor from "./pages/Decor";
import All from "./pages/All";
import ProductDetails from "./pages/ProductDetails";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
// import {UserProvider, useUser } from "./services/UserContext.jsx";
// import Cart from "./pages/Cart.jsx";
import NavBar from "./components/NavBar.jsx";

function App() {

  return (
    <div>
      {/* <UserProvider > */}
      <BrowserRouter>
      <NavBar />
      <Routes>
        <Route index element={<Home />} />
        <Route path="/home" element={<Home />}/>
        <Route path="/all" element={<All />}/>
        <Route path="/grocery" element={<Grocery category={"grocery"}/>}/>
        <Route path="/utensils" element={<Utensils category={"utensils"}/>} />
        <Route path="/cleaning" element={<Cleaning category={"cleaning"}/>} />
        <Route path="/decor" element={<Decor category={"decor"}/>} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/product/:id" element={<ProductDetails />}/>
        {/* <Route path="/cart" element={<Cart />}/> */}
      </Routes>
      </BrowserRouter>
      {/* </UserProvider> */}
    </div>
  );
}

export default App
