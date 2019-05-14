import { useEffect } from 'react';
import { connect } from 'react-redux';
import { withSnackbar } from 'notistack';

import { removeMessage } from 'redux/models/Notification/notificationsActions';

function Notifier({ notifications, removeMessage, enqueueSnackbar }) {
    useEffect(() => {
        notifications.forEach((notification) => {
            enqueueSnackbar(notification.message, notification.options);
            removeMessage(notification.key);
        });
    }, [notifications]);

    return null;
}

export default connect(
    ({ notifications }) => ({ notifications: notifications.messages }),
    { removeMessage },
)(withSnackbar(Notifier));
