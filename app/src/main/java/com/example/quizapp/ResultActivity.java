
// File: ResultActivity.java
package com.example.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 5);

        TextView scoreTextView = findViewById(R.id.score_text);
        scoreTextView.setText(String.format("Score: %d/%d", score, totalQuestions));

        PieChart pieChart = findViewById(R.id.result_pie_chart);
        setupPieChart(pieChart, score, totalQuestions);
    }

    private void setupPieChart(PieChart pieChart, int score, int totalQuestions) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(score, "Correct"));
        entries.add(new PieEntry(totalQuestions - score, "Incorrect"));

        PieDataSet dataSet = new PieDataSet(entries, "Quiz Results");
        dataSet.setColors(Color.GREEN, Color.RED);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(12f);

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Quiz Result");
        pieChart.animateY(1000);
        pieChart.invalidate();
    }
}

