package com.felipe.DoadorSangueAPI.repository;

import com.felipe.DoadorSangueAPI.model.InformacoesFisicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacoesFisicasRepository extends JpaRepository<InformacoesFisicas, Long> {
}
