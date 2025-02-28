package unit.policy;

import devices.FailingPolicy;
import devices.RandomFailing;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class RandomUnitTest {
    private FailingPolicy failingPolicy;

    @BeforeEach
    public void unit() {
        this.failingPolicy = new RandomFailing();
    }

    @Description("The policy name of a RandomFailing policy should be 'random'")
    @Tag("unit")
    @Test
    public void testPolicyName() {
        assert(this.failingPolicy.policyName().equals("random"));
    }
}
