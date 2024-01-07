import { Box } from "@mui/material";
import { useRouteError } from "react-router-dom";
import NavBar from "../layouts/layout/NavBar.tsx";
import Footer from "../layouts/layout/Footer.tsx";

const ErrorPage = () => {
    const error = useRouteError();

    return (
        <>
            <NavBar />
            <Box component="section" id="error-page">
                {/* @ts-ignore */}
                <h1>{error.status}</h1>
                {/* @ts-ignore */}
                <h4>{error.statusText || error.message}</h4>
            </Box>
            <Footer />
        </>
    );
};

export default ErrorPage;
