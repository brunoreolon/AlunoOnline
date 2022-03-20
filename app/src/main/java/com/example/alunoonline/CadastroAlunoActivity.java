package com.example.alunoonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.alunoonline.dao.AlunoDao;
import com.example.alunoonline.model.Aluno;
import com.example.alunoonline.util.CpfMask;
import com.example.alunoonline.util.Util;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroAlunoActivity extends AppCompatActivity {

    private TextInputEditText edRaAluno;
    private TextInputEditText edNomeAluno;
    private TextInputEditText edCpfAluno;
    private TextInputEditText edDtNascAluno;
    private TextInputEditText edDtMatAluno;
    private LinearLayout lnCadastroAlunos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        edRaAluno = findViewById(R.id.edRaAluno);
        edNomeAluno = findViewById(R.id.edNomeAluno);
        edCpfAluno = findViewById(R.id.edCpfAluno);
        edDtNascAluno = findViewById(R.id.edDtNascAluno);
        edDtMatAluno = findViewById(R.id.edDtMatAluno);
        lnCadastroAlunos = findViewById(R.id.lnCadastroAlunos);

        edCpfAluno.addTextChangedListener(CpfMask.insert(edCpfAluno));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_limpar:
                limparCampos();
                return true;
            case R.id.mn_salvar:
                validarCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void validarCampos() {
        if(edRaAluno.getText().toString().equals("")){
            edRaAluno.setError("Informe o RA do Aluno!");
            edRaAluno.requestFocus();
            return;
        }

        if(edNomeAluno.getText().toString().equals("")){
            edNomeAluno.setError("Informe o Nome do Aluno!");
            edNomeAluno.requestFocus();
            return;
        }

        if(edCpfAluno.getText().toString().equals("")){
            edCpfAluno.setError("Informe o CPF do Aluno!");
            edCpfAluno.requestFocus();
            return;
        }

        if(edDtNascAluno.getText().toString().equals("")){
            edDtNascAluno.setError("Informe a Data de nascimento do Aluno!");
            edDtNascAluno.requestFocus();
            return;
        }

        if(edDtMatAluno.getText().toString().equals("")){
            edDtMatAluno.setError("Informe a Data da matrícula do Aluno!");
            edDtMatAluno.requestFocus();
            return;
        }

        salvarAluno();
    }

    private void salvarAluno() {
        Aluno aluno = new Aluno();
        aluno.setRa(Integer.parseInt(edRaAluno.getText().toString()));
        aluno.setNome(edNomeAluno.getText().toString());
        aluno.setCpf(edCpfAluno.getText().toString());
        aluno.setDtNasc(edDtNascAluno.getText().toString());
        aluno.setDtMatricula(edDtMatAluno.getText().toString());
//        aluno.setCurso(spCursos.getSelectedItem().toString());
//        aluno.setPeriodo(spPeriodo.getSelectedItem().toString());

        if(AlunoDao.salvar(aluno) > 0){
            Util.customSnackBar(lnCadastroAlunos,
                    "Aluno (" + aluno.getNome() + ") " +
                            "Salvo com sucesso!",
                    1);
            finish();
        }else
            Util.customSnackBar(lnCadastroAlunos,
                    "Erro ao salvar o aluno (" + aluno.getNome() + ") " +
                            "Verifique o log",
                    0);
    }


    private void limparCampos() {
        edRaAluno.setText("");
        edNomeAluno.setText("");
        edCpfAluno.setText("");
        edDtNascAluno.setText("");
        edDtMatAluno.setText("");
    }
}