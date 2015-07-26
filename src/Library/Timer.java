package Library;

import java.text.NumberFormat;
import java.util.*;
import java.util.function.Consumer;

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
        String format;

        // measure in ƒÊs
        long time0 = timer.run(0, i -> System.out.println("Timer")) / 1000;
        format = NumberFormat.getNumberInstance(Locale.US).format(time0);
        System.out.println(format + "\u00B5s");
        System.out.println();

        long start = System.nanoTime();
        System.out.println("Timer");
        long end = System.nanoTime();
        long time1 = end - start;
        time1 /= 1000;
        format = NumberFormat.getNumberInstance(Locale.US).format(time1);
        System.out.println(format + "\u00B5s");
        System.out.println();

        long d = Math.abs(time0 - time1);
        System.out.println("Diff: " + d + "\u00B5s");
        System.out.println();
    }
}
