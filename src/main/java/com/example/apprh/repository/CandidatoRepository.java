package com.example.apprh.repository;

import com.example.apprh.models.Candidato;
import com.example.apprh.models.Vaga;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidatoRepository extends CrudRepository<Candidato, Long> {

    Iterable<Candidato> findByVaga(Vaga vaga);
    Candidato findByRg(String rg);
    Candidato findById(long id);

    // Query para a busca
    @Query(value = "select u from Candidato u where u.nomeCandidato like %?1%")
    List<Candidato> findByNomesCandidatos(String nomeCandidato);
}
