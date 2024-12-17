import React, { useState } from "react";
import axios from "axios";
import { useUser } from "../services/UserContext";

const Order = () => {
  const { user } = useUser();
  const [name, setName] = useState(""); // Add state for name
  const [address, setAddress] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Clear previous message
    setMessage("");

    // Validate inputs
    if (!name || !address || !phoneNumber) {
      setMessage("All fields are required.");
      return;
    }

    try {
        const response = await axios.post(
            "http://localhost:8080/orders/create",
            null,
            {
                params: {
                    userName: user,
                    name: name,
                    address: address,
                    phoneNumber: phoneNumber
                },
            }
        );
      setMessage(response.data); // Success message from backend
    } catch (error) {
      if (error.response) {
        setMessage(error.response.data); // Error message from backend
      } else {
        setMessage("An error occurred while placing the order.");
      }
    }
    console.log(message);
  };

  return (
    <div className="order-container">
      <h2 className="order-header">Order Confirmation</h2>
      <form onSubmit={handleSubmit} className="order-form">
        <div className="order-name-container">
          <label className="order-label">Name    </label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)} // On change, update name
            required
            className="order-name-input"
          />
        </div>
        <div className="order-address-container">
          <label className="order-label">Address    </label>
          <input
            type="text"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
            required
            className="order-address-input"
          />
        </div>
        <div className="order-phone-container">
          <label className="order-label">Phone    </label>
          <input
            type="text"
            value={phoneNumber}
            onChange={(e) => setPhoneNumber(e.target.value)}
            required
            className="order-phone-input"
          />
        </div>
        <button type="submit" className="order-submit-btn">Confirm Order</button>
      </form>
<p className="new-user-q">Cash On Delivery is the Only Payment Option available now.</p>
      {message && <p className="new-user-q">{message}</p>}
    </div>
  );
};

export default Order;
