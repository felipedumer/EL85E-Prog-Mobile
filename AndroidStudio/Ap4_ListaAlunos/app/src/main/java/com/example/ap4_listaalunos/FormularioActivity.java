package com.example.ap4_listaalunos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import dao.AlunoDAO;
import modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        this.helper = new FormularioHelper(this);
        Button botao = (Button) findViewById(R.id.botao);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = helper.pegaAlunoDoFormulario();
                AlunoDAO dao = new AlunoDAO(FormularioActivity.this);
                dao.insere(aluno);
                dao.close();
                finish();

                /*Toast.makeText(
                        FormularioActivity.this,
                        helper.pegaAlunoDoFormulario().getNome(),
                        Toast.LENGTH_LONG).show();
                */
            }
        });
    }
}
