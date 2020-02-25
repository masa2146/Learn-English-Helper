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
import com.blt.helperenglish.adapter.PodcastVoaContentAdapter;
import com.blt.helperenglish.common.retro.CallbackMethods;
import com.blt.helperenglish.common.retro.impl.APICallBackListener;
import com.blt.helperenglish.constant.PagesNames;
import com.blt.helperenglish.constant.PodcastType;
import com.blt.helperenglish.constant.ResponseType;
import com.blt.helperenglish.databinding.FragmentPodcastVoaBinding;
import com.blt.helperenglish.model.adapter.PodcastVoaContentData;
import com.blt.helperenglish.model.podcast.PodcastVoa;
import com.blt.helperenglish.model.retro.RetroPodcastVoa;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;


public class PodcastVoaFragment extends Fragment implements APICallBackListener<RetroPodcastVoa> {

    private View view;
    private RecyclerView recyclerView;
    private FragmentPodcastVoaBinding binding;
    private CallbackMethods<RetroPodcastVoa> callbackMethods;
    private ArrayList<PodcastVoaContentData> contentData;

    public PodcastVoaFragment() {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_podcast_voa, container, false);
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

        recyclerView = binding.mainPodcastVoa;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

    }

    public void getDataByPodcastType(ResponseType responseType, PodcastType podcastType, int page) {
        String tempUrl = "";
        switch (podcastType) {
            case PODCAST_VOA_1:
                tempUrl = PagesNames.PODCAST_VOA1_WITH_PAGE;
                break;
            case PODCAST_VOA_2:
                tempUrl = PagesNames.PODCAST_VOA2_WITH_PAGE;
                break;
        }
        callbackMethods.callData(responseType, "podcast/" + tempUrl, page);

    }


    @Override
    public void onResponse(Call<RetroPodcastVoa> call, Response<RetroPodcastVoa> response) {
        assert response.body() != null;
        convertToContentDataFromBaseModel(response.body().getContent());
        this.setContentDataToAdapter();
    }

    @Override
    public void onFailure(Call<RetroPodcastVoa> call, Throwable t) {
        System.out.println("PODCAST_VOA HATASI VAR " + t.getMessage());
    }

    private void convertToContentDataFromBaseModel(List<PodcastVoa> baseModels) {
        for (PodcastVoa model : baseModels) {
            this.contentData.add(new PodcastVoaContentData(R.drawable.profile_img, model.getTitle(), model.getDialog(), model.getUrl()));
            System.out.println("PODCAST VOA MODEL: "+model.getTitle());
        }
    }

    private void setContentDataToAdapter() {
        PodcastVoaContentAdapter podcastVoaContentAdapter = new PodcastVoaContentAdapter();
        podcastVoaContentAdapter.setMainView(this);
        podcastVoaContentAdapter.setPodcastVoaContentData(this.contentData.toArray(new PodcastVoaContentData[contentData.size()]));

        recyclerView.setAdapter(podcastVoaContentAdapter);
    }
}
