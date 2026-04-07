package com.solvd.smarthome.util;

import java.util.List;
import java.util.Optional;

public class Repository<T> {

    private final List<T> items;

    public Repository(List<T> items) {
        this.items = items;
    }

    public void add(T item) {
        items.add(item);
    }

    public T get(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public Optional<T> getFirst() {
        return items.isEmpty() ? Optional.empty() : Optional.of(items.get(0));
    }
}
