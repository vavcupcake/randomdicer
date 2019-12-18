package com.se.randomdicer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private ImageView dice6, dice8, dice12;
    private ImageView diceSkinButton;
    private int dice = 0;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        username = getIntent().getStringExtra("USERNAME");

        usernameTextView = findViewById(R.id.usernameTextView);
        dice6 = findViewById(R.id.diceButtonD6);
        dice8 = findViewById(R.id.diceButtonD8);
        dice12 = findViewById(R.id.diceButtonD12);
        diceSkinButton = findViewById(R.id.diceSkinButton);
        usernameTextView.setText(username + " !");

        dice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dice = 6;
            }
        });
        dice8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dice = 8;
            }
        });
        dice12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dice = 12;
            }
        });
        diceSkinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dice != 0) {
                    Intent intent = new Intent(WelcomeActivity.this, DiceSkinActivity.class);
                    intent.putExtra("DICE", dice);
                    intent.putExtra("USERNAME", username);
                    startActivity(intent);
                }
            }
        });
    }
}
