import {useRouteError} from "react-router-dom";

const ErrorPage = () => {
    const error = useRouteError();

    return (
        <section className="error-page">
            <h1>{error.status}</h1>
            <h4>{error.statusText || error.message}</h4>
        </section>
    );
}

export default ErrorPage;