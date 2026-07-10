package com.cetmaster.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * ProfileActivity - Displays user profile and settings
 * Allows users to modify preferences, view stats, and logout
 */
public class ProfileActivity extends AppCompatActivity {
    private TextView tvName, tvEmail, tvTestsTaken, tvAccuracy, tvNotesRead;
    private Switch swDarkMode, swNotifications;
    private Button btnLogout;
    private ImageButton ibBack;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        
        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        userId = mAuth.getCurrentUser().getUid();
        
        // Initialize UI elements
        initializeViews();
        
        // Load user profile
        loadProfile();
        
        // Load statistics
        loadStatistics();
        
        // Setup listeners
        setupListeners();
    }

    /**
     * Initialize all UI elements
     */
    private void initializeViews() {
        tvName = findViewById(R.id.profile_name);
        tvEmail = findViewById(R.id.profile_email);
        tvTestsTaken = findViewById(R.id.profile_tests_taken);
        tvAccuracy = findViewById(R.id.profile_accuracy);
        tvNotesRead = findViewById(R.id.profile_notes_read);
        swDarkMode = findViewById(R.id.profile_dark_mode_toggle);
        swNotifications = findViewById(R.id.profile_notifications_toggle);
        btnLogout = findViewById(R.id.profile_logout);
        ibBack = findViewById(R.id.profile_back);
    }

    /**
     * Load user profile data
     */
    private void loadProfile() {
        mDatabase.child("users").child(userId).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String name = task.getResult().child("name").getValue(String.class);
                        String email = task.getResult().child("email").getValue(String.class);
                        
                        if (name != null) {
                            tvName.setText(name);
                        }
                        if (email != null) {
                            tvEmail.setText(email);
                        }
                    }
                });
    }

    /**
     * Load user statistics from Firebase
     */
    private void loadStatistics() {
        mDatabase.child("statistics").child(userId).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Long testsTaken = task.getResult().child("testsTaken").getValue(Long.class);
                        Double accuracy = task.getResult().child("accuracy").getValue(Double.class);
                        Long notesRead = task.getResult().child("notesRead").getValue(Long.class);
                        
                        if (testsTaken != null) {
                            tvTestsTaken.setText(String.valueOf(testsTaken));
                        }
                        if (accuracy != null) {
                            tvAccuracy.setText(String.format("%.1f%%", accuracy));
                        }
                        if (notesRead != null) {
                            tvNotesRead.setText(String.valueOf(notesRead));
                        }
                    }
                });
    }

    /**
     * Setup click and switch listeners
     */
    private void setupListeners() {
        // Dark mode toggle
        swDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            // Save preference
            saveUserPreference("darkMode", isChecked);
        });
        
        // Notifications toggle
        swNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save preference
            saveUserPreference("notifications", isChecked);
        });
        
        // Logout button
        btnLogout.setOnClickListener(v -> logout());
        
        // Back button
        ibBack.setOnClickListener(v -> finish());
    }

    /**
     * Save user preference to Firebase
     */
    private void saveUserPreference(String key, Object value) {
        mDatabase.child("users").child(userId).child("preferences").child(key).setValue(value);
    }

    /**
     * Logout user
     */
    private void logout() {
        mAuth.signOut();
        // TODO: Navigate to login screen
        finish();
    }
}
