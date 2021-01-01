package dev.brkic.lv8.models;

import com.google.gson.annotations.SerializedName;

public class Article {
    private int id;
    private String name;
    private String price;
    private String rating;
    private String description;
    @SerializedName("image_link")
    private String imageLink;

    public String getImageLink() {
        return imageLink;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }
}
