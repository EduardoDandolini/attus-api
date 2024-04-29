package com.teste.attus.dto;

import com.teste.attus.entity.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(@NotEmpty(message = "Logradouro é obrigatório") String logradouro,
                          @NotEmpty(message = "CEP é obrigatório") String cep,
                          @NotEmpty(message = "Estado é obrigatório") String estado,
                          @NotEmpty(message = "Cidade é obrigatória") String cidade,
                          @NotNull(message = "Número é obrigatório") String numero,
                          @NotNull(message = "Informe se o endereço é principal ou não") Boolean principal,
                          @NotNull(message = "ID é obrigatório") Long idPessoa) {
}
