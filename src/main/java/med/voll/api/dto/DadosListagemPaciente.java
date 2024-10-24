package med.voll.api.dto;

import med.voll.api.model.Paciente;

public record DadosListagemPaciente(String nome, String email, String cpf, Long id ) {
    public DadosListagemPaciente(Paciente dados){
        this(dados.getNome(),
         dados.getEmail(),
        dados.getCpf(),dados.getId());
    }

}
