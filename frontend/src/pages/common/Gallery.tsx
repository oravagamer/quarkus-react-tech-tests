import {useParams} from "react-router-dom";

const Gallery = () => {
    let { gid } = useParams();

    return (
        <div id="gallery">Gallery {gid}</div>
    );
}

export default Gallery;