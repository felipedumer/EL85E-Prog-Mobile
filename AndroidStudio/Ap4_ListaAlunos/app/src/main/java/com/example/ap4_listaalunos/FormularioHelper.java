package com.example.ap4_listaalunos;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import modelo.Aluno;

public class FormularioHelper {

    private Aluno aluno;
    private EditText campoNome;
    private EditText campoEndereco;
    private EditText campoTelefone;
    private EditText campoSite;
    private SeekBar campoNota;
    private ImageView foto;

    public FormularioHelper(FormularioActivity activity) {
        aluno = new Aluno();
        campoNome =  (EditText) activity.findViewById(R.id.nome);
        campoEndereco =  (EditText) activity.findViewById(R.id.endereco);
        campoTelefone =  (EditText) activity.findViewById(R.id.telefone);
        campoSite =  (EditText) activity.findViewById(R.id.site);
        campoNota =  (SeekBar) activity.findViewById(R.id.nota);
        foto = (ImageView) activity.findViewById(R.id.foto);

    }

    public Aluno pegaAlunoDoFormulario() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setNota(campoNota.getProgress());

        return aluno;
    }
}
