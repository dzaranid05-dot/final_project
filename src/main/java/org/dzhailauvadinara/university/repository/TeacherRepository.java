package org.dzhailauvadinara.university.repository;

import org.dzhailauvadinara.university.entity.DzhailauvaDinaraTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<DzhailauvaDinaraTeacher, Long> {
    Optional<DzhailauvaDinaraTeacher> findByUserId(Long userId);
}
