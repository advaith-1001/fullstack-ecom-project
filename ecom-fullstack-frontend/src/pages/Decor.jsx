import NavBar from "../components/NavBar"
import Footer from "../components/Footer"
import ProductCard from "../components/ProductCard"
import { useState, useEffect } from "react"
import '../styles.css'
import axios from "axios"

function Decor({category}) {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);


    useEffect(() => {
        const fetchProducts = async () => {
            try {
                setLoading(true);
                const response = await axios.get(`http://localhost:8080/products/${category}`);
                setProducts(response.data);
            } catch (err) {
                setError("Failed to fetch products");
            } finally {
                setLoading(false);
            }
        };

        fetchProducts();
    }, [category]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div className="decor-content">
            <div className="category-header-container">
                <h1 className="category-header">Decor</h1>
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


export default Decor