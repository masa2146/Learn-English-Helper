package com.blt.helperenglish.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.blt.helperenglish.R;
import com.blt.helperenglish.adapter.impl.OnItemClickListener;
import com.blt.helperenglish.databinding.CardPodcastVoaContentBinding;
import com.blt.helperenglish.model.adapter.PodcastVoaContentData;

public class PodcastVoaContentAdapter extends RecyclerView.Adapter<PodcastVoaContentAdapter.PodcastVoaContentViewHolder> implements OnItemClickListener {

    private PodcastVoaContentData[] podcastVoaContentData;
    private LayoutInflater layoutInflater;
    private Fragment view;

    public void setPodcastVoaContentData(PodcastVoaContentData[] podcastVoaContentData) {
        this.podcastVoaContentData = podcastVoaContentData;
        notifyDataSetChanged();
    }

    public void setMainView(Fragment view) {
        this.view = view;
    }

    @NonNull
    @Override
    public PodcastVoaContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CardPodcastVoaContentBinding podcastVoaContentBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.card_podcast_voa_content, parent, false);
        return new PodcastVoaContentViewHolder(podcastVoaContentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastVoaContentViewHolder holder, int position) {
        holder.cardPodcastVoaContentBinding.setModel(podcastVoaContentData[position]);
        holder.cardPodcastVoaContentBinding.setListener(this);

    }

    @Override
    public int getItemCount() {
        return podcastVoaContentData.length;
    }

    @Override
    public void onItemClick(Object item) {

    }

    static class PodcastVoaContentViewHolder extends RecyclerView.ViewHolder {
        CardPodcastVoaContentBinding cardPodcastVoaContentBinding;

        PodcastVoaContentViewHolder(@NonNull CardPodcastVoaContentBinding cardPodcastVoaContentBinding) {
            super(cardPodcastVoaContentBinding.getRoot());
            this.cardPodcastVoaContentBinding = cardPodcastVoaContentBinding;
        }
    }
}
