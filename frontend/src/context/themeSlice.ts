import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {PaletteMode} from "@mui/material";
import Cookie from "js-cookie";
import {RootState} from "./store.ts";

interface ThemeState {
    value: PaletteMode
}

const initialValue: ThemeState = {
    value: Cookie.get("themeMode") == undefined ? "light" : Cookie.get("themeMode") as any as PaletteMode
}

export const themeSlice = createSlice({
    name: "themeMode",
    initialState: initialValue,
    reducers: {
        changeTheme: (state, action: PayloadAction<PaletteMode>) => {
            Cookie.set("themeMode", action.payload);
            state.value = action.payload;
        }
    }
});

export const { changeTheme } = themeSlice.actions;
export const themeSelector = (state: RootState) => state.theme;
export default themeSlice.reducer;