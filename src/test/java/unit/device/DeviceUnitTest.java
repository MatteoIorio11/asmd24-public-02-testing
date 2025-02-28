package unit.device;

import devices.Device;
import devices.FailingPolicy;
import devices.RandomFailing;
import devices.StandardDevice;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DeviceUnitTest {
    @Mock FailingPolicy mockFailingPolicy;
    private Device device;
    @BeforeEach
    void init() {
        try {
            MockitoAnnotations.openMocks(this);
        } catch (Exception e) {}
    }

    @Description("Creating a Device with a Null policy should return an error")
    @Tag("unit")
    @Test
    public void testNullPolicy() {
        try {
            this.device = new StandardDevice(null);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Description("When a new device is initialized, It must be turned off")
    @Tag("unit")
    @Test
    public void testInitiallyOff() {
        this.device = new StandardDevice(this.mockFailingPolicy);
        assertFalse(this.device.isOn());
    }

    @Description("When a new device is initialized, It should be possible to turning it on")
    @Tag("unit")
    @Test
    public void testTurnOn() {
        this.device = new StandardDevice(this.mockFailingPolicy);
        when(this.mockFailingPolicy.attemptOn()).thenReturn(true);
        this.device.on();
        assertTrue(this.device.isOn());
    }

    @Description("It should be possible to turning off a device that is on")
    @Tag("unit")
    @Test
    public void testTurnOffAfterBeingOn() {
        this.device = new StandardDevice(this.mockFailingPolicy);
        when(this.mockFailingPolicy.attemptOn()).thenReturn(true);
        this.device.on();
        assertTrue(this.device.isOn());
        this.device.off();
        assertFalse(this.device.isOn());
    }

    @Description("It should be possible to print the entire name of a device")
    @Tag("unit")
    @Test
    public void testPrintDeviceName() {
        this.device = new StandardDevice(this.mockFailingPolicy);
        when(this.mockFailingPolicy.policyName()).thenReturn("mock");
        assertEquals("StandardDevice{policy=mock, on=false}", this.device.toString());
    }

    
}
