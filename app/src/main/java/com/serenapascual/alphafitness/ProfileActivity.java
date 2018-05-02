package com.serenapascual.alphafitness;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        final EditText weightEditText = (EditText) findViewById(R.id.weightEditText);

        // Disable blinking cursor when EditText not in focus
        // Re-enable when EditText is in focus
        nameEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == nameEditText.getId()) {
                    nameEditText.setCursorVisible(true);
                }
            }
        });

        // Lose focus when user hits enter key
        nameEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    nameEditText.setFocusable(false);
                    nameEditText.setFocusableInTouchMode(true);
                    return true;
                } else {
                    return false;
                }
            }
        });

        // Set constant text " lbs" to end of user-editable weight
        weightEditText.setText("170 lbs");
        Selection.setSelection(weightEditText.getText(), weightEditText.getText().length());
        weightEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().endsWith(" lbs")) {
                    weightEditText.setText(" lbs");
                    Selection.setSelection(weightEditText.getText(), weightEditText.getText().length());
                }
            }
        });

        // Lose focus when user hits enter key
        weightEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    weightEditText.setFocusable(false);
                    weightEditText.setFocusableInTouchMode(true);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
