package com.bwei.download;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bwei.download.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    //@BindView(R.id.progress_main)
    private ProgressBar progressBar;
    //@BindView(R.id.progress_Tv)
    private TextView progressTv;
    //@BindView(R.id.download)
    private Button downBt;
    //@BindView(R.id.pause)
    private Button pauseBt;
    //@BindView(R.id.restart)
    private Button restartBt;

    private DownLoadFile downLoadFile;
    private String loadUrl = "http://gdown.baidu.com/data/wisegame/d2fbbc8e64990454/wangyiyunyinle_87.apk";
    private String filePath = Environment.getExternalStorageDirectory() + "/" + "网易云音乐.apk";

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        downLoadFile = new DownLoadFile(this, loadUrl, filePath, 3);
        progressBar = findViewById(R.id.progress_main);
        progressTv = findViewById(R.id.progress_Tv);
        downBt = findViewById(R.id.download);
        pauseBt = findViewById(R.id.pause);
        restartBt = findViewById(R.id.restart);

        downBt.setOnClickListener(this);
        pauseBt.setOnClickListener(this);
        restartBt.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        downLoadFile.setOnDownLoadListener(new DownLoadFile.DownLoadListener() {
            @Override
            public void getProgress(int progress) {
                progressTv.setText("当前进度 ：" + progress + " %");
                progressBar.setProgress(progress);

            }

            @Override
            public void onComplete() {
                byte[] bytes = EncryptUtils.encryptMD5("满纸荒唐言".getBytes());
                String ss = EncryptUtils.encryptMD5ToString(bytes);

                Log.e("123",ss);

                Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.download:
                downLoadFile.downLoad();
                break;
            case R.id.pause:
                downLoadFile.onPause();
                break;
            case R.id.restart:
                downLoadFile.onReStart();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
        downLoadFile.cancel();
        downLoadFile.onDestroy();
    }
}
