package com.example.apprh.repository;

import com.example.apprh.models.Vaga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VagaRepository extends CrudRepository<Vaga, Long> {

    Vaga findByCodigo(long codigo);

    List<Vaga> findByNome(String nome);
}
