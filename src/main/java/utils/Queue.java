package utils;

public class Queue<T> implements QueueInterface<T> {
    private static class Item<T> {
        private T value;
        private Item<T> next;

        public Item(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Item<T> head;
    private int size;

    public Queue() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void offer(T value) {
        Item<T> newItem = new Item<>(value);

        if (this.isEmpty()) {
            this.head = newItem;
        } else {
            Item<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newItem;
        }

        size++;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }

        T polledItem = head.value;

        Item<T> temp = head;
        head = head.next;
        temp.next = null;
        size--;

        return polledItem;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }

        return head.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        Item<T> temp = head;

        while (temp != null) {
            builder.append(temp.value);

            if (temp.next != null) {
                builder.append(", ");
            }

            temp = temp.next;
        }

        builder.append("]");
        return builder.toString();
    }
}