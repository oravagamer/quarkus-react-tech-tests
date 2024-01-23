import { FC, useState } from "react";
import { Backdrop, Button, Grid } from "@mui/material";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import { useNavigate } from "react-router-dom";
import GalleryImage from "./GalleryImage.tsx";

interface Props extends DefaultProps {
    onToLeft: (pid: number | undefined) => number | undefined;
    onToRight: (pid: number | undefined) => number | undefined;
    picture: Picture | undefined;
}

const GalleryBackdrop: FC<Props> = (props) => {
    const [next, setNext] = useState(false);
    const navigate = useNavigate();

    return (
        <Backdrop
            open={props.picture != undefined}
            onClick={() => {
                if (!next) {
                    navigate("./../");
                }
            }}
        >
            <Grid container columns={7} alignItems="stretch">
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
                        onClick={() =>
                            navigate(
                                `./../${
                                    props.onToLeft(props.picture?.id) !==
                                    undefined
                                        ? props.onToLeft(props.picture?.id)
                                        : props.picture?.id
                                }`,
                            )
                        }
                    >
                        <ArrowBackIosIcon />
                    </Button>
                </Grid>
                <Grid
                    item
                    xs={5}
                    justifyContent="center"
                    alignItems="center"
                    display="flex"
                    minWidth={50}
                    minHeight={50}
                >
                    <GalleryImage
                        pid={props.picture?.id}
                        key={props.picture?.id}
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
                        onClick={() =>
                            navigate(
                                `./../${
                                    props.onToRight(props.picture?.id) !==
                                    undefined
                                        ? props.onToRight(props.picture?.id)
                                        : props.picture?.id
                                }`,
                            )
                        }
                    >
                        <ArrowForwardIosIcon />
                    </Button>
                </Grid>
            </Grid>
        </Backdrop>
    );
};

export default GalleryBackdrop;
