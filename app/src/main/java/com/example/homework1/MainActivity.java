package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Button button_next;
    private static final String API_URL = "http://madlibz.herokuapp.com/api/random";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                client.addHeader("Accept", "application/json");
                client.get(API_URL, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        // 200 status code
                        Log.d("API RESPONSE", new String(responseBody));

                        try {
                            JSONObject json = new JSONObject(new String(responseBody));
                            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                            intent.putExtra("title", json.getString("title"));
                            JSONArray blanks_json = json.getJSONArray("blanks");
                            ArrayList<String> blanks_str = new ArrayList<>();
                            for (int i = 0; i < blanks_json.length(); i++) {
                                blanks_str.add(blanks_json.get(i).toString());
                            }
                            intent.putStringArrayListExtra("blanks", blanks_str);
                            JSONArray values_json = json.getJSONArray("value");
                            ArrayList<String> values_str = new ArrayList<>();
                            for (int i = 0; i < values_json.length(); i++) {
                                values_str.add(values_json.get(i).toString());
                            }
                            intent.putStringArrayListExtra("value", values_str);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        // other status codes
                        Log.e("API ERROR", new String(responseBody));
                    }
                });
            }
        });
    }
}