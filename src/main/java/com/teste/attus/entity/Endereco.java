package com.teste.attus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private String cep;

    private String estado;

    private String cidade;

    private String numero;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    private Boolean enderecoPrincipal;

}
