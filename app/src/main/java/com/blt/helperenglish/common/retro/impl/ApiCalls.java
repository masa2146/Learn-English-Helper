package com.blt.helperenglish.common.retro.impl;

import com.blt.helperenglish.model.retro.RetroNews;
import com.blt.helperenglish.model.retro.RetroPodcastLevel;
import com.blt.helperenglish.model.retro.RetroPodcastVoa;
import com.blt.helperenglish.model.translate.TranslateModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

import java.util.Map;

public interface ApiCalls<T> {

    @GET
    Call<RetroNews> getNews(@Url String url, @QueryMap Map<String, String> parameters);

    @GET
    Call<RetroPodcastLevel> getPodcastLevel(@Url String url, @QueryMap Map<String, String> parameters);

    @GET
    Call<RetroPodcastVoa> getPodcastVoa(@Url String url, @QueryMap Map<String, String> parameters);

    @GET
    Call<TranslateModel> getTranslate(@Url String url, @QueryMap Map<String, String> parameters);
}
