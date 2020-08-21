package com.example.ap4_listaalunos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import dao.AlunoDAO;
import modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*String[] alunos = {"João", "Maria", "José"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, alunos);

        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        listaAlunos.setAdapter(adapter); */

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Toast.makeText(ListaAlunosActivity.this, "Posicao selecionada: " + posicao, Toast.LENGTH_LONG).show();
            }
        });

        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
                String aluno = (String) adapter.getItemAtPosition(posicao);
                Toast.makeText(
                        ListaAlunosActivity.this,
                        "Clique longo: " + aluno, Toast.LENGTH_LONG).show();
                return false; // pergunta de prova, oq faz o return true do item long click = consome o método clickview
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void carregaLista(){
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();

        new ArrayAdapter<Aluno>(
                this,
                android.R.layout.simple_list_item_1,
                alunos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("Ligar");
        menu.add("Enviar SMS");
        menu.add("Achar no mapa");
        menu.add("Navegar no site");

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deleta(aluno);
                dao.close();

                return false;
            }
        });
    }



}
