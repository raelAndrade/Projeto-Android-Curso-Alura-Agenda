package br.com.androidpro.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.androidpro.agenda.R;
import br.com.androidpro.agenda.dao.AlunoDAO;
import br.com.androidpro.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();

    /* Para adicionar comportamentos nas Activities, exploramos o conceito de ciclo
    * de vida da Activity que é a forma na qual somos capazes de modificar os comportamentos
    * padrões controlados pelo Android */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configureFabNovoAluno();
        dao.salva(new Aluno("Israel", "(11)2222-3333", "israel@gmail.com"));
        dao.salva(new Aluno("Joyce", "(11)2222-3333", "joyce@gmail.com"));
        dao.salva(new Aluno("Bianca", "(11)2222-3333", "bianca@gmail.com"));
        dao.salva(new Aluno("Davi", "(11)2222-3333", "davi@gmail.com"));
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
        final List<Aluno> alunos = dao.todos();
        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos));

        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = alunos.get(position);
                // Log.i(">>>>>>>> Aluno:", "" + alunoEscolhido);
                Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
                // Tranferir dados para outras actitvity, a técnica de transferência via extra, exige que o dado seja serializável
                vaiParaFormularioActivity.putExtra("aluno", alunoEscolhido);
                startActivity(vaiParaFormularioActivity);
            }
        });
    }
}
