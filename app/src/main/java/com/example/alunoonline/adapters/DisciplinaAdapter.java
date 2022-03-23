package com.example.alunoonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alunoonline.R;
import com.example.alunoonline.model.Disciplina;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder>{

    private List<Disciplina> listaDisciplinas;
    private Context context;

    public DisciplinaAdapter(List<Disciplina> listaDisciplinas, Context context) {
        this.listaDisciplinas = listaDisciplinas;
        this.context = context;
    }

    public static class DisciplinaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edCodigoDisciplina;
        TextInputEditText edNomeDisciplina;
        TextInputEditText edProfessor;
        TextInputEditText edCargaHoraria;

        public DisciplinaViewHolder(@NonNull View itemView) {
            super(itemView);

            edCodigoDisciplina = (TextInputEditText)itemView.findViewById(R.id.edCodigoDisciplina);
            edNomeDisciplina = (TextInputEditText)itemView.findViewById(R.id.edNomeDisciplina);
            edProfessor =  (TextInputEditText)itemView.findViewById(R.id.edProfessor);
            edCargaHoraria = (TextInputEditText)itemView.findViewById(R.id.edCargaHoraria);
        }
    }

    @NonNull
    @Override
    public DisciplinaAdapter.DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        DisciplinaAdapter.DisciplinaViewHolder viewHolder = new DisciplinaAdapter.DisciplinaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaAdapter.DisciplinaViewHolder holder, int position) {
        Disciplina disciplina = listaDisciplinas.get(position);

        holder.edCodigoDisciplina.setText(String.valueOf(disciplina.getCodigo()));
        holder.edNomeDisciplina.setText(disciplina.getNome());
        holder.edProfessor.setText(disciplina.getProfessor());
        holder.edCargaHoraria.setText(disciplina.getCargaHoraria());
    }

    @Override
    public int getItemCount() {
        return listaDisciplinas.size();
    }
}