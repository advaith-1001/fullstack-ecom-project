import NavBar from "../components/NavBar"
import Footer from "../components/Footer"
import ProductCard from "../components/ProductCard"
import { useState, useEffect } from "react"
import '../styles.css'
import axios from "axios"

function All() {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);


    useEffect(() => {
        const fetchProducts = async () => {
            try {
                setLoading(true);
                const response = await axios.get(`http://localhost:8080/products/all`);
                setProducts(response.data);
            } catch (err) {
                setError("Failed to fetch products");
            } finally {
                setLoading(false);
            }
        };

        fetchProducts();
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div className="all-content">
            <NavBar />
            <div className="category-header-container">
                <h1 className="category-header">All Products</h1>
            </div>
            <div className="product-grid">
                {products.map((product) => (
                    <ProductCard key={product.id} product={product} />
                ))}
            </div>
            <Footer />
        </div>
    );
}


export default All