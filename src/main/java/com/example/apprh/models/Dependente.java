package com.example.apprh.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String cpf;

    private String nome;
    private  String datanascimento;

    @ManyToOne
    private Funcionario funcionario;

}
