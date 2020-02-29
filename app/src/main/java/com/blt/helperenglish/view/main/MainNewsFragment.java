package com.blt.helperenglish.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.blt.helperenglish.R;
import com.blt.helperenglish.common.retro.CallbackMethods;
import com.blt.helperenglish.common.retro.impl.APICallBackListener;
import com.blt.helperenglish.constant.PagesNames;
import com.blt.helperenglish.constant.ResponseType;
import com.blt.helperenglish.model.retro.RetroNews;
import retrofit2.Call;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;


public class MainNewsFragment extends Fragment implements APICallBackListener<RetroNews> {
    public MainNewsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CallbackMethods<RetroNews> callbackMethods = new CallbackMethods<>(this, PagesNames.API_BASE);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("page", "1");

        callbackMethods.callData(ResponseType.NEWS, "", parameters);
    }

    @Override
    public void onResponse(Call<RetroNews> call, Response<RetroNews> response) {

    }

    @Override
    public void onFailure(Call<RetroNews> call, Throwable t) {

    }
}
