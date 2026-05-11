package org.dzhailauvadinara.university.repository;

import org.dzhailauvadinara.university.entity.DzhailauvaDinaraStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface StudentRepository extends JpaRepository<DzhailauvaDinaraStudent, Long> {
    Page<DzhailauvaDinaraStudent> findByFirstNameContainingIgnoreCase(String name, Pageable p);
}
