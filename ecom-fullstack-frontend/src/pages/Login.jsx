import React, { useState } from "react";
import axios from "axios";

const Login = () => {
    const [credentials, setCredentials] = useState({ userName: "", password: "" });
    const [message, setMessage] = useState("");
  
    const handleChange = (e) => {
      setCredentials({ ...credentials, [e.target.name]: e.target.value });
    };
  
    const handleSubmit = async (e) => {
      e.preventDefault();
      try {
        const response = await axios.post("http://localhost:8080/api/auth/login", credentials, {
            headers: {
                "Content-Type": "application/json",
            },
            withCredentials: true, // Include credentials like cookies
        });
        setMessage(response.data);
      } catch (error) {
        setMessage(error.response.data);
      }
    };
  
    return (
      <div>
        <h2>Login</h2>
        <form onSubmit={handleSubmit}>
          <input type="text" name="username" placeholder="Username" onChange={handleChange} required />
          <input type="password" name="password" placeholder="Password" onChange={handleChange} required />
          <button type="submit">Login</button>
        </form>
        <p>New User? Sign up <a href="/SignUp">here.</a></p>
        <p>{message}</p>
      </div>
    );
  };
  
  export default Login;
  
