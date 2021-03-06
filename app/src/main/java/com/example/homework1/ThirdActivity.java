package com.example.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Button button_goHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        linearLayout = findViewById(R.id.linearLayout2);
        Intent intent = getIntent();
        String str = getContent(intent);
        addTextView(str);

        button_goHome = findViewById(R.id.button_goHome);
        button_goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    private String getContent (Intent intent) {
        String str = "";
        ArrayList<String> content = intent.getStringArrayListExtra("content");
        for (int i = 0; !content.get(i).equals("0"); i++) {
            str += content.get(i);
        }
        return str;
    }

    private void addTextView (String str) {
        TextView textView = new TextView(this);
        textView.setText(str);
        textView.setTextSize(30);
        linearLayout.addView(textView);
    }
}
