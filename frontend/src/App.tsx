import { createBrowserRouter, Outlet, RouterProvider } from "react-router-dom";

import loadable from "@loadable/component";
import Layout from "./layouts/layout/Layout.tsx";
import ErrorPage from "./pages/ErrorPage.tsx";
import LoadingPage from "./pages/LoadingPage.tsx";
import { Box, createTheme, CssBaseline, ThemeProvider } from "@mui/material";
import { useAppSelector } from "./context/hooks.ts";
import { themeSelector } from "./context/themeSlice.ts";
import LoginRequired from "./components/keycloak/LoginRequired.tsx";

const App = () => {
    const Home = loadComponentAsync("./pages/common/Home.tsx");
    const Galleries = loadComponentAsync("./pages/common/Galleries.tsx");
    const Gallery = loadComponentAsync("./pages/common/Gallery.tsx");
    const AdminHome = loadComponentAsync("./pages/admin/AdminHome.tsx");
    const AdminGalleries = loadComponentAsync(
        "./pages/admin/AdminGalleries.tsx",
    );

    const theme = useAppSelector(themeSelector);

    const darkTheme = createTheme({
        palette: {
            mode: theme.value,
        },
    });

    const router = createBrowserRouter([
        {
            path: "",
            element: <Layout />,
            errorElement: <ErrorPage />,
            children: [
                {
                    path: "",
                    children: [
                        {
                            index: true,
                            element: <Home />,
                        },
                        {
                            path: "gallery",
                            children: [
                                {
                                    index: true,
                                    element: <Galleries />,
                                },
                                {
                                    path: ":gid/:pid?",
                                    element: <Gallery />,
                                },
                            ],
                        },
                    ],
                },
                {
                    path: "admin",
                    element: (
                        <LoginRequired>
                            <Outlet />
                        </LoginRequired>
                    ),
                    children: [
                        {
                            index: true,
                            element: <AdminHome />,
                        },
                        {
                            path: "galleries",
                            children: [
                                {
                                    index: true,
                                    element: <AdminGalleries />,
                                },
                                {
                                    path: ":gid",
                                    children: [
                                        {
                                            path: "edit",
                                        },
                                        {
                                            index: true,
                                        },
                                    ],
                                },
                            ],
                        },
                    ],
                },
            ],
        },
    ]);

    return (
        <ThemeProvider theme={darkTheme}>
            <Box>
                <CssBaseline />
                <RouterProvider router={router} />
            </Box>
        </ThemeProvider>
    );
};

function loadComponentAsync(path: string) {
    return loadable(() => import(/* @vite-ignore*/ path), {
        fallback: <LoadingPage />,
    });
}

export default App;
