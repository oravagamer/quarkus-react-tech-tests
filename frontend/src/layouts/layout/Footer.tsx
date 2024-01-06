import {Container} from "@mui/material";
import {ReactNode} from "react";

const Footer = () => {
    return (
        <Container
        maxWidth={false}
        disableGutters
        component={(props: {children: ReactNode}) => <footer>{props.children}</footer>}>
            Footer
        </Container>
    );
}

export default Footer;