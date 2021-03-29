package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController controller;
    TrainUser user;

    TrainSensor sensor;

    @Before
    public void init() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void	NewSpeedLimitSetToNegative() {
        sensor.overrideSpeedLimit(-1);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void	NewSpeedLimitSetToOver500() {
        sensor.overrideSpeedLimit(501);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void	NewSpeedLimitLowerThanRelativeMargin() {
        when(controller.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(50);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void NewSpeedLimitNoAlarm(){
        when(controller.getReferenceSpeed()).thenReturn(50);
        sensor.overrideSpeedLimit(30);
        verify(user, times(0)).setAlarmState(true);
    }
}
