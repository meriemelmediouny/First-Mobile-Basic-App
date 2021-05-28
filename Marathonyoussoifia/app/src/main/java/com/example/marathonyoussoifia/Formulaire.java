package com.example.marathonyoussoifia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Formulaire extends AppCompatActivity {
     private Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openremerciemnt();
            }
        });
    }
    public void openremerciemnt(){
        Intent intent = new Intent(this, remerciement.class);
        startActivity(intent);
    }
}