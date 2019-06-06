import React from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';

import Paper from '@material-ui/core/es/Paper/Paper';

const styles = () => ({
    paper: {
        padding: '30px',
    },
    title: {
        paddingBottom: '20px',
    },
    group: {
        display: 'flex',
        justifyContent: 'space-between',
        width: '300px',
        margin: '10px',
    },
    developersGroup: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'flex-end',
    },
});

const developers = {
    backend: ['Filippov Artem', 'Nikishin Andrey', 'Sulim Aleksey'],
    frontend: ['Kolesnikova Ksenia'],
    curators: ['Linkov Alexander', 'Basharov Nikita', 'Bezdolniy Nikita'],
};

function About({ classes }) {
    return (
        <Paper className={classes.paper}>
            <Typography variant="h4" className={classes.title}>
                About
            </Typography>
            {Object.keys(developers).map(group => (
                <div className={classes.group} key={group}>
                    <Typography variant="h5">{group}</Typography>
                    <div className={classes.developersGroup}>
                        {developers[group].map(developer => (
                            <Typography variant="subtitle1" key={developer}>{developer}</Typography>))}
                    </div>
                </div>
            ))}
        </Paper>
    );
}

About.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(About);
