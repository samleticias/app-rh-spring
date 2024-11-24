package com.example.apprh.repository;

import com.example.apprh.models.Funcionario;
import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

    Funcionario findById(long id);
    Funcionario findByNome(String nome);
}
