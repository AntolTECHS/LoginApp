package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Create_Acc extends AppCompatActivity {

    private EditText username, password;
    private Button create;
    private LoginData ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        username = findViewById(R.id.et_new_username);
        password = findViewById(R.id.et_new_password);
        create = findViewById(R.id.create_button);

        ld = new LoginData(this);

        create.setOnClickListener(v -> {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                boolean added = ld.addUser(user, pass);
                if (added) {
                    Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
