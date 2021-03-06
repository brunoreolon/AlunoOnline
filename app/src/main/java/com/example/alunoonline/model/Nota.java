package com.example.alunoonline.model;

import com.orm.SugarRecord;

public class Nota extends SugarRecord {

    private float nota1;
    private float nota2;
    private float media;
    private Aluno aluno;

    public Nota() {
    }

    public Nota(float nota1) {
        this.nota1 = nota1;
    }

    public Nota(float nota1, float nota2) {
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
