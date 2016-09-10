package com.qianfeng.hyh.day25lrucache1;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Administrator on 16-8-26.
 */
public class LruUtil {
    LruCache<String, Bitmap> lruCache;


    public LruCache initLru() {
        int maxSize = 4 * 1024 * 1024;
        return lruCache = new LruCache<>(maxSize);
    }

    public Bitmap getImageBitmap(String url) {
        if (lruCache != null) {
            return lruCache.get(url);
        }
        return null;
    }

    public void saveImageBitmap(String url, Bitmap bitmap) {
        if (getImageBitmap(url) == null) {
            lruCache.put(url, bitmap);
        }
    }

    public void deleteImageBitmap(String url) {
        if (getImageBitmap(url) != null) {
            lruCache.remove(url);
        }
    }

}
