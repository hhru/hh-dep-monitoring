package ru.hh.school.depmonitoring.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.type.EnumType;

public class PostgreSQLEnumType extends EnumType {

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement,
            Object value,
            int index,
            SharedSessionContractImplementor session)
            throws SQLException {

        if (value == null) {
            preparedStatement.setNull(index, Types.OTHER);
            return;
        }

        preparedStatement.setObject(index, value.toString(), Types.OTHER);
    }
}

