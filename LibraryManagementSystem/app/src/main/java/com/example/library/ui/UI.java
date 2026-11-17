package com.example.library.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UI {
    public static final int BLUE = Color.rgb(30, 83, 155);
    public static final int NAVY = Color.rgb(20, 44, 85);
    public static final int BG = Color.rgb(244, 247, 252);
    public static final int TEXT = Color.rgb(35, 48, 66);
    public static final int MUTED = Color.rgb(105, 118, 135);

    public static int dp(Context a, float value) {
        return (int) (value * a.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static GradientDrawable rounded(int color, float radius, int strokeColor, int strokeWidth) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        if (strokeWidth > 0) d.setStroke(strokeWidth, strokeColor);
        return d;
    }

    public static LinearLayout page(Activity a, String title) {
        LinearLayout root = new LinearLayout(a);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(a, 20), dp(a, 20), dp(a, 20), dp(a, 24));
        root.setBackgroundColor(BG);

        TextView eyebrow = new TextView(a);
        eyebrow.setText("LIBRARY ADMINISTRATION");
        eyebrow.setTextSize(10);
        eyebrow.setLetterSpacing(0.08f);
        eyebrow.setTypeface(null, Typeface.BOLD);
        eyebrow.setTextColor(BLUE);
        eyebrow.setPadding(dp(a, 2), 0, 0, dp(a, 5));
        root.addView(eyebrow);

        TextView h = new TextView(a);
        h.setText(title);
        h.setTextSize(26);
        h.setTypeface(null, Typeface.BOLD);
        h.setTextColor(NAVY);
        h.setPadding(0, 0, 0, dp(a, 18));
        root.addView(h, new LinearLayout.LayoutParams(-1, -2));
        return root;
    }

    public static EditText input(Activity a, String hint) {
        EditText e = new EditText(a);
        e.setSingleLine(true);
        e.setHint(hint);
        e.setTextSize(15);
        e.setTextColor(TEXT);
        e.setHintTextColor(MUTED);
        e.setPadding(dp(a, 14), dp(a, 10), dp(a, 14), dp(a, 10));
        e.setBackground(rounded(Color.WHITE, dp(a, 12), Color.rgb(220, 228, 239), dp(a, 1)));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, dp(a, 52));
        lp.bottomMargin = dp(a, 10);
        e.setLayoutParams(lp);
        return e;
    }

    public static Button button(Activity a, String text) {
        Button b = new Button(a);
        b.setText(text);
        b.setAllCaps(false);
        b.setTextSize(15);
        b.setTypeface(null, Typeface.BOLD);
        b.setTextColor(Color.WHITE);
        b.setPadding(dp(a, 14), dp(a, 8), dp(a, 14), dp(a, 8));
        b.setBackground(rounded(BLUE, dp(a, 12), BLUE, 0));
        b.setMinHeight(dp(a, 48));
        b.setElevation(dp(a, 2));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, dp(a, 50));
        lp.topMargin = dp(a, 4);
        lp.bottomMargin = dp(a, 12);
        b.setLayoutParams(lp);
        return b;
    }

    public static void add(LinearLayout l, View v) {
        if (v.getLayoutParams() == null) l.addView(v, new LinearLayout.LayoutParams(-1, -2));
        else l.addView(v);
    }

    public static TextView label(Activity a, String s) {
        TextView t = new TextView(a);
        t.setText(s);
        t.setTextSize(14);
        t.setTextColor(TEXT);
        t.setPadding(dp(a, 14), dp(a, 12), dp(a, 14), dp(a, 12));
        t.setBackground(rounded(Color.WHITE, dp(a, 12), Color.rgb(230, 235, 243), dp(a, 1)));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.bottomMargin = dp(a, 8);
        t.setLayoutParams(lp);
        return t;
    }

    public static TextView section(Activity a, String s) {
        TextView t = new TextView(a);
        t.setText(s);
        t.setTextSize(16);
        t.setTypeface(null, Typeface.BOLD);
        t.setTextColor(NAVY);
        t.setPadding(dp(a, 2), dp(a, 14), dp(a, 2), dp(a, 8));
        return t;
    }

    public static void msg(Activity a, String s) {
        android.widget.Toast.makeText(a, s, android.widget.Toast.LENGTH_LONG).show();
    }
}
