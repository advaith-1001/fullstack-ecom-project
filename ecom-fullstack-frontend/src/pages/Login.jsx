import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const [formData, setFormData] = useState({ userName: "", password: "" });
    const [message, setMessage] = useState("");
    const navigate = useNavigate(); 
  
    const handleChange = (e) => {
      setFormData({ ...formData, [e.target.name]: e.target.value });
    };
  
    const handleSubmit = async (e) => {
      e.preventDefault(); // Prevent default behavior
  
      // Log formData to check credentials before sending the request
      console.log("Form data being sent:", formData);
  
      try {
          const response = await axios.post(
              "http://localhost:8080/api/auth/login",
              formData, // Correctly pass the form data
              {
                  headers: {
                      "Content-Type": "application/json",
                  },
                  withCredentials: true,
              }
          );
          console.log("Response from backend:", response.data); // Log response for debugging
          setMessage(response.data.message); // Set success message
          setTimeout(() => {
            navigate("/home"); // Redirect to the home page
        }, 1000);
      } catch (error) {
          console.error("Error from backend:", error.response?.data || error.message); // Log error details
          setMessage(error.response?.data?.error || "Something went wrong.");
      }
  };
  
  
    return (
      <div className="login-container">
        <div className="login-box">
        <h2 className="login-page-header">Login</h2>
        <form onSubmit={handleSubmit} className="login-page-input-container">
          <input type="text" className="username-input" name="userName" placeholder="Username" onChange={handleChange} required />
          <input type="password" className="password-input" name="password" placeholder="Password" onChange={handleChange} required />
          <button type="submit" className="login-page-button">Login</button>
        </form>
        <p className="new-user-q">New User? Sign up <a href="/SignUp">here.</a></p>
        <p className="err-msg">{message}</p>
        </div>
      </div>
    );
  };
  
  export default Login;
  
