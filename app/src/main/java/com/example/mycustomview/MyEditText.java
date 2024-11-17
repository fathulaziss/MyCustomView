package com.example.mycustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

public class MyEditText extends AppCompatEditText implements View.OnTouchListener {
    private Drawable clearButtonImage;

    public MyEditText(Context context) {
        super(context);
        init(context, null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        clearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_close_black_24dp);
        setOnTouchListener(this);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do Nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.length() > 0) {
                    showClearButton();
                } else {
                    hideClearButton();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // DO nothing
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setHint("Masukkan nama Anda");
        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
    }

    // Show the clear button
    private void showClearButton() {
        setButtonDrawables(null, null, clearButtonImage, null);
    }

    // Hide the clear button
    private void hideClearButton() {
        setButtonDrawables(null, null, null, null);
    }

    // Set the drawables for the start, top, end, and bottom of the EditText
    private void setButtonDrawables(Drawable startOfTheText, Drawable topOfTheText, Drawable endOfTheText, Drawable bottomOfTheText) {
        setCompoundDrawablesWithIntrinsicBounds(startOfTheText, topOfTheText, endOfTheText, bottomOfTheText);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (getCompoundDrawables()[2] != null) { // Check if clear button is visible
            float clearButtonStart;
            float clearButtonEnd;
            boolean isClearButtonClicked = false;

            // Determine the position of the clear button based on layout direction
            if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                clearButtonEnd = (clearButtonImage.getIntrinsicWidth() + getPaddingStart());
                if (motionEvent.getX() < clearButtonEnd) {
                    isClearButtonClicked = true;
                }
            } else {
                clearButtonStart = (getWidth() - getPaddingEnd() - clearButtonImage.getIntrinsicWidth());
                if (motionEvent.getX() > clearButtonStart) {
                    isClearButtonClicked = true;
                }
            }

            // Handle clear button click actions
            if (isClearButtonClicked) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        clearButtonImage = ContextCompat.getDrawable(getContext(), R.drawable.ic_close_black_24dp);
                        showClearButton();
                        return true;
                    case MotionEvent.ACTION_UP:
                        clearButtonImage = ContextCompat.getDrawable(getContext(), R.drawable.ic_close_black_24dp);
                        if (getText() != null) {
                            getText().clear(); // Clear the text in the EditText
                        }
                        hideClearButton();
                        return true;
                    default:
                        return false;
                }
            }
        }
        return false;
    }
}
