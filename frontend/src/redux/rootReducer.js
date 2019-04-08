import { combineReducers } from 'redux';

import { repositoriesReducer } from './models/Repository/repositoriesReducer';
import { relationsReducer } from './models/Relation/relationsReducer';
import { notificationsReducer } from './models/Notification/notificationsReducer';
import { toolsReducer } from './models/Tools/toolsReducer';

export default combineReducers({
    repositories: repositoriesReducer,
    relations: relationsReducer,
    notifications: notificationsReducer,
    tools: toolsReducer,
});
