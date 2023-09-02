package com.felipe.DoadorSangueAPI.repository;

import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatos;
import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatosInfo;
import com.felipe.DoadorSangueAPI.entities.AnaliseCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnaliseCandidatoRepository extends JpaRepository<AnaliseCandidato, Long> {
    @Query("select ac from AnaliseCandidato ac where ac.solicitacaoUsuario.id = :id ")
    List<AnaliseCandidato> buscarTodosIdsAnalisePorUsuarioId(@Param("id") Long id);


    @Query("select ac.id from AnaliseCandidato ac where ac.solicitacaoUsuario.id = :userId and ac.id = :id ")
    AnaliseCandidato buscarAnalisePorIdEUsuarioId(@Param("userId") Long userId, @Param("id") Long id);
}
