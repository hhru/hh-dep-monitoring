import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import Divider from '@material-ui/core/Divider';
import List from '@material-ui/core/List';
import Paper from '@material-ui/core/Paper';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';

import { fetchRepositoriesPage, clearRepositoriesPages,
    setSearchString } from 'redux/models/Repository/repositoriesActions';
import { getRepositoriesPages } from 'redux/models/Repository/repositoriesSelectors';
import { PER_PAGE_VARIANTS, DEFAULT_PER_PAGE_VARIANT, SEARCH_REQUEST_THRESHOLD } from 'Utils/constants';
import { flexInlineContainer, genericPaper } from 'Utils/commonStyles';
import PaginationWidget from 'MainBlock/Pagination/PaginationWidget';
import RepositoryItem from './RepositoryItem';
import FetchingParams from './FetchingParams/FetchingParams';

const styles = () => ({
    genericPaper,
    repositoriesHeader: flexInlineContainer,
    title: {
        flexGrow: 1,
        paddingTop: '10px',
        alignSelf: 'flex-start',
    },
});

function Repositories({ classes, repositories, pageCount, fetchRepositoriesPage, clearRepositoriesPages,
    searchString, setSearchString }) {
    const [page, setPage] = useState(0);
    const [perPage, setPerPage] = useState(PER_PAGE_VARIANTS[DEFAULT_PER_PAGE_VARIANT]);
    const [requestThresholdTimer, setRequestThresholdTimer] = useState(null);
    const [sortAttributes, setSortAttributes] = useState([]);
    const [sortingOrderText, setSortingOrderText] = useState('');

    useEffect(() => {
        !repositories[page] && fetchRepositoriesPage(perPage, searchString, sortAttributes, page);
    }, [page, perPage]);

    const handlePerPageChange = (event) => {
        clearRepositoriesPages();
        const newPerPage = event.target.value;
        const newPage = Math.floor((page) * perPage / newPerPage);
        setPerPage(newPerPage);
        setPage(newPage);
    };

    const handleSearchStringChange = (event) => {
        const { value } = event.target;
        setSearchString(value);

        if (requestThresholdTimer) {
            clearTimeout(requestThresholdTimer);
        }

        setRequestThresholdTimer(setTimeout(
            () => {
                clearRepositoriesPages();
                fetchRepositoriesPage(perPage, value, sortAttributes);
                setPage(0);
            },
            SEARCH_REQUEST_THRESHOLD,
        ));
    };

    const calculateOrderStrings = (sortedAttributes) => {
        let sortingOrderText = '';
        sortedAttributes.forEach((attribute) => {
            sortingOrderText += `by ${attribute.label} (${attribute.state})`;
            sortedAttributes.indexOf(attribute) + 1 !== sortedAttributes.length && (sortingOrderText += ', then ');
        });
        return sortingOrderText;
    };

    const handleOrderParamsChange = () => {
        const newSortingOrderText = calculateOrderStrings(sortAttributes);
        if (newSortingOrderText === sortingOrderText) {
            return;
        }
        setSortingOrderText(newSortingOrderText);
        clearRepositoriesPages();
        fetchRepositoriesPage(perPage, searchString, sortAttributes, page);
    };

    return (
        <Paper className={classes.genericPaper}>
            <div className={classes.repositoriesHeader}>
                <Typography variant="h4" className={classes.title}>
                    Repositories
                </Typography>
                <FetchingParams
                    handleSearchStringChange={handleSearchStringChange}
                    handleOrderParamsChange={handleOrderParamsChange}
                    sortAttributes={sortAttributes}
                    setSortAttributes={setSortAttributes}
                />
            </div>
            {sortingOrderText !== ''
                && <Typography variant="caption" color="primary">{`Sort ${sortingOrderText}`}</Typography>
            }
            {repositories[page] && (
                <List>
                    {repositories[page].items && repositories[page].items.map(repository => (
                        <Fragment key={repository.repositoryId}>
                            <RepositoryItem repository={repository} />
                            <Divider />
                        </Fragment>
                    ))}
                </List>
            )}
            {!!pageCount && (
                <PaginationWidget
                    page={page}
                    pages={pageCount}
                    perPage={perPage}
                    onPageChange={setPage}
                    onPerPageChange={handlePerPageChange}
                />
            )}
        </Paper>
    );
}

Repositories.propTypes = {
    classes: PropTypes.object.isRequired,
    repositories: PropTypes.object,
    pageCount: PropTypes.number,
    fetchRepositoriesPage: PropTypes.func.isRequired,
    clearRepositoriesPages: PropTypes.func.isRequired,
    searchString: PropTypes.string.isRequired,
    setSearchString: PropTypes.func.isRequired,
};

export default connect(
    state => ({
        repositories: getRepositoriesPages(state),
        pageCount: state.repositories.pageCount,
        searchString: state.repositories.searchString,
    }),
    {
        fetchRepositoriesPage,
        clearRepositoriesPages,
        setSearchString,
    },
)(withStyles(styles)(Repositories));
