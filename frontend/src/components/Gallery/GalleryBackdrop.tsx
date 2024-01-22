import { FC, useState } from "react";
import { Backdrop, Box, Button, Grid } from "@mui/material";
import { backendUrl } from "../../data/settings.ts";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";

interface Props {
    id?: string;
    className?: string;
    open: boolean;
    onClick: (prop: boolean) => void;
    picture: Picture;
}

const GalleryBackdrop: FC<Props> = (props) => {
    const [next, setNext] = useState(false);

    return (
        <Backdrop
            open={props.open}
            onClick={() => {
                if (!next) {
                    props.onClick(false);
                }
            }}
        >
            <Grid container columns={7} alignItems="center">
                <Grid
                    item
                    xs={1}
                    justifyContent="center"
                    alignContent="center"
                    display="flex"
                >
                    <Button
                        type="button"
                        onPointerEnter={() => setNext(true)}
                        onPointerLeave={() => setNext(false)}
                    >
                        <ArrowBackIosIcon />
                    </Button>
                </Grid>
                <Grid
                    item
                    xs={5}
                    justifyContent="center"
                    alignContent="center"
                    display="flex"
                >
                    <Box
                        sx={{ width: "100%", height: "auto" }}
                        src={`${backendUrl}/picture/${props.picture.id}`}
                        alt={`Picture ${props.picture.id}`}
                        component="img"
                    />
                </Grid>
                <Grid
                    item
                    xs={1}
                    justifyContent="center"
                    alignContent="center"
                    display="flex"
                >
                    <Button
                        type="button"
                        onPointerEnter={() => setNext(true)}
                        onPointerLeave={() => setNext(false)}
                    >
                        <ArrowForwardIosIcon />
                    </Button>
                </Grid>
            </Grid>
        </Backdrop>
    );
};

export default GalleryBackdrop;
