package com.teste.attus.service;

import com.teste.attus.dto.EnderecoDTO;
import com.teste.attus.entity.Endereco;
import com.teste.attus.entity.Pessoa;
import com.teste.attus.exception.NotFoundException;
import com.teste.attus.repository.EnderecoRepository;
import com.teste.attus.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    private PessoaRepository pessoaRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Endereco salvarEndereco(EnderecoDTO dto){
        Pessoa pessoa = pessoaRepository.findById(dto.idPessoa())
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada com o ID fornecido: " + dto.idPessoa()));

        return enderecoRepository.save(Endereco.builder()
                .logradouro(dto.logradouro())
                .cep(dto.cep())
                .estado(dto.estado())
                .cidade(dto.cidade())
                .numero(dto.numero())
                .enderecoPrincipal(dto.principal())
                .pessoa(pessoa)
                .build());
    }

    @Transactional
    public List<Endereco> enderecos(){
        List<Endereco> enderecos = enderecoRepository.findAll();

        if (enderecos.isEmpty()) {
            throw new NotFoundException("Nenhum endereço encontrado");
        }

        return enderecos;
    }

    @Transactional
    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
    }

    @Transactional(rollbackFor = Exception.class)
    public Endereco atualizarEndereco(EnderecoDTO dto, Long id){
        return enderecoRepository.findById(id).map(endereco -> {
            endereco.setCidade(dto.cidade());
            endereco.setCep(dto.cep());
            endereco.setEstado(dto.estado());
            endereco.setLogradouro(dto.logradouro());
            endereco.setNumero(dto.numero());
            endereco.setEnderecoPrincipal(dto.principal());
            return enderecoRepository.save(endereco);
        }).orElseThrow(() -> new NotFoundException("Endereço não encontrada"));
    }

    @Transactional
    public List<Endereco> buscarEnderecoPorPessoa(Long idPessoa) {
        if (!pessoaRepository.existsById(idPessoa)) {
            throw new NotFoundException("Pessoa não encontrada com o ID fornecido: " + idPessoa);
        }
        List<Endereco> enderecos = enderecoRepository.buscarEnderecosPorPessoa(idPessoa);

        if (enderecos.isEmpty()) {
            throw new NotFoundException("Nenhum endereço encontrado para a pessoa com o ID fornecido: " + idPessoa);
        }
        return enderecos;
    }
}
