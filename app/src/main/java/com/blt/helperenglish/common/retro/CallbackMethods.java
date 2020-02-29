package com.blt.helperenglish.common.retro;

import androidx.annotation.NonNull;
import com.blt.helperenglish.common.retro.impl.APICallBackListener;
import com.blt.helperenglish.common.retro.impl.ApiCalls;
import com.blt.helperenglish.constant.ResponseType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.QueryMap;

import java.util.Map;

public class CallbackMethods<T> {

    private ApiCalls apiCalls;
    private APICallBackListener<T> apiCallBackListener;
    private Call<T> call;


    Map<String, String> parameters;
    private String url = "";


    public CallbackMethods(APICallBackListener<T> apiCallBackListener,String baseUrl) {
        this.apiCallBackListener = apiCallBackListener;
        apiCalls = ApiClient.getClient(baseUrl).create(ApiCalls.class);
    }

    @SuppressWarnings("unchecked")
    private void setResponseType(ResponseType responseType) {
        switch (responseType) {
            case PODCAST_LEVEL:
                call = apiCalls.getPodcastLevel(url, parameters);
                break;
            case PODCAST_VOA:
                call = apiCalls.getPodcastVoa(url, parameters);
                break;
            case NEWS:
                call = apiCalls.getNews(url, parameters);
                break;
            case TRANSLATE:
                call = apiCalls.getTranslate(url, parameters);
                break;
        }
    }

    public void callData(ResponseType responseType, String url,Map<String, String> parameters) {
        this.parameters = parameters;
        this.url = url;
        setResponseType(responseType);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                assert response.body() != null;
                apiCallBackListener.onResponse(call, response);
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                apiCallBackListener.onFailure(call, t);
            }
        });
    }
}
