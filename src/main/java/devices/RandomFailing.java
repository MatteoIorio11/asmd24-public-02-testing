package devices;

import java.util.Random;
import java.util.random.RandomGenerator;

public class RandomFailing implements FailingPolicy {
    private final RandomGenerator random = new Random();
    private boolean failed = false;
    @Override
    public boolean attemptOn() {
        this.failed = this.failed || random.nextBoolean();
        return !this.failed;
    }

    @Override
    public void reset() {
        this.failed = false;
    }

    @Override
    public String policyName() {
        return "random";
    }
}
