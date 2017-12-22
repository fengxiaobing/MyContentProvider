package com.example.mycontentprovider;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/**
 * Created by RF
 * on 2017/12/22.
 */

public class MyContentObserver extends ContentObserver {
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    private Handler handler;
    public MyContentObserver(Handler handler) {
        super(handler);
        this.handler = handler;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        handler.obtainMessage(1111,"9999").sendToTarget();
    }
}
