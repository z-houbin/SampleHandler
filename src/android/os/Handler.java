package android.os;

public class Handler {
    private Looper mLooper;
    private MessageQueue messageQueue;
    private Callback callback;

    public Handler() {
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException(
                    "Can't create handler inside thread " + Thread.currentThread()
                            + " that has not called Looper.prepare()");
        }
        messageQueue = mLooper.getQueue();
    }

    public Handler(Looper looper, Callback callback) {
        this.mLooper = looper;
        this.callback = callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public boolean sendMessage(Message message) {
        return this.sendMessageDelayed(message, 0);
    }

    public boolean sendEmptyMessage(int what) {
        return this.sendEmptyMessageDelayed(what, 0);
    }

    public boolean sendEmptyMessageDelayed(int what, long delayMillis) {
        Message msg = Message.obtain();
        msg.what = what;
        return sendMessageDelayed(msg, delayMillis);
    }

    public boolean sendMessageDelayed(Message message, long delayMillis) {
        return this.sendMessageAtTime(message, System.currentTimeMillis() + delayMillis);
    }

    public boolean sendMessageAtTime(Message message, long uptimeMillis) {
        return this.enqueueMessage(messageQueue, message, uptimeMillis);
    }

    private boolean enqueueMessage(MessageQueue queue, Message msg, long uptimeMillis) {
        msg.target = this;
        return queue.enqueueMessage(msg, uptimeMillis);
    }

    private static Message getPostMessage(Runnable r) {
        Message m = Message.obtain();
        m.callback = r;
        return m;
    }

    public boolean post(Runnable runnable) {
        return this.sendMessageDelayed(getPostMessage(runnable), 0);
    }

    public final boolean postDelayed(Runnable r, long delayMillis) {
        return this.sendMessageDelayed(getPostMessage(r), delayMillis);
    }

    public void dispatchMessage(Message message) {
        if (message.callback != null) {
            message.callback.run();
        } else if (callback != null) {
            callback.handleMessage(message);
        } else {
            this.handleMessage(message);
        }
    }

    public void handleMessage(Message msg) {

    }

    public interface Callback {
        boolean handleMessage(Message msg);
    }
}
