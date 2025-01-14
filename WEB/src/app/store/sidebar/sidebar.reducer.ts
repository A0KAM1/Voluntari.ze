import { createReducer, on } from '@ngrx/store';
import { closeSideBar, toggleSidebar } from './sidebar.actions';

export interface ISidebarState {
  isOpen: boolean;
}

export const initialState: ISidebarState = {
  isOpen: false,
};

export const sidebarReducer = createReducer<ISidebarState>(
  initialState,
  on(toggleSidebar, state => ({ ...state, isOpen: !state.isOpen })),
  on(closeSideBar, state => ({ ...state, isOpen: false })),
);
