package com.cetmaster.app;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ChapterViewHolder - ViewHolder for RecyclerView displaying chapters
 */
public class ChapterViewHolder extends RecyclerView.ViewHolder {
    public TextView chapterTitle;
    public TextView chapterDescription;
    public ImageView chapterIcon;
    public ChapterViewHolder listener;

    public ChapterViewHolder(android.view.View itemView) {
        super(itemView);
        chapterTitle = itemView.findViewById(R.id.chapter_title);
        chapterDescription = itemView.findViewById(R.id.chapter_description);
        chapterIcon = itemView.findViewById(R.id.chapter_icon);
    }

    /**
     * Bind chapter data to views
     */
    public void bind(ChapterNotesActivity.Chapter chapter, OnChapterClickListener listener) {
        chapterTitle.setText(chapter.title);
        chapterDescription.setText(chapter.description);
        
        itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onChapterClick(chapter);
            }
        });
    }

    /**
     * Interface for chapter click events
     */
    public interface OnChapterClickListener {
        void onChapterClick(ChapterNotesActivity.Chapter chapter);
    }
}
