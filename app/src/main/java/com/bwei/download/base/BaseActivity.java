package com.bwei.download.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther :Hming
 * @Date : 2019/7/12  19:40
 * @Description: BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity {


    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImmersionBar.with(this).transparentBar().statusBarDarkFont(true).init();//透明效果
        bind = ButterKnife.bind(this);
        setContentView(bindLayoutId());
        initView();
        initData();
    }

    protected abstract int bindLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
