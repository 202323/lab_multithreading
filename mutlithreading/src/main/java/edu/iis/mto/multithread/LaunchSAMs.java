package edu.iis.mto.multithread;

import java.util.concurrent.Executor;

public class LaunchSAMs implements Executor {

    private ExecutionLocation location;

    public LaunchSAMs(ExecutionLocation location) {
        this.location = location;
    }

    @Override
    public void execute(Runnable target) {
        switch(location){
            case FROM_HERE:
                target.run();
                break;
            case OVER_THERE:
                new Thread(target).run();
                break;
            default:
                System.out.println("Target not detected");
        }
    }
}
