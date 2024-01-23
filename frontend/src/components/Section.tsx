import { Box } from "@mui/material";
import { FC } from "react";

interface Props extends DefaultProps {}

const Section: FC<Props> = (props) => {
    return (
        <Box component="section" id={props.id} className={props.className}>
            {props.children}
        </Box>
    );
};

export default Section;
