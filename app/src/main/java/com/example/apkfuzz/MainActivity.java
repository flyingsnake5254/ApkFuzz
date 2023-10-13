package com.example.apkfuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apkfuzz.ipc_fuzzing.IntentFuzzing;

public class MainActivity extends AppCompatActivity {

    Button bIntentFuzz, bBinderFuzz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();
        buttonListener();
    }

    @Override
    protected void onResume() {
        super.onResume();

        bIntentFuzz.post(new Runnable() {
            @Override
            public void run() {
                int width = bIntentFuzz.getWidth();
                bIntentFuzz.setHeight(width);
                bBinderFuzz.setHeight(width);
            }
        });
    }

    private void initial() {
        bIntentFuzz = (Button) findViewById(R.id.b_intent_fuzz);
        bBinderFuzz = (Button) findViewById(R.id.b_binder_fuzz);
    }

    private void buttonListener() {
        bIntentFuzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, IntentFuzzing.class));
            }
        });
    }

}