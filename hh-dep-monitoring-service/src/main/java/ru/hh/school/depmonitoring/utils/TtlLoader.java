package ru.hh.school.depmonitoring.utils;

import java.util.function.Supplier;

public class TtlLoader<T> {
    private Long lastUpdateTime;
    private Long ttl;
    private Supplier<T> supplier;
    private T value;

    public synchronized T get() {
        if (ttl == null || lastUpdateTime == null || lastUpdateTime < System.currentTimeMillis() - ttl) {
            value = supplier.get();
            lastUpdateTime = System.currentTimeMillis();
        }
        return value;
    }

    public TtlLoader(long ttl, Supplier<T> supplier) {
        this.ttl = ttl;
        this.supplier = supplier;
    }
}
