package com.example.a21l_5302_quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private Button startQuizButton;
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
    }

    private void init(){
        nameEditText = findViewById(R.id.name_edit_text);
        startQuizButton = findViewById(R.id.start_quiz_button);
        startQuizButton.setOnClickListener(v -> {
            String userName = nameEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("USER_NAME", userName); // Pass the name using Intent
            startActivity(intent);
        });
    }
}