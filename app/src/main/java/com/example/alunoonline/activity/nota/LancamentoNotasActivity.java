package com.example.alunoonline.activity.nota;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alunoonline.R;
import com.example.alunoonline.dao.AlunoDAO;
import com.example.alunoonline.dao.DisciplinaDAO;
import com.example.alunoonline.dao.NotaDAO;
import com.example.alunoonline.dao.TurmaDAO;
import com.example.alunoonline.model.Aluno;
import com.example.alunoonline.model.Disciplina;
import com.example.alunoonline.model.Nota;
import com.example.alunoonline.model.Turma;
import com.example.alunoonline.util.Util;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class LancamentoNotasActivity extends AppCompatActivity {

    private TextInputLayout edBuscaAluno;

    private TextView tvNomeAluno;
    private TextView tvRaAluno;

    private MaterialSpinner spCurso;
    private MaterialSpinner spDisciplinas;

    private TextInputLayout edNota1;
    private TextInputLayout edNota2;

    private LinearLayout lnLancamentoNotas;
    private LinearLayout llBuscaAluno;

    private Aluno aluno;
    private String regimeTurma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_notas);

        edBuscaAluno = findViewById(R.id.edBuscaAluno);
        tvNomeAluno = findViewById(R.id.tvNomeAlunoBusca);
        tvRaAluno = findViewById(R.id.tvRaAlunoBusca);
        edNota1 = findViewById(R.id.edNota1);
        edNota2 = findViewById(R.id.edNota2);
        lnLancamentoNotas = findViewById(R.id.lnLancamentoNotas);
        llBuscaAluno = findViewById(R.id.llBuscaAluno);
    }

    public void buscarAluno(View view) {

        if (edBuscaAluno.getEditText().getText().toString().equals("")) {
            edBuscaAluno.setError("Informe o Ra!");
            edBuscaAluno.requestFocus();
            return;
        }

        aluno = AlunoDAO.getAlunoRa(Integer.parseInt(edBuscaAluno.getEditText().getText().toString()));

        if (aluno == null) {
            Util.customSnackBar(lnLancamentoNotas, "Aluno não encontrado!", 0);

            llBuscaAluno.setVisibility(View.GONE);
            return;
        }

        edBuscaAluno.getEditText().setText("");
        llBuscaAluno.setVisibility(View.VISIBLE);
        tvNomeAluno.setText("Nome: " + aluno.getNome().toString());
        tvRaAluno.setText("RA: " + String.valueOf(aluno.getRa()));

        iniciaSpinners();
    }

    private void iniciaSpinners() {
        spCurso = findViewById(R.id.spCurso);
        spDisciplinas = findViewById(R.id.spDisciplinas);

        String curso = aluno.getCurso();

        List<Disciplina> disciplinas = DisciplinaDAO.getDisciplinasCurso(curso);

        ArrayAdapter adapterCurso = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, new String[]{curso});

        ArrayAdapter adapterDisciplinas = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, disciplinas);

        spCurso.setAdapter(adapterCurso);
        spDisciplinas.setAdapter(adapterDisciplinas);

        spCurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Turma turma = TurmaDAO.getRegimeTurmaAlunoCadastrado(aluno.getRa(), 123);
                System.out.println("========= " + turma.getCodigo());
                System.out.println("--------- " + turma.getRegime());

                if (turma.getRegime() == "Semestral") {
                    edNota1.setVisibility(View.VISIBLE);
                    edNota1.setHint("1ª Nota");

                    edNota2.setVisibility(View.VISIBLE);
                }

                if (turma.getRegime() == "Anual") {
                    edNota1.setVisibility(View.VISIBLE);
                    edNota1.setHint("Nota");

                    edNota2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastrar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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
        if (spCurso.getSelectedItem() == null) {
            Util.customSnackBar(lnLancamentoNotas, "Selecione um curso!", 0);
            return;
        }

        if (edNota1.getVisibility() == View.VISIBLE &&
                edNota1.getEditText().getText().toString().equals("")) {
            edNota1.setError("Informe a nota!");
            edNota1.requestFocus();
            return;
        }

        if (edNota2.getVisibility() == View.VISIBLE &&
                edNota2.getEditText().getText().toString().equals("")) {
            edNota2.setError("Informe a nota!");
            edNota2.requestFocus();
            return;
        }

        salvarNota();
    }

    private void salvarNota() {
        Nota nota = new Nota();

        float n1 = Float.parseFloat(edNota1.getEditText().getText().toString());

        nota.setNota1(n1);
        nota.setMedia(n1);

        if (edNota2.getVisibility() == View.VISIBLE) {
            float n2 = Float.parseFloat(edNota2.getEditText().getText().toString());

            nota.setNota2(n2);

            nota.setMedia((n1 + n2) / 2);
        }

        System.out.println(">>>>>> " + nota.getMedia());
        nota.setAluno(aluno);

        if (NotaDAO.salvar(nota) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnLancamentoNotas, "Erro ao salvar a nota (" + nota.getId() + ") " +
                    "verifique o log", 0);
    }

    private void limparCampos() {
        edBuscaAluno.getEditText().setText("");
        edNota1.getEditText().setText("");
        edNota2.getEditText().setText("");
    }
}