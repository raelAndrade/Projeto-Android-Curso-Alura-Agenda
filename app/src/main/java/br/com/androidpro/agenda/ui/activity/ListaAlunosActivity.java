package br.com.androidpro.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.androidpro.agenda.R;
import br.com.androidpro.agenda.dao.AlunoDAO;
import br.com.androidpro.agenda.model.Aluno;
import br.com.androidpro.agenda.ui.adapter.ListaAlunosAdapter;

import static br.com.androidpro.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private ListaAlunosAdapter adapter;
    // private ArrayAdapter<Aluno> adapter; // Componente responsável por manter e atualização dos dados

    /* Para adicionar comportamentos nas Activities, exploramos o conceito de ciclo
    * de vida da Activity que é a forma na qual somos capazes de modificar os comportamentos
    * padrões controlados pelo Android */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configureFabNovoAluno();
        configuraLista();
        dao.salva(new Aluno("Israel", "(11)2222-3333", "israel@gmail.com"));
        dao.salva(new Aluno("Joyce", "(11)2222-3333", "joyce@gmail.com"));
        dao.salva(new Aluno("Bianca", "(11)2222-3333", "bianca@gmail.com"));
        dao.salva(new Aluno("Davi", "(11)2222-3333", "davi@gmail.com"));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // menu.add("Remover");
        // Fazendo o processo de inflar menu por meio do método inflate() do MenuInflater(), para usar o MenuInflater
        // Você pode usar o método getMenuInflater().
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        // CharSequence tituloDoMenu = item.getTitle();
        // if(tituloDoMenu.equals("Remover")){
        if(itemId == R.id.activity_lista_alunos_menu_remover){
            // Convertendo o MenuInfo via cast para AdapterView.AdapterContextMenuInfo, que considerando que as informações do menu está
            // relacionado ao AdapterView.
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            // Com essa nova referência acesse a posição do elemento a partir do atributo position, e então, pegue o aluno a partir do método
            // getItem() do adapter.
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);

            // Em seguida chame o método remove() enviando o aluno que foi selecionado
            remove(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    private void configureFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter(listaDeAlunos);
        configuraListenerDeCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
                // Log.i(">>>>>>>> Aluno:", "" + alunoEscolhido);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        // Tranferir dados para outras actitvity, a técnica de transferência via extra, exige que o dado seja serializável
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ListaAlunosAdapter(this);
        listaDeAlunos.setAdapter(adapter);
    }
}
