package data_structures;

import java.util.Arrays;

public class List<T> {
    private Object[] array;
    private int size;
    private static int capacity = 10;

    public List() {
        array = new Object[capacity];
        size = 0;
    }

    public void add(T value) {
        if (size >= capacity) {
            grow();
        }
        array[size++] = value;
    }

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
        grow();
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds for the size " + size + ".");
        }
        return (T) array[size];
    }

    public void set(int index, T value) {
        array[index] = value;
    }

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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        array = Arrays.copyOf(array, capacity * 2);
    }

    private void shrink() {
        array = Arrays.copyOf(array, capacity / 2);
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