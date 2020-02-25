package com.blt.helperenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.blt.helperenglish.R;
import com.blt.helperenglish.adapter.impl.OnItemClickListener;
import com.blt.helperenglish.databinding.CardPageNewsBinding;
import com.blt.helperenglish.model.adapter.PageNewsData;

import java.util.ArrayList;

/**
 * @author Fatih Bulut
 */
public class PageNewsAdapter extends RecyclerView.Adapter<PageNewsAdapter.NewsPageViewHolder> implements OnItemClickListener {

    private ArrayList<PageNewsData> newsPageData;
    private LayoutInflater layoutInflater;
    private Context context;

    public void setMainPageData(ArrayList<PageNewsData> newsPageData) {
        this.newsPageData = newsPageData;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NewsPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater != null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CardPageNewsBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.card_page_news, parent, false);
        return new NewsPageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsPageViewHolder holder, int i) {
        holder.binding.setModel(newsPageData.get(i));
        holder.binding.setListener(this);
    }

    @Override
    public int getItemCount() {
        return newsPageData != null ? newsPageData.size() : 0;
    }

    @Override
    public void onItemClick(Object item) {
        System.out.println("NEW ADAPTER CLICK");
    }

    public static class NewsPageViewHolder extends RecyclerView.ViewHolder {
        private CardPageNewsBinding binding;

        public NewsPageViewHolder(@NonNull CardPageNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
