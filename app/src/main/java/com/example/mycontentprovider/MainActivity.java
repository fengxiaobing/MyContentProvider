package com.example.mycontentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private Uri uri;
    ContentResolver contentResolver;
    //用于内容观察者的Handler
Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.e("","");
                return false;
            }
        });
        /**
         * 对user表进行操作
         */
        // 设置URI
        uri = Uri.parse("content://com.example.mycontentprovider/user");
//        uri = Uri.parse("content://com.example.mycontentprovider/job");

        contentResolver = getContentResolver();

        //注册内容观察者（handler为监听返回执行的方法）
        contentResolver.registerContentObserver(uri, true, new MyContentObserver(handler));

        Button btn_read = findViewById(R.id.btn_read);
        btn_read.setOnClickListener(this);
        Button btn_insert = findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_read:
                // 通过ContentResolver 向ContentProvider中查询数据
                // 获取ContentResolver
                // 通过ContentResolver 向ContentProvider中查询数据
                Cursor cursor = contentResolver.query(uri, new String[]{"_id","name"}, null, null, null);
                while (cursor.moveToNext()){
                    // 将表中数据全部输出
                   Log.e("TAG","query book:" + cursor.getInt(0) +" "+ cursor.getString(1));

                }
                cursor.close();
                break;
            case R.id.btn_insert:
                //插入表中数据
                ContentValues values = new ContentValues();
                values.put("_id", 3);
                values.put("name", "Iverson");
//                values.put("job", "333");
                // 获取ContentResolver

                // 通过ContentResolver 根据URI 向ContentProvider中插入数据
                contentResolver.insert(uri,values);
                break;

        }

    }
}
