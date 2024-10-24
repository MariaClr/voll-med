package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DadosAutenticacao;
import med.voll.api.dto.DtoTokenGerado;
import med.voll.api.model.Usuario;
import med.voll.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
//        precismos trasnformar nosso dto em uma classe do spring pq ele esepera um objeto dessa classe para fazer a autenticacao
        var dtoSpring = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
//        aqui pegarmos quem vai fazer o processo de autenticacao é ele quem chama lembrando que configuramaos esse metodo classe configuracoes
        var authentication = manager.authenticate(dtoSpring);

//        gerando token e enviando com o usuario encotnrado atraves da autenticacao(authentication)
//        ou seja o gerar token ele gera normal mas voce pode passar o usuario para ele retornar um token contendo tmb os dados do usuario
//        fazemos o castinha de userdetail para usuario pq usairo implementa a interface userdetail

        String token = tokenService.gerarToken((Usuario)authentication.getPrincipal());

//        retornando o token
        return   ResponseEntity.ok(new DtoTokenGerado(token));


    }
}
