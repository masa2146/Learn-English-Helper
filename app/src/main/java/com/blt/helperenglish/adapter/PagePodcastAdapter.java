package com.blt.helperenglish.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.blt.helperenglish.R;
import com.blt.helperenglish.adapter.impl.OnItemClickListener;
import com.blt.helperenglish.databinding.CardPagePodcastBinding;
import com.blt.helperenglish.model.adapter.PagePodcastData;
import com.blt.helperenglish.view.main.MainPodcastFragment;

/**
 * @author Fatih Bulut
 */
public class PagePodcastAdapter  extends RecyclerView.Adapter<PagePodcastAdapter.PodcastPageViewHolder> implements OnItemClickListener {

    private PagePodcastData[] podcastPageData;
    private LayoutInflater layoutInflater;
    private Fragment view;

    public void setPodcastPageData(PagePodcastData[] grammarPageData) {
        this.podcastPageData = grammarPageData;
        notifyDataSetChanged();
    }

    public void setMainView(Fragment view) {
        this.view = view;
    }

    @NonNull
    @Override
    public PodcastPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CardPagePodcastBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.card_page_podcast, parent, false);
        return new PodcastPageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastPageViewHolder holder, int i) {
        holder.binding.setModel(podcastPageData[i]);
        holder.binding.setListener(this);
    }

    @Override
    public int getItemCount() {
        return podcastPageData != null ? podcastPageData.length : 0;
    }

    @Override
    public void onItemClick(Object item) {
        PagePodcastData podcastPageData = (PagePodcastData) item;
        ((MainPodcastFragment)view).connectPageWithResponseType(podcastPageData);
    }

    static class PodcastPageViewHolder extends RecyclerView.ViewHolder {

        private CardPagePodcastBinding binding;

        PodcastPageViewHolder(@NonNull CardPagePodcastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
