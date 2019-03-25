import moment from 'moment';

export default function formatDate(date) {
    const millisecondsInDay = 86400000;
    const countDaysAgo = (new Date() - new Date(date)) / millisecondsInDay;
    if (countDaysAgo < 30) {
        return moment(date).fromNow();
    }
    if (countDaysAgo < 120) {
        return moment(date).format('Do MMM');
    }
    return moment(date).format('Do MMM YYYY');
}
