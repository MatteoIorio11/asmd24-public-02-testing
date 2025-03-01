package integration;

import devices.Device;
import devices.FailingPolicy;
import devices.StandardDevice;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeviceWithPolicyTest {
    @Spy
    Device device;
    @Mock
    FailingPolicy mockFailingPolicy;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.device = new StandardDevice(this.mockFailingPolicy);
    }

    @Description("When a new device is turned on, the FailingPolicy should be called")
    @Tag("integration")
    @Test
    public void testCollaborationDuringTurnOn() {
        when(this.mockFailingPolicy.attemptOn()).thenReturn(true);
        this.device.on();
        assertTrue(this.device.isOn());
        verify(this.mockFailingPolicy).attemptOn();
    }
}
