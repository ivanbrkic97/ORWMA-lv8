package dev.brkic.lv8.common;

import java.util.List;

import dev.brkic.lv8.models.Article;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IAPIInterface {
    @GET("products.json")
    Call<List<Article>> getArticles(@Query("brand") String brand,@Query("product_type") String type);
}
