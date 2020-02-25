package com.blt.helperenglish.common.retro.impl;

import com.blt.helperenglish.model.retro.RetroNews;
import com.blt.helperenglish.model.retro.RetroPodcastLevel;
import com.blt.helperenglish.model.retro.RetroPodcastVoa;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiCalls<T> {

    @GET
    Call<RetroNews> getNews(@Url String url, @Query("page") int page);

    @GET
    Call<RetroPodcastLevel> getPodcastLevel(@Url String url, @Query("page") int page);

    @GET
    Call<RetroPodcastVoa> getPodcastVoa(@Url String url, @Query("page") int page);
}
