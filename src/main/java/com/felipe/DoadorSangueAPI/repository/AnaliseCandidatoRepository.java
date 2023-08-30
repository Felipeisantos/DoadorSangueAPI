package com.felipe.DoadorSangueAPI.repository;

import com.felipe.DoadorSangueAPI.entities.AnaliseCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseCandidatoRepository extends JpaRepository<AnaliseCandidato, Long> {

    @Query("SELECT coalesce(max(ac.id), 0) FROM AnaliseCandidato ac")
    Long getMaxId();
}
