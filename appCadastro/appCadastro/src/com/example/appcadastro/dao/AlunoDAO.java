package com.example.appcadastro.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.appcadastro.modelo.Aluno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlunoDAO extends SQLiteOpenHelper {

	private static final String DATABASE = "NomeDoBanco";
	private static final int VERSAO = 1;
	private static final String TABELA = "Alunos";

	public AlunoDAO(Context context) {
		super(context, DATABASE, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		String sql = "CREATE TABLE " + TABELA + " ("
			+ "id INTEGER PRIMARY KEY, "
			+ "nome TEXT UNIQUE NOT NULL, "
			+ "telefone TEXT, "
			+ "endereco TEXT, "
			+ "site TEXT, "
			+ "caminhoFoto TEXT, "
			+ "nota INT"
			+ ");";
		database.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int versaoNova) {
		String sql = "DROP TABLE IF EXISTS " + TABELA +";";
		database.execSQL(sql);
		onCreate(database);
	}

	public void insere(Aluno aluno) {
		ContentValues cv = new ContentValues();
		cv.put("nome", aluno.getNome());
		cv.put("telefone", aluno.getTelefone());
		cv.put("endereco", aluno.getEndereco());
		cv.put("site", aluno.getSite());
		cv.put("caminhoFoto", aluno.getCaminhoFoto());
		cv.put("nota", aluno.getNota());
		getWritableDatabase().insert(TABELA, null, cv);
	}

	public List<Aluno> getLista() {
		List<Aluno> alunos = new ArrayList<Aluno>();

		String sql = "SELECT * FROM " + TABELA + ";";
		Cursor c = getReadableDatabase().rawQuery(sql, null);
		while (c.moveToNext()) {
			Aluno aluno = new Aluno();
			aluno.setId(c.getLong(c.getColumnIndex("id")));
			aluno.setNome(c.getString(c.getColumnIndex("nome")));
			aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
			aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
			aluno.setSite(c.getString(c.getColumnIndex("site")));
			aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
			aluno.setNota(c.getInt(c.getColumnIndex("nota")));
			alunos.add(aluno);
		}

		return alunos;
	}

	public void deleta(Aluno aluno) {
		String[] args = {aluno.getId().toString()};
		getWritableDatabase().delete(TABELA, "id=?", args);
	}

	public void atualiza(Aluno aluno) {
		ContentValues cv = new ContentValues();
		cv.put("nome", aluno.getNome());
		cv.put("telefone", aluno.getTelefone());
		cv.put("endereco", aluno.getEndereco());
		cv.put("site", aluno.getSite());
		cv.put("caminhoFoto", aluno.getCaminhoFoto());
		cv.put("nota", aluno.getNota());
		String[] args = {aluno.getId().toString()};
		getWritableDatabase().update(TABELA, cv, "id=?", args);
	}

}
