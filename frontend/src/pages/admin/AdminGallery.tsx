import { useParams } from "react-router-dom";
import { backendUrl } from "../../data/settings.ts";
import RequestLayout from "../../components/RequestLayout.tsx";
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
import { useEffect, useState } from "react";
import {
    arrayMove,
    rectSortingStrategy,
    SortableContext,
    sortableKeyboardCoordinates,
} from "@dnd-kit/sortable";
import axios from "axios";

const AdminGallery = () => {
    let { gid } = useParams();
    const [error, setError] = useState<any>();
    const [loading, setLoading] = useState(true);
    const [dataOld, setDataOld] = useState<[Gallery, Picture[]] | undefined>();
    const [dataNew, setDataNew] = useState<[Gallery, Picture[]] | undefined>();
    const [changed, setChanged] = useState(false);
    const [activeId, setActiveId] = useState<number | null>(null);
    const sensors = useSensors(
        useSensor(PointerSensor),
        useSensor(KeyboardSensor, {
            coordinateGetter: sortableKeyboardCoordinates,
        }),
    );

    useEffect(() => {
        axios
            .get<[Gallery, Picture[]]>(`${backendUrl}/galleries/${gid}`)
            .then((response) => {
                setDataOld(response.data);
                setDataNew(dataOld);
                setLoading(false);
            })
            .catch((ex) => setError(ex));
    }, [loading]);

    useEffect(() => {
        setChanged(
            !(
                JSON.stringify(
                    dataNew?.[1] === undefined ? dataOld?.[1] : dataNew?.[1],
                ) === JSON.stringify(dataOld?.[1])
            ),
        );
    }, [dataNew]);

    const saveChanges = async () => {
        axios
            .put<Picture[]>(
                `${backendUrl}/galleries/${gid}/ord`,
                dataNew?.[1].map((value) => value.id),
            )
            .then((response) => response.data)
            .then((data) => {
                setDataOld([dataOld?.[0] as Gallery, data]);
                setDataNew([dataOld?.[0] as Gallery, data]);
                setChanged(false);
            })
            .catch((error) => setError(error));
    };

    const handleDragStart = (event: DragStartEvent) => {
        setActiveId(event.active.id as any as number);
    };

    const handleDragEnd = (event: DragEndEvent) => {
        setActiveId(null);
        const { active, over } = event;

        setDataNew((items) => {
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
            return dataNew as [Gallery, Picture[]];
        });
    };

    return (
        <RequestLayout loading={loading} error={error} id="admin-gallery">
            <AdminGallerySection
                galleryName={dataOld?.[0].name}
                onSaveChanges={saveChanges}
                changed={changed}
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
                            items={dataNew ? dataNew[1] : [1]}
                            strategy={rectSortingStrategy}
                        >
                            {dataNew?.[1].map((picture) => (
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
