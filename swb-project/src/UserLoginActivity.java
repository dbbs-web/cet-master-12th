package com.cetmaster.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * UserLoginActivity - Handles user authentication via Firebase
 * Validates email and password before login
 */
public class UserLoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvSignUp, tvAdminLogin;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        
        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        
        // Initialize UI elements
        initializeViews();
        
        // Set up click listeners
        setupListeners();
    }

    /**
     * Initialize all UI elements
     */
    private void initializeViews() {
        etEmail = findViewById(R.id.login_email);
        etPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_btn);
        tvSignUp = findViewById(R.id.login_signup_link);
        tvAdminLogin = findViewById(R.id.login_admin_link);
    }

    /**
     * Setup click listeners for buttons
     */
    private void setupListeners() {
        btnLogin.setOnClickListener(v -> loginUser());
        tvSignUp.setOnClickListener(v -> navigateToRegister());
        tvAdminLogin.setOnClickListener(v -> navigateToAdminLogin());
    }

    /**
     * Validate email format
     */
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Validate password (minimum 6 characters)
     */
    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    /**
     * Login user with email and password
     */
    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        
        // Validate inputs
        if (!isValidEmail(email)) {
            Toast.makeText(this, R.string.error_invalid_email, Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (!isValidPassword(password)) {
            Toast.makeText(this, R.string.error_short_password, Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Firebase login
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Toast.makeText(UserLoginActivity.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                            navigateToDashboard();
                        }
                    } else {
                        Toast.makeText(UserLoginActivity.this, R.string.error_login_failed, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Navigate to register activity
     */
    private void navigateToRegister() {
        startActivity(new Intent(UserLoginActivity.this, UserRegisterActivity.class));
    }

    /**
     * Navigate to admin login activity
     */
    private void navigateToAdminLogin() {
        startActivity(new Intent(UserLoginActivity.this, AdminLoginActivity.class));
    }

    /**
     * Navigate to user dashboard
     */
    private void navigateToDashboard() {
        startActivity(new Intent(UserLoginActivity.this, UserDashboardActivity.class));
        finish();
    }
}
