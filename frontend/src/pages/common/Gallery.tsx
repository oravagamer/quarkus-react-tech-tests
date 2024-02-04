import { useParams } from "react-router-dom";
import { backendUrl } from "../../data/settings.ts";
import RequestLayout from "../../components/RequestLayout.tsx";
import GallerySection from "../../components/common/Gallery/GallerySection.tsx";
import GalleryCard from "../../components/common/Gallery/GalleryCard.tsx";
import GalleryBackdrop from "../../components/common/Gallery/GalleryBackdrop.tsx";
import useFetchMyData from "../../context/fetchMyData.ts";

const Gallery = () => {
    let { gid, pid } = useParams();
    const { myData, loading, error } = useFetchMyData<[Gallery, Picture[]]>(
        `${backendUrl}/galleries/${gid}`,
    );

    const toLeft = (pid: number | undefined): number | undefined => {
        try {
            return myData?.["1"][
                myData?.["1"].findIndex((value) => value.id === Number(pid)) - 1
            ].id;
        } catch (e) {
            return undefined;
        }
    };

    const toRight = (pid: number | undefined): number | undefined => {
        try {
            return myData?.["1"][
                myData?.["1"].findIndex((value) => value.id === Number(pid)) + 1
            ].id;
        } catch (e) {
            return undefined;
        }
    };

    return (
        <RequestLayout loading={loading} error={error} id="gallery">
            <GallerySection galleryName={myData?.[0].name}>
                {myData?.[1].map((picture) => (
                    <GalleryCard picture={picture} key={picture.id} />
                ))}
            </GallerySection>
            <GalleryBackdrop
                picture={myData?.["1"].find(
                    (value) => value.id === Number(pid),
                )}
                onToLeft={toLeft}
                onToRight={toRight}
            />
        </RequestLayout>
    );
};

export default Gallery;
