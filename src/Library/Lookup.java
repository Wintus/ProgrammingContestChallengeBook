package Library;

import java.util.List;
import java.util.function.UnaryOperator;

/**
 * helper library.
 * Reference:
 * <a href="http://thushw.blogspot.com/2010/07/why-am-i-writing-lowerbound-in-java.html">
 * Why am I writing lower_bound() in Java again?</a>
 * Created by Yuya on 2015/07/14.
 */
public class Lookup {
    /**
     * Gets the smallest index of key &lt;= arr[i] by using binary search.
     *
     * @param arr array to be looked up.
     * @param key lookup key.
     * @param <T> type parameter.
     * @return the smallest index.
     */
    public static <T extends Comparable<? super T>> int
    lower_bound(T[] arr, T key) {
        int len = arr.length;
        int lo = 0;
        int hi = len - 1;
        int mid = (lo + hi) / 2;
        while (true) {
            int cmp = arr[mid].compareTo(key);
            if (cmp == 0 || cmp > 0) {
                hi = mid - 1;
                if (hi < lo)
                    return mid;
            } else {
                lo = mid + 1;
                if (hi < lo)
                    return mid < len - 1 ? mid + 1 : -1;
            }
            mid = (lo + hi) / 2;
        }
    }

    /**
     * Gets the smallest index of key &lt; arr[i] by using binary search.
     *
     * @param arr array to be looked up.
     * @param key lookup key.
     * @param <T> type parameter.
     * @return the smallest index.
     */
    public static <T extends Comparable<? super T>> int
    upper_bound(T[] arr, T key) {
        int len = arr.length;
        int lo = 0;
        int hi = len - 1;
        int mid = (lo + hi) / 2;
        while (true) {
            int cmp = arr[mid].compareTo(key);
            if (cmp == 0 || cmp < 0) {
                lo = mid + 1;
                if (hi < lo)
                    return mid < len - 1 ? mid + 1 : -1;
            } else {
                hi = mid - 1;
                if (hi < lo)
                    return mid;
            }
            mid = (lo + hi) / 2;
        }
    }

    /**
     * Converts the element at index given by {@code lower_bound}.
     *
     * @param array    array to be looked up.
     * @param key      the key.
     * @param operator operator convert the found element.
     * @param <T>      type parameter.
     */
    public static <T extends Comparable<? super T>> void
    lower_bound(T[] array, T key, UnaryOperator<T> operator) {
        int i = lower_bound(array, key);
        array[i] = operator.apply(array[i]);
    }

    /**
     * Converts the element at index given by {@code upper_bound}.
     *
     * @param array    array to be looked up.
     * @param key      the key.
     * @param operator operator convert the found element.
     * @param <T>      type parameter.
     */
    public static <T extends Comparable<? super T>> void
    upper_bound(T[] array, T key, UnaryOperator<T> operator) {
        int i = upper_bound(array, key);
        array[i] = operator.apply(array[i]);
    }

    public static int lowerBound(int[] array, int key) {
        int lb = -1, ub = array.length;
        while (ub - lb > 1) {
            int mid = (lb + ub) / 2;
            if (array[mid] >= key) ub = mid;
            else lb = mid;
        }
        return ub;
    }

    public static int upperBound(int[] array, int key) {
        int lb = -1, ub = array.length;
        while (ub - lb > 1) {
            int mid = (lb + ub) / 2;
            if (array[mid] > key) ub = mid;
            else lb = mid;
        }
        return ub;
    }

    public static <T extends Comparable<? super T>>
    int lowerBound(List<T> list, T key) {
        int lb = -1, ub = list.size();
        while (ub - lb > 1) {
            int mid = (lb + ub) / 2;
            if (list.get(mid).compareTo(key) >= 0)
                ub = mid;
            else
                lb = mid;
        }
        return ub;
    }

    public static <T extends Comparable<? super T>>
    int upperBound(List<T> list, T key) {
        int lb = -1, ub = list.size();
        while (ub - lb > 1) {
            int mid = (lb + ub) / 2;
            if (list.get(mid).compareTo(key) > 0)
                ub = mid;
            else
                lb = mid;
        }
        return ub;
    }
}
