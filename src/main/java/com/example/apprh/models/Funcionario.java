package com.example.apprh.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String data;
    private String email;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.REMOVE)
    private List<Dependente>dependentes;

}
