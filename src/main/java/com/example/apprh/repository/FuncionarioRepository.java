package com.example.apprh.repository;

import com.example.apprh.models.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

    Funcionario findById(long id);
    Funcionario findByNome(String nome);

    // Query para a busca
    @Query(value = "select u from Funcionario u where u.nome like %?1%")
    List<Funcionario> findByNomes(String nome);
}
