package com.zzc.signImg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView inspection = findViewById(R.id.inspection_txt);
        TextView sign = findViewById(R.id.sign_txt);
        TextView grid = findViewById(R.id.grid_txt);
        TextView multiply = findViewById(R.id.multiply_txt);

        sign.setOnClickListener(v -> {
            Intent intent = new Intent(this,SignActivity.class);
            startActivity(intent);
        });

        inspection.setOnClickListener(v -> {
            Intent intent = new Intent(this,InspectionListActivity.class);
            startActivity(intent);
        });

        grid.setOnClickListener(v -> {
            Intent intent = new Intent(this,GridviewActivity.class);
            startActivity(intent);
        });

        multiply.setOnClickListener(v -> {
            Intent intent = new Intent(this,Multiplyctivity.class);
            startActivity(intent);
        });
    }
}