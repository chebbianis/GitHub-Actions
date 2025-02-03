package com.example.SpringBootExamStarter.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuestion;

    private String libelleQ;

    @Enumerated(EnumType.STRING)
    private Complexite complexite;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Reponse> list;

    @JsonIgnore
    @ManyToOne
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Reponse> reponses;


    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getLibelleQ() {
        return libelleQ;
    }

    public void setLibelleQ(String libelleQ) {
        this.libelleQ = libelleQ;
    }

    public Complexite getComplexite() {
        return complexite;
    }

    public void setComplexite(Complexite complexite) {
        this.complexite = complexite;
    }

    public List<Reponse> getList() {
        return list;
    }

    public void setList(List<Reponse> list) {
        this.list = list;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }
}