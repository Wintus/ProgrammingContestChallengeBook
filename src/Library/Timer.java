package Library;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Performance measure.
 * Created by Yuya on 2015/07/26.
 */
public class Timer<T> {

    /**
     * measure elapsed time.
     *
     * @param t        value.
     * @param consumer function.
     * @return elapsed time in nano seconds.
     */
    public long run(T t, Consumer<T> consumer) {
        long start = System.nanoTime();
        consumer.accept(t);
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        Timer<Object> timer = new Timer<>();
        Function<Long, String> formatter = n ->
                NumberFormat.getNumberInstance(Locale.US).format(n);

        // measure in ƒÊs
        long time0 = timer.run(0, i -> System.out.println("Timer")) / 1000;
        System.out.println(time0 + "\u00B5s");
        System.out.println();

        long start = System.nanoTime();
        System.out.println("Timer");
        long end = System.nanoTime();
        long time1 = end - start;
        time1 /= 1000;
        System.out.println(time1 + "\u00B5s");
        System.out.println();

        long d = Math.abs(time0 - time1);
        System.out.println("Diff: " + d + "\u00B5s");
        System.out.println();

        // do nothing test
        System.out.println("Measure time (ns) = count");
        SortedMap<Long, Integer> times = new TreeMap<>();
        SortedMap<Long, Integer> runs = new TreeMap<>();
        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            end = System.nanoTime();
            d = end - start;
            times.put(d, times.getOrDefault(d, 0) + 1);
            // @formatter:off
            d = timer.run(0, o -> {}); // minimal consumer of no-op
            runs.put(d, times.getOrDefault(d, 0) + 1);
        }
        StringJoiner t = new StringJoiner("; ", "[", "]");
        StringJoiner r = new StringJoiner("; ", "[", "]");
        times.forEach((time, count) ->
                t.add(formatter.apply(time) + "=" + count));
        runs.forEach((time, count) ->
                r.add(formatter.apply(time) + "=" + count));
        System.out.println("times:\t" + t); // < .5-5ms
        System.out.println("runs:\t" + r); // < 5-30ms
    }
}
