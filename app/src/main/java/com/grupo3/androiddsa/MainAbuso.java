package com.grupo3.androiddsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.grupo3.androiddsa.domain.Issue;
import com.grupo3.androiddsa.retrofit.Api;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAbuso extends AppCompatActivity {

    EditText nameInformer, messageAbuso;
    Button enviar;
    String informer, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_abuso);

        nameInformer = findViewById(R.id.nameUserAbuso);
        messageAbuso = findViewById(R.id.messageAbuso);
        enviar = findViewById(R.id.enviarAbuso);
    }

    public void enviarAbuso(View view) {
        informer = String.valueOf(nameInformer.getText());
        message = String.valueOf(messageAbuso.getText());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateformat.format(c.getTime());

        Api service = Api.retrofit.create(Api.class);
        Call<Void> call = service.enviarIssue(new Issue(date, informer, message));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Abuse successfully submitted", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}