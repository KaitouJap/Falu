package com.example.falu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView sexTextView;
    private TextView ageTextView;
    private TextView addressTextView;
    private Button backButton;

    private static final String[] sexes = {"Male", "Female", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Random random = new Random();
        String sex = sexes[random.nextInt(3)];
        int age = intent.getIntExtra("age", 0);
        String address = intent.getStringExtra("address");
        nameTextView.setText(name);
        sexTextView.setText(sex);
        ageTextView.setText(String.valueOf(age));
        addressTextView.setText(address);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void init(){
        nameTextView = findViewById(R.id.detailsActivity_nameTextView);
        sexTextView = findViewById(R.id.detailsActivity_sexTextView);
        ageTextView = findViewById(R.id.detailsActivity_ageTextView);
        addressTextView = findViewById(R.id.detailsActivity_addressTextView);
        backButton = findViewById(R.id.detailsActivity_backButton);
    }
}