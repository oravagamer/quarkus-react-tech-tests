import {Link} from 'react-router-dom';

import "./NavBar.scss";

const NavBar = () => {
    return (
        <nav id="nav-bar">
            <ul>
                <li><Link to="">Home</Link></li>
                <li><Link to="gallery">Gallery</Link></li>
            </ul>
        </nav>
    );
}

export default NavBar;