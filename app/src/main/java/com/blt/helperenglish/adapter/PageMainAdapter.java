package com.blt.helperenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.blt.helperenglish.R;
import com.blt.helperenglish.adapter.impl.OnItemClickListener;
import com.blt.helperenglish.databinding.CardPageMainBinding;
import com.blt.helperenglish.model.adapter.PageMainData;

/**
 * @author Fatih Bulut
 */
public class PageMainAdapter extends RecyclerView.Adapter<PageMainAdapter.MainPageViewHolder> implements OnItemClickListener {

    private PageMainData[] mainPageData;
    private LayoutInflater layoutInflater;
    private Context context;


    public void setMainPageData(PageMainData[] mainPageData) {
        this.mainPageData = mainPageData;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public MainPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CardPageMainBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.card_page_main, parent, false);

        return new MainPageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageViewHolder holder, int i) {
        holder.binding.setModel(mainPageData[i]);
        holder.binding.setListener(this);
    }

    @Override
    public int getItemCount() {
        return mainPageData != null ? mainPageData.length : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onItemClick(Object item) {
        System.out.println((((PageMainData) item)).getContentText());
        ((FragmentActivity) context).
                getSupportFragmentManager().
                beginTransaction().
                replace(R.id.container_fragment, (((PageMainData) item)).getFragment(), (((PageMainData) item)).getClass().getSimpleName()).
                addToBackStack(null)
                .commit();
    }

    static class MainPageViewHolder extends RecyclerView.ViewHolder {

        private CardPageMainBinding binding;

        MainPageViewHolder(@NonNull CardPageMainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
