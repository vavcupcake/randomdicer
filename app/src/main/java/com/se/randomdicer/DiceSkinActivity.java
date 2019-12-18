package com.se.randomdicer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DiceSkinActivity extends AppCompatActivity {

    private ImageView diceSkinDoneButton;
    private CardView blackSkin, pinkSkin;
    private int skin = 0;
    private int dice = 0;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_skin);
        username = getIntent().getStringExtra("USERNAME");
        dice = getIntent().getIntExtra("DICE", 6);

        blackSkin = findViewById(R.id.blackSkin);
        pinkSkin = findViewById(R.id.pinkSkin);
        diceSkinDoneButton = findViewById(R.id.diceSkinDoneButton);
        blackSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skin = 1;
                pinkSkin.setCardBackgroundColor(getResources().getColor(android.R.color.transparent));
                blackSkin.setCardBackgroundColor(getResources().getColor(android.R.color.white));
            }
        });
        pinkSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skin = 2;
                blackSkin.setCardBackgroundColor(getResources().getColor(android.R.color.transparent));
                pinkSkin.setCardBackgroundColor(getResources().getColor(android.R.color.white));
            }
        });
        diceSkinDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skin != 0) {
                    Intent intent = new Intent(DiceSkinActivity.this, WelcomeActivity.class);
                    intent.putExtra("SKIN", skin);
                    intent.putExtra("DICE", dice);
                    intent.putExtra("USERNAME", username);
                    startActivity(intent);
                }
            }
        });
    }
}
