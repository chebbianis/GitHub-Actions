package com.example.SpringBootExamStarter.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuiz;

    private String titreQuiz;

    private String specialite;

    @Temporal(TemporalType.DATE)
    private Date dateQuiz;

    @JsonIgnore
    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;

    @ManyToMany
    private List<Candidat> candidats;

    public Quiz(String titreQuiz, String specialite, Date dateQuiz) {
        this.titreQuiz = titreQuiz;
        this.specialite = specialite;
        this.dateQuiz = dateQuiz;
    }

    public Quiz() {

    }

    public Integer getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(Integer idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getTitreQuiz() {
        return titreQuiz;
    }

    public void setTitreQuiz(String titreQuiz) {
        this.titreQuiz = titreQuiz;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Date getDateQuiz() {
        return dateQuiz;
    }

    public void setDateQuiz(Date dateQuiz) {
        this.dateQuiz = dateQuiz;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Candidat> getCandidats() {
        return candidats;
    }

    public void setCandidats(List<Candidat> candidats) {
        this.candidats = candidats;
    }

    public String getTitre() {
        return titreQuiz;
    }

    public void setTitre(String titre) {
        this.titreQuiz = titre;
    }
}