import { FC, useEffect, useState } from "react";
import {
    Button,
    Card,
    CardActions,
    CardContent,
    CardMedia,
    Grid,
    Skeleton,
    Typography,
} from "@mui/material";
import { backendUrl } from "../../../data/settings.ts";
import { Link } from "react-router-dom";

interface Props extends DefaultProps {
    prefix?: string;
    gallery: Gallery;
}

const GalleriesCard: FC<Props> = (props) => {
    const [pictureLoading, setPictureLoading] = useState(true);

    useEffect(() => {}, [pictureLoading]);

    return (
        <Grid item xs={1} className="gallery" justifyContent="center">
            <Card sx={{ minWidth: 300, height: "100%" }}>
                <CardMedia
                    component="img"
                    image={`${backendUrl}/picture/${
                        props.gallery?.thumbnail === null
                            ? 1
                            : props.gallery?.thumbnail
                    }`}
                    onLoad={() => setPictureLoading(false)}
                    alt={`${props.gallery.name} thumbnail`}
                    sx={{ display: !pictureLoading ? "block" : "none" }}
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
                <CardContent>
                    <Typography variant="h5" component="div">
                        {props.gallery?.name}
                    </Typography>
                    <Typography variant="body2" component="div">
                        {props.gallery?.description}
                    </Typography>
                </CardContent>
                <CardActions>
                    <Button
                        size="small"
                        color="primary"
                        component={Link}
                        to={`${props.gallery?.id.toString()}/${
                            props.prefix !== undefined ? props.prefix : ""
                        }`}
                    >
                        More
                    </Button>
                </CardActions>
            </Card>
        </Grid>
    );
};

export default GalleriesCard;
