package com.se.randomdicer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class RollActivity extends AppCompatActivity {

    private ImageView rollButton;
    private LinearLayout layer;
    private TextView downNumberResult, upNumberResult;
    private ImageView diceResult;
    private int []diceImages;
    private int dice;
    private int number;
    private Random rand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);

        rand = new Random();
        dice = getIntent().getIntExtra("DICE", 6);
        if (dice == 6) {
            diceImages = new int[]{
                    R.drawable.random_dice_6_1,
                    R.drawable.random_dice_6_2,
                    R.drawable.random_dice_6_3,
                    R.drawable.random_dice_6_4,
                    R.drawable.random_dice_6_5,
                    R.drawable.random_dice_6_6,
            };
        }
        rollButton = findViewById(R.id.rollButton);
        layer = findViewById(R.id.layer);
        diceResult = findViewById(R.id.diceResult);
        upNumberResult = findViewById(R.id.upNumberResult);
        downNumberResult = findViewById(R.id.downNumberResult);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = rand.nextInt(dice) + 1;
                layer.setVisibility(View.GONE);
                upNumberResult.setText(number + "");
                downNumberResult.setText(number + "");
                diceResult.setImageResource(diceImages[number-1]);
            }
        });
    }
}
