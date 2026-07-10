package com.cetmaster.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BottomNavigationView;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * UserDashboardActivity - Main user interface after login
 * Displays subjects, quick access links, and navigation
 */
public class UserDashboardActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private TextView tvUserName, tvUserEmail;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        
        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        userId = mAuth.getCurrentUser().getUid();
        
        // Initialize UI elements
        initializeViews();
        
        // Load user data
        loadUserData();
        
        // Set up bottom navigation
        setupBottomNavigation();
        
        // Set up subject click listeners
        setupSubjectListeners();
    }

    /**
     * Initialize all UI elements
     */
    private void initializeViews() {
        bottomNavigation = findViewById(R.id.dashboard_bottom_nav);
        tvUserName = findViewById(R.id.dashboard_user_name);
        tvUserEmail = findViewById(R.id.dashboard_user_email);
    }

    /**
     * Load user data from Firebase
     */
    private void loadUserData() {
        mDatabase.child("users").child(userId).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String name = task.getResult().child("name").getValue(String.class);
                        String email = task.getResult().child("email").getValue(String.class);
                        
                        if (name != null) {
                            tvUserName.setText("Welcome, " + name);
                        }
                        if (email != null) {
                            tvUserEmail.setText(email);
                        }
                    }
                });
    }

    /**
     * Setup bottom navigation view
     */
    private void setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                // Home tab
                return true;
            } else if (itemId == R.id.nav_bookmarks) {
                // Bookmarks tab
                return true;
            } else if (itemId == R.id.nav_downloads) {
                // Downloads tab
                return true;
            } else if (itemId == R.id.nav_profile) {
                // Profile tab
                navigateToProfile();
                return true;
            }
            return false;
        });
    }

    /**
     * Setup subject click listeners
     */
    private void setupSubjectListeners() {
        findViewById(R.id.subject_physics).setOnClickListener(v -> 
            navigateToNotes("Physics"));
        findViewById(R.id.subject_chemistry).setOnClickListener(v -> 
            navigateToNotes("Chemistry"));
        findViewById(R.id.subject_math).setOnClickListener(v -> 
            navigateToNotes("Mathematics"));
        findViewById(R.id.subject_biology).setOnClickListener(v -> 
            navigateToNotes("Biology"));
    }

    /**
     * Navigate to chapter notes activity
     */
    private void navigateToNotes(String subject) {
        // TODO: Implement navigation to ChapterNotesActivity with subject
    }

    /**
     * Navigate to profile activity
     */
    private void navigateToProfile() {
        // TODO: Implement navigation to ProfileActivity
    }
}
