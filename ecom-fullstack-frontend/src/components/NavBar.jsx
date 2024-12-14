import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import "../styles.css";

function NavBar() {
    const [user, setUser] = useState(null); // State for user details
    const navigate = useNavigate();

    // Fetch the current user details on mount
    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/auth/current-user", {
                    withCredentials: true, // Ensures cookies are sent with requests
                });
                setUser(response.data); // Set user data if logged in
            } catch (error) {
                console.log("No user is logged in.");
            }
        };
        fetchUser();
    }, []);

    // Handle logout functionality
    const handleLogout = async () => {
        try {
            await axios.post("http://localhost:8080/api/auth/logout", {}, { withCredentials: true });
            setUser(null); // Clear user state
            navigate("/home"); // Redirect to home page
        } catch (error) {
            console.error("Logout failed", error);
        }
    };

    return (
        <nav className="nav">
            <Link className="brand-name" to="/home">
                HOMESTORE
            </Link>
            <ul className="nav-list">
                <li><Link to="/grocery">Grocery</Link></li>
                <li><Link to="/utensils">Utensils</Link></li>
                <li><Link to="/cleaning">Cleaning</Link></li>
                <li><Link to="/decor">Decor</Link></li>
            </ul>
            <div className="login-cart-container">
                {user ? (
                    <>
                        <span className="new-user-q">Welcome, {user.userName}</span>
                        <button className="login-button" onClick={handleLogout}>
                            Logout
                        </button>
                    </>
                ) : (
                    <Link to="/login">
                        <button className="login-button">Login</button>
                    </Link>
                )}
            </div>
        </nav>
    );
}

export default NavBar;
