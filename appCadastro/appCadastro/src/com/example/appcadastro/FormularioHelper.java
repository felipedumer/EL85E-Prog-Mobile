package com.example.appcadastro;

import com.example.appcadastro.modelo.Aluno;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

public class FormularioHelper {

	private Aluno aluno;
	private EditText campoNome;
	private EditText campoTelefone;
	private EditText campoEndereco;
	private EditText campoSite;
	private ImageView foto;
	private SeekBar campoNota;

	public FormularioHelper(FormularioActivity activity) {
		aluno = new Aluno();
		campoNome = (EditText) activity.findViewById(R.id.nome);
		campoTelefone = (EditText) activity.findViewById(R.id.telefone);
		campoEndereco = (EditText) activity.findViewById(R.id.endereco);
		campoSite = (EditText) activity.findViewById(R.id.site);
		foto = (ImageView) activity.findViewById(R.id.foto);
		campoNota = (SeekBar) activity.findViewById(R.id.nota);
	}

	public Aluno pegaAlunoDoFormulario() {
		aluno.setNome(campoNome.getText().toString());
		aluno.setTelefone(campoTelefone.getText().toString());
		aluno.setEndereco(campoEndereco.getText().toString());
		aluno.setSite(campoSite.getText().toString());
		aluno.setNota(campoNota.getProgress());
		return aluno;
	}

	public void colocaAlunoNoFormulario(Aluno alunoParaSerAlterado) {
		aluno = alunoParaSerAlterado;
		campoNome.setText(alunoParaSerAlterado.getNome());
		campoTelefone.setText(alunoParaSerAlterado.getTelefone());
		campoEndereco.setText(alunoParaSerAlterado.getEndereco());
		campoSite.setText(alunoParaSerAlterado.getSite());
		campoNota.setProgress(alunoParaSerAlterado.getNota());
		if (aluno.getCaminhoFoto() != null) {
			carregaImagem(aluno.getCaminhoFoto());
		}
	}

	private void carregaImagem(String caminhoFoto) {
		aluno.setCaminhoFoto(caminhoFoto);
		Bitmap imagem = BitmapFactory.decodeFile(caminhoFoto);
		Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 100, 100, true);
		foto.setImageBitmap(imagemReduzida);
	}

}
