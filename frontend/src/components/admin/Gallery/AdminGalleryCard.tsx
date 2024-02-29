import { FC, useEffect, useState } from "react";
import { Card, CardMedia, Grid, Skeleton } from "@mui/material";
import { backendUrl } from "../../../data/settings.ts";
import { useSortable } from "@dnd-kit/sortable";
import { CSS } from "@dnd-kit/utilities";

interface Props extends DefaultProps {
    picture: Picture;
}

const AdminGalleryCard: FC<Props> = (props) => {
    const [pictureLoading, setPictureLoading] = useState(true);

    const {
        attributes,
        listeners,
        isDragging,
        setNodeRef,
        transform,
        transition,
    } = useSortable({ id: props.picture.id });

    useEffect(() => {}, [pictureLoading]);
    return (
        <Grid
            item
            className="image"
            xs={1}
            justifyContent="center"
            ref={setNodeRef}
            {...attributes}
            {...listeners}
            sx={{
                transition,
                transform: CSS.Transform.toString(transform),
                zIndex: isDragging ? "100" : "auto",
                opacity: isDragging ? "0.3!important" : "1!important",
            }}
        >
            <Card sx={{ minWidth: 300, height: "100%", display: "flex" }}>
                <CardMedia
                    component="img"
                    src={`${backendUrl}/picture/${props.picture.id}`}
                    alt={`Picture ${props.picture.id}`}
                    onLoad={() => setPictureLoading(false)}
                    sx={{ display: !pictureLoading ? "block" : "none", alignSelf: "center"}}
                />
                <CardMedia
                    component={Skeleton}
                    variant="rectangular"
                    sx={{
                        aspectRatio: 2,
                        height: "unset",
                        width: "100%",
                        margin: 2,
                        display: pictureLoading ? "block" : "none",
                    }}
                />
            </Card>
        </Grid>
    );
};

export default AdminGalleryCard;
