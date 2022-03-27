package com.example.alunoonline.activity.nota;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.alunoonline.R;
import com.example.alunoonline.dao.AlunoDAO;
import com.example.alunoonline.dao.NotaDAO;
import com.example.alunoonline.dao.TurmaDAO;
import com.example.alunoonline.model.Aluno;
import com.example.alunoonline.model.Nota;
import com.example.alunoonline.model.Turma;
import com.example.alunoonline.util.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class LancamentoNotasActivity extends AppCompatActivity {

    private MaterialSpinner spRegime;
    private MaterialSpinner spAlunos;
    private TextInputLayout edNota1;
    private TextInputLayout edNota2;
    private LinearLayout lnLancamentoNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_notas);

        edNota1 = findViewById(R.id.edNota1);
        edNota2 = findViewById(R.id.edNota2);
        lnLancamentoNotas = findViewById(R.id.lnLancamentoNotas);

        iniciaSpinners();
    }

    private void iniciaSpinners() {
        spRegime = findViewById(R.id.spRegime);
        spAlunos = findViewById(R.id.spAlunos);

        String regimes[] = new String[]{
                "Semestral",
                "Anual",
        };

        List<Aluno> alunos = new ArrayList<>();
        alunos = AlunoDAO.retornaAlunos("", new String[]{}, "nome asc");

        ArrayAdapter adapterRegime = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, regimes);

        ArrayAdapter adapterAlunos = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alunos);

        spRegime.setAdapter(adapterRegime);
        spAlunos.setAdapter(adapterAlunos);

        spRegime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    edNota1.setVisibility(View.VISIBLE);
                    edNota1.setHint("1ª Nota");

                    edNota2.setVisibility(View.VISIBLE);
                }

                if (i == 1) {
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
        if (spAlunos.getSelectedItem() == null) {
            Util.customSnackBar(lnLancamentoNotas, "Selecione um aluno!", 0);
            return;
        }

        if (spRegime.getSelectedItem() == null) {
            Util.customSnackBar(lnLancamentoNotas, "Selecione um regime!", 0);
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
        nota.setNota1(Float.parseFloat(edNota1.getEditText().getText().toString()));

        if (edNota2.getVisibility() != View.INVISIBLE) {
            nota.setNota2(Float.parseFloat(edNota2.getEditText().getText().toString()));
        }

        nota.setNota2(0);

        int ra = ((Aluno) spAlunos.getSelectedItem()).getRa();

        nota.setAluno(AlunoDAO.getAluno(ra));

        if (NotaDAO.salvar(nota) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnLancamentoNotas, "Erro ao salvar a turma (" + nota.getId() + ") " +
                    "verifique o log", 0);
    }

    private void limparCampos() {
        edNota1.getEditText().setText("");
        edNota2.getEditText().setText("");
    }
}