    import { useEffect, useState } from "react";
    import { useNavigate, useParams } from "react-router-dom";
    import axios from "axios";
    import NavBar from "../components/NavBar";
    import Footer from "../components/Footer";
    import '../styles.css'
    // import { useUser } from "../services/UserContext.jsx";

    function ProductDetails() {
        const {id} = useParams();
        const[product, setProduct] = useState(null);
        const[qty, setQty] = useState(1);
        const [user, setUser] = useState("");
        const[message, setMessage] = useState("");

        const navigate = useNavigate();

        const fetchUser = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/auth/current-user", {
                    withCredentials: true, // Send cookies if required
                });
                setUser(response.data.userName); // Use this username in your logic
            } catch (error) {
                console.error("Error fetching current user", error);
            }
        };

        useEffect(() => {
            fetchUser();
        }, []);

        const handleAddToCart = async () => {
            try {
                const response = await axios.post(
                    "http://localhost:8080/cart/add-item", // Your API endpoint
                    null, // No body, since the data is sent via query params
                    {
                        params: {
                            userName: user, // Pass the logged-in user's username
                            productId: id, // ID of the product being added
                            quantity: qty,  // Quantity selected by the user
                        },
                    }
                );
                setMessage(response.data); // Display success message from the server
            } catch (error) {
                if (error.response && error.response.data) {
                    setMessage(error.response.data); // Display error message from the server
                } else {
                    setMessage("Something went wrong. Please try again.");
                }
            }
        };
        

        function increment() {
            if(qty < 5) {
                setQty(qty + 1);
            }
        }
        
        function decrement() {
            if(qty > 1) {
                setQty(qty - 1);
            }
        }

        useEffect(() => {
            axios.get(`http://localhost:8080/products/get/${id}`)
            .then((response) => setProduct(response.data))
            .catch((error) => console.error("Error fetching product", error));
        }, [id]);

        if(!product) {
            return <p>Loading product details...</p>
        }


        return(<div className="product-details-container">
            <NavBar />
            <div className="product-page-container">
                <div className="product-page-image-container">
                    <img 
                    src={`http://localhost:8080/products/product/${product.id}/image`}
                    className="product-page-image" 
                    alt={product.name} />
                </div>
                <div className="product-page-details-container">
                    <h1 className="product-page-product-name">{product.name}</h1>
                    <p className="product-page-product-desc">{product.description}</p>
                    <p className="product-page-product-price">${product.price}</p>
                    <div className="add-cart-qty-btn-container">

                        <button className="dec-btn" onClick={decrement}>-</button>
                        <input type="number" className="qty-input" value={qty} />
                        <button className="inc-btn" onClick={increment}>+</button>

                    <button className="add-to-cart-btn" onClick={handleAddToCart}>Add to Cart</button>
                    
                    </div>
                </div>
            </div>
            <Footer />
        </div>);
    }

    export default ProductDetails