package dev.brkic.lv8.common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {
    private static final String BASE_API =
            "https://makeup-api.herokuapp.com/api/v1/";
    private static IAPIInterface apiInterface;
    public static IAPIInterface getApiInterface() {
        if (apiInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterface = retrofit.create(IAPIInterface.class);
        }
        return apiInterface;
    }
}

