import React, { useEffect, useState } from "react";
import { useUser } from "../services/UserContext";
import Footer from "../components/Footer";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const { user } = useUser();
  const [totalPrice, setTotalPrice] = useState(0);

  // Fetch cart items from the backend
  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/cart/get-items?userName=${user}`
        );
        if (response.ok) {
          const data = await response.json();
          setCartItems(data);
        } else {
          console.error("Failed to fetch cart items.");
        }
      } catch (error) {
        console.error("Error fetching cart items:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchCartItems();
  }, [user]);

  useEffect(() => {
    const fetchTotalPrice = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/cart/total?userName=${user}`
        );
        if (response.ok) {
          const data = await response.json();
          setTotalPrice(data);
        } else {
          console.error("Failed to fetch total price.");
        }
      } catch (error) {
        console.error("Error fetching total price:", error);
      }
    };

    fetchTotalPrice();
  }, [user]);

  // Render loading state
  if (loading) {
    return <div>Loading cart items...</div>;
  }

  // Render cart items
  return (
    <div>
      <h2 className="cart-heading">Your Cart</h2>
      <div className="cart-items-container">
        {cartItems.length === 0 ? (
          <p>Your cart is empty.</p>
        ) : (
          cartItems.map((item) => (
            <div className="cart-item-div" key={item.id}>
              <img
              className="cart-item-img"
                src={`http://localhost:8080/products/product/${item.product.id}/image`}
                alt={item.product.name}
                style={{ width: "100px", height: "100px", objectFit: "cover", marginRight: "1rem" }}
              />
              <div className="cart-item-details">
                <h3>{item.product.name}</h3>
                <p>Quantity: {item.quantity}</p>
                <p>Price: ${item.price}</p>
              </div>
            </div>
          ))
        )}

        {cartItems.length > 0 && (
            <div className="total-price">
                <p>Total Price: ${totalPrice}</p>
                <button className="place-order">Place Order</button>
             </div>
        )}
      </div>

      <Footer />
    </div>
  );
};

export default Cart;
