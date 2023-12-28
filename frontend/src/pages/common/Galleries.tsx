import useAxios from "axios-hooks";
import {backendUrl} from "../../settings.ts";

const Galleries = () => {
    const [{data, loading, error}, reFetch] = useAxios<Gallery[]>(`${backendUrl}/galleries`);

    if (loading) return <p>Loading...</p>
    if (error) {
        return (
            <div id="galleries">
                <h1>{error.response?.status}</h1>
                <h3>{error.message}</h3>
                <h5>{error.code}</h5>
            </div>
        )
    }

    return (
        <div id="galleries">
            <button onClick={() => reFetch()}>reFetch</button>
            <pre>{JSON.stringify(data, null, 2)}</pre>
        </div>
    );
}

export default Galleries;