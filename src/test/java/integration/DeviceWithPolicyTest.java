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

import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Description("When a new device is reset after being turned on, also the FailingPolicy should be reset")
    @Tag("integration")
    @Test
    public void testCollaborationDuringReset() {
        when(this.mockFailingPolicy.attemptOn()).thenReturn(true);
        this.device.on();
        this.device.reset();
        verify(this.mockFailingPolicy).reset();
        assertFalse(this.device.isOn());
    }

    @Description("When the dumping the Device as a String, the policy name should be included")
    @Tag("integration")
    @Test
    public void testDeviceToString() {
        when(this.mockFailingPolicy.policyName()).thenReturn("mock");
        assertTrue(this.device.toString().contains("mock"));
        verify(this.mockFailingPolicy).policyName();
    }
}
