package com.se.randomdicer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private EditText usernameText;
    private ImageView doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        usernameText = findViewById(R.id.usernameText);
        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
                intent.putExtra("USERNAME", usernameText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
