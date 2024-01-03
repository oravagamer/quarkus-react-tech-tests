import {Outlet} from "react-router-dom";

import NavBar from "./layout/NavBar.tsx";
import Footer from "./layout/Footer.tsx";

const Layout = () => {
    return (
        <>
            <header>
                <NavBar/>
            </header>
            <Outlet/>
            <Footer/>
        </>
    );
}

export default Layout;