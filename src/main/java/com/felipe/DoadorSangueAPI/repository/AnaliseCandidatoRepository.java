package com.felipe.DoadorSangueAPI.repository;

import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatos;
import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatosInfo;
import com.felipe.DoadorSangueAPI.entities.AnaliseCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnaliseCandidatoRepository extends JpaRepository<AnaliseCandidato, Long> {
    @Query("select ac from AnaliseCandidato ac ")
    List<AnaliseCandidato> buscarTodosIdsAnalise();
}
