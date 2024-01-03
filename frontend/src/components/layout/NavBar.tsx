import {NavLink, Link} from 'react-router-dom';
import oravixLogo from '/svg/logo-transparent.svg';

import "./NavBar.scss";

const NavBar = () => {
    return (
        <nav id="nav-bar">
            <Link to="" id="site-name-logo"><img id="nav-logo" src={oravixLogo} alt="Oravix logo"/>
                <div>Oravix</div>
            </Link>
            <div>
                <NavLink to="">Home</NavLink>
                <NavLink to="gallery">Gallery</NavLink>
                <NavLink to="files">Files</NavLink>
                <NavLink to="calendar">Calendar</NavLink>
                <NavLink to="chat">Chat</NavLink>
            </div>
        </nav>
    );
}

export default NavBar;