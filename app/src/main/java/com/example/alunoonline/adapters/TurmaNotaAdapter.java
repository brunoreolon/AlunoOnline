package com.example.alunoonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alunoonline.R;
import com.example.alunoonline.model.Aluno;
import com.example.alunoonline.model.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class TurmaNotaAdapter extends RecyclerView.Adapter<TurmaNotaAdapter.TurmaNotaViewHolder> {
    private List<Aluno> listaAlunos;
    private Context context;

    public TurmaNotaAdapter(List<Aluno> listaAlunos, Context context) {
        this.listaAlunos = listaAlunos;
        this.context = context;
    }

    public static class TurmaNotaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edRaAluno;
        TextInputEditText edNomeAluno;
        TextInputEditText edSituacaoAluno;

        public TurmaNotaViewHolder(@NonNull View itemView) {
            super(itemView);

            edRaAluno = (TextInputEditText) itemView.findViewById(R.id.edRaAluno);
            edNomeAluno = (TextInputEditText) itemView.findViewById(R.id.edNomeAluno);
            edSituacaoAluno = (TextInputEditText) itemView.findViewById(R.id.edSituacaoAluno);
        }
    }

    @Override
    public TurmaNotaAdapter.TurmaNotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_alunos_turma, parent, false);

        TurmaNotaAdapter.TurmaNotaViewHolder viewHolder = new TurmaNotaAdapter.TurmaNotaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TurmaNotaAdapter.TurmaNotaViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);

        holder.edRaAluno.setText(String.valueOf(aluno.getRa()));
        holder.edNomeAluno.setText(aluno.getNome());
//        holder.edSituacaoAluno.setText(aluno.());
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }
}
