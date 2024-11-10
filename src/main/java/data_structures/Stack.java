package data_structures;

public class Stack<T> {
    private static class Item<T> {
        private T value;
        private Item<T> next;

        public Item(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private int size;
    private Item<T> top;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public void push(T item) {
        Item<T> newItem = new Item<>(item);
        newItem.next = top;
        top = newItem;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack underflow. There's nothing to pop!");
        }

        T poppedItem = top.value;

        Item<T> temp = top.next;
        top.next = null;
        top = temp;
        size--;

        return poppedItem;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack underflow. There's nothing to peek!");
        }
        return top.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        Item<T> current = top;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }
}