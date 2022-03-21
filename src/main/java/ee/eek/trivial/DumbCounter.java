package ee.eek.trivial;

import java.util.stream.IntStream;

public class DumbCounter {
    int count;

    void increment() {
        count++;
    }

    public static void main(String[] args) {
        DumbCounter c1 = new DumbCounter();
        IntStream.range(0, 1000000).forEach(i -> c1.increment());

        DumbCounter c2 = new DumbCounter();
        IntStream.range(0, 1000000).parallel().forEach(i -> c2.increment());

        System.out.printf("%d, %d%n", c1.count, c2.count);
    }
}
