package org.zuel.mould.task;

public abstract class NcTaskExecutor implements Runnable {

    public abstract boolean isRunning();

    @Override
    public abstract void run();
}
