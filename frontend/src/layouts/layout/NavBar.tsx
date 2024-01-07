import { Link as RouterLink, NavLink as RouterNavLink } from "react-router-dom";
import {
    AppBar,
    Box,
    Container,
    Divider,
    FormControlLabel,
    FormGroup,
    IconButton,
    Link,
    List,
    ListItem,
    ListItemButton,
    ListItemText,
    SwipeableDrawer,
    Switch,
    Toolbar,
    Typography,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import { useState } from "react";
import "./NavBar.scss";
import { useAppDispatch, useAppSelector } from "../../context/hooks.ts";
import { changeTheme, themeSelector } from "../../context/themeSlice.ts";

interface Page {
    name: string;
    url: string;
}

const pages: Page[] = [
    {
        name: "Home",
        url: "/",
    },
    {
        name: "Gallery",
        url: "/gallery",
    },
    {
        name: "Calendar",
        url: "/calendar",
    },
    {
        name: "Chat",
        url: "/chat",
    },
    {
        name: "Files",
        url: "/files",
    },
];

const NavBar = () => {
    const [openedDrawer, setOpenedDrawer] = useState(false);
    const theme = useAppSelector(themeSelector);
    const dispatch = useAppDispatch();

    const toggleDrawer =
        (open: boolean) => (event: KeyboardEvent | MouseEvent) => {
            if (
                event &&
                event.type === "keydown" &&
                ((event as KeyboardEvent).key === "Tab" ||
                    (event as KeyboardEvent).key === "Shift")
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
                            flexGrow: 1,
                        }}
                    >
                        <Link
                            component={RouterLink}
                            to="/"
                            className="nav-link-custom"
                        >
                            Oravix
                        </Link>
                    </Typography>
                    {/* @ts-ignore */}
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                        onClick={toggleDrawer(true)}
                    >
                        <MenuIcon />
                    </IconButton>
                    <SwipeableDrawer
                        anchor="right"
                        /* @ts-ignore */
                        onClose={toggleDrawer(false)}
                        /* @ts-ignore */
                        onOpen={toggleDrawer(true)}
                        open={openedDrawer}
                    >
                        <Box>
                            <List>
                                <ListItem>
                                    <FormGroup sx={{ flexGrow: 0 }}>
                                        <FormControlLabel
                                            control={
                                                <Switch
                                                    checked={
                                                        theme.value == "dark"
                                                    }
                                                    onChange={() =>
                                                        theme.value == "dark"
                                                            ? dispatch(
                                                                  changeTheme(
                                                                      "light",
                                                                  ),
                                                              )
                                                            : dispatch(
                                                                  changeTheme(
                                                                      "dark",
                                                                  ),
                                                              )
                                                    }
                                                />
                                            }
                                            label={
                                                theme.value == "dark"
                                                    ? "Dark mode"
                                                    : "Light mode"
                                            }
                                        />
                                    </FormGroup>
                                </ListItem>
                                <Divider />
                                {pages.map((value) => {
                                    return (
                                        <ListItem key={value.name}>
                                            <ListItemButton
                                                component={RouterNavLink}
                                                to={value.url}
                                            >
                                                {value.name}
                                            </ListItemButton>
                                        </ListItem>
                                    );
                                })}
                                <Divider />
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
};

export default NavBar;
