package edu.iis.mto.multithread;

import java.util.concurrent.Executor;

public class BetterRadar implements Runnable {

    private PatriotBattery battery;
    private Executor executor;

    private final int antiMissileCount;

    public BetterRadar(PatriotBattery battery, Executor executor, int antiMissileCount) {
        this.battery = battery;
        this.executor = executor;
        this.antiMissileCount = antiMissileCount;
    }

    public void notice(Scud enemyMissle) {
        launchPatriot();
    }

    private void launchPatriot() {
        executor.execute(this);
    }

    @Override
    public void run() {
        fireAntiMissiles();
    }

    public void fireAntiMissiles(){
        for(int i = 0; i<antiMissileCount; i++)
            battery.launchPatriot();
    }


}
