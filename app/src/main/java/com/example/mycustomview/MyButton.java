package com.example.mycustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

public class MyButton extends AppCompatButton {
    private int txtColor;
    private Drawable enabledBackground;
    private Drawable disabledBackground;

    public MyButton(Context context) {
        super(context);
        init(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // Initialize text color and backgrounds
        txtColor = ContextCompat.getColor(context, android.R.color.background_light);
        enabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button);
        disabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button_disable);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Set the background depending on whether the button is enabled or disabled
        setBackground(isEnabled() ? enabledBackground : disabledBackground);

        // Set the text color
        setTextColor(txtColor);

        // Set the text size
        setTextSize(12f);

        // Set the gravity (centered text)
        setGravity(Gravity.CENTER);

        // Set the button text depending on whether it is enabled or not
        setText(isEnabled() ? "Submit" : "Isi Dulu");
    }
}
