package com.example.exemplo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.activity_main);

        // Solicita o android para abrir o textView
        TextView view = new TextView(this);
        view.setText("Olá mano do céu!");
        setContentView(view);

    }
}
