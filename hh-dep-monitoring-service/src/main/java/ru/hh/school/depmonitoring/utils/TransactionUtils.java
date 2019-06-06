package ru.hh.school.depmonitoring.utils;

import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

public class TransactionUtils {
    @Transactional
    public  <T> T doInTransaction(Supplier<T> supplier) {
        return supplier.get();
    }

    @Transactional
    public void doInTransaction(Runnable runnable) {
        runnable.run();
    }
}
