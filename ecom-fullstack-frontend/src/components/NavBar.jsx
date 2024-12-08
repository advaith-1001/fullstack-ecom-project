import { Link, useNavigate } from "react-router-dom";
// import { useUser } from "../services/UserContext.jsx";
import "../styles.css";

function NavBar() {
    // const { user, setUser } = useUser();
    // const navigate = useNavigate();

    // const handleLogout = () => {
    //     setUser(null); // Clear user state
    //     navigate("/auth/login");
    // };

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
                {/* <Link to="/cart" className="shopping-cart">
                    <i className="fas fa-shopping-cart"></i>
                </Link> */}
                <a href="http://localhost:8080/login" >
                    <button className="login-button">
                            Login
                    </button>
                </a>
            </div>
        </nav>
    );
}

export default NavBar;
