    import React, { useState } from "react";
    import axios from "axios";
    import { useUser } from "../services/UserContext.jsx";
    import { useNavigate } from "react-router-dom";

    function Login() {
        const [userName, setUserName] = useState("");
        const [password, setPassword] = useState("");
        const { setUser } = useUser();

        const navigate = useNavigate();

        const handleLogin = async () => {
            try {
              const response = await fetch("http://localhost:8080/auth/login-user", {
                method: "POST",
                headers: {   
                  "Content-Type": "application/json", // Required for JSON requests
                },
                body: JSON.stringify({
                  userName: "testUser", // Adjust according to your login payload
                  password: "testPassword",
                }),
              });
          
              if (response.ok) {
                console.log("Login successful");
              } else {
                console.error(`Login failed with status ${response.status}`);
              }
            } catch (error) {
              console.error("Error during login:", error);
            }
          };
        return (
            <div className="login-container">
            <div className="login-box">
                <div className="login-page-header-container">
                <h2 className="login-page-header">Login</h2>
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
                <div className="login-page-button-container">
                <button className="login-page-button" onClick={handleLogin}>Login</button>
                </div>
                <div className="new-user-q-container">
                    <p className="new-user-q">New User?<a href="/auth/signup">Sign Up</a> here</p>
                </div>
            </div>
            </div>
        );
    }

    export default Login;
