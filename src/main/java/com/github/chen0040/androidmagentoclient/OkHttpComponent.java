package com.github.chen0040.androidmagentoclient;

import com.alibaba.fastjson.JSON;
import com.github.chen0040.magento.services.HttpComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by chen0 on 19/8/2017.
 */

public class OkHttpComponent implements HttpComponent {

    private OkHttpClient client;

    public OkHttpComponent() {
        client = new OkHttpClient.Builder().build();
    }

    @Override
    public String post(String url, String body, Map<String, String> headers) {
        MediaType textPlainMT = MediaType.parse("text/plain; charset=utf-8");

        Request.Builder requestBuilder = new Request.Builder().url(url)
                .post(RequestBody.create(textPlainMT, body));

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }

        Request request = requestBuilder.build();

        String resp = null;
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                ResponseBody b = response.body();
                if(b != null) {
                    resp = b.string();
                }
            }
            response.close();
        } catch (IOException e) {
            resp = e.getMessage();
        }

        return resp;
    }

    @Override
    public String put(String url, String body, Map<String, String> headers) {

        MediaType textPlainMT = MediaType.parse("text/plain; charset=utf-8");

        Request.Builder requestBuilder = new Request.Builder().url(url)
                .put(RequestBody.create(textPlainMT, body));

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }

        Request request = requestBuilder.build();

        String resp = null;
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                ResponseBody b = response.body();
                if(b != null) {
                    resp = b.string();
                }
            }
            response.close();
        } catch (IOException e) {
            resp = e.getMessage();
        }

        return resp;
    }

    @Override
    public String delete(String url, Map<String, String> headers) {

        Request.Builder requestBuilder = new Request.Builder()
                .url(url).delete();

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }

        Request request = requestBuilder
                .build();

        String resp = null;
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                ResponseBody b = response.body();
                if(b != null) {
                    resp = b.string();
                }
            }
            response.close();
        } catch (IOException e) {
            resp = e.getMessage();
        }

        return resp;
    }

    @Override
    public String get(String uri, Map<String, String> headers) {

        Request.Builder requestBuilder = new Request.Builder()
                .url(uri).get();

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }

        Request request = requestBuilder
                .build();

        String resp = null;
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                ResponseBody b = response.body();
                if(b != null) {
                    resp = b.string();
                }
            }
            response.close();
        } catch (IOException e) {
            resp = e.getMessage();
        }

        return resp;

    }

    @Override
    public String jsonPost(String uri, Map<String, String> data) {
        Map<String, String> headers = new HashMap<>();
        String body = JSON.toJSONString(data);
        headers.put("Content-Type", "application/json");
        return post(uri, body, headers);
    }
}
