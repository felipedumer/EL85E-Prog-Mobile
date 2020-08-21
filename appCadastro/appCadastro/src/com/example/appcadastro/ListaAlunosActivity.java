package com.example.appcadastro;

import java.util.List;

import com.example.appcadastro.dao.AlunoDAO;
import com.example.appcadastro.modelo.Aluno;

import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

public class ListaAlunosActivity extends Activity {

	private ListView listaAlunos;

	private Aluno aluno;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        registerForContextMenu(listaAlunos);

        listaAlunos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {

				Aluno alunoParaSerAlterado = (Aluno) adapter.getItemAtPosition(posicao);
				Intent irParaOFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
				irParaOFormulario.putExtra("alunoSelecionado", alunoParaSerAlterado);
				startActivity(irParaOFormulario);

			}
		});

        listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {

				aluno = (Aluno) adapter.getItemAtPosition(posicao);

				return false;
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}

	public void carregaLista() {
		AlunoDAO dao = new AlunoDAO(this);
		List<Aluno> alunos = dao.getLista();
		dao.close();
		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno> (
			this,
			android.R.layout.simple_list_item_1,
			alunos);
		listaAlunos.setAdapter(adapter);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_novo:
			Intent intent = new Intent(this, FormularioActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {

		MenuItem ligar = menu.add("Ligar");
		ligar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaTelaDeDiscagem = new Intent(Intent.ACTION_CALL);
				Uri telefoneDoAluno = Uri.parse("tel:" + aluno.getTelefone());
				irParaTelaDeDiscagem.setData(telefoneDoAluno);
				startActivity(irParaTelaDeDiscagem);
				return false;
			}
		});

		MenuItem sms = menu.add("Enviar SMS");
		sms.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaSms = new Intent(Intent.ACTION_VIEW);
				Uri telefoneDoAluno = Uri.parse("sms:" + aluno.getTelefone());
				irParaSms.setData(telefoneDoAluno);
				irParaSms.putExtra("sms_body", "Mensagem");
				startActivity(irParaSms);
				return false;
			}
		});

		MenuItem mapa = menu.add("Achar no mapa");
		mapa.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaMapa = new Intent(Intent.ACTION_VIEW);
				Uri enderecoDoAluno = Uri.parse("geo:0,0?z=14&q="+aluno.getEndereco());
				irParaMapa.setData(enderecoDoAluno);
				startActivity(irParaMapa);
				return false;
			}
		});

		MenuItem site = menu.add("Navegar no site");
		site.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaOSiteDoAluno = new Intent(Intent.ACTION_VIEW);
				Uri siteDoAluno = Uri.parse("http://" + aluno.getSite());
				irParaOSiteDoAluno.setData(siteDoAluno);
				startActivity(irParaOSiteDoAluno);
				return false;
			}
		});

		MenuItem deletar = menu.add("Deletar");
		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
				dao.deleta(aluno);
				dao.close();
				carregaLista();
				return false;
			}
		});

		MenuItem email = menu.add("Enviar e-mail");
		Intent intentEmail = new Intent(Intent.ACTION_SEND);
		intentEmail.setType("message/rfc822");
		intentEmail.putExtra(Intent.EXTRA_EMAIL,
		new String[] { "belmonte@utfpr.edu.br" });
		intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Assunto");
		intentEmail.putExtra(Intent.EXTRA_TEXT, "Teste de envio de email!");
		email.setIntent(intentEmail);

		super.onCreateContextMenu(menu, v, menuInfo);
	}


}
