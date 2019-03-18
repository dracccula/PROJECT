package kireev.ftshw.project.Courses;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread extends Thread {
    private static Handler handler;

    public LooperThread(CoursesFragment coursesFragment) {
    }

    public void run() {
        Log.d("LooperThread", "Started...");
        Looper.prepare();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                Log.d(getClass().getSimpleName(), message.getData().toString());
                return true;
            }
        });
    }

    public static void post(Runnable runnable) {
        handler.post(runnable);
    }

    public static void send(Message message) {
        handler.sendMessage(message);
    }

}
