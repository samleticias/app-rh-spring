package com.example.apprh.repository;

import com.example.apprh.models.Dependente;
import com.example.apprh.models.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DependenteRepository extends CrudRepository<Dependente, Long> {

    Iterable<Dependente> findByFuncionario(Funcionario funcionario);
    Dependente findByCpf(String cpf);
    Dependente findById(long id);

    // Query para a busca
    @Query(value = "select u from Dependente u where u.nome like %?1%")
    List<Dependente> findByNomesDependentes(String nome);
}
