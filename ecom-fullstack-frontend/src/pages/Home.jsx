import NavBar from "../components/NavBar";
import Footer from "../components/Footer";
import Testimonials from "../components/Testimonials";
import "../styles.css"

function Home() {
    return(<div>
        <NavBar />
        <div className="home-body">
        <h1 className="home-typography">Home Essentials,</h1>
        <h1 className="home-typography">at one place.</h1>
        <a href="/all">
            <button className="home-shop-button">Start Shopping</button>
        </a>
        </div>
        <Testimonials />
        <Footer />
    </div>);
}

export default Home;