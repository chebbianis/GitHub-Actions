package com.example.SpringBootExamStarter.Service;

import com.example.SpringBootExamStarter.Entity.Candidat;
import com.example.SpringBootExamStarter.Entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.SpringBootExamStarter.Entity.Niveau;
import com.example.SpringBootExamStarter.Entity.Question;
import com.example.SpringBootExamStarter.Entity.Reponse;
import com.example.SpringBootExamStarter.Entity.Complexite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;

@Service
public class MainService {

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // Ajouter un candidat
    public Candidat ajouterCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    // Ajouter un Quiz
    public Quiz ajouterQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    // Affecter un quiz à un candidat
    public Quiz affecterQuizCandidat(String titreQuiz, Integer idCandidat) {
        // Rechercher le candidat par son ID
        Candidat candidat = candidatRepository.findById(idCandidat)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        // Rechercher le quiz par son titre
        Quiz quiz = quizRepository.findByTitreQuiz(titreQuiz);
        if (quiz == null) {
            throw new RuntimeException("Quiz non trouvé");
        }

        // Affecter le candidat au quiz
        quiz.getCandidats().add(candidat);

        // Incrémenter le nombre de quizs du candidat
        candidat.setNbQuiz(candidat.getNbQuiz() + 1);
        candidatRepository.save(candidat);

        // Sauvegarder et retourner le quiz mis à jour
        return quizRepository.save(quiz);
    }

    // Récupérer les candidats par spécialité et niveau
    public List<Candidat> recupererCandidat(String specialite, Niveau niveau) {
        return quizRepository.findCandidatsBySpecialiteAndNiveau(specialite, niveau);
    }

    // Ajouter une question avec ses réponses et l'affecter à un quiz
    public Question ajouterQuestEtRepEtAffecterQuestAQuiz(Question question, Integer idQuiz) {
        // Rechercher le quiz par son ID
        Quiz quiz = quizRepository.findById(idQuiz)
                .orElseThrow(() -> new RuntimeException("Quiz non trouvé"));

        // Affecter le quiz à la question
        question.setQuiz(quiz);

        // Pour chaque réponse dans la question
        if (question.getReponses() != null) {
            for (Reponse reponse : question.getReponses()) {
                // Affecter la question à la réponse
                reponse.setQuestion(question);
            }
        }

        // Sauvegarder la question (les réponses seront sauvegardées en cascade)
        return questionRepository.save(question);
    }

    @Scheduled(fixedRate = 30000) // Exécution toutes les 30 secondes
    public void recupererQuizPlusDifficile() {
        // Utiliser la nouvelle méthode qui charge les questions
        List<Quiz> quizzes = quizRepository.findAllWithQuestions();

        Quiz quizPlusDifficile = null;
        long maxQuestionsDifficiles = 0;

        // Parcourir tous les quiz pour trouver celui avec le plus de questions
        // difficiles
        for (Quiz quiz : quizzes) {
            long nombreQuestionsDifficiles = quiz.getQuestions().stream()
                    .filter(q -> q.getComplexite() == Complexite.DIFFICILE)
                    .count();

            if (nombreQuestionsDifficiles > maxQuestionsDifficiles) {
                maxQuestionsDifficiles = nombreQuestionsDifficiles;
                quizPlusDifficile = quiz;
            }
        }

        // Afficher le résultat
        if (quizPlusDifficile != null) {
            System.out.println("=== Quiz le plus difficile (vérifié à " +
                    new Date() + ") ===");
            System.out.println("Titre: " + quizPlusDifficile.getTitreQuiz());
            System.out.println("Spécialité: " + quizPlusDifficile.getSpecialite());
            System.out.println("Nombre de questions difficiles: " + maxQuestionsDifficiles);
        } else {
            System.out.println("Aucun quiz trouvé");
        }
    }

}
