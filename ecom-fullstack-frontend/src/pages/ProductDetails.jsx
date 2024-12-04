    import { useEffect, useState } from "react";
    import { useNavigate, useParams } from "react-router-dom";
    import axios from "axios";
    import NavBar from "../components/NavBar";
    import Footer from "../components/Footer";
    import '../styles.css'
    import { useUser } from "../services/UserContext.jsx";

    function ProductDetails() {
        const {id} = useParams();
        const[product, setProduct] = useState(null);
        const[qty, setQty] = useState(1);
        const { user, setUser } = useUser();

        const navigate = useNavigate();

        const handleAddToCart = async () => {
            if (!user || !user.userName) {
                alert("You need to be logged in to add items to the cart.");
                navigate("/auth/login"); // Redirect to login page if not logged in
                return;
            }
        
            try {
                const response = await axios.post(
                    `http://localhost:8080/cart/add-item`,
                    null, // No request body; params are used instead
                    {
                        params: {
                            userName: user.userName, // Use `userName` from context
                            productId: id,          // Product ID from route params
                            quantity: qty           // Quantity state
                        },
                        withCredentials: true // Send cookies for session-based authentication
                    }
                );
                console.log("Item added to cart:", response.data);
            } catch (error) {
                console.error("Error adding item to cart:", error);
                alert("Could not add item to cart.");
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