import {NavLink as RouterNavLink, Link as RouterLink} from 'react-router-dom';
import {
    AppBar,
    Container,
    FormControlLabel,
    FormGroup,
    PaletteMode,
    Switch,
    Toolbar,
    Typography,
    Link, IconButton, SwipeableDrawer, Box, List, ListItem, Divider, ListItemButton, ListItemText
} from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import {FC, useState} from "react";

interface Page {
    name: string,
    url: string
}

const pages: Page[] = [
    {
        name: "Home",
        url: "/"
    },
    {
        name: "Gallery",
        url: "/gallery"
    },
    {
        name: "Calendar",
        url: "/calendar"
    },
    {
        name: "Chat",
        url: "/chat"
    },
    {
        name: "Files",
        url: "/files"
    }
]

interface Props {
    mode: PaletteMode,
    setMode: (mode: PaletteMode) => void,
}

const NavBar: FC<Props> = (props) => {
    const [openedDrawer, setOpenedDrawer] = useState(false);

    const toggleDrawer =
        (open: boolean) =>
            (event: KeyboardEvent | MouseEvent) => {
                if (
                    event &&
                    event.type === 'keydown' &&
                    ((event as KeyboardEvent).key === 'Tab' ||
                        (event as KeyboardEvent).key === 'Shift')
                ) {
                    return;
                }

                setOpenedDrawer(open);
            };

    return (
        <AppBar id="nav-bar" position="static">
            <Container>
                <Toolbar>
                    <Typography
                        variant="h6"
                        sx={{
                            flexGrow: 1
                        }}>
                        <Link
                            component={RouterLink}
                            to="/"
                            className="nav-link-custom">
                            Oravix
                        </Link>
                    </Typography>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{mr: 2}}
                        onClick={toggleDrawer(true)}>
                        <MenuIcon/>
                    </IconButton>
                    <SwipeableDrawer
                        anchor="right"
                        onClose={toggleDrawer(false)}
                        onOpen={toggleDrawer(true)}
                        open={openedDrawer}
                    >
                        <Box>
                            <List>
                                <ListItem>
                                    <FormGroup sx={{flexGrow: 0}}>
                                        <FormControlLabel
                                            control={
                                                <Switch
                                                    checked={props.mode == "dark"}
                                                    onChange={() => props.mode == "dark" ? props.setMode("light") : props.setMode("dark")}/>
                                            }
                                            label={props.mode == "dark" ? "Dark mode" : "Light mode"}/>
                                    </FormGroup>
                                </ListItem>
                                <Divider/>
                                {
                                    pages.map(value => {
                                        return (
                                            <ListItem key={value.name}>
                                                <ListItemButton
                                                component={RouterNavLink}
                                                to={value.url}>
                                                    {value.name}
                                                </ListItemButton>
                                            </ListItem>
                                        );
                                    })
                                }
                                <Divider/>
                                <ListItem>
                                    <ListItemButton>
                                        <ListItemText>Login</ListItemText>
                                    </ListItemButton>
                                </ListItem>
                            </List>
                        </Box>
                    </SwipeableDrawer>
                </Toolbar>
            </Container>
        </AppBar>
    );
}

export default NavBar;