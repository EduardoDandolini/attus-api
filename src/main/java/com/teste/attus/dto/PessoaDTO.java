package com.teste.attus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PessoaDTO(@NotEmpty(message = "Nome obrigatório") String nomeCompleto,
                        @NotNull(message = "Data de nascimento obrigatória") LocalDateTime dataNascimento) {
}
