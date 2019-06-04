import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import Select from 'react-select';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import FormHelperText from '@material-ui/core/FormHelperText';

import { fetchRepositories } from 'redux/models/Repository/repositoriesActions';
import { getReposForSearch } from 'redux/models/Repository/repositoriesSelectors';
import { selectComponentsStyles } from 'Utils/commonStyles';
import SelectComponents from 'CommonComponents/SelectComponents';

const menuSelectHeight = 250;

const styles = selectComponentsStyles;

function SelectRepository({ classes, repository, setRepository, repositoryFor, direction, repositories, disabled,
    fetchRepositories }) {
    useEffect(() => {
        !repositories && fetchRepositories();
    }, []);

    const handleChange = (selected) => {
        setRepository({
            ...selected,
            error: repository.error,
        });
    };

    return (
        <Fragment>
            <Select
                autoFocus
                classes={classes}
                textFieldProps={{
                    label: `${direction} repository`,
                    InputLabelProps: {
                        shrink: true,
                    },
                }}
                options={repositories}
                components={SelectComponents}
                value={repository}
                onChange={handleChange}
                placeholder="Search a repository"
                maxMenuHeight={menuSelectHeight}
                isClearable
                isDisabled={(String(repositoryFor) === String(repository.value)) || disabled}
            />
            {repository.error
            && <FormHelperText className={classes.errorText}>{repository.error}</FormHelperText>}
            <div className={classes.divider} />
        </Fragment>
    );
}

SelectRepository.propTypes = {
    classes: PropTypes.object.isRequired,
    repository: PropTypes.object.isRequired,
    setRepository: PropTypes.func.isRequired,
    repositoryFor: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number,
    ]).isRequired,
    direction: PropTypes.string.isRequired,
    repositories: PropTypes.array,
    disabled: PropTypes.bool.isRequired,
    fetchRepositories: PropTypes.func.isRequired,
};

export default connect(
    (state, ownProps) => ({ repositories: getReposForSearch(state, ownProps.repositoryFor) }),
    { fetchRepositories },
)(withStyles(styles, { withTheme: true })(SelectRepository));
