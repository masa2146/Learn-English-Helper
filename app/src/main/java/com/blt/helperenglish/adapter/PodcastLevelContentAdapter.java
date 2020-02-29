package com.blt.helperenglish.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.blt.helperenglish.R;
import com.blt.helperenglish.adapter.impl.OnItemClickListener;
import com.blt.helperenglish.databinding.CardPodcastLevelContentBinding;
import com.blt.helperenglish.model.adapter.PodcastLevelContentData;

/**
 * @author Fatih Bulut
 * This class is for card_podcast_level_content.xml file.
 */
public class PodcastLevelContentAdapter extends RecyclerView.Adapter<PodcastLevelContentAdapter.PodcastLevelContentViewHolder> implements OnItemClickListener {

    private PodcastLevelContentData[] podcastLevelContentData;
    private LayoutInflater layoutInflater;
    private Fragment view;

    public void setPodcastLevelContentData(PodcastLevelContentData[] podcastLevelContentData) {
        this.podcastLevelContentData = podcastLevelContentData;
        notifyDataSetChanged();
    }

    public void setMainView(Fragment view) {
        this.view = view;
    }

    @NonNull
    @Override
    public PodcastLevelContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CardPodcastLevelContentBinding cardPodcastLevelContentBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.card_podcast_level_content, parent, false);
        return new PodcastLevelContentViewHolder(cardPodcastLevelContentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastLevelContentViewHolder holder, int position) {
        holder.cardPodcastLevelContentBinding.setModel(podcastLevelContentData[position]);
        holder.cardPodcastLevelContentBinding.setListener(this);
    }

    @Override
    public int getItemCount() {
        return podcastLevelContentData.length;
    }

    @Override
    public void onItemClick(Object item) {
    }

    class PodcastLevelContentViewHolder extends RecyclerView.ViewHolder {
        private CardPodcastLevelContentBinding cardPodcastLevelContentBinding;

        PodcastLevelContentViewHolder(@NonNull CardPodcastLevelContentBinding cardPodcastLevelContentBinding) {
            super(cardPodcastLevelContentBinding.getRoot());
            this.cardPodcastLevelContentBinding = cardPodcastLevelContentBinding;
        }
    }
}
