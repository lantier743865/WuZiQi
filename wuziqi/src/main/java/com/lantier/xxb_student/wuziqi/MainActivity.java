package com.lantier.xxb_student.wuziqi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private WuZiQiPanel wuZiQiPanel;
    private TextView again;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wuZiQiPanel = (WuZiQiPanel) findViewById(R.id.wuziqi);
        again = (TextView) findViewById(R.id.again);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wuZiQiPanel.start();
            }
        });
    }

}
