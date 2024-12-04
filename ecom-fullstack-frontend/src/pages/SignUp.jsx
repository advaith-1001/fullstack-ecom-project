import React, { useState } from "react";
import axios from "axios";
import { useUser } from "../services/UserContext.jsx";
import { useNavigate } from "react-router-dom";

function SignUp() {
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleSignUp = async () => {
        try {
            const response = await axios.post("http://localhost:8080/auth/register", {
                userName, // User's username
                password, // User's password
            });
            alert(response.data); // Show success message
            console.log({ userName });
            navigate("/home");
        } catch (error) {
            alert("Registration failed. Try a different username.");
        }
    };

    return (
        <div className="login-container">
        <div className="login-box">
            <div className="login-page-header-container">
            <h2 className="login-page-header">Sign Up</h2>
            </div>
            <div className="login-page-input-container">
            <input
            className="username-input"
                type="text"
                placeholder="Username"
                value={userName}
                onChange={(e) => setUserName(e.target.value)}
            />
            <input
            className="password-input"
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            </div>
            <div className="signup-page-button-container">
            <button className="login-page-button" onClick={handleSignUp}>Sign Up</button>
            </div>
        </div>
        </div>
    );
}

export default SignUp;
