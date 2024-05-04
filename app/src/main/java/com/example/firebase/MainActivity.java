package com.example.firebase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText FirstName, LastName, Mail;
    private TextView Message;
    private DatabaseReference mDataBase;
    private final String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void init(){
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);

        FirstName = findViewById(R.id.FirstName);
        LastName = findViewById(R.id.LastName);
        Mail = findViewById(R.id.Mail);

        Message = findViewById(R.id.Message);
    }

    public void onClickSave(View view) {
        String id = mDataBase.getKey();
        String firstname = FirstName.getText().toString();
        String lastName = LastName.getText().toString();
        String email = Mail.getText().toString();

        User newUser = new User(id, firstname, lastName, email);

        mDataBase.push().setValue(newUser);

        FirstName.setText("");
        LastName.setText("");
        Mail.setText("");

        showMessage();
    }

    private void showMessage() {
        Message.setText("Данные добавлены!");

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Message.setText("");
                    }
                }, 1000);
            }
}