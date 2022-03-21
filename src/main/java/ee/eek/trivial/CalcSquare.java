package ee.eek.trivial;

public class CalcSquare extends Thread {
    final int argument;
    int result;

    CalcSquare(int argument) {
        this.argument = argument;
    }

    @Override
    public void run() {
        //«complex» calculations
        result = argument * argument;
    }

    public static void main(String[] args) throws InterruptedException {
        CalcSquare t1 = new CalcSquare(2);
        CalcSquare t2 = new CalcSquare(3);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.printf("%d, %d%n", t1.result, t2.result);
    }
}
