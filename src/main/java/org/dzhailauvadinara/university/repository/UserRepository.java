package org.dzhailauvadinara.university.repository;


import org.dzhailauvadinara.university.entity.DzhailauvaDinaraUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<DzhailauvaDinaraUser, Long> {
    Optional<DzhailauvaDinaraUser> findByEmail(String email);
}
