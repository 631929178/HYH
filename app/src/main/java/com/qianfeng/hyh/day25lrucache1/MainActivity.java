package com.qianfeng.hyh.day25lrucache1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1, button2, button3, button4;
    ImageView imageView;
    String url = "http://img.firefoxchina.cn/2016/07/4/201607290822490.jpg";
    LruUtil lruUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        button1 = (Button) findViewById(R.id.button1Id);
        button2 = (Button) findViewById(R.id.button2Id);
        button3 = (Button) findViewById(R.id.button3Id);
        button4 = (Button) findViewById(R.id.button4Id);
        imageView = (ImageView) findViewById(R.id.imageId);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        lruUtil = new LruUtil();
        lruUtil.initLru();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    Bitmap bitmap = null;
    Bitmap cacheBitmap = null;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1Id:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        byte[] imagebyte = DownLoadUtils.getImageByte(url);
                        Log.d("hyh", "iamgebyte=====" + imagebyte);
                        bitmap = BitmapFactory.decodeByteArray(imagebyte, 0, imagebyte.length);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();

                break;
            case R.id.button2Id:
                if (bitmap != null) {
                    lruUtil.saveImageBitmap(url, bitmap);
                }

                break;
            case R.id.button3Id:
                cacheBitmap = lruUtil.getImageBitmap(url);
                Log.d("hyh", "取出来的图片=====" + cacheBitmap);
                imageView.setImageBitmap(cacheBitmap);
                break;
            case R.id.button4Id:
                lruUtil.deleteImageBitmap(url);
                break;
            default:

                break;
        }
    }
}
