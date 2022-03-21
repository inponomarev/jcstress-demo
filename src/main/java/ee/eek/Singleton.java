package ee.eek;

public class Singleton {
    Byte x;

    Singleton() {
        x = 42;
    }

    public Byte x() {
        return x;
    }

    public static int map(Singleton singleton) {
        if (singleton == null) {
            return 0;
        }
        if (singleton.x() == null) {
            return 1;
        }
        return singleton.x();
    }
}
