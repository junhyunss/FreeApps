package com.junhyunsss.webcontentsviewer.networkmodule;

import com.junhyunsss.webcontentsviewer.networkmodule.melon.MelonRequest;

/**
 * Created by junhyunss on 16. 3. 6..
 */
public class RequestFactory {

    public static final int REQUEST_MELON = 0;

    public static <T> NetworkRequest getNetworkRequest(int type, OnResultListener<T> listener) {
        switch (type) {
            case REQUEST_MELON:
                return new MelonRequest(listener);
        }

        return null;
    }
}
