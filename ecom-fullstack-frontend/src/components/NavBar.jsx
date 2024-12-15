import { Link, useNavigate } from "react-router-dom";
import { useUser } from "../services/UserContext";
import "../styles.css";
import axios from "axios";

function NavBar() {
    const { user, setUser } = useUser();
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            await axios.post("http://localhost:8080/api/auth/logout", {}, { withCredentials: true });
            setUser("");
            navigate("/home");
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
                {user != "" ? (
                    <>
                        <span className="new-user-q">Welcome, {user}</span>
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
