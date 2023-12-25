import {Route, Routes, BrowserRouter} from "react-router-dom";

import loadable from '@loadable/component';
import Layout from "./components/Layout.tsx";
import ErrorPage from "./pages/ErrorPage.tsx";
import LoadingPage from "./pages/LoadingPage.tsx";

const App = () => {
    const Home = loadComponentAsync("./pages/common/Home.tsx");
    const Gallery = loadComponentAsync("./pages/common/Gallery.tsx");
    const AdminHome = loadComponentAsync("./pages/admin/AdminHome.tsx");

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route path="">
                        <Route index element={<Home/>}/>
                        <Route path="gallery" element={<Gallery/>}/>
                    </Route>
                    <Route path="admin">
                        <Route index element={<AdminHome/>}/>
                    </Route>
                    <Route path="*" element={<ErrorPage/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

function loadComponentAsync(path: string) {
    return loadable(() => import(/* @vite-ignore*/path), {
        fallback: <LoadingPage />
    });
}

export default App
