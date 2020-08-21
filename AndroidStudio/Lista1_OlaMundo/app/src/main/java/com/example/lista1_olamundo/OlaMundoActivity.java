package com.example.lista1_olamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.Log;

public class OlaMundoActivity extends AppCompatActivity {

    private Button copiar;
    private EditText campo;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // atividade
        Log.i("vida", "onCreate");
        copiar = (Button) findViewById(R.id.botao);
        campo = (EditText) findViewById(R.id.campo);
        texto = (TextView) findViewById(R.id.texto);

        copiar.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i("vida", "Botão clicando");
                texto.setText(campo.getText());
            }
        });

    }

    @Override
    protected  void onStart()
    {
        super.onStart();
        Log.i("vida", "onStart");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.i("vida", "onResume");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i("vida", "onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i("vida", "onResume");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i("vida", "onResume");
    }
}
