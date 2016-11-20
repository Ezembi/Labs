package labs.mobile.victor.lab_1.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import labs.mobile.victor.lab_1.Lab_4.Lab4Activity;
import labs.mobile.victor.lab_1.R;

public class MainActivity extends AppCompatActivity {

    private EditText text1;
    private EditText text2;
    private Button buttonSend;
    private Button lab_3;
    public static final int REQUEST_CODE = 1;
    public static String TEXT_EXTRA_1 = "text1";
    public static String TEXT_EXTRA_2 = "text2";

    public static final String LOG_TAG = "Lab_all";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (EditText) findViewById(R.id.editText);
        text2 = (EditText) findViewById(R.id.editText2);
        buttonSend = (Button) findViewById(R.id.buttoSend);
        lab_3 = (Button) findViewById(R.id.lab_3);

        Button lab_4 = (Button) findViewById(R.id.lab_4);
        lab_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lab4Activity.class));
            }
        });

        buttonSend.setOnClickListener(new LocalOnClickListener(text1, text2));
        lab_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lab_3_activity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(LOG_TAG,"Result: request = " + requestCode + "; result = " + resultCode + "; data = " + data);
        if(resultCode == RESULT_OK){
            text1.setText(data.getStringExtra(TEXT_EXTRA_1));
            text2.setText(data.getStringExtra(TEXT_EXTRA_2));
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.answer_error), Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class LocalOnClickListener implements View.OnClickListener{

        private EditText text1;
        private EditText text2;

        public LocalOnClickListener(EditText text1, EditText text2){
            this.text1 = text1;
            this.text2 = text2;
        }

        @Override
        public void onClick(View v) {
            if(text1.getText().length() == 0){
                text1.setError(getString(R.string.error_length));
                return;
            } else {
                text1.setError(null);
            }

            if(text2.getText().length() == 0){
                text2.setError(getString(R.string.error_length));
                return;
            } else {
                text2.setError(null);
            }

            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra(TEXT_EXTRA_1, text1.getText().toString());
            intent.putExtra(TEXT_EXTRA_2, text2.getText().toString());

            startActivityForResult(intent, REQUEST_CODE);
        }
    }
}
