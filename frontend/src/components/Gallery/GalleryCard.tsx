import { Card, CardMedia, Grid, Skeleton } from "@mui/material";
import { FC, useEffect, useState } from "react";
import { backendUrl } from "../../data/settings.ts";
import GalleryBackdrop from "./GalleryBackdrop.tsx";

interface Props {
    id?: string;
    className?: string;
    picture: Picture;
}

const GalleryCard: FC<Props> = (props) => {
    const [pictureLoading, setPictureLoading] = useState(true);
    const [open, setOpen] = useState(false);

    useEffect(() => {}, [pictureLoading]);
    return (
        <Grid item className="image" xs={1} justifyContent="center">
            <Card sx={{ minWidth: 300, height: "100%" }}>
                <CardMedia
                    component="img"
                    src={`${backendUrl}/picture/${props.picture.id}`}
                    alt={`Picture ${props.picture.id}`}
                    onLoad={() => setPictureLoading(false)}
                    sx={{ display: !pictureLoading ? "block" : "none" }}
                    onClick={() => setOpen(true)}
                />
                <GalleryBackdrop
                    picture={props.picture}
                    open={open}
                    onClick={setOpen}
                />
                <CardMedia
                    component={Skeleton}
                    variant="rectangular"
                    sx={{
                        aspectRatio: 2,
                        height: "unset",
                        width: "auto",
                        margin: 2,
                        display: pictureLoading ? "block" : "none",
                    }}
                />
            </Card>
        </Grid>
    );
};

export default GalleryCard;
