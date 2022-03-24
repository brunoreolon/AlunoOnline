package com.example.alunoonline.activity.turma;

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

import com.example.alunoonline.R;
import com.example.alunoonline.dao.DisciplinaDAO;
import com.example.alunoonline.dao.TurmaDAO;
import com.example.alunoonline.model.Disciplina;
import com.example.alunoonline.model.Turma;
import com.example.alunoonline.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroTurmaActivity extends AppCompatActivity {

    private TextInputEditText edCodigoTurma;
    private LinearLayout lnCadastroTurmas;
    private MaterialSpinner spCursos;
    private MaterialSpinner spPeriodo;
    private MaterialSpinner spTurno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        edCodigoTurma = findViewById(R.id.edCodigoTurma);
        lnCadastroTurmas = findViewById(R.id.lnCadastroTurmas);

        iniciaSpinners();
    }

    private void iniciaSpinners() {
        spCursos = findViewById(R.id.spCurso);
        spPeriodo = findViewById(R.id.spPeriodo);
        spTurno = findViewById(R.id.spTurno);

        String cursos[] = new String[]{
                "Análise e Desenv. Sistemas",
                "Administração",
                "Ciências Contábeis",
                "Direito",
                "Farmácia",
                "Nutrição"
        };

        String periodos[] = new String[]{
                "1ª Série",
                "2ª Série",
                "3ª Série",
                "4ª Série"
        };

        String turnos[] = new String[]{
                "Matutino",
                "Vespertino",
                "Noturno"
        };

        ArrayAdapter adapterCursos = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, cursos);
        ArrayAdapter adapterPeriodo = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, periodos);
        ArrayAdapter adapterTurno = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, turnos);

        spCursos.setAdapter(adapterCursos);
        spPeriodo.setAdapter(adapterPeriodo);
        spTurno.setAdapter(adapterTurno);

        //Ação ao selecionar o item da lista
        spCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {

                    /*Button btADS = new Button(getBaseContext());
                    btADS.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                             LinearLayout.LayoutParams.WRAP_CONTENT));
                    btADS.setText("Botao ADS");
                    btADS.setBackgroundColor(getColor(R.color.teal_200));

                    llPrincipal.addView(btADS);*/
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
        if(edCodigoTurma.getText().toString().equals("")){
            edCodigoTurma.setError("Informe o Código da turma!");
            edCodigoTurma.requestFocus();
            return;
        }

        salvarTurma();
    }

    private void salvarTurma() {
        Turma turma = new Turma();
        turma.setCodigo(Integer.parseInt(edCodigoTurma.getText().toString()));

        if(TurmaDAO.salvar(turma) > 0) {
            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnCadastroTurmas, "Erro ao salvar a turma ("+turma.getCodigo()+") " +
                    "verifique o log", 0);
    }

    private void limparCampos() {
        edCodigoTurma.setText("");
    }
}