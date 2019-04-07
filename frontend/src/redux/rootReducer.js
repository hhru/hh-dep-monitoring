import { combineReducers } from 'redux';

import { repositoriesReducer } from './models/Repository/repositoriesReducer';
import { relationsReducer } from './models/Relation/relationsReducer';

export default combineReducers({
    repositories: repositoriesReducer,
    relations: relationsReducer,
});
