package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista_abusos);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        progressBar = findViewById(R.id.progressBar);

        getIssues();

    }

    public void getIssues(){
        progressBar.setVisibility(View.VISIBLE);
        Api service = Api.retrofit.create(Api.class);
        Call<List<Issue>> call = service.getIssues();

        call.enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                progressBar.setVisibility(View.GONE);
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
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void volver (View view){
        Intent i = new Intent(MainListaAbusos.this, MainAbuso.class);
        startActivity(i);
    }
}