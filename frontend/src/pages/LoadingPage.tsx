import { CircularProgress } from "@mui/material";
import Section from "../components/Section.tsx";

const LoadingPage = () => {
    return (
        <Section id="loading-page">
            <CircularProgress />
        </Section>
    );
};

export default LoadingPage;
