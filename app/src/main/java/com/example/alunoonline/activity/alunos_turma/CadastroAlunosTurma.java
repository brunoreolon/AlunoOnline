package com.example.alunoonline.activity.alunos_turma;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alunoonline.R;
import com.example.alunoonline.dao.AlunoDAO;
import com.example.alunoonline.model.Aluno;
import com.example.alunoonline.util.Util;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroAlunosTurma extends AppCompatActivity {

    private Aluno aluno;
    private TextInputLayout edBuscaAluno;
    private LinearLayout llInfoAluno;
    private LinearLayout llCadastroAlunosTurma;
    private TextView tvNomeAluno;
    private TextView tvRaAluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        edBuscaAluno = findViewById(R.id.edBuscaAluno);
        llInfoAluno = findViewById(R.id.llBuscaAluno);
        llCadastroAlunosTurma = findViewById(R.id.llCadastroAlunosTurma);
        tvNomeAluno = findViewById(R.id.tvNomeAlunoBusca);
        tvRaAluno = findViewById(R.id.tvRaAlunoBusca);
    }

    public void buscarAluno(View view) {
        if (edBuscaAluno.getEditText().getText().toString().equals("")) {
            edBuscaAluno.setError("Informe o Ra!");
            edBuscaAluno.requestFocus();
            return;
        }

        aluno = AlunoDAO.getAlunoRa(Integer.parseInt(edBuscaAluno.getEditText().getText().toString()));

        if (aluno == null) {
            Util.customSnackBar(llCadastroAlunosTurma, "Aluno não encontrado!", 0);
            return;
        }

        edBuscaAluno.getEditText().setText("");
        llInfoAluno.setVisibility(View.VISIBLE);
        tvNomeAluno.setText("Nome: " + aluno.getNome().toString());
        tvRaAluno.setText("RA: " + String.valueOf(aluno.getRa()));
    }

    public void adicionaAluno(View view) {

    }
}
