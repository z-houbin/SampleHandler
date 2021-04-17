package z.houbin.handler;

import android.os.Handler;
import android.os.LooperThread;
import android.os.Message;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        LooperThread looperThread = new LooperThread(countDownLatch);
        looperThread.start();

        countDownLatch.await();

        Handler handler = looperThread.getHandler();
        handler.setCallback(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                System.out.println("--------------");
                System.out.println("handleMessage: " + msg.toString());
                return false;
            }
        });

        countDownLatch.await();

        handler.sendMessageDelayed(getMessage(1, "1"), 4000);
        handler.sendMessageDelayed(getMessage(2, "2"), 6000);
        handler.sendMessageDelayed(getMessage(3, "3 - " + System.currentTimeMillis()), 10000);
        handler.sendEmptyMessage(4);
        handler.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("post run...");
            }
        });
    }

    private static Message getMessage(int what, Object obj) {
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        return message;
    }
}
