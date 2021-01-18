package br.com.androidpro.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.androidpro.agenda.R;
import br.com.androidpro.agenda.dao.AlunoDAO;
import br.com.androidpro.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        // Pegar o dados digitado do formulário
        inicializacaoDosCampos();
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        // O método setOnClickListener() e implemente a interface View.OnClickListener() utilizando a técnica de classe anônima.
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(FormularioAlunoActivity.this, "Cliquei no botão salvar", Toast.LENGTH_SHORT).show();
                // Pega o conteúdo da cada EditText e atribui nas variáveis: nome, email e telefone
                Aluno alunoCriado = criaAluno();
                // Toast.makeText(FormularioAlunoActivity.this, alunoCriado.getNome() + " - " + alunoCriado.getEmail() + " - " + alunoCriado.getTelefone(),Toast.LENGTH_SHORT).show();
                // startActivity(new Intent(FormularioAlunoActivity.this, ListaAlunosActivity.class));
                salva(alunoCriado);
            }
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salva(Aluno aluno) {
        dao.salva(aluno);
        finish();
    }

    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String email = campoTelefone.getText().toString();
        String telefone = campoEmail.getText().toString();

        return new Aluno(nome, telefone, email);
    }
}