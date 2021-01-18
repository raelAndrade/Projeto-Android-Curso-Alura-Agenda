package br.com.androidpro.agenda.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.androidpro.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if(alunoEncontrado != null){
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        Aluno alunoEncontrado = null;
        for (Aluno a : alunos){
            if(a.getId() == aluno.getId()) {
               return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() { return new ArrayList<>(alunos); }
}
