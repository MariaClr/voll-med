package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.Especialidade;

public record DadosCadastroPaciente(@NotBlank(message = "campo obrigatorio")
                                    String nome,
                                    @NotBlank(message = "campo obrigatorio")
                                    @Email(message = "Formato de email incorreto")
                                    String email,
                                    @NotBlank(message = "campo obrigatorio")
                                    String telefone,
                                    @NotBlank(message = "campo obrigatorio")
                                    @Pattern(regexp = ("\\d{11}"))
                                    String cpf,

                                    @NotNull(message = "campo obrigatorio") @Valid
                                    DadosEndereco endereco) {
}
