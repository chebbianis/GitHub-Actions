package com.example.SpringBootExamStarter.Controller;

import com.example.SpringBootExamStarter.Entity.Candidat;
import com.example.SpringBootExamStarter.Entity.Quiz;
import com.example.SpringBootExamStarter.Service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.SpringBootExamStarter.Entity.Niveau;
import java.util.List;
import com.example.SpringBootExamStarter.Entity.Question;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    @PostMapping("/ajouterCandidat")
    public ResponseEntity<Candidat> ajouterCandidat(@RequestBody Candidat candidat) {
        Candidat nouveauCandidat = mainService.ajouterCandidat(candidat);
        return new ResponseEntity<>(nouveauCandidat, HttpStatus.CREATED);
    }

    @PostMapping("/ajouterQuiz")
    public ResponseEntity<Quiz> ajouterQuiz(@RequestBody Quiz quiz) {
        Quiz nouveauQuiz = mainService.ajouterQuiz(quiz);
        return new ResponseEntity<>(nouveauQuiz, HttpStatus.CREATED);
    }

    @PostMapping("/affecterQuiz/{titreQuiz}/candidat/{idCandidat}")
    public ResponseEntity<Quiz> affecterQuizCandidat(
            @PathVariable String titreQuiz,
            @PathVariable Integer idCandidat) {
        Quiz quizAffecte = mainService.affecterQuizCandidat(titreQuiz, idCandidat);
        return new ResponseEntity<>(quizAffecte, HttpStatus.OK);
    }

    @GetMapping("/candidats/{specialite}/{niveau}")
    public ResponseEntity<List<Candidat>> recupererCandidats(
            @PathVariable String specialite,
            @PathVariable Niveau niveau) {
        List<Candidat> candidats = mainService.recupererCandidat(specialite, niveau);
        return new ResponseEntity<>(candidats, HttpStatus.OK);
    }

    /*
     * 
     * POST http://localhost:8080/questions/quiz/2
     * 
     * Body (JSON):
     * {
     * "libelleQ": "exception",
     * "complexite": "DIFFICILE",
     * "reponses": [
     * {
     * "libelleR": "try...catch"
     * }
     * ]
     * }
     */

    @PostMapping("/questions/quiz/{idQuiz}")
    public ResponseEntity<Question> ajouterQuestionEtReponses(
            @RequestBody Question question,
            @PathVariable Integer idQuiz) {
        Question nouvelleQuestion = mainService.ajouterQuestEtRepEtAffecterQuestAQuiz(question, idQuiz);
        return new ResponseEntity<>(nouvelleQuestion, HttpStatus.CREATED);
    }

}