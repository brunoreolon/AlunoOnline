package com.example.alunoonline.dao;

import android.util.Log;

import com.example.alunoonline.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    public static long salvar(Aluno aluno){
        try {
            return aluno.save();
        }catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar o aluno: " + ex.getMessage());
            return -1;
        }
    }

    public static Aluno getAluno(int id){
        try {
            return Aluno.findById(Aluno.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar o aluno: " + ex.getMessage());
            return null;
        }
    }

    public static List<Aluno> retornaAlunos(String where, String[]whereArgs, String orderBy){
        List<Aluno> list = new ArrayList<>();
        try{
            list = Aluno.find(Aluno.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de alunos: " + ex.getMessage());

        }
        return list;
    }

    public static boolean delete(Aluno aluno){
        try{
            return Aluno.delete(aluno);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao deletar um aluno: " + ex.getMessage());
            return false;
        }
    }

}
