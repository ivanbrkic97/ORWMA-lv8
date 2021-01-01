package dev.brkic.lv8.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import dev.brkic.lv8.R;
import dev.brkic.lv8.models.Article;

public class ItemViewHolder extends RecyclerView.ViewHolder
{

    private TextView nameTextView;
    private TextView ratingTextView;
    private TextView priceTextView;
    private TextView descriptionTextView;
    private ImageView imageView;

    public ItemViewHolder(@NonNull View itemView)
    {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        ratingTextView = itemView.findViewById(R.id.ratingTextView);
        priceTextView = itemView.findViewById(R.id.priceTextView);
        descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        imageView = itemView.findViewById(R.id.imageView);
    }

    public void  setArticle(Article article)
    {
        nameTextView.setText(article.getName());
        ratingTextView.setText(article.getRating());
        priceTextView.setText(article.getPrice());
        descriptionTextView.setText(article.getDescription());
        Picasso.with(imageView.getContext()).load(article.getImageLink()).into(imageView);
    }
}