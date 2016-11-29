package com.win.rxjavademo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 1234356 on 2016/11/29.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(getView());
        unbinder=ButterKnife.bind(this);
        initData();
    }

    public abstract  int getView();

    public abstract  void initData();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
