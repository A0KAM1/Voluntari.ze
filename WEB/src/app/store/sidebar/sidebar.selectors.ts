import { createFeatureSelector, State } from "@ngrx/store";

export const sidebarKey = 'sidebar';

export const selectSideBar = createFeatureSelector<State>(sidebarKey);
