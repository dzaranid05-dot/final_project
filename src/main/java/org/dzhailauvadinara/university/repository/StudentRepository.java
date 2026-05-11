package org.dzhailauvadinara.university.repository;

import org.dzhailauvadinara.university.entity.DzhailauvaDinaraStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<DzhailauvaDinaraStudent, Long> {
    Page<DzhailauvaDinaraStudent> findByFirstNameContainingIgnoreCase(String name, Pageable p);
}
