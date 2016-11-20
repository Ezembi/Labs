package labs.mobile.victor.lab_1.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import labs.mobile.victor.lab_1.R;

public class Main2Activity extends AppCompatActivity {

    private EditText text1;
    private EditText text2;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String string1, string2;

        string1 = getIntent().getExtras().getString("text1");
        string2 = getIntent().getExtras().getString("text2");

        text1 = (EditText) findViewById(R.id.editText);
        text2 = (EditText) findViewById(R.id.editText2);
        buttonSend = (Button) findViewById(R.id.buttoSend);

        text1.setText(string1);
        text2.setText(string2);

        buttonSend.setOnClickListener(new LocalOnClickListener(text1, text2));
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

            Intent intent = new Intent();
            intent.putExtra(MainActivity.TEXT_EXTRA_1, text1.getText().toString());
            intent.putExtra(MainActivity.TEXT_EXTRA_2, text2.getText().toString());

            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
