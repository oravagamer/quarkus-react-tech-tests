import { useParams } from "react-router-dom";
import { backendUrl } from "../../data/settings.ts";
import RequestLayout from "../../components/RequestLayout.tsx";
import useFetchMyData from "../../context/fetchMyData.ts";
import AdminGalleryCard from "../../components/admin/Gallery/AdminGalleryCard.tsx";
import AdminGallerySection from "../../components/admin/Gallery/AdminGallerySection.tsx";
import {
    closestCenter,
    DndContext,
    DragEndEvent,
    DragStartEvent,
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
import axios from "axios";

const AdminGallery = () => {
    let { gid } = useParams();
    const { myData, loading, error, setMyData } = useFetchMyData<
        [Gallery, Picture[]]
    >(`${backendUrl}/galleries/${gid}`);

    const saveChanges = async () => {
        await axios
            .put<Picture[]>(
                `${backendUrl}/galleries/${gid}/ord`,
                myData?.[1].map((value) => value.id),
            )
            .then((response) => response.data)
            .then((data) => setMyData([myData?.[0] as Gallery, data]));
        console.log(myData);
    };

    const [activeId, setActiveId] = useState<number | null>(null);
    const sensors = useSensors(
        useSensor(PointerSensor),
        useSensor(KeyboardSensor, {
            coordinateGetter: sortableKeyboardCoordinates,
        }),
    );

    const handleDragStart = (event: DragStartEvent) => {
        setActiveId(event.active.id as any as number);
    };

    const handleDragEnd = (event: DragEndEvent) => {
        setActiveId(null);
        const { active, over } = event;

        setMyData((items) => {
            if (active.id !== over?.id) {
                const oldIndex = items?.[1].findIndex(
                    (value) => value.id == active.id,
                );
                const newIndex = items?.[1].findIndex(
                    (value) => value.id == over?.id,
                );
                return [
                    items?.[0] as Gallery,
                    arrayMove(
                        items?.[1] as Picture[],
                        oldIndex as number,
                        newIndex as number,
                    ),
                ];
            }
            return myData as [Gallery, Picture[]];
        });
    };

    return (
        <RequestLayout loading={loading} error={error} id="admin-gallery">
            <AdminGallerySection
                galleryName={myData?.[0].name}
                onSaveChanges={saveChanges}
            >
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
