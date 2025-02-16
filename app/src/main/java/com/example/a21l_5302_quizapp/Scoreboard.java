package com.example.a21l_5302_quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Scoreboard extends AppCompatActivity {
    private TextView userNameTextView, userScoreTextView;
    private Button shareButton;
    private String userName;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scoreboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
    }
    private void init(){
        // Initialize Views
        userNameTextView = findViewById(R.id.user_name);
        userScoreTextView = findViewById(R.id.user_score);
        shareButton = findViewById(R.id.share_button);
        ImageButton previousButton = findViewById(R.id.previous_button);

        // Get the user's name and score from the Intent Extras
        userName = getIntent().getStringExtra("USER_NAME");
        score = getIntent().getIntExtra("USER_SCORE", 0);

        // Set the username and score
        userNameTextView.setText(userName);
        userScoreTextView.setText(score + "/10");

        // Previous button action: Go back to the Main Activity (QuizActivity)
        previousButton.setOnClickListener(v -> {
            Intent intent = new Intent(Scoreboard.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the ScoreboardActivity
        });

        // Share button action: Share the score
        shareButton.setOnClickListener(v -> shareScore());
    }
    private void shareScore() {
        String shareText = "I scored " + score + "/10 in the quiz on QuizKhelo!";

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

        // Start the sharing intent
        try {
            startActivity(Intent.createChooser(shareIntent, "Share your score"));
        } catch (Exception e) {
            Toast.makeText(this, "Error sharing the score", Toast.LENGTH_SHORT).show();
        }
    }
}