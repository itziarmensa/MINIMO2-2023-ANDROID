package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.grupo3.androiddsa.adapters.AdapterAbuso;
import com.grupo3.androiddsa.domain.Issue;
import com.grupo3.androiddsa.retrofit.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainListaAbusos extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Issue> listIssues;
    AdapterAbuso adapterAbuso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista_abusos);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        getIssues();

    }

    public void getIssues(){
        Api service = Api.retrofit.create(Api.class);
        Call<List<Issue>> call = service.getIssues();

        call.enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                listIssues = response.body();
                adapterAbuso = new AdapterAbuso(listIssues, new AdapterAbuso.OnItemClickListener() {
                    @Override
                    public void onItemClick(Issue issue) {

                    }
                });
                recyclerView.setAdapter(adapterAbuso);
            }

            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}