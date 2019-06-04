import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import Select from 'react-select';

import { withStyles } from '@material-ui/core/styles';

import { fetchArtifacts } from 'redux/models/Artifact/artifactsActions';
import { selectWithButtonStyles } from 'Utils/commonStyles';
import SelectComponents from 'CommonComponents/SelectComponents';
import Button from '@material-ui/core/es/Button/Button';

const menuSelectHeight = 300;

const styles = selectWithButtonStyles;

function ArtifactsSelect({ classes, artifacts, fetchArtifacts }) {
    const [artifact, setArtifact] = useState(null);

    useEffect(() => {
        artifacts.length === 0 && fetchArtifacts();
    }, []);

    const handleChange = (selected) => {
        setArtifact(selected);
    };

    return (
        <div className={classes.selectWithButton}>
            <div className={classes.select}>
                <Select
                    classes={classes}
                    textFieldProps={{
                        InputLabelProps: {
                            shrink: true,
                        },
                    }}
                    options={artifacts}
                    components={SelectComponents}
                    value={artifact}
                    onChange={handleChange}
                    placeholder="Artifact name"
                    maxMenuHeight={menuSelectHeight}
                    isClearable
                />
            </div>
            <Link
                to={artifact ? `/artifacts/${artifact.value}/artifactName` : '/artifacts'}
                className={classes.buttonLink}
            >
                <Button component="button" variant="contained" color="primary">Search</Button>
            </Link>
        </div>
    );
}

ArtifactsSelect.propTypes = {
    classes: PropTypes.object.isRequired,
    artifacts: PropTypes.array,
    fetchArtifacts: PropTypes.func.isRequired,
};

export default connect(
    state => ({ artifacts: state.artifacts.list.map(artifactName => ({
        value: artifactName,
        label: artifactName,
    })) }),
    { fetchArtifacts },
)(withStyles(styles, { withTheme: true })(ArtifactsSelect));
