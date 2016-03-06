package com.junhyunsss.webcontentsviewer.networkmodule;

/**
 * Created by junhyunss on 16. 3. 6..
 */
public interface OnResultListener<T> {
    public void onSuccess(NetworkRequest<T> request, T result);
    public void onFail(NetworkRequest<T> request, int code);

}
