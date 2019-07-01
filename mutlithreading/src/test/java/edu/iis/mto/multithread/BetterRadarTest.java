package edu.iis.mto.multithread;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BetterRadarTest {

    PatriotBattery patriotBattery = mock(PatriotBattery.class);
    BetterRadar betterRadar;
    LaunchSAMs launchSAMs;

    @Test
    public void launchPatriotTenTimesFromThisThreadWhenScudMissileIsNoticed() {
        launchSAMs = new LaunchSAMs(ExecutionLocation.FROM_HERE);
        betterRadar = new BetterRadar(patriotBattery, launchSAMs, 10);
        betterRadar.notice(new Scud());

        verify(patriotBattery, times(10)).launchPatriot();
    }

    @Test
    public void launchPatriotTwiceFromAnotherThreadWhenScudMissileIsNoticed() {
        launchSAMs = new LaunchSAMs(ExecutionLocation.OVER_THERE);
        betterRadar = new BetterRadar(patriotBattery, launchSAMs, 2);
        betterRadar.notice(new Scud());

        verify(patriotBattery, times(2)).launchPatriot();
    }

    @Test
    @RepeatRule.Repeat(times = 5)
    public void launchPatriotOnceFromThisThreadWithFiveRepeats() {
        launchSAMs = new LaunchSAMs(ExecutionLocation.FROM_HERE);
        betterRadar = new BetterRadar(patriotBattery, launchSAMs, 1);
        betterRadar.notice(new Scud());

        verify(patriotBattery, times(1)).launchPatriot();
    }

    @Test
    @RepeatRule.Repeat(times = 10)
    public void launchPatriotOnceFromAnotherThreadWithTenRepeats() {
        launchSAMs = new LaunchSAMs(ExecutionLocation.FROM_HERE);
        betterRadar = new BetterRadar(patriotBattery, launchSAMs, 1);
        betterRadar.notice(new Scud());

        verify(patriotBattery, times(1)).launchPatriot();
    }

}
