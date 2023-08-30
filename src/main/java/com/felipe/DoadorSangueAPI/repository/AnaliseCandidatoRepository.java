package com.felipe.DoadorSangueAPI.repository;

import com.felipe.DoadorSangueAPI.entities.AnaliseCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseCandidatoRepository extends JpaRepository<AnaliseCandidato, Long> {

}
