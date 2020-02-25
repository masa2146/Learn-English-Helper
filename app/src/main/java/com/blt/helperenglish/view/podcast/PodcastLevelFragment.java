package com.blt.helperenglish.view.podcast;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blt.helperenglish.R;
import com.blt.helperenglish.adapter.PodcastLevelContentAdapter;
import com.blt.helperenglish.common.retro.CallbackMethods;
import com.blt.helperenglish.common.retro.impl.APICallBackListener;
import com.blt.helperenglish.constant.PagesNames;
import com.blt.helperenglish.constant.PodcastType;
import com.blt.helperenglish.constant.ResponseType;
import com.blt.helperenglish.databinding.FragmentPodcastLevelBinding;
import com.blt.helperenglish.model.adapter.PodcastLevelContentData;
import com.blt.helperenglish.model.podcast.PodcastLevel;
import com.blt.helperenglish.model.retro.RetroPodcastLevel;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class PodcastLevelFragment extends Fragment implements APICallBackListener<RetroPodcastLevel> {

    private View view;
    private RecyclerView recyclerView;
    private FragmentPodcastLevelBinding binding;
    private CallbackMethods<RetroPodcastLevel> callbackMethods;
    private ArrayList<PodcastLevelContentData> contentData;

    public PodcastLevelFragment() {
        //noinspection unchecked
        callbackMethods = new CallbackMethods(this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_podcast_level, container, false);
        view = binding.getRoot();
        init();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void init() {
        contentData = new ArrayList<>();

        recyclerView = binding.mainPodcastLevel;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

    }

    public void getDataByPodcastType(ResponseType responseType, PodcastType podcastType, int page) {
        String tempUrl = "";
        switch (podcastType) {
            case PODCAST_LEVEL_1:
                tempUrl = PagesNames.PODCAST_LEVEL1_WITH_PAGE;
                break;
            case PODCAST_LEVEL_2:
                tempUrl = PagesNames.PODCAST_LEVEL2_WITH_PAGE;
                break;
            case PODCAST_LEVEL_3:
                tempUrl = PagesNames.PODCAST_LEVEL3_WITH_PAGE;
                break;
            case PODCAST_LEVEL_BUSINESS:
                tempUrl = PagesNames.PODCAST_LEVEL_BUSINESS_WITH_PAGE;
                break;
        }
        callbackMethods.callData(responseType, "podcast/" + tempUrl, page);
    }

    @Override
    public void onResponse(Call<RetroPodcastLevel> call, Response<RetroPodcastLevel> response) {
        assert response.body() != null;
        convertToContentDataFromBaseModel(response.body().getContent());
        this.setContentDataToAdapter();
    }

    @Override
    public void onFailure(Call<RetroPodcastLevel> call, Throwable t) {

    }

    private void convertToContentDataFromBaseModel(List<PodcastLevel> baseModels) {
        for (PodcastLevel model : baseModels) {
            this.contentData.add(new PodcastLevelContentData(R.drawable.profile_img, model.getTitle(), model.getDescription(), model.getUrl()));
            System.out.println("PODCAST LEVEL MODEL: " + model.getTitle());
        }
    }

    private void setContentDataToAdapter() {
        PodcastLevelContentAdapter podcastLevelContentAdapter = new PodcastLevelContentAdapter();
        podcastLevelContentAdapter.setMainView(this);
        podcastLevelContentAdapter.setPodcastLevelContentData(this.contentData.toArray(new PodcastLevelContentData[contentData.size()]));

        recyclerView.setAdapter(podcastLevelContentAdapter);
    }
}
