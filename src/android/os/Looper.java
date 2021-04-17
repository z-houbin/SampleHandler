package android.os;

public class Looper {
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
    public MessageQueue queue = new MessageQueue();

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    public static void loop() {
        final Looper me = myLooper();
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        final MessageQueue queue = me.getQueue();
        for (; ; ) {
            Message msg = queue.next(); // might block
            if (msg == null) {
                return;
            }
            msg.target.dispatchMessage(msg);
        }
    }

    public MessageQueue getQueue() {
        return queue;
    }


    public static Looper myLooper() {
        return sThreadLocal.get();
    }
}
