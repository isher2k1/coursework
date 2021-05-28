package com.isher.skytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class HeroAddActivity extends AppCompatActivity {

    private EditText nameHero, easyChoose, medChoose, hardChoose;
    private Button startButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hero_add);

        nameHero = findViewById(R.id.heroName);
        easyChoose = findViewById(R.id.easyChoose);
        medChoose = findViewById(R.id.mediumChoose);
        hardChoose = findViewById(R.id.hardChoose);
        startButt = findViewById(R.id.startButton);

        startButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeroAddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}