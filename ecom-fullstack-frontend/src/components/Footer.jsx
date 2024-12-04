function Footer() {
    return (<footer className="footer">
            <div className="footer-links-container">
                <ul className="footer-links">
                    <li><a href="/about">About Us</a></li>
                    <li><a href="/legal">Legal Info</a></li>
                    <li><a href="/privacy">Privacy Policy</a></li>
                    <li><a href="/faq">FAQs</a></li>
                </ul>
            </div>

            <div className="contact">
                <p className="contact-head">Contact Us</p>
                <p className="phone"><i className="fas fa-phone"></i>+1 (555) 123-4567</p>
                <p className="email"><i className="fas fa-envelope"></i>contact@homestore.com</p>
            </div>

        <div className="social-media">
            <p className="follow">Follow Us</p>
            <a href="https://www.instagram.com" target="_blank" rel="noopener noreferrer">
        <i className="fab fa-instagram"></i>
    </a>
    <a href="https://www.facebook.com" target="_blank" rel="noopener noreferrer">
        <i className="fab fa-facebook-f"></i>
    </a>
    <a href="https://www.youtube.com" target="_blank" rel="noopener noreferrer">
        <i className="fab fa-youtube"></i>
    </a>
    <a href="https://www.pinterest.com" target="_blank" rel="noopener noreferrer">
        <i className="fab fa-pinterest"></i>
    </a>
        </div>
        <div className="copyright">
                <p>&copy; 2024 Homestore. All Rights Reserved.</p>
            </div>
    </footer>);
}

export default Footer