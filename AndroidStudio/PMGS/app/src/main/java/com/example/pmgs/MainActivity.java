package com.example.pmgs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();

    private Button bgerarpalavra, bsair;
    private TextView palavrapmgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bsair = (Button) findViewById(R.id.sair);
        bgerarpalavra = (Button) findViewById(R.id.gerarpalavra);
        palavrapmgs = (TextView) findViewById(R.id.vpmgs);

        bgerarpalavra.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                char[] pmgs = {'P', 'M', 'G', 'S'};

                for (int first = 0; first < 4; first++)
                {
                    int second = rand.nextInt(4);

                    char temp = pmgs[first];
                    pmgs[first] = pmgs[second];
                    pmgs[second] = temp;
                }

                String novopmgs = String.valueOf(pmgs);
                palavrapmgs.setText(novopmgs);
            }
        });


        // botao sair
        bsair.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}
