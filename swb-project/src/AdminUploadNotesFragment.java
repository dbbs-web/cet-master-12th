package com.cetmaster.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

/**
 * AdminUploadNotesFragment - Fragment for uploading chapter notes
 */
public class AdminUploadNotesFragment extends Fragment {
    private EditText etChapterTitle, etChapterDescription;
    private Spinner spinnerSubject;
    private Button btnUploadNotes;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_upload_notes, container, false);
        
        // Initialize Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        
        // Initialize UI elements
        etChapterTitle = view.findViewById(R.id.et_chapter_title);
        etChapterDescription = view.findViewById(R.id.et_chapter_description);
        spinnerSubject = view.findViewById(R.id.spinner_subject);
        btnUploadNotes = view.findViewById(R.id.btn_upload_notes);
        
        // Setup click listener
        btnUploadNotes.setOnClickListener(v -> uploadNotes());
        
        return view;
    }

    /**
     * Upload notes to Firebase
     */
    private void uploadNotes() {
        String title = etChapterTitle.getText().toString().trim();
        String description = etChapterDescription.getText().toString().trim();
        String subject = spinnerSubject.getSelectedItem().toString();
        
        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Create chapter data
        Map<String, Object> chapterData = new HashMap<>();
        chapterData.put("subject", subject);
        chapterData.put("title", title);
        chapterData.put("description", description);
        chapterData.put("createdAt", System.currentTimeMillis());
        
        // Save to Firebase
        String chapterId = mDatabase.child("chapters").push().getKey();
        if (chapterId != null) {
            mDatabase.child("chapters").child(chapterId).setValue(chapterData)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Notes uploaded successfully", Toast.LENGTH_SHORT).show();
                            etChapterTitle.setText("");
                            etChapterDescription.setText("");
                        } else {
                            Toast.makeText(getContext(), "Error uploading notes", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
