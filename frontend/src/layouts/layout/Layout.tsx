import {Outlet} from "react-router-dom";

import NavBar from "./NavBar.tsx";
import Footer from "./Footer.tsx";
import {PaletteMode} from "@mui/material";
import {FC} from "react";

interface Props {
    mode: PaletteMode,
    setMode: (mode: PaletteMode) => void,
}

const Layout: FC<Props> = (props) => {
    return (
        <>
            <NavBar mode={props.mode} setMode={props.setMode}/>
            <Outlet/>
            <Footer/>
        </>
    );
}

export default Layout;