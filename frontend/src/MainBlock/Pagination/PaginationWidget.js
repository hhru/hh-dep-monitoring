import React from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import MenuItem from '@material-ui/core/MenuItem';
import Select from '@material-ui/core/Select';

import { PER_PAGE_VARIANTS } from 'Utils/constants';
import { secondaryItemColor, flexInlineContainer } from 'Utils/commonStyles';
import PaginationButton from './PaginationButton';

const styles = theme => ({
    widgetContainer: flexInlineContainer,
    perPageLabel: {
        padding: '18px',
        color: secondaryItemColor,
        [theme.breakpoints.down('sm')]: {
            padding: '0px 10px 0px 0px',
        },
    },
    perPageSelect: {
        color: secondaryItemColor,
    },
});

const MAX_BUTTONS_COUNT = 12;
const FIRST_PAGE = 0;

function PaginationWidget({ classes, page = 0, pages, perPage, onPageChange, onPerPageChange }) {
    let pagination = {};
    let paginationButtonsList = [];

    const handlePageChange = (newPage) => {
        (newPage !== page) && onPageChange(newPage);
    };

    const regenerateCache = () => {
        if (!pages || pages < 2) {
            pagination = { pages, page };
            paginationButtonsList = [];
            return;
        }

        const lastPage = pages - 1;

        paginationButtonsList.push(
            <PaginationButton
                key={FIRST_PAGE}
                page={FIRST_PAGE}
                active={page === FIRST_PAGE}
                onClick={handlePageChange}
            />,
        );

        const nearestPagesCount = (MAX_BUTTONS_COUNT - 5) / 2;
        const nearestPagesCountLow = Math.floor(nearestPagesCount);
        const nearestPagesCountHigh = Math.ceil(nearestPagesCount);
        const leftoverPagesLow = Math.max(0, nearestPagesCountLow - page + 2);
        const leftoverPagesHigh = Math.max(0, nearestPagesCountHigh + page - lastPage + 2);
        const firstVisiblePage = Math.max(2, page - nearestPagesCountLow - leftoverPagesHigh);
        const lastVisiblePage = Math.min(lastPage - 2, page + nearestPagesCountHigh + leftoverPagesLow);

        if (firstVisiblePage > 2) {
            paginationButtonsList.push(<PaginationButton key="ellipsis-low" disabled />);
        } else {
            paginationButtonsList.push(
                <PaginationButton
                    key={FIRST_PAGE + 1}
                    page={FIRST_PAGE + 1}
                    active={page === FIRST_PAGE + 1}
                    onClick={handlePageChange}
                />,
            );
        }

        for (let i = firstVisiblePage; i <= lastVisiblePage; i++) {
            paginationButtonsList.push(
                <PaginationButton
                    key={i}
                    page={i}
                    active={page === i}
                    onClick={handlePageChange}
                />,
            );
        }

        if (lastPage > 2) {
            if (lastVisiblePage < lastPage - 2) {
                paginationButtonsList.push(<PaginationButton key="ellipsis-high" disabled />);
            } else {
                paginationButtonsList.push(
                    <PaginationButton
                        key={lastPage - 1}
                        page={lastPage - 1}
                        active={page === lastPage - 1}
                        onClick={handlePageChange}
                    />,
                );
            }
        }

        if (lastPage > 1) {
            paginationButtonsList.push(
                <PaginationButton
                    key={lastPage}
                    page={lastPage}
                    active={page === lastPage}
                    onClick={handlePageChange}
                />,
            );
        }

        pagination = { pages, page };
    };

    return (
        <div className={classes.widgetContainer}>
            <div>
                <span className={classes.perPageLabel}>Per page</span>
                <Select className={classes.perPageSelect} value={perPage} onChange={onPerPageChange}>
                    {PER_PAGE_VARIANTS && PER_PAGE_VARIANTS.map(item => (
                        <MenuItem key={item} value={item}>
                            {item}
                        </MenuItem>
                    ))}
                </Select>
            </div>
            {(pagination.page !== page || pagination.pages !== pages) && regenerateCache()}
            <div>{paginationButtonsList}</div>
        </div>
    );
}

PaginationWidget.propTypes = {
    classes: PropTypes.object.isRequired,
    page: PropTypes.number.isRequired,
    pages: PropTypes.number.isRequired,
    perPage: PropTypes.number.isRequired,
    onPageChange: PropTypes.func.isRequired,
    onPerPageChange: PropTypes.func.isRequired,
};

export default withStyles(styles)(PaginationWidget);
