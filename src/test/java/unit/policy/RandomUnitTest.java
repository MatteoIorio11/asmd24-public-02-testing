package unit.policy;

import devices.FailingPolicy;
import devices.RandomFailing;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.random.RandomGenerator;

import static org.mockito.Mockito.when;

public class RandomUnitTest {
    FailingPolicy failingPolicy;
    @Mock RandomGenerator fixedRandom;
    private static final String RANDOM_ATTRIBUTE_NAME = "random";

    @BeforeEach
    public void unit() {
        try {
            MockitoAnnotations.openMocks(this);
            this.failingPolicy = new RandomFailing();
            final Field field = this.failingPolicy.getClass().getDeclaredField(RANDOM_ATTRIBUTE_NAME);
            field.setAccessible(true);
            field.set(this.failingPolicy, this.fixedRandom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Description("The policy name of a RandomFailing policy should be 'random'")
    @Tag("unit")
    @Test
    public void testPolicyName() {
        assert(this.failingPolicy.policyName().equals("random"));
    }

    @Description("The RandomFailing policy should be able to be turned on")
    @Tag("unit")
    @Test
    public void testAttemptOn() {
        when(this.fixedRandom.nextBoolean()).thenReturn(false);
        assert(this.failingPolicy.attemptOn());
    }
}
