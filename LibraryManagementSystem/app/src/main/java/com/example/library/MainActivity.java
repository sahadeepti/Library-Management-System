package com.example.library;

import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.library.fragments.HomeFragment;
import com.example.library.fragments.BooksFragment;
import com.example.library.fragments.StudentsFragment;
import com.example.library.fragments.IssueFragment;
import com.example.library.fragments.ReportsFragment;
import com.example.library.ui.UI;

public class MainActivity extends FragmentActivity {
    private int fragmentContainerId;
    private LinearLayout nav;
    private TextView[] navItems;
    private int selected = 0;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(UI.NAVY);
        getWindow().setNavigationBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        buildLayout();
        if (savedInstanceState == null) showFragment(0);
    }

    private void buildLayout() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(UI.BG);

        FrameLayout container = new FrameLayout(this);
        fragmentContainerId = View.generateViewId();
        container.setId(fragmentContainerId);
        root.addView(container, new LinearLayout.LayoutParams(-1, 0, 1f));

        nav = new LinearLayout(this);
        nav.setOrientation(LinearLayout.HORIZONTAL);
        nav.setGravity(Gravity.CENTER);
        nav.setPadding(UI.dp(this, 4), UI.dp(this, 5), UI.dp(this, 4), UI.dp(this, 5));
        nav.setBackground(UI.rounded(Color.WHITE, UI.dp(this, 16), Color.rgb(225, 231, 240), UI.dp(this, 1)));
        nav.setElevation(UI.dp(this, 6));
        navItems = new TextView[5];
        String[] labels = {"⌂\nHome", "▤\nBooks", "♙\nStudents", "↔\nIssue", "▥\nReports"};
        for (int i=0;i<labels.length;i++) {
            final int index=i;
            TextView item = new TextView(this);
            item.setText(labels[i]);
            item.setGravity(Gravity.CENTER);
            item.setTextSize(10);
            item.setTypeface(null, Typeface.BOLD);
            item.setPadding(0, UI.dp(this, 5), 0, UI.dp(this, 5));
            navItems[i]=item;
            nav.addView(item, new LinearLayout.LayoutParams(0, UI.dp(this, 54), 1f));
            item.setOnClickListener(v -> showFragment(index));
        }
        LinearLayout.LayoutParams navLp = new LinearLayout.LayoutParams(-1, -2);
        navLp.setMargins(UI.dp(this, 8), UI.dp(this, 6), UI.dp(this, 8), UI.dp(this, 8));
        root.addView(nav, navLp);
        setContentView(root);
    }

    private void showFragment(int index) {
        Fragment fragment;
        switch (index) {
            case 1: fragment = new BooksFragment(); break;
            case 2: fragment = new StudentsFragment(); break;
            case 3: fragment = new IssueFragment(); break;
            case 4: fragment = new ReportsFragment(); break;
            default: fragment = new HomeFragment(); index = 0;
        }
        selected = index;
        for (int i=0;i<navItems.length;i++) {
            boolean active = i == selected;
            navItems[i].setTextColor(active ? UI.BLUE : UI.MUTED);
            navItems[i].setBackground(UI.rounded(active ? Color.rgb(232, 240, 255) : Color.TRANSPARENT,
                    UI.dp(this, 10), Color.TRANSPARENT, 0));
        }
        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainerId, fragment)
                .commit();
    }
}
