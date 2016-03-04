package com.junhyunsss.webcontentsviewer.networkmodule;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by jhsong on 2016-03-04.
 */
public class NetworkManager {
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

    private ThreadPoolExecutor mExecutor;

    public static final int CORE_POLL_SIZE = 5;
    public static final int MAXIMUM_POOL_SIZE = 64;
    public static final int KEEP_ALIVE_TIME = 5000;

    private LinkedBlockingQueue<Runnable> mRequestQueue = new LinkedBlockingQueue<Runnable>();
    private  NetworkManager() {
        mExecutor = new ThreadPoolExecutor(CORE_POLL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MICROSECONDS, mRequestQueue);
    }

}
