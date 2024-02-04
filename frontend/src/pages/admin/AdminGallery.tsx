import { useParams } from "react-router-dom";
import { backendUrl } from "../../data/settings.ts";
import RequestLayout from "../../components/RequestLayout.tsx";
import useFetchMyData from "../../context/fetchMyData.ts";
import AdminGalleryCard from "../../components/admin/Gallery/AdminGalleryCard.tsx";
import AdminGallerySection from "../../components/admin/Gallery/AdminGallerySection.tsx";
import {
    closestCenter,
    DndContext,
    KeyboardSensor,
    PointerSensor,
    useSensor,
    useSensors,
} from "@dnd-kit/core";
import { Grid } from "@mui/material";
import { useState } from "react";
import {
    arrayMove,
    rectSortingStrategy,
    SortableContext,
    sortableKeyboardCoordinates,
} from "@dnd-kit/sortable";

const AdminGallery = () => {
    let { gid } = useParams();
    const { myData, loading, error, setMyData } = useFetchMyData<
        [Gallery, Picture[]]
    >(`${backendUrl}/galleries/${gid}`);

    const [activeId, setActiveId] = useState(null);
    const sensors = useSensors(
        useSensor(PointerSensor),
        useSensor(KeyboardSensor, {
            coordinateGetter: sortableKeyboardCoordinates,
        }),
    );

    const handleDragStart = (event) => {
        setActiveId(event.active.id);
        console.log(event.active.id);
    };

    const handleDragEnd = (event) => {
        setActiveId(null);
        const { active, over } = event;

        setMyData((items) => {
            const oldIndex = items?.[1].indexOf(21);
            const newIndex = items?.[1].indexOf(1);

            // @ts-ignore
            return [items?.[0], arrayMove(items?.[1], oldIndex, newIndex)];
        });
        console.log(myData);
    };

    return (
        <RequestLayout loading={loading} error={error} id="admin-gallery">
            <AdminGallerySection galleryName={myData?.[0].name}>
                <DndContext
                    sensors={sensors}
                    collisionDetection={closestCenter}
                    onDragStart={handleDragStart}
                    onDragEnd={handleDragEnd}
                >
                    <Grid
                        container
                        spacing={2}
                        justifyContent="space-around"
                        alignItems="stretch"
                        columns={{ xl: 4, lg: 3, md: 2, sm: 1, xs: 1 }}
                        sx={{ paddingTop: 2, paddingBottom: 2 }}
                    >
                        <SortableContext
                            items={myData ? myData[1] : [1]}
                            strategy={rectSortingStrategy}
                        >
                            {myData?.[1].map((picture) => (
                                <AdminGalleryCard
                                    picture={picture}
                                    key={picture.id}
                                />
                            ))}
                        </SortableContext>
                    </Grid>
                </DndContext>
            </AdminGallerySection>
        </RequestLayout>
    );
};
export default AdminGallery;
