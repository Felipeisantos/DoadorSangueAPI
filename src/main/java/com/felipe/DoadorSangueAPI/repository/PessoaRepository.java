package com.felipe.DoadorSangueAPI.repository;

import com.felipe.DoadorSangueAPI.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}
