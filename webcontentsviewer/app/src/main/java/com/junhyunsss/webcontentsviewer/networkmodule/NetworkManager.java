package com.junhyunsss.webcontentsviewer.networkmodule;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by junhyunss on 2016-03-04.
 */
public class NetworkManager {

    public static final int MESSAGE_SUCCESS = 0;
    public static final int MESSAGE_FAIL = 1;
    public static final int MESSAGE_CANCEL = 2;

    private static NetworkManager sInstance = null;
    public static NetworkManager getsInstance() {
        if (sInstance == null) {
            synchronized (NetworkManager.class) {
                if (sInstance == null) {
                    return new NetworkManager();
                }
            }
        }
        return sInstance;
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

    private ThreadPoolExecutor mExecutor;

    public static final int CORE_POLL_SIZE = 5;
    public static final int MAXIMUM_POOL_SIZE = 64;
    public static final int KEEP_ALIVE_TIME = 5000;

    private LinkedBlockingQueue<Runnable> mRequestQueue = new LinkedBlockingQueue<Runnable>();
    private  NetworkManager() {
        mExecutor = new ThreadPoolExecutor(CORE_POLL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MICROSECONDS, mRequestQueue);
    }

    public <T> void getNetworkData(Context context, NetworkRequest<T> request) {
        mExecutor.execute(request);
    }

    public void sendSuccess(NetworkRequest request) {
        mHandler.sendMessage(Message.obtain(mHandler, MESSAGE_SUCCESS, request));
    }

    public void sendFail(NetworkRequest request) {
        mHandler.sendMessage(Message.obtain(mHandler, MESSAGE_FAIL, request));
    }

    public void requestCancel(NetworkRequest request) {
        request.cancel();
        mRequestQueue.remove(request);
    }


}
