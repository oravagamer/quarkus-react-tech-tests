import { useParams } from "react-router-dom";
import useAxios from "axios-hooks";
import { backendUrl } from "../../data/settings.ts";
import RequestLayout from "../../components/RequestLayout.tsx";
import GallerySection from "../../components/common/Gallery/GallerySection.tsx";
import GalleryCard from "../../components/common/Gallery/GalleryCard.tsx";
import GalleryBackdrop from "../../components/common/Gallery/GalleryBackdrop.tsx";

const Gallery = () => {
    let { gid, pid } = useParams();
    const [{ data, loading, error }] = useAxios<[Gallery, Picture[]]>(
        `${backendUrl}/galleries/${gid}`,
    );

    const toLeft = (pid: number | undefined): number | undefined => {
        try {
            return data?.["1"][
                data?.["1"].findIndex((value) => value.id === Number(pid)) - 1
            ].id;
        } catch (e) {
            return undefined;
        }
    };

    const toRight = (pid: number | undefined): number | undefined => {
        try {
            return data?.["1"][
                data?.["1"].findIndex((value) => value.id === Number(pid)) + 1
            ].id;
        } catch (e) {
            return undefined;
        }
    };

    return (
        <RequestLayout loading={loading} error={error} id="gallery">
            <GallerySection galleryName={data?.[0].name}>
                {data?.[1].map((picture) => (
                    <GalleryCard picture={picture} key={picture.id} />
                ))}
            </GallerySection>
            <GalleryBackdrop
                picture={data?.["1"].find((value) => value.id === Number(pid))}
                onToLeft={toLeft}
                onToRight={toRight}
            />
        </RequestLayout>
    );
};

export default Gallery;
