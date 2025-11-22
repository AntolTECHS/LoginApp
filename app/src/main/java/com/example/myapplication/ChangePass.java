package com.example.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePass extends AppCompatActivity {

    private EditText username, newPassword;
    private Button change;
    private LoginData ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        username = findViewById(R.id.et_change_username);
        newPassword = findViewById(R.id.et_new_password_change);
        change = findViewById(R.id.change_button);

        ld = new LoginData(this);

        change.setOnClickListener(v -> changePassword());
    }

    private void changePassword() {
        String user = username.getText().toString().trim();
        String pass = newPassword.getText().toString().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = ld.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", pass);

        int updated = db.update("users", values, "username=?", new String[]{user});
        db.close();

        if (updated > 0) {
            Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show();
        }
    }
}
