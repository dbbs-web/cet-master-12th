package com.cetmaster.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * SplashActivity - Displays splash screen on app launch
 * Handles initial navigation based on user authentication status
 */
public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 3000; // 3 seconds
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        
        // Hide action bar for splash screen
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        // Delay navigation
        new Handler().postDelayed(this::navigateToNextScreen, SPLASH_DURATION);
    }

    /**
     * Navigate to appropriate screen based on authentication status
     */
    private void navigateToNextScreen() {
        if (mAuth.getCurrentUser() != null) {
            // User is already logged in
            // Check if user is admin or regular user
            checkUserRole();
        } else {
            // Navigate to login screen
            startActivity(new Intent(SplashActivity.this, UserLoginActivity.class));
            finish();
        }
    }

    /**
     * Check user role and navigate accordingly
     */
    private void checkUserRole() {
        // TODO: Implement role checking from Firebase Database
        // For now, navigate to user dashboard
        startActivity(new Intent(SplashActivity.this, UserDashboardActivity.class));
        finish();
    }
}
