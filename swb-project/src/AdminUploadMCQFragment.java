package com.cetmaster.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/**
 * AdminUploadMCQFragment - Fragment for uploading MCQ tests
 */
public class AdminUploadMCQFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_upload_mcq, container, false);
    }
}
