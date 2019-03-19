package kireev.ftshw.project.Courses;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public final class LooperThread extends Thread {
    private static Handler handler; // in Android Handler should be static or leaks might occur


    public LooperThread() {
    }

    @Override public void run() {
        // Note: Looper is usually already created in the Activity
        boolean looperIsNotPreparedInCurrentThread = Looper.myLooper() == null;

        if (looperIsNotPreparedInCurrentThread) {
            Looper.prepare();
        }

        handler = new Handler(new Handler.Callback() {
            @Override public boolean handleMessage(Message message) {
            return true;
            }
        });

        if (looperIsNotPreparedInCurrentThread) {
            Looper.loop();
        }
    }

    public static void post(Runnable runnable) {
        handler.post(runnable);
    }
}
