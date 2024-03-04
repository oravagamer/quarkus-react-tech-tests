import { Card, CardMedia, Grid, Skeleton } from "@mui/material";
import { FC, useEffect, useState } from "react";
import { backendUrl } from "../../../data/settings.ts";
import { useNavigate } from "react-router-dom";

interface Props extends DefaultProps {
    picture: Picture;
}

const GalleryCard: FC<Props> = (props) => {
    const [pictureLoading, setPictureLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {}, [pictureLoading]);
    return (
        <Grid item className="image" xs={1} justifyContent="center">
            <Card
                sx={{
                    minWidth: 300,
                    height: "100%",
                    display: "flex",
                    justifyContent: "center",
                }}
            >
                <CardMedia
                    component="img"
                    src={`${backendUrl}/picture/${props.picture.id}`}
                    alt={`Picture ${props.picture.id}`}
                    onLoad={() => setPictureLoading(false)}
                    onClick={() => navigate(`./${props.picture.id}`)}
                    sx={{
                        display: !pictureLoading ? "block" : "none",
                        alignSelf: "center",
                    }}
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

export default GalleryCard;
