package ee.eek;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Mode;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.Signal;
import org.openjdk.jcstress.annotations.State;

import java.util.stream.IntStream;

@JCStressTest(Mode.Termination)
@Outcome(id = "TERMINATED", expect = Expect.ACCEPTABLE, desc = "Gracefully finished.")
@Outcome(id = "STALE", expect = Expect.FORBIDDEN, desc = "Test hung up.")
@State
public class DumbWayToFallAsleep {
    boolean asleep;
    @Actor
    public void actor1() {
        while (!asleep) {
            // count some sheep
        }
    }
    @Signal
    synchronized public void signal() {
        this.asleep = true;
    }
}
