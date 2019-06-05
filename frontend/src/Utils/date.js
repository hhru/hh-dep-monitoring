import moment from 'moment';

const MILLISECONDS_IN_DAY = 86400000;

export function formatDate(date) {
    const countDaysAgo = (new Date() - new Date(date)) / MILLISECONDS_IN_DAY;
    if (countDaysAgo < 30) {
        return moment(date).fromNow();
    }
    if (countDaysAgo < 120) {
        return moment(date).format('Do MMM');
    }
    return moment(date).format('Do MMM YYYY');
}

export function formatTime(date) {
    const countDaysAgo = (new Date() - new Date(date)) / MILLISECONDS_IN_DAY;
    if (countDaysAgo < 1) {
        return null;
    }
    return moment(date).format('HH:mm');
}
