import phil from '../assets/phil.jpg'
import claire from "../assets/claire.jpg"
import gloria from "../assets/gloria.jpg"
import jay from "../assets/jay.jpg"

function Testimonials() {
    return (<div className="testimonials-container">
        <div className="testimonial">
            <img src={phil} alt="phil" className='testimonial-image'></img>
            <p className='quote'>"Homestore has everything I need for my family. The products are reliable, and the service is exceptional. Highly recommended!"</p>
            <p className='test-name'>-Phil</p>
        </div>
        <div className="testimonial">
            <img src={claire} alt="claire" className='testimonial-image'/>
            <p className='quote'>"I love the convenience of shopping here! From groceries to decor, it's all in one place. The quality never disappoints."</p>
            <p className='test-name'>-Claire</p>
        </div>
        <div className="testimonial">
            <img src={gloria} alt="gloria" className='testimonial-image'/>
            <p className='quote'>"This store helped me transform my home into something truly beautiful. The range of products and quick delivery are unbeatable!"</p>
            <p className='test-name'>-Gloria</p>
        </div>
        <div className="testimonial" >
            <img src={jay} alt="jay" className='testimonial-image'/>
            <p className='quote'>"I wasn’t sure what to expect, but Homestore exceeded my expectations. Affordable prices and excellent customer support. I’ll definitely come back!"</p>
            <p className='test-name'>-Jay</p>
        </div>
    </div>);
}

export default Testimonials