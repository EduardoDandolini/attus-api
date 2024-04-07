package com.teste.attus.service;

import com.teste.attus.dto.PessoaDTO;
import com.teste.attus.entity.Pessoa;
import com.teste.attus.exception.NotFoundException;
import com.teste.attus.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Pessoa salvarPessoa(PessoaDTO dto){
        return pessoaRepository.save(Pessoa.builder()
                .nomeCompleto(dto.nomeCompleto())
                .dataNascimento(dto.dataNascimento())
                .build());
    }
    @Transactional(readOnly = true)
    public List<Pessoa> pessoas(){
        List<Pessoa> pessoas = pessoaRepository.findAll();

        if (pessoas.isEmpty()) {
            throw new NotFoundException("Nenhuma pessoa encontrada");
        }

        return pessoas;
    }

    @Transactional(readOnly = true)
    public Pessoa buscarPessoaPorId(Long id){
        return pessoaRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
    }

    @Transactional(rollbackFor = Exception.class)
    public Pessoa atualizarPessoa(PessoaDTO dto, Long id){
        return pessoaRepository.findById(id).map(pessoa -> {
            pessoa.setNomeCompleto(dto.nomeCompleto());
            pessoa.setDataNascimento(dto.dataNascimento());
            return pessoaRepository.save(pessoa);
        }).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
    }
}
