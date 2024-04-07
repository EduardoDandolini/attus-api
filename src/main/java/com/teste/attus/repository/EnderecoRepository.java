package com.teste.attus.repository;

import com.teste.attus.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("select e from Endereco e where e.pessoa.id = :idPessoa")
    List<Endereco> buscarEnderecosPorPessoa(@Param("idPessoa") Long idPessoa);

}
