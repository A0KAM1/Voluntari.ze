import { createReducer, on } from "@ngrx/store";
import { toggleSidebar } from "./sidebar.actions";

export const initialState = false;

export const sidebarReducer = createReducer(
  initialState,
  on(toggleSidebar, (state) => !state),
);
