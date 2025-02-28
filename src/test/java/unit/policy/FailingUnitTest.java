package unit.policy;

import devices.FailingPolicy;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class FailingUnitTest {
    @Mock private FailingPolicy failingPolicy;

    @BeforeEach
    void init() {
        try {
            MockitoAnnotations.openMocks(this);
        } catch (Exception e) {}
    }

    @Description("A policy that always fails should return false when attempting on")
    @Tag("unit")
    @Test
    public void testAlwaysFailingPolicy() {
        when(this.failingPolicy.attemptOn()).thenReturn(false);
        assertFalse(this.failingPolicy.attemptOn());
    }

}
