package com.example.library.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.library.StudentActivity;
import com.example.library.ui.UI;

public class StudentsFragment extends Fragment {
    public StudentsFragment() { super(); }
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout root = new LinearLayout(requireContext());
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(UI.dp(requireContext(), 20), UI.dp(requireContext(), 24), UI.dp(requireContext(), 20), UI.dp(requireContext(), 20));
        root.setBackgroundColor(UI.BG);
        TextView eyebrow = new TextView(requireContext());
        eyebrow.setText("LIBRARY ADMINISTRATION"); eyebrow.setTextSize(10); eyebrow.setLetterSpacing(.08f);
        eyebrow.setTypeface(null, Typeface.BOLD); eyebrow.setTextColor(UI.BLUE);
        root.addView(eyebrow);
        TextView heading = new TextView(requireContext());
        heading.setText("Students"); heading.setTextSize(27); heading.setTypeface(null, Typeface.BOLD);
        heading.setTextColor(UI.NAVY); heading.setPadding(0, UI.dp(requireContext(), 5), 0, UI.dp(requireContext(), 18));
        root.addView(heading);
        LinearLayout card = new LinearLayout(requireContext()); card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(UI.dp(requireContext(), 22), UI.dp(requireContext(), 24), UI.dp(requireContext(), 22), UI.dp(requireContext(), 22));
        card.setBackground(UI.rounded(Color.WHITE, UI.dp(requireContext(), 20), Color.rgb(228,234,243), UI.dp(requireContext(), 1)));
        card.setElevation(UI.dp(requireContext(), 3));
        TextView icon = new TextView(requireContext()); icon.setText("🎓"); icon.setTextSize(32); icon.setGravity(Gravity.CENTER);
        card.addView(icon);
        TextView title = new TextView(requireContext()); title.setText("Students"); title.setTextSize(21);
        title.setTypeface(null, Typeface.BOLD); title.setTextColor(UI.NAVY); title.setGravity(Gravity.CENTER);
        title.setPadding(0, UI.dp(requireContext(), 12), 0, UI.dp(requireContext(), 8)); card.addView(title);
        TextView description = new TextView(requireContext()); description.setText("Register readers and maintain student details for borrowing."); description.setTextSize(15);
        description.setTextColor(UI.MUTED); description.setGravity(Gravity.CENTER); description.setPadding(0,0,0,UI.dp(requireContext(), 18)); card.addView(description);
        Button action = new Button(requireContext()); action.setText("Manage Students"); action.setAllCaps(false);
        action.setTextColor(Color.WHITE); action.setTypeface(null, Typeface.BOLD); action.setBackground(UI.rounded(UI.BLUE, UI.dp(requireContext(), 12), UI.BLUE, 0));
        action.setMinHeight(UI.dp(requireContext(), 48));
        card.addView(action, new LinearLayout.LayoutParams(-1, UI.dp(requireContext(), 50)));
        action.setOnClickListener(v -> startActivity(new Intent(requireContext(), StudentActivity.class)));
        root.addView(card);
        return root;
    }
}
