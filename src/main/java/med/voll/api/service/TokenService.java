package med.voll.api.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Service
public class TokenService {
    public String gerarToken(Usuario usuario) {

        try {
//            algoritmo escolhido para que nossa codificacao seja feita com base
//            2111 senha informado para nossa criacao token
            Algorithm algoritimoEscolhido = Algorithm.HMAC256("2111");
            return JWT.create()
//                    issuer = dono do token da api/ quem/ qual api ta implementando ele
                    .withIssuer("vall med")
//                    retornar no token informacoes do usuario pode retornar mais usando o withclaim
                    .withSubject(usuario.getLogin())
//                    aqui é a data da expiracao do nosso token
                    .withExpiresAt(Instant.from(dataExpriracao()))
//                    informamos qual algoritmo foi escolhido para codificar no caso oq falamos la em cima
                    .sign(algoritimoEscolhido);
        } catch (JWTCreationException exception) {
            throw  new RuntimeException("erro ao gerar token", exception);
        }


    }
//    metodo para contagem de dias ou horas para expirar o token
    public LocalDate dataExpriracao(){
        LocalDate dataAtual = LocalDate.now().plusDays(30);
        return dataAtual;
    }
}
