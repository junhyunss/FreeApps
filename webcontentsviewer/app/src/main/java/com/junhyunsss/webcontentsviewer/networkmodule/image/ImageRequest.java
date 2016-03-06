package com.junhyunsss.webcontentsviewer.networkmodule.image;

import android.graphics.Bitmap;

import com.junhyunsss.webcontentsviewer.networkmodule.NetworkRequest;
import com.junhyunsss.webcontentsviewer.networkmodule.OnResultListener;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by junhyunss on 16. 3. 6..
 */
public class ImageRequest extends NetworkRequest<Bitmap> {

    private String mURL;
    private int mWidth = 0;
    private int mHeight = 0;

    public ImageRequest(String url, OnResultListener listener) {
        super(listener);
        mURL = url;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public void setWidth(int width) {
        mWidth = width;
    }


    @Override
    public URL getURL() {
        return null;
    }

    @Override
    public Bitmap parse(InputStream is) {
        return ImageUtils.getBitmap(is, mWidth, mHeight);
    }
}
