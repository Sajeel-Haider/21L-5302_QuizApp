package com.example.a21l_5302_quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizActivity extends AppCompatActivity {
    private TextView userNameTextView;

    // Views
    private TextView questionText;
    private RadioGroup radioGroup;
    private Button nextButton;
    private ImageButton previousButton;
    private int currentQuestionIndex = 0;
    private int score = 0;

    // Questions and answers
    private String[] questions = {
            "What is the output of 3 + 2 * 2?",
            "Which of the following is the correct syntax for a Java method that returns an integer?",
            "What is the time complexity of the binary search algorithm?",
            "Which keyword is used to define a class in Java?",
            "What is the default value of a boolean variable in Java?",
            "Which of the following is used to create a new object in Java?",
            "What is the correct way to declare an array in Java?",
            "What is the output of System.out.println(0 / 2)?",
            "What is the keyword used to inherit from a superclass in Java?",
            "Which of the following is not a valid Java identifier?"
    };

    private String[][] options = {
            {"5", "7", "4", "8"},
            {"void method(int a)", "int method()", "method(int a)", "int method(int a)"},
            {"O(n)", "O(log n)", "O(n^2)", "O(1)"},
            {"class", "object", "def", "function"},
            {"true", "false", "0", "null"},
            {"new", "create", "init", "instantiate"},
            {"int[] arr", "int arr[]", "int arr[];", "int[] arr[]"},
            {"0", "0.0", "Error", "null"},
            {"extends", "implements", "super", "inherits"},
            {"@var", "$variable", "1variable", "_variable"}
    };

    private String[] correctAnswers = {
            "7", // Q1: 3 + 2 * 2
            "int method(int a)", // Q2: Correct syntax
            "O(log n)", // Q3: Binary search time complexity
            "class", // Q4: Keyword to define a class
            "false", // Q5: Default value of boolean
            "new", // Q6: Used to create a new object
            "int[] arr", // Q7: Correct way to declare an array
            "0", // Q8: Output of System.out.println(0 / 2)
            "extends", // Q9: Keyword used for inheritance
            "1variable" // Q10: Invalid identifier
    };

    private int[] userAnswers = new int[10]; // Store user's selected answers


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
    }
    private void init(){
        questionText = findViewById(R.id.question_text);
        radioGroup = findViewById(R.id.radio_group);
        previousButton = findViewById(R.id.previous_button);
        nextButton = findViewById(R.id.next_button);
        // Get the user's name from the Intent Extra
        String userName = getIntent().getStringExtra("USER_NAME");
//        userNameTextView.setText("Welcome, " + userName);

        displayQuestion(currentQuestionIndex);

        // Handle Next Button click
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectedOption = findViewById(radioGroup.getCheckedRadioButtonId());
                if (selectedOption != null) {
                    String selectedAnswer = selectedOption.getText().toString();
                    checkAnswer(selectedAnswer);

                    // Move to the next question
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.length) {
                        displayQuestion(currentQuestionIndex);
                    } else {
                        finishQuiz(userName);
                    }
                }
            }
        });

        // Handle Previous Button click
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    displayQuestion(currentQuestionIndex);
                }
            }
        });
    }

   // Method to display a question and its options
    private void displayQuestion(int questionIndex) {
        questionText.setText(questions[questionIndex]);

        // Clear any previous selections
        radioGroup.clearCheck();

        // Set the radio buttons with the options for the current question
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton option = (RadioButton) radioGroup.getChildAt(i);
            option.setText(options[questionIndex][i]);
        }
    }

    // Method to check if the answer is correct and update score
    private void checkAnswer(String answer) {
        if (answer.equals(correctAnswers[currentQuestionIndex])) {
            score++;
        }
    }

    // Finish the quiz and show the score
    private void finishQuiz(String userName) {
        Intent intent = new Intent(QuizActivity.this, Scoreboard.class);
        intent.putExtra("USER_NAME", userName);
        intent.putExtra("USER_SCORE", score);
        startActivity(intent);
        score = 0;
    }
}