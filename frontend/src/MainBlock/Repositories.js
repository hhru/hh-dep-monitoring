import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Divider from '@material-ui/core/Divider';

const styles = theme => ({
  root: {
    padding: theme.spacing.unit * 2,
    width: theme.spacing.unit * 50,
  },
});

const repositories = [
  {
    id: 1231,
    repoName: 'HH_HTML',
    repoLink: 'https://github.com/SuhoyVasya/HH_HTML',
    comitterName: 'SuhoyVasya',
    comitterLink: 'https://github.com/SuhoyVasya',
    date: '2019.01.31 12:15:17',
  },
  {
    id: 543634,
    repoName: 'money-transfer',
    repoLink: 'https://github.com/SuhoyVasya/money-transfer',
    comitterName: 'Afftobus',
    comitterLink: 'https://github.com/Afftobus',
    date: '2019.01.31 12:15:17',
  },
  {
    id: 54675,
    repoName: 'git-tasks',
    repoLink: 'https://github.com/K-ksenia/git-tasks',
    comitterName: 'SuhoyVasya',
    comitterLink: 'https://github.com/SuhoyVasya',
    date: '2019.01.31 12:15:17',
  },
  {
    id: 2345234,
    repoName: 'maven-practice',
    repoLink: 'https://github.com/anickishin/maven-practice',
    comitterName: 'SuhoyVasya',
    comitterLink: 'https://github.com/SuhoyVasya',
    date: '2019.01.31 12:15:17',
  },
  {
    id: 3456,
    repoName: 'JS_Task1',
    repoLink: 'https://github.com/SuhoyVasya/JS_Task1',
    comitterName: 'SuhoyVasya',
    comitterLink: 'https://github.com/SuhoyVasya',
    date: '2019.01.31 12:15:17',
  },
  {
    id: 23457,
    repoName: 'xml-xsl-xpath',
    repoLink: 'https://github.com/Afftobus/xml-xsl-xpath',
    comitterName: 'anickishin',
    comitterLink: 'https://github.com/anickishin',
    date: '2019.01.31 12:15:17',
  },
  {
    id: 2345,
    repoName: 'hibernate-hw',
    repoLink: 'https://github.com/Afftobus/hibernate-hw',
    comitterName: 'SuhoyVasya',
    comitterLink: 'https://github.com/SuhoyVasya',
    date: '2019.01.31 12:15:17',
  },
  {
    id: 2562,
    repoName: 'jersey-hometask',
    repoLink: 'https://github.com/SuhoyVasya/jersey-hometask',
    comitterName: 'anickishin',
    comitterLink: 'https://github.com/anickishin',
    date: '2019.01.31 12:15:17',
  },
  {
    id: 2624,
    repoName: 'JS_Task2',
    repoLink: 'https://github.com/SuhoyVasya/JS_Task2',
    comitterName: 'SuhoyVasya',
    comitterLink: 'https://github.com/SuhoyVasya',
    date: '2019.01.31 12:15:17',
  },
  {
    id: 23465,
    repoName: 'hh-school',
    repoLink: 'https://github.com/K-ksenia/hh-school',
    comitterName: 'anickishin',
    comitterLink: 'https://github.com/anickishin',
    date: '2019.01.31 12:15:17',
  },
];

function ListItemLink(props) {
  return <ListItem button component="a" {...props} />;
}

function Repositories(props) {
  const { classes } = props;
  return (
    <Paper classes={{ root: classes.root }}>
      <Typography variant="h4">
                    Repositories
      </Typography>
      <List>
        {repositories.map(repo => (
          <Fragment key={repo.id}>
            <ListItemLink href={repo.repoLink}>
              <ListItemText primary={`${repo.comitterName}/${repo.repoName}`} />
            </ListItemLink>
            <Divider />
          </Fragment>
        ))}
      </List>
    </Paper>
  );
}

Repositories.propTypes = {
  classes: PropTypes.string.isRequired,
};

export default withStyles(styles)(Repositories);
