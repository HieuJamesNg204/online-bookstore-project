package utils;

public class Searching {
    public static <T extends Comparable<T>> T search(List<T> list, T target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            T midVal = list.get(mid);

            int compare = midVal.compareTo(target);

            if (compare < 0) {
                left = mid + 1;
            } else if (compare > 0) {
                right = mid - 1;
            } else {
                return list.get(mid);
            }
        }

        return null;
    }

    public static <T extends Comparable<T>> int searchReturningIndex(List<T> list, T target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            T midVal = list.get(mid);

            int compare = midVal.compareTo(target);

            if (compare < 0) {
                left = mid + 1;
            } else if (compare > 0) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}