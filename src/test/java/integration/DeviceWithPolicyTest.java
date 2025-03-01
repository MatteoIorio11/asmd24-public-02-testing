package integration;

import devices.Device;
import devices.FailingPolicy;
import devices.StandardDevice;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DeviceWithPolicyTest {
    private Device device;
    @Mock
    FailingPolicy mockFailingPolicy;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.device = new StandardDevice(this.mockFailingPolicy);
    }
}
