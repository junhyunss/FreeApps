package com.junhyunsss.webcontentsviewer.networkmodule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by junhyunss on 16. 3. 6..
 */
abstract public class NetworkRequest<T> implements Runnable {

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final int DEFAULT_TIMEOUT = 10000;
    public static final int RETRY_MAX_COUNT = 3;

    private T mResult;
    private NetworkManager mManager;
    private OnResultListener<T> mListener;
    private int errorCode = -1;
    private int mRetry = RETRY_MAX_COUNT;
    private boolean isCanceled = false;
    private HttpURLConnection mConn;

    public NetworkRequest(OnResultListener listener) {
        mManager = NetworkManager.getsInstance();
        mListener = listener;
    }

    @Override
    public void run() {
        try {

            while (mRetry > 0 && !isCanceled) {

                URL url = getURL();
                mConn = (HttpURLConnection) url.openConnection();
                mConn.setConnectTimeout(DEFAULT_TIMEOUT);
                mConn.setReadTimeout(DEFAULT_TIMEOUT);
                String method = getRequestMethod();
                if (method.equals(METHOD_POST)) {
                    mConn.setDoOutput(true);
                }
                mConn.setRequestMethod(method);
                setRequestHeader(mConn);
                setRequestConfiguration(mConn);
                if (mConn.getDoOutput()) {
                    setRequestWriteOutput(mConn.getOutputStream());
                }
                if (isCanceled) continue;
                int responseCode = mConn.getResponseCode();
                if (isCanceled) continue;
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream is = mConn.getInputStream();
                    mResult = parse(is);
                    mManager.sendSuccess(this);
                    return;
                } else {
                    mRetry = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            mRetry--;
        }
        mManager.sendFail(this);


    }

    abstract public URL getURL();
    abstract public T parse(InputStream is);

    public String getRequestMethod() {
        return METHOD_GET;
    }

    public void setRequestHeader(HttpURLConnection conn) {

    }

    public void setRequestConfiguration(HttpURLConnection conn) {

    }

    public void setRequestWriteOutput(OutputStream os) {

    }

    public void sendSuccess() {
        if (mListener != null) {
            if (isCanceled) {
                mManager.requestCancel(this);
            } else {
                mListener.onSuccess(this, mResult);
            }
        }
    }

    public void sendFail() {
        if (mListener != null) {
            if (isCanceled) {
                mManager.requestCancel(this);
            } else {
                mListener.onFail(this, errorCode);
            }
        }
    }

    public void cancel() {
        isCanceled = true;
        if (mConn != null) {
            mConn.disconnect();
        }
        mManager.requestCancel(this);
    }
}
