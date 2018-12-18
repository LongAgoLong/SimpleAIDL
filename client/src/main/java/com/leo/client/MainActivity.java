package com.leo.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leo.client.util.AIDLUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBindBtn;
    private Button mUnbindBtn;
    private Button mSendRequestBtn;
    private TextView mResultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBindBtn = findViewById(R.id.bindBtn);
        mBindBtn.setOnClickListener(this);
        mUnbindBtn = findViewById(R.id.unbindBtn);
        mUnbindBtn.setOnClickListener(this);
        mSendRequestBtn = findViewById(R.id.sendRequestBtn);
        mSendRequestBtn.setOnClickListener(this);
        mResultTv = findViewById(R.id.resultTv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bindBtn:
                AIDLUtil.getInstance().bindService(MainActivity.this);
                break;
            case R.id.unbindBtn:
                AIDLUtil.getInstance().unbindService(MainActivity.this);
                break;
            case R.id.sendRequestBtn:
                String request = AIDLUtil.getInstance().request("char", "");
                if (!TextUtils.isEmpty(request)) {
                    mResultTv.append(request);
                    mResultTv.append("\n");
                }
                break;
        }
    }
}
