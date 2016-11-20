package labs.mobile.victor.lab_1.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import labs.mobile.victor.lab_1.Utils.Helper;

public class ServiceV2 extends Service {

    private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public ServiceV2 getService() {
            return ServiceV2.this;
        }
    }

    public String getPrimeNumbers(int limit) {
        return Helper.listToString(Helper.getPrimeNumbers(limit));
    }
}
