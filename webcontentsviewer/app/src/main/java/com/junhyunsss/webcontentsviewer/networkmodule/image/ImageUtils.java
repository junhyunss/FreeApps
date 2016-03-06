package com.junhyunsss.webcontentsviewer.networkmodule.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.ScriptGroup;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by junhyunss on 16. 3. 6..
 */
public class ImageUtils {

    public static int adjustSampleSize(int width, int height, BitmapFactory.Options options) {
        if (width == 0 || height == 0) return 1;

        int widthScale = options.outWidth / width;
        int heightScale = options.outHeight / height;

        int scale = widthScale < heightScale ? widthScale : heightScale;

        if (scale == 0) {
            return 1;
        }
        return scale;
    }

    public static Bitmap getBitmap(InputStream is, int width, int height) {
        is = getSupportResetInputStream(is);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, options);
        int scale = adjustSampleSize(width, height, options);
        options = new BitmapFactory.Options();
        options.inSampleSize = scale;
        try {
            is.reset();
            Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
            if (bitmap != null) {
                if (width != 0 && height != 0) {
                    Bitmap resizeBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
                    bitmap.recycle();;
                    return resizeBitmap;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static InputStream getSupportResetInputStream(InputStream is) {
        return is;
    }
}
