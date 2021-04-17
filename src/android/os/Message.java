package android.os;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed {
    public int what;
    public int arg1, arg2;
    public Object obj;
    Handler target;
    Message next;
    Runnable callback;
    public long time;

    public Message() {
        this.time = System.currentTimeMillis();
    }

    public static Message obtain() {
        return new Message();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        long t1 = this.getDelay(TimeUnit.MILLISECONDS);
        long t2 = o.getDelay(TimeUnit.MILLISECONDS);
        return Long.compare(t1, t2);
    }

    @Override
    public String toString() {
        return "Message{" +
                "what=" + what +
                ", arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", obj=" + obj +
                ", target=" + target +
                ", next=" + next +
                ", callback=" + callback +
                ", time=" + time +
                '}';
    }
}
