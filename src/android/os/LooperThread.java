package android.os;

import java.util.concurrent.CountDownLatch;

public class LooperThread extends Thread {
    public Handler mHandler;
    private final CountDownLatch downLatch;

    public LooperThread(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        super.run();
        Looper.prepare();

        mHandler = new Handler();
        downLatch.countDown();

        Looper.loop();
    }

    public Handler getHandler() {
        return mHandler;
    }
}
