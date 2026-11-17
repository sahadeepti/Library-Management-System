package com.example.library.fragments;

import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.library.dao.impl.BookDAOImpl;
import com.example.library.dao.impl.IssueDAOImpl;
import com.example.library.dao.impl.StudentDAOImpl;
import com.example.library.model.Book;
import com.example.library.ui.UI;

public class HomeFragment extends Fragment {
    public HomeFragment() { super(); }
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ScrollView scroll = new ScrollView(requireContext());
        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(UI.dp(requireContext(), 20), UI.dp(requireContext(), 24), UI.dp(requireContext(), 20), UI.dp(requireContext(), 20));
        layout.setBackgroundColor(UI.BG);
        scroll.addView(layout);

        TextView eyebrow = new TextView(requireContext()); eyebrow.setText("ADMIN DASHBOARD");
        eyebrow.setTextSize(10); eyebrow.setLetterSpacing(.10f); eyebrow.setTypeface(null, Typeface.BOLD); eyebrow.setTextColor(UI.BLUE);
        layout.addView(eyebrow);
        TextView title = new TextView(requireContext()); title.setText("Welcome back!"); title.setTextSize(28);
        title.setTypeface(null, Typeface.BOLD); title.setTextColor(UI.NAVY); title.setPadding(0, UI.dp(requireContext(), 4), 0, UI.dp(requireContext(), 4));
        layout.addView(title);
        TextView subtitle = new TextView(requireContext()); subtitle.setText("Here is your library overview."); subtitle.setTextSize(14); subtitle.setTextColor(UI.MUTED);
        subtitle.setPadding(0, 0, 0, UI.dp(requireContext(), 20)); layout.addView(subtitle);

        BookDAOImpl bookDao = new BookDAOImpl(requireContext());
        IssueDAOImpl issueDao = new IssueDAOImpl(requireContext());
        StudentDAOImpl studentDao = new StudentDAOImpl(requireContext());
        int total = 0, available = 0;
        for (Book book : bookDao.getAllBooks()) { total += book.getTotalQuantity(); available += bookDao.getAvailableQuantity(book.getBookId()); }
        int issued = issueDao.getActiveIssues().size();
        int students = studentDao.getAllStudents().size();

        addStat(layout, "📚", "Total book copies", String.valueOf(total), Color.rgb(232,240,255));
        addStat(layout, "✓", "Available copies", String.valueOf(available), Color.rgb(230,247,239));
        addStat(layout, "↗", "Currently issued", String.valueOf(issued), Color.rgb(255,243,226));
        addStat(layout, "♙", "Registered students", String.valueOf(students), Color.rgb(244,235,255));

        TextView tip = new TextView(requireContext());
        tip.setText("Quick tip\nUse the navigation bar below to manage books, students, issue/return transactions, and reports.");
        tip.setTextSize(14); tip.setTextColor(UI.TEXT); tip.setPadding(UI.dp(requireContext(), 16), UI.dp(requireContext(), 16), UI.dp(requireContext(), 16), UI.dp(requireContext(), 16));
        tip.setBackground(UI.rounded(Color.WHITE, UI.dp(requireContext(), 14), Color.rgb(230,235,243), UI.dp(requireContext(), 1)));
        LinearLayout.LayoutParams tipLp = new LinearLayout.LayoutParams(-1, -2); tipLp.topMargin = UI.dp(requireContext(), 8); layout.addView(tip, tipLp);
        return scroll;
    }
    private void addStat(LinearLayout parent, String icon, String label, String value, int tint) {
        LinearLayout card = new LinearLayout(requireContext()); card.setOrientation(LinearLayout.HORIZONTAL); card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(UI.dp(requireContext(), 16), UI.dp(requireContext(), 14), UI.dp(requireContext(), 16), UI.dp(requireContext(), 14));
        card.setBackground(UI.rounded(Color.WHITE, UI.dp(requireContext(), 15), Color.rgb(230,235,243), UI.dp(requireContext(), 1)));
        TextView symbol = new TextView(requireContext()); symbol.setText(icon); symbol.setTextSize(22); symbol.setGravity(Gravity.CENTER);
        symbol.setBackground(UI.rounded(tint, UI.dp(requireContext(), 12), tint, 0));
        card.addView(symbol, new LinearLayout.LayoutParams(UI.dp(requireContext(), 48), UI.dp(requireContext(), 48)));
        LinearLayout textCol = new LinearLayout(requireContext()); textCol.setOrientation(LinearLayout.VERTICAL); textCol.setPadding(UI.dp(requireContext(), 14), 0, 0, 0);
        TextView lbl = new TextView(requireContext()); lbl.setText(label); lbl.setTextSize(13); lbl.setTextColor(UI.MUTED);
        TextView val = new TextView(requireContext()); val.setText(value); val.setTextSize(24); val.setTypeface(null, Typeface.BOLD); val.setTextColor(UI.NAVY);
        textCol.addView(lbl); textCol.addView(val); card.addView(textCol);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2); lp.bottomMargin = UI.dp(requireContext(), 10); parent.addView(card, lp);
    }
}
