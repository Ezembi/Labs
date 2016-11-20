package labs.mobile.victor.lab_1.Services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import labs.mobile.victor.lab_1.Utils.Helper;
import labs.mobile.victor.lab_1.gui.Lab_3_activity;

public class ServiceV3 extends IntentService {

    public static final String ACTION_RESULT = "labs.mobile.victor.lab_1.Services.action.RESULT";

    public static final String RESULT = "RESULT";

    public ServiceV3() {
        super("ServiceV3");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Intent responseIntent = new Intent();
        responseIntent.setAction(ACTION_RESULT);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        responseIntent.putExtra(
                RESULT, Helper.listToString(
                        Helper.getPrimeNumbers(
                                intent.getIntExtra(Lab_3_activity.LIMIT, 0)))
        );

        sendBroadcast(responseIntent);
    }
}
