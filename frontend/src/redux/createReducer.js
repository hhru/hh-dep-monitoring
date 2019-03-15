export default function createReducer(initialState, actionsMap) {
    return (state, action) => {
        const currentState = state !== undefined ? state : initialState;
        const handler = actionsMap[action.type];
        return handler ? handler(currentState, action) : currentState;
    };
}
