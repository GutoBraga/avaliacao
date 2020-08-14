package com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_modulo")
@Data
public class ModuloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modulo")
    private Long idModulo;

    @Column(name = "ds_nome")
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_instrutor")
    private InstrutorEntity instrutor;

}
