package algo;

import data_structures.List;

public class Searching {
    public static <T extends Comparable<T>> T search(List<T> list, T target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid).compareTo(target) < 0) {
                left = mid + 1;
            } else if (list.get(mid).compareTo(target) > 0) {
                right = mid - 1;
            } else {
                return list.get(mid);
            }
        }

        return null;
    }
}