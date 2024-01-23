import { backendUrl } from "../../../data/settings.ts";
import { Box, CircularProgress } from "@mui/material";
import { FC, useState } from "react";

interface Props extends DefaultProps {
    pid: number | undefined;
}

const GalleryImage: FC<Props> = (props) => {
    const [pictureLoading, setPictureLoading] = useState(true);
    return (
        <>
            <Box
                sx={{
                    width: "100%",
                    height: "auto",
                    display: !pictureLoading ? "block" : "none",
                }}
                src={
                    props.pid !== undefined
                        ? `${backendUrl}/picture/${props.pid}`
                        : `${backendUrl}/picture/1`
                }
                alt={`Picture ${props.pid}`}
                component="img"
                onLoad={() => setPictureLoading(false)}
            />
            <Box
                component={CircularProgress}
                sx={{
                    display: pictureLoading ? "block" : "none",
                }}
            />
        </>
    );
};

export default GalleryImage;
