package com.augmentis.ayp.asyctest;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TestLooper.Callbacks{
    private EditText mEditText;
    protected TextView mTextView;
    private Button mButton;
    private Handler handlerMainThread;
    private Integer val;
    private TestLooper testLooper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlerMainThread = new Handler();

        mEditText = (EditText) findViewById(R.id.textInput);
        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.textShow);

        testLooper = new TestLooper("ThreadName",this);

        testLooper.setHandlerMainThread(handlerMainThread);
        testLooper.start();
        testLooper.onLooperPrepared();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //--- AsyncTask --- //
                val = Integer.parseInt(mEditText.getText().toString());
                testLooper.addMessage(val);
//                new CountNumber().execute(val);

                //



            }
        });
    }

    @Override
    public void convertNum(String nums) {
        mTextView.setText(nums);

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

//            mTextView = (TextView) findViewById(R.id.textShow);
//            mTextView.setText(values[0].toString());
        }

        @Override
        protected void onPostExecute(String s) {
            mTextView.setText(s.toString());
        }
    }

}
