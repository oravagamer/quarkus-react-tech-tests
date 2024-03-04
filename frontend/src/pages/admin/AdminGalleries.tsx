import { backendUrl } from "../../data/settings.ts";
import RequestLayout from "../../components/RequestLayout.tsx";
import GalleriesCard from "../../components/common/Galleries/GalleriesCard.tsx";
import GalleriesSection from "../../components/common/Galleries/GalleriesSection.tsx";
import { useEffect, useState } from "react";
import axios from "axios";

const AdminGalleries = () => {
    const [error, setError] = useState<any>();
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState<Gallery[] | undefined>();

    useEffect(() => {
        axios
            .get(`${backendUrl}/galleries`)
            .then((res) => {
                setData(res.data);
                setLoading(false);
            })
            .catch((err) => setError(err));
    }, [loading]);
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
