import {Outlet} from "react-router-dom";

import NavBar from "./layout/NavBar.tsx";
import Footer from "./layout/Footer.tsx";

const Layout = () => {
    return (
        <>
            <NavBar />
            <Outlet />
            <Footer />
        </>
    );
}

export default Layout;