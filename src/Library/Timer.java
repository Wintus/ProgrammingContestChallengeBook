package Library;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    /**
     * minimal no-op consumer
     */
    // @formatter:off
    public static final Consumer<Object> NOP = whatever -> {};
    // @formatter:on

    public static void main(String[] args) {
        Timer<Object> timer = new Timer<>();
        Function<Long, String> formatter =
                NumberFormat.getNumberInstance(Locale.US)::format;

        // measure in ƒÊs
        long time0 = timer.run(null, x -> System.out.println("Timer")) / 1000;
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
        Counter<Long> counter = new Counter<>();
        for (int i = 0; i < 1000; i++) {
            start = System.nanoTime();
            end = System.nanoTime();
            d = end - start;
            counter.count(times, d);
            d = timer.run(null, NOP);
            counter.count(runs, d);
        }
        String t = times.entrySet().stream()
                        .map(entry -> formatter.apply(entry.getKey()) +
                                "=" + entry.getValue())
                        .collect(Collectors.joining("; ", "[", "]"));
        String r = runs.entrySet().stream()
                       .map(entry -> formatter.apply(entry.getKey()) +
                               "=" + entry.getValue())
                       .collect(Collectors.joining("; ", "[", "]"));
        System.out.println("times:\t" + t); // < .5-5ms
        System.out.println("runs:\t" + r); // < 5-30ms
    }
}
