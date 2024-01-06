import {RouterProvider, createBrowserRouter} from "react-router-dom";

import loadable from "@loadable/component";
import Layout from "./layouts/layout/Layout.tsx";
import ErrorPage from "./pages/ErrorPage.tsx";
import LoadingPage from "./pages/LoadingPage.tsx";
import {Box, createTheme, CssBaseline, PaletteMode, ThemeProvider} from "@mui/material";
import {useCookies} from "react-cookie";


const App = () => {
    const Home = loadComponentAsync("./pages/common/Home.tsx");
    const Galleries = loadComponentAsync("./pages/common/Galleries.tsx");
    const Gallery = loadComponentAsync("./pages/common/Gallery.tsx");
    const AdminHome = loadComponentAsync("./pages/admin/AdminHome.tsx");

    const [cookies, setCookies] = useCookies(["themeMode"]);

    const setMode = (mode: PaletteMode) => setCookies("themeMode", mode);

    if (cookies["themeMode"] === null) {
        setCookies("themeMode", "light");
    }

    const darkTheme = createTheme({
        palette: {
            mode: cookies["themeMode"],
        }
    })

    const router = createBrowserRouter([
        {
            path: "",
            element: <Layout mode={cookies["themeMode"]} setMode={setMode}/>,
            errorElement: <ErrorPage/>,
            children: [
                {
                    path: "",
                    children: [
                        {
                            index: true,
                            element: <Home/>
                        },
                        {
                            path: "gallery",
                            children: [
                                {
                                    index: true,
                                    element: <Galleries/>
                                },
                                {
                                    path: ":gid",
                                    element: <Gallery/>
                                }
                            ]
                        }
                    ]
                },
                {
                    path: "admin",
                    children: [
                        {
                            index: true,
                            element: <AdminHome/>
                        }
                    ]
                }
            ]
        }
    ]);

    return (
        <ThemeProvider theme={darkTheme}>
            <Box sx={{flexGrow: 1}}>
                <CssBaseline />
                <RouterProvider router={router}/>
            </Box>
        </ThemeProvider>
    );
}

function loadComponentAsync(path: string) {
    return loadable(() => import(/* @vite-ignore*/path), {
        fallback: <LoadingPage />
    });
}

export default App
