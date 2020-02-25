package com.blt.helperenglish.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blt.helperenglish.R;
import com.blt.helperenglish.adapter.PagePodcastAdapter;
import com.blt.helperenglish.constant.PagesNames;
import com.blt.helperenglish.databinding.FragmentMainPodcastBinding;
import com.blt.helperenglish.model.adapter.PagePodcastData;
import com.blt.helperenglish.view.podcast.PodcastLevelFragment;
import com.blt.helperenglish.view.podcast.PodcastVoaFragment;

public class MainPodcastFragment extends Fragment {

    private View view;
    private FragmentMainPodcastBinding binding;
    private PodcastLevelFragment podcastLevelFragment;
    private PodcastVoaFragment podcastVoaFragment;

    public MainPodcastFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_podcast, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        RecyclerView recyclerView = binding.mainPodcast;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        podcastLevelFragment = new PodcastLevelFragment();
        podcastVoaFragment = new PodcastVoaFragment();

        PagePodcastAdapter podcastPageAdapter = new PagePodcastAdapter();
        podcastPageAdapter.setMainView(this);
        podcastPageAdapter.setPodcastPageData(PagesNames.podcastPageDataArray);

        recyclerView.setAdapter(podcastPageAdapter);
    }

    public void connectPageWithResponseType(PagePodcastData podcastPageData) {
        switch (podcastPageData.getResponseType()) {
            // TODO: podcastvoa ve podcastlevel ile haberleşilecek
            case PODCAST_VOA:
                FragmentTransaction fragmentTransaction = ((FragmentActivity) getContext()).
                        getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.podcast_container_fragment, podcastVoaFragment, podcastVoaFragment.getClass().getName()).
                        addToBackStack(null);
                podcastVoaFragment.getDataByPodcastType(podcastPageData.getResponseType(), podcastPageData.getPodcastType(), 1);
                fragmentTransaction.commit();
                break;
            case PODCAST_LEVEL:
                fragmentTransaction = ((FragmentActivity) getContext()).
                        getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.podcast_container_fragment, podcastLevelFragment, podcastLevelFragment.getClass().getName()).
                        addToBackStack(null);
                podcastLevelFragment.getDataByPodcastType(podcastPageData.getResponseType(), podcastPageData.getPodcastType(), 1);
                fragmentTransaction.commit();
                break;
        }
        System.out.println("GRAMMAR CLICK ISLEMI");
    }
}