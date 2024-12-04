import React from "react";
import { Link } from "react-router-dom";

function ProductCard({ product }) {
    return (
        <Link to={`/product/${product.id}`} className="product-card-link">
            <div className="product-card">
                <div className="product-card-image-container">
                <img className="product-card-image"
                    src={`http://localhost:8080/products/product/${product.id}/image`}
                    alt={product.name}
                />
                </div> 
                <div className="product-card-details">
                    <p className="product-card-name">{product.name}</p>
                    <p className="product-card-price">${product.price}</p>
                </div>
            </div>
        </Link>
    );
}

export default ProductCard
