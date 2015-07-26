package Library;

import java.util.Map;

/**
 * Counter for Collection.
 * Created by Yuya on 2015/07/26.
 */
public class Counter<T> {
    void count(Map<T, Integer> map, T t) {
        map.put(t, map.getOrDefault(t, 0) + 1);
    }
}
