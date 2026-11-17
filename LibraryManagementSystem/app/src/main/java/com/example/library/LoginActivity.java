package com.example.library;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;

import com.example.library.dao.impl.AdminDAOImpl;
import com.example.library.ui.UI;

public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().setStatusBarColor(Color.rgb(18, 43, 82));
        getWindow().setNavigationBarColor(Color.rgb(18, 43, 82));

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(UI.BG);

        LinearLayout outer = new LinearLayout(this);
        outer.setOrientation(LinearLayout.VERTICAL);
        outer.setGravity(Gravity.CENTER);
        outer.setPadding(UI.dp(this, 24), UI.dp(this, 28), UI.dp(this, 24), UI.dp(this, 28));
        scroll.addView(outer);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(UI.dp(this, 24), UI.dp(this, 26), UI.dp(this, 24), UI.dp(this, 24));
        GradientDrawable cardBg = UI.rounded(Color.WHITE, UI.dp(this, 22), Color.rgb(229, 235, 244), UI.dp(this, 1));
        card.setBackground(cardBg);
        card.setElevation(UI.dp(this, 5));
        outer.addView(card, new LinearLayout.LayoutParams(-1, -2));

        TextView icon = new TextView(this);
        icon.setText("📚");
        icon.setTextSize(34);
        icon.setGravity(Gravity.CENTER);
        card.addView(icon, new LinearLayout.LayoutParams(-1, -2));

        TextView title = new TextView(this);
        title.setText("Library Management");
        title.setTextSize(25);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(UI.NAVY);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, UI.dp(this, 8), 0, UI.dp(this, 4));
        card.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("SYSTEM ADMINISTRATOR");
        subtitle.setTextSize(11);
        subtitle.setLetterSpacing(0.12f);
        subtitle.setTypeface(null, Typeface.BOLD);
        subtitle.setTextColor(UI.BLUE);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 0, 0, UI.dp(this, 24));
        card.addView(subtitle);

        TextView welcome = new TextView(this);
        welcome.setText("Welcome back");
        welcome.setTextSize(18);
        welcome.setTypeface(null, Typeface.BOLD);
        welcome.setTextColor(UI.TEXT);
        card.addView(welcome);

        TextView hint = new TextView(this);
        hint.setText("Sign in to manage your library.");
        hint.setTextSize(13);
        hint.setTextColor(UI.MUTED);
        hint.setPadding(0, UI.dp(this, 4), 0, UI.dp(this, 16));
        card.addView(hint);

        EditText user = UI.input(this, "Username");
        EditText pass = UI.input(this, "Password");
        pass.setInputType(129);
        card.addView(user);
        card.addView(pass);

        Button login = UI.button(this, "Sign in");
        LinearLayout.LayoutParams loginLp = (LinearLayout.LayoutParams) login.getLayoutParams();
        loginLp.topMargin = UI.dp(this, 8);
        card.addView(login, loginLp);

        TextView demo = new TextView(this);
        demo.setText("Demo credentials: admin  /  admin123");
        demo.setTextSize(12);
        demo.setTextColor(UI.MUTED);
        demo.setGravity(Gravity.CENTER);
        demo.setPadding(0, UI.dp(this, 12), 0, 0);
        card.addView(demo);

        TextView footer = new TextView(this);
        footer.setText("Library Management System  •  Admin Portal");
        footer.setTextSize(11);
        footer.setTextColor(UI.MUTED);
        footer.setGravity(Gravity.CENTER);
        footer.setPadding(0, UI.dp(this, 20), 0, 0);
        outer.addView(footer);

        login.setOnClickListener(v -> {
            if (new AdminDAOImpl(this).login(user.getText().toString(), pass.getText().toString())) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                UI.msg(this, "Invalid username or password");
            }
        });
        setContentView(scroll);
    }
}
