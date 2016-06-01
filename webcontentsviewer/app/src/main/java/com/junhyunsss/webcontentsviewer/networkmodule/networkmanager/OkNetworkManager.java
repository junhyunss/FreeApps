package com.junhyunsss.webcontentsviewer.networkmodule.networkmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.junhyunsss.webcontentsviewer.networkmodule.NetworkRequest;
import com.junhyunsss.webcontentsviewer.networkmodule.OnResultListener;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;

/**
 * Created by junhyunss on 16. 3. 7..
 */
public class OkNetworkManager {
    public static final int MESSAGE_SUCCESS = 0;
    public static final int MESSAGE_FAIL = 1;
    public static final int MESSAGE_CANCEL = 2;

    private static OkNetworkManager sInstance = null;
    public static OkNetworkManager getsInstance() {
        if (sInstance == null) {
            synchronized (OkNetworkManager.class) {
                if (sInstance == null) {
                    return new OkNetworkManager();
                }
            }
        }
        return sInstance;
    }

    private OkHttpClient mClient;

    private OkNetworkManager () {
        mClient = new OkHttpClient();
    }

    private static class OkResult<T> {
        OnResultListener<T> listener;
        Request request;
        T result;ㅌ
        IOException e;
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            NetworkRequest request = (NetworkRequest) msg.obj;
            switch (msg.what) {
                case MESSAGE_SUCCESS:
                    request.sendSuccess();
                    break;
                case MESSAGE_FAIL:
                    request.sendFail();;
                    break;
            }
        }
    };
}
