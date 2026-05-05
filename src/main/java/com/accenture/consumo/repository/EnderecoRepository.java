package com.accenture.consumo.repository;

import com.accenture.consumo.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    List<EnderecoEntity> findByCepOrderByConsultadoEmDesc(String cep);
    Optional<EnderecoEntity> findFirstByCepOrderByConsultadoEmDesc(String cep);
}
