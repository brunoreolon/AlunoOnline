package com.example.alunoonline.activity.alunos_turma;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alunoonline.R;
import com.example.alunoonline.adapters.TurmaNotaAdapter;
import com.example.alunoonline.dao.AlunoDAO;
import com.example.alunoonline.model.Aluno;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosTurmaActivity extends AppCompatActivity {

    private RecyclerView rvListaAlunosTurma;
    private LinearLayout llListaAlunosTurma;
    private TextInputLayout edBuscaAluno;
    private Aluno aluno;
    private LinearLayout llInfoAluno;
    private TextView tvNomeAluno;
    private TextView tvRaAluno;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_professor);

        edBuscaAluno = findViewById(R.id.edBuscaAluno);
        llListaAlunosTurma = findViewById(R.id.llListaAlunosTurma);
        rvListaAlunosTurma = findViewById(R.id.rvListaAlunosTurma);
//        llInfoAluno = findViewById(R.id.llInfoAluno);
//        tvNomeAluno = findViewById(R.id.tvNomeAluno);
//        tvRaAluno = findViewById(R.id.tvRaAluno);

        atualizaListaTurma();

    }

    public void adicionaAluno(View view) {
        System.out.println("=================");

        Intent intent = new Intent(this, CadastroAlunosTurma.class);
        startActivity(intent);
    }

    public void atualizaListaTurma() {
        List<Aluno> listaAlunos = new ArrayList<>();
        listaAlunos = AlunoDAO.retornaAlunos("", new String[]{}, "nome asc");
        Log.e("PHS", "Tamanho da lista: " + listaAlunos.size());

        rvListaAlunosTurma = findViewById(R.id.rvListaProfessores);
        TurmaNotaAdapter adapter = new TurmaNotaAdapter(listaAlunos, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaAlunosTurma.setLayoutManager(llm);
        rvListaAlunosTurma.setAdapter(adapter);
    }
}
