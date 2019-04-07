export const archivedRepositoryColor = '#6c6c6c';
const activeRepositoryColor = '#3f51b5';
export const secondaryItemColor = '#646464';

export const secondaryItem = {
    fontWeight: 300,
    color: secondaryItemColor,
};

export const dateListItem = {
    ...secondaryItem,
    fontSize: 12,
    margin: '3px 0',
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
    paddingBottom: '20px',
};

export const repositoryItemTitle = {
    fontSize: 18,
    fontWeight: 600,
    margin: '3px 0',
    color: activeRepositoryColor,
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

export const priorityMark = {
    marginRight: '10px',
    height: '22px',
    color: '#fff',
    fontSize: 11,
};

export const nestedBlock = {
    paddingLeft: 27,
    marginLeft: 27,
    borderLeft: '1px solid #D0D0D0',
};
