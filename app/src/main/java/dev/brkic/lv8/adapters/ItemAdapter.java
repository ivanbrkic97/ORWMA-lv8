package dev.brkic.lv8.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.brkic.lv8.R;
import dev.brkic.lv8.models.Article;
import dev.brkic.lv8.viewHolders.ItemViewHolder;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Article> dataList = new ArrayList<>();

    public ItemAdapter() {
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_template, parent, false);
        return new ItemViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setArticle(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<Article> data) {
        this.dataList.clear();
        this.dataList.addAll(data);
        notifyDataSetChanged();
    }

    public void removeData() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    public void addNewCell(Article article, int position) {
        if (dataList.size() >= position) {
            dataList.add(position, article);
            notifyItemInserted(position);
        }
    }

    public void removeCell(int position) {
        if (dataList.size() > position) {
            dataList.remove(position);
            notifyItemRemoved(position);
        }
    }

}