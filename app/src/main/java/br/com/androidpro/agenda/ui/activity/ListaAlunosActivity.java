package br.com.androidpro.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.androidpro.agenda.R;
import br.com.androidpro.agenda.dao.AlunoDAO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();

    /* Para adicionar comportamentos nas Activities, exploramos o conceito de ciclo
    * de vida da Activity que é a forma na qual somos capazes de modificar os comportamentos
    * padrões controlados pelo Android */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Toast.makeText(this, "Israel Andrade", Toast.LENGTH_LONG).show();
        // TextView aluno = new TextView(this);
        // aluno.setText("Israel Andrade");

        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configureFabNovoAluno();

        // List<String> alunos = new ArrayList<>(Arrays.asList("Joyce", "Bianca", "Davi", "Bingo", "Otávio", "Kevin", "Luna"));
        // TextView primeiroAluno = findViewById(R.id.textView1);
        // TextView segundoAluno = findViewById(R.id.textView2);
        // TextView terceiroAluno = findViewById(R.id.textView3);
        // primeiroAluno.setText(alunos.get(0));
        // segundoAluno.setText(alunos.get(1));
        // terceiroAluno.setText(alunos.get(2));

        // listaDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos));
    }

    private void configureFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioAlunoActivity();
            }
        });
    }

    private void abreFormularioAlunoActivity() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));
    }
}
