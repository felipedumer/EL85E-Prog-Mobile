package com.example.appcadastro;

import com.example.appcadastro.dao.AlunoDAO;
import com.example.appcadastro.modelo.Aluno;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

public class FormularioActivity extends Activity {

	private FormularioHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario);

		this.helper = new FormularioHelper(this);

		final Aluno alunoParaSerAlterado = (Aluno) getIntent().getSerializableExtra("alunoSelecionado");

		if (alunoParaSerAlterado != null) {
			helper.colocaAlunoNoFormulario(alunoParaSerAlterado);
		}

		final Button botao = (Button) findViewById(R.id.botao);
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Aluno aluno = helper.pegaAlunoDoFormulario();
				 AlunoDAO dao = new AlunoDAO(FormularioActivity.this);
				 if (alunoParaSerAlterado != null) {
					 aluno.setId(alunoParaSerAlterado.getId());
					 botao.setText("Atualizar");
					 dao.atualiza(aluno);
				 } else {
					 dao.insere(aluno);
				 }
				 dao.close();
				 finish();

/*
				Toast.makeText(
					FormularioActivity.this,
					helper.pegaAlunoDoFormulario().getNome(),
					Toast.LENGTH_LONG).show();
*/
			}
		});
	}

}
