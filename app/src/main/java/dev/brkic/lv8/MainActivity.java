package dev.brkic.lv8;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.brkic.lv8.adapters.ItemAdapter;
import dev.brkic.lv8.common.NetworkUtils;
import dev.brkic.lv8.models.Article;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private Call<List<Article>> apiCall;
    private String brand;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brand = getResources().getStringArray(R.array.brands)[0].toString();
        type = getResources().getStringArray(R.array.types)[0].toString();
        Spinner brandSpinner = findViewById(R.id.brandSpinner);
        Spinner typeSpinner = findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence>  brandSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.brands, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>  typeSpinnerAdapter=ArrayAdapter.createFromResource(this,R.array.types, android.R.layout.simple_spinner_item);
        brandSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(brandSpinnerAdapter);
        typeSpinner.setAdapter(typeSpinnerAdapter);
        brandSpinner.setOnItemSelectedListener(this);
        typeSpinner.setOnItemSelectedListener(this);
        setupRecycler();
        setUpApiCall();
    }

    private  void setupRecycler()
    {
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }

    public void setupRecyclerData(List<Article> articles)
    {
        adapter.addData(articles);
    }

    private void setUpApiCall() {
        apiCall = NetworkUtils.getApiInterface().getArticles(brand,type);
        apiCall.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>>
                    response) {
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    showArticles(response.body());
                }
                else{
                    removeArticles();
                    Toast.makeText(MainActivity.this, getText(R.string.notFound) + brand+ " " + type + "\".", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showArticles(List<Article> data) {
        setupRecyclerData(data);
    }
    private void removeArticles() {
        adapter.removeData();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (apiCall != null)
            apiCall.cancel();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner brandSpin = (Spinner)parent;
        Spinner typeSpin = (Spinner)parent;
        if(brandSpin.getId() == R.id.brandSpinner)
        {
            brand = brandSpin.getItemAtPosition(position).toString();
        }
        if(typeSpin.getId() == R.id.typeSpinner)
        {
            type = typeSpin.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void searchArticles(View view) {
        setUpApiCall();
    }
}