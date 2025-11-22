package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout username, password;
    private Button login;
    private TextView create, changePass;
    private LoginData ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.login_button);
        create = findViewById(R.id.create);
        changePass = findViewById(R.id.change_password);

        ld = new LoginData(this);

        login.setOnClickListener(v -> loginUser());
        create.setOnClickListener(v -> startActivity(new Intent(this, Create_Acc.class)));
        changePass.setOnClickListener(v -> startActivity(new Intent(this, ChangePass.class)));
    }

    private void loginUser() {
        String name = username.getEditText().getText().toString().trim();
        String pass = password.getEditText().getText().toString().trim();

        if (name.isEmpty() || pass.isEmpty()) {
            showMessage("Error", "Empty fields are not allowed!");
            return;
        }

        if (ld.validate(name, pass)) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            showMessage("Error", "Invalid credentials!");
        }
    }

    private void showMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .show();
    }
}
