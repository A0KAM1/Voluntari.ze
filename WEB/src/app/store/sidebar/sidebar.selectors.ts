import { createFeatureSelector, createSelector } from "@ngrx/store";
import { ISidebarState } from "./sidebar.reducer";

export const sidebarKey = 'sidebar';

export const selectSideBarState = createFeatureSelector<ISidebarState>(sidebarKey);

export const selectIsOpen = createSelector(
  selectSideBarState,
  (state: ISidebarState) => state.isOpen
)
