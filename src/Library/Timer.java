package Library;

import java.text.NumberFormat;
import java.util.Locale;
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
        Timer<Integer> timer = new Timer<>();
        long time = timer.run(0, i -> System.out.println("Timer"));
        String format = NumberFormat.getNumberInstance(Locale.US).format(time);
        System.out.println(format + " ns");
        System.out.println();

        long start = System.nanoTime();
        System.out.println("Timer");
        long end = System.nanoTime();
        time = end - start;
        format = NumberFormat.getNumberInstance(Locale.US).format(time);
        System.out.println(format + " ns");
    }
}
