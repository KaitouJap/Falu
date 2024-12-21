package com.example.falu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText addressEditText;
    private Button submitButton;
    private ListView listView;
    private List<Villager> villagers;
    private VillagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String address = addressEditText.getText().toString();

                if(name.isEmpty() || age.isEmpty() || address.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int ageInt = Integer.parseInt(age);
                    if (ageInt < 0 || ageInt > 100) {
                        Toast.makeText(MainActivity.this, "Age must be between 0 and 100", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Age is not a number. Please enter a valid number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                villagers.add(new Villager(name, Integer.parseInt(age), address));
                adapter.notifyDataSetChanged();

                nameEditText.setText("");
                ageEditText.setText("");
                addressEditText.setText("");
            }
        });



    }

    public void init(){
        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        addressEditText = findViewById(R.id.addressEditText);
        submitButton = findViewById(R.id.submitButton);
        listView = findViewById(R.id.listView);
        villagers = new ArrayList<>();
        adapter = new VillagerAdapter(villagers, this);
        listView.setAdapter(adapter);
    }
}