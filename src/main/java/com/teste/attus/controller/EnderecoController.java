package com.teste.attus.controller;

import com.teste.attus.dto.EnderecoDTO;
import com.teste.attus.entity.Endereco;
import com.teste.attus.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
@Tag(name = "attus-api")
public class EnderecoController {

    private EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Operation(summary = "Salvar um novo endereço", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/salvar")
    public ResponseEntity salvarEndereco(@RequestBody @Valid EnderecoDTO dto){
        enderecoService.salvarEndereco(dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar todos os endereços")
    @ApiResponse(responseCode = "200", description = "Lista de endereços recuperada com sucesso")
    @GetMapping("/listar")
    public ResponseEntity buscarEnderecos(){
        List<Endereco> enderecos = enderecoService.enderecos();
        return ResponseEntity.ok(enderecos);
    }

    @Operation(summary = "Buscar endereço por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity buscarEnderecoPorId(@PathVariable Long id){
        Endereco endereco = enderecoService.buscarEnderecoPorId(id);
        return ResponseEntity.ok(endereco);
    }

    @Operation(summary = "Atualizar endereço por ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizarEndereco(@RequestBody @Valid EnderecoDTO dto, @PathVariable Long id){
        enderecoService.atualizarEndereco(dto, id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar endereços por ID de Pessoa", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de endereços encontrada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada ou não possui endereços")
    })
    @GetMapping("/listar-por-pessoa/{idPessoa}")
    public ResponseEntity buscarEnderecoPorPessoa(@PathVariable Long idPessoa){
        List<Endereco> enderecos = enderecoService.buscarEnderecoPorPessoa(idPessoa);
        return ResponseEntity.ok(enderecos);
    }
}
