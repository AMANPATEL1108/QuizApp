
// File: QuizActivity.java
package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.question_text);
        optionsRadioGroup = findViewById(R.id.options_radio_group);
        nextButton = findViewById(R.id.next_button);

        initializeQuestions();
        loadQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                currentQuestionIndex++;

                if (currentQuestionIndex < questions.size()) {
                    loadQuestion();
                } else {
                    Intent resultIntent = new Intent(QuizActivity.this, ResultActivity.class);
                    resultIntent.putExtra("SCORE", score);
                    resultIntent.putExtra("TOTAL_QUESTIONS", questions.size());
                    startActivity(resultIntent);
                    finish();
                }
            }
        });
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question(
                "What is the capital of France?",
                new String[]{"London", "Berlin", "Paris", "Madrid"},
                2
        ));
        questions.add(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{"Venus", "Mars", "Jupiter", "Saturn"},
                1
        ));
        questions.add(new Question(
                "What is 2 + 2?",
                new String[]{"3", "4", "5", "6"},
                1
        ));
        questions.add(new Question(
                "Who painted the Mona Lisa?",
                new String[]{"Van Gogh", "Picasso", "Da Vinci", "Rembrandt"},
                2
        ));
        questions.add(new Question(
                "What is the largest mammal?",
                new String[]{"Elephant", "Blue Whale", "Giraffe", "Hippopotamus"},
                1
        ));
    }

    private void loadQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionTextView.setText(currentQuestion.getQuestionText());

        optionsRadioGroup.removeAllViews();
        for (int i = 0; i < currentQuestion.getOptions().length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(currentQuestion.getOptions()[i]);
            radioButton.setId(View.generateViewId());
            optionsRadioGroup.addView(radioButton);
        }
        optionsRadioGroup.clearCheck();
    }

    private void checkAnswer() {
        int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedOptionId != -1) {
            int selectedIndex = optionsRadioGroup.indexOfChild(findViewById(selectedOptionId));
            Question currentQuestion = questions.get(currentQuestionIndex);

            if (selectedIndex == currentQuestion.getCorrectOptionIndex()) {
                score++;
            }
        }
    }
}
