package com.junhyunsss.webcontentsviewer.networkmodule.melon;

import com.junhyunsss.webcontentsviewer.networkmodule.NetworkRequest;
import com.junhyunsss.webcontentsviewer.networkmodule.OnResultListener;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by junhyunss on 16. 3. 6..
 */
public class MelonRequest extends NetworkRequest<Melon> {

    public MelonRequest(OnResultListener listener) {
        super(listener);
    }

    @Override
    public URL getURL() {
        return null;
    }

    @Override
    public Melon parse(InputStream is) {
        return null;
    }
}
