import {Link, useParams} from "react-router-dom";
import useAxios from "axios-hooks";
import {backendUrl} from "../../data/settings.ts";
import RequestLayout from "../../components/RequestLayout.tsx";

const Gallery = () => {
    let {gid} = useParams();
    const [{data, loading, error}] = useAxios<[Gallery, Picture[]]>(`${backendUrl}/galleries/${gid}`);

    return (
        <RequestLayout loading={loading} error={error} id="gallery">
            <h1>Gallery {data?.[0].name}</h1>
            <Link to="..">Go Back</Link>
            {
                data?.[1].map(picture =>
                    <div className="image" key={picture.id.toString()}>
                        <img src={`${backendUrl}/picture/${picture.id}`} alt={`Picture ${picture.id}`}/>
                        <h4>{picture.description}</h4>
                    </div>
                )
            }
        </RequestLayout>
    );
}

export default Gallery;