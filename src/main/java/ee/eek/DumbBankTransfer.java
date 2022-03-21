package ee.eek;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

import java.util.concurrent.locks.ReentrantLock;

@JCStressTest
@State
@Outcome(id = "0, 0", expect = Expect.ACCEPTABLE, desc = "invariant holds")
public class DumbBankTransfer {
    int[] accounts = new int[4];

    int dumbMoneyTransfer(
            int from, int to, int amount) {
        accounts[from] -= amount;
        accounts[to] += amount;
        return accounts[from] + accounts[to];
    }


    @Actor
    void transferOne(II_Result r) {
        r.r1 = dumbMoneyTransfer(1, 2, 1);
    }

    @Actor
    void transferTwo(II_Result r) {
        r.r2 = dumbMoneyTransfer(2, 1, 1);
    }

}
