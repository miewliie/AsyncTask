package com.augmentis.ayp.asyctest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;
    private int val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.textInput);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                val = Integer.parseInt(mEditText.getText().toString());
                new CountNumber().execute(val);

            }
        });
    }

    class CountNumber extends AsyncTask<Integer, Integer, String>{

        @Override
        protected String doInBackground(Integer... nums) {

//            int i = 0;
            for(int i = nums[0]; i >= 0; i--){
                publishProgress(i);
                try {
                    Thread.sleep(1000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "finish";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            mTextView = (TextView) findViewById(R.id.textShow);
            mTextView.setText(values[0].toString());
        }

        @Override
        protected void onPostExecute(String s) {
            mTextView.setText(s.toString());
        }
    }

}
