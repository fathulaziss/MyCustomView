package com.example.mycustomview;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private MyButton myButton;
    private MyEditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.my_button);
        myEditText = findViewById(R.id.my_edit_text);

        setMyButtonEnable();

        // Add a TextWatcher to myEditText to listen for text changes
        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call setMyButtonEnable to enable or disable the button based on text input
                setMyButtonEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        });

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a Toast message with the text from myEditText
                Toast.makeText(MainActivity.this, myEditText.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setMyButtonEnable() {
        // Get the text from the MyEditText
        CharSequence result = myEditText.getText();

        // Enable or disable the button based on whether MyEditText has text
        myButton.setEnabled(result != null && result.length() > 0);
    }
}