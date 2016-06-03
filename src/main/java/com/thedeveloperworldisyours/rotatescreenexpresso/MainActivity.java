package com.thedeveloperworldisyours.rotatescreenexpresso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_COUNT = "count";
    private int mCount = 0;
    private TextView mCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCountView = (TextView) findViewById(R.id.activity_main_count_text_view);

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt(KEY_COUNT, 0);
        }

        updateCount();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, mCount);
    }

    public void increment(View v) {
        mCount += 1;
        updateCount();
    }

    private void updateCount() {
        mCountView.setText(String.valueOf(mCount));
    }

}
