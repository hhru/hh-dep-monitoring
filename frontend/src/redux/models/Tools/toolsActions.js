export const SET_ADMIN_MODE = 'SET_ADMIN_MODE';

export const setAdminModeAction = option => ({
    type: SET_ADMIN_MODE,
    payload: {
        option,
    },
});

export function setAdminMode(option) {
    return (dispatch) => {
        dispatch(setAdminModeAction(option));
    };
}
