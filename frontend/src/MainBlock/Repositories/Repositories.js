import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import Divider from '@material-ui/core/Divider';
import List from '@material-ui/core/List';
import Paper from '@material-ui/core/Paper';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';

import { fetchRepositoriesPage, clearRepositoriesPages } from 'redux/models/Repository/repositoriesActions';
import { getRepositoriesPages } from 'redux/models/Repository/repositoriesSelectors';
import { PER_PAGE_VARIANTS, DEFAULT_PER_PAGE_VARIANT } from 'Utils/constants';
import PaginationWidget from 'MainBlock/Pagination/PaginationWidget';
import { genericPaper } from 'Utils/commonStyles';
import RepositoryItem from './RepositoryItem';

const styles = genericPaper;

function Repositories({ classes, repositories, pageCount, fetchRepositoriesPage, clearRepositoriesPages }) {
    const [page, setPage] = useState(0);
    const [perPage, setPerPage] = useState(PER_PAGE_VARIANTS[DEFAULT_PER_PAGE_VARIANT]);

    useEffect(() => {
        !repositories[page] && fetchRepositoriesPage(page, perPage);
    }, [page, perPage]);

    const handlePerPageChange = (event) => {
        clearRepositoriesPages();
        const newPerPage = event.target.value;
        const newPage = Math.floor((page) * perPage / newPerPage);
        setPerPage(newPerPage);
        setPage(newPage);
    };

    return (
        <Paper className={classes.root}>
            <Typography variant="h4">
                Repositories
            </Typography>
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
            {pageCount && (
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
};

export default connect(
    state => ({
        repositories: getRepositoriesPages(state),
        pageCount: state.repositories.pageCount,
    }),
    {
        fetchRepositoriesPage,
        clearRepositoriesPages,
    },
)(withStyles(styles)(Repositories));
