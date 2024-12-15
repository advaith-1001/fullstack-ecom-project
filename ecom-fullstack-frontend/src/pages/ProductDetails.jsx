import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import NavBar from "../components/NavBar";
import Footer from "../components/Footer";
import { useUser } from "../services/UserContext";
import '../styles.css';

function ProductDetails() {
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [qty, setQty] = useState(1);
    const { user } = useUser();
    const [message, setMessage] = useState("");
    const navigate = useNavigate();

    const handleAddToCart = async () => {
        if (user == "") {
            setMessage("You must log in to add items to the cart.");
            return;
        }
        try {
            const response = await axios.post(
                "http://localhost:8080/cart/add-item",
                null,
                {
                    params: {
                        userName: user,
                        productId: id,
                        quantity: qty,
                    },
                }
            );
            setMessage(response.data);
        } catch (error) {
            setMessage(
                error.response?.data || "Something went wrong. Please try again."
            );
        }
    };

    useEffect(() => {
        axios.get(`http://localhost:8080/products/get/${id}`)
            .then((response) => setProduct(response.data))
            .catch((error) => console.error("Error fetching product", error));
    }, [id]);

    if (!product) {
        return <p>Loading product details...</p>;
    }

    return (
        <div className="product-details-container">
            <div className="product-page-container">
                <div className="product-page-image-container">
                    <img
                        src={`http://localhost:8080/products/product/${product.id}/image`}
                        className="product-page-image"
                        alt={product.name}
                    />
                </div>
                <div className="product-page-details-container">
                    <h1 className="product-page-product-name">{product.name}</h1>
                    <p className="product-page-product-desc">{product.description}</p>
                    <p className="product-page-product-price">${product.price}</p>
                    <div className="add-cart-qty-btn-container">
                        <button className="dec-btn" onClick={() => setQty((q) => Math.max(1, q - 1))}>-</button>
                        <input type="number" className="qty-input" value={qty} readOnly />
                        <button className="inc-btn" onClick={() => setQty((q) => Math.min(5, q + 1))}>+</button>
                        <button className="add-to-cart-btn" onClick={handleAddToCart}>Add to Cart</button>
                    </div>
                    <p className="new-user-q">{message}</p>
                </div>
            </div>
            <Footer />
        </div>
    );
}

export default ProductDetails;
