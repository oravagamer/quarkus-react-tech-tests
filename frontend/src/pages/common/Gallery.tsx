import { useParams } from "react-router-dom";
import useAxios from "axios-hooks";
import { backendUrl } from "../../data/settings.ts";
import RequestLayout from "../../components/RequestLayout.tsx";
import GallerySection from "../../components/Gallery/GallerySection.tsx";
import GalleryCard from "../../components/Gallery/GalleryCard.tsx";

const Gallery = () => {
    let { gid } = useParams();
    const [{ data, loading, error }] = useAxios<[Gallery, Picture[]]>(
        `${backendUrl}/galleries/${gid}`,
    );

    return (
        <RequestLayout loading={loading} error={error} id="gallery">
            <GallerySection galleryName={data?.[0].name}>
                {data?.[1].map((picture) => (
                    <GalleryCard picture={picture} key={picture.id} />
                ))}
            </GallerySection>
        </RequestLayout>
    );
};

export default Gallery;
