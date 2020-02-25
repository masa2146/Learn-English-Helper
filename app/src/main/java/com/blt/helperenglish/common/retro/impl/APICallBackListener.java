package com.blt.helperenglish.common.retro.impl;

import retrofit2.Call;
import retrofit2.Response;

public interface APICallBackListener<T> {
    void onResponse(Call<T> call, Response<T> response);
    void onFailure(Call<T> call, Throwable t);
}
