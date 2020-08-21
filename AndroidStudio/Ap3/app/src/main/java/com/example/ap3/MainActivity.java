package com.example.ap3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

   private EditText etPeso, etAltura;
   private Button btCalcular, btLimpar;
   private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPeso = (EditText) findViewById(R.id.etPeso);
        etAltura = (EditText) findViewById(R.id.etAltura);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        btCalcular = (Button) findViewById(R.id.btCalcular);
        btLimpar = (Button) findViewById(R.id.btLimpar);

        btLimpar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                etPeso.setText("");
                etAltura.setText("");
                tvResultado.setText("");
            }

        });

        btCalcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (etPeso.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, getString(R.string.erroPeso), Toast.LENGTH_LONG).show();
                    etPeso.requestFocus();
                    return;
                }
                if (etAltura.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.erroAltura), Toast.LENGTH_LONG).show();
                    etAltura.requestFocus();
                    return;
                }
                double peso = Double.parseDouble(etPeso.getText().toString());
                double altura = Double.parseDouble(etAltura.getText().toString());
                double imc = 0;

                if (Locale.getDefault().getDisplayLanguage().equals("en")){
                    imc = peso * 703 / Math.pow(altura, 2);
                } else {
                    imc = peso / Math.pow(altura, 2);
                }

                NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
                DecimalFormat df = (DecimalFormat) nf;
                tvResultado.setText(df.format(imc));
            }
        });
    }
}
