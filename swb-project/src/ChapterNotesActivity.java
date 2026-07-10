package com.cetmaster.app;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * ChapterNotesActivity - Displays chapter-wise notes for a subject
 * Uses RecyclerView to show list of chapters
 */
public class ChapterNotesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageButton btnBack;
    private TextView tvTitle;
    private DatabaseReference mDatabase;
    private String selectedSubject;
    private List<Chapter> chapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_notes);
        
        // Initialize Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        
        // Get subject from intent
        selectedSubject = getIntent().getStringExtra("subject");
        
        // Initialize UI elements
        initializeViews();
        
        // Set title
        if (selectedSubject != null) {
            tvTitle.setText(selectedSubject + " Notes");
        }
        
        // Set up RecyclerView
        setupRecyclerView();
        
        // Load chapters
        loadChapters();
        
        // Set up back button
        btnBack.setOnClickListener(v -> finish());
    }

    /**
     * Initialize all UI elements
     */
    private void initializeViews() {
        recyclerView = findViewById(R.id.notes_recycler);
        btnBack = findViewById(R.id.notes_back);
        tvTitle = findViewById(R.id.notes_title);
        chapterList = new ArrayList<>();
    }

    /**
     * Setup RecyclerView with adapter
     */
    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Create and set ChapterAdapter
    }

    /**
     * Load chapters from Firebase
     */
    private void loadChapters() {
        if (selectedSubject == null) return;
        
        mDatabase.child("chapters").orderByChild("subject")
                .equalTo(selectedSubject)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        chapterList.clear();
                        // TODO: Parse chapters and update adapter
                    }
                });
    }

    /**
     * Chapter data model
     */
    public static class Chapter {
        public String id;
        public String subject;
        public String title;
        public String description;
        public String notesUrl;
        public long createdAt;

        public Chapter() {}

        public Chapter(String id, String subject, String title, String description, String notesUrl) {
            this.id = id;
            this.subject = subject;
            this.title = title;
            this.description = description;
            this.notesUrl = notesUrl;
            this.createdAt = System.currentTimeMillis();
        }
    }
}
