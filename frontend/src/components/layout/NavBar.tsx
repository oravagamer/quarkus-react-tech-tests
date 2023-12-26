import {NavLink, Link} from 'react-router-dom';
import oravixLogo from "/svg/logo-transparent.svg";

import "./NavBar.scss";

const NavBar = () => {
    return (
        <nav id="nav-bar">
            <Link to="" id="site-name-logo"><img id="nav-logo" src={oravixLogo} alt="Oravix logo"/><div>Oravix</div></Link>
            <ul>
                <li><NavLink to="">Home</NavLink></li>
                <li><NavLink to="gallery">Gallery</NavLink></li>
                <li className="float-right"><NavLink to="files">Files</NavLink></li>
                <li className="float-right"><NavLink to="calendar">Calendar</NavLink></li>
                <li className="float-right"><NavLink to="chat">Chat</NavLink></li>
            </ul>
        </nav>
    );
}

export default NavBar;