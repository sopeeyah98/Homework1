package com.example.homework1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private TextView text_title;
    private LinearLayout linearLayout;
    private Button button_generate;
    private ArrayList<String> blanks;
    private int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        text_title = findViewById(R.id.text_title);
        text_title.setText(intent.getStringExtra("title"));

        blanks = intent.getStringArrayListExtra("blanks");
        final ArrayList<String> output = intent.getStringArrayListExtra("value");
        linearLayout = findViewById(R.id.linearLayout);
        addBlanks();

        i = 0;

        button_generate = findViewById(R.id.button_generate);
        button_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SecondActivity.this, ThirdActivity.class);
                while (i < linearLayout.getChildCount()) {
                    EditText editText1 = (EditText) linearLayout.getChildAt(i);
                    if (!editText1.getText().toString().isEmpty())
                        output.add(i*2 + 1, editText1.getText().toString().trim());
                    else
                        break;
                    i++;
                }
                if (i != linearLayout.getChildCount()) {
                    Log.e("error", "incomplete");
                    errorPopUp();
                } else {
                    intent1.putStringArrayListExtra("content", output);
                    startActivity(intent1);
                }
            }
        });
    }

    private void addBlanks() {
        for (int i = 0; i < blanks.size(); i++) {
            EditText editText = new EditText(this);
            CharSequence blank = blanks.get(i);
            editText.setHint(blank);
            linearLayout.addView(editText);
        }
    }

    private void errorPopUp() {
        AlertDialog dialog = new AlertDialog.Builder(SecondActivity.this, R.style.Theme_AppCompat_Light)
                .setTitle("Error")
                .setMessage("All fields must be filled")
                .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }
}
