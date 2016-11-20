package labs.mobile.victor.lab_1.Services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import labs.mobile.victor.lab_1.gui.Lab_3_activity;
import labs.mobile.victor.lab_1.Utils.Helper;

public class ServiceV1 extends Service {

    private int limit;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate()
    {
        Log.i("Lab", "Служба создана");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PendingIntent pi = intent.getParcelableExtra(Lab_3_activity.PARAM_PINTENT);
        limit = intent.getIntExtra(Lab_3_activity.LIMIT, 0);
        try {
            pi.send(Lab_3_activity.SERVICE_START);
            String result = Helper.listToString(Helper.getPrimeNumbers(limit));
            pi.send(ServiceV1.this, Lab_3_activity.SERVICE_FINISH, new Intent().putExtra(Lab_3_activity.LIMIT, result));
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
//        new Thread(new Calculate(limit, pi)).run();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        Log.i("Lab", "onDestroy");
    }

    private class Calculate implements Runnable {

        int limit;
        PendingIntent pi;

        public Calculate(int limit, PendingIntent pi) {
            this.limit = limit;
            this.pi = pi;
        }

        public void run() {
            try {
                String result = Helper.listToString(Helper.getPrimeNumbers(limit));
                pi.send(ServiceV1.this, Lab_3_activity.SERVICE_FINISH, new Intent().putExtra(Lab_3_activity.LIMIT, result));

            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
            stop();
        }

        void stop() {

        }
    }
}
