package com.example.apprh.repository;

import com.example.apprh.models.Candidato;
import com.example.apprh.models.Vaga;
import org.springframework.data.repository.CrudRepository;

public interface CandidatoRepository extends CrudRepository<Candidato, Long> {

    Iterable<Candidato> findByVaga(Vaga vaga);
    Candidato findByRg(String rg);
    Candidato findById(long id);
}
