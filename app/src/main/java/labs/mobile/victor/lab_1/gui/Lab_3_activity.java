package labs.mobile.victor.lab_1.gui;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import labs.mobile.victor.lab_1.R;
import labs.mobile.victor.lab_1.Services.ServiceV1;
import labs.mobile.victor.lab_1.Services.ServiceV2;
import labs.mobile.victor.lab_1.Services.ServiceV3;

public class Lab_3_activity extends AppCompatActivity {

    private EditText primeNumber;

    private Button goButtonV1;
    private ProgressDialog pd;

    private Button goButtonV2;
    private ServiceV2 serviceV2;
    private boolean mBound = false;

    private Button goButtonV3;
    private BroadcastReceiverV3 receiverV3;

    public final static int SERVICE_START = 1;
    public final static int SERVICE_FINISH = 0;
    public final static String LIMIT = "limit";
    public final static String PARAM_PINTENT = "pendingIntent";
    public final static int PENDING_CODE = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_3);
        setTitle(getString(R.string.go_lab_3));

        primeNumber = (EditText) findViewById(R.id.primeNumber);

        goButtonV1 = (Button) findViewById(R.id.goV1);
        goButtonV1.setOnClickListener(new LocalV1ButtonGoClick());

        goButtonV2 = (Button) findViewById(R.id.goV2);
        goButtonV2.setOnClickListener(new LocalV2ButtonGoClick());

        goButtonV3 = (Button) findViewById(R.id.goV3);
        goButtonV3.setOnClickListener(new LocalV3ButtonGoClick());

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ServiceV2.class);
        bindService(intent, connectionV2, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(connectionV2);
            mBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverV3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Ловим сообщения о старте задач
        if (resultCode == SERVICE_START) {
            switch (requestCode) {
                case PENDING_CODE:
                    Log.i(MainActivity.LOG_TAG, "SERVICE_START");
                    showDialog(true);
                    break;
            }
        }

        // Ловим сообщения об окончании задач
        if (resultCode == SERVICE_FINISH) {
            String result = data.getStringExtra(LIMIT);
            switch (requestCode) {
                case PENDING_CODE:
                    Log.i(MainActivity.LOG_TAG, "SERVICE_FINISH");
                    showDialog(false);
                    showResultDialog(result);
                    stopService(new Intent(Lab_3_activity.this, ServiceV1.class));
                    break;
            }
        }
    }

    private class LocalV1ButtonGoClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            try {
                PendingIntent pi;
                pi = createPendingResult(PENDING_CODE, new Intent(), 0);
                startService(
                        new Intent(Lab_3_activity.this, ServiceV1.class)
                                .putExtra(LIMIT, checkValue(primeNumber))
                                .putExtra(PARAM_PINTENT, pi));
            } catch (Exception ex) {
                Log.e(MainActivity.LOG_TAG, ex + "", ex);
            }
        }
    }

    private class LocalV2ButtonGoClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                showResultDialog(serviceV2.getPrimeNumbers(checkValue(primeNumber)));
            } catch (Exception ex){
                Log.e(MainActivity.LOG_TAG, ex + "", ex);
            }
        }
    }

    private class LocalV3ButtonGoClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                Intent intent = new Intent(Lab_3_activity.this, ServiceV3.class);

                startService(intent.putExtra(LIMIT, checkValue(primeNumber)));

                receiverV3 = new BroadcastReceiverV3();

                IntentFilter intentFilter = new IntentFilter(ServiceV3.ACTION_RESULT);
                intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
                registerReceiver(receiverV3, intentFilter);

            } catch (Exception ex){
                Log.e(MainActivity.LOG_TAG, ex + "", ex);
            }
        }
    }

    public int checkValue(EditText text) {
        if (text.getText().length() == 0) {
            text.setError(getString(R.string.error_length));
            throw new NullPointerException();
        } else {
            text.setError(null);
            return Integer.parseInt(text.getText().toString());
        }
    }

    private void showDialog(boolean show) {
        if (pd == null) {
            pd = new ProgressDialog(this);
            pd.setMessage(getString(R.string.calculate_wait));
        }

        if (show) {
            pd.show();
        } else {
            pd.dismiss();
        }

    }

    private void showResultDialog(String result) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(result);
        dlgAlert.setTitle(getString(R.string.title_result));
        dlgAlert.create().show();
    }

    private ServiceConnection connectionV2 = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            ServiceV2.LocalBinder binder = (ServiceV2.LocalBinder) service;
            serviceV2 = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    public class BroadcastReceiverV3 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent
                    .getStringExtra(ServiceV3.RESULT);
            showResultDialog(result);
        }
    }
}
