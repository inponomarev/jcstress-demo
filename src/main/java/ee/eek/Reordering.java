package ee.eek;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

@JCStressTest
@State
@Outcome(id = "0, 1", expect = Expect.ACCEPTABLE, desc = "actor1 incremented, then actor2.")
@Outcome(id = "1, 0", expect = Expect.ACCEPTABLE, desc = "actor2 incremented, then actor1.")
@Outcome(id = "1, 1", expect = Expect.ACCEPTABLE, desc = "both actors incremented")
@Outcome(id = "0, 0", expect = Expect.ACCEPTABLE_INTERESTING, desc = "reordering")
public class Reordering {
    int a = 0;
    int b = 0;

    @Actor
    public void actor1(II_Result r) {
        a = 1;
        r.r1 = b;
    }

    @Actor
    public void actor2(II_Result r) {
        b = 1;
        r.r2 = a;
    }
}
