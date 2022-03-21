package ee.eek;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Arbiter;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.Z_Result;

@JCStressTest
@State
@Outcome(id = "true", expect = Expect.ACCEPTABLE, desc = "Singleton created only once")
@Outcome(id = "false", expect = Expect.FORBIDDEN, desc = "Singleton created twice")
public class SingletonFactory3 {

    private static volatile Singleton instance;

    private Singleton s1;
    private Singleton s2;

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    @Actor
    public void actor1() {
        s1 = getInstance();
    }

    @Actor
    public void actor2() {
        s2 = getInstance();
    }

    @Arbiter
    public void check(Z_Result r) {
        r.r1 = (s1 == s2);
    }
}
