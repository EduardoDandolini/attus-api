package com.teste.attus.controller;

import com.teste.attus.dto.PessoaDTO;
import com.teste.attus.entity.Pessoa;
import com.teste.attus.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController(PessoaService service) {
        this.pessoaService = service;
    }

    @Operation(summary = "Salvar uma nova pessoa", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa salva com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/salvar")
    public ResponseEntity salvarPessoa(@RequestBody @Valid PessoaDTO dto){
        pessoaService.salvarPessoa(dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar todas as pessoas", method = "GET")
    @ApiResponse(responseCode = "200", description = "Lista de pessoas recuperada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity buscarPessoas(){
        List<Pessoa> pessoas = pessoaService.pessoas();
        return ResponseEntity.ok(pessoas);
    }

    @Operation(summary = "Buscar pessoa por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity buscarPessoaPorId(@PathVariable Long id){
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);
        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Atualizar pessoa por ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizarPessoa(@RequestBody @Valid PessoaDTO dto, @PathVariable Long id){
        pessoaService.atualizarPessoa(dto, id);
        return ResponseEntity.ok().build();
    }
}
