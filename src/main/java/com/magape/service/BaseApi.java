package com.magape.service;

import com.magape.MagApe;
import com.magape.net.ApiRequest;
import com.magape.net.OkHttpUtils;
import com.magape.util.DigitalSignECDSA;
import com.magape.util.JSONUtil;

import java.nio.charset.Charset;

import static com.magape.net.ContentType.APPLICATION_JSON;
import static com.magape.net.HeaderConstant.*;

public class BaseApi {

    public static <T> T execute(ApiRequest apiRequest,Class<T> tClass) throws Exception {
        OkHttpUtils client = OkHttpUtils.builder().url(MagApe.getApiBase() + apiRequest.getPath())
                .addHeader(ACCESS_KEY,MagApe.publicKey)
                .addHeader(REQUEST_ID,apiRequest.getRequestId())
                .addHeader(SIGNATURE, DigitalSignECDSA.digitalSign(JSONUtil.toJSON(apiRequest.getParam()).getBytes(Charset.defaultCharset()), MagApe.privateKey));
        switch (apiRequest.getMethod()) {
            case POST -> {
                if (apiRequest.getContentType().equals(APPLICATION_JSON)) {
                    client.setJsonParam(JSONUtil.toJSON(apiRequest.getParam()));
                    client.post(true);
                }
            }
        }
        return JSONUtil.fromJSON(client.sync(), tClass);
    }
}
