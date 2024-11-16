package utils;

public interface QueueInterface<T> {
    void offer(T value);
    T poll();
    T peek();
    int size();
    boolean isEmpty();
}