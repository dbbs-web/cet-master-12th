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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * AdminLoginActivity - Handles admin authentication
 * Verifies admin credentials against Firebase database
 */
public class AdminLoginActivity extends AppCompatActivity {
    private EditText etAdminEmail, etAdminPassword;
    private Button btnAdminLogin;
    private TextView tvUserLogin;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private static final String ADMIN_ROLE = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        
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
        etAdminEmail = findViewById(R.id.admin_login_email);
        etAdminPassword = findViewById(R.id.admin_login_password);
        btnAdminLogin = findViewById(R.id.admin_login_btn);
        tvUserLogin = findViewById(R.id.admin_login_user_link);
    }

    /**
     * Setup click listeners
     */
    private void setupListeners() {
        btnAdminLogin.setOnClickListener(v -> loginAdmin());
        tvUserLogin.setOnClickListener(v -> finish());
    }

    /**
     * Validate admin email
     */
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Validate admin password
     */
    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    /**
     * Login admin user
     */
    private void loginAdmin() {
        String email = etAdminEmail.getText().toString().trim();
        String password = etAdminPassword.getText().toString().trim();
        
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
                        // Verify admin role
                        String userId = mAuth.getCurrentUser().getUid();
                        verifyAdminRole(userId);
                    } else {
                        Toast.makeText(AdminLoginActivity.this, R.string.error_login_failed, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Verify if user has admin role
     */
    private void verifyAdminRole(String userId) {
        mDatabase.child("users").child(userId).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Object roleObj = task.getResult().child("role").getValue();
                        if (roleObj != null && roleObj.toString().equals(ADMIN_ROLE)) {
                            Toast.makeText(AdminLoginActivity.this, "Admin Login Successful", Toast.LENGTH_SHORT).show();
                            navigateToAdminPanel();
                        } else {
                            mAuth.signOut();
                            Toast.makeText(AdminLoginActivity.this, "You are not authorized as admin", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AdminLoginActivity.this, "Error verifying admin status", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Navigate to admin panel
     */
    private void navigateToAdminPanel() {
        startActivity(new Intent(AdminLoginActivity.this, AdminPanelActivity.class));
        finish();
    }
}
