package utils;

import java.util.Arrays;

public class List<T> implements ListInterface<T> {
    private Object[] array;
    private int size;
    private static int capacity = 10;

    public List() {
        array = new Object[capacity];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size >= capacity) {
            grow();
        }
        array[size++] = value;
    }

    @Override
    public void insert(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds for the size " + size + ".");
        }

        if (size >= capacity) {
            grow();
        }

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds for the size " + size + ".");
        }
        return (T) array[index];
    }

    @Override
    public void set(int index, T value) {
        array[index] = value;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds for the size " + size + ".");
        }

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;

        if (size <= (int) (capacity / 2)) {
            shrink();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        capacity *= 2;
        array = Arrays.copyOf(array, capacity);
    }

    private void shrink() {
        capacity /= 2;
        array = Arrays.copyOf(array, capacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}