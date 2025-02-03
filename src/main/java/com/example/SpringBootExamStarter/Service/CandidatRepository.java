package com.example.SpringBootExamStarter.Service;

import com.example.SpringBootExamStarter.Entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
}
