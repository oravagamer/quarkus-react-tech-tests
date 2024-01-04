import useAxios from "axios-hooks";
import {backendUrl} from "../../settings.ts";
import {Link} from "react-router-dom";
import RequestLayout from "../../components/RequestLayout.tsx";
import './Galleries.scss';

const Galleries = () => {
    const [{data, loading, error}] = useAxios<Gallery[]>(`${backendUrl}/galleries`);

    return (
        <RequestLayout loading={loading} error={error} id="galleries">
            {
                data?.map(gallery =>
                    <div className="gallery" key={gallery.id}>
                        <img src={`${backendUrl}/picture/${gallery?.thumbnail}`} alt={`${gallery.name} thumbnail`} />
                        <h1>{gallery?.name}</h1>
                        <h4>{gallery?.description}</h4>
                        <Link to={gallery?.id.toString()}>More</Link>
                    </div>)
            }
        </RequestLayout>
    );
}

export default Galleries;