package org.dzhailauvadinara.university.repository;

import org.dzhailauvadinara.university.entity.DzhailauvaDinaraEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<DzhailauvaDinaraEnrollment, Long> {
    List<DzhailauvaDinaraEnrollment> findByStudentId(Long studentId);
}
