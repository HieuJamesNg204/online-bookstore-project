package utils;

public interface ListInterface<T> {
    void add(T value);
    void insert(int index, T value);
    T get(int index);
    void set(int index, T value);
    void remove(int index);
    int size();
    boolean isEmpty();
}