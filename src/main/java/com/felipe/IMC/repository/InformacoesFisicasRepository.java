package com.felipe.IMC.repository;

import com.felipe.IMC.model.InformacoesFisicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacoesFisicasRepository extends JpaRepository<InformacoesFisicas, Long> {
}
