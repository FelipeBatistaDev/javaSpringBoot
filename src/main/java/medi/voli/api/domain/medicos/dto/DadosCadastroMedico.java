package medi.voli.api.domain.medicos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medi.voli.api.domain.enderecos.dto.DadosEndereco;
import medi.voli.api.domain.medicos.dto.enums.Especialidade;

public record DadosCadastroMedico(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotBlank
        String telefone,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid
        DadosEndereco endereco
) {

}
