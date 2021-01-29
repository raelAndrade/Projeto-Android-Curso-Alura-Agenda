package br.com.androidpro.agenda;

import android.app.Application;

import br.com.androidpro.agenda.dao.AlunoDAO;
import br.com.androidpro.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Israel", "(11)2222-3333", "israel@gmail.com"));
        dao.salva(new Aluno("Joyce", "(11)2222-3333", "joyce@gmail.com"));
        dao.salva(new Aluno("Bianca", "(11)2222-3333", "bianca@gmail.com"));
        dao.salva(new Aluno("Davi", "(11)2222-3333", "davi@gmail.com"));
    }
}
