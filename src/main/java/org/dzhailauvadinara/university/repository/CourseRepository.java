package org.dzhailauvadinara.university.repository;

import org.dzhailauvadinara.university.entity.DzhailauvaDinaraCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<DzhailauvaDinaraCourse, Long> {
    List<DzhailauvaDinaraCourse> findByTitleContainingIgnoreCase(String title);
    List<DzhailauvaDinaraCourse> findByCredits(Integer credits);
}
