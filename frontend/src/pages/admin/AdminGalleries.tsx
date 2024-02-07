import { backendUrl } from "../../data/settings.ts";
import RequestLayout from "../../components/RequestLayout.tsx";
import GalleriesCard from "../../components/common/Galleries/GalleriesCard.tsx";
import GalleriesSection from "../../components/common/Galleries/GalleriesSection.tsx";
import useAxios from "axios-hooks";

const AdminGalleries = () => {
    const [{ data, loading, error }] = useAxios<Gallery[]>(
        `${backendUrl}/galleries`,
    );
    return (
        <RequestLayout loading={loading} error={error} id="admin-galleries">
            <GalleriesSection>
                {data?.map((gallery) => (
                    <GalleriesCard gallery={gallery} key={gallery.id} />
                ))}
            </GalleriesSection>
        </RequestLayout>
    );
};

export default AdminGalleries;
