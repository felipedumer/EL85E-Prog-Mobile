package com.example.ap2_gasolina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // variáveis
    private Button bresultado, bsair;
    private EditText tvgasolina, tvetanol, tcgasolina, tcetanol;
    private double dvgasolina, dvetanol, dcgasolina, dcetanol;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pegando os valores
        tvgasolina = (EditText) findViewById(R.id.vgasolina);
        tvetanol = (EditText) findViewById(R.id.vetanol);
        tcgasolina = (EditText) findViewById(R.id.cgasolina);
        tcetanol = (EditText) findViewById(R.id.cetanol);
        // pegando botoes
        bresultado = (Button) findViewById(R.id.bresultado);
        bsair = (Button) findViewById(R.id.bsair);
        resultado = (TextView) findViewById(R.id.resultado);

        // botao resultado clique curto
        bresultado.setOnClickListener(new View.OnClickListener()
                                     {
                                     @Override
                                     public void onClick(View v) {
                                         try {
                                             dvgasolina = Double.parseDouble(tvgasolina.getText().toString());
                                         } catch (NumberFormatException e) {
                                             tvgasolina.setError("Insira um valor válido");
                                         }
                                         try {
                                             dcgasolina = Double.parseDouble(tcgasolina.getText().toString());
                                         } catch (NumberFormatException e) {
                                             tcgasolina.setError("Insira um valor válido");
                                         }
                                         try {
                                             dvetanol = Double.parseDouble(tvetanol.getText().toString());
                                         } catch (NumberFormatException e) {
                                             tvetanol.setError("Insira um valor válido");
                                         }
                                         try {
                                             dcetanol = Double.parseDouble(tcetanol.getText().toString());
                                         } catch (NumberFormatException e) {
                                             tcetanol.setError("Insira um valor válido");
                                         }

                                         if ((dvgasolina / dcgasolina) < (dvetanol / dcetanol)) {
                                             resultado.setText("Gasolina");
                                         } else if ((dvgasolina / dcgasolina) > (dvetanol / dcetanol)) {
                                             resultado.setText("Etanol");
                                         } else {
                                             resultado.setText("Valor inválido inserido");
                                         }
                                     }
                                 });

        // clique longo resultado
        bresultado.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                Toast.makeText(MainActivity.this,
                        "Calcula a melhor opção de abastecimento", Toast.LENGTH_LONG).show();
                return true;
            }
        }

        );
        // botao sair
        bsair.setOnClickListener(new View.OnClickListener()
                                 {
                                     @Override
                                     public void onClick(View v)
                                     {
                                        finish();
                                     }
                                 });

        // clique longo sair
        bsair.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                Toast.makeText(MainActivity.this,
                        "Fecha o aplicativo", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }





}
