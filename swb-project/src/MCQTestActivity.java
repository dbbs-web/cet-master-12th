package com.cetmaster.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * MCQTestActivity - Displays MCQ tests with timer
 * Handles answer selection, scoring, and result calculation
 */
public class MCQTestActivity extends AppCompatActivity {
    private TextView tvQuestion, tvTimer, tvProgress;
    private RadioGroup rgOptions;
    private Button btnPrevious, btnNext, btnSubmit;
    private DatabaseReference mDatabase;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private Timer timer;
    private int timeRemaining = 600; // 10 minutes
    private String testId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_test);
        
        // Initialize Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        
        // Get test ID from intent
        testId = getIntent().getStringExtra("testId");
        
        // Initialize UI elements
        initializeViews();
        
        // Initialize question list
        questionList = new ArrayList<>();
        
        // Load questions
        loadQuestions();
        
        // Set up click listeners
        setupListeners();
        
        // Start timer
        startTimer();
    }

    /**
     * Initialize all UI elements
     */
    private void initializeViews() {
        tvQuestion = findViewById(R.id.mcq_question);
        tvTimer = findViewById(R.id.mcq_timer);
        tvProgress = findViewById(R.id.mcq_progress);
        rgOptions = findViewById(R.id.mcq_options_group);
        btnPrevious = findViewById(R.id.mcq_prev_btn);
        btnNext = findViewById(R.id.mcq_next_btn);
        btnSubmit = findViewById(R.id.mcq_submit_btn);
    }

    /**
     * Setup click listeners
     */
    private void setupListeners() {
        btnPrevious.setOnClickListener(v -> previousQuestion());
        btnNext.setOnClickListener(v -> nextQuestion());
        btnSubmit.setOnClickListener(v -> submitTest());
    }

    /**
     * Load questions from Firebase
     */
    private void loadQuestions() {
        if (testId == null) return;
        
        mDatabase.child("mcqs").child(testId).child("questions")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        questionList.clear();
                        // TODO: Parse questions from Firebase
                        if (!questionList.isEmpty()) {
                            displayQuestion(0);
                        }
                    }
                });
    }

    /**
     * Display question at specified index
     */
    private void displayQuestion(int index) {
        if (index >= 0 && index < questionList.size()) {
            currentQuestionIndex = index;
            Question question = questionList.get(index);
            
            tvQuestion.setText(question.text);
            tvProgress.setText("Q " + (index + 1) + "/" + questionList.size());
            
            // Clear previous options
            rgOptions.removeAllViews();
            
            // Add options
            for (int i = 0; i < question.options.size(); i++) {
                RadioButton rb = new RadioButton(this);
                rb.setText((char)('A' + i) + ") " + question.options.get(i));
                rb.setTag(i);
                rgOptions.addView(rb);
            }
            
            // Update button visibility
            btnPrevious.setEnabled(index > 0);
            btnNext.setVisibility(index < questionList.size() - 1 ? android.view.View.VISIBLE : android.view.View.GONE);
            btnSubmit.setVisibility(index == questionList.size() - 1 ? android.view.View.VISIBLE : android.view.View.GONE);
        }
    }

    /**
     * Go to previous question
     */
    private void previousQuestion() {
        if (currentQuestionIndex > 0) {
            displayQuestion(currentQuestionIndex - 1);
        }
    }

    /**
     * Go to next question
     */
    private void nextQuestion() {
        if (currentQuestionIndex < questionList.size() - 1) {
            // Save current answer
            int selectedId = rgOptions.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton rb = findViewById(selectedId);
                int selectedOption = (int) rb.getTag();
                questionList.get(currentQuestionIndex).userAnswer = selectedOption;
            }
            displayQuestion(currentQuestionIndex + 1);
        }
    }

    /**
     * Start countdown timer
     */
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeRemaining--;
                runOnUiThread(() -> {
                    int minutes = timeRemaining / 60;
                    int seconds = timeRemaining % 60;
                    tvTimer.setText(String.format("%02d:%02d", minutes, seconds));
                    
                    if (timeRemaining <= 0) {
                        timer.cancel();
                        submitTest();
                    }
                });
            }
        }, 0, 1000);
    }

    /**
     * Submit test and calculate score
     */
    private void submitTest() {
        if (timer != null) {
            timer.cancel();
        }
        
        // Save last answer
        int selectedId = rgOptions.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton rb = findViewById(selectedId);
            int selectedOption = (int) rb.getTag();
            questionList.get(currentQuestionIndex).userAnswer = selectedOption;
        }
        
        // Calculate score
        score = 0;
        for (Question q : questionList) {
            if (q.userAnswer == q.correctAnswer) {
                score++;
            }
        }
        
        // Save result to Firebase
        saveTestResult();
        
        // Show result
        Toast.makeText(this, "Score: " + score + "/" + questionList.size(), Toast.LENGTH_LONG).show();
        // TODO: Navigate to result screen
    }

    /**
     * Save test result to Firebase
     */
    private void saveTestResult() {
        // TODO: Implement result saving
    }

    /**
     * Question data model
     */
    public static class Question {
        public String id;
        public String text;
        public List<String> options;
        public int correctAnswer;
        public int userAnswer = -1;
        public String explanation;

        public Question() {
            options = new ArrayList<>();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
