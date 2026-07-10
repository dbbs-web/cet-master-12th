package com.cetmaster.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

/**
 * UserRegisterActivity - Handles new user registration
 * Validates form and creates user account via Firebase
 */
public class UserRegisterActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPhone, etPassword, etConfirmPassword;
    private Button btnRegister;
    private ImageButton ibBack;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        
        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        
        // Initialize UI elements
        initializeViews();
        
        // Set up click listeners
        setupListeners();
    }

    /**
     * Initialize all UI elements
     */
    private void initializeViews() {
        etName = findViewById(R.id.register_name);
        etEmail = findViewById(R.id.register_email);
        etPhone = findViewById(R.id.register_phone);
        etPassword = findViewById(R.id.register_password);
        etConfirmPassword = findViewById(R.id.register_confirm_password);
        btnRegister = findViewById(R.id.register_btn);
        ibBack = findViewById(R.id.register_back);
    }

    /**
     * Setup click listeners
     */
    private void setupListeners() {
        btnRegister.setOnClickListener(v -> registerUser());
        ibBack.setOnClickListener(v -> finish());
    }

    /**
     * Validate all form fields
     */
    private boolean validateForm() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }
        
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, R.string.error_invalid_email, Toast.LENGTH_SHORT).show();
            return false;
        }
        
        if (TextUtils.isEmpty(phone) || phone.length() < 10) {
            Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            Toast.makeText(this, R.string.error_short_password, Toast.LENGTH_SHORT).show();
            return false;
        }
        
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, R.string.error_passwords_not_match, Toast.LENGTH_SHORT).show();
            return false;
        }
        
        return true;
    }

    /**
     * Register new user
     */
    private void registerUser() {
        if (!validateForm()) {
            return;
        }
        
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        
        // Create user in Firebase Auth
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        
                        // Save user data to Realtime Database
                        Map<String, Object> userData = new HashMap<>();
                        userData.put("name", name);
                        userData.put("email", email);
                        userData.put("phone", phone);
                        userData.put("role", "user");
                        userData.put("createdAt", System.currentTimeMillis());
                        
                        mDatabase.child(userId).setValue(userData)
                                .addOnCompleteListener(dbTask -> {
                                    if (dbTask.isSuccessful()) {
                                        Toast.makeText(UserRegisterActivity.this, R.string.success_register, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(UserRegisterActivity.this, UserDashboardActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(UserRegisterActivity.this, "Error saving user data", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(UserRegisterActivity.this, R.string.error_register_failed, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
