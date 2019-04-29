import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';

import { addRelation, editRelation } from 'redux/models/Relation/relationsActions';
import Validator from 'Utils/Validator';
import { formButton } from 'Utils/commonStyles';
import SelectRepository from './SelectRepository';
import SelectPriority from './SelectPriority';
import DescriptionInput from './DescriptionInput';

const styles = () => ({
    formButton,
});

function FormContent({ classes, setOpen, repositoryId, repositoryName, addRelation, formResult,
    relation, editRelation }) {
    const isNewRelation = !relation;

    const values = {
        repositoryFrom: isNewRelation ? repositoryId : relation.repositoryFromId,
        repositoryTo: isNewRelation ? '' : relation.repositoryToId,
        priority: isNewRelation ? '' : relation.priority,
        description: isNewRelation ? '' : relation.description,
    };

    const labels = {
        repositoryFrom: isNewRelation ? repositoryName : relation.repositoryFromName,
        repositoryTo: isNewRelation ? '' : relation.repositoryToName,
    };

    const [repositoryFrom, setRepositoryFrom] = useState(Validator.generateAttribute(
        values.repositoryFrom, { label: labels.repositoryFrom },
    ));
    const [repositoryTo, setRepositoryTo] = useState(Validator.generateAttribute(
        values.repositoryTo, { label: labels.repositoryTo },
    ));
    const [priority, setPriority] = useState(Validator.generateAttribute(values.priority));
    const [description, setDescription] = useState(Validator.generateAttribute(values.description));

    const clearForm = () => {
        setRepositoryTo(Validator.generateAttribute('', { label: '' }));
        setPriority(Validator.generateAttribute(''));
        setDescription(Validator.generateAttribute(''));
    };

    const handleClose = () => {
        setOpen(false);
        clearForm();
    };

    useEffect(() => {
        formResult.success && handleClose();
    }, [formResult]);

    const validationRules = {
        repository: [
            { rule: 'required', message: 'Repository is required to be selected' },
        ],
        priority: [
            { rule: 'required', message: 'Priority is required to be selected' },
        ],
    };

    const handleSubmit = () => {
        const { valid, attributes } = Validator.validateForm({
            repository: repositoryTo, priority,
        }, validationRules);
        if (valid) {
            isNewRelation
                ? addRelation(repositoryId, repositoryTo.value, priority.value, description.value)
                : editRelation(relation.relationId, relation.repositoryFromId, repositoryTo.value,
                    priority.value, description.value);
        } else {
            setRepositoryTo({
                label: repositoryTo.label,
                ...attributes.repository,
            });
            setPriority(attributes.priority);
        }
    };

    return (
        <Fragment>
            <DialogContent>
                {isNewRelation && (
                    <DialogContentText paragraph>
                        To add new relation, please enter following fields.
                    </DialogContentText>
                )}
                <SelectRepository
                    repository={repositoryFrom}
                    setRepository={setRepositoryFrom}
                    repositoryFor={repositoryId}
                    direction="From"
                    disabled={!!relation}
                />
                <SelectRepository
                    repository={repositoryTo}
                    setRepository={setRepositoryTo}
                    repositoryFor={repositoryId}
                    direction="To"
                    disabled={!!relation}
                />
                <SelectPriority priority={priority} setPriority={setPriority} />
                <DescriptionInput
                    description={description}
                    setDescription={setDescription}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} className={classes.formButton}>
                    Cancel
                </Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">
                    Submit
                </Button>
            </DialogActions>
        </Fragment>
    );
}

FormContent.propTypes = {
    classes: PropTypes.object.isRequired,
    setOpen: PropTypes.func.isRequired,
    repositoryId: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number,
    ]).isRequired,
    repositoryName: PropTypes.string,
    addRelation: PropTypes.func.isRequired,
    formResult: PropTypes.object,
    relation: PropTypes.object,
    editRelation: PropTypes.func.isRequired,
};

FormContent.defaultProps = {
    repositoryName: '',
    formResult: {},
};

export default connect(
    state => ({ formResult: state.relations.formResult }),
    {
        addRelation,
        editRelation,
    },
)(withStyles(styles)(FormContent));
