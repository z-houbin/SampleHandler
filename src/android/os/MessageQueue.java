package android.os;

import java.util.concurrent.DelayQueue;

public class MessageQueue {
    private final DelayQueue<Message> deque = new DelayQueue<>();

    public Message next() {
        try {
            return deque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    boolean enqueueMessage(Message message, long when) {
        message.time = when;
        deque.add(message);
        return true;
    }

    void quit() {

    }
}
