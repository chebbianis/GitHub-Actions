package com.example.SpringBootExamStarter.Service;

import com.example.SpringBootExamStarter.Entity.Quiz;
import com.example.SpringBootExamStarter.Entity.Candidat;
import com.example.SpringBootExamStarter.Entity.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    Quiz findByTitreQuiz(String titreQuiz);

    // Trouver les quiz par spécialité avec date future
    List<Quiz> findBySpecialiteAndDateQuizAfter(String specialite, Date date);

    @Query("SELECT DISTINCT c FROM Quiz q JOIN q.candidats c " +
            "WHERE q.specialite = :specialite " +
            "AND q.dateQuiz > CURRENT_DATE " +
            "AND c.niveau = :niveau")
    List<Candidat> findCandidatsBySpecialiteAndNiveau(
            @Param("specialite") String specialite,
            @Param("niveau") Niveau niveau);

    @Query("SELECT DISTINCT q FROM Quiz q LEFT JOIN FETCH q.questions")
    List<Quiz> findAllWithQuestions();

}
