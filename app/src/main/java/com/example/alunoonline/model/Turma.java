package com.example.alunoonline.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Turma extends SugarRecord {

    private int codigo;
    private String curso;
    private String periodo;
    private String turno;

    public Turma() {
    }

    public Turma(int codigo, String curso, String periodo, String turno) {
        this.codigo = codigo;
        this.curso = curso;
        this.periodo = periodo;
        this.turno = turno;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return codigo == turma.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
