package com.example.projeto_cadastro_felipe;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText textoTarefa;
    private Button botaoAdicionar;
    private ListView listaTarefas;
    private SQLiteDatabase bancoDados;

    private ArrayAdapter<String> itensAdaptador;
    private ArrayList<String> itens;
    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Recuperar componentes
            textoTarefa = (EditText) findViewById(R.id.textTarefa);
            botaoAdicionar = (Button) findViewById(R.id.botaoAdicionarTerefa);

            // Lista de tarefas
            listaTarefas = (ListView) findViewById(R.id.ListaTarefas);

            // Banco de dados
            bancoDados = openOrCreateDatabase("appcadastro", MODE_PRIVATE, null);

            // Criação de tabelas
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTEGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR)");

            // Botao para adicionar tarefas
            botaoAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String textoDigitado = textoTarefa.getText().toString();
                    salvarTarefa(textoDigitado);
                }
            });

            // Botao para excluir tarefas
            listaTarefas.setLongClickable(true);
            listaTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids.get( position ) );
                    return true;
                }
            });

            // Botao para informar ao clique
            listaTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "Pressione por mais tempo para remover a tarefa!", Toast.LENGTH_SHORT).show();
                }
            });

            // Recuperar tarefas
            recuperarTarefas();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // Método para salvar a tarefa
    private void salvarTarefa(String texto)
    {
        try {

            if (texto.equals("") || (texto.equals(" "))) {
                Toast.makeText(MainActivity.this, "Digite uma tarefa", Toast.LENGTH_SHORT).show();
            } else {
                bancoDados.execSQL("INSERT INTO tarefas (tarefa) VALUES('" + texto + "') ");
                Toast.makeText(MainActivity.this, "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show();
                recuperarTarefas();
                textoTarefa.setText("");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Método para reuperar as tarefas do banco
    private void recuperarTarefas()
    {
        try
        {
            // Recupera as tarefas
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM tarefas ORDER BY id DESC", null);

            // Recupera os Ids das colunas
            int indiceColunaID = cursor.getColumnIndex("id");
            int indiceColunaTarefa =  cursor.getColumnIndex("tarefa");

            // Cria adaptador
            itens = new ArrayList<String>();
            ids = new ArrayList<Integer>();

            itensAdaptador = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    itens);
            listaTarefas.setAdapter(itensAdaptador);

            // Lista as tarefas
            cursor.moveToFirst();

            while (cursor != null)
            {
                itens.add(cursor.getString(indiceColunaTarefa));
                ids.add(Integer.parseInt(cursor.getString(indiceColunaID)));

                cursor.moveToNext();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Método para remover tarefas
    private void removerTarefa(Integer id)
    {
        try
        {
            bancoDados.execSQL("DELETE FROM tarefas WHERE id=" + id);
            recuperarTarefas();
            Toast.makeText(MainActivity.this, "Tarefa removida com sucesso", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
