import { useRouteError } from "react-router-dom";
import NavBar from "../layouts/layout/NavBar.tsx";
import Footer from "../layouts/layout/Footer.tsx";
import Section from "../components/Section.tsx";

const ErrorPage = () => {
    const error = useRouteError();

    return (
        <>
            <NavBar />
            <Section id="error-page">
                {/* @ts-ignore */}
                <h1>{error.status}</h1>
                {/* @ts-ignore */}
                <h4>{error.statusText || error.message}</h4>
            </Section>
            <Footer />
        </>
    );
};

export default ErrorPage;
