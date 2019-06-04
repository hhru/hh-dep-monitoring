export const archivedRepositoryColor = '#6c6c6c';
const mainThemeColor = '#c30019';
export const secondaryItemColor = '#646464';
export const secondaryItemColorLighter = '#7d7d7d';

export const secondaryItem = {
    fontWeight: 300,
    color: secondaryItemColor,
};

export const dateListItem = {
    ...secondaryItem,
    fontSize: 12,
    marginLeft: '5px',
    marginRight: '10px',
};

export const dateFullItem = {
    ...secondaryItem,
    fontSize: 12,
    margin: '5px 0',
    textAlign: 'right',
};

export const descriptionListItem = {
    ...secondaryItem,
    fontSize: 18,
    margin: '5px 0',
};

export const repositoryItemTitle = {
    fontSize: 18,
    fontWeight: 600,
    margin: '3px 0',
    color: mainThemeColor,
    '&:hover': {
        textDecoration: 'underline',
    },
};

export const archiveColored = {
    color: archivedRepositoryColor,
};

export const archiveIcon = {
    fill: archivedRepositoryColor,
    width: '19px',
    height: '19px',
    margin: '-1px 3px',
};

export const archiveIconBig = {
    width: '30px',
    height: '30px',
    margin: '-2px 7px',
};

export const relationsHeader = {
    display: 'flex',
    height: '36px',
    paddingTop: '20px',
};

export const relationsTitle = {
    flexGrow: 1,
    lineHeight: '1.6',
};

export const buttonLink = {
    textDecoration: 'none',
    color: 'inherit',
};

export const genericLink = {
    ...buttonLink,
    '&:hover': {
        textDecoration: 'underline',
    },
};

export const chip = {
    height: '22px',
    fontSize: 11,
    marginRight: '10px',
};

export const priorityMark = {
    ...chip,
    color: '#fff',
};

export const nestedBlock = {
    paddingLeft: 27,
    marginLeft: 27,
    borderLeft: '1px solid #D0D0D0',
};

export const errorText = {
    color: '#ff0000',
};

export const selectComponentsStyles = theme => ({
    input: {
        display: 'flex',
        padding: 0,
    },
    valueContainer: {
        display: 'flex',
        flexWrap: 'wrap',
        flex: 1,
        alignItems: 'center',
        overflow: 'hidden',
    },
    noOptionsMessage: {
        padding: `${theme.spacing.unit}px ${theme.spacing.unit * 2}px`,
    },
    placeholder: {
        position: 'absolute',
        left: 2,
        fontSize: 16,
    },
    divider: {
        height: theme.spacing.unit * 2,
    },
    errorText,
});

export const formButton = {
    color: mainThemeColor,
};

export const adminBadge = {
    padding: '0px',
    backgroundColor: secondaryItemColor,
    '&:hover': {
        backgroundColor: mainThemeColor,
    },
};

export const addIcon = {
    ...adminBadge,
    color: '#fff',
    width: '30px',
    minHeight: '30px',
    maxHeight: '30px',
};

export const addRelationIcon = {
    ...addIcon,
    margin: '0px 16px',
};

export const addLinkIcon = {
    ...addIcon,
    margin: 'auto 6px',
};

export const controlIcon = {
    margin: '0px 3px',
    color: secondaryItemColor,
    '&:hover': {
        color: mainThemeColor,
    },
};

export const relationFormPaper = {
    maxWidth: '450px',
};

export const linkFormPaper = {
    maxWidth: '600px',
};

export const repoLinkIconWrapper = {
    padding: '4px',
    fontSize: 'unset',
};

export const repoLinkIcon = size => ({
    width: `${0.8 * size}px`,
    height: `${0.8 * size}px`,
    verticalAlign: 'middle',
});

export const relatedRepositoryIcon = {
    fill: secondaryItemColor,
    marginTop: '7px',
    marginRight: '3px',
    marginLeft: '5px',
};

export const flexVerticalContainer = {
    display: 'flex',
    flexDirection: 'column',
};

export const repoIconsContainer = {
    ...flexVerticalContainer,
    alignItems: 'flex-end',
};

export const flexInlineContainer = {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
};

export const capitalize = {
    display: 'block',
    textTransform: 'lowercase',
    '&:first-letter': {
        textTransform: 'uppercase',
    },
};

export const adminBadgeIcon = {
    width: '13px',
};

export const linkFormHeader = {
    ...flexInlineContainer,
    paddingRight: '20px',
};

export const dialogContent = {
    '&:first-child': {
        paddingTop: '0px',
    },
};

export const pageButton = {
    width: '40px',
    height: '40px',
    padding: '0px',
    fontSize: '1.1rem',
};

export const listItemWithoutIcon = {
    marginLeft: '60px',
};

export const genericPaper = {
    padding: '16px',
    width: '800px',
};
