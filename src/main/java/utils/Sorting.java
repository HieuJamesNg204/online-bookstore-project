package utils;

public class Sorting {
    private static <T extends Comparable<T>> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private static <T extends Comparable<T>> int medianOfTheThree(List<T> list, int left, int right) {
        int mid = (left + right) / 2;
        T a = list.get(left);
        T b = list.get(mid);
        T c = list.get(right);

        if ((b.compareTo(a) <= 0 && a.compareTo(c) <= 0) || (c.compareTo(a) <= 0 && a.compareTo(b) <= 0)) {
            return left;
        } else if ((a.compareTo(b) <= 0 && b.compareTo(c) <= 0) || (c.compareTo(b) <= 0 && b.compareTo(a) <= 0)) {
            return mid;
        } else {
            return right;
        }
    }

    public static <T extends Comparable<T>> void quickSort(List<T> list, int left, int right) {
        if (left < right) {
            int partitionPos = partition(list, left, right);
            quickSort(list, left, partitionPos - 1);
            quickSort(list, partitionPos + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(List<T> list, int left, int right) {
        int pivotIndex = medianOfTheThree(list, left, right);
        T pivot = list.get(pivotIndex);
        swap(list, pivotIndex, right);

        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (list.get(j).compareTo(pivot) < 0) {
                swap(list, ++i, j);
            }
        }

        swap(list, i + 1, right);
        return i + 1;
    }
}