package com.example.alunoonline.activity.disciplina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.alunoonline.R;
import com.example.alunoonline.dao.AlunoDAO;
import com.example.alunoonline.dao.DisciplinaDAO;
import com.example.alunoonline.model.Aluno;
import com.example.alunoonline.model.Disciplina;
import com.example.alunoonline.util.Util;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroDisciplinaActivity extends AppCompatActivity {

    private TextInputEditText edCodigoDisciplina;
    private TextInputEditText edNomeDisciplina;
    private TextInputEditText edProfessor;
    private TextInputEditText edCargaHoraria;
    private LinearLayout lnCadastroDisciplinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        edCodigoDisciplina = findViewById(R.id.edCodigoDisciplina);
        edNomeDisciplina = findViewById(R.id.edNomeDisciplina);
        edProfessor = findViewById(R.id.edProfessor);
        edCargaHoraria = findViewById(R.id.edCargaHoraria);
        lnCadastroDisciplinas = findViewById(R.id.lnCadastroDisciplinas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastrar, menu);

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
        if(edCodigoDisciplina.getText().toString().equals("")){
            edCodigoDisciplina.setError("Informe o Código da disciplina!");
            edCodigoDisciplina.requestFocus();
            return;
        }

        if(edNomeDisciplina.getText().toString().equals("")){
            edNomeDisciplina.setError("Informe o Nome da disciplina!");
            edNomeDisciplina.requestFocus();
            return;
        }

        if(edProfessor.getText().toString().equals("")){
            edProfessor.setError("Informe o nome do professor!");
            edProfessor.requestFocus();
            return;
        }

        if(edCargaHoraria.getText().toString().equals("")){
            edCargaHoraria.setError("Informe a Carga horária da disciplina!");
            edCargaHoraria.requestFocus();
            return;
        }

        salvarDisciplina();
    }

    private void salvarDisciplina() {
        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo(Integer.parseInt(edCodigoDisciplina.getText().toString()));
        disciplina.setNome(edNomeDisciplina.getText().toString());
        disciplina.setProfessor(edProfessor.getText().toString());
        disciplina.setCargaHoraria(Integer.parseInt(edCargaHoraria.getText().toString()));

        if(DisciplinaDAO.salvar(disciplina) > 0) {
            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnCadastroDisciplinas, "Erro ao salvar a disciplina ("+disciplina.getNome()+") " +
                    "verifique o log", 0);
    }

    private void limparCampos() {
        edCodigoDisciplina.setText("");
        edNomeDisciplina.setText("");
        edProfessor.setText("");
        edCargaHoraria.setText("");
    }
}