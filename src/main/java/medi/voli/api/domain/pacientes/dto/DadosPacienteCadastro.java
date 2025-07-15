package medi.voli.api.domain.pacientes.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medi.voli.api.domain.enderecos.dto.DadosEndereco;

public record DadosPacienteCadastro(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,


        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}", message = "formato errado de cpf")
        String cpf,

        @NotNull @Valid
        DadosEndereco endereco
) {
}
