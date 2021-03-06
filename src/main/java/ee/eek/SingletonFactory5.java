package ee.eek;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Arbiter;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.IIZ_Result;
import org.openjdk.jcstress.infra.results.ZZZ_Result;
import org.openjdk.jcstress.infra.results.Z_Result;

@JCStressTest
@State
@Outcome(id = "42, 42, true", expect = Expect.ACCEPTABLE, desc = "Singleton created only once")
public class SingletonFactory5 {
    //NON-VOLATILE!
    private Singleton instance;
    private final Object lock = new Object();

    private Singleton s1;
    private Singleton s2;

    public Singleton getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }

    @Actor
    public void actor1(IIZ_Result r) {
        s1 = getInstance();
        r.r1 = Singleton.map(s1);
    }

    @Actor
    public void actor2(IIZ_Result r) {
        s2 = getInstance();
        r.r2 = Singleton.map(s2);
    }

    @Arbiter
    public void check(IIZ_Result r) {
        r.r3 = (s1 == s2);
    }
}
